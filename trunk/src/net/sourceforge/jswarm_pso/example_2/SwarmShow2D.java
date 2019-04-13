package net.sourceforge.jswarm_pso.example_2;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import net.sourceforge.jswarm_pso.Gpr;
import net.sourceforge.jswarm_pso.Swarm;

/**
 * An extremely simple swarm optimization example
 * 
 * Maximize function
 * 		f( x1 , x2 ) = 1 - Sqrt( ( x1 - 3/4 )^2 + ( x2 - 1/4 )^2 )
 * Solution is (obviously): [ 3/4 , 1/4 ]
 * 
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public class SwarmShow2D implements ActionListener, Runnable {

	/** Refresh display every 'displayRefresh' number of iterations */
	int displayRefresh;
	/** Drawing area */
	DrawingArea drawingArea;
	/** Frame containing drawing area */
	JFrame frame;
	/** Message text */
	JLabel message;
	/** Number of iterations */
	int numberOfIterations;
	/** Prefered drawing area's dimention */
	Dimension preferredSize;
	/** Dimentions to show in graph */
	int showDimention0, showDimention1;
	/** Show velocities in graph? */
	boolean showVelocity;
	/** Swarm optimizer */
	Swarm swarm;

	//-------------------------------------------------------------------------
	// Methods
	//-------------------------------------------------------------------------

	/**
	 * Create a new SwarmShow2D Object 
	 * @param swarm : Swram to optimize
	 * @param numberOfIterations : Number of iterations
	 * @param displayRefresh : Refresh display every N iterations
	 * @param showVelocity : Show velocity lines
	 */
	public SwarmShow2D(Swarm swarm, int numberOfIterations, int displayRefresh, boolean showVelocity) {
		// Initialize
		this.swarm = swarm;
		this.numberOfIterations = numberOfIterations;
		this.showVelocity = showVelocity;
		this.displayRefresh = displayRefresh;
		showDimention0 = 0; // Default dimntions to show
		showDimention1 = 1;
	}

	//-------------------------------------------------------------------------
	// Methods
	//-------------------------------------------------------------------------

	/**
	 * Action dispatcher
	 */
	public void actionPerformed(ActionEvent e) {
		Gpr.debug("actionPerformed: ActionCommand = " + e.getActionCommand());
	}

	/**
	 * Builds User Interface
	 * @param container
	 */
	private void buildUserInterface(Container container) {
		int rowNum = 0;

		preferredSize = new Dimension(800, 600);

		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		gbc.weightx = 1;
		gbc.weighty = 1;

		// Add an area to draw
		drawingArea = new DrawingArea(this);
		gbc.gridx = 0;
		gbc.gridy = rowNum;
		gbc.gridwidth = 10;
		gbc.gridheight = 4;
		container.add(drawingArea, gbc);
		rowNum += 10;

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0;
		gbc.weighty = 0;

		// Add a 'message' label
		message = new JLabel(); //, drawingArea, JLabel.LEFT );
		message.setText("Ok");
		gbc.gridx = 0;
		gbc.gridy = rowNum++;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		container.add(message, gbc);
	}

	public void clear() {
		drawingArea.clear();
	}

	public int getDisplayRefresh() {
		return displayRefresh;
	}

	public int getNumberOfIterations() {
		return numberOfIterations;
	}

	public Dimension getPreferredSize() {
		return preferredSize;
	}

	public int getShowDimention0() {
		return showDimention0;
	}

	public int getShowDimention1() {
		return showDimention1;
	}

	public Swarm getSwarm() {
		return swarm;
	}

	public boolean isShowVelocity() {
		return showVelocity;
	}

	/** Run swarm */
	public void run() {
		//---
		// Create and show graphics user interface
		//---
		JFrame.setDefaultLookAndFeelDecorated(true);

		// Create and set up the window.
		frame = new JFrame("Swarm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		this.buildUserInterface(frame.getContentPane());

		// Display the window.
		frame.pack();
		frame.setVisible(true);

		this.drawingArea.runSwarm();
	}

	public void setDisplayRefresh(int displayRefresh) {
		this.displayRefresh = displayRefresh;
	}

	public void setMessage(String text) {
		message.setText(text);
	}

	public void setNumberOfIterations(int numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}

	public void setPreferredSize(Dimension preferredSize) {
		this.preferredSize = preferredSize;
	}

	public void setShowDimention0(int showDimention0) {
		this.showDimention0 = showDimention0;
	}

	public void setShowDimention1(int showDimention1) {
		this.showDimention1 = showDimention1;
	}

	public void setShowVelocity(boolean showVelocity) {
		this.showVelocity = showVelocity;
	}

	public void setSwarm(Swarm swarm) {
		this.swarm = swarm;
	}

	public void showSwarm() {
		drawingArea.showSwarm();
	}
}
