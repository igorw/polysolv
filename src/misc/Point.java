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
		this.x = (x == -0.0) ? 0.0 : x;
	}
	public Double getX() {
		return x;
	}
	
	public void setY(Double y) {
		this.y = (y == -0.0) ? 0.0 : y;
	}
	public Double getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "point (" + x + "|" + y + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		return hashCode() == o.hashCode();
	}
	
	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + x.hashCode();
        result = prime * result + y.hashCode();
        
        return result;
	}
}
