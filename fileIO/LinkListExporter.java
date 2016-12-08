package fileIO;

import data.LinkData;
import param.Param;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/06/21.
 */
public class LinkListExporter extends DataExporter {
    static String fileName = Param.sumLinkFile;
    static String text = "requirement,code,addition score,deletion score,total score";

    public static void exportLinkList(ArrayList<LinkData> linkList) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(text);

        for (LinkData link : linkList) {
            list.add(link.getArti1() + "," + link.getArti2() + "," + link.getAddScore() + "," +
                    link.getDelScore() + "," + link.getSumScore());
        }

        export(list, fileName);
    }
}
