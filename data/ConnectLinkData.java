package data;

/**
 * Created by kazuki on 2016/06/20.
 */
public class ConnectLinkData implements Data {
    String req;
    String issue;
    String code;
    double score;
    int addition;
    int deletion;
    double addScore;
    double delScore;
    double sumScore;

    public ConnectLinkData(String req, String issue, String code, double score, int addition, int deletion, double addScore, double delScore, double sumScore) {
        this.req = req;
        this.issue = issue;
        this.code = code;
        this.score = score;
        this.addition = addition;
        this.deletion = deletion;
        this.addScore = addScore;
        this.delScore = delScore;
        this.sumScore = sumScore;
    }

    public String getReq() {
        return req;
    }

    public String getIssue() {
        return issue;
    }

    public String getCode() {
        return code;
    }

    public double getScore() {
        return score;
    }

    public int getAddition() {
        return addition;
    }

    public int getDeletion() {
        return deletion;
    }

    public double getAddScore() {
        return addScore;
    }

    public double getDelScore() {
        return delScore;
    }

    public double getSumScore() {
        return sumScore;
    }
}
