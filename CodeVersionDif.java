import data.LinkData;
import fileIO.DataExporter;
import fileIO.DataImporter;
import fileIO.LinkFileImporter;
import fileIO.LinkListExporter;

import java.util.ArrayList;

import static param.Param.linkListFile;

/**
 * Created by kazuki on 2016/12/08.
 */
public class CodeVersionDif {
    static String beforeFile = "data/import/beforeCodeList.txt";
    static String afterFile = "data/import/afterCodeList.txt";
    static String difFile = "data/export/difCodeList.txt";
    static String difCodeFile = "data/import/difCodeList.txt";

    public static void main(String[] args) {
        applyDif();
    }

    public static void applyDif() {
        String version = "v2.5.0";

        ArrayList<String> difCode = DataImporter.load(difCodeFile);
        ArrayList<LinkData> links = LinkFileImporter.importLink_ver(linkListFile);
        int flag = 0; //1=delete 2=add
        for (String code : difCode) {
            if (code.equals("deleted code")) {
                flag = 1;
                continue;
            } else if (code.equals("add code")) {
                flag = 2;
                continue;
            }
            for (int i = 0; i < links.size(); i++) {
                LinkData link = links.get(i);
                if (code.equals(link.getArticle2())) {
                    if (flag == 1) {
                        String str = link.getStatus() + ",code deleted in " + version;
                        link.setStatus(str);
                        links.set(i, link);
                    } else if (flag == 2) {
                        String str = link.getStatus() + ",code added in " + version;
                        link.setStatus(str);
                        links.set(i, link);
                    }
                }
            }
        }
        LinkListExporter.exportVersionLinkList(links);

    }

    public static void searchDif() {
        ArrayList<String> beforeCode = DataImporter.load(beforeFile);
        ArrayList<String> afterCode = DataImporter.load(afterFile);

        ArrayList<String> output = new ArrayList<String>();
        ArrayList<String> delCode = new ArrayList<String>();

        for (String code1 : beforeCode) {
            boolean flag = true;
            for (int i = 0; i < afterCode.size(); i++) {
                String code2 = afterCode.get(i);
                if (code1.equals(code2)) {
                    afterCode.remove(i);
                    flag = false;
                    break;
                }
            }
            if (flag)
                delCode.add(code1);
        }

        output.add("deleted code");
        output.addAll(delCode);
        output.add("\r\nadd code");
        output.addAll(afterCode);

        DataExporter.export(output, difFile);
    }
}
