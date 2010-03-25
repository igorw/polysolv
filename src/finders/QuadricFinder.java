package finders;

import java.util.HashSet;

// y = ax^2 + bx + c
// mögliche nullstellen: 0-2
public class QuadricFinder {
	// gibt x koordinaten der nullstellen zurück
	public HashSet<Integer> find(int a, int b, int c) {
		HashSet<Integer> nullstellen = new HashSet<Integer>();
		
		// (-b +- sqrt(b^2-4ac)) / 2a
		int x1 = (int) ((-b + Math.sqrt(b^2-4*a*c)) / 2*a);
		nullstellen.add(x1);
		int x2 = (int) ((-b - Math.sqrt(b^2-4*a*c)) / 2*a);
		nullstellen.add(x2);
		
		return nullstellen;
	}
}
