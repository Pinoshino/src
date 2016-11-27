package format;

import fileIO.DataExporter;
import fileIO.DataImporter;
import param.Param;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by kazuki on 2016/06/22.
 */
public class FormatMaker {
    static String textFile = Param.textFile;
    static String formatFile;
    static String inputFolder;

    static String ID;
    static String name;
    static String description;
    static String IDName;

    static ArrayList<String> articleList = new ArrayList<String>();
    int num = 1;

    void initialize() {
    }

    public void setInputFolder(String inputFolder) {
        this.inputFolder = inputFolder;
    }

    public void setIDName(String IDName) {
        this.IDName = IDName;
    }

    public void setFormatFile(String formatFile) {
        this.formatFile = formatFile;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void makeFormat() {
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> dataList = DataImporter.load(textFile);
        for (String line : dataList) {
            String text = "";

            if (line.equals("<id>")) {
                text = "\t<id>" + ID + "</id>" + "\r\n";
            } else if (line.equals("<name>")) {
                text = "\t<name>" + name + "</name>" + "\r\n";
            } else if (line.equals("<description>")) {
                text = "\t<description>" + description + "</description>" + "\r\n";
            } else if (line.equals("<artifacts>")) {
                text = "<artifacts>" + "\r\n";
                list.add(text);
                list.addAll(makeArticleList());
                text = "</artifacts>" + "\r\n";
            } else {
                text = line + "\r\n";
            }
            list.add(text);
        }
        DataExporter.export(list, formatFile);
        articleList.clear();
    }

    public void readFolder(File dir) {
        File[] files = dir.listFiles();
        if (files == null)
            return;
        for (File file : files) {
            if (!file.exists())
                continue;
            else if (file.isDirectory())
                readFolder(file);
            else if (file.isFile())
                writeCodeName(file);
        }
    }

    public void writeCodeName(File file) {
        String[] ID = file.toString().split("\\\\");
        String str = "<artifact><id>" + ID[ID.length-1] + "</id><content>" + file + "</content><parent_id/></artifact>" + "\r\n";
//        String str = "<artifact><id>," + IDName +num+ ",</id><content>" + file + "</content><parent_id/></artifact>" + "\r\n";
//        num++;
        articleList.add(str);

    }

    ArrayList<String> makeArticleList() {
        readFolder(new File(inputFolder));
        return articleList;
    }
}
