package manager;

import javax.swing.table.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BooksTable extends AbstractTableModel {

    private Vector<String> colNames;
    private Vector<Book> data;

    BooksTable() {
        colNames = new Vector<>();
        colNames.add("书籍编号");
        colNames.add("书名");
        colNames.add("作者");
        colNames.add("简介");
        colNames.add("书籍状态");

        data = new Vector<>();
        Connection conn = DataBase.getUserConnection();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from books");
            while(rs.next()) {
                Book book = new Book(rs.getInt("book_id"), rs.getString("bookname"),
                        rs.getString("author"), rs.getString("about"), rs.getString("Borrowed").equals("y"));
                data.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).getEle(columnIndex);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Class getColumnClass(int col) {
        if(col == 0) return Integer.class;
        else return String.class;
    }

    @Override
    public String getColumnName(int col) {
        return colNames.elementAt(col);
    }

    void update() {
        data.clear();
        Connection conn = DataBase.getUserConnection();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from books");
            while(rs.next()) {
                Book book = new Book(rs.getInt("book_id"), rs.getString("bookname"),
                        rs.getString("author"), rs.getString("about"), rs.getString("Borrowed").equals("y"));
                data.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void search(String s) {
        if(s.equals("")) {
            update();
            return ;
        }
        data.clear();
        Connection conn = DataBase.getUserConnection();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from books");
            while(rs.next()) {
                if(rs.getString("book_id").equals(s) || rs.getString("bookname").contains(s)
                        || rs.getString("author").contains(s) || (rs.getString("Borrowed").equals("y")?"已借走":"未借走").equals(s)) {
                    Book book = new Book(rs.getInt("book_id"), rs.getString("bookname"),
                            rs.getString("author"), rs.getString("about"), rs.getString("Borrowed").equals("y"));
                    data.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
