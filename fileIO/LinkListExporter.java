package fileIO;

import data.LinkData;
import param.Param;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/06/21.
 */
public class LinkListExporter extends DataExporter {
    static String fileName = Param.sumLinkFile;


    public static void exportLinkList(ArrayList<LinkData> linkList) {
        String text = "requirement,code,score";
        ArrayList<String> list = new ArrayList<String>();
        list.add(text);

        for (LinkData link : linkList) {
            list.add(link.getArticle1() + "," + link.getArticle2() + "," + link.getSumScore());
        }

        export(list, fileName);
    }

    public static void exportVersionLinkList(ArrayList<LinkData> linkList) {
        String text = "requirement,code,total score,status";
        ArrayList<String> list = new ArrayList<String>();
        list.add(text);

        for (LinkData link : linkList) {
            list.add(link.getArticle1() + "," + link.getArticle2() + "," + link.getSumScore() + "," + link.getStatus());
        }
        export(list, fileName);
    }
}
