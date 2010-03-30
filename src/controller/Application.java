package controller;

import misc.PolyFunction;
import finders.FinderInterface;
import finders.NewtonFinderGrade3;

public class Application {
	public void run() throws Exception {
		PolyFunction f = new PolyFunction().
			setKoeff(0, 1.0).
			setKoeff(1, -3.0).
			setKoeff(2, 1.0).
			setKoeff(3, 1.0);
		System.out.println(f);
		
		FinderInterface finder = new NewtonFinderGrade3();
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
