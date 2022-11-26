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
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g3d = (Graphics2D) g;
        Graphics2D g4d = (Graphics2D) g;
        Graphics2D g42d = (Graphics2D) g;
        Graphics2D g43d = (Graphics2D) g;
        Graphics2D g44d = (Graphics2D) g;
        Graphics g1d = (Graphics) g;
        g2d.setColor(Color.GRAY);
        g2d.fillRect(320, 180, 640, 360);
        g3d.setColor(Color.GREEN);
        g3d.fillRect(340, 200, 600, 320);
        g2d.setBackground(getBackground());
        g4d.setColor(Color.BLACK);
        g4d.fillOval(320, 180, 40, 40);
        g42d.setColor(Color.BLACK);
        g42d.fillOval(320, 500, 40, 40);        
        g43d.setColor(Color.BLACK);
        g43d.fillOval(920, 180, 40, 40);
        g44d.setColor(Color.BLACK);
        g44d.fillOval(920, 500, 40, 40);   
        g1d.setColor(Color.WHITE);
        g1d.drawLine(790, 200, 790, 520);
    }
}