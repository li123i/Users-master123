package manager;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class AddBookFrame extends JFrame {
    private Container con;
    private JTextField bookName, bookAuthor;
    private JTextArea bookAbout;
    private BooksTable model;

    AddBookFrame(BooksTable model) {
        this.model = model;
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
        JLabel title1 = new JLabel("添加书籍");
        title1.setFont(new Font("宋体",Font.BOLD,20));
        welcome.add(title1);
        con.add(welcome);

        //书名
        JPanel bookNamePanel = new JPanel();
        JLabel bn = new JLabel("书籍名称");
        bookNamePanel.add(bn);
        bookName = new JTextField(16);
        bookName.setDocument(new MyDocument(30));
        bookName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) bookAbout.requestFocus();
            }
        });
        bookNamePanel.add(bookName);
        con.add(bookNamePanel);

        //作者
        JPanel bookAuthorPanel = new JPanel();
        JLabel au = new JLabel("书籍作者");
        bookAuthorPanel.add(au);
        bookAuthor = new JTextField(16);
        bookAuthor.setDocument(new MyDocument(30));
        bookAuthor.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) bookAbout.requestFocus();
            }
        });
        bookAuthorPanel.add(bookAuthor);
        con.add(bookAuthorPanel);

        //简介
        JPanel bookAboutPanel = new JPanel();
        JLabel ab = new JLabel("书籍简介");
        bookAboutPanel.add(ab);
        bookAbout = new JTextArea(3,16);
        bookAbout.setDocument(new MyDocument(100));
        bookAbout.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) add();
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
        bookAboutPanel.add(bookAbout);
        con.add(bookAboutPanel);

        JPanel buttonPanel = new JPanel();
        //添加
        JButton loginButton = new JButton("添加");
        loginButton.addActionListener(arg0 -> add());
        buttonPanel.add(loginButton);

        //取消
        JButton exitButton = new JButton("取消");
        exitButton.addActionListener(arg0 -> dispose());
        buttonPanel.add(exitButton);
        con.add(buttonPanel);

    }

    void add() {
        String name = bookName.getText();
        String author = bookAuthor.getText();
        String about = bookAbout.getText();
        if(name.equals("")) {
            JOptionPane.showMessageDialog(con, "请输入书籍名称");
            return ;
        } else if(author.equals("")) {
            JOptionPane.showMessageDialog(con, "请输入书籍作者");
            return ;
        } else if(about.equals("")) {
            JOptionPane.showMessageDialog(con, "请输入书籍简介");
            return ;
        }
        Connection conn ;
        try {
            conn = DataBase.getUserConnection();
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO books(bookname, author, about, Borrowed)"+
                    " VALUES ('"+name+"','"+ author +"','"+ about +"','n')";
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(con, "添加成功^_^");
            model.update();
            model.fireTableStructureChanged();
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(con, "添加失败");
            e.printStackTrace();
        }

    }


}
