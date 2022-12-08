package mesapool;

import java.awt.Color;
import java.awt.Graphics;

public class RigidBody extends GameObject{
    //Radio y masa
    private double r;
    private float mass;
    
    private Vector3 pos0; //Posicion al cambiar de estado
    
    //Velocidad
    private Vector3 v;    //Velocidad instantanea
    private Vector3 v0;   //Velocidad Inicial (al cambiar de estado(?)
    
    //Velocidad angular
    private Vector3 w;   
    private Vector3 w0;
    
    //Constantes de friccion de la superficie en la que está
    protected float usp;
    protected float us;
    protected float ur;
    
    //El tiempo que ha pasado en un estado
    private double t;
    
    public RigidBody(double x, double y, double r, float mass, Superficie superficie){
        super(x, y);
        this.r = r;
        this.mass = mass;
        
        v = Vector3.zero;
        v0 = Vector3.zero; 
        w = Vector3.zero;
        w0 = Vector3.zero; 
        
        usp = superficie.getUsp();
        ur = superficie.getUr();
        us = superficie.getUs();
    }
    
    public double getMass(){
        return mass;
    }
    
    public double getR(){
        return r;
    }
    
    @Override
    public Vector3 getPos(){
        return super.getPos();
    }
    
    public Vector3 getIPos(){
        return pos0;
    }
    
    public Vector3 getVel(){
        return v;
    }
    
    public Vector3 getIVel(){
        return v0;
    }
    
    public Vector3 getAngularVel(){
        return w;
    }
    
    public Vector3 getIAngularVel(){
        return w0;
    }
    
    @Override
    public void setPos(Vector3 pos){
        super.setPos(pos);
    }
    
    public void setIPos(Vector3 pos0){
        this.pos0 = pos0;
    }
    
    public void setVel(Vector3 v){
        this.v = v;
    }
    
    public void setAngularVel(Vector3 w){
        this.w = w; 
    }
    
    public void setIVel(Vector3 v0){
        this.v0 = v0;
    }
    
    public void setIAngularVel(Vector3 w0){
        this.w0 = w0;
    }
    
    public void paint(Graphics g){
        g.setColor(Color.blue);
        System.out.println(" " + r);
        g.fillOval((int)((this.getPos().x - (r/2))), (int)(this.getPos().y), (int)(r * 1000), (int)(r * 1000));
    }
}
