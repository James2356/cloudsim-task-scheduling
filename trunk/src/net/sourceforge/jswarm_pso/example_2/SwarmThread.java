package net.sourceforge.jswarm_pso.example_2;

/**
 * A thread that runs in background while calculating kohonen's learning algorithm
 * @author pcingola@users.sourceforge.net
 */
public class SwarmThread extends Thread {

	/** Controller */
	SwarmShow2D controller;

	//-------------------------------------------------------------------------
	// Constructor
	//-------------------------------------------------------------------------

	public SwarmThread(SwarmShow2D controller) {
		super("SwarmThread");
		this.controller = controller;
		start();
	}

	//-------------------------------------------------------------------------
	// Methods
	//-------------------------------------------------------------------------

	/** Run */
	public void run() {
		for( int i = 0; i < controller.getNumberOfIterations(); i++ ) {
			// Show something every displayStep iterations
			if( (i % controller.getDisplayRefresh()) == 0 ) {
				controller.setMessage("Iteration: " + i + "  Best fitness: " + controller.getSwarm().getBestFitness() + "          ");
				controller.clear();
				controller.showSwarm();
			}

			// Evolve swarm
			controller.getSwarm().evolve();
		}
		controller.setMessage("Finished: Best fitness: " + controller.getSwarm().getBestFitness() + "          ");
	}

}
