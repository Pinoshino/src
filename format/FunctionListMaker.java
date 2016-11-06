package format;

import param.Param;

/**
 * Created by kazuki on 2016/06/22.
 */
public class FunctionListMaker extends FormatMaker {
    static String functionFile = Param.functionFile;
    static String inputFolder = Param.inputFunctionFolder;

    static String ID = "FF";
    static String name = "function file";
    static String description = "this is function file";
    static String IDName = "ff";

    void initialize() {
        setFormatFile(functionFile);
        setInputFolder(inputFolder);
        setID(ID);
        setName(name);
        setDescription(description);
        setIDName(IDName);
    }
}
