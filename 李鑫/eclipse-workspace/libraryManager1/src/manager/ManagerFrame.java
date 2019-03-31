package manager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.Date;

class ManagerFrame extends JFrame {
    private JTextField search;
    private BooksTable model;

    ManagerFrame(Users u) {

        setTitle("乐读图书馆管理系统");
        ImageIcon img = new ImageIcon(getClass().getResource("/img/library.png"));
        setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setSize(1000,800);
        setLocation(400,200);

        Container con = getContentPane();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbs = new GridBagConstraints();
        con.setLayout(gbl);

        JPanel title = new JPanel();
        JLabel tit = new JLabel("乐读图书馆");
        tit.setFont(new Font("宋体", Font.BOLD,20));
        title.add(tit);
        gbs.weightx=0;gbs.weighty=0;
        gbs.gridheight=1;gbs.gridwidth=12;
        gbs.gridx=0;gbs.gridy=0;
        gbl.setConstraints(title, gbs);
        con.add(title);

        //查询借阅记录
        JButton record = new JButton("借阅记录");
        record.addActionListener(arg0 -> {
            JFrame frame = new BorrowRecordFrame(u);
            frame.setVisible(true);
        });
        gbs.gridwidth=1;gbs.gridheight=1;
        gbs.gridx=0;gbs.gridy=1;
        gbl.setConstraints(record, gbs);
        con.add(record);

        //修改密码
        JButton change = new JButton("修改密码");
        change.addActionListener(arg0 -> {
            ForgetFrame forgetFrame = new ForgetFrame(u);
            forgetFrame.setVisible(true);
        });
        gbs.gridx=1;
        gbl.setConstraints(change, gbs);
        con.add(change);

        //添加书籍
        JButton add = new JButton("添加书籍");
        add.addActionListener(arg0 -> {

            AddBookFrame addBookFrame = new AddBookFrame(model);
            addBookFrame.setVisible(true);

        });
        gbs.gridx=2;
        gbl.setConstraints(add, gbs);
        if(u.getJob().equals("Manager")) con.add(add);

        //删除书籍
        JButton del = new JButton("删除书籍");
        del.addActionListener(arg0 -> {
            DeleteBookFrame deleteBookFrame = new DeleteBookFrame(model);
            deleteBookFrame.setVisible(true);

        });
        gbs.gridx=3;
        gbl.setConstraints(del, gbs);
        if(u.getJob().equals("Manager")) con.add(del);

        //借阅书籍
        JButton borrow = new JButton("借阅书籍");
        borrow.addActionListener(arg0 -> {

            BorrowBookFrame borrowBookFrame = new BorrowBookFrame(u, model);
            borrowBookFrame.setVisible(true);

        });
        gbl.setConstraints(borrow, gbs);
        if(u.getJob().equals("Student")) con.add(borrow);

        //归还书籍
        JButton ret = new JButton("归还书籍");
        ret.addActionListener(arg0 -> {
            ReturnBookFrame returnBookFrame = new ReturnBookFrame(model);
            returnBookFrame.setVisible(true);

        });
        gbs.gridx = 4;
        gbl.setConstraints(ret, gbs);
        if(u.getJob().equals("Manager")) con.add(ret);

        //修改学生密码
        JButton ch = new JButton("修改学生密码");
        ch.addActionListener(arg0 -> {
            JFrame frame = new ChangeStuPwdFrame();
            frame.setVisible(true);

        });
        gbs.gridx=5;
        gbl.setConstraints(ch, gbs);
        if(u.getJob().equals("Manager")) con.add(ch);

        //刷新
        JButton re = new JButton("刷新");
        re.addActionListener(arg0 -> {
            model.update();
            model.fireTableStructureChanged();
        });
        gbs.gridx=6;
        gbl.setConstraints(re, gbs);
        con.add(re);

        //注销登录
        JButton logout = new JButton("注销登录");
        logout.addActionListener(arg0 -> {
            dispose();
            JFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
        gbs.gridx=7;
        gbl.setConstraints(logout, gbs);
        con.add(logout);

        JLabel label = new JLabel("  欢迎您,"+ u.getUserName());
        gbs.gridx=9;
        gbl.setConstraints(label, gbs);
        con.add(label);

        //检索
        JPanel searchPanel = new JPanel();
        search = new JTextField(10);
        search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                model.search(search.getText());
                model.fireTableStructureChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                model.search(search.getText());
                model.fireTableStructureChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                model.search(search.getText());
                model.fireTableStructureChanged();
            }
        });
        gbs.gridx=8;gbs.gridy=1;
        searchPanel.add(search);
        gbl.setConstraints(searchPanel, gbs);
        con.add(searchPanel);


        model = new BooksTable();
        JTable table = new JTable(model);
        JScrollPane scrollpane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        gbs.fill = GridBagConstraints.BOTH;
        gbs.weightx=1;gbs.weighty=2;
        gbs.gridheight=8;gbs.gridwidth=12;
        gbs.gridx=0;gbs.gridy=2;
        gbl.setConstraints(scrollpane, gbs);
        con.add(scrollpane);

        if(u.getJob().equals("Manager")) return;
        BorrowTable borrowTable = new BorrowTable(u);
        Date date = new Date(System.currentTimeMillis());
        for(int i = 0; i < borrowTable.getRowCount(); i++) {
            String returned = borrowTable.getValueAt(i,6).toString();
            if(date.toString().compareTo(borrowTable.getValueAt(i, 5).toString()) > 0 && returned.equals("未归还")) {
                JOptionPane.showMessageDialog(con, "您有逾期未归还的书籍,请查看借阅记录");
                break;
            }
        }

    }

}
