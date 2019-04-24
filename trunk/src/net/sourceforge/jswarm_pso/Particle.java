package net.sourceforge.jswarm_pso;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Basic (abstract) particle
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public abstract class Particle {

	/** Best fitness funtion so far */
	double bestFitness;
	/** Best particles's position so far */
	double bestPosition[];
	/** current fitness */
	double fitness;
	/** Position */
	double position[];
	/** Velocity */
	double velocity[];

	//-------------------------------------------------------------------------
	// Constructors
	//-------------------------------------------------------------------------

	/**
	 * Constructor 
	 */
	public Particle() {
		throw new RuntimeException("You probably need to implement your own 'Particle' class");
	}

	/**
	 * Constructor 
	 * @param dimention : Particle's dimention
	 */
	public Particle(int dimention) {
		allocate(dimention);
	}

	/**
	 * Constructor 
	 * @param sampleParticle : A sample particles to copy
	 */
	public Particle(Particle sampleParticle) {
		int dimention = sampleParticle.getDimention();
		allocate(dimention);
	}

	//-------------------------------------------------------------------------
	// Methods
	//-------------------------------------------------------------------------

	/** Allocate memory */
	public void allocate(int dimention) {
		this.position = new double[dimention];
		this.bestPosition = new double[dimention];
		this.velocity = new double[dimention];
		bestFitness = Double.NaN;
		fitness = Double.NaN;
		for( int i = 0; i < position.length; i++ )
			bestPosition[i] = Double.NaN;
	}

	/**
	 * Apply position and velocity constraints (clamp)
	 * @param minPosition : Minimum position
	 * @param maxPosition : Maximum position
	 * @param minVelocity : Minimum velocity
	 * @param maxVelocity : Maximum velocity
	 */
	public void applyConstraints(double[] minPosition, double[] maxPosition, double[] minVelocity, double[] maxVelocity) {
		//---
		// Every constraint is setted? (do all of them it one loop)
		//---
		if( (minPosition != null) && (maxPosition != null) && (minVelocity != null) && (maxVelocity != null) ) {
			for( int i = 0; i < position.length; i++ ) {
				if( !Double.isNaN(minPosition[i]) ) position[i] = (minPosition[i] > position[i] ? minPosition[i] : position[i]);
				if( !Double.isNaN(maxPosition[i]) ) position[i] = (maxPosition[i] < position[i] ? maxPosition[i] : position[i]);
				if( !Double.isNaN(minVelocity[i]) ) velocity[i] = (minVelocity[i] > velocity[i] ? minVelocity[i] : velocity[i]);
				if( !Double.isNaN(maxVelocity[i]) ) velocity[i] = (maxVelocity[i] < velocity[i] ? maxVelocity[i] : velocity[i]);
			}
		} else {
			//---
			// Position constraints are setted? (do both of them in the same loop)
			//---
			if( (minPosition != null) && (maxPosition != null) ) {
				for( int i = 0; i < position.length; i++ ) {
					if( !Double.isNaN(minPosition[i]) ) position[i] = (minPosition[i] > position[i] ? minPosition[i] : position[i]);
					if( !Double.isNaN(maxPosition[i]) ) position[i] = (maxPosition[i] < position[i] ? maxPosition[i] : position[i]);
				}
			} else {
				//---
				// Do it individually
				//---
				if( minPosition != null ) {
					for( int i = 0; i < position.length; i++ ) {
						if( !Double.isNaN(minPosition[i]) ) position[i] = (minPosition[i] > position[i] ? minPosition[i] : position[i]);
					}
				}
				if( maxPosition != null ) {
					for( int i = 0; i < position.length; i++ ) {
						if( !Double.isNaN(maxPosition[i]) ) position[i] = (maxPosition[i] < position[i] ? maxPosition[i] : position[i]);
					}
				}
			}

			//---
			// Velocity constraints are setted? (do both of them in the same loop)
			//---
			if( (minVelocity != null) && (maxVelocity != null) ) {
				for( int i = 0; i < velocity.length; i++ ) {
					if( !Double.isNaN(minVelocity[i]) ) velocity[i] = (minVelocity[i] > velocity[i] ? minVelocity[i] : velocity[i]);
					if( !Double.isNaN(maxVelocity[i]) ) velocity[i] = (maxVelocity[i] < velocity[i] ? maxVelocity[i] : velocity[i]);
				}
			} else {
				//---
				// Do it individually
				//---
				if( minVelocity != null ) {
					for( int i = 0; i < velocity.length; i++ ) {
						if( !Double.isNaN(minVelocity[i]) ) velocity[i] = (minVelocity[i] > velocity[i] ? minVelocity[i] : velocity[i]);
					}
				}
				if( maxVelocity != null ) {
					for( int i = 0; i < velocity.length; i++ ) {
						if( !Double.isNaN(maxVelocity[i]) ) velocity[i] = (maxVelocity[i] < velocity[i] ? maxVelocity[i] : velocity[i]);
					}
				}
			}
		}
	}

	/** Copy position[] to positionCopy[] */
	public void copyPosition(double positionCopy[]) {
		for( int i = 0; i < position.length; i++ )
			positionCopy[i] = position[i];
	}

	/** Copy position[] to bestPosition[] */
	public void copyPosition2Best() {
		for( int i = 0; i < position.length; i++ )
			bestPosition[i] = position[i];
	}

	public double getBestFitness() {
		return bestFitness;
	}

	public double[] getBestPosition() {
		return bestPosition;
	}

	public int getDimention() {
		return position.length;
	}

	public double getFitness() {
		return fitness;
	}

	public double[] getPosition() {
		return position;
	}

	public double[] getVelocity() {
		return velocity;
	}

	/**
	 * Initialize a particles's position and velocity vectors 
	 * @param maxPosition : Vector stating maximun position for each dimention
	 * @param minPosition : Vector stating minimum position for each dimention
	 * @param maxVelocity : Vector stating maximun velocity for each dimention
	 * @param minVelocity : Vector stating minimum velocity for each dimention
	 */
	public void init(double maxPosition[], double minPosition[], double maxVelocity[], double minVelocity[]) {
		for( int i = 0; i < position.length; i++ ) {
			if( Double.isNaN(maxPosition[i]) ) throw new RuntimeException("maxPosition[" + i + "] is NaN!");
			if( Double.isInfinite(maxPosition[i]) ) throw new RuntimeException("maxPosition[" + i + "] is Infinite!");

			if( Double.isNaN(minPosition[i]) ) throw new RuntimeException("minPosition[" + i + "] is NaN!");
			if( Double.isInfinite(minPosition[i]) ) throw new RuntimeException("minPosition[" + i + "] is Infinite!");

			if( Double.isNaN(maxVelocity[i]) ) throw new RuntimeException("maxVelocity[" + i + "] is NaN!");
			if( Double.isInfinite(maxVelocity[i]) ) throw new RuntimeException("maxVelocity[" + i + "] is Infinite!");

			if( Double.isNaN(minVelocity[i]) ) throw new RuntimeException("minVelocity[" + i + "] is NaN!");
			if( Double.isInfinite(minVelocity[i]) ) throw new RuntimeException("minVelocity[" + i + "] is Infinite!");

			// Initialize using uniform distribution
			position[i] = (maxPosition[i] - minPosition[i]) * Math.random() + minPosition[i];
			velocity[i] = (maxVelocity[i] - minVelocity[i]) * Math.random() + minVelocity[i];

			bestPosition[i] = Double.NaN;
		}
	}

	/**
	 * Create a new instance of this particle 
	 * @return A new particle, just like this one
	 */
	public Object selfFactory() {
		Class cl = this.getClass();
		Constructor cons;

		try {
			cons = cl.getConstructor((Class[])null);
		} catch(SecurityException e) {
			throw new RuntimeException(e);
		} catch(NoSuchMethodException e) {
			throw new RuntimeException(e);
		}

		try {
			return cons.newInstance((Object[])null);
		} catch(IllegalArgumentException e1) {
			throw new RuntimeException(e1);
		} catch(InstantiationException e1) {
			throw new RuntimeException(e1);
		} catch(IllegalAccessException e1) {
			throw new RuntimeException(e1);
		} catch(InvocationTargetException e1) {
			throw new RuntimeException(e1);
		}
	}

	public void setBestFitness(double bestFitness) {
		this.bestFitness = bestFitness;
	}

	public void setBestPosition(double[] bestPosition) {
		this.bestPosition = bestPosition;
	}


	/**
	 * Set fitness and best fitness accordingly.
	 * If it's the best fitness so far, copy data to bestFitness[]
	 * @param fitness : New fitness value
	 * @param maximize : Are we maximizing or minimizing fitness funcion?
	 */
	public void setFitness(double fitness, boolean maximize) {
		this.fitness = fitness;

		if( (maximize && (fitness > bestFitness)) // Maximize and bigger? => store data
				|| (!maximize && (fitness < bestFitness)) // Minimize and smaller? => store data too
				|| Double.isNaN(bestFitness) ) {
			copyPosition2Best();
			bestFitness = fitness;
		}
	}

	public void setPosition(double[] position) {
		this.position = position;
	}

	public void setVelocity(double[] velocity) {
		this.velocity = velocity;
	}

	/** Printable string */
	public String toString() {
		String str = "fitness: " + fitness + "\tbest fitness: " + bestFitness;

		if( position != null ) {
			str += "\n\tPosition:\t";
			for( int i = 0; i < position.length; i++ )
				str += position[i] + "\t";
		}

		if( velocity != null ) {
			str += "\n\tVelocity:\t";
			for( int i = 0; i < velocity.length; i++ )
				str += velocity[i] + "\t";
		}

		if( bestPosition != null ) {
			str += "\n\tBest:\t";
			for( int i = 0; i < bestPosition.length; i++ )
				str += bestPosition[i] + "\t";
		}

		str += "\n";
		return str;
	}

	/**
	 * 启动变异策略 需要加入变异策略时重写该方法
	 */
	public void InitMutation()
	{
		//do nothing
	}
}