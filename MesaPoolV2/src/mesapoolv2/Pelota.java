package mesapoolv2;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota {
    private Color color;
    private Mesa parent;
    private Vector2 parentSize;
    private Vector2 parentPos;
    protected Vector2 position;
    protected Vector2 velocity;
    protected double friccion = 0.11;
    protected double r;

    public boolean active;
    
    public Pelota(Mesa parent, Vector2 position, Color color, double r){
        this.r = r;
        this.parent = parent;
        this.color = color;
        //this.position = Vector2.add(position, parent.getPosition());
        this.position = position;
        this.parentSize = parent.getSize();
        this.parentPos = parent.getPosition();
        this.velocity = new Vector2(0, 0);
        this.active = false;
    }
    
    public void update(){

        //Colision con la mesa
        if(position.x > parentSize.x - (int)(r) - parent.getBordeWidth()){
            position.x = parentSize.x - (int)(r) - parent.getBordeWidth();
            velocity.x = - velocity.x; 
        }
        if(position.y > parentSize.y - (int)(r) - parent.getBordeWidth()){
            position.y = parentSize.y - (int)(r) - parent.getBordeWidth();
            velocity.y = - velocity.y;
        }
        if(position.x < 0 + (int)(r) + parent.getBordeWidth()){
            position.x = 0 + (int)(r) + parent.getBordeWidth();
            velocity.x = - velocity.x;
        }
        if(position.y < 0 + (int)(r) + parent.getBordeWidth()){
            position.y = 0 + (int)(r) + parent.getBordeWidth();
            velocity.y = - velocity.y;
        }
        
        
        
        double m0 =  velocity.magnitude();
        double m1 = m0 - friccion;
        if(m1 <= 0){
               velocity.x = 0;
            velocity.y = 0;
        }
        else{
            velocity = Vector2.scale(velocity, m1 / m0);
        }
        
        position.x += velocity.x * FPS.getDeltaTime();
        position.y += velocity.y * FPS.getDeltaTime();
        
        //System.out.println("m0: " + m0 + " m1: " + m1 + " vx: " + velocity.x);
        //System.out.println("x:" + position.x + " y:" + position.y);
    }
    
    
    public void setPosition(Vector2 position){
        this.position.x = position.x;
        this.position.y = position.y;
    }
    
    public void paint(Graphics g){
        g.setColor(color);
        g.fillOval((int)(position.x + parentPos.x - r), (int)(position.y + parentPos.y - r), (int)(2*r), (int)(2*r));
        
    }
    
    public boolean checkTroneria(Hoyo hoyo){
        if(Vector2.dist(position, hoyo.getPosition()) < 2*r){
            System.out.println("Aa");
            return true;
        }
        return false;
    }

    public boolean checkCollition(Pelota other){
        if(Vector2.dist(this.position, other.position) <= 2*r){
            
            //separarPelotasEnColision(other);
            resolveCollition(other);
            //separarPelotasEnColision(other);
            return true;
        }
        return false;
    }
    
    public void separarPelotasEnColision(Pelota other){
        //FIXME
        Vector2 puntoMedio = Vector2.puntoMedio(this.position, other.position);
        Vector2 separacion = Vector2.sub(this.position, other.position);
        separacion.normalize();

        this.position = new Vector2(puntoMedio.x + separacion.x * r, puntoMedio.y + separacion.y * r);
        other.position = new Vector2(puntoMedio.x - separacion.x * r, puntoMedio.y - separacion.y * r);
    }
    
    private void resolveCollition(Pelota other){
        Vector2 v1 = this.velocity; 
        Vector2 v2 = other.velocity;
        Vector2 x1 = this.position;
        Vector2 x2 = other.position;
        
        this.velocity = compute_velocity(v1, v2, x1, x2);
        other.velocity = compute_velocity(v2, v1, x2, x1);
        System.out.println("tv: " + compute_velocity(v1, v2, x1, x2).x + " ov: " + compute_velocity(v2, v1, x2, x1).x);
        
    }
    
    private Vector2 compute_velocity(Vector2 v1, Vector2 v2, Vector2 x1, Vector2 x2){
        Vector2 x1_x2 = Vector2.sub(x1, x2);
        Vector2 v1_v2 = Vector2.sub(v1, v2);
        double dot_v_x = Vector2.dot(v1_v2, x1_x2);
        double norm_squared = Math.pow(x1_x2.magnitude(), 2);
        return Vector2.sub(v1, Vector2.scale(x1_x2, dot_v_x/norm_squared));
    }
    
    public void setColor(Color color){
        this.color = color;
    }

    public Vector2 getGlobalPosition(){
        return Vector2.copy(Vector2.add(position, parentPos));
    }
}
