import data.LinkData;
import fileIO.CodeIDImporter;
import fileIO.DataImporter;
import fileIO.LinkFileImporter;
import fileIO.LinkListExporter;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kazuki on 2016/08/09.
 */
public class CodeChange {
    public static void main(String[] args) {
        Map<String, String> codeList = new CodeIDImporter().importCodeID();
        ArrayList<LinkData> linkList = new LinkFileImporter().importLinkFile();

        ArrayList<LinkData> linkList2 = new ArrayList<LinkData>();

        for(LinkData link: linkList){
            if(codeList.containsKey(link.getArti2())){
                link.setArti2(codeList.get(link.getArti2()));
                linkList2.add(link);
            }
        }
        LinkListExporter.exportLinkList(linkList2);
    }
}
