package misc;

// represents one point on a graph (x/y)
public class Point {
	private Double x = 0.0;
	private Double y = 0.0;
	
	public Point() {
	}
	
	public Point(Double x, Double y) {
		setX(x);
		setY(y);
	}
	
	public void setX(Double x) {
		this.x = x;
	}
	public Double getX() {
		return x;
	}
	
	public void setY(Double y) {
		this.y = y;
	}
	public Double getY() {
		return y;
	}
}
