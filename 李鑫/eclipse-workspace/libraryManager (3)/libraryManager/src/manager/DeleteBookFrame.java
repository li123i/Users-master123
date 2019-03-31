package manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class DeleteBookFrame extends JFrame {
    private Container con;
    private JTextField bookID;
    private BooksTable model;

    DeleteBookFrame(BooksTable model) {
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
        JLabel title1 = new JLabel("删除书籍");
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
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) del();
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
        bookPanel.add(bookID);
        con.add(bookPanel);

        JPanel buttonPanel = new JPanel();
        //删除
        JButton loginButton = new JButton("删除");
        loginButton.addActionListener(arg0 -> del());
        buttonPanel.add(loginButton);

        //取消
        JButton exitButton = new JButton("取消");
        exitButton.addActionListener(arg0 -> dispose());
        buttonPanel.add(exitButton);
        con.add(buttonPanel);
    }

    private void del() {
        if(bookID.getText().equals("")) {
            JOptionPane.showMessageDialog(con, "请输入书籍编号");
            return ;
        }
        if(Integer.valueOf(bookID.getText()) < 0 ||
                Integer.valueOf(bookID.getText()) > Integer.valueOf(model.getValueAt(model.getRowCount()-1,0).toString())) {
            JOptionPane.showMessageDialog(con, "书籍编号不存在");
            return ;
        }
        Connection conn = DataBase.getUserConnection();
        try {
            Statement stm = conn.createStatement();
            int id = Integer.parseInt(bookID.getText());
            Book book = Book.getBookById(id);
            if(book.isBorrowed) {
                JOptionPane.showMessageDialog(con, "此书尚未归还~");
                return ;
            }

            String sql =  "DELETE FROM books WHERE book_id="+bookID.getText();
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(con, "删除成功");
            model.update();
            model.fireTableStructureChanged();
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(con, "删除失败x_x");
            e.printStackTrace();
        }
    }
}
