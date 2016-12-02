package pullRequest;

import net.arnx.jsonic.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Search_class {

int i,j;
int del_flag=0, new_flag=0,rename_flag=0;

String str = "中身となる文字列"; //個々に中身
String[] rows = str.split("\n", 0);

for (i=0; i<rows.length; i++) {
  if (rows[i].contains(" class ") && !rows[i].contains("*")) {
    if (rows[i].contains("-")) {
      del_flag=1;  //削除された可能性
    }

    else if (rows[i].contains("+")) {
      new_flag=1;   //新規ファイルの可能性
    }

  }
}

if(del_flag==1 && new_flag==1){
  rename_flag[n] = 1;
}


}
