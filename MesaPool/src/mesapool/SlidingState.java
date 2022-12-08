package mesapool;

public class SlidingState extends State {

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
        
        v = new Vector3(v.x - rb.us * GameObject.g*t, v.y - rb.us * GameObject.g*t, 0);
        rb.setVel(v);
        
        rb.setAngularVel(new Vector3(0, 0, w0.z - (t * 5 * rb.usp * GameObject.g)/(2*r)));
        
        rb.setPos(new Vector3(x0.x + v0.x*t - (1/2)*rb.us*GameObject.g*t*t, x0.y + v0.y*t - (1/2)*rb.us*GameObject.g*t*t, 0));
    }

}
