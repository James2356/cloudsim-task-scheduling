package SAPSO;

import net.sourceforge.jswarm_pso.FitnessFunction;
import net.sourceforge.jswarm_pso.Particle;
import utils.Calculator;
import utils.Constants;

import java.util.Random;

public class SchedulerParticle extends Particle {

    //起始温度
    private double T;

    //终止温度
    private double T_limit;

    //退火速度
    private double a;


    SchedulerParticle() {
        super(Constants.NO_OF_TASKS);
        double[] position = new double[Constants.NO_OF_TASKS];
        double[] velocity = new double[Constants.NO_OF_TASKS];

        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            Random randObj = new Random();
            position[i] = randObj.nextInt(Constants.NO_OF_VMS);
            velocity[i] = Math.random();
        }
        setPosition(position);
        setVelocity(velocity);
        T=1000.0;
        a=0.95;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < Constants.NO_OF_VMS; i++) {
            String tasks = "";
            int no_of_tasks = 0;
            for (int j = 0; j < Constants.NO_OF_TASKS; j++) {
                if (i == (int) getPosition()[j]) {
                    tasks += (tasks.isEmpty() ? "" : " ") + j;
                    ++no_of_tasks;
                }
            }
            if (tasks.isEmpty()) output += "There is no tasks associated to VM " + i + "\n";
            else
                output += "There are " + no_of_tasks + " tasks associated to VM " + i + " and they are " + tasks + "\n";
        }
        return output;
    }

    @Override
    public void InitMutation()
    {
        //置换规则获取随机两个任务,并取对应的虚拟机
        double[] position = this.getBestPosition();

        //扰动当前局部最优解,得到first，second的值 置换规则
        Random rd = new Random();
        int first=rd.nextInt(Constants.NO_OF_TASKS);//任务的序号
        int second=rd.nextInt(Constants.NO_OF_TASKS);//另一个任务的序号

        if(position[first]!=position[second])
        {
            //交换虚拟机
            double tmp = 0.0;
            tmp = position[first];
            position[first] = position[second];
            position[second] = tmp;

            //计算新解的适应度值（执行成本）
            double fitness = new SchedulerFitnessFunction().evaluate(position);
            if(fitness<this.getBestFitness())
            {
                //接受新解
                AcceptNewPosition(position, fitness);
            }
            else
            {
                CalByMetropolis(position, fitness);
            }
        }
    }

    private void AcceptNewPosition(double[] position, double fitness) {
        setBestPosition(position);
        setBestFitness(fitness);
    }

    private void CalByMetropolis(double[] position, double fitness) {
        //如果交换后任务的适应度值(任务执行时间)较小减少,那么就接受新解,否则根据模拟退火的 Metropolis 准则判断是否接受新解
        double temp = this.getFitness()-this.getBestFitness();
        double p= temp<=0? 1.0 : Math.exp(-Calculator.div(temp, T));
        if(p==1.0)
        {
            AcceptNewPosition(position, fitness);
        }
        else
        {
            double value = GenerateValueUnderCurrentT();
            if(p>=value)
            {
                AcceptNewPosition(position, fitness);
            }
        }
        T= Calculator.mul(a, T);
    }


    private double GenerateValueUnderCurrentT()
    {
        Random rd = new Random();
        double value = rd.nextDouble();
        return value;
    }
}
