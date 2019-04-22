package SAWPSO;

import net.sourceforge.jswarm_pso.Swarm;
import utils.Calculator;
import utils.Constants;

public class SAWPSO {
    private static Swarm swarm;
    private static SchedulerParticle particles[];
    private static SchedulerFitnessFunction ff = new SchedulerFitnessFunction();

    public SAWPSO() {
        initParticles();
    }


    public double[] run() {
        swarm = new Swarm(Constants.POPULATION_SIZE, new SchedulerParticle(), ff);
        double w_max = 0.9;
        double w_min = 0.5;
        swarm.setInertia(0.9);
        swarm.setMinPosition(0);
        swarm.setMaxPosition(Constants.NO_OF_VMS - 1);
        swarm.setMaxMinVelocity(0.5);
        swarm.setParticles(particles);
        swarm.setParticleUpdate(new SchedulerParticleUpdate(new SchedulerParticle()));
        for (int i = 0; i < Constants.NO_OF_Iterations; i++) {
            double w = swarm.getInertia();//获取惯性权值
            double iter = i+1;//当前迭代次数
            double ret = Calculator.div(iter,Constants.NO_OF_Iterations);
            if(iter>=1&iter<=Constants.NO_OF_Iterations/2)
            {
                w= -Math.pow(ret,2.0)+w_max;
            }
            if(iter<=Constants.NO_OF_Iterations&&iter>Constants.NO_OF_Iterations/2)
            {
                w= -Math.pow(ret-1.0,2.0)+w_min;
            }
            if(w>w_max)
            {
                w = w_max;
            }
            if(w<w_min)
            {
                w = w_min;
            }
            swarm.setInertia(w);//设置惯性权值
            swarm.evolve();//算法正式的计算流程
            if (i % 10 == 0) {
                System.out.printf("Gloabl best at iteration (%d): %f\n", i+1, swarm.getBestFitness());
            }
        }
        System.out.println("\nThe best fitness value: " + swarm.getBestFitness() + "\nBest makespan: " + ff.calcMakespan(swarm.getBestParticle().getBestPosition()));

        System.out.println("The best solution is: ");
        SchedulerParticle bestParticle = (SchedulerParticle) swarm.getBestParticle();
        System.out.println(bestParticle.toString());

        return swarm.getBestPosition();
    }

    private static void initParticles() {
        particles = new SchedulerParticle[Constants.POPULATION_SIZE];
        for (int i = 0; i < Constants.POPULATION_SIZE; ++i)
            particles[i] = new SchedulerParticle();
    }

    public void printBestFitness() {
        System.out.println("\nBest fitness value: " + swarm.getBestFitness() +
                "\nBest makespan: " + ff.calcMakespan(swarm.getBestParticle().getBestPosition()));
    }
}
