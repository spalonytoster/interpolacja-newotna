import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;


public class InterpolacjaNewtona {
	
	Double x[];
	Double y[];
	Double b[];
	int n;
	List<String> wielomianyDoNarysowania = new ArrayList<>();
	String[] kolor = {
			
			"green", "red", "blue",
			"black", "brown", "slateblue"
	};
	Scanner scanner = new Scanner(System.in);
	
	int przedzialOd = -5;
	int przedzialDo = 5;

	public InterpolacjaNewtona () { 
		
	}
	
	public InterpolacjaNewtona (Double x[], Double y[]) {
		
		this.x = x;
		this.y = y;
		
		if (x.length == y.length) {
			this.b = new Double[x.length]; 
			this.n = x.length;
		
		} else {
			throw new ArithmeticException("Podano nierÃ³wnÄ… liczbÄ™ argumentÃ³w");
		}
	}
	
	public void setPrzedzialOd(int przedzialOd) {
		this.przedzialOd = przedzialOd;
	}

	public void setPrzedzialDo(int przedzialDo) {
		this.przedzialDo = przedzialDo;
	}
	
	public void getUserInput() {
		
		ArrayList<Double> x = new ArrayList<>();
		ArrayList<Double> y = new ArrayList<>();
		
		
		System.out.println("Podaj wartoœci x:");
		
		while (scanner.hasNext()) {
			
			String val = scanner.next();
			
			if (val.equals("end")) {
				break;
				
			} else {
				x.add(Double.parseDouble(val));
			}
		}
		
		System.out.println("Podaj wartoœci y:");
		
		while (scanner.hasNext()) {
			
			String val = scanner.next();
			
			if (val.equals("end")) {
				break;
				
			} else {
				y.add(Double.parseDouble(val));
			}
		}
		
		//scanner.close();
		
		this.x = new Double[x.size()];
		this.y = new Double[y.size()];
		
		try {
			this.b = new Double[x.size() == y.size() ? x.size() : null];
			this.n = x.size() == y.size() ? x.size() : null;
			
		} catch (Exception e) {
			System.err.println("Podano nieprawid³ow¹ iloœæ danych wejœciowych.");
		}
		
		this.x = x.toArray(this.x);
		this.y = y.toArray(this.y);
	}

	public void dodajPunktyWezlowe() {
		
		ArrayList<Double> x = new ArrayList<>(1);
		ArrayList<Double> y = new ArrayList<>(1);
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nPodaj wartoœci x:");
		
		while (scanner.hasNext()) {
			
			String val = scanner.next();
			
			if (val.equals("end")) {
				break;
				
			} else {
				x.add(Double.parseDouble(val));
			}
		}
		
		System.out.println("Podaj wartoœci y:");
		
		while (scanner.hasNext()) {
			
			String val = scanner.next();
			
			if (val.equals("end")) {
				break;
				
			} else {
				y.add(Double.parseDouble(val));
			}
		}	
		
		//scanner.close();
		
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
			System.err.println("Podano nieprawid³ow¹ iloœæ danych wejœciowych.");
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
	
	public String dajWielomian() {
		
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
	    			wypisz += wynik[i]+"*x";
	    		else
	    			wypisz += wynik[i]+"*x^"+i;
	    	}
    	
	    wielomianyDoNarysowania.add(wypisz);
	    return wypisz;
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
	
	public void createRScript() {
		
		try {
			
			File output = new File("interpolacja-skrypt.r");
			FileWriter fileWriter = new FileWriter(output);
			
			for (int i = 0; i < wielomianyDoNarysowania.size(); i++) {
				
				String line = String.format("%scurve(%s, %d, %d, add=TRUE, col=\"%s\") ; abline(h=0,v=0,lty=3)",
						i == 0 ? "" : "\n", wielomianyDoNarysowania.get(i), przedzialOd, przedzialDo, kolor[i]);
				fileWriter.write(line);
			}
			
			fileWriter.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

//		Double x[] = {1.0, 2.0, 3.0};
//		Double y[] = {5.0, 10.0, 19.0};
//		InterpolacjaNewtona interpolacja = new InterpolacjaNewtona(x, y);
		InterpolacjaNewtona interpolacja = new InterpolacjaNewtona();
		interpolacja.getUserInput();
		System.out.println(interpolacja.dajWielomian());
		interpolacja.dodajPunktyWezlowe();
		System.out.println(interpolacja.dajWielomian());
		
		interpolacja.setPrzedzialOd(-5);
		interpolacja.setPrzedzialDo(5);
		interpolacja.createRScript();

	}

}
