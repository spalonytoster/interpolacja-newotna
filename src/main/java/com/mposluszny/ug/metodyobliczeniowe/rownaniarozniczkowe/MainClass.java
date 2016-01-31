package com.mposluszny.ug.metodyobliczeniowe.rownaniarozniczkowe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainClass {
	
	protected final static String FILE_NAME = "r-skrypt.r";
	protected static Double minX = 0.0;
	protected static Double minY = 0.0;
	protected static Double maxX = 0.0;
	protected static Double maxY = 0.0;
	
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
			
			//minX=0.0; minY=6.0; maxX=-1.0; maxY=3.0;
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
		
		protected void checkMinMax (Double x, Double y) {
			
			if (x > maxX) {
				maxX = x;
			}
			
			if (x < minX) {
				minX = x;
			}
			
			if (y > maxY) {
				maxY = y;
			}
			
			if (y < minY) {
				minY = y;
			}
		}
		
		protected void calculate() {
			
			for (int i = 0; i <= n; i++) {
				x[i] = calcX(i);
				y[i] = calcY(i);
				
				checkMinMax(x[i], y[i]);
				
				System.out.println(String.format("x[%d] == %f, y[%d] == %f", i, x[i], i, y[i]));
			}
			
		}
		
		protected void createRScript() {
			
			
			String createPlot = "plot(5, 5,type=\"l\",axes=TRUE,ann=TRUE,xlim=c(" + minX + ", " + maxX + "),ylim = c(" + minY + "," + maxY + "),xlab=\"x\",ylab=\"y\")\n";
			
			String plot = "lines(x, y, type=\"b\", col=\"red\", pch=19)";
			
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
				
				File output = new File(FILE_NAME);
				FileWriter fileWriter = new FileWriter(output);
				
				fileWriter.write(createPlot);
				fileWriter.write(sbX.toString());
				fileWriter.write(sbY.toString());
				fileWriter.write(plot + "\n");
				
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
				
				String plot = "lines(x, y, type=\"b\", col=\"blue\", pch=18, lty=2)";
				
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, true)));
				out.print(sbX.toString());
				out.print(sbY.toString());
				out.println(plot);
				
				out.close();
			
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
	}
	
	static class Rozw extends Euler {
		
		public Rozw(Double a, Double b, int n) {
			super(a, b, n);
		}
		
		@Override
		protected Double getFunctionValue(int index) {
			return (0.2 * Math.pow(Math.E,-2*x[index-1]))*((Math.pow(Math.E,2*x[index-1])*Math.sin(x[index-1]))+2*Math.pow(Math.E,2*x[index-1])*Math.cos(x[index-1])-3);
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
				
				String plot = "lines(x, y, type=\"b\", col=\"brown\", pch=17, lty=3)";
				
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, true)));
				out.print(sbX.toString());
				out.print(sbY.toString());
				out.println(plot);
				
				/// LEGENDA ///
				String legend = "legend('bottomleft', legend=c(\"Euler\", \"Heun\", \"Rozw.\"),"
								+ " col=c(\"red\", \"blue\", \"green\"), lty=1:3, cex=0.8)";
				
				out.print(legend);
				out.close();
			
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		
		// Dane startowe
		Double a = (double) 0;
		Double b = (double) 10;
		int n = 10;
		
		System.out.println("\n----------- EULER -----------\n");
		
		Euler euler = new Euler(a, b, n);
		euler.calculate();
		
		System.out.println("\n----------- HEUN -----------\n");
		
		Heun heun = new Heun(a, b, n);
		heun.calculate();
		
		System.out.println("\n---------- ROZW. ------------\n");
		Rozw r = new Rozw(a, b, n);
		r.calculate();
		
		euler.createRScript();
		heun.createRScript();
		r.createRScript();
	}
}
