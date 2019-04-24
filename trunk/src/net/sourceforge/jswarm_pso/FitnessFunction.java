package net.sourceforge.jswarm_pso;


/**
 * Base Fitness Function
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public abstract class FitnessFunction {

	/** Should this funtion be maximized or minimized */
	boolean maximize;

	//-------------------------------------------------------------------------
	// Constructors
	//-------------------------------------------------------------------------

	/** Default constructor */
	public FitnessFunction() {
		this.maximize = true; // Default: Maximize
	}

	/**
	 * Constructor 
	 * @param maximize : Should we try to maximize or minimize this funtion?
	 */
	public FitnessFunction(boolean maximize) {
		this.maximize = maximize;
	}

	//-------------------------------------------------------------------------
	// Methods
	//-------------------------------------------------------------------------
	
	/**
	 * Evaluates a particles at a given position
	 * NOTE: You should write your own method!
	 * 
	 * @param position : Particle's position
	 * @return Fitness function for a particle
	 */
	public abstract double evaluate(double position[]);

	/**
	 * Evaluates a particles fitness
	 * @param particle : Particle to evaluate
	 * @return Fitness function for a particle
	 */
	public double evaluate(Particle particle) {
		double position[] = particle.getPosition();
		double fit = evaluate(position);
		particle.setFitness(fit,maximize);
		return fit;
	}

	/** Are we maximizing this fitness funtion? */
	public boolean isMaximize() {
		return maximize;
	}

	public void setMaximize(boolean maximize) {
		this.maximize = maximize;
	}
}