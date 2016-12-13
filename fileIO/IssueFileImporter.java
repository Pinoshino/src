package fileIO;

import data.IssueData;
import param.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kazuki on 2016/08/08.
 */
public class IssueFileImporter extends DataImporter {
    static String fileName = Param.issueDataFile;

    public static Map<String, IssueData> importIssueFile(int flag) {
        ArrayList<String[]> list = loadList(fileName);
        Map<String, IssueData> outputIssue = new HashMap<String, IssueData>();

        String pullRequest = "";
        String ID = "issue";
        int IDNum = 0;
        IssueData issue = null;
        list.remove(0);

        for (String[] lines : list) {
            String pullRequestTitle = lines[0];
            String pullRequestBody = lines[1];
            String commitText = lines[2];
            String codePath = lines[3];
            int addNum = Integer.parseInt(lines[4]);

            if (flag == 1) {//    IDがプルリクエストごと
                if (pullRequest.equals(pullRequestTitle)) {
                    issue.setCommit(commitText);
                    issue.setCode(codePath, addNum);
                } else {
                    if (!pullRequest.equals(""))
                        outputIssue.put(ID + IDNum + ".txt", issue);
                    IDNum++;
                    pullRequest = pullRequestTitle;
                    issue = new IssueData(ID + IDNum + ".txt", pullRequestTitle, pullRequestBody, commitText, codePath, addNum);
                }

            } else if (flag == 2) {//    IDがコミットごと
                if (pullRequest.equals(pullRequestTitle)) {
                    if (!issue.getCommits().containsKey(commitText)) {
                        outputIssue.put(ID + IDNum + ".txt", issue);
                        IDNum++;
                        issue = new IssueData(ID + IDNum + ".txt", pullRequestTitle, pullRequestBody, commitText, codePath, addNum);
                    } else {
                        issue.setCode(codePath, addNum);
                    }
                } else {
                    if (!pullRequest.equals(""))
                        outputIssue.put(ID + IDNum + ".txt", issue);
                    IDNum++;
                    pullRequest = pullRequestTitle;
                    issue = new IssueData(ID + IDNum + ".txt", pullRequestTitle, pullRequestBody, commitText, codePath, addNum);
                }
            }
        }
        outputIssue.put(ID + IDNum + ".txt", issue);
        return outputIssue;
    }


}