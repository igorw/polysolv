package misc;

public class Differentiator {
	static public PolyFunction differentiate(PolyFunction f) {
		PolyFunction a = new PolyFunction();
		
		for (int i = 0, size = f.getMaxGrade(); i <= size; i++) {
			if (!f.hasKoeff(i)) {
				continue;
			}
			if (i == 0) {
				// x^0 => 0
				continue;
			}
			a.setKoeff(i-1, i * f.getKoeff(i));
		}
		
		return a;
	}
}
