package finders;

import java.util.Collections;
import java.util.Vector;

import misc.Differentiator;
import misc.Point;
import misc.PolyFunction;

// y = ax^3 + bx^2 + cx + d
// mšgliche nullstellen 1-3
public class NewtonFinder implements FinderInterface {
	
	// precision/depth of recursive newton algorithm
	private int newtonDepth = 1000;
	
	// offset for searching left/right
	private int newtonOffset = 100;
	
	private double a, b, c, d;
	
	public Vector<Double> find(PolyFunction f) throws InvalidFuncException {
		Vector<Double> results = new Vector<Double>();
		
		a = f.getKoeff(3);
		b = f.getKoeff(2);
		c = f.getKoeff(1);
		d = f.getKoeff(0);
		
		// ableiten damit sie quadratisch ist
		// nullstellen x koordinaten der abgeleiteten funktion
		// entsprechen den extrema von f
		PolyFunction fa = Differentiator.differentiate(f);
		Vector<Double> extrema = new QuadricFinder().find(fa);
		
		Vector<ContextAwarePoint> points = preparePoints(f, extrema);
		
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
		
		return points;
	}
	
	// recursive newton
	// prevValue = startValue
	// Xn+1 = Xn - (f(Xn) / f'(Xn))
	public Double newton(PolyFunction f, double prevValue, int depth) {
		PolyFunction fa = Differentiator.differentiate(f);
		double x = prevValue - (f.calculate(prevValue) / fa.calculate(prevValue));
		if (depth > 0) {
			return newton(f, x, --depth);
		} else {
			return x;
		}
	}
	
	// a point that knows where roots (nullstellen) are
	private class ContextAwarePoint {
		private Point point = new Point();
		private PolyFunction f;
		
		// where are the roots
		private boolean isRoot = false,
						leftRoot = false,
						rightRoot = false;
		
		public ContextAwarePoint(Double x, PolyFunction f) {
			this.f = f;
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
	}
}
