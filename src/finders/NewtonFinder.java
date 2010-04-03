package finders;

import java.util.Collections;
import java.util.Vector;

import misc.Apex;
import misc.Bisect;
import misc.Differentiate;
import misc.Point;
import misc.PolyFunction;
import misc.Range;

// y = ax^3 + bx^2 + cx + d
// mšgliche nullstellen 1-3
public class NewtonFinder implements FinderInterface {
	
	// precision/depth of recursive newton algorithm
	private int newtonDepth = 1000;
	
	// offset for searching left/right
	private int newtonOffset = 100;
	
	// amount of range bisect iterations
	private int bisectDepth = 10;
	
	private double a/*, b, c, d*/;
	
	public Vector<Double> find(PolyFunction f) throws InvalidFuncException {
		Vector<Double> results = new Vector<Double>();
		
		a = f.getKoeff(3);
		/*b = f.getKoeff(2);
		c = f.getKoeff(1);
		d = f.getKoeff(0);*/
		
		// ableiten damit sie quadratisch ist
		// nullstellen x koordinaten der abgeleiteten funktion
		// entsprechen den extrema von f
		PolyFunction fa = Differentiate.differentiate(f);
		Vector<Double> extrema = null;
		
		if (f.getMaxGrade() == 3) {
			// ableiten damit sie quadratisch ist
			// nullstellen x koordinaten der abgeleiteten funktion
			// entsprechen den extrema von f
			extrema = new QuadraticFinder().find(fa);
		} else if (f.getMaxGrade() > 3) {
			// recursion
			extrema = new NewtonFinder().find(fa);
			System.out.println(fa);
			System.out.println(extrema);
		}
		
		Vector<ContextAwarePoint> points = preparePoints(f, extrema);
		
		if (points.size() == 0) {
			// keine nullstelle der ableitung gefunden
			// scheitelpunkt der ableitung entspricht sattelpunkt
			results.add(Apex.apex(fa).getX());
			return results;
		}
		
		if (points.size() == 1) {
			// nur ein extremum
			ContextAwarePoint p = points.firstElement();
			
			if (p.isRoot()) {
				results.add(p.getPoint().getX());
			} else if (p.hasLeftRoot()) {
				results.add(newton(f, p.getPoint().getX() - 1, newtonDepth));
			} else if (p.hasRightRoot()) {
				results.add(newton(f, p.getPoint().getX() + 1, newtonDepth));
			}
		} else {
			// mehr als ein extremum
			ContextAwarePoint firstPoint = points.firstElement();
			if (firstPoint.hasLeftRoot()) {
				try {
					Range range = findRangeLeft(firstPoint.getPoint().getX(), f);
					range = new Bisect().bisect(range, f, bisectDepth);
					System.out.println(range);
				} catch (RangeNotFoundException e) {
					e.printStackTrace();
				}
				results.add(newton(f, firstPoint.getPoint().getX() - newtonOffset, newtonDepth));
			}
			
			ContextAwarePoint prev = null;
			for (ContextAwarePoint current : points) {
				if (current.isRoot()) {
					results.add(current.getPoint().getX());
				}
				
				if (prev == null) {
					prev = current;
					continue;
				}
				
				if (prev.hasRightRoot() && current.hasLeftRoot()) {
					results.add(newton(f, (prev.getPoint().getX()+prev.getPoint().getX()) / 2.0, newtonDepth));
				}
				
				prev = current;
			}
			
			ContextAwarePoint lastPoint = points.lastElement();
			if (lastPoint.hasRightRoot()) {
				results.add(newton(f, firstPoint.getPoint().getX() + newtonOffset, newtonDepth));
			}
		}
		
		return results;
	}

