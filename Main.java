public class Main {
	public static void main(String[] args) {
		AbstractShape p1 = new Point();
		AbstractShape p2 = new Point(23.2f, 111.1f);
		
		CollisionDetector p3 = new Point(7.1f, 0.4f);
		CollisionDetector r1 = new Rectangle();
		CollisionDetector c1 = new Circle((new Point(7.1f, 0.4f)), 3.3f);
		CollisionDetector l1 = new LineSeg((Point)p1, (Point)p2);
		
		System.out.println("r1 intersects with c1 is " + r1.intersect((Circle)c1));
		
		System.out.println("You created " + AbstractShape.getNumOfInstances() + " Shapes:");
		System.out.println("  " + Rectangle.getNumOfInstances() + " Rectangles.");
		System.out.println("  " + LineSeg.getNumOfInstances() + " Line Segments.");
		System.out.println("  " + Point.getNumOfInstances() + " Points.");
		System.out.println("  " + Circle.getNumOfInstances() + " Circles.");
		
		LineSeg l = new LineSeg(new Point(2, 2), new Point(2, 8));
		LineSeg tl4 = new LineSeg(new Point(2, 3), new Point(2, 3));
		System.out.println(l.intersect(tl4));


	}
}
