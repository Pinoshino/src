package data;

/**
 * Created by kazuki on 2016/08/08.
 */
public class Code {
        String code;
        int changeNum;
        int addNum;

        Code(String code, int changeNum, int addNum) {
            this.code = code;
            this.changeNum = changeNum;
            this.addNum = addNum;
        }

    public String getCode() {
        return code;
    }
    public int getChangeNum() {
        return changeNum;
    }
    public int getAddNum() { return addNum;}
}
