package manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class FindPwdFrame extends JFrame {
    private Container con;
    private JPanel userID, buttonPanel, questionPanel, answerPanel;
    private JTextField idText, answerText;
    private JPasswordField passwordText,passwordText2;
    private JLabel title;
    private Users user;

    FindPwdFrame(Users user) {
        this.user = user;
        setTitle("乐读图书馆管理系统");
        ImageIcon img = new ImageIcon(getClass().getResource("/img/library.png"));
        setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setSize(400,300);
        setLocation(600,350);

        con = getContentPane();
        con.setLayout(new GridLayout(5,1));

        JPanel welcome = new JPanel();
        title = new JLabel("找回密码");
        title.setFont(new Font("宋体",Font.BOLD,20));
        welcome.add(title);
        con.add(welcome);

        userID = new JPanel();
        JLabel id = new JLabel("输入用户名");
        userID.add(id);
        idText = new JTextField(18);
        idText.setDocument(new MyDocument(30));
        idText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) verifi();
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
        userID.add(idText);
        con.add(userID);

        buttonPanel = new JPanel();
        JButton registButton = new JButton("验证密保");
        registButton.addActionListener(e -> verifi());
        buttonPanel.add(registButton);
        con.add(buttonPanel);

        JButton exitButton = new JButton("取消");
        exitButton.addActionListener(arg0 -> dispose());
        buttonPanel.add(exitButton);
        con.add(buttonPanel);

        idText.requestFocus();

        if(user != null) {
            verifi();
        }

    }


    private void verifi() {
        String name = idText.getText();
        if(name.equals("")) {
            JOptionPane.showMessageDialog(con, "请输入用户名");
        } else {
            this.user = new Users(name);
            if(this.user.getPassword() != null) {
                con.remove(userID);
                con.remove(buttonPanel);
                title = new JLabel("验证密保问题");

                questionPanel = new JPanel();
                JLabel questionLabel = new JLabel("密保问题: "+user.getQuestion());
                questionPanel.add(questionLabel);
                con.add(questionPanel);
                questionPanel.revalidate();

                answerPanel = new JPanel();
                JLabel answerLabel = new JLabel("密保答案");
                answerPanel.add(answerLabel);
                answerText = new JTextField(18);
                answerText.setDocument(new MyDocument(100));
                answerText.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) { }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyChar() == KeyEvent.VK_ENTER) changePwd();
                    }

                    @Override
                    public void keyReleased(KeyEvent e) { }
                });
                answerPanel.add(answerText);
                con.add(answerPanel);
                answerPanel.revalidate();

                buttonPanel = new JPanel();
                JButton registButton = new JButton("下一步");
                registButton.addActionListener(arg0 -> changePwd());
                buttonPanel.add(registButton);

                JButton exitButton = new JButton("取消");
                exitButton.addActionListener(arg0 -> dispose());
                buttonPanel.add(exitButton);
                con.add(buttonPanel);
                buttonPanel.revalidate();

                repaint();
                answerText.requestFocus();
            } else {
                JOptionPane.showMessageDialog(con, "用户名不存在");
            }
        }

    }

    private void changePwd() {

        String ans = answerText.getText();
        if(user.getAnswer().equals(ans)) {
            con.remove(questionPanel);
            con.remove(answerPanel);
            con.remove(buttonPanel);
            title = new JLabel("修改密码");

            JPanel passwordPanel = new JPanel();
            JLabel passwordLabel = new JLabel("   新密码");
            passwordPanel.add(passwordLabel);
            passwordText = new JPasswordField(18);
            passwordText.setEchoChar('*');
            passwordText.setDocument(new MyDocument(30));
            passwordText.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {}

                @Override
                public void keyReleased(KeyEvent e) {
                    if(e.getKeyChar() == KeyEvent.VK_ENTER) passwordText2.requestFocus();
                }
            });
            passwordPanel.add(passwordText);
            con.add(passwordPanel);
            passwordPanel.revalidate();

            JPanel passwordPanel2 = new JPanel();
            JLabel passwordLabel2 = new JLabel("确认密码");
            passwordPanel2.add(passwordLabel2);
            passwordText2 = new JPasswordField(18);
            passwordText2.setEchoChar('*');
            passwordText2.setDocument(new MyDocument(30));
            passwordText2.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) { }

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyChar() == KeyEvent.VK_ENTER) change();
                }

                @Override
                public void keyReleased(KeyEvent e) { }
            });
            passwordPanel2.add(passwordText2);
            con.add(passwordPanel2);
            passwordPanel2.revalidate();

            JPanel buttonPanel = new JPanel();
            JButton registButton = new JButton("修改密码");
            registButton.addActionListener(arg0 -> change());
            buttonPanel.add(registButton);

            JButton exitButton = new JButton("取消");
            exitButton.addActionListener(arg0 -> dispose());
            buttonPanel.add(exitButton);
            con.add(buttonPanel);
            buttonPanel.revalidate();

            repaint();
            passwordText.requestFocus();
        } else {
            JOptionPane.showMessageDialog(con, "答案错误");
        }

    }

    private void change() {
        String pass = new String(passwordText.getPassword());
        String pass2 = new String(passwordText2.getPassword());
        System.out.println(pass);
        if(pass.equals("")) {
            JOptionPane.showMessageDialog(con, "请输入新密码");
        } else if(pass2.equals("")) {
            JOptionPane.showMessageDialog(con, "请确认新密码");
        } else if(pass.equals(pass2)) {
            if(user.setPassword(pass)) {
                JOptionPane.showMessageDialog(con, "修改成功");
                dispose();
            } else {
                JOptionPane.showMessageDialog(con, "修改失败");
            }
        } else {
            JOptionPane.showMessageDialog(con, "两次输入密码不一样");
        }
    }

}