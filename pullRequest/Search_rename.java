package pullRequest;

import java.util.ArrayList;

public class Search_rename {
  public static ArrayList<String> search(ArrayList<String> contents) {
    ArrayList<String> output = new ArrayList<String>();

    String beforeName;
    String afterName;

    for (String content : contents) {
      // 矢印を含んでいてかつコメント行ではなかったら
      if (content.contains(" → ") && !content.contains("*")) {
//        String[] temp = content.split(" → ");

        if(content.contains("{")){
          output.add(content+"\"");
        }
//      →が二つあれば
//        if (temp.length == 3) {
//          String[] hoge = temp[0].split(" ");
//          beforeName = hoge[hoge.length - 1];
//          afterName = temp[1].split(" ")[0];
//          output.add(beforeName + "," + afterName);
//        }
      }
    }
    return output;
  }
}