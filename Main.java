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
//        pullResuest単位なら 1; commit単位なら 2
        int flag = 1;

        Map<String, IssueData> issueList = new IssueFileImporter().importIssueFile(flag);
        //        プルリクエストごとのissue作成
//        new Issue2ListMaker().pullRequestIssueFileExport(issueList);

//        コミットごとのissue作成
        new IssueFileExporter().exportIssueFile(issueList, flag);
    }

    static void traceLabFormatMaker(){

//        TraceLab用のファイル作成
        new IssueListMaker().makeFormat();
        new CodeListMaker().makeFormat();
        new FunctionListMaker().makeFormat();
    }
}
