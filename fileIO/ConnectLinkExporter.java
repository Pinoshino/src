package fileIO;

import data.ConnectLinkData;
import param.Param;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/06/21.
 */
public class ConnectLinkExporter extends DataExporter{
    static String fileName = Param.connectalinkListFile;
    static String text = "requirement,issue,score,code name,addition,addition score,deletion,deletion score,total score\r\n";

    public static void exportConnectLink(ArrayList<ConnectLinkData> connectList) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(text);

        for (ConnectLinkData link : connectList) {
            list.add(link.getReq() + "," + link.getIssue() + "," + link.getScore() + "," + link.getCode() + "," +
                    link.getAddition() + "," + link.getAddScore() + "," + link.getDeletion() + "," + link.getDelScore() + "," + link.getSumScore() + "\r\n");
        }
        export(list, fileName);
    }
}
