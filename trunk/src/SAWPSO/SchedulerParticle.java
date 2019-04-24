package SAWPSO;

import net.sourceforge.jswarm_pso.Particle;
import utils.ChaosStrategy;
import utils.Constants;

import java.util.Random;

public class SchedulerParticle extends Particle {


    /** 粒子活跃判断计数*/
    int count;


    public SchedulerParticle() {
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
        count=0;
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

    /**
     * 启动混沌的变异策略
     */
    @Override
    public void InitMutation()
    {
        //变异策略部分。
        double temp = this.getFitness()-this.getBestFitness();
        if(Math.abs(temp)<0.005)
        {
            count++;
            if(count>=10)//粒子连续10次判断都不活跃 进入变异环节
            {
                count=0;
                //启动变异策略
                System.out.println("go particle mutation!");
                ChaosStrategy instance = ChaosStrategy.getInstance();
                instance.CalChaos();

                double[] new_vel = new double[Constants.NO_OF_TASKS];
                double[] new_pos = new double[Constants.NO_OF_TASKS];
                for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
                    //混沌映射方式生成变异粒子的速度和位置
                    new_pos[i] = instance.PLM(1,instance.getChaosValue())*Constants.NO_OF_VMS;
                    new_vel[i] = instance.LM(1,instance.getChaosValue());
                }
                setPosition(new_pos);
                setVelocity(new_vel);
            }
        }
    }
}
