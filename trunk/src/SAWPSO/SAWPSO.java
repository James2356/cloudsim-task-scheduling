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

    /**
     * 算法主流程
     * @return 返回粒子群全局最优位置的粒子位置，即位置向量，即最优解
     */
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
            double w = swarm.getInertia();//获取当前惯性权值
            swarm.evolve();//算法正式的计算流程
            if (i % 10 == 0) {
                System.out.printf("Gloabl best at iteration (%d): %f\n", i+1, swarm.getBestFitness());
            }
            w = CalSOW(w_max, w_min, i, w);
            swarm.setInertia(w);//设置惯性权值
        }
        System.out.println("\nThe best fitness value: " + swarm.getBestFitness() + "\nBest makespan: " + ff.calcMakespan(swarm.getBestParticle().getBestPosition()));
        System.out.println("The best totalcost:"+ff.calcTotalTime(swarm.getBestParticle().getPosition()));
        System.out.println("The best solution is: ");
        SchedulerParticle bestParticle = (SchedulerParticle) swarm.getBestParticle();
        System.out.println(bestParticle.toString());

        return swarm.getBestPosition();
    }

    /**
     * 根据迭代的次数计算自组织方式的惯性权重
     * @param w_max 权重上限
     * @param w_min 权重下限
     * @param i 当前迭代循环的中间变量 一般从0开始
     * @param w 惯性权重
     * @return 返回计算后的惯性权重值
     */
    private double CalSOW(double w_max, double w_min, int i, double w) {
        double iter = i+1;//当前迭代的次数
        double ret = Calculator.div(iter, Constants.NO_OF_Iterations);
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
        return w;
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
