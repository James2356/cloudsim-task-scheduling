package net.sourceforge.jswarm_pso.alpine;

import net.sourceforge.jswarm_pso.Swarm;

/**
 * Maximize Alpine function,  http://clerc.maurice.free.fr/pso/Alpine/Alpine_Function.htm
 * 	
 * 		f( x1 , x2 ) = sin( x2 ) * sin( x2 ) * Sqrt( x1 * x2 )
 * 	
 * Solution should be: [x1, x2] = [ 7.917 , 7.917 ] where f() is 7.8856 (NOT 2.88 as the page says)
 * 
 * @author Alvaro Jaramillo Duque <aduque@inescporto.pt>
 */
public class Example {

	//-------------------------------------------------------------------------
	// Main
	//-------------------------------------------------------------------------
	public static void main(String[] args) {
		System.out.println("Begin: Example alpine\n");

		// Create a swarm (using 'MyParticle' as sample particle and 'MyFitnessFunction' as finess function)
		Swarm swarm = new Swarm(Swarm.DEFAULT_NUMBER_OF_PARTICLES, new MyParticle(), new MyFitnessFunction());

		// Set position (and velocity) constraints. I.e.: where to look for solutions
		swarm.setInertia(0.95);
		swarm.setMaxPosition(10); // This should be 10, not 100 as your code said....
		swarm.setMinPosition(0);
		swarm.setMaxMinVelocity(0.1);

		int numberOfIterations = 10000;

		// Optimize (and time it)
		for( int i = 0; i < numberOfIterations; i++ )
			swarm.evolve();

		// Print en results
		System.out.println(swarm.toStringStats());
		System.out.println("Particle "+ swarm.getBestParticleIndex());
		System.out.println("Particle "+ swarm.getParticle(swarm.getBestParticleIndex()).toString());
		System.out.println("End: Example alpine");
	}
}
