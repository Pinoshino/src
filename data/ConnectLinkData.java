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
    int sumAddition;
    double sumScore;
    double ratio;

    public ConnectLinkData(String function, String issue, String code, double score, int addition, int sumAddition, double ratio, double sumScore) {
        this.function = function;
        this.issue = issue;
        this.code = code;
        this.score = score;
        this.addition = addition;
        this.sumAddition = sumAddition;
        this.ratio = ratio;
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

    public int getSumAddition() {
        return sumAddition;
    }

    public double getRatio() {
        return ratio;
    }

    public double getScore() {
        return score;
    }

    public int getAddition() {
        return addition;
    }

    public double getSumScore() {
        return sumScore;
    }
}
