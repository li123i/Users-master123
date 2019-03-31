package manager;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

class Book {
    int id;
    private String name, author, about;
    boolean isBorrowed;

    Book(){}

    Book(int id, String name, String author, String about, boolean isBorrowed) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.about = about;
        this.isBorrowed = isBorrowed;
    }

    static Book getBookById(int d) {
        Connection conn = DataBase.getUserConnection();
        Book book = null;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from books");
            while(rs.next()) {
                int book_id = rs.getInt("book_id");
                if(d == book_id) {
                    book = new Book(book_id, rs.getString("bookname"), rs.getString("author"),
                            rs.getString("about"), rs.getString("Borrowed").equals("y"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getAuthor() {
        return author;
    }

    String getAbout() {
        return about;
    }

    Object getEle(int idx) {
        if(idx == 0) return id;
        if(idx == 1) return name;
        if(idx == 2) return author;
        if(idx == 3) return about;
        if(isBorrowed) return "已借走";
        Button button = new Button("借书");
        button.addActionListener(arg0 -> borrow());
        return "未借走";
    }

    void setName(String name) {
        this.name = name;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    void setAbout(String about) {
        this.about = about;
    }

    void borrow() {

    }
}
