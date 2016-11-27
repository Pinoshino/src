package format;

import param.Param;

import java.io.File;

/**
 * Created by kazuki on 2016/06/22.
 */
public class CodeListMaker extends FormatMaker {
    static String codeFile = Param.codeFile;
    static String inputFolder = Param.inputCodeFolder;

    static String ID = "CF";
    static String name = "Code file";
    static String description = "this is code file";
    static String IDName = "";

    void initialize() {
        setFormatFile(codeFile);
        setInputFolder(inputFolder);
        setID(ID);
        setName(name);
        setDescription(description);
        setIDName(IDName);
    }

    public void writeCodeName(File file) {
        String filename = file.toString().replace("\\","/");
        String str = "<artifact><id>" + filename + "</id><content>" + file + "</content><parent_id/></artifact>" + "\r\n";
        articleList.add(str);
    }
}
