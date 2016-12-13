package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kazuki on 2016/06/20.
 */
public class IssueData implements Data {

    String ID;
    String pullRequestTitle;
    String pullRequestBody;
    Map<String, String> commits = new HashMap<String, String>();
    ArrayList<Code> codes = new ArrayList<Code>();
    int sumAddNum = 0;

    public String getID() {
        return ID;
    }

    public ArrayList<Code> getCodes() {
        return codes;
    }

    public String getPullRequestBody() {
        return pullRequestBody;
    }

    public int getSumAddNum() {
        return sumAddNum;
    }

    public String getPullRequestTitle() {
        return pullRequestTitle;
    }

    public Map<String, String> getCommits() {
        return commits;
    }

    public IssueData(String ID, String pullRequestTitle, String pullRequestBody, String commit, String code, int changeAddNum) {
        this.ID = ID;
        this.pullRequestTitle = pullRequestTitle;
        this.pullRequestBody = pullRequestBody;
        setCommit(commit);
        setCode(code, changeAddNum);
    }

    public void setCommit(String commit) {
        if (!commits.containsKey(commit)) {
            commits.put(commit, commit);
        }
    }

    public void setCode(String code, int changeAddNum) {
        codes.add(new Code(code, changeAddNum));
        sumAddNum += changeAddNum;
    }

    public String getCommitList() {
        Map<String, String> commitList = getCommits();
        String str = "\"";
        for (String commit : commitList.values())
            str += commit.replace("\"", "\"\"") + "\r\n";
        str += "\"";
        return str;
    }

    public String getCodeList() {
        ArrayList<Code> codeList = getCodes();
        String str = "\"";
        for (Code code : codeList)
            str += code.getCode() + "\r\n";
        str += "\"";
        return str;
    }

}

