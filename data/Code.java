package data;

/**
 * Created by kazuki on 2016/08/08.
 */
public class Code {
        String code;
        int changeNum;
        int changeAddNum;

        Code(String code, int changeNum, int changeAddNum) {
            this.code = code;
            this.changeNum = changeNum;
            this.changeAddNum = changeAddNum;
        }

    public String getCode() {
        return code;
    }
    public int getChangeNum() {
        return changeNum;
    }
    public int getChangeAddNum() {
        return changeAddNum;
    }
}
