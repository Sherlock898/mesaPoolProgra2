package mesapool;

public class Superficie extends GameObject{
    private float usp;  //Coef. spinning friction
    private float ur;   //Coef. rolling friction
    private float us;   //Coef. sliding friction
    
    public Superficie(double x, double y, float usp, float ur, float us){
        super(x, y);
        this.usp = usp;
        this.ur = ur;
        this.us = us;    
    }
    
    public float getUsp(){
        return usp;
    }
    
    public float getUr(){
        return ur;
    }
    
    public float getUs(){
        return us;
    }
          
}
