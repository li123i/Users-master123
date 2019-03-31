package manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class ChangeStuPwdFrame extends JFrame {
    private Container con;
    private JTextField idText;
    private JPasswordField password2,password3;


    ChangeStuPwdFrame() {
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
        JLabel title1 = new JLabel("修改学生密码");
        title1.setFont(new Font("宋体",Font.BOLD,20));
        welcome.add(title1);
        con.add(welcome);

        //用户名
        JPanel userID = new JPanel();
        JLabel id = new JLabel("用户名");
        userID.add(id);
        idText = new JTextField(16);
        idText.setDocument(new MyDocument(30));
        idText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) password2.requestFocus();
            }
        });
        userID.add(idText);
        con.add(userID);

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
        JButton registButton = new JButton("确认");
        registButton.addActionListener(arg0 -> change());
        buttonPanel.add(registButton);
        con.add(buttonPanel);

        JButton exitButton = new JButton("取消");
        exitButton.addActionListener(arg0 -> dispose());
        buttonPanel.add(exitButton);
        con.add(buttonPanel);
    }

    private void change() {
        String name = idText.getText();
        if(name.equals("")) {
            JOptionPane.showMessageDialog(con, "请输入用户名");
        } else {
            Users user = new Users(name);
            if(user.getPassword() != null) {
                String pass = new String(password2.getPassword());
                String pass2 = new String(password3.getPassword());
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
            } else {
                JOptionPane.showMessageDialog(con, "用户名不存在");
            }
        }
    }

}
