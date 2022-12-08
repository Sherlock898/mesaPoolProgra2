package mesapool;

public abstract class State {
    protected RigidBody rb;
    
    abstract public void update(double t);
}
