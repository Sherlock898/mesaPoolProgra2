package mesapoolv2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel implements ActionListener, MouseListener, MouseMotionListener{

    private ArrayList<Pelota> pelotas;
    Vector2 mousePosition;
    boolean shooting;
    Pelota player;
    Mesa mesa;
    
    
    public PanelPrincipal(){
        shooting = false;
        FPS.calcBeginTime();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        mesa = new Mesa();

        pelotas = new ArrayList<Pelota>();
        player = new Pelota(mesa, new Vector2(100, 100), Color.WHITE);
        Pelota p1 = new Pelota(mesa, new Vector2(1000, 10), Color.RED);
        Pelota p2 = new Pelota(mesa, new Vector2(500, 500), Color.RED);
        Pelota p3 = new Pelota(mesa, new Vector2(50, 500), Color.RED);
        Pelota p4 = new Pelota(mesa, new Vector2(500, 50), Color.RED);
        Pelota p5 = new Pelota(mesa, new Vector2(50, 50), Color.RED);
        
        pelotas.add(player);
        pelotas.add(p1);
        pelotas.add(p2);
        pelotas.add(p3);
        pelotas.add(p4);
        pelotas.add(p5);


    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!leTocaAlJugador()){
            return;
        }
        System.out.println("click");
        mousePosition = new Vector2(e.getX(), e.getY());
        if(Vector2.dist(mousePosition, player.getGlobalPosition()) < player.r && player.velocity.magnitude() == 0){
            shooting = true;
            System.out.println("clicersk");
        }
    }
    
    private boolean leTocaAlJugador(){
        for(int i = 0; i < pelotas.size(); i++){
            if(!pelotas.get(i).active){
                continue;
            }
            if(pelotas.get(i).velocity.magnitude()!= 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!shooting){
            return;
        }
        Vector2 shoot = Vector2.sub(player.getGlobalPosition(), mousePosition);
        double shoot_mag = shoot.magnitude();
        /*
        shoot.normalize();
        Vector2 shoot_dir = shoot;
        if(shoot_mag > 1500){
            shoot_mag = 1500;
        }
        */
        player.velocity = shoot;
        shooting = false;
        
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition = new Vector2(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void paint(Graphics g){
        //TODO refractor this
        super.paint(g);
        mesa.paint(g);
        if(shooting){
            g.drawLine((int)(player.getGlobalPosition().x), (int)(player.getGlobalPosition().y), (int)(mousePosition.x), (int)(mousePosition.y));
        }
        if(leTocaAlJugador() && !shooting){
            player.setColor(Color.WHITE);
        }
        else{
            player.setColor(Color.LIGHT_GRAY);
        }
        for(int i = 0; i < pelotas.size(); i++){
            if(!pelotas.get(i).active){
                continue;
            }
            for(int j = 0; j < pelotas.size(); j++){
                if(j == i || !pelotas.get(j).active){
                    continue;
                }
                pelotas.get(i).checkCollition(pelotas.get(j));
            }
            for(int j = 0; j < mesa.hoyos.size(); j++){
                if(pelotas.get(i).checkTroneria(mesa.hoyos.get(j))){
                    pelotas.get(i).active = false;
                }
            }
            if(pelotas.get(i).active){
                pelotas.get(i).update();
                pelotas.get(i).paint(g);
            }

        }
        
    //super.paint(g);   
        
        
        FPS.calcDeltaTime();
        repaint();
    }
}
