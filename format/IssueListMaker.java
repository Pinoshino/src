package format;

import param.Param;

/**
 * Created by kazuki on 2016/06/22.
 */
public class IssueListMaker extends FormatMaker {
    static String issueFile = Param.issueFile;
    static String inputFolder = Param.inputIssueFolder;

    static String ID = "IF";
    static String name = "Issue";
    static String description = "this is issue";
    static String IDName = "if";


    void initialize() {
        setFormatFile(issueFile);
        setInputFolder(inputFolder);
        setID(ID);
        setName(name);
        setDescription(description);
        setIDName(IDName);
    }
}
