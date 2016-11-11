package pullRequest;

import net.arnx.jsonic.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import format.PullRequestListMaker;

/**
 * Created by Ryoto on 2016/08/06.
 */
public class GitHubReferrer2 {
    // GitHubAPIにアクセスするためのトークン。アクセス制限数を増やせる
    private static final String GITHUB_TOKEN = "ad5b2f370efa8124701d728f9e13ff1ebfcc1859";
    /*
     * GitHubAPIから取得した情報を格納する
     * ArrayList<String>には
     * 0     : title
     * 1~... : message
     * が記録されている
     */
    private static ArrayList<ArrayList<String>> pullRequestsContents = new ArrayList<ArrayList<String>>();

    /**
     * URLにコネクションして読み取りきるまでreadLineする
     * @param url 接続先URL
     * @return 読み取った全行
     * @throws java.io.IOException
     */
    private static String getLineFromUrl(String url) throws IOException {
        String read = "", readBlock;
        URL connectUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) connectUrl.openConnection();
        con.setRequestProperty("Authorization", "token " + GITHUB_TOKEN);
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setInstanceFollowRedirects(true);

        // 接続
        con.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        while ((readBlock = reader.readLine()) != null) {
            read = read + readBlock;
        }
        con.disconnect();
        return read;
    }

    /**
     * ダブルクォートをエスケープ
     * @param str 文字列
     * @return エスケープ後の文字列
     */
    private static String escDblQuote(String str) {
        return str.replace("\"", "\"\"");
    }

    // 現在はmainメソッドにしているが必要に応じて入れ替える
    //    public static void makePullRequestCsv(String user, String repo) throws Exception {
    public static void main(String[] args) throws Exception {
        // userとrepoは試験的に固定にしている
//        String user = "AndlyticsProject"; // リポジトリのユーザー
//        String repo = "andlytics"; // リポジトリ名
        String user = "dougkeen"; // リポジトリのユーザー
        String repo = "bartrunnerandroid"; // リポジトリ名

        String baseUrl = "https://api.github.com";

        /*
         * csvの設定とファイル作成、上書き
         */
        String csvFilename = repo + "-" + user + ".csv";
        File csv = new File(csvFilename);
        if (!csv.exists()) csv.createNewFile();
        else csv.delete();
        PrintWriter csvWriter = new PrintWriter(new FileWriter(csv));

        int page = 1;
        while (true) {
            // 全てのプルリクエストの概要のURL
            String pullsUrl = baseUrl + "/repos/" + user + "/" + repo + "/pulls?state=all&page=" + page;
            //取得
            Map[] pullRequests = JSON.decode(getLineFromUrl(pullsUrl), Map[].class);
            if (pullRequests.length == 0) break;

            // 取得したプルリクエスト一覧についてコミット一覧を取得する
            for (Map pullRequest : pullRequests) {

//               テスト用
//            Map pullRequest = pullRequests[0];
//                if(pullRequest.containsKey("milestone")) {
//                    System.out.println(pullRequest.containsKey("milestone"));
//                    csvWriter.println(pullRequest.get("milestone"));
//                }

//                ArrayList<String> contents = new ArrayList<String>();
//                contents.add(pullRequest.get("title").toString());
                Map milestones = (Map) pullRequest.get("milestone");
                String milestone;
                if(milestones==null) milestone=null;
                else milestone = (String) milestones.get("title");


                // 1プルリクエストのコミット一覧URL
                String pullUrl = pullRequest.get("commits_url").toString();
                // 取得
                Map<String, Map>[] commits = JSON.decode(getLineFromUrl(pullUrl), Map[].class);

                // 取得したコミット一覧について、各コミットの詳細情報を取得する
                for (Map<String, Map> commit : commits) {

//                テスト用
//            Map<String, Map> commit = commits[0];
//            csvWriter.println(commit);

                    // 1コミットの詳細URL
                    String commitUrl = baseUrl + "/repos/" + user + "/" + repo + "/commits/" + commit.get("sha");
                    // 取得
                    Map<String, ArrayList<Map>> commitDetail = JSON.decode(getLineFromUrl(commitUrl), Map.class);

                    // 取得したコミット情報の中から、ファイルに関する情報を取り出し詳細を書き出す
                    ArrayList<Map> files = commitDetail.get("files");

//                    テスト用
//            Map file = files.get(0);
//            csvWriter.println(file);

                    for (Map file : files) {
                        if (!((String) file.get("filename")).contains(".java"))
                            continue;
                       //contents.add(""+commit.get("message"));
                        //csv書き出し
//                       csvWriter.println("\""+ escDblQuote((String) pullRequest.get("title")) + "\",\"" + escDblQuote((String) commit.get("commit").get("message")) + "\",\"" + escDblQuote((String) file.get("filename")) + "\",\"" + file.get("additions") + "\",\"" + file.get("deletions") + "\",\"" + file.get("changes")  + "\",\"" + milestone + "\"");
//                       csvWriter.flush();
//                        System.out.println( file.get("patch"));
                    }
                }
//                pullRequestsContents.add(contents);
            }
            page++;
        }
        // 要求-プルリクエスト対応xmlの作成
//        new PullRequestListMaker(pullRequestsContents).makeFormat();
        csvWriter.close();
    }
}
