package net.sourceforge.jswarm_pso;


/**
 * Swarm variables update
 * Every Swarm.evolve() iteration, update() is called  
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public class VariablesUpdate {

	/**
	 * Default constructor 
	 */
	public VariablesUpdate() {
		super();
	}
	
	/**
	 * Update Swarm parameters here
	 * @param swarm : Swarm to update
	 */
	public void update(Swarm swarm) {
////		 Nothing updated in this case (build your own VariablesUpdate class)
////		 e.g. (exponential update):
//		 swarm.setInertia( 0.99 * swarm.getInertia() );
	}
}
