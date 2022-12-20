package mesapoolv2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel implements ActionListener, MouseListener, MouseMotionListener{

    
    private ArrayList<Pelota> pelotas;
    private Vector2 mousePosition;
    private boolean shooting;
    private Pelota player;
    private Mesa mesa;
    private Sfx ballHit_sfx;
    private Sfx ballTroneracion_sfx;
    private final double radio_pelotas = 15;
    
    
    public PanelPrincipal(){
        
        ballHit_sfx = new Sfx("ballballhit1");
        ballTroneracion_sfx = new Sfx("troneriassfx");
        shooting = false;
        FPS.calcBeginTime();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        mesa = new Mesa();

        pelotas = new ArrayList<Pelota>();
        player = new Pelota(mesa, new Vector2(100, 100), Color.WHITE, radio_pelotas);
        
        pelotas.add(player); 
        for(int i = 0; i < 15; i++){
            pelotas.add(new Pelota(mesa, new Vector2(0, 0), Color.RED, radio_pelotas));
        }
        SetearMesa(15);


    }
    
    private void SetearMesa(int nPelotas){
        pelotas.get(1).setPosition(new Vector2(mesa.getSize().x*3/4, mesa.getSize().y/2));
        pelotas.get(2).setPosition(new Vector2(mesa.getSize().x*3/4, mesa.getSize().y/2 - 2*radio_pelotas - 1));
        pelotas.get(3).setPosition(new Vector2(mesa.getSize().x*3/4, mesa.getSize().y/2 + 2*radio_pelotas + 1));
        pelotas.get(4).setPosition(new Vector2(mesa.getSize().x*3/4 + 2*radio_pelotas + 1, mesa.getSize().y/2 + 2*radio_pelotas/2 + 2));
        pelotas.get(5).setPosition(new Vector2(mesa.getSize().x*3/4 + 2*radio_pelotas + 1, mesa.getSize().y/2 - 2*radio_pelotas/2 - 2));
        pelotas.get(6).setPosition(new Vector2(mesa.getSize().x*3/4 - 2*radio_pelotas - 1, mesa.getSize().y/2 + 2*radio_pelotas/2 + 2));
        pelotas.get(7).setPosition(new Vector2(mesa.getSize().x*3/4 - 2*radio_pelotas - 1, mesa.getSize().y/2 - 2*radio_pelotas/2 - 2));
        pelotas.get(8).setPosition(new Vector2(mesa.getSize().x*3/4 + 2*radio_pelotas + 1, mesa.getSize().y/2 + (7/2)*radio_pelotas + 3));
        pelotas.get(9).setPosition(new Vector2(mesa.getSize().x*3/4 + 2*radio_pelotas + 1, mesa.getSize().y/2 - (7/2)*radio_pelotas - 3));
        pelotas.get(10).setPosition(new Vector2(mesa.getSize().x*3/4 + 4*radio_pelotas + 2, mesa.getSize().y/2));
        pelotas.get(11).setPosition(new Vector2(mesa.getSize().x*3/4 + 4*radio_pelotas + 2, mesa.getSize().y/2 - 2*radio_pelotas - 1));
        pelotas.get(12).setPosition(new Vector2(mesa.getSize().x*3/4 + 4*radio_pelotas + 2, mesa.getSize().y/2 + 2*radio_pelotas + 1));
        pelotas.get(13).setPosition(new Vector2(mesa.getSize().x*3/4 + 4*radio_pelotas + 2, mesa.getSize().y/2 - 4*radio_pelotas - 2));
        pelotas.get(14).setPosition(new Vector2(mesa.getSize().x*3/4 + 4*radio_pelotas + 2, mesa.getSize().y/2 + 4*radio_pelotas + 2));
        pelotas.get(15).setPosition(new Vector2(mesa.getSize().x*3/4 - 4*radio_pelotas - 2, mesa.getSize().y/2));
        
        
        for(int i = 0; i < nPelotas + 1; i++){
            pelotas.get(i).active = true;
        }
        
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
                if(pelotas.get(i).checkCollition(pelotas.get(j))){
                    ballHit_sfx.play();
                }
            }
            for(int j = 0; j < mesa.hoyos.size(); j++){
                if(pelotas.get(i).checkTroneria(mesa.hoyos.get(j))){
                    pelotas.get(i).active = false;
                    ballTroneracion_sfx.play();
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
