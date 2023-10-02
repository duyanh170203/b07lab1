import java.io.*;
import java.util.Scanner;

public class Polynomial {
	double[] coeff;
	int[] exp;

	public Polynomial() {
	}

	public Polynomial(double[] coeff, int[] exp) {
		if (coeff != null && exp != null) {
			this.coeff = new double[coeff.length];
			this.exp = new int[exp.length];
			for (int i = 0; i < coeff.length; i++) {
				this.coeff[i] = coeff[i];
				this.exp[i] = exp[i];
			}
		}
	}

	public Polynomial(File file) throws FileNotFoundException {
		String poly;
		String[] terms, temp;
		Scanner scanner;

		if (!file.exists())
			return;

		scanner = new Scanner(file);
		poly = scanner.nextLine();
		terms = poly.split("(?=\\+|\\-)");
		scanner.close();

		if (terms.length == 1 && terms[0].equals('0')) {
			coeff = null;
			exp = null;
		} else {
			coeff = new double[terms.length];
			exp = new int[terms.length];
			for (int i = 0; i < terms.length; i++) {
				temp = terms[i].split("x", 2);
				coeff[i] = Double.parseDouble(temp[0]);
				if (temp.length > 1)
					exp[i] = Integer.parseInt(temp[1]);
			}
		}
	}

	public Polynomial add(Polynomial in_poly) {
		int deg, zeros, i, j;
		double[] temp, coeff;
		int[] exp;

		if (this.coeff == null || this.exp == null)
			return new Polynomial(in_poly.coeff, in_poly.exp);
		else if (in_poly.coeff == null || in_poly.exp == null)
			return new Polynomial(this.coeff, this.exp);

		deg = this.exp[0];
		for (i = 0; i < this.exp.length; i++) {
			if (this.exp[i] > deg)
				deg = this.exp[i];
		}
		for (i = 0; i < in_poly.exp.length; i++) {
			if (in_poly.exp[i] > deg)
				deg = in_poly.exp[i];
		}

		temp = new double[deg + 1];
		zeros = 0;
		for (i = 0; i < temp.length; i++) {
			temp[i] = 0;
			for (j = 0; j < this.exp.length; j++) {
				if (this.exp[j] == i)
					temp[i] = temp[i] + this.coeff[j];
			}
			for (j = 0; j < in_poly.exp.length; j++) {
				if (in_poly.exp[j] == i)
					temp[i] = temp[i] + in_poly.coeff[j];
			}
			if (temp[i] == 0)
				zeros = zeros + 1;
		}

		coeff = new double[temp.length - zeros];
		exp = new int[coeff.length];
		j = 0;
		for (i = 0; i < temp.length; i++) {
			if (temp[i] != 0) {
				coeff[i - j] = temp[i];
				exp[i - j] = i;
			} else
				j = j + 1;
		}

		return new Polynomial(coeff, exp);
	}

	public double evaluate(double x_val) {
		double result = 0.0;
		if (coeff != null) {
			for (int i = 0; i < coeff.length; i++)
				result = result + coeff[i] * Math.pow(x_val, exp[i]);
		}

		return result;
	}

	public boolean hasRoot(double x_val) {
		if (evaluate(x_val) == 0.0)
			return true;
		else
			return false;
	}

	public Polynomial multiply(Polynomial in_poly) {
		Polynomial out_poly = new Polynomial();
		Polynomial temp = new Polynomial(in_poly.coeff, in_poly.exp);

		if (coeff == null || in_poly.coeff == null)
			return out_poly;

		for (int i = 0; i < coeff.length; i++) {
			for (int j = 0; j < in_poly.coeff.length; j++) {
				temp.coeff[j] = coeff[i] * in_poly.coeff[j];
				temp.exp[j] = exp[i] + in_poly.exp[j];
			}
			out_poly = out_poly.add(temp);
		}
		return out_poly;
	}

	public void saveToFile(String filename) throws FileNotFoundException {
		String poly = "";
		PrintStream printer = new PrintStream(filename);

		if (coeff == null || exp == null)
			return;

		for (int i = 0; i < coeff.length; i++) {
			if (coeff[i] >= 0)
				poly = poly + '+' + String.valueOf(coeff[i]);
			else
				poly = poly + String.valueOf(coeff[i]);

			if (exp[i] != 0)
				poly = poly + 'x' + String.valueOf(exp[i]);
		}

		if (poly.charAt(0) == '+')
			printer.println(poly.substring(1));
		else
			printer.println(poly);
		printer.close();
	}
}