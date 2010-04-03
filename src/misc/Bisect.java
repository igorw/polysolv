package misc;

public class Bisect {
	// bisect a range recursively
	// return a more precise range
	// close to y=0
	// f(x1) < 0 < f(x2)
	public Range bisect(Range range, PolyFunction f, Integer bisectDepth) {
		if (bisectDepth == 0) {
			// we have our result
			return range;
		}
		
		Double middle = range.getMiddle();
		
		if (f.calculate(middle) > 0) {
			// middle is larger than 0
			// 0 is left of the middle
			// use right half of the range
			return bisect(new Range(middle, range.getX2()), f, bisectDepth - 1);
		} else {
			// middle is lower than 0
			// 0 is right of the middle
			// use left half of the range
			return bisect(new Range(range.getX1(), middle), f, bisectDepth - 1);
		}
	}
}
