package net.sourceforge.jswarm_pso;

/**
 * A swarm of repulsive particles
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public class SwarmRepulsive extends Swarm {

	public static double DEFAULT_OTHER_PARTICLE_INCREMENT = 0.9;
	public static double DEFAULT_RANDOM_INCREMENT = 0.1;

	/** Other particle increment */
	double otherParticleIncrement;
	/** Random increment */
	double randomIncrement;

	/**
	 * Create a Swarm and set default values
	 * @param numberOfParticles : Number of particles in this swarm (should be greater than 0). 
	 * If unsure about this parameter, try Swarm.DEFAULT_NUMBER_OF_PARTICLES or greater
	 * @param sampleParticle : A particle that is a sample to build all other particles
	 * @param fitnessFunction : Fitness function used to evaluate each particle
	 */
	public SwarmRepulsive(int numberOfParticles, Particle sampleParticle, FitnessFunction fitnessFunction) {
		super(numberOfParticles, sampleParticle, fitnessFunction);

		this.otherParticleIncrement = DEFAULT_OTHER_PARTICLE_INCREMENT;
		this.randomIncrement = DEFAULT_RANDOM_INCREMENT;

		// Set up particle update strategy (default: ParticleUpdateRepulsive) 
		this.particleUpdate = new ParticleUpdateRepulsive(sampleParticle);
	}

	public double getOtherParticleIncrement() {
		return otherParticleIncrement;
	}

	public double getRandomIncrement() {
		return randomIncrement;
	}

	public void setOtherParticleIncrement(double otherParticleIncrement) {
		this.otherParticleIncrement = otherParticleIncrement;
	}

	public void setRandomIncrement(double randomIncrement) {
		this.randomIncrement = randomIncrement;
	}
}