package mesapool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
    
@SuppressWarnings("serial")
public class GameManager extends JPanel {
    Superficie s;
    RigidBody rb;
    
    public GameManager(){
        //usp ur us
        s = new Superficie(0, 0, 10, 0.01f, 0.2f);
        rb = new RigidBody(0, 0, 0.05715f, 0.17009713875f, s);
    }
    
    @Override
    public void paint(Graphics g) {
        rb.paint(g);
        repaint();
    }
}