package controller;

import misc.PolyFunction;
import finders.FinderInterface;
import finders.NewtonFinder;

public class Application {
	public void run() throws Exception {
		PolyFunction f = new PolyFunction().
			setCoeff(1.0,	3).
			setCoeff(1.0,	2).
			setCoeff(-3.0,	1).
			setCoeff(1.0,	0);
		System.out.println(f);
		
		FinderInterface finder = new NewtonFinder();
		for (double x : finder.find(f)) {
			System.out.println(x);
		}
	}
	
	public static void main(String[] args) {
		Application app = new Application();
		try {
			app.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
