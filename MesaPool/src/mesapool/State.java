package mesapool;

public abstract class State {
    protected RigidBody rb;
    protected double vsin;
    protected double vcos;
    
    abstract public void update(double t);
    abstract public double validity();
    
    public State(RigidBody rb){
        this.rb = rb;
    }
}
