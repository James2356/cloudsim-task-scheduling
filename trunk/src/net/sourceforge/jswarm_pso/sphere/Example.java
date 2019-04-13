package net.sourceforge.jswarm_pso.sphere;

import net.sourceforge.jswarm_pso.Swarm;

/**
 * Minimize  sphere function
 * 
 * 		f( x ) = \sum_{i=1}^{n} { (x_i-1)^2 }
 *
 * @author Alvaro Jaramillo Duque <aduque@inescporto.pt>
 */
public class Example {

	//-------------------------------------------------------------------------
	// Main
	//-------------------------------------------------------------------------

	public static void main(String[] args) {
		System.out.println("Begin: Example sphere\n");

		// Create a swarm (using 'MyParticle' as sample particle and 'MyFitnessFunction' as finess function)
		Swarm swarm = new Swarm(Swarm.DEFAULT_NUMBER_OF_PARTICLES, new MyParticle(), new MyFitnessFunction());

		// Set position (and velocity) constraints. I.e.: where to look for solutions
		swarm.setInertia(0.99);
		swarm.setMaxPosition(100);
		swarm.setMinPosition(0);
		swarm.setMaxMinVelocity(0.1);

		int numberOfIterations = 10000;

		// Optimize (and time it)
		for( int i = 0; i < numberOfIterations; i++ ) {
			swarm.evolve();
			if( i % 1000 == 0 ) System.out.println("Iteration: " + i + "\tParticle: " + swarm.getParticle(swarm.getBestParticleIndex()).toString());
		}

		// Print en results
		System.out.println(swarm.toStringStats());
		System.out.println("End: Example sphere");
	}
}
