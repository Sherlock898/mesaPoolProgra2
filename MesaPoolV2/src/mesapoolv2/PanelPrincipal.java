package mesapoolv2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener{

    
    private ArrayList<Pelota> pelotas;
    private Vector2 mousePosition;
    private boolean shooting;
    private Pelota player;
    private Mesa mesa;
    private Sfx ballHit_sfx;
    private Sfx ballTroneracion_sfx;
    private final double radio_pelotas = 20;
    private final int nPelotas = 15;
    
    private int pelotasEnJuego;
    private boolean canReset;
    private boolean up;
    private boolean down;
    private int puntaje = 0;
    
    
    public PanelPrincipal(){
        
        ballHit_sfx = new Sfx("ballballhit1");
        ballTroneracion_sfx = new Sfx("troneriassfx");
        shooting = false;
        FPS.calcBeginTime();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        mesa = new Mesa();

        pelotasEnJuego = nPelotas;
        
        pelotas = new ArrayList<Pelota>();
        player = new Pelota(mesa, Color.WHITE, radio_pelotas, 0);
        
        pelotas.add(player); 
        for(int i = 0; i < pelotasEnJuego; i++){
            pelotas.add(new Pelota(mesa, Color.RED, radio_pelotas, i + 1));
        }
        pelotas.get(1).setColor(new Color(254, 217, 0));
        pelotas.get(2).setColor(new Color(1, 47, 52));
        pelotas.get(3).setColor(new Color(252, 2, 4));
        pelotas.get(4).setColor(new Color(61, 1, 121));
        pelotas.get(5).setColor(new Color(255, 109, 2));
        pelotas.get(6).setColor(new Color(0, 76, 32));
        pelotas.get(7).setColor(new Color(136, 2, 31));
        pelotas.get(8).setColor(new Color(0, 0, 0));
        pelotas.get(9).setColor(new Color(254, 217, 0));
        pelotas.get(10).setColor(new Color(1, 47, 52));
        pelotas.get(11).setColor(new Color(252, 2, 4));
        pelotas.get(12).setColor(new Color(61, 1, 121));
        pelotas.get(13).setColor(new Color(255, 109, 2));
        pelotas.get(14).setColor(new Color(0, 76, 32));
        pelotas.get(15).setColor(new Color(136, 2, 31));
        setearMesa(pelotasEnJuego, 0);
        canReset = true;
        up = false;
        down = false;


    }
    
    private void setearMesa(int n, int type){
        if(type == 1){
         
            for(int i = 0; i < 16; i++){
                Random r = new Random();
                double px = 2*radio_pelotas + mesa.getBordeWidth() + (mesa.getSize().x - 2*radio_pelotas -  mesa.getBordeWidth()) * r.nextDouble();
                double py = 2*radio_pelotas +  mesa.getBordeWidth() + (mesa.getSize().y - 2*radio_pelotas -  mesa.getBordeWidth()) * r.nextDouble();
                pelotas.get(i).setPosition(new Vector2(px, py));
            }        
        }
        else{
        
        pelotas.get(0).setPosition(new Vector2(mesa.getSize().x*1/4, mesa.getSize().y/2));
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
        
        }
        for(int i = 0; i < nPelotas + 1; i++){
            pelotas.get(i).setVelocity(new Vector2(0, 0));
        }
        for(int i = 0; i < n + 1; i++){
            pelotas.get(i).active = true;
        }
        for(int i = n + 1; i < nPelotas + 1; i++){
            pelotas.get(i).active = false;
        }
        
        canReset = false;
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
                    if(pelotas.get(i).id == 0){
                        puntaje -= 10;
                        Random r = new Random();
                        double px = 2*radio_pelotas + mesa.getBordeWidth() + (mesa.getSize().x - 2*radio_pelotas -  mesa.getBordeWidth()) * r.nextDouble();
                        double py = 2*radio_pelotas +  mesa.getBordeWidth() + (mesa.getSize().y - 2*radio_pelotas -  mesa.getBordeWidth()) * r.nextDouble();
                        pelotas.get(0).setPosition(new Vector2(px, py));
                        pelotas.get(0).velocity = new Vector2(0,0);
                        continue;
                    }
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

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        //System.out.println("1");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 82){
            setearMesa(pelotasEnJuego, 1);
        }
        if(e.getKeyCode() == 84){
            setearMesa(pelotasEnJuego, 0);
        }
        if(( e.getKeyCode() == 87 || e.getKeyCode() == 38) && up){
            pelotasEnJuego = Math.min(pelotasEnJuego + 1, nPelotas);
            setearMesa(pelotasEnJuego, 0);
            up = false;
        }
        if((e.getKeyCode() == 83 || e.getKeyCode() == 40) && down){
            pelotasEnJuego = Math.max(pelotasEnJuego - 1, 0);
            setearMesa(pelotasEnJuego, 0);
            down = false;
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 82){
            canReset = true;
        }
        if(e.getKeyCode() == 84){
            setearMesa(pelotasEnJuego, 0);
            canReset = true;
        }
        if(e.getKeyCode() == 87 || e.getKeyCode() == 38){
            up = true;
        }
        if(e.getKeyCode() == 83 || e.getKeyCode() == 40){
            down = true;
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
