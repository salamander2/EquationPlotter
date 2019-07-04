package eqnPlot;

/* Write your equation here.
 * Also specify the domain and range below 
 */

class EQ {
	
	static double xmin = -6.0;
	static double xmax = 6.0;
	static double ymin = -6.0;
	static double ymax = 6.0;
	
	//this must be a function (in the math sense). 
	static double equation(double x) {
		double y;
		
		//This equation is y = x^3 - 2x
		y = x*x*x - 2*x;
		
		return y;
	}
}
