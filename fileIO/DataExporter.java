package fileIO;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by kazuki on 2016/06/20.
 */
public class DataExporter {

    public static void export(ArrayList<String> list, String fileName) {
        try {
            File file = new File(fileName);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "Shift_JIS"));

            for (String link : list) {
                bw.write(link);
            }

            bw.close();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
