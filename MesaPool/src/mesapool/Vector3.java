package mesapool;

public class Vector3 {
    public double x;
    public double y;
    public double z;
    
    public static final Vector3 zero = new Vector3(0, 0, 0);
    public static final Vector3 right = new Vector3(1, 0, 0);
    public static final Vector3 left = new Vector3(-1, 0, 0);
    public static final Vector3 up = new Vector3(0, -1, 0);
    public static final Vector3 down = new Vector3(0, 1, 0);
    
    public static Vector3 add(Vector3 a, Vector3 b){
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }
    
    public static Vector3 mult(Vector3 a, double c){
        return new Vector3(a.x * c, a.y * c, a.z * c);
    }
    
    public static Vector3 cross(Vector3 u, Vector3 v){
        return new Vector3(u.y*v.z - u.z*v.y, u.z*v.x - u.x*v.z, u.x*v.y - u.y*v.x);
    }
    
    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public double magnitude(){
        return Math.sqrt(x*x+y*y+z*z);
    }
    
    public Vector3 normalized(){
        double m = this.magnitude();
        return new Vector3(x/m, y/m, z/m);
    }
    
    public double angle(){
        if(x == 0){
            return Math.PI/2;
        }
        return Math.atan(y/x) * Math.signum(y);
    }
       
}
