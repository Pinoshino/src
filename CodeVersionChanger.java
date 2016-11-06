import data.LinkData;
import fileIO.DataImporter;
import fileIO.LinkFileImporter;
import fileIO.LinkListExporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kazuki on 2016/10/17.
 */
public class CodeVersionChanger {
    static String codeFileName = "data/import/codeVersionList.csv";
    static String linkFileName =  "data/import/sumSameLink.csv";
    static Double threshold = 0.8;

    public static void main(String[] args) {
        Map<String,String> codeVersionList= loadCodeList(codeFileName);
        ArrayList<LinkData> link =  new LinkFileImporter().importLinkFile(linkFileName);

        ArrayList<LinkData> resultLink = changeCodeVersion(codeVersionList,link);

        LinkListExporter.exportLinkList(resultLink);
    }

    static  ArrayList<LinkData> changeCodeVersion(Map<String,String> codeVersionList, ArrayList<LinkData> links){
        ArrayList<LinkData> resultLink = new ArrayList<LinkData>();
        for (LinkData link:links){
            String code = link.getArti2();
            if(codeVersionList.containsKey(code)){
                resultLink.add(new LinkData(link.getArti1(),codeVersionList.get(code),link.getSumScore()));
            }else{
                resultLink.add(link);
            }
        }
        return resultLink;
    }

    static  Map<String,String> loadCodeList(String codeFileName) {
        Map<String,String> codeVersionList = new HashMap<String, String>();
        ArrayList<String> list = DataImporter.load(codeFileName);

        list.remove(0);
        for (String str:list){
            String[] strs = str.split(",");

            if(Double.parseDouble(strs[2]) < threshold) break;

            if(!codeVersionList.containsKey(strs[0]))
                codeVersionList.put(strs[0],strs[1]);
        }
        return codeVersionList;
    }
}
