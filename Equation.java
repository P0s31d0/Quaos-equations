import java.util.ArrayList;
import java.util.Arrays;

public class Equation {

	private int min, max;
	private String[] equations;
	private final String[] CHARS = {"x", "X", "y", "Y", "t", "T"};
	private final String[] OPERATIONS = {"+", "-"};
	
	public Equation(int min, int max) {
		this.min = min;
		this.max = max;
		equations = generate_equations(1);
	}
	
	public String[] generate_equations(int n) {
		String[] e = new String[n];
		
		for (int i = 0; i < n; i++) {
			e[i] = "";
			for (int j = 0; j < min + (int)(Math.random() * max); j++) {
				e[i] +=  rand_operation() + rand_chars();
			}
		}
		
		return e;
	}
	
	private String rand_chars() {
		if (Math.random() > 0.5) {
			return CHARS[(int)(Math.random() * CHARS.length)];
		}
		return CHARS[(int)(Math.random() * CHARS.length)] + CHARS[(int)(Math.random() * CHARS.length)];
	}
	
	private String rand_operation() {
		return OPERATIONS[(int)(Math.random() * (OPERATIONS.length))];
	}
	
	public double interprete_equation(String e, double x, double y, double t) {
		double r = 0;
		
		ArrayList<String> parts = new ArrayList<String>();
		for (int i = 0; i < e.length(); i++) {
			if (Arrays.asList(OPERATIONS).contains(String.valueOf(e.charAt(i)))) {
				if (i + 2 <= e.length()) {
					for (int j = 0; j < 3; j++) {
						parts.add("" + e.charAt(i + j));
					}
					i += 2;
					r += add(parts, x, y, t);
					parts.clear();
				}
			}
		}
		return r;
	}
	
	private double add(ArrayList<String> e, double x, double y, double t) {
		double r = 0;
		
		if (e.get(0).equals("+")) {
			r += value(e.get(1), x, y, t);
		} else {
			r -= value(e.get(1), x, y, t);
		}
		
		for (int i = 2; i < e.size(); i++) {
			r *= value(e.get(i), x, y, t);
		}
		
		return r;
	}
	
	private double value(String s, double x, double y, double t) {
		if (s.equals("x")) return x;
		if (s.equals("X")) return x * x;
		if (s.equals("y")) return y;
		if (s.equals("Y")) return y * y;
		if (s.equals("T")) return t * t;
		return t;
	}
	
	public void print_equations() {
		for (String s: equations) {
			System.out.println(s + " = " + interprete_equation(s, 0.5, 1, 1)); 
		}
	}
}
