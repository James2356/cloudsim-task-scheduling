package net.sourceforge.jswarm_pso.schaffer;

import net.sourceforge.jswarm_pso.Swarm;

/**
 * An extremely simple swarm optimization example
 * 
 * Minimizar schaffer function
 * 
 * 		f( x1 , x2 ) = 0.5 + ( sin( Sqrt( x1^2 + x2^2 ))^2 - 0.5 ) / (1 + 0.001 * ( x1^2 * x2^2 ))^2
 * 
 * @author Alvaro Jaramillo Duque <aduque@inescporto.pt>
 */
public class Example {

	//-------------------------------------------------------------------------
	// Main
	//-------------------------------------------------------------------------
	public static void main(String[] args) {
		System.out.println("Begin: Example schaffer\n");

		// Create a swarm (using 'MyParticle' as sample particle and 'MyFitnessFunction' as finess function)
		Swarm swarm = new Swarm(Swarm.DEFAULT_NUMBER_OF_PARTICLES, new MyParticle(), new MyFitnessFunction());

		// Set position (and velocity) constraints. I.e.: where to look for solutions
		swarm.setInertia(0.95);
		swarm.setGlobalIncrement(0.1);
		swarm.setParticleIncrement(0.1);

		swarm.setMaxPosition(50);
		swarm.setMinPosition(-50);
		swarm.setMaxMinVelocity(0.1);

		int numberOfIterations = 10000;

		// Optimize (and time it)
		for( int i = 0; i < numberOfIterations; i++ ) {
			swarm.evolve();
			if( i % 1000 == 0 ) System.out.println("Iteration: " + i + "\tParticle: " + swarm.getParticle(swarm.getBestParticleIndex()).toString());
		}

		// Print en results
		System.out.println(swarm.toStringStats());
		System.out.println("End: Example schaffer");
	}
}
