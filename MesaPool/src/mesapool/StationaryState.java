package mesapool;

public class StationaryState extends State{
    
    @Override
    public void update(double t){
        rb.setVel(Vector3.zero);
        rb.setAngularVel(Vector3.zero);
        rb.setPos(rb.getPos());
    }

    @Override
    public double validity() {
        return -1;
    }
}
