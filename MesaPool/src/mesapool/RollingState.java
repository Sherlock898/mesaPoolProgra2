package mesapool;

public class RollingState extends State{

    public RollingState(RigidBody rb){
        super(rb);
    }
    
    @Override
    public void update(double t) {
        double r = rb.getR();
        Vector3 v, w, x, rv;
        Vector3 v0 = rb.getIVel();
        Vector3 w0 = rb.getIAngularVel();
        Vector3 x0 = rb.getIPos();
               
        vcos = rb.getIVel().angle();
        vsin = rb.getIVel().angle();
        
        v = new Vector3(Math.max(0, v0.x - rb.ur * GameObject.g * vcos * t), Math.max(0, v0.y - rb.ur * GameObject.g * vsin * t), 0);
        rb.setVel(v);        
        
        w = new Vector3( -(1/r) * v.magnitude() * vsin, -(1/r) * v.magnitude() * vcos, Math.max(0, w0.z - (t * 5 * rb.usp * GameObject.g)/(2*r)));
        rb.setAngularVel(w);
        
        x = new Vector3(x0.x + v0.x*t - (1/2)*rb.ur*GameObject.g*vcos*t*t, x0.y + v0.y*t - (1/2)*rb.ur*GameObject.g*vsin*t*t, 0);
        //x = new Vector3(x0.x + v.x * t, x0.y + v.y, 0);
        rb.setPos(x);
        
        rv = Vector3.add(v, Vector3.cross(Vector3.mult(new Vector3(0, 0, 1), r), w));
        rb.setRVel(rv);
        System.out.println(v.x);
    }

    @Override
    public double validity() {
        return (rb.getIVel().magnitude()/(rb.ur*GameObject.g));
    }
    
}
