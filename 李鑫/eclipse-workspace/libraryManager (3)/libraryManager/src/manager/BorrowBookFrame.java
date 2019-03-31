package manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

class BorrowBookFrame extends JFrame {
    private Users user;
    private Container con;
    private JTextField bookID, borrowDays;
    private BooksTable model;

    BorrowBookFrame(Users user, BooksTable model) {
        this.model = model;
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
        JLabel title1 = new JLabel("借阅书籍");
        title1.setFont(new Font("宋体",Font.BOLD,20));
        welcome.add(title1);
        con.add(welcome);

        //书籍编号
        JPanel bookPanel = new JPanel();
        JLabel bk = new JLabel("书籍编号");
        bookPanel.add(bk);
        bookID = new JTextField(16);
        bookID.setDocument(new MyDocument(10,true));
        bookID.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) borrowDays.requestFocus();
            }
        });
        bookPanel.add(bookID);
        con.add(bookPanel);

        //借阅时长
        JPanel timePanel = new JPanel();
        JLabel tm = new JLabel("借阅天数");
        timePanel.add(tm);
        borrowDays = new JTextField(16);
        borrowDays.setDocument(new MyDocument(3,true));
        borrowDays.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) borrow();
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
        timePanel.add(borrowDays);
        con.add(timePanel);

        JPanel buttonPanel = new JPanel();
        //借阅
        JButton loginButton = new JButton("借阅");
        loginButton.addActionListener(arg0 -> borrow());
        buttonPanel.add(loginButton);

        //取消
        JButton exitButton = new JButton("取消");
        exitButton.addActionListener(arg0 -> dispose());
        buttonPanel.add(exitButton);
        con.add(buttonPanel);
    }

    private void borrow() {
        if(bookID.getText().equals("")) {
            JOptionPane.showMessageDialog(con, "请输入书籍编号");
            return ;
        } else if(Integer.valueOf(bookID.getText()) < 0 ||
                Integer.valueOf(bookID.getText()) > Integer.valueOf(model.getValueAt(model.getRowCount()-1,0).toString())) {
            JOptionPane.showMessageDialog(con, "书籍编号不存在");
            return ;
        }
        if(borrowDays.getText().equals("")) {
            JOptionPane.showMessageDialog(con, "请输入借阅天数");
            return ;
        } else if(Integer.valueOf(borrowDays.getText()) > 180 || Integer.valueOf(borrowDays.getText()) < 0) {
            JOptionPane.showMessageDialog(con, "借阅天数范围[0,180]天");
            return ;
        }
        Connection conn = DataBase.getUserConnection();
        try {
            Statement stm = conn.createStatement();
            int id = Integer.parseInt(bookID.getText());
            Book book = Book.getBookById(id);
            if(book == null) {
                JOptionPane.showMessageDialog(con, "此书已下架~");
                return ;
            }
            if(book.isBorrowed) {
                JOptionPane.showMessageDialog(con, "此书已被借走~");
                return ;
            }
            String sql =  "UPDATE books SET Borrowed='y' WHERE book_id="+bookID.getText();
            stm.executeUpdate(sql);
            Date date = new Date(System.currentTimeMillis());
            int d = Integer.valueOf(borrowDays.getText());
            Date date2 = new Date(date.getTime()+d*24*3600*1000);
            sql = "INSERT INTO record(book_id, username, borrow_date, return_date, Returned)"+
                    " VALUES ('"+bookID.getText()+"','"+ user.getUserName() +"','"+date.toString()+
                    "','"+date2.toString()+"','n')";
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(con, "借书成功,记得在"+date2.toString()+"归还^_^");
            model.update();
            model.fireTableStructureChanged();
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(con, "借书失败x_x");
            e.printStackTrace();
        }

    }
}
