package net.sourceforge.jswarm_pso.rosenbrock;

import net.sourceforge.jswarm_pso.Swarm;

/**
 * Minize Rosenbrock function. http://mathworld.wolfram.com/RosenbrockFunction.html
 * 
 * 	General form
 * 
 * 		f( x1 , x2 ) = (1 - x_1)^2 + 100 (x_2 - x_1^2)^2
 * 		
 * @author Alvaro Jaramillo Duque <aduque@inescporto.pt>
 */
public class Example {

	//-------------------------------------------------------------------------
	// Main
	//-------------------------------------------------------------------------
	public static void main(String[] args) {
		System.out.println("Begin: Example Rosenbrock\n");

		// Create a swarm (using 'MyParticle' as sample particle and 'MyFitnessFunction' as finess function)
		Swarm swarm = new Swarm(Swarm.DEFAULT_NUMBER_OF_PARTICLES, new MyParticle(), new MyFitnessFunction());

		// Set position (and velocity) constraints. I.e.: where to look for solutions
		swarm.setInertia(0.99);			// Tuned up intertia (just a little)
		swarm.setMaxPosition(30);
		swarm.setMinPosition(0);
		swarm.setMaxMinVelocity(0.1);

		int numberOfIterations = 10000; // Added some more iterations

		// Optimize (and time it)
		for( int i = 0; i < numberOfIterations; i++ )
			swarm.evolve();

		// Print en results
		System.out.println(swarm.toStringStats());
		System.out.println("End: Example Rosenbrock");
	}
}
