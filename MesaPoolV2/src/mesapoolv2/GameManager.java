package mesapoolv2;

import java.awt.Color;
import java.awt.Font;
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

public class GameManager extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener{

    public static boolean start = false;
    
    private static GameManager instance;
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
    private int multiplicador_puntaje = 0;
    private int delta_puntaje = 0;
    
    
   
    
    
    private GameManager(){
        
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
        puntaje = 0;
        delta_puntaje = 0;
        multiplicador_puntaje = 1;
        start = false;
        
        if(type == 1){
         
            for(int i = 0; i < 16; i++){
                Random r = new Random();
                double px = mesa.getBordeWidth() + 3*radio_pelotas + (mesa.getSize().x - 2*mesa.getBordeWidth() - 7*radio_pelotas) * r.nextDouble();
                double py = mesa.getBordeWidth() + 3*radio_pelotas + (mesa.getSize().y - 2*mesa.getBordeWidth() - 7*radio_pelotas) * r.nextDouble();
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
        if(Vector2.dist(mousePosition, player.getGlobalPosition()) < player.getRadius() && player.getVelocity().magnitude() == 0){
            shooting = true;
            System.out.println("clicersk");
        }
    }
    
    private boolean leTocaAlJugador(){
        for(int i = 0; i < pelotas.size(); i++){
            if(!pelotas.get(i).active){
                continue;
            }
            if(pelotas.get(i).getVelocity().magnitude()!= 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        start = true;
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
        player.setVelocity(shoot);
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
        if(leTocaAlJugador() && (delta_puntaje != 0 || multiplicador_puntaje == 0)){
            if(multiplicador_puntaje == 0){
                puntaje += Math.min(delta_puntaje, 0);
            }
            else{
                puntaje += delta_puntaje;
            }
            
            delta_puntaje = 0;
            multiplicador_puntaje = 1;
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
            for(int j = 0; j < mesa.getHoyos().size(); j++){
                if(pelotas.get(i).checkTroneria(mesa.getHoyos().get(j))){
                    if(pelotas.get(i).id == 0){
                        multiplicador_puntaje = 0;
                        delta_puntaje--;
                        Random r = new Random();
                        double px = 2*radio_pelotas + mesa.getBordeWidth() + (mesa.getSize().x - 2*radio_pelotas -  mesa.getBordeWidth()) * r.nextDouble();
                        double py = 2*radio_pelotas +  mesa.getBordeWidth() + (mesa.getSize().y - 2*radio_pelotas -  mesa.getBordeWidth()) * r.nextDouble();
                        pelotas.get(0).setPosition(new Vector2(px, py));
                        pelotas.get(0).setVelocity(new Vector2(0,0));
                        continue;
                    }
                    else{
                        delta_puntaje++;
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
        
        g.setColor(Color.BLUE);
        g.setFont( new Font( "Tahoma", Font.BOLD, 46 ) );
        g.drawString("Puntaje: " + Integer.toString(puntaje), 3*Ventana.w/7, 40);
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
        if(e.getKeyCode() == 82 && canReset){
            setearMesa(pelotasEnJuego, 1);
            canReset = false;
        }
        if(e.getKeyCode() == 84 && canReset){
            setearMesa(pelotasEnJuego, 0);
            canReset = false;
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
    public static GameManager getGameManager(){
        if(instance == null){
            instance = new GameManager(); 
        }
        return instance;
    }
}
