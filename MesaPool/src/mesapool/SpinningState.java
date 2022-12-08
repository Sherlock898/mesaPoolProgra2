package mesapool;

public class SpinningState extends State{

    @Override
    public void update(double t) {
        rb.setVel(Vector3.zero);

        rb.setAngularVel(new Vector3(0, 0, rb.getIAngularVel().z + (-5 * rb.usp * GameObject.g * t)/ (2 * rb.getR())));
    }
    
}
