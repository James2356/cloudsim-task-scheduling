package utils;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
public class Calculator
{
    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    //构造器私有化，让这个类不能实例化
    private Calculator(){}
    //提供精确的加法运算
    public static double add(double v1, double v2)
    {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }
    //精确的减法运算
    public static double sub(double v1, double v2)
    {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }
    //精确的乘法运算
    public static double mul(double v1, double v2)
    {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).doubleValue();
    }
    //提供（相对）精确的除法运算，当发生除不尽的情况时
    //精确到小数点后10位的数字四舍五入
    public static double div(double v1, double v2)
    {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double div(double d1,double d2,int len) {// 进行除法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2,len,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void LogResult(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));// true,进行追加写。
            out.write(conent+"\r\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    public static void main(String[] args)
//    {
//        System.out.println("0.05 + 0.01 = " + Caculator.add(0.05, 0.01));
//        System.out.println("1.0 - 0.42 = " + Caculator.sub(1.0, 0.42));
//        System.out.println("4.015*100 = " + Caculator.mul(4.015, 100));
//        System.out.println("123.3/100 = " + Caculator.div(123.3, 100));
//    }
}
