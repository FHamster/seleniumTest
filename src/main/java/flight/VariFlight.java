package flight;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;


/**
 * 飞常准航班爬虫
 * 网址http://www.variflight.com/
 * 遵守网站robots.txt协议爬取
 */
public class VariFlight {
    static WebDriver driver;

    public static void main(String[] args) throws Exception {
        //设置系统变量
        System.setProperty("webdriver.chrome.driver", "chromedriver");
//        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        //实例化一个Chrome浏览器的实例
        driver = new ChromeDriver();
//12 18
        driver.get("http://www.variflight.com/");
        for (int i = 15; i < 18; i++) {

            for (String city : CityList.cityList) {
                Thread.sleep(1000);
                //切换为按照起降
                driver.findElement(By.id("myOrder")).click();

                //选择起飞城市
                driver.findElement(By.id("dep_city")).click();
                driver.findElement(By.id("dep_city")).sendKeys(city);
                driver.findElement(By.id("dep_city")).click();

                //选择时间
                driver.findElement(By.id("cityDatepicker")).click();
                List<WebElement> timeElementList = driver.findElements(By.cssSelector(".datepicker-panel > [data-view=days] > li"));
                //时间选择器
                timeElementList.get(i).click();

                //选择到达城市
                driver.findElement(By.id("arr_city")).click();
                driver.findElement(By.id("arr_city")).sendKeys("上海浦东");
                driver.findElement(By.id("arr_city")).click();


                //点击提交
                driver.findElement(By.id("byCityBtn")).click();

                List<Flight> list = getList(i);

                Output.out(list);
                Thread.sleep(3000);
                driver.navigate().back();
            }
        }


        driver.close();


    }

    public static List<Flight> getList(int i) {

        List<WebElement> flightInfoList = driver.findElements(By.cssSelector("#list > li > .li_com"));
        List<Flight> list = new ArrayList<>();
        for (WebElement x : flightInfoList) {
            Flight flight = new Flight();
            //获取航班名称
            flight.setName(x.findElement(By.cssSelector("span:nth-child(1)")).getText());
//            System.out.println(x.findElement(By.cssSelector("span:nth-child(1)")).getText());
            //计划起飞
            flight.setDplan(x.findElement(By.cssSelector("span:nth-child(2)")).getText());
//            System.out.println(x.findElement(By.cssSelector("span:nth-child(2)")).getText());
            //实际起飞img
            try {
                flight.setDreal(x.findElement(By.cssSelector("span:nth-child(3) > [src]")).getAttribute("src"));
            } catch (Exception e) {
                e.getCause();
            }
//            System.out.println(x.findElement(By.cssSelector("span:nth-child(3) > [src]")).getAttribute("src"));
            //出发地
            flight.setDplace(x.findElement(By.cssSelector("span:nth-child(4)")).getText());
//            System.out.println(x.findElement(By.cssSelector("span:nth-child(4)")).getText());
            //计划到达
            flight.setAplan(x.findElement(By.cssSelector("span:nth-child(5)")).getText());
//            System.out.println(x.findElement(By.cssSelector("span:nth-child(5)")).getText());
            //实际到达img
            try {
                flight.setAreal(x.findElement(By.cssSelector("span:nth-child(6) > [src]")).getAttribute("src"));
            } catch (Exception e) {
                e.getCause();
            }
//            System.out.println(x.findElement(By.cssSelector("span:nth-child(6)> [src]")).getAttribute("src"));
            //到达地
            flight.setAplace(x.findElement(By.cssSelector("span:nth-child(7)")).getText());
//            System.out.println(x.findElement(By.cssSelector("span:nth-child(7)")).getText());
            //准点率img
            try {
                flight.setRace(x.findElement(By.cssSelector("span:nth-child(8) > [src]")).getAttribute("src"));
            } catch (Exception e) {
                e.getCause();
            }
//            System.out.println(x.findElement(By.cssSelector("span:nth-child(8)> [src]")).getAttribute("src"));
            //状态
            flight.setGre_cor(x.findElement(By.cssSelector("span:nth-child(9)")).getText());
//            System.out.println(x.findElement(By.cssSelector("span:nth-child(9)")).getText());

            flight.setDay(i);

            System.out.println(flight);
            list.add(flight);
        }
        return list;
    }
}
