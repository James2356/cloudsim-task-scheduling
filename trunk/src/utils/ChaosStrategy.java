package utils;

import java.util.Random;

public class ChaosStrategy
{
    //使用静态内部类实现 本人使用的目前最优单例模式线程安全实现

    /** 可以看到使用这种方式我们没有显式的进行任何同步操作，那他是如何保证线程安全呢？
     * 和饿汉模式一样，是靠JVM保证类的静态成员只能被加载一次的特点，
     * 这样就从JVM层面保证了只会有一个实例对象。
     * 那么问题来了，这种方式和饿汉模式又有什么区别呢？不也是立即加载么？
     * 实则不然，加载一个类时，其内部类不会同时被加载。
     * 一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。
     * */
    private ChaosStrategy()
    {
        chaosvalue = 0.0;
        u=3.9999;//完全混沌状态
    }

    private double u;

    private static class ChaosStrategyInner
    {
        private static ChaosStrategy instance= new ChaosStrategy();
    }

    public static ChaosStrategy getInstance()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ChaosStrategyInner.instance;
    }

    private double chaosvalue;
    public double getChaosValue()
    {
        return chaosvalue;
    }

    public void setChaosValue(double value)
    {
        this.chaosvalue = value;
    }

    //计算拜托初始值影响，迭代500次，得到混沌状态值，基于Logistic映射产生
    public void CalChaos()
    {
        Random rd = new Random();
        double chaosvalue = LM(500,rd.nextDouble());
        setChaosValue(chaosvalue);
    }

    public double LM(int n,double x0)
    {
        double result = x0;//迭代的初始值
        for(int i=0;i<n;i++)//迭代n次
        {
            result = Calculator.mul(Calculator.mul(Calculator.sub(1.0, result), result), u);
        }
        setChaosValue(result);
        return result;
    }

    public double PLM(int n,double x0)
    {
        double result = x0;//迭代的初始值
        for(int i=0;i<n;i++)//迭代n次
        {
            if(result>=0.0&&result<=0.5)
                result = Calculator.mul(Calculator.mul(Calculator.sub(0.5, result), result), u);
            if(result>=0.5&&result<=1.0)
                result = 1.0-Calculator.mul(Calculator.mul(Calculator.sub(1.0, result), Calculator.sub(result,0.5)), u);
        }
        setChaosValue(result);
        return result;
    }
}
