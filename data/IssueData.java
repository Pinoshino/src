package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kazuki on 2016/06/20.
 */
public class IssueData implements Data {

    String ID;
    String pullRequest;
    Map<String,String> commits = new HashMap<String, String>();
    ArrayList<Code> codes = new ArrayList<Code>();
    int sumChangeNum =0;
    int sumAddNum =0;

    public String getID() {
        return ID;
    }

    public ArrayList<Code> getCodes() {
        return codes;
    }

    public int getSumChangeNum() {
        return sumChangeNum;
    }

    public int getSumAddNum() {
        return sumAddNum;
    }

    public String getPullRequest() {
        return pullRequest;
    }

    public Map<String, String> getCommits() {
        return commits;
    }

    public IssueData(String ID, String pullRequest, String commit, String code, int changeNum,int changeAddNum) {
        this.ID = ID;
        this.pullRequest = pullRequest;
        setCommit(commit);
        setCode(code, changeNum,changeAddNum);
    }

    public void setCommit(String commit){
        if(!commits.containsKey(commit)){
            commits.put(commit,commit);
        }
    }

    public void setCode(String code, int changeNum, int changeAddNum){
        codes.add(new Code(code, changeNum,changeAddNum));
        sumChangeNum+=changeNum;
        sumAddNum +=changeAddNum;
    }

    public String getCommitList(){
        Map<String, String> commitList = getCommits();
        String str="";
        for(String commit: commitList.values())
            str +=commit+" ";
        return str;
    }

    public String getCodeList(){
        ArrayList<Code> codeList = getCodes();
        String str="";
        for(Code code: codeList)
            str +=code.getCode()+" ";
        return str;
    }

}

