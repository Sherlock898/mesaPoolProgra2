package mesapoolv2;

import java.awt.Graphics;

public class Hoyo {
    private Vector2 position;
    private double r;
    private Mesa parent;

    public Hoyo(Mesa parent, Vector2 position, double r){
        this.parent = parent;
        this.r = r;
        this.position = position;
    }

    public Vector2 getPosition(){
        return Vector2.copy(position);
    }

    public void paint(Graphics g){
        g.fillOval((int)(position.x + parent.getPosition().x - r), (int)(position.y + parent.getPosition().y - r), (int)(2*r), (int)(2*r));
    }
}
