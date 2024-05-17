public class LineSeg extends AbstractShape implements CollisionDetector {
    private Point begin;
    private Point end;
    private static int numberOfInstances = 0;
    private Point pointDependency;

    public LineSeg(){
        this(new Point(), new Point()); 
    }
    
    public LineSeg(Point begin, Point end){
        try {
            if (begin.getX() == end.getX() && begin.getY() == end.getY()) {
                throw new ShapeArgumentException("Line segment cannot have coinciding begin and end points");
            }
            this.begin = begin;
            this.end = end;
            numberOfInstances++;
        } catch (ShapeArgumentException e) {
            System.out.println("ShapeArgumentException in constructing LineSeg: " + e.getMessage());
        }
    }

    public Point getBegin(){
        return begin;
    }

    public Point getEnd(){
        return end;
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
        float xB = this.getBegin().getX();
        float yB = this.getBegin().getY();
        float xE = this.getEnd().getX();
        float yE = this.getEnd().getY();

        float px = s.getX();
        float py = s.getY();

        float dx = xE - xB;
        float dy = yE - yB;

        float distance = Math.abs(dx * (py - yB) - dy * (px - xB)) / (float) Math.sqrt(dx * dx + dy * dy);
        if (distance <= 1e-6){
            return true;
        }
        else {
            return false;
        }
    }
    

    public boolean intersect(LineSeg s){
        Point p1 = this.begin;
        Point q1 = this.end;
        Point p2 = s.begin;
        Point q2 = s.end;

        // Calculate the orientations of the line segments
        int orientation1 = orientation(p1, q1, p2);
        int orientation2 = orientation(p1, q1, q2);
        int orientation3 = orientation(p2, q2, p1);
        int orientation4 = orientation(p2, q2, q1);

        // Check if the line segments intersect
        if (orientation1 != orientation2 && orientation3 != orientation4)
            return true;

        // Check for collinearity and overlapping
        if (orientation1 == 0 && onSegment(p1, p2, q1))
            return true;
        if (orientation2 == 0 && onSegment(p1, q2, q1))
            return true;
        if (orientation3 == 0 && onSegment(p2, p1, q2))
            return true;
        if (orientation4 == 0 && onSegment(p2, q1, q2))
            return true;

        return false;
    }

    private int orientation(Point p, Point q, Point r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());
        if (Math.abs(val) < 1e-6)
            return 0; // Collinear
        return (val > 0) ? 1 : 2; // Clockwise or counterclockwise
    }
    
    // Helper function to check if a point lies on a line segment
    private boolean onSegment(Point p, Point q, Point r) {
        return q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
               q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY());
    }

    public boolean intersect(Rectangle s){
        //code
        Point a = new Point(s.getLeft(), s.getBottom());
        Point b = new Point(s.getRight(), s.getBottom());
        Point c = new Point(s.getRight(), s.getTop());
        Point d = new Point(s.getLeft(), s.getTop());

        LineSeg ab = new LineSeg(a, b);
        LineSeg bc = new LineSeg(b, c);
        LineSeg cd = new LineSeg(c, d);
        LineSeg da = new LineSeg(d, a);

        return this.getBegin().intersect(s) || this.end.intersect(s) ||  this.intersect(ab) || this.intersect(bc) || this.intersect(cd) || this.intersect(da);
    }
    
    public boolean intersect(Circle s){
        float x1 = this.getBegin().getX();
        float y1 = this.getBegin().getY();
        float x2 = this.getEnd().getX();
        float y2 = this.getEnd().getY();
    
        float cx = s.getCenter().getX();
        float cy = s.getCenter().getY();
        float radius = s.getRadius();
    
        // Calculate the vector from the line segment's starting point to the circle's center
        float dx1 = cx - x1;
        float dy1 = cy - y1;
    
        // Calculate the vector representing the line segment
        float dx2 = x2 - x1;
        float dy2 = y2 - y1;
    
        // Calculate the dot product of the two vectors
        float dot = dx1 * dx2 + dy1 * dy2;
    
        // Calculate the closest point on the line segment to the circle's center
        float closestX, closestY;
    
        if (dot <= 0) {
            closestX = x1;
            closestY = y1;
        } else if (dot >= (dx2 * dx2 + dy2 * dy2)) {
            closestX = x2;
            closestY = y2;
        } else {
            float t = dot / (dx2 * dx2 + dy2 * dy2);
            closestX = x1 + t * dx2;
            closestY = y1 + t * dy2;
        }
    
        // Calculate the distance between the closest point and the circle's center
        float distance = (float) Math.sqrt((cx - closestX) * (cx - closestX) + (cy - closestY) * (cy - closestY));
    
        // Check if the distance is less than or equal to the circle's radius
        return distance < radius;
    }
}
