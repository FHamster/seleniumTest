package flight;

public class Flight {
    private int day;
    private String name;
    //计划到达时间
    private String dplan;
    //实际的到达时间（图片转文字）
    private String dreal;
    //出发地
    private String dplace;
    //计划到达
    private String aplan;
    //实际到达
    private String areal;
    //到达地
    private String aplace;
    //准点率
    private String race;
    //状态
    private String gre_cor;

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s", day-5, name, dplan, dreal, dplace, aplan, areal, aplace, race, gre_cor);
    }

    public String toString2() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ",\"dplan\":\"" + dplan + '\"' +
                ",\"dreal\":\"" + dreal + '\"' +
                ",\"dplace\":\"" + dplace + '\"' +
                ",\"aplan\":\"" + aplan + '\"' +
                ",\"areal\":\"" + areal + '\"' +
                ",\"aplace\":\"" + aplace + '\"' +
                ",\"race\":\"" + race + '\"' +
                ",\"gre_cor\":\"" + gre_cor + '\"' +
                "},";
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDplan() {
        return dplan;
    }

    public void setDplan(String dplan) {
        this.dplan = dplan;
    }

    public String getDreal() {
        return dreal;
    }

    public void setDreal(String dreal) {
        this.dreal = dreal;
    }

    public String getDplace() {
        return dplace;
    }

    public void setDplace(String dplace) {
        this.dplace = dplace;
    }

    public String getAplan() {
        return aplan;
    }

    public void setAplan(String aplan) {
        this.aplan = aplan;
    }

    public String getAreal() {
        return areal;
    }

    public void setAreal(String areal) {
        this.areal = areal;
    }

    public String getAplace() {
        return aplace;
    }

    public void setAplace(String aplace) {
        this.aplace = aplace;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGre_cor() {
        return gre_cor;
    }

    public void setGre_cor(String gre_cor) {
        this.gre_cor = gre_cor;
    }
}
