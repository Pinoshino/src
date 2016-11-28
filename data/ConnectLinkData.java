package data;

/**
 * Created by kazuki on 2016/06/20.
 */
public class ConnectLinkData implements Data {
    String function;
    String issue;
    String code;
    double score;
    int addition;
    int deletion;
    double addScore;
    double delScore;
    double sumScore;

    public ConnectLinkData(String function, String issue, String code, double score, int addition, int deletion, double addScore, double delScore, double sumScore) {
        this.function = function;
        this.issue = issue;
        this.code = code;
        this.score = score;
        this.addition = addition;
        this.deletion = deletion;
        this.addScore = addScore;
        this.delScore = delScore;
        this.sumScore = sumScore;
    }

    public String getFunction() {
        return function;
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
