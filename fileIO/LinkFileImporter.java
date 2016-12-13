package fileIO;

import data.LinkData;
import param.Param;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/06/21.
 */
public class LinkFileImporter extends DataImporter {
    static String fileName = Param.linkListFile;

    public static ArrayList<LinkData> importLinkFile() {
        ArrayList<String[]> list = loadList(fileName);
        ArrayList<LinkData> outputLink = new ArrayList<LinkData>();

        list.remove(0);
        for (String[] lines : list) {
            outputLink.add(new LinkData(lines[0], lines[1], Double.parseDouble(lines[2])));
        }
        return outputLink;
    }

    public static ArrayList<LinkData> importLinkFile(String file) {
        ArrayList<String[]> list = loadList(file);
        ArrayList<LinkData> linkList = new ArrayList<LinkData>();

        list.remove(0);
        for (String[] lines : list) {
            linkList.add(new LinkData(lines[0], lines[1], Double.parseDouble(lines[2])));
        }
        return linkList;
    }

    public static ArrayList<LinkData> importLink_ver(String file) {
        ArrayList<String[]> list = loadList(file);
        ArrayList<LinkData> linkList = new ArrayList<LinkData>();

        list.remove(0);
        for (String[] lines : list) {
            if (lines.length == 3)
                linkList.add(new LinkData(lines[0], lines[1], Double.parseDouble(lines[2])));
            else if (lines.length == 4)
                linkList.add(new LinkData(lines[0], lines[1], Double.parseDouble(lines[2]), lines[3]));
        }
        return linkList;
    }
}
