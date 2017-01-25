package data;

/**
 * Created by kazuki on 2016/06/20.
 */
public class LinkData implements Data {
    String function;
    String issue;
    String code;

    double score;
    String status = "";
    String answer;

    int addition;
    int totalAddition;
    double ratio;

    public LinkData(String function, String issue, String code, double score, int addition, int totalAddition, double ratio) {
        this.function = function;
        this.issue = issue;
        this.code = code;
        this.score = score;
        this.addition = addition;
        this.totalAddition = totalAddition;
        this.ratio = ratio;
    }

    public LinkData(String function, String issue, String code, double score) {
        this.function = function;
        this.issue = issue;
        this.code = code;
        this.score = score;
    }

    public LinkData(String function, String issue, String code, double score, String status) {
        this.function = function;
        this.issue = issue;
        this.code = code;
        this.status = status;
        this.score = score;
    }

    public void setStatus(String status) {
        if (this.status.isEmpty())
            this.status = status;
        else
            this.status += " ;" + status;
    }

    public String getLink() {
        return function + "," + code + "," + score;
    }

    public String getFullLink() {
        return function + "," + issue + "," + code + "," + score + "," +
                addition + "," + totalAddition + "," + ratio + "," + getCulcScore();
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
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

    public double getCulcScore() {
        return score * ratio;
    }
}
