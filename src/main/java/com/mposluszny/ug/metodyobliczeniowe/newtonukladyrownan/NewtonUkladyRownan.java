package com.mposluszny.ug.metodyobliczeniowe.newtonukladyrownan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.DoublePredicate;

public class NewtonUkladyRownan {
	
	private Double p[];  // punkty startowe
	private int n;		// liczba iteracji
	private Double iter[][];// poprzednie iteracje

	public NewtonUkladyRownan () { }

	public void pobierzDane() {
		
		p = new Double[3];
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Podaj punkt startowe:");
			System.out.println("x0: ");
			p[0] = Double.parseDouble(scanner.next());
			System.out.println("y0: ");
			p[1] = Double.parseDouble(scanner.next());
			System.out.println("z0: ");
			p[2] = Double.parseDouble(scanner.next());
			System.out.println("Podaj ilosc iteracji: ");
			n = Integer.parseInt(scanner.next());
			iter = new Double[n][n];
			
		} catch(Exception e) {
			System.out.println("Blad");
		}
		scanner.close();
	}
	
	public Double[] dajMacierz() {
		Double macierz[] = new Double[3];
		macierz[0] = (p[0]*p[0]) + (p[1]*p[1]) - (p[2]*p[2]) - 1;
		macierz[1] = ((p[0]*p[0])/4) + ((p[1]*p[1])/9) + ((p[2]*p[2])/4) - 1;
		macierz[2] = (p[0]*p[0]) + (p[1]*p[1] - 2*p[1] + 1) - 1;
		return macierz;
	}
	
	public Double[][] dajMacierzPochodnych() {
		Double macierz[][] = new Double[3][3];
		macierz[0][0] = 2*p[0];
		macierz[0][1] = 2*p[1];
		macierz[0][2] = -2*p[2];
		macierz[1][0] = p[0]/2;
		macierz[1][1] = 2*p[1]/9;
		macierz[1][2] = p[2]/2;
		macierz[2][0] = 2*p[0];
		macierz[2][1] = 2*p[1] - 2;
		macierz[2][2] = (double)0;
		return macierz;
	}
	
	public Double[][] odwroc(Double[][] macierz) {
		Double[][] odwrot = new Double [3][3];
		double wyznacznik =
				(((macierz[0][0]*macierz[1][1]*macierz[2][2]) +
				(macierz[0][1]*macierz[1][2]*macierz[2][0]) +
				(macierz[0][2]*macierz[1][0]*macierz[2][1])) -
				((macierz[0][2]*macierz[1][1]*macierz[2][0]) +
				(macierz[0][0]*macierz[1][2]*macierz[2][1]) +
				(macierz[0][1]*macierz[1][0]*macierz[2][2])));
		odwrot[0][0] = (macierz[1][1]*macierz[2][2]) - (macierz[1][2]*macierz[2][1]);
		odwrot[0][1] = -((macierz[1][0]*macierz[2][2]) - (macierz[1][2]*macierz[2][0]));
		odwrot[0][2] = (macierz[1][0]*macierz[2][1]) - (macierz[1][1]*macierz[2][0]);
		odwrot[1][0] = -((macierz[0][1]*macierz[2][2]) - (macierz[0][2]*macierz[2][1]));
		odwrot[1][1] = (macierz[0][0]*macierz[2][2]) - (macierz[0][2]*macierz[2][0]);
		odwrot[1][2] = -((macierz[0][0]*macierz[2][1]) - (macierz[0][1]*macierz[2][0]));
		odwrot[2][0] = (macierz[0][1]*macierz[1][2]) - (macierz[0][2]*macierz[1][1]);
		odwrot[2][1] = -((macierz[0][0]*macierz[1][2]) - (macierz[0][2]*macierz[1][0]));
		odwrot[2][2] = (macierz[0][0]*macierz[1][1]) - (macierz[0][1]*macierz[1][0]);
		odwrot = transpozycja(odwrot);
		macierz = odwrot;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				macierz[i][j] *= (1/wyznacznik);
			}
		}
		return macierz;
	}
	
	public Double[][] transpozycja(Double[][] macierz) {
		Double[][] transpozycja = new Double [3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				transpozycja[j][i] = macierz[i][j];
		macierz = transpozycja;
		return macierz;
	}
	
	public Double[] wylicz(Double[] f, Double[][] fp) {
		Double wynik[] = new Double[3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				wynik[i] = (double)0;
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				wynik[i] += (fp[i][j] * f[j]);
		for(int i = 0; i < 3; i++)
			wynik[i] = p[i] - wynik[i];
		return wynik;
	}
	
	public void wykonajIteracje() {
		Double f[];
		Double fp[][];
		for(int i = 0; i < n; i++) {
			f = dajMacierz();
			fp = dajMacierzPochodnych();
			fp = odwroc(fp);
			p = wylicz(f, fp);
			iter[i] = p;
		}
	}
	
	public Double calcE (Double... points) {
		Double underSqrt = 0.0;
		for (Double arg : points) {
			underSqrt += (arg*arg);
		}
		return Math.sqrt(underSqrt);
	}
	
	public void createRScript() {
		
		StringBuilder sbX = new StringBuilder();
		StringBuilder sbY = new StringBuilder();
		
		sbX.append("iteracje = c(");
		for (int i = 0; i < n-1; i++) {
			sbX.append(i+1);
			sbX.append(",");
		}
		sbX.append(n);
		sbX.append(")\n");
		
		Double[] e = new Double[n];
		
		for (int i = 0; i < n; i++) {
			e[i] = calcE(iter[i]);
		}
		
		sbY.append("e = c(");
		for (int i = 0; i < n-1; i++) {
			sbY.append(e[i]);
			sbY.append(",");
		}
		sbY.append(e[n-1]);
		sbY.append(")\n");
					
		try {
			
			File output = new File("newton-skrypt.r");
			FileWriter fileWriter = new FileWriter(output);
			
			fileWriter.write(sbX.toString());
			fileWriter.write(sbY.toString());
			fileWriter.write("plot(iteracje,e,type=\"b\")");
			
			fileWriter.close();
		
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void main(String[] args) {
		NewtonUkladyRownan newton = new NewtonUkladyRownan();
		newton.pobierzDane();
		newton.wykonajIteracje();
		System.out.println("Wynikiem iteracji nr " + newton.n + " jest wektor: [" + newton.p[0]
				+ ", " + newton.p[1] + ", " + newton.p[2] + "]");
		newton.createRScript();
	}
	
}
