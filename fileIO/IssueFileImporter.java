package fileIO;

import data.IssueData;
import param.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kazuki on 2016/08/08.
 */
public class IssueFileImporter extends DataImporter{
    static String fileName = Param.issue2DataFile;
//    static String fileName = Param.issueListFile;



//    IDがプルリクエストごと
        public  Map<String, IssueData> importIssueFile(int flag) {
            ArrayList<String[]> list = loadList(fileName);
            Map<String, IssueData> issue = new HashMap<String, IssueData>();

            String pullRequest = "";
//            String ID = "app\\issue\\";
            String ID = "issue";
            int IDNum=0;
            IssueData issue2 = null;
            for (String[] lines : list) {
                if(flag == 1) {//    IDがプルリクエストごと
                    if (pullRequest.equals(lines[0])) {
                        issue2.setComment(lines[1]);
                        issue2.setCode(lines[2], Integer.parseInt(lines[5]),Integer.parseInt(lines[3]));
                    } else {
                        if (!pullRequest.equals(""))
                            issue.put(ID + IDNum+".txt", issue2);
                        IDNum++;
                        pullRequest = lines[0];
                        issue2 = new IssueData(ID + IDNum+".txt", lines[0], lines[1], lines[2], Integer.parseInt(lines[5]),Integer.parseInt(lines[3]));
                    }
                }else if(flag==2){//    IDがコミットごと
                    if (pullRequest.equals(lines[0])){
                        if(!issue2.getComments().containsKey(lines[1])) {
                            issue.put(ID+IDNum+".txt",issue2);
                            IDNum++;
                            issue2 = new IssueData(ID+IDNum+".txt",lines[0],lines[1],lines[2],Integer.parseInt(lines[5]),Integer.parseInt(lines[3]));
                        }else {
                            issue2.setCode(lines[2], Integer.parseInt(lines[5]),Integer.parseInt(lines[3]));
                        }
                    } else{
                        if (!pullRequest.equals(""))
                            issue.put(ID+IDNum+".txt",issue2);
                        IDNum++;
                        pullRequest = lines[0];
                        issue2 = new IssueData(ID+IDNum+".txt",lines[0],lines[1],lines[2],Integer.parseInt(lines[5]),Integer.parseInt(lines[3]));
                    }
                }
            }
            issue.put(ID+IDNum+".txt",issue2);
            return issue;
        }



//    IDがコミットごと
    public  Map<String, IssueData> importIssueFile2() {
        ArrayList<String[]> list = loadList(fileName);
        Map<String, IssueData> issue = new HashMap<String, IssueData>();

        String pullRequest = "";
        String ID = "if";
        int IDNum=0;
        IssueData issue2 = null;
        for (String[] lines : list) {
            if (pullRequest.equals(lines[0])){
                if(!issue2.getComments().containsKey(lines[1])) {
                    issue.put(ID+IDNum,issue2);
                    IDNum++;
                    issue2 = new IssueData(ID+IDNum,lines[0],lines[1],lines[2],Integer.parseInt(lines[5]),Integer.parseInt(lines[3]));
                }else {
                    issue2.setCode(lines[2], Integer.parseInt(lines[5]),Integer.parseInt(lines[3]));
                }
            } else{
                if (!pullRequest.equals(""))
                    issue.put(ID+IDNum,issue2);
                IDNum++;
                pullRequest = lines[0];
                issue2 = new IssueData(ID+IDNum,lines[0],lines[1],lines[2],Integer.parseInt(lines[5]),Integer.parseInt(lines[3]));
            }
        }
        issue.put(ID+IDNum,issue2);
        return issue;
    }

}