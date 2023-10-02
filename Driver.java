import java.io.*;
import java.util.Scanner;

public class Driver {
	public static void main(String [] args) throws FileNotFoundException {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double[] c1 = {6, 5};
		int[] e1 = {0, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double[] c2 = {-2, -9};
		int[] e2 = {1, 4};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");

		Polynomial p1_by_p2 = p1.multiply(p2);
		p1_by_p2.saveToFile("C:\\Users\\admin\\OneDrive\\Documents\\202309\\cscb07\\p1_by_p2.txt");

		File file = new File("C:\\Users\\admin\\OneDrive\\Documents\\202309\\cscb07\\polynomial.txt");
		Polynomial p3 = new Polynomial(file);
		p3.saveToFile("C:\\Users\\admin\\OneDrive\\Documents\\202309\\cscb07\\p3.txt");
	}
}