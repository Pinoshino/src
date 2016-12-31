import data.LinkData;
import fileIO.ChangedCodeInfoImporter;
import fileIO.DataImporter;
import fileIO.LinkExporter;
import fileIO.LinkImporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kazuki on 2016/08/09.
 */
public class CodeChange {
    public static void main(String[] args) {
        changeRenameCode();
//        changeCodeVersion();

    }

    static void changeRenameCode() {
        Map<String, String> codeList = ChangedCodeInfoImporter.importCodeID();
        ArrayList<LinkData> linkList = LinkImporter.importFun_CodeLink();

        ArrayList<LinkData> outputLinkList = new ArrayList<LinkData>();

        for (LinkData link : linkList) {
            if (codeList.containsKey(link.getCode())) {
                link.setCode(codeList.get(link.getCode()));
                link.setStatus("code rename");
            }
            outputLinkList.add(link);
        }
        LinkExporter.exportVersionLinkList(outputLinkList);
    }

    static void changeCodeVersion() {
        String codeFileName = "data/import/codeVersionList.csv";
        String linkFileName = "data/import/sumSameLink.csv";
        Double threshold = 0.8;

        Map<String, String> codeVersionList = loadCodeList(codeFileName, threshold);
        ArrayList<LinkData> links = LinkImporter.importFun_CodeLink(linkFileName);
        ArrayList<LinkData> resultLink = new ArrayList<LinkData>();


        for (LinkData link : links) {
            String code = link.getCode();
            if (codeVersionList.containsKey(code)) {
                resultLink.add(new LinkData(link.getFunction(),"", codeVersionList.get(code), link.getScore()));
            } else {
                resultLink.add(link);
            }
        }
        LinkExporter.exportLinkList(resultLink);
    }

    static Map<String, String> loadCodeList(String codeFileName, double threshold) {
        Map<String, String> codeVersionList = new HashMap<String, String>();
        ArrayList<String> list = DataImporter.load(codeFileName);

        list.remove(0);
        for (String str : list) {
            String[] lines = str.split(",");

            if (Double.parseDouble(lines[2]) < threshold) break;

            if (!codeVersionList.containsKey(lines[0]))
                codeVersionList.put(lines[0], lines[1]);
        }
        return codeVersionList;
    }

}
