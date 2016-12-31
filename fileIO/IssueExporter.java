package fileIO;

import data.IssueData;
import param.Param;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kazuki on 2016/10/10.
 */
public class IssueExporter extends DataExporter {
    static String fileName = Param.issueListFile;


    public static void exportIssueList(Map<String, IssueData> issueList) {
        String text = "id,pullRequestTitle,pullRequestBody,commit,code";
        ArrayList<String> outputList = new ArrayList<String>();
        outputList.add(text);

        for (IssueData link : issueList.values()) {
            outputList.add(link.getID() + "," + link.getPullRequestTitle() + "," + link.getPullRequestBody() + ","
                    + link.getCommitList() + "," + link.getCodeList());
        }
        export(outputList, fileName);
    }


    public static void exportIssueFile(Map<String, IssueData> issueList, int frag) {
        String file = "app/issue_full/";

        File folder = new File("app/issue_full");
        if (!folder.exists())
            folder.mkdir();


        ArrayList<String> str = new ArrayList<String>();
        for (IssueData issue : issueList.values()) {
            str.clear();
            str.add(issue.getPullRequestTitle() + " " + issue.getPullRequestBody() + " ");

            if (frag == 1) { //プルリクエストごと
                for (String commit : issue.getCommits().values())
                    str.add(commit + " ");
                DataExporter.export(str, file + issue.getID());

            } else if (frag == 2) { //コミットごと
                for (String commit : issue.getCommits().values()) {
                    str.add(commit);
                    DataExporter.export(str, file + issue.getID());
                    str.clear();
                    str.add(issue.getPullRequestTitle() + " " + issue.getPullRequestBody() + " ");
                }
            }
        }
    }
}
