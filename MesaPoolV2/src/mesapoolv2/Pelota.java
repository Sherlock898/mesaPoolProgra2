package mesapoolv2;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota {
    protected Vector2 position;
    protected Vector2 velocity;
    protected double friccion = 0.01;
    protected double r = 50;
    
    public Pelota(Vector2 position, Vector2 velocity){
        this.position = position;
        this.velocity = velocity;
    }
    
    public void update(){

        if(position.x > 1280 - (int)(r/2)){
            position.x = 1280 - (int)(r/2);
            velocity.x = - velocity.x;
        }
        if(position.y > 720 - (int)(r/2)){
            position.y = 720 - (int)(r/2);
            velocity.y = - velocity.y;
        }
        if(position.x < 0 + (int)(r/2)){
            position.x = 0 + (int)(r/2);
            velocity.x = - velocity.x;
        }
        if(position.y < 0 + (int)(r/2)){
            position.y = 0 + (int)(r/2);
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
        
        System.out.println("m0: " + m0 + " m1: " + m1 + " vx: " + velocity.x);
        
       
        
      
       
    }
    
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval((int)(position.x - r/2), (int)(position.y - r/2), (int)(r), (int)(r));
        
    }
    
    
}
