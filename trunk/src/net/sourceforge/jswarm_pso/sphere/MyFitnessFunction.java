package net.sourceforge.jswarm_pso.sphere;

import net.sourceforge.jswarm_pso.FitnessFunction;

/**
 * Minimize  sphere function
 * 
 * 		f( x ) = \sum_{i=1}^{n} { (x_i-1)^2 }
 *
 * @author Alvaro Jaramillo Duque <aduque@inescporto.pt>
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
		for( int i = 0; i < (position.length - 1); i++ )
			f += position[i] * position[i];
		return f;
	}

}
