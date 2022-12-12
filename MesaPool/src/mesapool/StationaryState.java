package mesapool;

public class StationaryState extends State{
    
    public StationaryState(RigidBody rb){
        super(rb);
    }
     
    @Override
    public void update(double t){
        rb.setVel(Vector3.zero);
        rb.setAngularVel(Vector3.zero);
        rb.setRVel(Vector3.zero);
        //rb.setPos(rb.getPos());
    }

    @Override
    public double validity() {
        return -1;
    }
}
