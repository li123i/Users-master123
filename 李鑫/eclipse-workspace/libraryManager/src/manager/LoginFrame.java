package manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class LoginFrame extends JFrame {
        private Container con;
        private JTextField idText;
        private JPasswordField password;

        LoginFrame() {
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
            JLabel title1 = new JLabel("乐读图书馆管理系统");
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
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {}

                @Override
                public void keyReleased(KeyEvent e) {
                    if(e.getKeyChar() == KeyEvent.VK_ENTER) password.requestFocus();
                }
            });
            userID.add(idText);
            con.add(userID);

            //密码
            JPanel pwdPanel = new JPanel();
            JLabel pwdLabel = new JLabel("    密码");
            pwdPanel.add(pwdLabel);
            password = new JPasswordField(16);
            password.setEchoChar('*');
            password.setDocument(new MyDocument(30));
            password.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyChar() == KeyEvent.VK_ENTER) Login();
                }

                @Override
                public void keyReleased(KeyEvent e) { }
            });
            pwdPanel.add(password);
            con.add(pwdPanel);

            JPanel buttonPanel = new JPanel();

            //登录
            JButton loginButton = new JButton("登录");
            loginButton.addActionListener(arg0 -> Login());
            buttonPanel.add(loginButton);

            //注册
            JButton registButton = new JButton("注册");
            registButton.addActionListener(arg0 -> {
                JFrame reg = new RegistFrame();
                reg.setVisible(true);
            });
            buttonPanel.add(registButton);

            JButton findPwdButton = new JButton("忘记密码");
            findPwdButton.addActionListener(arg0 -> {
                JFrame forget = new FindPwdFrame(null);
                forget.setVisible(true);
            });
            buttonPanel.add(findPwdButton);

            //取消
            JButton exitButton = new JButton("退出");
            exitButton.addActionListener(arg0 -> System.exit(0));
            buttonPanel.add(exitButton);
            con.add(buttonPanel);

            idText.requestFocus();

        }

        private void Login() {
            String userName = idText.getText();
            String inputPwd = new String(password.getPassword());
            if(userName.equals("")) {
                JOptionPane.showMessageDialog(con, "请输入用户名^_^");
                return ;
            }
            if(inputPwd.equals("")) {
                JOptionPane.showMessageDialog(con, "请输入密码>_<");
                return ;
            }
            Users user = new Users(userName);
            if(user.getPassword()!=null) {
                if(user.getPassword().equals(inputPwd)) {
                    JFrame manFrame = new ManagerFrame(user);
                    manFrame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(con, "密码错误x_x");
                }
            } else {
                JOptionPane.showMessageDialog(con, "用户名不存在啊x_x");
            }

        }
}