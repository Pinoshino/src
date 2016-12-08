package fileIO;

import data.ConnectLinkData;
import param.Param;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/06/21.
 */
public class ConnectLinkExporter extends DataExporter{
    static String fileName = Param.connectLinkListFile;
    static String text = "requirement,issue,score,code name,addition num,addition ratio,total addition num, none ,total score";

    public static void exportConnectLink(ArrayList<ConnectLinkData> connectList) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(text);

        for (ConnectLinkData link : connectList) {
            list.add(link.getFunction() + "," + link.getIssue() + "," + link.getScore() + "," + link.getCode() + "," +
                    link.getAddition() + "," + link.getAddScore() + "," + link.getDeletion() + "," + link.getDelScore() + "," + link.getSumScore());
        }
        export(list, fileName);
    }
}
