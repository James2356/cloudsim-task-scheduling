package net.sourceforge.jswarm_pso;

/**
 * Particle update: Each particle selects an rlocal and rother 
 * independently from other particles' values
 * 
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public class ParticleUpdateRandomByParticle extends ParticleUpdate {

	/**
	 * Constructor 
	 * @param particle : Sample of particles that will be updated later
	 */
	public ParticleUpdateRandomByParticle(Particle particle) {
		super(particle);
	}

	/** Update particle's velocity and position */
	public void update(Swarm swarm, Particle particle) {
		double position[] = particle.getPosition();
		double velocity[] = particle.getVelocity();
		double globalBestPosition[] = swarm.getBestPosition();
		double particleBestPosition[] = particle.getBestPosition();

		double rlocal = Math.random();
		double rglobal = Math.random();

		// Update velocity and position
		for( int i = 0; i < position.length; i++ ) {
			// Update position
			position[i] = position[i] + velocity[i];

			// Update velocity
			velocity[i] = swarm.getInertia() * velocity[i] // Inertia
					+ rlocal * swarm.getParticleIncrement() * (particleBestPosition[i] - position[i]) // Local best
					+ rglobal * swarm.getGlobalIncrement() * (globalBestPosition[i] - position[i]); // Global best
		}
	}
}
