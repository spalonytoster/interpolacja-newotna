import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;


public class InterpolacjaNewtona {
	
	Double x[];
	Double y[];
	Double b[];
	int n;
	
	public InterpolacjaNewtona () { 
		
	}
	
	public InterpolacjaNewtona (Double x[], Double y[]) {
		
		this.x = x;
		this.y = y;
		
		if (x.length == y.length) {
			this.b = new Double[x.length]; 
			this.n = x.length;
		
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

	public void dodajPunktyWezlowe() {
		
		ArrayList<Double> x = new ArrayList<>(1);
		ArrayList<Double> y = new ArrayList<>(1);
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nPodaj wartości x:");
		
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
		
		Double newX[] = new Double[x.size()];
		Double newY[] = new Double[y.size()];
		
		newX = x.toArray(newX);
		newY = y.toArray(newY);
		this.x = ArrayUtils.addAll(this.x, newX);
		this.y = ArrayUtils.addAll(this.y, newY);
		
		try {
			this.b = ArrayUtils.add(this.b, new Double(0.0));
			this.n = this.x.length == this.y.length ? this.x.length : null;
			
		} catch (Exception e) {
			System.err.println("Podano nieprawidłową ilość danych wejściowych.");
		}
		
//		for (int i = 0; i < this.x.length; i++) {
//			System.out.println("x = " + this.x[i] + ", y = " + this.y[i]);
//		}
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
	
	public Double wyliczB (int n, double y) {
		
        double licznik = y;
        
        for (int i = 0; i < n; i++) {
            licznik -= b[i]*wyliczMnozniki(n, i);
        }
        
        double mianownik = wyliczMnozniki(n, n);
        
        return licznik/mianownik;
	}
	

	public double[] budujWielomian() {
		
		 double suma[] = new double [n];
	     double iloczyn[] = new double [n];
	     
	     for(int i=0; i<n; i++) {
	         suma[i]=0;
	         iloczyn[i]=0;
	     }
	     
	     suma[0]=b[0];
	     
	     if (n!=1) {
	        suma = budujWiekszyWielomian(suma, iloczyn);
	     }
	     
		return suma;
	}  
	
	public double[] budujWiekszyWielomian(double suma[], double iloczyn[]) {
		
	     double pom[] = new double[n];
	     for(int i=0; i<n; i++)
	          pom[i]=0;
	     iloczyn[1]++;
	     iloczyn[0]=(-x[0]);
	     suma[1]=b[1];
	     suma[0]+=iloczyn[0]*b[1];
	     for(int i=2; i<n; i++) {  
	    	 
             for(int k=0; k<i; k++) {
                 pom[k+1]+=iloczyn[k];
                 pom[k]+=(iloczyn[k]*(-x[i-1]));
             }
             
             for(int k=0; k<n; k++) {
                 iloczyn[k]=pom[k]; 
             }
             
             for(int k=0;k<n; k++) {
                 suma[k]+=(iloczyn[k]*b[i]);
                 pom[k]=0;
             } 
	     } 
	     
		return suma;
	}
	
	public void dajWielomian() {
		
	    b[0] = y[0];
	    for (int i = 1; i < n; i++)
	         b[i]=wyliczB(i, y[i]);
	    double[] wynik = new double[n];
	    wynik = budujWielomian();
	    String wypisz = "";
	    for (int i = n-1; i >= 0; i--)
	    	if(wynik[i]!=0)
	    	{
	    		if(wynik[i] > 0 && !wypisz.equals(""))
	    			wypisz += "+";
	    		if(i == 0)
	    			wypisz += Double.toString(wynik[i]);
	    		else if(i == 1)
	    			wypisz += wynik[i]+"x";
	    		else
	    			wypisz += wynik[i]+"x^"+i;
	    	}
    	System.out.print(wypisz);    		
	}
	
	public void wypiszDane() {
		System.out.println();
		for (Double x : this.x) {
			System.out.print(x + ", ");
		}
		System.out.println();
		for (Double y : this.y) {
			System.out.print(y + ", ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {

		Double x[] = {1.0, 2.0, 4.0, 5.0};
		Double y[] = {0.0, 2.0, 12.0, 20.0};
		InterpolacjaNewtona interpolacja = new InterpolacjaNewtona(x, y);
		interpolacja.dajWielomian();
		interpolacja.wypiszDane();
		interpolacja.dodajPunktyWezlowe();
		interpolacja.wypiszDane();
		interpolacja.dajWielomian();

	}

}
