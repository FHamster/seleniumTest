package MMN;/*
 * 完整版排队论仿真模型（MM1与MMN）
 * 所需要的其他都可在此基础上更改
 * 2018.1.21-2017.1.28陆续修改而成
 * from 济民宝宝
 */

import java.util.Arrays;
import java.util.Random;

class MMN extends MM1 {
    private int N;

    /**
     * 构造函数，知道langda和miu以及number仿真总人数就可以进行仿真,增加参数N
     *
     * @param in     M/M/1 lambda(λ)值
     * @param out    M/M/1 miu(μ)值倒数
     * @param number M/M/1 仿真规模
     * @param N      M/M/N 服务机数量
     */
    MMN(double in, double out, int number, int N) {
        super(in, out, number);
        this.N = N;
        // TODO Auto-generated constructor stub
    }

    @Override
    void Leave(int number) {
        for (int i = 0; i < number; i++) {
            Random random = new java.util.Random();// 定义随机类
            double result = random.nextDouble();// 返回0到1之间的小数
            if (result < 0) result *= -1;
            serve_time[i] = Math.log(result) * -1 / out;

        }

        double[] leave_x = new double[N];
        for (int i = 0; i < N; i++) {
            leave_time[i] = serve_time[i] + arrivetime[i];
            leave_x[i] = leave_time[i];
        }

        for (int i = N; i < number; i++) {
            Arrays.sort(leave_x);
            if (leave_x[0] < arrivetime[i])//到达时间比最小的都大时
            {
                leave_time[i] = arrivetime[i] + serve_time[i];
                leave_x[0] = leave_time[i];
            } else if (leave_x[0] > arrivetime[i]) {
                leave_time[i] = leave_x[0] + serve_time[i];
                leave_x[0] = leave_time[i];
            }
        }

        for (int i = 0; i < number; i++) {
            queue_time[i] = leave_time[i] - arrivetime[i] - serve_time[i];
            wait_time[i] = leave_time[i] - arrivetime[i];
        }
    }
}

