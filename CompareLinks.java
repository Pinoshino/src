import data.LinkData;
import fileIO.LinkFileImporter;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/08/09.
 */
public class CompareLinks {
    static String file1 = "data/import/CLM.csv";
    static String file2 = "data/import/VSM.csv";

    public static void main(String[] args) {
        int num=0;
        ArrayList<LinkData> linkList1 = new LinkFileImporter().importLinkFile(file1);
        ArrayList<LinkData> linkList2 = new LinkFileImporter().importLinkFile(file2);

        for(LinkData link: linkList1){
            String[] str = link.getArticle2().split("/",0);
            String code = str[str.length-1];
            String ID = link.getArticle1();

            for (LinkData link2: linkList2){
                String[] str2 = link2.getArticle2().split("/",0);
                String code2 = str2[str2.length-1];
                String ID2 = link2.getArticle1();
                if(code.equals(code2)){
                    if(ID.equals(ID2)) {
                        num++;
                        break;
                    }
                }
            }
        }

        System.out.println(num);
    }
}
