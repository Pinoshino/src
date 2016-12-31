import data.LinkData;
import fileIO.LinkImporter;
import fileIO.LinkExporter;

import java.util.ArrayList;


/**
 * Created by kazuki on 2016/12/28.
 */
public class AnswerCheck {
    static String answerFile = "data/import/answer.csv";
    static String linkFile = "data/import/sim.csv";
    public static void main(String[] args) {
        ArrayList<LinkData> answer = LinkImporter.importFun_CodeLink(answerFile);
        ArrayList<LinkData> linkList = LinkImporter.importFun_CodeLink(linkFile);


        for(LinkData ans: answer){
            String ansFunction = ans.getFunction();
            String ansCode = ans.getCode();

            for (LinkData link: linkList){
                String function = link.getFunction();
                String code = link.getCode();
                if(ansCode.equals(code)){
                    if(ansFunction.equals(function)) {
                        link.setAnswer("true");
                        break;
                    }
                }
            }
        }

        LinkExporter.exportAnswerLinkList(linkList);
    }
}
