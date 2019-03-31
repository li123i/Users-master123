package manager;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

public class BorrowTable extends AbstractTableModel {

    private Vector<String> colNames;
    private Vector<Vector<Object> > data;

    BorrowTable(Users user) {
        colNames = new Vector<>();
        colNames.add("用户名");
        colNames.add("书籍");
        colNames.add("作者");
        colNames.add("书籍编号");
        colNames.add("借阅时间");
        colNames.add("归还时间");
        colNames.add("是否归还");

        data = new Vector<>();
        Connection conn = DataBase.getUserConnection();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from record");
            while(rs.next()) {
                String name = rs.getString("username");
                if(user.getUserName().equals(name) || user.getJob().equals("Manager")) {
                    Vector<Object> v = new Vector<>();
                    v.add(name);
                    int id = rs.getInt("book_id");
                    Book book = Book.getBookById(id);
                    v.add(book.getName());
                    v.add(book.getAuthor());
                    v.add(id);
                    Date borrowDate = rs.getDate("borrow_date");
                    Date returnDate = rs.getDate("return_date");
                    v.add(borrowDate.toString());
                    v.add(returnDate.toString());
                    v.add(rs.getString("Returned").equals("y")?"已归还":"未归还");
                    data.add(v);
                }
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
        return data.get(rowIndex).get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Class getColumnClass(int col) {
        if(col == 3) return Integer.class;
        else return String.class;
    }

    @Override
    public String getColumnName(int col) {
        return colNames.elementAt(col);
    }
}
