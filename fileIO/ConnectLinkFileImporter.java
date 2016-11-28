package fileIO;

import data.ConnectLinkData;
import param.Param;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/06/21.
 */
public class ConnectLinkFileImporter extends DataImporter {
    static String fileName = Param.connectLinkListFile;

    public ArrayList<ConnectLinkData> importConnectLinkFile() {
        ArrayList<String[]> list = loadList(fileName);
        ArrayList<ConnectLinkData> link = new ArrayList<ConnectLinkData>();

        list.remove(0);
        for (String[] lines : list) {
            link.add(new ConnectLinkData(lines[0], lines[1], lines[3], Double.parseDouble(lines[2]), Integer.parseInt(lines[4]),
                    Integer.parseInt(lines[6]), Double.parseDouble(lines[5]), Double.parseDouble(lines[7]), Double.parseDouble(lines[8])));
        }
        return link;
    }
}