	// make x coordinates aware of roots
	// in certain cases it can be empty
	// in which case there is a saddle point
	// this case must be handled by the caller
	public Vector<ContextAwarePoint> preparePoints(PolyFunction f, Vector<Double> extrema) {
		// extrema aufsteigend sortieren
		Collections.sort(extrema);
		
		// vektor von ContextAwarePoints
		Vector<ContextAwarePoint> points = new Vector<ContextAwarePoint>();
		
		ContextAwarePoint prev = null;
		for (Double x : extrema) {
			// iterate over all extrema
			
			ContextAwarePoint current = new ContextAwarePoint(x, f);
			points.add(current);
			
			if (f.calculate(x) == 0.0) {
				// extremum = nullstelle
				current.setRoot(true);
			}
			
			if (prev == null) {
				prev = current;
				continue;
			}
			
			// calculate coordinates of prev and current
			double y1 = prev.getPoint().getY();
			double y2 = current.getPoint().getY();
			
			// vorzeichen vergleichen
			// falls es Šndert ist nullstelle dazwischen
			// mit mittelwert suchen
			if (y1 < 0 && y2 > 0 || y1 > 0 && y2 < 0) {
				prev.setRightRoot(true);
				current.setLeftRoot(true);
			}
			
			prev = current;
		}
		
		if (points.size() > 0) {
			ContextAwarePoint firstPoint = points.firstElement();
			if (a > 0.0 && firstPoint.getPoint().getY() > 0.0 ||
				a < 0.0 && firstPoint.getPoint().getY() < 0.0) {
				// Ÿber x-achse
				// links suchen
				firstPoint.setLeftRoot(true);
			}

			ContextAwarePoint lastPoint = points.lastElement();
			if (a > 0.0 && lastPoint.getPoint().getY() < 0.0 ||
				a < 0.0 && lastPoint.getPoint().getY() > 0.0) {
				// unter x-achse
				// rechts suchen
				lastPoint.setRightRoot(true);
			}
		}
		
		return points;
	}
	
	// find a range on the left side
	// @todo the same for right and in between
	public Range findRangeLeft(Double x, PolyFunction f) throws RangeNotFoundException {
		Double prev = null, prevResult = null;
		
		// hard-coded depth
		for (int i = 0; i < 10; i++) {
			// current x
			double current = x - Math.pow(10, i);
			
			// current y
			double result = f.calculate(current);
			
			// first iteration
			if (prev == null) {
				prev = current;
				prevResult = result;
				continue;
			}
			
			// arithmetic sign change
			// we have our range
			if (prevResult < 0.0 && result > 0.0 ||
				prevResult > 0.0 && result < 0.0) {
				// return range
				return new Range(prev, current);
			}
			
			// special case
			// we actually found the root
			// return something close
			if (result == 0.0) {
				return new Range(prev, current - 1.0);
			}
			
			prev = current;
			prevResult = result;
		}
		
		// we did not find a good range
		throw new RangeNotFoundException();
	}
	
	// recursive newton
	// prevValue = startValue
	// Xn+1 = Xn - (f(Xn) / f'(Xn))
	public Double newton(PolyFunction f, Double prevValue, Integer depth) {
		PolyFunction fa = Differentiate.differentiate(f);
		double x = prevValue - (f.calculate(prevValue) / fa.calculate(prevValue));
		if (depth > 0) {
			return newton(f, x, depth - 1);
		} else {
			return x;
		}
	}
	
	// helper function to check if sign changed
	static public boolean signChange(Double y1, Double y2) {
		return (y1 < 0 && y2 > 0 ||
				y1 > 0 && y2 < 0);
	}
	
	// a point that knows where roots (nullstellen) are
	private class ContextAwarePoint {
		private Point point = new Point();
		
		// where are the roots
		private boolean isRoot = false,
						leftRoot = false,
						rightRoot = false;
		
		public ContextAwarePoint(Double x, PolyFunction f) {
			point.setX(x);
			point.setY(f.calculate(x));
		}
		public Point getPoint() {
			return point;
		}
		public void setRoot(boolean isRoot) {
			this.isRoot = isRoot;
		}
		public boolean isRoot() {
			return isRoot;
		}
		public void setLeftRoot(boolean leftRoot) {
			this.leftRoot = leftRoot;
		}
		public boolean hasLeftRoot() {
			return leftRoot;
		}
		public void setRightRoot(boolean rightRoot) {
			this.rightRoot = rightRoot;
		}
		public boolean hasRightRoot() {
			return rightRoot;
		}
		
		@Override
		public String toString() {
			return point.toString() + " "
				+ (isRoot() ? "h" : "")
				+ (hasLeftRoot() ? "l" : "")
				+ (hasRightRoot() ? "r" : "");
		}
	}
}
