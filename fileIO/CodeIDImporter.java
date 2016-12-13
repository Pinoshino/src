package fileIO;

import param.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kazuki on 2016/08/09.
 */
public class CodeIDImporter extends DataImporter {
    static String fileName = Param.codeListFile;

    public static Map<String, String> importCodeID() {
        ArrayList<String[]> list = loadList(fileName);
        Map<String, String> codeList = new HashMap<String, String>();

        for (String[] lines : list) {
            codeList.put(lines[0], lines[1]);
        }
        return codeList;
    }
}
