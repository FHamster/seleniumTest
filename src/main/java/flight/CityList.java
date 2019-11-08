package flight;

import java.util.ArrayList;
import java.util.List;

public class CityList {
    public static void main(String[] args) {
        cityList.stream().forEach(System.out::println);

    }
    public static List<String> cityList = new ArrayList<>();
    static {
        cityList.add("广州白云");
        cityList.add("北京首都");
        cityList.add("成都双流");
        cityList.add("深圳宝安");
        cityList.add("昆明长水");
        cityList.add("西安咸阳");
        cityList.add("重庆江北");
        cityList.add("杭州萧山");
        cityList.add("厦门高崎");
        cityList.add("南京禄口");
        cityList.add("武汉天河");
        cityList.add("沈阳桃仙");
        cityList.add("青岛流亭");
        cityList.add("福州长乐");
        cityList.add("三亚凤凰");
        cityList.add("天津滨海");
    }
}
