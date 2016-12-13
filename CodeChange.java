import data.LinkData;
import fileIO.CodeIDImporter;
import fileIO.LinkFileImporter;
import fileIO.LinkListExporter;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kazuki on 2016/08/09.
 */
public class CodeChange {
    public static void main(String[] args) {
        Map<String, String> codeList = CodeIDImporter.importCodeID();
        ArrayList<LinkData> linkList = LinkFileImporter.importLinkFile();

        ArrayList<LinkData> outputLinkList = new ArrayList<LinkData>();

        for(LinkData link: linkList){
            if(codeList.containsKey(link.getArticle2())){
                link.setArticle2(codeList.get(link.getArticle2()));
                outputLinkList.add(link);
            }
        }
        LinkListExporter.exportLinkList(outputLinkList);
    }
}
