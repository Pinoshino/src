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
        sumChangeAddNum+=changeAddNum;
    }

}

