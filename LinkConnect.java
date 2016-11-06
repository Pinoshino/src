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
        int commit=2;

//        Map<String, Issue2Data> issueList = new Issue2FileImporter().importIssueFile();
        Map<String, IssueData> issueList = new IssueFileImporter().importIssueFile(commit);
        IssueFileExporter.exportIssueList(issueList);


        ArrayList<LinkData> linkList = new LinkFileImporter().importLinkFile();
        ArrayList<ConnectLinkData> connectLink = connectLink2(linkList, issueList);
        ConnectLinkExporter.exportConnectLink(connectLink);

        linkList=sumSameLink(new ConnectLinkFileImporter().importConnectLinkFile());
        LinkListExporter.exportLinkList(linkList);
    }

    static ArrayList<LinkData> sumSameLink(ArrayList<ConnectLinkData> connectLink) {
        Map<String, Map<String, LinkData>> hashlist = new HashMap<String, Map<String, LinkData>>();

        for (ConnectLinkData list : connectLink) {
            double add = list.getAddScore();
            double del = list.getDelScore();
            double sum = list.getSumScore();
            String req = list.getReq();
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

//    static ArrayList<ConnectLinkData> connectLink(ArrayList<LinkData> linkList, Map<String, ArrayList<IssueData>> issueList) {
//        ArrayList<ConnectLinkData> connectLink = new ArrayList<ConnectLinkData>();
//
//        for (LinkData link1 : linkList) {
//            String req = link1.getArti1();
//            String issue = link1.getArti2();
//            Double score = link1.getSumScore();
//
//            ArrayList<IssueData> list = null;
//            if (issueList.containsKey(issue))
//                list = issueList.get(issue);
//            else continue;
//
//            for (IssueData link : list) {
//                if (link == null) break;
//
//                double addScore = score * link.getRateAddNum();
//                double delScore = score * link.getRateDelNum();
//                double sumScore = score * link.getRateNum();
//                connectLink.add(new ConnectLinkData(req, issue, link.getCode(), score, link.getAddNum(), link.getDelNum(), addScore, delScore, sumScore));
//            }
//        }
//        return connectLink;
//    }

    static ArrayList<ConnectLinkData> connectLink2(ArrayList<LinkData> linkList, Map<String, IssueData> issueList) {
        ArrayList<ConnectLinkData> connectLink = new ArrayList<ConnectLinkData>();

        for (LinkData link1 : linkList) {
            String req = link1.getArti1();
            String issue = link1.getArti2();
            Double score = link1.getSumScore();

            if (issueList.containsKey(issue)) {
                IssueData list = issueList.get(issue);

                for (Code code : list.getCodes()) {
                    double sumScore = score * ((double) code.getChangeAddNum() / list.getSumChangeAddNum());
                    connectLink.add(new ConnectLinkData(req, issue, code.getCode(), score, code.getChangeAddNum(), list.getSumChangeAddNum(), ((double) code.getChangeAddNum() / list.getSumChangeAddNum()), 0, sumScore));
                }
            }
        }
        return connectLink;
    }


}
