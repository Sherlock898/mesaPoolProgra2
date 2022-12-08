package mesapool;

public class RollingState extends State{

    @Override
    public void update(double t) {
        double vAngle, vcos, vsin;
        double r = rb.getR();
        Vector3 v, w, x;
        Vector3 v0 = rb.getIVel();
        Vector3 w0 = rb.getIAngularVel();
        Vector3 x0 = rb.getIPos();
               
        v = rb.getVel();
        vAngle = v.angle();
        vcos = Math.cos(vAngle);
        vsin = Math.sin(vAngle);
        
        v = new Vector3(v0.x - rb.ur * GameObject.g * vcos, v0.y - rb.ur * GameObject.g * vsin, 0);
        rb.setVel(v);        
        
        vAngle = v.angle();
        vcos = Math.cos(vAngle);
        vsin = Math.sin(vAngle);
        
        w = new Vector3( -(1/r) * v.magnitude() * vsin, -(1/r) * v.magnitude() * vcos, w0.z - (t * 5 * rb.usp * GameObject.g)/(2*r));
        rb.setAngularVel(w);
        
        x = new Vector3(x0.x + v0.x*t - (1/2)*rb.ur*GameObject.g*vcos*t*t, x0.y + v0.y*t - (1/2)*rb.ur*GameObject.g*vsin*t*t, 0);
        rb.setPos(x);
        
    }
    
}
