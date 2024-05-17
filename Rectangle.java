public class Rectangle extends AbstractShape implements CollisionDetector {
    private float left;
    private float right;
    private float top;
    private float bottom;
    private static int numOfInstances;
    
    public Rectangle(){
        this(0.0f, 0.0f, 0.0f, 0.0f);        
    }

    public Rectangle(float left, float right, float top, float bottom) {
        try {
            if (left >= right || bottom >= top) {
                throw new ShapeArgumentException("Invalid rectangle dimensions: left must be less than right and bottom must be less than top");
            }
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
            numOfInstances++;
        } catch (ShapeArgumentException e) {
            System.out.println("ShapeArgumentException in constructing Rectangle: " + e.getMessage());
        }
    }

    public float getLeft(){
        return left;
    }

    public float getRight(){
        return right;
    }

    public float getTop(){
        return top;
    }

    public float getBottom(){
        return bottom;
    }

    public static int getNumOfInstances(){
        return numOfInstances;
    }

    public boolean intersect(Point s){
        float x = s.getX();
        float y = s.getY();
        float left = this.getLeft();
        float right = this.getRight();
        float top = this.getTop();
        float bottom = this.getBottom();

        // Check if the point is within the rectangle's boundaries
        if (x > left && x < right && y < top && y > bottom){
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
        float left1 = this.getLeft();
        float right1 = this.getRight();
        float top1 = this.getTop();
        float bottom1 = this.getBottom();

        float left2 = s.getLeft();
        float right2 = s.getRight();
        float top2 = s.getTop();
        float bottom2 = s.getBottom();

        if (top1 <= bottom2 || bottom1 >= top2) {
        return false;
        }
        if (right1 <= left2 || left1 >= right2) {
            return false;
        }
        return true;
    }
    
    public boolean intersect(Circle s){
        float circleX = s.getCenter().getX();
        float circleY = s.getCenter().getY();
        float circleRadius = s.getRadius();

        float rectLeft = this.getLeft();
        float rectRight = this.getRight();
        float rectTop = this.getTop();
        float rectBottom = this.getBottom();

        // Case 1: Check if the center of the circle is inside the rectangle
        if (circleX > rectLeft && circleX < rectRight && circleY > rectBottom && circleY < rectTop) {
            return true;
        }

        // Case 2: Check if any of the rectangle's corners are inside the circle
        if (isPointInsideCircle(rectLeft, rectTop, circleX, circleY, circleRadius) ||
            isPointInsideCircle(rectLeft, rectBottom, circleX, circleY, circleRadius) ||
            isPointInsideCircle(rectRight, rectTop, circleX, circleY, circleRadius) ||
            isPointInsideCircle(rectRight, rectBottom, circleX, circleY, circleRadius)) {
            return true;
        }

        // Case 3: Check if any part of the rectangle's boundary is within the circle
        if (circleIntersectsLine(circleX, circleY, circleRadius, rectLeft, rectTop, rectLeft, rectBottom) ||
            circleIntersectsLine(circleX, circleY, circleRadius, rectLeft, rectBottom, rectRight, rectBottom) ||
            circleIntersectsLine(circleX, circleY, circleRadius, rectRight, rectBottom, rectRight, rectTop) ||
            circleIntersectsLine(circleX, circleY, circleRadius, rectRight, rectTop, rectLeft, rectTop)) {
            return true;
        }

        return false;
        }

        // Helper method to check if a point is inside a circle
        private boolean isPointInsideCircle(float pointX, float pointY, float circleX, float circleY, float circleRadius) {
        return Math.pow(pointX - circleX, 2) + Math.pow(pointY - circleY, 2) <= Math.pow(circleRadius, 2);
        }

        // Helper method to check if a circle intersects a line segment
        private boolean circleIntersectsLine(float circleX, float circleY, float circleRadius, float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        float cx = circleX - x1;
        float cy = circleY - y1;

        float dot = cx * dx + cy * dy;
        float lengthSquared = dx * dx + dy * dy;

        if (dot < 0) {
            return Math.pow(cx, 2) + Math.pow(cy, 2) <= Math.pow(circleRadius, 2);
        }

        if (dot > lengthSquared) {
            float cx2 = circleX - x2;
            float cy2 = circleY - y2;
            return Math.pow(cx2, 2) + Math.pow(cy2, 2) <= Math.pow(circleRadius, 2);
        }

        float cross = cx * dy - cy * dx;
        return Math.abs(cross) / Math.sqrt(lengthSquared) <= circleRadius;
        }
}   
