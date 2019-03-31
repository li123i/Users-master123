package manager;

public class Manager extends Users {

    Manager(String userName) {
        super(userName);
    }

    public void changeStuPwd(Student student, String pwd) {
        //TODO
        student.setPassword(pwd);
    }

    public void addBook(Book book) {

    }
}
