package pullResuest;


/**
 * Created by Yuya Shinohara on 2017/01/06.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import pullRequest.GitHubReferrer2;


class pullRequest_gui extends JFrame implements ActionListener {

  JTextField text1;
  JTextField text2;
  JLabel label1;
  // JLabel label2;

  public static void main(String args[]){
    pullRequest_gui frame = new pullRequest_gui("GitHubReferrer");
    frame.setVisible(true);
  }

  pullRequest_gui(String title){
    setTitle(title);
    setBounds(100, 100, 800, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();

    panel.setBackground(Color.GREEN);

    text1 = new JTextField("ユーザ名入力", 20);
    text2 = new JTextField("リポジトリ名入力", 20);

    // text2 = new JTextField("住所2", 20);
    JButton button = new JButton("読み込み");
    button.addActionListener(this);
    label1 = new JLabel();
    // label2 = new JLabel();

    panel.add(text1);
    panel.add(text2);

    // panel.add(text2);
    panel.add(button);

    Container contentPane = getContentPane();
    contentPane.add(panel, BorderLayout.NORTH);
    contentPane.add(label1, BorderLayout.CENTER);
    // contentPane.add(label2, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent e){
    // label1.setText(text1.getText() + text2.getText());
    label1.setText("ユーザ名は" + text1.getText() + ", リポジトリ名は" + text2.getText() + "です。");
    // label2.setText(text2.getText());

    // 次の関数に2つの引数を渡して実行させる
    try {
      GitHubReferrer2.getGithubInfo(text1.getText(), text2.getText());
    }catch(Exception ex){;
    }
  }


}
