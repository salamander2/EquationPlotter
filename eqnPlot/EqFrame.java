package eqnPlot;

import javax.swing.JFrame;
import javax.swing.JPanel;

/* This is the JFrame/window class. Note that you can resize the window and the equation will still plot correctly.
 * It is called from EqnMain.java 
 */

class EqFrame extends JFrame{
	
	EqFrame() {
		this.setTitle("Equation Plotter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		JPanel grpanel = new GraphicsPanel();
		this.add(grpanel);
		this.setVisible(true);
	}
	
}
