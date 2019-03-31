package manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.util.Arrays;

class RegistFrame extends JFrame {
    private Container con;
    private JTextField idText, questionText, answerText;
    private JPasswordField password, password2;

    RegistFrame() {
        setTitle("乐读图书馆管理系统");
        ImageIcon img = new ImageIcon(getClass().getResource("/img/library.png"));
        setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setSize(600,400);
        setLocation(600,350);

        con = getContentPane();
        con.setLayout(new GridLayout(10,1));

        JPanel welcome = new JPanel();
        JLabel title = new JLabel("用户注册");
        title.setFont(new Font("宋体",Font.BOLD,20));
        welcome.add(title);
        con.add(welcome);

        JPanel userID = new JPanel();
        JLabel id = new JLabel("   用户名");
        userID.add(id);
        idText = new JTextField(18);
        idText.setDocument(new MyDocument(30));
        idText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) password.requestFocus();
            }
        });
        userID.add(idText);
        con.add(userID);

        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("设置密码");
        passwordPanel.add(passwordLabel);
        password = new JPasswordField(18);
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
        passwordPanel.add(password);
        con.add(passwordPanel);

        JPanel passwordPanel2 = new JPanel();
        JLabel passwordLabel2 = new JLabel("确认密码");
        passwordPanel2.add(passwordLabel2);
        password2 = new JPasswordField(18);
        password2.setEchoChar('*');
        password2.setDocument(new MyDocument(30));
        password2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) questionText.requestFocus();
            }
        });
        passwordPanel2.add(password2);
        con.add(passwordPanel2);

        JPanel questionPanel = new JPanel();
        JLabel questionLabel = new JLabel("密保问题");
        questionPanel.add(questionLabel);
        questionText = new JTextField(18);
        questionText.setDocument(new MyDocument(100));
        questionText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) answerText.requestFocus();
            }
        });
        questionPanel.add(questionText);
        con.add(questionPanel);

        JPanel answerPanel = new JPanel();
        JLabel answerLabel = new JLabel("密保答案");
        answerPanel.add(answerLabel);
        answerText = new JTextField(18);
        answerText.setDocument(new MyDocument(100));
        answerText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) regist();
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
        answerPanel.add(answerText);
        con.add(answerPanel);

        JPanel buttonPanel = new JPanel();
        JButton registButton = new JButton("注册");
        registButton.addActionListener(arg0 -> regist());
        buttonPanel.add(registButton);
        con.add(buttonPanel);

        JButton exitButton = new JButton("返回");
        exitButton.addActionListener(arg0 -> dispose());
        buttonPanel.add(exitButton);
        con.add(buttonPanel);

    }

    private void regist() {
        String userName = idText.getText();
        if(userName.equals("")) {
            JOptionPane.showMessageDialog(con, "用户名不能为空");
            return ;
        }
        String p = new String(password.getPassword());
        if(p.equals("")) {
            JOptionPane.showMessageDialog(con, "密码不能为空,请设置密码");
            return ;
        }
        String p2 = new String(password2.getPassword());
        if(p2.equals("")) {
            JOptionPane.showMessageDialog(con, "请确认密码");
            return ;
        }
        String mb = questionText.getText();
        if(mb.equals("")) {
            JOptionPane.showMessageDialog(con, "请设置密保问题,方便您找回密码");
            return ;
        }
        String as = answerText.getText();
        if(as.equals("")) {
            JOptionPane.showMessageDialog(con, "密保答案不能为空");
            return ;
        }

        Connection conn ;
        try {
            conn = DataBase.getUserConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from user");
            while(rs.next()){
                String name = rs.getString("username");
                if(name.equals(userName)) {
                    JOptionPane.showMessageDialog(con, "用户名已经存在");
                    return ;
                }
            }
            if(!Arrays.equals(password.getPassword(), password2.getPassword())) {
                JOptionPane.showMessageDialog(con, "两次输入密码不一样");
                return ;
            }
            String sql = "INSERT INTO user(username, passwd, question, answer, job)"+
                    " VALUES ('"+userName+"','"+ new String(password.getPassword()) +"','"+questionText.getText()+"','"+answerText.getText()+ "','Student"+"')";
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(con, "注册成功^_^");
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(con, "数据库连接失败");
            e.printStackTrace();
        }
    }

}