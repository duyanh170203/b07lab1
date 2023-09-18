public class Polynomial {
	double[] coeff;

	public Polynomial() {
		coeff = new double[1];
		coeff[0] = 0;
	}

	public Polynomial(double[] coeff) {
		this.coeff = new double[coeff.length];
		for (int i = 0; i < coeff.length; i++)
			this.coeff[i] = coeff[i];
	}

	public Polynomial add(Polynomial in_poly) {
		Polynomial out_poly;
		if (this.coeff.length >= in_poly.coeff.length) {
			out_poly = new Polynomial(this.coeff);
			for (int i = 0; i < in_poly.coeff.length; i++)
				out_poly.coeff[i] = out_poly.coeff[i] + in_poly.coeff[i];
		}
		else {
			out_poly = new Polynomial(in_poly.coeff);
			for (int i = 0; i < this.coeff.length; i++)
				out_poly.coeff[i] = out_poly.coeff[i] + this.coeff[i];
		}

		return out_poly;
	}

	public double evaluate(double x_val) {
		double result = 0.0;
		for (int i = 0; i < coeff.length; i++)
			result = result + coeff[i] * Math.pow(x_val, i);

		return result;
	}

	public boolean hasRoot(double x_val) {
		if (evaluate(x_val) == 0.0)
			return true;
		else
			return false;
	}
}

	
