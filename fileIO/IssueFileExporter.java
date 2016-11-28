package fileIO;

import data.Code;
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
    static String text = "ID,pull request,commit,code\r\n";

    public static void exportIssueList(Map<String, IssueData> issueList){
        ArrayList<String> list = new ArrayList<String>();
        list.add(text);

        for (IssueData link : issueList.values()) {
            list.add(link.getID() + "," + link.getPullRequest() + "," + link.getCommits() + "," +
                    getCodeList(link.getCodes())+"\r\n");
        }
        export(list, fileName);
    }

   static String getCodeList(ArrayList<Code> codeList){
       String str="";
        for (int i=0;i<codeList.size(); i++){
            Code code = codeList.get(i);
            str +=code.getCode()+",";
        }
       return str;
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
