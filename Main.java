import data.IssueData;
import fileIO.IssueFileExporter;
import fileIO.IssueFileImporter;
import format.*;
;import java.util.Map;

/**
 * Created by kazuki on 2016/06/20.
 */
public class Main {
    public static void main(String[] args) {
        issueFormater();
        traceLabFormatMaker();

    }
    static void issueFormater(){
        int pullResuest = 1;
        int commit = 2;

        Map<String, IssueData> issueList = new IssueFileImporter().importIssueFile(pullResuest);
        //        プルリクエストごとのissue作成
//        new Issue2ListMaker().pullRequestIssueFileExport(issueList);

//        コミットごとのissue作成
        new IssueFileExporter().exportIssueFile(issueList, pullResuest);
    }

    static void traceLabFormatMaker(){

//        TraceLab用のファイル作成
        new IssueListMaker().makeFormat();
        new CodeListMaker().makeFormat();
        new FunctionListMaker().makeFormat();
    }
}
