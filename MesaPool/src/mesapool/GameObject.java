package mesapool;

public class GameObject {
    private Vector3 pos;
    
    public static final double g = 9.807;
    
    public GameObject(double x, double y){
        pos = new Vector3(x, y, 0);
    }
    
    public void setPos(Vector3 pos){
        this.pos = pos;
    }
    
    public Vector3 getPos(){
        return pos;
    }
}
