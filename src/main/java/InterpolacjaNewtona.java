import java.util.ArrayList;
import java.util.Scanner;


public class InterpolacjaNewtona {
	
	Double x[];
	Double y[];
	Double b[];
	int n;
	
	public InterpolacjaNewtona () { 
		
	}
	
	public InterpolacjaNewtona (Double x[], Double y[], Double b[]) {
		
		this.x = x;
		this.y = y;
		
		if (x.length == y.length) {
			this.b = new Double[x.length]; 
		
		} else {
			throw new ArithmeticException("Podano nierówną liczbę argumentów");
		}
	}
	
	public void getUserInput() {
		
		ArrayList<Double> x = new ArrayList<>();
		ArrayList<Double> y = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Podaj wartości x:");
		
		while (scanner.hasNext()) {
			
			String val = scanner.next();
			
			if (val.equals("end")) {
				break;
				
			} else {
				x.add(Double.parseDouble(val));
			}
		}
		
		System.out.println("Podaj wartości y:");
		
		while (scanner.hasNext()) {
			
			String val = scanner.next();
			
			if (val.equals("end")) {
				break;
				
			} else {
				y.add(Double.parseDouble(val));
			}
		}
		
		scanner.close();
		
		this.x = new Double[x.size()];
		this.y = new Double[y.size()];
		
		try {
			this.b = new Double[x.size() == y.size() ? x.size() : null];
			this.n = x.size() == y.size() ? x.size() : null;
			
		} catch (Exception e) {
			System.err.println("Podano nieprawidłową ilość danych wejściowych.");
		}
		
		this.x = x.toArray(this.x);
		this.y = y.toArray(this.y);
	}

	public Double wyliczMnozniki (int base, int iter) {
		
		Double wynik = 1.0;
		
		if (iter >= 1) {
			
			if (iter > base) {
				iter++;
			}
			
			for (int i = 0; i < iter; i++) {
				if (base != i) {
					wynik *= x[base] - x[i];
				}
			}
		}
		
		return wynik;
	}
	
	public Double wyliczB (int i) {
		
		return null;
	}
	
	public static void main(String[] args) {

		//InterpolacjaNewtona interpolacja = new InterpolacjaNewtona();
		//interpolacja.getUserInput();
		
		

	}

}
