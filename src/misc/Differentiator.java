package misc;

import java.util.Map.Entry;

public class Differentiator {
	static public PolyFunction differentiate(PolyFunction f) {
		PolyFunction a = new PolyFunction();
		
		for (Entry<Integer, Double> entry : f) {
			if (entry.getKey() == 0) {
				// x^0 => 0
				continue;
			}
			a.setKoeff(entry.getKey()-1, entry.getKey() * entry.getValue());
		}
		
		return a;
	}
}
