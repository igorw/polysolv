package finders;

import java.util.HashSet;

// y = ax + b
// m�gliche nullstellen: genau eine
public class LinearFinder {
	// gibt x koordinaten der nullstellen zur�ck
	public HashSet<Integer> find(int a, int b) {
		HashSet<Integer> nullstellen = new HashSet<Integer>();
		
		// -b / a
		int x = -b / a;
		nullstellen.add(x);
		
		return nullstellen;
	}
}
