package manager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class BorrowRecordFrame extends JFrame {
    private Container con;
    private Users user;
    private BorrowTable model;

    BorrowRecordFrame(Users user) {
        this.user = user;
        setTitle("乐读图书馆管理系统");
        ImageIcon img = new ImageIcon(getClass().getResource("/img/library.png"));
        setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setSize(800,600);
        setLocation(500,300);

        con = getContentPane();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbs = new GridBagConstraints();
        con.setLayout(gbl);

        JPanel title = new JPanel();
        JLabel tit = new JLabel("乐读图书馆借阅记录");
        tit.setFont(new Font("宋体", Font.BOLD,20));
        title.add(tit);
        gbs.weightx=0;gbs.weighty=0;
        gbs.gridheight=1;gbs.gridwidth=8;
        gbs.gridx=0;gbs.gridy=0;
        gbl.setConstraints(title, gbs);
        con.add(title);

        model = new BorrowTable(user);
        JTable table = new JTable(model);
        JScrollPane scrollpane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        gbs.fill = GridBagConstraints.BOTH;
        gbs.weightx=1;gbs.weighty=2;
        gbs.gridheight=5;gbs.gridwidth=8;
        gbs.gridx=0;gbs.gridy=2;
        gbl.setConstraints(scrollpane, gbs);
        con.add(scrollpane);
    }
}
