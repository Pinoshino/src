package pullRequest;

import fileIO.DataExporter;
import net.arnx.jsonic.JSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static param.Param.renameListFile;


/**
 * Created by Ryoto on 2016/08/06.
 */
public class GitHubReferrer {
    // GitHubAPIにアクセスするためのトークン。アクセス制限数を増やせる
    private static final String GITHUB_TOKEN = "ad5b2f370efa8124701d728f9e13ff1ebfcc1859";
    /*
     * GitHubAPIから取得した情報を格納する
     * ArrayList<String>には
     * 0     : title
     * 1~... : message
     * が記録されている
     */

    /**
     * URLにコネクションして読み取りきるまでreadLineする
     *
     * @param url 接続先URL
     * @return 読み取った全行
     * @throws java.io.IOException
     */
    private static String getLineFromUrl(String url) {
        String read = "", readBlock;
        try {
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
        } catch (Exception e) {

        }
        return read;
    }

    /**
     * ダブルクォートをエスケープ
     *
     * @param str 文字列
     * @return エスケープ後の文字列
     */
    private static String escDblQuote(String str) {
        return str.replace("\"", "\"\"");
    }


    // userとrepoは試験的に固定にしている
//    static String user = "AndlyticsProject"; // リポジトリのユーザー
//    static String repo = "andlytics"; // リポジトリ名
    static String user = "dougkeen"; // リポジトリのユーザー
    static String repo = "bartrunnerandroid"; // リポジトリ名

    static String baseUrl = "https://api.github.com";

    public static void main(String[] args) {
        //   rename情報を出力
//        makeRenameFile();
//        プルリクエスト情報を出力
        makePullRequestCsv();
    }

//pullRequestをcsvで生成
    public static void makePullRequestCsv() {
        String csvFilename = "pullRequest/"+repo + "-" + user + ".csv";
        ArrayList<String> output = new ArrayList<String>();
        output.add("title,body,commit,filename,additions,milestone,date");

        int page = 1;
        while (true) {
            // 全てのプルリクエストの概要のURL
            String pullsUrl = baseUrl + "/repos/" + user + "/" + repo + "/pulls?state=all&page=" + page;
            //取得
            Map[] pullRequests = JSON.decode(getLineFromUrl(pullsUrl), Map[].class);
            if (pullRequests.length == 0) break;


            // 取得したプルリクエスト一覧についてコミット一覧を取得する
            for (Map pullRequest : pullRequests) {

                Map milestones = (Map) pullRequest.get("milestone");
                String milestone;
                if (milestones == null) milestone = null;
                else milestone = (String) milestones.get("title");

                // 1プルリクエストのコミット一覧URL
                String pullUrl = pullRequest.get("commits_url").toString();
                // 取得
                Map<String, Map>[] commits = JSON.decode(getLineFromUrl(pullUrl), Map[].class);

                // 取得したコミット一覧について、各コミットの詳細情報を取得する
                for (Map<String, Map> commit : commits) {

                    // 1コミットの詳細URL
                    String commitUrl = baseUrl + "/repos/" + user + "/" + repo + "/commits/" + commit.get("sha");
                    // 取得
                    Map<String, ArrayList<Map>> commitDetail = JSON.decode(getLineFromUrl(commitUrl), Map.class);

                    // 取得したコミット情報の中から、ファイルに関する情報を取り出し詳細を書き出す
                    ArrayList<Map> files = commitDetail.get("files");

                    for (Map file : files) {
                        if (!((String) file.get("filename")).contains(".java"))
                            continue;
                        LinkedHashMap commitAuthor = (LinkedHashMap) commit.get("commit").get("author");

                        //csv書き出し
                        output.add("\"" + escDblQuote((String) pullRequest.get("title")) + "\",\"" + escDblQuote((String) pullRequest.get("body")) + "\",\"" + escDblQuote((String) commit.get("commit").get("message")) + "\",\"" + escDblQuote((String) file.get("filename")) + "\",\"" + file.get("additions") + "\",\"" + milestone + "\",\"" + commitAuthor.get("date") + "\"");
//                       csvWriter.println("\""+ escDblQuote((String) pullRequest.get("title"))+ "\",\"" + escDblQuote((String) commit.get("commit").get("message"))+ "\",\"" + escDblQuote((String) file.get("filename")) + "\",\"" + file.get("additions") + "\",\"" + file.get("deletions") + "\",\"" + file.get("changes")  + "\",\"" + milestone  + "\"");
                    }
                }
            }
            page++;
        }
        DataExporter.export(output, csvFilename);
    }

    static void makeRenameFile() {
        ArrayList<String> renameInfo = new ArrayList<String>();
        renameInfo.add("title,date");

        int page = 1;
        while (true) {
            // 全てのプルリクエストの概要のURL
            String pullsUrl = baseUrl + "/repos/" + user + "/" + repo + "/pulls?state=all&page=" + page;
            //取得
            Map[] pullRequests = JSON.decode(getLineFromUrl(pullsUrl), Map[].class);
            if (pullRequests.length == 0) break;

            for (Map pullRequest : pullRequests) {
                renameInfo.add("\"" + escDblQuote((String) pullRequest.get("title")) + "\",\"" + pullRequest.get("created_at") + "\"");
                ArrayList<String> webInfo = Display.returnWebInfo(pullRequest.get("html_url") + "/files");
                renameInfo.addAll(Search_rename.search(webInfo));
            }
            page++;
        }
        DataExporter.export(renameInfo, renameListFile);
    }
}
