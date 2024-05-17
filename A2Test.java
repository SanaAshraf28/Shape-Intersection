import javax.sound.sampled.Line;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class A2Test {

    //Test methods for Point
    @Test
    public void testPointIntersectPoint() {
        Point p1 = new Point(5, 7);
        Point tp1 = new Point(5, 7); //inside the point
        Point tp2 = new Point(7, 5); //outside the point

        Assert.assertTrue(p1.intersect(tp1));
        Assert.assertFalse(p1.intersect(tp2));
    }

    @Test
    public void testPointIntersectRectangle() {
        Point p1 = new Point(2, 2);
        Rectangle r1 = new Rectangle(0, 5, 7, 0); //Inside the Rectangle
        Rectangle r2 = new Rectangle(5, 10, 12, 5); //Outside
        Rectangle r3 = new Rectangle(2, 5, 3, 0); //On the rectangle

        Assert.assertTrue(p1.intersect(r1));
        Assert.assertFalse(p1.intersect(r2));
        Assert.assertFalse(p1.intersect(r3));
    }

    @Test
    void testPointIntersectCircle() {
        Point p1 = new Point(2, 2);
        Circle c1 = new Circle(new Point(2, 3), 5); //Inside the Circle
        Circle c2 = new Circle(new Point(9,3), 5); //Outside the circle
        Circle c3 = new Circle(new Point(7,2), 5); //On the circle

        Assert.assertTrue(p1.intersect(c1));
        Assert.assertFalse(p1.intersect(c2));
        Assert.assertFalse(p1.intersect(c3));
    }

    @Test
    void testPointIntersectLineSeg() {
        Point p1 = new Point(2, 2);
        LineSeg l1 = new LineSeg(new Point(0, 0), new Point(4, 4)); //On the line
        LineSeg l2 = new LineSeg(new Point(4, 0), new Point(4, 4)); //Outside the line

        Assert.assertTrue(p1.intersect(l1));
        Assert.assertFalse(p1.intersect(l2));
    }

    //Test Methods for LineSeg
    @Test
    void testLineSegIntersectLineSeg() {
        LineSeg l1 = new LineSeg(new Point(2, 2), new Point(2, 8));
        LineSeg tl1 = new LineSeg(new Point(0, 1), new Point(4, 5)); //Intersects at one point
        LineSeg tl2 = new LineSeg(new Point(5, 0), new Point(5, 8)); //Do not intersect
        LineSeg tl3 = new LineSeg(new Point(2, 0), new Point(2, 6)); //Coincides

        Assert.assertTrue(l1.intersect(tl1));
        Assert.assertFalse(l1.intersect(tl2));
        Assert.assertTrue(l1.intersect(tl3));
        
    }

    @Test
    void testLineSegIntersectRectangle() {
        LineSeg l1 = new LineSeg(new Point(2, 2), new Point(2, 8));
        Rectangle r1 = new Rectangle(0, 5, 7, 0); //Intersects
        Rectangle r2 = new Rectangle(5, 10, 12, 5); //Do not intersect
        Rectangle r3 = new Rectangle(2, 10, 4, 0); //Coincides
        
        Assert.assertTrue(l1.intersect(r1));
        Assert.assertFalse(l1.intersect(r2));
        Assert.assertTrue(l1.intersect(r3)); //Failed Test Case
        
    }

    @Test
    void testLineSegIntersectPoint() {
        LineSeg l1 = new LineSeg(new Point(2, 2), new Point(2, 8));
        Point p1 = new Point(2, 4); //on the line
        Point p2 = new Point(7, 5); //outside the line

        Assert.assertTrue(l1.intersect(p1));
        Assert.assertFalse(l1.intersect(p2));
        
    }

    @Test
    void testLineSegIntersectCircle() {
        LineSeg l1 = new LineSeg(new Point(2, 2), new Point(2, 8));
        Circle c1 = new Circle(new Point(2, 3), 5); //Intersects - True   
        Circle c2 = new Circle(new Point(9,3), 5); //Do not intersect - False
        Circle c3 = new Circle(new Point(5,4), 3); //Touches - False

        Assert.assertTrue(l1.intersect(c1));
        Assert.assertFalse(l1.intersect(c2));
        Assert.assertFalse(l1.intersect(c3));


        
    }

    //Test Methods for Rectangle
    @Test
    void testRectangleIntersectPoint() {
        Rectangle r1 = new Rectangle(0, 5, 7, 0);
        Point p1 = new Point(2, 4); //inside
        Point p2 = new Point(7, 5); //outside
        Point p3 = new Point(0, 2); //On the rectangle

        Assert.assertTrue(r1.intersect(p1));
        Assert.assertFalse(r1.intersect(p2));
        Assert.assertFalse(r1.intersect(p3));
        
    }

    @Test
    void testRectangleIntersectLineSeg() {
        Rectangle r1 = new Rectangle(0, 5, 7, 0);
        LineSeg l1 = new LineSeg(new Point(0, 0), new Point(6, 5)); //Intersects 
        LineSeg l2 = new LineSeg(new Point(6, 0), new Point(6, 8)); //Do not intersect
        LineSeg l3 = new LineSeg(new Point(2, 0), new Point(2, 10)); //Coincides

        Assert.assertTrue(r1.intersect(l1));
        Assert.assertFalse(r1.intersect(l2));
        Assert.assertTrue(r1.intersect(l3)); //Failed Test Case
    }

    @Test
    void testRectangleIntersectRectangle() {
        Rectangle r1 = new Rectangle(0, 5, 7, 0);
        Rectangle tr1 = new Rectangle(3, 6, 5, 1); //Intersects
        Rectangle tr2 = new Rectangle(6, 8, 7, 0); //Do not intersect
        Rectangle tr3 = new Rectangle(0, 5, 7, 0); //Coincides

        Assert.assertTrue(r1.intersect(tr1));
        Assert.assertFalse(r1.intersect(tr2));
        Assert.assertTrue(r1.intersect(tr3)); //Failed Test Case
    }

    @Test
    void testRectangleIntersectCircle() {
        Rectangle r1 = new Rectangle(0, 5, 7, 0);
        Circle c1 = new Circle(new Point(9, 3), 5); //Intersects
        Circle c2 = new Circle(new Point(12, 3), 5); //Do not intersect
        Circle c3 = new Circle(new Point(10, 3), 5); //Touches

        Assert.assertTrue(r1.intersect(c1));
        Assert.assertFalse(r1.intersect(c2)); 
        Assert.assertTrue(r1.intersect(c3)); //Failed Test Case       
    }

    //Test Methods for Circle
    @Test
    void testCircleIntersectPoint() {
        Circle c1 = new Circle(new Point(2, 3), 5);
        Point p1 = new Point(2, 3); //inside the circle
        Point p2 = new Point(5, 7); //on the circle
        Point p3 = new Point(7, 5); //outside the circle

        Assert.assertTrue(c1.intersect(p1));
        Assert.assertFalse(c1.intersect(p2));
        Assert.assertFalse(c1.intersect(p3)); 
    }

    @Test
    void testCircleIntersectCircle() {
        Circle c1 = new Circle(new Point(2, 3), 5); 
        Circle ct1 = new Circle(new Point(9,3), 5); //Intersects
        Circle ct2 = new Circle(new Point(9,10.2f), 5); //Touches
        Circle ct3 = new Circle(new Point(9,11), 5); //Do not intersect

        Assert.assertTrue(c1.intersect(ct1))    ;
        Assert.assertFalse(c1.intersect(ct2));
        Assert.assertFalse(c1.intersect(ct3));        
    }

    @Test
    void testCircleIntersectLineSeg() {
        Circle c1 = new Circle(new Point(2, 3), 5);
        LineSeg l1 = new LineSeg(new Point(0, 0), new Point(7, 7)); //Intersects 
        LineSeg l2 = new LineSeg(new Point(10, 0), new Point(10, 5)); //Do not intersect
        LineSeg l3 = new LineSeg(new Point(7, 0), new Point(7, 5)); //Touches
        
        Assert.assertTrue(c1.intersect(l1));
        Assert.assertFalse(c1.intersect(l2));
        Assert.assertFalse(c1.intersect(l3)); 
    }

    @Test
    void testCircleIntersectRectangle() {
        Circle c1 = new Circle(new Point(2, 3), 5);
        Rectangle r1 = new Rectangle(0, 5, 7, 0); //Inside the circle
        Rectangle r2 = new Rectangle(5, 10, 12, 5); //intersects the circle
        Rectangle r3 = new Rectangle(10, 15, 5, 0); //Do not intersect
        Rectangle r4 = new Rectangle(7, 15, 5, 0); //Touches the Circle

        Assert.assertTrue(c1.intersect(r1));
        Assert.assertTrue(c1.intersect(r2));
        Assert.assertFalse(c1.intersect(r3)); 
        Assert.assertTrue(c1.intersect(r4)); //Failed Test Case
    }


    
}
