import java.util.ArrayList;
import java.util.Scanner;


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
	    			wypisz += wynik[i]+"x"+i;
	    	}
    	System.out.print(wypisz);    		
	}
	
	public static void main(String[] args) {

		//InterpolacjaNewtona interpolacja = new InterpolacjaNewtona();
		//interpolacja.getUserInput();
		
		

	}

}
