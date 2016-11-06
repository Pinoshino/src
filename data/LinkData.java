package data;

/**
 * Created by kazuki on 2016/06/20.
 */
public class LinkData implements Data{
    String arti1;
    String arti2;
    double addScore;
    double delScore;
    double sumScore;

    public LinkData(String arti1, String arti2, double sumScore) {
        this.arti1 = arti1;
        this.arti2 = arti2;
        this.sumScore = sumScore;
    }

    public LinkData(String arti1, String arti2, double addScore, double delScore, double sumScore) {
        this.arti1 = arti1;
        this.arti2 = arti2;
        this.addScore = addScore;
        this.delScore = delScore;
        this.sumScore = sumScore;
    }

    public String getArti1() {
        return arti1;
    }

    public void setArti1(String arti1) {
        this.arti1 = arti1;
    }

    public void setArti2(String arti2) {
        this.arti2 = arti2;
    }

    public String getArti2() {
        return arti2;
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
