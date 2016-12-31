import data.LinkData;
import fileIO.LinkImporter;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/08/09.
 */
public class CompareLinks {
    static String file1 = "data/import/CLM.csv";
    static String file2 = "data/import/VSM.csv";

    public static void main(String[] args) {
        int num=0;
        ArrayList<LinkData> linkList1 = LinkImporter.importFun_CodeLink(file1);
        ArrayList<LinkData> linkList2 = LinkImporter.importFun_CodeLink(file2);

        for(LinkData link: linkList1){
            String[] str = link.getCode().split("/",0);
            String code = str[str.length-1];
            String function = link.getFunction();

            for (LinkData link2: linkList2){
                String[] str2 = link2.getCode().split("/",0);
                String code2 = str2[str2.length-1];
                String function2 = link2.getFunction();
                if(code.equals(code2)){
                    if(function.equals(function2)) {
                        num++;
                        break;
                    }
                }
            }
        }

        System.out.println(num);
    }
}
