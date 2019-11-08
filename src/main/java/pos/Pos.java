package pos;

public class Pos {

    private int n;                        //粒子数量
    private ParticleState[] p;            //粒子数组
    private ParticleState[] v;            //速度数组
    private ParticleState[] pbest;        //粒子最优解
    private ParticleState gbest;        //全局最优解
    private double vmax;                //最大速度
    private int c1, c2;                    //学习参数
    private double w = 0.3;//惯性权重


    public static void main(String[] args) {
        int maxit = 200;//最大迭代次数

        Pos e = new Pos();
        e.init();
        e.PSO(maxit);
    }

    //适应函数
    public void fitnessFunction() {
        for (int i = 0; i < n; i++) {
            p[i].f = p[i].x * p[i].x * p[i].x + 3 * p[i].x * p[i].y * p[i].y - 15 * p[i].x - 12 * p[i].y;
        }
    }

    //初始化
    private void init() {
        n = 1000;
        p = new ParticleState[n];
        v = new ParticleState[n];
        pbest = new ParticleState[n];
        gbest = new ParticleState(0.0, 0.0);
        c1 = c2 = 3;
        vmax = 0.1;

        for (int i = 0; i < n; i++) {
            p[i] = new ParticleState(Math.random(), Math.random());
            v[i] = new ParticleState(Math.random() * vmax, Math.random() * vmax);
        }

        fitnessFunction();

        //初始化粒子与集群的最优值
        gbest.f = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            pbest[i] = p[i];
            if (p[i].f > gbest.f) {
                gbest = p[i];
            }
        }
        System.out.println("初始最优值：" + gbest.x + " " + gbest.y + " " + " " + gbest.f);
    }

    //粒子群算法	max-迭代次数
    private void PSO(int max) {
        for (int i = 0; i < max; i++) {

            for (int j = 0; j < n; j++) {
                //更新粒子速度
                double vx = w * v[j].x + c1 * Math.random() * (pbest[j].x - p[j].x) + c2 * Math.random() * (gbest.x - p[j].x);
                double vy = w * v[j].y + c1 * Math.random() * (pbest[j].y - p[j].y) + c2 * Math.random() * (gbest.y - p[j].y);

                if (vx > vmax) vx = vmax;
                if (vy > vmax) vy = vmax;

                v[j] = new ParticleState(vx, vy);

                //更新粒子的位置
                p[j].x += v[j].x;
                p[j].y += v[j].y;
            }

            fitnessFunction();

            //更新个体极值和群体极值
            for (int j = 0; j < n; j++) {
                if (p[i].f > pbest[i].f) {
                    pbest[i] = p[i];
                }
                if (p[i].f > gbest.f) {
                    gbest = p[i];
                }
            }
            System.out.println("===" + i + "===" + gbest.x + " " + gbest.y + " " + " " + gbest.f);
        }
    }
}
