import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * 这个是一个这学期初做的一个采集绿色通道的采集
 */
public class Test {
    public static void main(String[] args) throws Exception {
        //设置系统变量
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        //实例化一个Chrome浏览器的实例
        WebDriver driver = new ChromeDriver();

//        driver.get("http://localhost:8080/GreenChannel/DisplayStuInfo.jsp?tdsourcetag=s_pcqq_aiomsg");
        driver.get("http://119.3.226.50:8080/GreenChannel/DisplayStuInfo.jsp?tdsourcetag=s_pcqq_aiomsg");


        int from = 500;
        int to = 585;
        Robot robot = new Robot();
//        driver.findElements(By.cssSelector("[value = 查看详情]")).size()
        for (int i = from; i < to; i++) {
            //声明一个WebElement的list变量
            List<WebElement> elementList;

//            elementList = driver.findElements(By.cssSelector("tbody > tr > td > input "));

            //通过浏览器找元素
            elementList = driver.findElements(By.cssSelector("[value = 查看详情]"));
            elementList.get(i).click();

            String name = driver.findElement(By.id("stuname")).getText();

            String xue = driver.findElement(By.id("stuAca")).getText();
            System.out.println(xue + name + i);
            //李彬武-航海学院.html
//            String text = xue + "-" + name + ".html";
//            printf("格式化字符串",age，ge)
            String text = String.format("%d-%s-%s.html", i, xue, name);

//            String text = String.format("%d.html", i);
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);


            //ctrl+s
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            //等待
            Thread.sleep(1000);

            //从剪切版获取字符串
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);


            //等待
            Thread.sleep(1000);

            //按回车
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            //等待
            Thread.sleep(3000);
            driver.navigate().back();
        }


//        elementList = driver.findElements(By.cssSelector("tbody > tr > td > input"));
//        elementList.get(2).click();


        driver.close();
    }
}
