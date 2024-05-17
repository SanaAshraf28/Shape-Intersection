public class Circle extends AbstractShape implements CollisionDetector {
    private Point center;
    private float radius;
    private static int numberOfInstances = 0;
    private Point pointDependency;

    public Circle(){
        this(new Point(), 0.0f);
    }

    public Circle(Point center, float radius){
        try {
            if (radius <= 0) {
                throw new ShapeArgumentException("Circle radius must be greater than zero");
            }
            this.center = center;
            this.radius = radius;
            numberOfInstances++;
        } catch (ShapeArgumentException e) {
            System.out.println("ShapeArgumentException in constructing Circle: " + e.getMessage());
        }
    }

    public Point getCenter(){
        return center;
    }

    public float getRadius(){
        return radius;
    }

    public static int getNumOfInstances(){
        return numberOfInstances;
    }

    public Point getPointDependency() {
        return pointDependency;
    }

    public void setPointDependency(Point pointDependency) {
        this.pointDependency = pointDependency;
    }

    public boolean intersect(Point s){
        float pX = s.getX();
        float pY = s.getY();
        float cX = this.getCenter().getX();
        float cY = this.getCenter().getY();
        float distance = (float) Math.sqrt((pX - cX) * (pX - cX) + (pY - cY) * (pY - cY));
        if (distance < this.getRadius()){
            return true;
        }
        else{
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
       float x1 = this.getCenter().getX();
       float y1 = this.getCenter().getY();
       float x2 = s.getCenter().getX();
       float y2 = s.getCenter().getY();

       float r1 = this.getRadius();
       float r2 = s.getRadius();
       float totalR = r1 + r2;

       float distance = (float) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
       if (distance < totalR){
        return true;
       }
       else{
        return false;
       }
    }  
}
