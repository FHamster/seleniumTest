package flight;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class Output {
    public static void out(List<Flight> list) throws IOException {
        String file = "stream.txt";
        String charSet = "UTF-8";
        //写字符转换成字节流
        FileOutputStream fileWriter = new FileOutputStream(file, true);
        OutputStreamWriter writer = new OutputStreamWriter(fileWriter, charSet);
        try {
            for (Flight x : list) {
                writer.write(x.toString() + '\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}