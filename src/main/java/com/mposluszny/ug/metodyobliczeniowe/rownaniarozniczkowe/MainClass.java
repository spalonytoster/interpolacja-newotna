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
		
		protected Double calcX(int index) {
			if (index == 0) {
				return (double) 0;
			}
			
			return x[index-1] + h;
		}
		
		protected Double calcY(int index) {
			if (index == 0) {
				return (double) 1;
			}
			
			return y[index-1] + getFunctionValue(index);
		}
		
		protected Double getFunctionValue(int index) {
			
			return h * (Math.cos(x[index-1]) - (2 * y[index-1]));
		}
		
		protected void calculate() {
			
			for (int i = 0; i <= n; i++) {
				x[i] = calcX(i);
				y[i] = calcY(i);
				
				System.out.println(String.format("x[%d] == %f, y[%d] == %f", i, x[i], i, y[i]));
			}
			
		}
		
		protected void createRScript() {
			
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
	
	static class Heun extends Euler {
		
		public Heun(Double a, Double b, int n) {
			super(a, b, n);
		}
		
		@Override
		protected Double getFunctionValue(int index) {
			
			return (h/2) * (Math.cos(x[index-1]) - 2*y[index-1] + Math.cos(x[index-1] + h) - 2*(y[index-1] + h*(Math.cos(x[index-1]) - 2*y[index-1])));
		}
		
	}

	public static void main(String[] args) {
		
		// Dane startowe
		Double a = (double) 0;
		Double b = (double) 6;
		int n = 6;
		
		Euler euler = new Euler(a, b, n);
		euler.calculate();
		euler.createRScript();
		
		System.out.println("\n----------------------\n");
		
		Heun heun = new Heun(a, b, n);
		heun.calculate();
		heun.createRScript();
		
	}
}
