package misc;

import finders.InvalidFuncException;

// scheitelpunkt einer quadratischen funktion berechnen
public class Apex {
	static public Point apex(PolyFunction f) throws InvalidFuncException {
		// must be grade 2
		if (f.getMaxGrade() != 2) {
			throw new InvalidFuncException("Apex only supports grade 2");
		}
		
		// scheitelpunktformel
		// -b/2a | -b^2+4ac/4a
		double a = f.getKoeff(2);
		double b = f.getKoeff(1);
		double c = f.getKoeff(0);
		
		return new Point(-b / (2.0*a),
				(-Math.pow(b, 2)+4.0*a*c) / (4.0*a));
	}
}
