package manager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.*;

class ForgetFrame extends JFrame {
    private Container con;
    private JPasswordField password,password2,password3;
    private Users user;

    ForgetFrame(Users user) {
        this.user = user;
        setTitle("乐读图书馆管理系统");
        ImageIcon img = new ImageIcon(getClass().getResource("/img/library.png"));
        setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setSize(500,320);
        setLocation(650,400);

        con = getContentPane();
        con.setLayout(new GridLayout(5,1));

        //标题
        JPanel welcome = new JPanel();
        JLabel title1 = new JLabel("修改密码");
        title1.setFont(new Font("宋体",Font.BOLD,20));
        welcome.add(title1);
        con.add(welcome);

        //密码
        JPanel pwdPanel = new JPanel();
        JLabel pwdLabel = new JLabel("  原密码");
        pwdPanel.add(pwdLabel);
        password = new JPasswordField(16);
        password.setEchoChar('*');
        password.setDocument(new MyDocument(30));
        password.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) password2.requestFocus();
            }
        });
        pwdPanel.add(password);
        con.add(pwdPanel);

        //新密码
        JPanel pwdPanel2 = new JPanel();
        JLabel pwdLabel2 = new JLabel("  新密码");
        pwdPanel2.add(pwdLabel2);
        password2 = new JPasswordField(16);
        password2.setEchoChar('*');
        password2.setDocument(new MyDocument(30));
        password2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) password3.requestFocus();
            }
        });
        pwdPanel2.add(password2);
        con.add(pwdPanel2);

        //确认密码
        JPanel pwdPanel3 = new JPanel();
        JLabel pwdLabel3 = new JLabel("确认密码");
        pwdPanel3.add(pwdLabel3);
        password3 = new JPasswordField(16);
        password3.setEchoChar('*');
        password3.setDocument(new MyDocument(30));
        password3.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) change();
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
        pwdPanel3.add(password3);
        con.add(pwdPanel3);

        JPanel buttonPanel = new JPanel();

        //修改密码
        JButton registButton = new JButton("修改密码");
        registButton.addActionListener(arg0 -> change());
        buttonPanel.add(registButton);

        //忘记密码
        JButton findPwdButton = new JButton("忘记密码");
        findPwdButton.addActionListener(arg0 -> {
            JFrame forget = new FindPwdFrame(user);
            forget.setVisible(true);
        });
        buttonPanel.add(findPwdButton);

        //取消
        JButton exitButton = new JButton("取消");
        exitButton.addActionListener(arg0 -> dispose());
        buttonPanel.add(exitButton);
        con.add(buttonPanel);

    }

    private void change() {
        if (user.getPassword().equals(new String(password.getPassword()))) {
            if(Arrays.equals(password2.getPassword(), password3.getPassword())) {
                if(user.setPassword(new String(password2.getPassword()))) {
                    JOptionPane.showMessageDialog(con, "修改成功");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(con, "修改失败");
                }
            } else {
                JOptionPane.showMessageDialog(con, "两次输入密码不一样");
            }
        } else {
            JOptionPane.showMessageDialog(con, "原密码错误");
        }
    }

}