package MMN;

import java.util.Random;

class MM1 {
    private double in;//in为langda,一秒钟到来了几个人
    double out;//out为miu,人离开的速率，服务速率

    private double[] arrive_time;//到达时间间隔
    double[] arrivetime;//顾客到达时间
    double[] serve_time;//服务时间
    double[] leave_time;//离开时间
    double[] wait_time;//等待（在服务窗的总时间）时间
    double[] queue_time;//排队时间

    /**
     * 构造函数，知道langda和miu以及number仿真总人数就可以进行仿真
     *
     * @param in     M/M/1 lambda(λ)值
     * @param out    M/M/1 miu(μ)值倒数
     * @param number M/M/1 仿真规模
     */
    MM1(double in, double out, int number) {
        arrive_time = new double[number];//到达时间间隔
        arrivetime = new double[number];//顾客到达时间
        serve_time = new double[number];//服务时间
        leave_time = new double[number];//顾客离开时间
        wait_time = new double[number];//等待时间，（在服务窗的总时间）
        queue_time = new double[number];//排队时间
        this.in = in;
        this.out = out;
    }

    /**
     * 通过负指数分布landa的值产生到达人群的时间间隔，计算得出时间点（泊松分布是产生人群，与到达人群是无关的）
     *
     * @param number M/M/1 仿真规模
     */
    void Produce(int number) {
        for (int i = 0; i < number; i++) {
            Random random = new Random();// 定义随机类
            double result = random.nextDouble();// 返回0到1之间的小数
            arrive_time[i] = Math.log(result) * -1 / in;//负指数分布函数产生到达时间间隔
        }
        arrivetime[0] = arrive_time[0];
        for (int i = 1; i < number; i++) {
            arrivetime[i] = arrivetime[i - 1] + arrive_time[i];//通过到达时间间隔计算出到达时间
        }

    }

    /**
     * 通过负指数分布miu的值计算服务时间，队列时间以及等待时间
     *
     * @param number M/M/1 仿真规模
     */
    void Leave(int number) {
        for (int i = 0; i < number; i++) {
            Random random = new Random();// 定义随机类
            double result = random.nextDouble();// 返回0到1之间的小数
            if (result < 0) result *= -1;
            serve_time[i] = Math.log(result) * -1 / out;
        }
        leave_time[0] = serve_time[0] + arrivetime[0];
        /*
         * 离开时间的计算方法：
         * 如果前面那个人的离开时间大于我，我的离开时间等于他的离开时间加上我的服务时间
         * 如果前面那个人的离开时间小于我，我的离开时间等于我的到达时间加上我的服务时间
         */
        for (int i = 1; i < number; i++) {
            if (leave_time[i - 1] < arrivetime[i])
                leave_time[i] = arrivetime[i] + serve_time[i];
            else
                leave_time[i] = leave_time[i - 1] + serve_time[i];
        }

        for (int i = 0; i < number; i++) {
            queue_time[i] = leave_time[i] - arrivetime[i] - serve_time[i];//队列时间的计算
            wait_time[i] = leave_time[i] - arrivetime[i];//等待时间的计算
        }
    }

    /**
     * 打印出编号为x的人的到达时间，队列时间，服务时间与离开时间
     *
     * @param x 编号
     */
    void Print_people_x(int x) {
        System.out.println("第" + x + "个人的数据如下：");
        System.out.println("到达时间：" + arrivetime[x]);
        System.out.println("队列时间：" + queue_time[x]);
        System.out.println("服务时间：" + serve_time[x]);
        System.out.println("离开时间：" + leave_time[x]);
    }

    /**
     * 打印出所需要的平均逗留时间，平均队列时间，平均服务时间与平均队长
     *
     * @param x 规模
     */
    void Print_need(int x) {
        double[] line_wait;//当前队长（包括正在服务的）

        double sum_wait = 0, sum_queue = 0, sum_serve = 0, sum_line = 0;
        for (int i = 0; i < x; i++) {
            sum_wait += wait_time[i];
            sum_queue += queue_time[i];
            sum_serve += serve_time[i];
        }
        sum_wait /= x;
        sum_queue /= x;
        sum_serve /= x;
        System.out.println("平均逗留时间为：" + sum_wait);
        System.out.println("平均队列时间为：" + sum_queue);
        System.out.println("平均服务时间为：" + sum_serve);

        double last_time = leave_time[x - 1];
        line_wait = new double[(int) last_time + 1];
        int time = 0;
        for (time = 0; time < last_time; time++) {
            for (int i = 0; i < x; i++) {
                if (time > arrivetime[i] && time < leave_time[i]) {
                    line_wait[time]++;
                }
            }
            sum_line += line_wait[time];
        }
        sum_line /= time;
        System.out.println("平均队长为：" + sum_line);
    }

    /**
     * 打印出时间为T时的队列人数，此时的平均服务时间，到达该窗口的累计人数，当前窗口人数
     *
     * @param time   需要知道的时间点
     * @param number M/M/1 仿真规模
     */
    void Print_time(double time, int number) {

        int queue_people = 0;
        int arrive_people = 0;
        int leave_people = 0;
        double servertime = 0;
        for (int i = 0; i < number; i++) {
            if (arrivetime[i] < time && leave_time[i] > time)
                queue_people++;
            if (arrivetime[i] < time)
                arrive_people++;
            if (leave_time[i] < time)
                leave_people++;
        }
        servertime = (time - arrive_time[0]) / (leave_people + 1);
        System.out.println("第" + time + "时间的数据如下：");
        System.out.println("此时队列人数有：" + queue_people);
        System.out.println("此时的平均服务时间" + servertime);
        System.out.println("到达该窗口累计人数：" + arrive_people);
        System.out.println("离开此窗口的累计人数：" + leave_people);
    }
}
