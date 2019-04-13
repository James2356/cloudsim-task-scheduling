package net.sourceforge.jswarm_pso.example_2;

import net.sourceforge.jswarm_pso.FitnessFunction;

/**
 * Sample Fitness function (Rastrigin's function)
 * 		f( x1 , x2 ) = 20.0 + (x1 * x1) + (x2 * x2) - 10.0 * (Math.cos(2 * Math.PI * x1) + Math.cos(2 * Math.PI * x2));
 * 
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public class MyFitnessFunction extends FitnessFunction {

	//-------------------------------------------------------------------------
	// Constructors
	//-------------------------------------------------------------------------

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
		return 20.0 + (x1 * x1) + (x2 * x2) - 10.0 * (Math.cos(2 * Math.PI * x1) + Math.cos(2 * Math.PI * x2));
	}
}