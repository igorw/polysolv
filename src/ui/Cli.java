package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

import misc.PolyFunction;
import factory.FinderFactory;
import factory.FinderNotFoundException;
import finders.FinderInterface;

public class Cli {
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			
			int grade = 0;
			System.out.print("Polynomial of what grade: ");
			grade = Integer.valueOf(br.readLine());
			
			PolyFunction f = new PolyFunction();
			for (int i = grade; i >= 0; i--) {
				System.out.print("x^" + i + ": ");
				f.setCoeff(Double.valueOf(br.readLine()), i);
			}
			
			System.out.println(f);
			
			FinderInterface finder = new FinderFactory().getFinder(f);
			
			Vector<Double> results = finder.find(f);
			
			if (results.size() == 0) {
				System.out.println("no result");
			} else {
				int i = 0;
				for (Double x : results) {
					System.out.println("x" + i++ + " = " + x);
				}
			}
			
		} catch (FinderNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		Cli cli = new Cli();
		cli.run();
	}
}
