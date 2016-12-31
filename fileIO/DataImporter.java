package fileIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by kazuki on 2016/06/20.
 */
public class DataImporter {

    public static ArrayList<String> load(String fileName) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            File file = new File(fileName);
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    new FileInputStream(file), "Shift_JIS"));

            Scanner sc = new Scanner(file);

            String str;
            while (sc.hasNext()) {
                str = sc.nextLine();
                if (str.equals("")) continue;
                list.add(str);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        return list;
    }

    static ArrayList<String[]> loadList(String fileName) {
        ArrayList<String[]> lists = new ArrayList<String[]>();
        ArrayList<String> list = load(fileName);

        for (String str : list) {
            String[] lines = str.split(",");

            lists.add(lines);
        }
        return lists;
    }
}
