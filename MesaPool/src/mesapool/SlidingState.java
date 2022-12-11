package mesapool;

public class SlidingState extends State {

    @Override
    public void update(double t) {
        double vAngle, vcos, vsin;
        double r = rb.getR();
        Vector3 v, w, x;
        Vector3 v0 = rb.getIVel();
        Vector3 rv0 = rb.getIrv();
        Vector3 w0 = rb.getIAngularVel();
        Vector3 x0 = rb.getIPos();
               
        v = rb.getVel();
        vAngle = v.angle();
        vcos = Math.cos(vAngle);
        vsin = Math.sin(vAngle);
        
        v = new Vector3(    
            v0.x - rb.us * GameObject.g * (rv0.x * vcos - rv0.y * vsin)*t,
            v0.y - rb.us * GameObject.g * (rv0.x * vsin - rv0.y * vcos)*t,
            0
        );

        vAngle = v.angle();
        vcos = Math.cos(vAngle);
        vsin = Math.sin(vAngle);
        
        w = new Vector3(
            w0.x * vcos - w0.y * vsin + (5*rb.us*GameObject.g/(2*rb.getR())) * (rv0.y * vcos + rv0.x * vsin) * t,
            w0.x * vsin + w0.y * vcos + (5*rb.us*GameObject.g/(2*rb.getR())) * (rv0.y * vsin - rv0.x * vcos) * t,
            Math.max(0, w0.z - (5*rb.us*GameObject.g/(2*rb.getR())))
        );
    
        x = new Vector3(
            x0.x + v0.x*t - (1/2)*rb.us*GameObject.g * (rv0.x * vcos - rv0.y * vsin)*t*t,
            x0.y + v0.y*t - (1/2)*rb.us*GameObject.g * (rv0.x * vsin + rv0.y * vcos)*t*t,
            0
        );
        
        rb.setVel(v);
        rb.setAngularVel(w);
        rb.setPos(x);
    }   

    @Override
    public double validity() {
        return (2*(rb.getIrv().magnitude()) /(7*rb.us * GameObject.g));
    }

}
