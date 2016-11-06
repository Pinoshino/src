package param;

/**
 * Created by kazuki on 2016/06/20.
 */
public class Param {
    //入力データ

    public static final String codeListFile = "data/import/codeList.xml";

//リンクの結合
    public static final String issueDataFile = "data/import/issueData.txt";
    public static final String issue2DataFile = "data/import/issue.csv";
    public static final String linkListFile = "data/import/sim.csv";

    //traceLab用実データ
    public static String inputCodeFolder = "app/code";
    public static String inputIssueFolder = "app/issue";
    public static String inputFunctionFolder = "app/function";
    //    TraceLab用フォーマットファイル
    public static String textFile = "data/template/text.txt";

    //出力データ
//リンクの結合結果
    public static final String issueListFile = "data/export/issueList.csv";
    public static final String sumLinkFile = "data/export/sumSameLink.csv";
    public static final String connectalinkListFile = "data/export/connectLinkList.csv";

    //TraceLab用ファイル
    public static String codeFile = "data/export/codefile.xml";
    public static String issueFile = "data/export/issuefile.xml";
    public static String functionFile = "data/export/functionfile.xml";

}
