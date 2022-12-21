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
    
    public int id;
    public boolean active;
    
    public Pelota(Mesa parent, Color color, double r, int id){
        this.id = id;
        this.r = r;
        this.parent = parent;
        this.color = color;
        this.position = new Vector2(0, 0);
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
    
    public void setVelocity(Vector2 velocity){
        this.velocity.x = velocity.x;
        this.velocity.y = velocity.y;
    }
    
    public void paint(Graphics g){
        g.setColor(color);
        g.fillOval((int)(position.x + parentPos.x - r), (int)(position.y + parentPos.y - r), (int)(2*r), (int)(2*r));
        if(id == 0){
            return;
        }
        
        if(id >= 9){
            g.setColor(Color.WHITE);
            g.fillOval((int)(position.x + parentPos.x - r), (int)(position.y + parentPos.y - r), (int)(2*r), (int)(2*r));
            g.setColor(color);
            g.fillArc((int)(position.x + parentPos.x - r), (int)(position.y + parentPos.y - r), (int)(2*r), (int)(2*r), 140, 80);
            g.fillArc((int)(position.x + parentPos.x - r), (int)(position.y + parentPos.y - r), (int)(2*r), (int)(2*r), 320, 80);
            g.fillRect((int)(position.x + parentPos.x - r + 0.234*r), (int)(position.y + parentPos.y - r + 0.3572*r), (int)(1.5321*r), (int)(r*0.64278761*2));
        }
        
        g.setColor(Color.WHITE);
        g.fillOval((int)(position.x + parentPos.x - r/2), (int)(position.y + parentPos.y - r/2), (int)(r), (int)(r));
        g.setColor(Color.BLACK);
        if(id < 10){
            g.drawString(Integer.toString(id), (int)(position.x + parentPos.x - 2), (int)(position.y + parentPos.y + r/2 - 4));
        }
        else{
            g.drawString(Integer.toString(id), (int)(position.x + parentPos.x - 5), (int)(position.y + parentPos.y + r/2 - 4));
        }
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
            separarPelotasEnColision(other);
            return true;
        }
        return false;
    }
    
    public void separarPelotasEnColision(Pelota other){
        //FIXME
        if(Vector2.add(this.velocity, other.velocity).magnitude() <= 0){
            this.position = Vector2.add(this.position, new Vector2(-r * 2, 2*r));
            other.position = Vector2.add(other.position, new Vector2(2*r, -r * 2));
            return;
        }
        double distancia_entre_radios = Vector2.sub(this.position, other.position).magnitude();
        double distancia_que_se_tiene_que_mover = (2*r - distancia_entre_radios) / 2;
        Vector2 direccion_this = velocity.normalized();
        Vector2 direccion_other = other.velocity.normalized();
        
        this.position = Vector2.add(this.position, Vector2.scale(direccion_this, distancia_que_se_tiene_que_mover));
        other.position = Vector2.add(other.position, Vector2.scale(direccion_other, distancia_que_se_tiene_que_mover));
    }
    
    private void resolveCollition(Pelota other){
        Vector2 v1 = this.velocity; 
        Vector2 v2 = other.velocity;
        Vector2 x1 = this.position;
        Vector2 x2 = other.position;
        
        this.velocity = compute_velocity(v1, v2, x1, x2);
        other.velocity = compute_velocity(v2, v1, x2, x1);
        //System.out.println("tv: " + compute_velocity(v1, v2, x1, x2).x + " ov: " + compute_velocity(v2, v1, x2, x1).x);
        
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
