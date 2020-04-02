package com.jason.model;

import com.jason.dao.JDBCHelper;
import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GPAstatic extends JFrame implements ActionListener {

    private JLabel searchlab,gpastatic;
    private JTable jTable,footTable;
    private JButton serachscore;
    private JPanel initpanel, head, foot;
    private JScrollPane spstu,spstat;
    private JComboBox classselect;
    private JDBCHelper jdbcHelper;
    private JTableHeader jTableHeader;

    public GPAstatic() {
        init();
        setChar();
        this.setTitle("成绩管理");
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000, 800);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        setLocation(680, 320);

        jdbcHelper.ConnecttoSQL();
        jTable = jdbcHelper.scrollpanelsocrce("select * from student");
        jTableHeader = jTable.getTableHeader();
        spstat.getViewport().add(footTable);
        spstu.getViewport().add(jTable);
        initpanel.setLayout(new BorderLayout());
        head.add(searchlab);
        head.add(classselect);
        head.add(serachscore);
        foot.add(gpastatic);
        foot.add(spstat);
        initpanel.add(head, BorderLayout.NORTH);
        spstu.setSize(200, 300);
        initpanel.add(spstu, BorderLayout.CENTER);
        initpanel.add(foot, BorderLayout.SOUTH);
        add(initpanel);
    }

    private void init() {
        jdbcHelper = new JDBCHelper();
        foot = new JPanel(new GridLayout(2,1));
        head = new JPanel();
        gpastatic = new JLabel("平均成绩");
        initpanel = new JPanel();
        serachscore = new JButton("查询");
        spstu = new JScrollPane();
        spstat = new JScrollPane();
        classselect = new JComboBox();
        setStu_class();
        serachscore.addActionListener(this);
        classselect.addActionListener(this);
        searchlab = new JLabel("查询班级成绩  ");
        jTable = null;
        footTable = null;
    }

    private void setStu_class() {
        classselect.addItem("计算机科学与技术");
        classselect.addItem("网络工程");
        classselect.addItem("数字媒体技术");
        classselect.addItem("金融大数据");
        classselect.addItem("金融信息化");
    }

    private String Trans2eng() {
        String classname = classselect.getSelectedItem().toString();
        String engclassname;
        if (classname.equals("计算机科学与技术")) {
            engclassname = "CS";
        } else if (classname.equals("网络工程")) {
            engclassname = "NP";
        } else if (classname.equals("数字媒体技术")) {
            engclassname = "DMT";
        } else if (classname.equals("金融大数据")) {
            engclassname = "FB";
        } else if (classname.equals("金融信息化")) {
            engclassname = "FI";
        } else {
            engclassname = "CS";
        }
        return engclassname;
    }

    private void setChar() {
        Font fontlab = new Font("Arial", Font.BOLD, 18);
        Font fontbut = new Font("Times New Roman", Font.PLAIN, 28);
        serachscore.setFont(fontbut);
        searchlab.setFont(fontlab);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("查询")) {
            String sql = "select * from student where stu_class='" + Trans2eng() + "'";
            jTable = jdbcHelper.scrollpanelsocrcestat(sql);
            jTableHeader = jTable.getTableHeader();
            spstu.getViewport().add(jTable);
        }
    }
}