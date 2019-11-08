package pos;

//粒子的状态类
class ParticleState {
    double x;
    double y;

    double f;//适应度，即求解函数值

    ParticleState(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
