package net.sourceforge.jswarm_pso.schaffer;

import net.sourceforge.jswarm_pso.FitnessFunction;

/**
 * Minimize schaffer function
 * 
 * 		f( x1 , x2 ) = 0.5 + ( sin( Sqrt( x1^2 + x2^2 ))^2 - 0.5 ) / (1 + 0.001 * ( x1^2 * x2^2 ))^2
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
		double x1 = position[0];
		double x2 = position[1];
		double sum = x1 * x1 + x2 * x2;

		return 0.5 + (Math.pow(Math.sin(Math.sqrt(sum)), 2) - 0.5) / Math.pow(1 + 0.001 * sum, 2);
	}
}
