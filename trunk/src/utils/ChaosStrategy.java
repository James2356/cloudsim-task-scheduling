package utils;

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

    }

    private double a;
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

    public double LM(int n)
    {
        double result = 0.0;
        return result;
    }

    public double PLM(int n)
    {
        double result = 0.0;
        return result;
    }
}
