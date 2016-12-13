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
        Map<String, IssueData> issueList = IssueFileImporter.importIssueFile(flag);
        IssueFileExporter.exportIssueList(issueList);

//        リンクの結合
        ArrayList<LinkData> linkList = LinkFileImporter.importLinkFile();
        ArrayList<ConnectLinkData> connectLink = connectLink(linkList, issueList);
        ConnectLinkExporter.exportConnectLink(connectLink);

//        重複リンクの統合
//        linkList=sumSameLink(new ConnectLinkFileImporter().importConnectLinkFile());
        linkList = sumSameLink(connectLink);
        LinkListExporter.exportLinkList(linkList);
    }


    static ArrayList<LinkData> sumSameLink(ArrayList<ConnectLinkData> connectLink) {
        Map<String, Map<String, LinkData>> hashList = new HashMap<String, Map<String, LinkData>>();

        for (ConnectLinkData list : connectLink) {
            double sum = list.getSumScore();
            String req = list.getFunction();
            String code = list.getCode();

            if (hashList.containsKey(req) && hashList.get(req).containsKey(code)) {
                LinkData link = hashList.get(req).get(code);
                sum += link.getSumScore();
                hashList.get(req).put(code, new LinkData(req, code, sum));
            } else {
                if (!hashList.containsKey(req))
                    hashList.put(req, new HashMap<String, LinkData>());
                hashList.get(req).put(code, new LinkData(req, code,  sum));
            }
        }

        ArrayList<LinkData> linkList = new ArrayList<LinkData>();
        for (String key1 : hashList.keySet()) {
            Map<String, LinkData> temp = new HashMap<String, LinkData>(hashList.get(key1));
            for (String key2 : temp.keySet()) {
                LinkData link = temp.get(key2);
                linkList.add(new LinkData(link.getArticle1(), link.getArticle2(),  link.getSumScore()));
            }
        }
        return linkList;
    }


    static ArrayList<ConnectLinkData> connectLink(ArrayList<LinkData> linkList, Map<String, IssueData> issueList) {
        ArrayList<ConnectLinkData> connectLink = new ArrayList<ConnectLinkData>();

        for (LinkData link : linkList) {
            String functionID = link.getArticle1();
            String issueID = link.getArticle2();
            Double score = link.getSumScore();

            if (issueList.containsKey(issueID)) {
                IssueData list = issueList.get(issueID);

                for (Code code : list.getCodes()) {
                    double ratio;

//                    変更行数の割合でスコアの重み付け
                    if(list.getSumAddNum()!=0)
                        ratio = ((double) code.getAddNum() / list.getSumAddNum());
                    else
                        ratio = 0;
                    double sumScore = score * ratio;
                            connectLink.add(new ConnectLinkData(functionID, issueID, code.getCode(), score, code.getAddNum(), list.getSumAddNum(), ratio, sumScore));
                }
            }
        }
        return connectLink;
    }


}
