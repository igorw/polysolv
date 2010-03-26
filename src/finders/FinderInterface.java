package finders;

import java.util.Vector;

import misc.PolyFunction;

public interface FinderInterface {
	// gibt x koordinaten der nullstellen zurück
	public Vector<Double> find(PolyFunction f) throws InvalidFuncException;
}
