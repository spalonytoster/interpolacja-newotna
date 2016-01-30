package com.mposluszny.ug.metodyobliczeniowe.rownaniarozniczkowe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainClass {
	
	static class Euler {
		
		final Double a, b;
		final int n;
		final Double h;
		Double x[], y[];
		
		public Euler(Double a, Double b, int n) {
			this.a = a; this.b = b;
			this.n = n;
			
			this.x = new Double[n+1]; this.y = new Double[n+1];
			
			this.h = (b - a) / n;
		}
		
		private Double calcX(int index) {
			if (index == 0) {
				return (double) 0;
			}
			
			return x[index-1] + h;
		}
		
		private Double calcY(int index) {
			if (index == 0) {
				return (double) 1;
			}
			
			return y[index-1] + (h * getFunctionValue(index));
		}
		
		private Double getFunctionValue(int index) {
			
			return Math.cos(x[index-1]) - (2 * y[index-1]);
		}
		
		private void calculate() {
			
			for (int i = 0; i <= n; i++) {
				x[i] = calcX(i);
				y[i] = calcY(i);
				
				System.out.println(String.format("x[%d] == %f, y[%d] == %f", i, x[i], i, y[i]));
			}
			
		}
		
		private void createRScript() {
			
			StringBuilder sbX = new StringBuilder();
			StringBuilder sbY = new StringBuilder();
			
			sbX.append("x = c(");
			for (int i = 0; i < n; i++) {
				sbX.append(x[i]);
				sbX.append(",");
			}
			sbX.append(x[n]);
			sbX.append(")\n");

			sbY.append("y = c(");
			for (int i = 0; i < n; i++) {
				sbY.append(y[i]);
				sbY.append(",");
			}
			sbY.append(y[n]);
			sbY.append(")\n");
						
			try {
				
				File output = new File("euler-skrypt.r");
				FileWriter fileWriter = new FileWriter(output);
				
				fileWriter.write(sbX.toString());
				fileWriter.write(sbY.toString());
				fileWriter.write("plot(x, y, type=\"b\")");
				
				fileWriter.close();
			
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
		
	}
	
	public class Heunn {
		
	}

	public static void main(String[] args) {
		
		// Dane startowe
		Double a = (double) 0;
		Double b = (double) 30;
		int n = 30;
		
		Euler euler = new Euler(a, b, n);
		euler.calculate();
		euler.createRScript();
		
	}
}
