package net.sourceforge.jswarm_pso.example_1;

import net.sourceforge.jswarm_pso.Swarm;
import net.sourceforge.jswarm_pso.example_2.SwarmShow2D;

/**
 * An extremely simple swarm optimization example
 * 
 * Maximize function
 * 		f( x1 , x2 ) = 1 - Sqrt( ( x1 - 1/2 )^2 + ( x2 - 1/2 )^2 )
 * Solution is (obviously): [ 1/2 , 1/2 ]
 * 
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public class Example {

	//-------------------------------------------------------------------------
	// Main
	//-------------------------------------------------------------------------
	public static void main(String[] args) {
		System.out.println("Begin: Example 1\n");

		// Create a swarm (using 'MyParticle' as sample particle and 'MyFitnessFunction' as finess function)
		Swarm swarm = new Swarm(Swarm.DEFAULT_NUMBER_OF_PARTICLES, new MyParticle(), new MyFitnessFunction());

		// Set position (and velocity) constraints. I.e.: where to look for solutions
		swarm.setInertia(0.95);
		swarm.setMaxPosition(1);
		swarm.setMinPosition(0);
		swarm.setMaxMinVelocity(0.1);

		int numberOfIterations = 100;
		boolean showGraphics = false;

		if( showGraphics ) {
			int displayEvery = numberOfIterations / 100 + 1;
			SwarmShow2D ss2d = new SwarmShow2D(swarm, numberOfIterations, displayEvery, true);
			ss2d.run();
		} else {
			// Optimize (and time it)
			for( int i = 0; i < numberOfIterations; i++ )
				swarm.evolve();
		}

		// Print en results
		System.out.println(swarm.toStringStats());
		System.out.println("End: Example 1");
	}
}
