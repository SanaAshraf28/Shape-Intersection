public class Point extends AbstractShape implements CollisionDetector {
    private float x = 0;
    private float y = 0;
    private static int numberOfInstances = 0;

    public Point(){
        this(0f, 0f); 
    }

    public Point(float x, float y){
        this.x = x;
        this.y = y;
        numberOfInstances++;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public static int getNumOfInstances(){
        return numberOfInstances;
    }

    public boolean intersect(Point s){
        if (this.x == s.x && this.y == s.y){
            return true;
        }
        else {
            return false;
            }
    }

    public boolean intersect(LineSeg s){
        return ((LineSeg) s).intersect(this);
    }

    public boolean intersect(Rectangle s){
        return ((Rectangle) s).intersect(this);
    }
    
    public boolean intersect(Circle s){
        return ((Circle) s).intersect(this);
    }
    
}
