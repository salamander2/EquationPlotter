package eqnPlot;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/* This is the program that creates the JPanel (subclass) that does all of the graphics.
 * It is called from EqFrame.java
 */

class GraphicsPanel extends JPanel{

	GraphicsPanel(){
		this.setBackground(Color.BLACK);		
	}
	
	int scrW, scrH;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		scrW = this.getSize().width;
		scrH = this.getSize().height;
		double xstep = (EQ.xmax - EQ.xmin) / scrW;		
		
		//draw Axes
		plotLine(EQ.xmin, 0, EQ.xmax, 0, Color.ORANGE, g);
		plotLine(0, EQ.ymin, 0, EQ.ymax, Color.ORANGE, g);
			
		//draw the graph as points, rather than as a solid line (which causes problems)
		g.setColor(Color.WHITE);
		for (double x = EQ.xmin; x <= EQ.xmax; x += xstep) {
			double y = EQ.equation(x);
			plotPt(x,y,g);
		}
	}
		
	/*********************************************************************************
	Name:		plotPt()
	Purpose:	This function draws a single pixel on the screen based on the screen size
				and the scale of the graph.	
	Parameters: x and y are the point coordinates in scale space, not screen pixel locations.
	Called from: paintComponent()
	Calls: --
	**********************************************************************************/
	private void plotPt(double x, double y, Graphics g)  {
		int px = (int)(scrW * (x - EQ.xmin) / (EQ.xmax - EQ.xmin)) ;
		int py = scrH - (int)(scrH * (y - EQ.ymin) / (EQ.ymax - EQ.ymin)) ;
		g.drawLine(px, py, px, py);		
	}
	
	/*********************************************************************************
	Name:		plotLine()
	Purpose:	This function draws lines on the screen based on the screen size
				and the scale of the graph.	This method screws up if the points are are too large or too small:
				strange lines appear on the screen. I haven't bothered to try and duplicate this problem
				in Java 1.6. It was a problem in Java 1.2.
	Parameters: x1, y1, x2, y2 are the point coordinates in scale space, not screen pixel locations.
	Called from: paintComponent()
	Calls: --
	**********************************************************************************/
	public void plotLine (double x1, double y1, double x2, double y2, Color col, Graphics g) {
		/* This method screws up when a number results in an int that it too big or two small so that it wraps around.
		e.g. plotLine(-9.46875,-1295.2,-9.5,-1306.625,Color.red);
			c.print("\n\tpx1=" + px1 + "\tpy1=" + py1 + "\tpx2=" + px2 + "\tpy2=" + py2);
		This results in py1 = 32630  py2 = 32915, px1 = 17, px2 = 16. A vertical line is drawn down the screen!!!
		It looks like the real drawLine converts everything to a short, but I can't find any reference to this in the documentation.
		*/

		int px1 = (int) (scrW * (x1 - EQ.xmin) / (EQ.xmax - EQ.xmin));
		int px2 = (int) (scrW * (x2 - EQ.xmin) / (EQ.xmax - EQ.xmin));
		int py1 = scrH - (int) (scrH * (y1 - EQ.ymin) / (EQ.ymax - EQ.ymin));
		int py2 = scrH - (int) (scrH * (y2 - EQ.ymin) / (EQ.ymax - EQ.ymin));

		g.setColor (col);
		//Test for integer wrapping.
		/*if (px1 > Short.MAX_VALUE)
			return;
			if (px2 > Short.MAX_VALUE)
			return;
			if (py1 > Short.MAX_VALUE)
			return;
			if (py2 > Short.MAX_VALUE)
			return; */
		g.drawLine (px1, py1, px2, py2);
		return;
	} //end of PlotLine
}
