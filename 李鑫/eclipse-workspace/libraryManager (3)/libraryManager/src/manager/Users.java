package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Users {
    private String userName, password, question, answer, job;
    private Connection conn;

    Users(String userName) {
        conn = DataBase.getUserConnection();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from user");
            while(rs.next()) {
                String name = rs.getString("username");
                if(name.equals(userName)) {
                    this.userName = userName;
                    this.password = rs.getString("passwd");
                    this.question = rs.getString("question");
                    this.answer = rs.getString("answer");
                    this.job = rs.getString("job");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String getUserName() {
        return userName;
    }

    String getPassword() {
        return password;
    }

    String getQuestion() {
        return question;
    }

    String getAnswer() {
        return answer;
    }

    String getJob() {
        return job;
    }

    boolean setPassword(String password) {
        this.password = password;
        try {
            Statement stm = conn.createStatement();
            String sql =  "UPDATE user SET passwd='"+password+"' WHERE username='"+userName+"'";
            stm.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
