package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kazuki on 2016/06/20.
 */
public class IssueData implements Data {

//    class Code{
//        String code;
//        int changeNum;
//
//        Code(String code, int changeNum) {
//            this.code = code;
//            this.changeNum = changeNum;
//        }
//    }
//    static Map<String, ArrayList<data.IssueData>> issueList = new HashMap<String, ArrayList<data.IssueData>>();

    String ID;
    String pullRequest;
    Map<String,String> comments = new HashMap<String, String>();
    ArrayList<Code> codes = new ArrayList<Code>();
    int sumChangeNum =0;
    int sumChangeAddNum =0;

    public String getID() {
        return ID;
    }

    public ArrayList<Code> getCodes() {
        return codes;
    }

    public int getSumChangeNum() {
        return sumChangeNum;
    }

    public int getSumChangeAddNum() {
        return sumChangeAddNum;
    }

    public String getPullRequest() {
        return pullRequest;
    }

    public Map<String, String> getComments() {
        return comments;
    }

    public IssueData(String ID, String pullRequest, String comment, String code, int changeNum,int changeAddNum) {
        this.ID = ID;
        this.pullRequest = pullRequest;
        setComment(comment);
        setCode(code, changeNum,changeAddNum);
    }

    public void setComment(String comment){
        if(!comments.containsKey(comment)){
            comments.put(comment,comment);
        }
    }

    public void setCode(String code, int changeNum, int changeAddNum){
        codes.add(new Code(code, changeNum,changeAddNum));
        sumChangeNum+=changeNum;
        sumChangeAddNum+=changeAddNum;
    }

}

