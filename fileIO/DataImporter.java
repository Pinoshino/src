package fileIO;

import java.io.*;
import java.util.ArrayList;


/**
 * Created by kazuki on 2016/06/20.
 */
public class DataImporter {

    public static ArrayList<String> load(String fileName) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "Shift_JIS"));

            String str;
            while ((str = br.readLine()) != null) {
                if (str.equals("")) continue;

                list.add(str);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        return list;
    }

    ArrayList<String[]> loadList(String fileName) {
        ArrayList<String[]> lists = new ArrayList<String[]>();
        ArrayList<String> list = load(fileName);

        for (String str : list) {
            String[] lines = str.split(",", 0);

            lists.add(lines);
        }
        return lists;
    }
}
