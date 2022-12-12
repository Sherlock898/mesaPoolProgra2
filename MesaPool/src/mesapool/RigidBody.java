package mesapool;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

public class RigidBody extends GameObject{
    
    private State state;
    private RollingState rolling;
    private SlidingState sliding;
    private SpinningState spinning;
    private StationaryState stationary;
    
    //Radio y masa
    private double r;
    private float mass;
    
    private Vector3 pos0; //Posicion al cambiar de estado
    
    //Velocidad
    private Vector3 v;    //Velocidad instantanea
    private Vector3 v0;   //Velocidad Inicial (al cambiar de estado(?)
    private Vector3 rv;   //Velocidad Relativa
    private Vector3 rv0;  //Velocidad Relativa inicial al cambiar de estado
    
    //Velocidad angular
    private Vector3 w;   
    private Vector3 w0;
    
    //Constantes de friccion de la superficie en la que está
    protected float usp;
    protected float us;
    protected float ur;
    
    //El tiempo que ha pasado en un estado
    private double tGlobal;
    private double t;
    ArrayList<Double> timeUntilEvent;
    
    Color color;
    
    public RigidBody(double x, double y, double r, float mass, Superficie superficie){
        super(x, y);
        this.r = r;
        this.mass = mass;
        
        //v = Vector3.zero;
        //v0 = Vector3.zero; 
        v = new Vector3(1, 1, 0);
        v0 = v;
        rv = Vector3.add(v, Vector3.cross(Vector3.mult(new Vector3(0, 0, 1), r), Vector3.zero));
        rv0 = Vector3.add(v, Vector3.cross(Vector3.mult(new Vector3(0, 0, 1), r), Vector3.zero));
        w = Vector3.zero;
        w0 = Vector3.zero; 
        pos0 = this.getPos();
        
        usp = superficie.getUsp();
        ur = superficie.getUr();
        us = superficie.getUs();
        
        rolling = new RollingState(this);
        sliding = new SlidingState(this);
        spinning = new SpinningState(this);
        stationary = new StationaryState(this);
        
        this.state = sliding;
        color = Color.RED;
    }
    
    public void update(double dt){
        this.t += dt;
        this.updateState();
        this.state.update(this.t);
      
    }
    
    public void updateState(){
        if(this.state == stationary){
            return;
        }
        
        timeUntilEvent = new ArrayList<Double>();
        
        //Calcular tiempo para transición
        //spining -> stationary
        double tss = 2*r*w0.z/(5*usp*GameObject.g);
        if(state == spinning){
            timeUntilEvent.add(tss);
        }
        
        //rolling -> spining
        double trs = v0.magnitude()/(ur*GameObject.g);
        if(state == rolling){
            timeUntilEvent.add(trs);
        }
        
        //sliding -> rolling
        double tsr = (2/7)*(rv0.magnitude()/(us*GameObject.g));
        if(state == sliding){
            timeUntilEvent.add(tsr);
        }
        
        double minTime = Collections.min(timeUntilEvent);
        if(t >= minTime){
            v0 = v;
            w0 = w;
            rv0 = rv;            
            t = 0;
            
            if(minTime == tss){
               this.state = stationary;
            }
            else if(minTime == trs){
                this.state = spinning;
            }
            else{
                this.state = rolling;
            }
        }
        
        
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
    
    public Vector3 getIrv(){
        return rv0;
    }
    
    public void setState(State state){
        this.state = state;
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
    
    public void setRVel(Vector3 rv){
        this.rv = rv;
    }
    
    public void setIrv(Vector3 rv0){
        this.rv0 = rv0;
    }
    
    public void setIAngularVel(Vector3 w0){
        this.w0 = w0;
    }
    
    public void paint(Graphics g){
        if(this.state == sliding){
           color = Color.BLUE;
        }
        else if(this.state == rolling){
            color = Color.BLACK;
        }
        else if(this.state == spinning){
            color = Color.PINK;
        }
        else if(this.state == stationary){
            color = Color.RED;
        }
        else{
            color = Color.YELLOW;
        }
        g.setColor(color);
        //System.out.println(" " + r);
        g.fillOval((int)((this.getPos().x - (r/2))), (int)(this.getPos().y), (int)(r * 1000), (int)(r * 1000));
    }
   
}
