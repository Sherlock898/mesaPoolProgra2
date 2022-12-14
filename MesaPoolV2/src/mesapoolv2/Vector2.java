package mesapoolv2;

/**
 * All purpose 2d vector class
 * @author Si2
 */
public class Vector2 {
    public double x;
    public double y;
    
    /**
     * Defines x and y component of the vector
     * @param x x component
     * @param y y component
     */
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Calcules the magnitude of the vector
     * @return magnitude
     */
    public double magnitude(){
        return Math.sqrt(x*x + y*y);
    }
    
    /**
     * Normalizes the vector
     */
    public void normalize(){
        x = x/magnitude();
        y = y/magnitude();
    }
    
    /**
     * Sums two vectors
     * @param v vector 2
     * @param u vector 2
     * @return resultant vector of the sum
     */
    public static Vector2 add(Vector2 v, Vector2 u){
        return new Vector2(v.x + u.x, v.y + u.y);
    }
    
    /**
     * Substracts two vectors
     * @param v vector 1
     * @param u vector 2
     * @return  resultant vector of the substraction
     */
    public static Vector2 sub(Vector2 v, Vector2 u){
        return new Vector2(v.x - u.x, v.y - u.y);
    }
    
    /**
     * Multiply vector by an scalar
     * @param v vector 
     * @param k scalar
     * @return Multiplication of vector by scalar
     */
    public static Vector2 scale(Vector2 v, double k){
        return(new Vector2(v.x * k, v.y * k));
    }
    
    /**
     * Calcule the determinant of two vectors
     * @param v vector 1
     * @param u vector 2
     * @return determinant of v and u
     */
    public static double det(Vector2 v, Vector2 u){
        return (v.x*u.y - u.x*v.y);
    }
    
    /**
     * Dot product of two vectors
     * @param v vector 1
     * @param u vector 2
     * @return dot product v and u
     */
    public static double dot(Vector2 v, Vector2 u){
        return (u.x*v.x + u.y*v.y);
    }
    
    /**
     * Distance between two vector
     * @param v vector 1
     * @param u vector 2
     * @return Distance vector between v and u
     */
    public static double dist(Vector2 v, Vector2 u){
        return Vector2.sub(v, u).magnitude();
    }
    
    /**
     * Calculate the middle point between two vectors
     * @param v vector 1 
     * @param u vector 2
     * @return middle point between v and u
     */
    public static Vector2 puntoMedio(Vector2 v, Vector2 u){
        return new Vector2((v.x + u.x) / 2, (u.x + u.y) / 2);
    }
}
