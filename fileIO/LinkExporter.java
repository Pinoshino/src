package fileIO;

import data.LinkData;
import param.Param;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/06/21.
 */
public class LinkExporter extends DataExporter {
    static String fileName = Param.resultLinkFile;

    public static void exportLinkList(ArrayList<LinkData> linkList) {
        String text = "requirement,code,score";
        ArrayList<String> list = new ArrayList<String>();
        list.add(text);

        for (LinkData link : linkList) {
            list.add(link.getLink());
        }
        export(list, fileName);
    }


    public static void exportConnectLink(ArrayList<LinkData> connectList) {
        String fileName = Param.connectLinkListFile;
        String text = "requirement,issue,code,score,addition,total Addition,ratio,calced score";
        ArrayList<String> list = new ArrayList<String>();
        list.add(text);

        for (LinkData link : connectList) {
            list.add(link.getFullLink());
        }
        export(list, fileName);
    }


    public static void exportVersionLinkList(ArrayList<LinkData> linkList) {
        String text = "requirement,code,score,status";
        ArrayList<String> list = new ArrayList<String>();
        list.add(text);

        for (LinkData link : linkList) {
            list.add(link.getLink() + "," + link.getStatus());
        }
        export(list, fileName);
    }

    public static void exportAnswerLinkList(ArrayList<LinkData> linkList) {
        String text = "requirement,code,score,answer";
        ArrayList<String> list = new ArrayList<String>();
        list.add(text);

        for (LinkData link : linkList) {
            list.add(link.getLink() + "," + link.getAnswer());
        }
        export(list, fileName);
    }
}
