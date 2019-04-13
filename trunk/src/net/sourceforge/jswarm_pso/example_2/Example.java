package net.sourceforge.jswarm_pso.example_2;

import net.sourceforge.jswarm_pso.Swarm;

/**
 * An extremely simple swarm optimization example
 * Minimize Rastrigin's function
 * 		f( x1 , x2 ) = 20.0 + (x1 * x1) + (x2 * x2) - 10.0 * (Math.cos(2 * Math.PI * x1) + Math.cos(2 * Math.PI * x2));
 * Solution is (obviously): [ 0, 0 ]
 * 
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public class Example {

	public static void main(String[] args) {
		System.out.println("Example of Particle Swarm Optimization: Optimizing Rastrijin's funtion");

		// Create a swarm (using 'MyParticle' as sample particle and 'MyFitnessFunction' as finess function)
		Swarm swarm = new Swarm(Swarm.DEFAULT_NUMBER_OF_PARTICLES, new MyParticle(), new MyFitnessFunction());

		// Tune swarm's update parameters (if needed)
		swarm.setInertia(0.95);
		swarm.setParticleIncrement(0.8);
		swarm.setGlobalIncrement(0.8);

		// Set position (and velocity) constraints. I.e.: where to look for solutions
		swarm.setMaxPosition(100);
		swarm.setMinPosition(-100);

		// Show a 2D graph
		int numberOfIterations = 5000;
		int displayEvery = numberOfIterations / 100 + 1;
		SwarmShow2D ss2d = new SwarmShow2D(swarm, numberOfIterations, displayEvery, true);
		ss2d.run();

		// Show best position
		double bestPosition[] = ss2d.getSwarm().getBestPosition();
		System.out.println("Best position: [" + bestPosition[0] + ", " + bestPosition[1] + " ]\nBest fitness: " + ss2d.getSwarm().getBestFitness() + "\nKnown Solution: [0.0, 0.0]");
	}

}
