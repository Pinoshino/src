package pullRequest;


import java.io.*;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;


public class Display {
    public static ArrayList<String> returnWebInfo(String url) {
        // 実際はGitHubReferrer2.javaのnormalPullを代入
        // String s = getSourceText( new URL(normalPull) );
        ArrayList<String> output= new ArrayList<String>();
        try {
//        URL url = new URL("https://github.com/Pinoshino/src/pull/1/files");
        output = getSourceText(new URL(url));


        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return output;
    }

    // webの文字情報をすべて取得する関数
    public static ArrayList<String> getSourceText(URL url) {
        ArrayList<String> output = new ArrayList<String>();
        try {
            InputStream in = url.openStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            String s;
            while ((s = bf.readLine()) != null) {
                output.add(s);
            }
            in.close();
        } catch (IOException e){
        }
        return output;
    }
}