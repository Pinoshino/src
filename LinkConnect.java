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
        Map<String, IssueData> issueList = IssueImporter.importIssueFile(flag);
        IssueExporter.exportIssueList(issueList);

//        リンクの結合
        ArrayList<LinkData> linkList = LinkImporter.importFun_IssueLink();
        ArrayList<LinkData> connectLink = connectLink(linkList, issueList);
        LinkExporter.exportConnectLink(connectLink);

//        重複リンクの統合
//        linkList=sumSameLink(new ConnectLinkFileImporter().importConnectLinkFile());
        linkList = sumSameLink(connectLink);
        LinkExporter.exportLinkList(linkList);
    }


    static ArrayList<LinkData> sumSameLink(ArrayList<LinkData> connectLink) {
        Map<String, Map<String, LinkData>> hashList = new HashMap<String, Map<String, LinkData>>();

        for (LinkData list : connectLink) {
            String req = list.getFunction();
            String code = list.getCode();
            double score = list.getCulcScore();

            if (hashList.containsKey(req) && hashList.get(req).containsKey(code)) {
                score += hashList.get(req).get(code).getScore();
                hashList.get(req).put(code, new LinkData(req,"",code,score));
            } else {
                if (!hashList.containsKey(req))
                    hashList.put(req, new HashMap<String, LinkData>());
                hashList.get(req).put(code, new LinkData(req,"",code,score));
            }
        }

        ArrayList<LinkData> linkList = new ArrayList<LinkData>();
        for (String key1 : hashList.keySet()) {
            Map<String, LinkData> temp = new HashMap<String, LinkData>(hashList.get(key1));
            for (String key2 : temp.keySet()) {
                LinkData link = temp.get(key2);
                linkList.add(link);
            }
        }
        return linkList;
    }


    static ArrayList<LinkData> connectLink(ArrayList<LinkData> linkList, Map<String, IssueData> issueList) {
        ArrayList<LinkData> connectLink = new ArrayList<LinkData>();

        for (LinkData link : linkList) {
            String functionID = link.getFunction();
            String issueID = link.getIssue();
            Double score = link.getScore();

            if (issueList.containsKey(issueID)) {
                IssueData list = issueList.get(issueID);

                for (Code code : list.getCodes()) {
                    double ratio;

//                    変更行数の割合でスコアの重み付け
                    if(list.getSumAddNum()!=0)
                        ratio = ((double) code.getAddNum() / list.getSumAddNum());
                    else
                        ratio = 0;
                            connectLink.add(new LinkData(functionID, issueID, code.getCode(), score, code.getAddNum(), list.getSumAddNum(), ratio));
                }
            }
        }
        return connectLink;
    }


}
