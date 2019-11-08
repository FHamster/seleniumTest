import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;

import java.io.File;
import java.io.IOException;

public class html2pdf2 {
    public static void main(String[] args) throws IOException, InterruptedException {


        Pdf pdf = new Pdf();
        pdf.addPageFromFile("D:\\Downloads\\file\\港口与环境工程学院-刘洋-55.html");
        pdf.saveAs("D:\\" + "港口与环境工程学院-刘洋-55.html" + ".pdf");


    }
}
