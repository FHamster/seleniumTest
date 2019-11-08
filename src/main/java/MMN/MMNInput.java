package MMN;

/**
 * 仿真参数输入类
 */
public class MMNInput {
    public static void main(String[] args) {
        //仿真规模
        int number = 10000;

        for (int i = 1; i < 25; i++) {
            System.out.println("N=" + i);
            //15/60=0.25 (缓冲区每分钟15辆放行)
            //1/(29.8+15)=0.022 (上客时间+出乘车区时间)的倒数
            MMN test2 = new MMN(0.25, 0.022, number, i);
            test2.Produce(number);
            test2.Leave(number);
            test2.Print_need(number);
            System.out.println("====================分割符号==================");
        }
    }
}
