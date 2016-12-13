package data;

/**
 * Created by kazuki on 2016/06/20.
 */
public class LinkData implements Data {
    String article1;
    String article2;
    double sumScore;
    String status;

    public LinkData(String article1, String article2, double sumScore) {
        this.article1 = article1;
        this.article2 = article2;
        this.sumScore = sumScore;
    }

    public LinkData(String article1, String article2, double sumScore, String status) {
        this.article1 = article1;
        this.article2 = article2;
        this.sumScore = sumScore;
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArticle1() {
        return article1;
    }

    public String getArticle2() {
        return article2;
    }

    public void setArticle1(String article1) {
        this.article1 = article1;
    }

    public void setArticle2(String article2) {
        this.article2 = article2;
    }

    public double getSumScore() {
        return sumScore;
    }
}
