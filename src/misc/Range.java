package misc;

public class Range {
	private Double x1 = 0.0;
	private Double x2 = 0.0;
	
	public Range(Double x1, Double x2) {
		setX1(x1);
		setX2(x2);
	}
	
	// sort values ascending by y value
	// fluent interface
	public Range sort(PolyFunction f) {
		Double y1 = f.calculate(x1);
		Double y2 = f.calculate(x2);
		
		// swap vars
		if (y1 > y2) {
			Double temp = y1;
			y1 = y2;
			y2 = temp;
		}
		
		return this;
	}
	
	public Double getMiddle() {
		return (x1 + x2) / 2.0;
	}
	
	public Range setX1(Double x1) {
		this.x1 = x1;
		
		return this;
	}
	public Double getX1() {
		return x1;
	}

	public Range setX2(Double x2) {
		this.x2 = x2;
		
		return this;
	}
	public Double getX2() {
		return x2;
	}
	
	@Override
	public String toString() {
		return "range [" + x1 + ":" + x2 + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		return hashCode() == o.hashCode();
	}
	
	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + x1.hashCode();
        result = prime * result + x2.hashCode();
        
        return result;
	}
}
