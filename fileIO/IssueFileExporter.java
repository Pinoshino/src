package fileIO;

import data.IssueData;
import param.Param;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kazuki on 2016/10/10.
 */
public class IssueFileExporter extends DataExporter{
    static String fileName = Param.issueListFile;
    static String text = "id,pull request,commit,code\r\n";

    public static void exportIssueList(Map<String, IssueData> issueList){
        ArrayList<String> outputList = new ArrayList<String>();
        outputList.add(text);

        for (IssueData link : issueList.values()) {
            outputList.add(link.getID() + "," + link.getPullRequest() + "," + link.getCommitList()+ "," +
                    link.getCodeList()+"\r\n");
        }
        export(outputList, fileName);
    }


    public void exportIssueFile(Map<String, IssueData> issueList, int frag){
        String file="app/issue/";

        File folder = new File("app/issue");
        if(!folder.exists())
            folder.mkdir();


        ArrayList<String> str = new ArrayList<String>();
        for(IssueData issue: issueList.values()){
            str.clear();
            str.add(issue.getPullRequest()+" ");

            if(frag==1) { //プルリクエストごと
                for (String commit : issue.getCommits().values())
                    str.add(commit + " ");
                DataExporter.export(str, file + issue.getID());

            }else if(frag==2){ //コミットごと
                for(String commit: issue.getCommits().values()) {
                    str.add(commit);
                    DataExporter.export(str, file + issue.getID());
                    str.clear();
                    str.add(issue.getPullRequest()+" ");
                }
            }
        }
    }
}
