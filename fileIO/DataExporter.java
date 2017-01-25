package fileIO;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by kazuki on 2016/06/20.
 */
public class DataExporter {

    public static void export(ArrayList<String> list, String fileName) {
        try {
            File csv = new File(fileName);
            if (!csv.exists()) csv.createNewFile();
            else csv.delete();
            PrintWriter csvWriter = new PrintWriter(new FileWriter(csv));


//            File file = new File(fileName);
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
//                    new FileOutputStream(file), "Shift_JIS"));

            for (String link : list) {
                csvWriter.println(link);
                csvWriter.flush();
            }

            csvWriter.close();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
