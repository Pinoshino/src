import data.*;
import fileIO.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by kazuki on 2016/05/28.
 */
public class LinkConnect {

    public static void main(String[] args) {
        //        pullResuest単位なら 1; commit単位なら 2
        int flag = 2;

//        issueの読み込み&書き出し
        Map<String, IssueData> issueList = new IssueFileImporter().importIssueFile(flag);
        IssueFileExporter.exportIssueList(issueList);

//        リンクの結合
        ArrayList<LinkData> linkList = new LinkFileImporter().importLinkFile();
        ArrayList<ConnectLinkData> connectLink = connectLink(linkList, issueList);
        ConnectLinkExporter.exportConnectLink(connectLink);

//        重複リンクの統合
        linkList=sumSameLink(new ConnectLinkFileImporter().importConnectLinkFile());
        LinkListExporter.exportLinkList(linkList);
    }

    static ArrayList<LinkData> sumSameLink(ArrayList<ConnectLinkData> connectLink) {
        Map<String, Map<String, LinkData>> hashlist = new HashMap<String, Map<String, LinkData>>();

        for (ConnectLinkData list : connectLink) {
            double add = list.getAddScore();
            double del = list.getDelScore();
            double sum = list.getSumScore();
            String req = list.getFunction();
            String code = list.getCode();

            if (hashlist.containsKey(req) && hashlist.get(req).containsKey(code)) {
                LinkData link = hashlist.get(req).get(code);
                add += link.getAddScore();
                del += link.getDelScore();
                sum += link.getSumScore();
                hashlist.get(req).put(code, new LinkData(req, code, add, del, sum));
            } else {
                if (!hashlist.containsKey(req))
                    hashlist.put(req, new HashMap<String, LinkData>());
                hashlist.get(req).put(code, new LinkData(req, code, add, del, sum));
            }
        }

        ArrayList<LinkData> linkList = new ArrayList<LinkData>();
        for (String key1 : hashlist.keySet()) {
            Map<String, LinkData> temp = new HashMap<String, LinkData>(hashlist.get(key1));
            for (String key2 : temp.keySet()) {
                LinkData link = temp.get(key2);
                linkList.add(new LinkData(link.getArti1(), link.getArti2(), link.getAddScore(), link.getDelScore(), link.getSumScore()));
            }
        }
        return linkList;
    }


    static ArrayList<ConnectLinkData> connectLink(ArrayList<LinkData> linkList, Map<String, IssueData> issueList) {
        ArrayList<ConnectLinkData> connectLink = new ArrayList<ConnectLinkData>();

        for (LinkData link1 : linkList) {
            String functionID = link1.getArti1();
            String issueID = link1.getArti2();
            Double score = link1.getSumScore();

            if (issueList.containsKey(issueID)) {
                IssueData list = issueList.get(issueID);

                for (Code code : list.getCodes()) {
                    double percent;

//                    変更行数の割合でスコアの重み付け
                    if(list.getSumAddNum()!=0)
                        percent = ((double) code.getAddNum() / list.getSumAddNum());
                    else
                        percent = 0;
                    double sumScore = score * percent;
                            connectLink.add(new ConnectLinkData(functionID, issueID, code.getCode(), score, code.getAddNum(), list.getSumAddNum(), percent, 0, sumScore));
                }
            }
        }
        return connectLink;
    }


}
