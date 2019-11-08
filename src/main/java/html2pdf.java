import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.google.common.base.Strings;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

public class html2pdf {
    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("D:\\Downloads\\file");
        String[] fileArr = file.list((dir, name) -> name.endsWith("html"));


        assert fileArr != null;

        for (String s : fileArr) {
            Pdf pdf = new Pdf();
//            pdf.addPageFromFile("D:\\Downloads\\" + i + ".html");
            pdf.addPageFromFile("D:\\Downloads\\file\\" + s);
            pdf.saveAs("D:\\" + s + ".pdf");
        }
        // Save the PDF

    }
}
