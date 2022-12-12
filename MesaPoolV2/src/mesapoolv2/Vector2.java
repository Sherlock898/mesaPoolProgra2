package mesapoolv2;

public class Vector2 {
    public double x;
    public double y;
    
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double magnitude(){
        return Math.sqrt(x*x + y*y);
    }
    
    public void normalize(){
        x = x/magnitude();
        y = y/magnitude();
    }
    
    public static Vector2 add(Vector2 v, Vector2 u){
        return new Vector2(v.x + u.x, v.y + u.y);
    }
    
    public static Vector2 scale(Vector2 v, double k){
        return(new Vector2(v.x * k, v.y * k));
    }
    
    public static double det(Vector2 v, Vector2 u){
        return (v.x*u.y - u.x*v.y);
    }
    
    public static Vector2 dot(Vector2 v, Vector2 u){
        return new Vector2(v.x + u.x, v.y + u.y);
    }
}
