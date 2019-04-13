package net.sourceforge.jswarm_pso.rosenbrock;

import net.sourceforge.jswarm_pso.FitnessFunction;

/**
 * Minize Rosenbrock function. http://mathworld.wolfram.com/RosenbrockFunction.html
 * 
 * 	General form
 * 
 * 		f( x1 , x2 ) = Sum i=1 to n ; 100*(x(i+1) - xi^2)^2 + (1 - xi)^2
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
		return (100 * Math.pow((x2 - Math.pow(x1, 2)), 2) + Math.pow((1 - x1), 2));
	}

}
