package net.sourceforge.jswarm_pso.rosenbrock_30;

import net.sourceforge.jswarm_pso.FitnessFunction;

/**
 * Minize Rosenbrock function. http://mathworld.wolfram.com/RosenbrockFunction.html
 * 
 * 	General form
 * 
 * 		f( x1 , x2 ) = \sum_{i=1}^{n} { 100 (x_{i+1} - x_i^2)^2 + (1 - x_i)^2 }
 * 		
 * @author �lvaro Jaramillo Duque <aduque@inescporto.pt>
 */
public class MyFitnessFunction extends FitnessFunction {

	/** Default constructor */
	public MyFitnessFunction() {
		super(false); // Minimize this function
	}

	//-------------------------------------------------------------------------
	// Methods
	//-------------------------------------------------------------------------

	/**
	 * Evaluates a particles at a given position
	 * @param position : Particle's position
	 * @return Fitness function for a particle
	 */
	public double evaluate(double position[]) {
		double f = 0;
		for( int i = 0; i < (position.length - 1); i++ ) {
			double p1 = position[i] * position[i]; //	x_i^2
			double p2 = (1 - position[i]); // 			( 1 - x_i )
			p2 *= p2; // 								( 1 - x_i )^2
			double p3 = position[i + 1] - p1; // 		( x_{i+1} - x_i^2 )
			p3 *= p3; // 								( x_{i+1} - x_i^2 )^2

			f += 100 * p3 + p2; // 						100 ( x_{i+1} - x_i^2 )^2 + ( 1 - x_i )^2 
		}
		return f;
	}
}
