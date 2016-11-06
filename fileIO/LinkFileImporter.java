package fileIO;

import data.LinkData;
import param.Param;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/06/21.
 */
public class LinkFileImporter extends DataImporter {
    static String fileName = Param.linkListFile;

    public ArrayList<LinkData> importLinkFile() {
        ArrayList<String[]> list = loadList(fileName);
        ArrayList<LinkData> linkList = new ArrayList<LinkData>();

        list.remove(0);
        for (String[] lines : list) {
            linkList.add(new LinkData(lines[0], lines[1], Double.parseDouble(lines[2])));
        }
        return linkList;
    }

    public ArrayList<LinkData> importLinkFile(String file) {
        ArrayList<String[]> list = loadList(file);
        ArrayList<LinkData> linkList = new ArrayList<LinkData>();

        list.remove(0);
        for (String[] lines : list) {
            linkList.add(new LinkData(lines[0], lines[1], Double.parseDouble(lines[2])));
        }
        return linkList;
    }
}
