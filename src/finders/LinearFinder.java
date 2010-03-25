package finders;

import java.util.HashSet;

// y = ax + b
// mögliche nullstellen: genau eine
public class LinearFinder {
	// gibt x koordinaten der nullstellen zurück
	public HashSet<Integer> find(int a, int b) {
		HashSet<Integer> nullstellen = new HashSet<Integer>();
		
		// -b / a
		int x = -b / a;
		nullstellen.add(x);
		
		return nullstellen;
	}
}
