package com.jason.model;
import com.jason.dao.JDBCHelper;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GPAControl extends JFrame implements ActionListener {
    private JLabel searchlab;
    private JTable jTable;
    private JButton addsroce,updatescore,serachscore;
    private JPanel initpanel,head,foot;
    private JScrollPane spstu;
    private JComboBox classselect;
    private JDBCHelper jdbcHelper;
    private JTableHeader jTableHeader;
    public GPAControl(){
        init();
        setChar();
        this.setTitle("成绩管理");
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000,800);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        setLocation(680,320);

        jdbcHelper.ConnecttoSQL();
        jTable=jdbcHelper.scrollpanelsocrce("select * from student");
        jTableHeader = jTable.getTableHeader();
        spstu.getViewport().add(jTable);
        initpanel.setLayout(new BorderLayout());
        head.add(searchlab);
        head.add(classselect);
        head.add(serachscore);
        initpanel.add(head,BorderLayout.NORTH);
        spstu.setSize(200,300);
        initpanel.add(spstu,BorderLayout.CENTER);
        foot.add(addsroce);
        foot.add(updatescore);
        initpanel.add(foot,BorderLayout.SOUTH);
        add(initpanel);
    }
    private void init(){
        jdbcHelper = new JDBCHelper();
        foot = new JPanel();
        head = new JPanel();
        initpanel = new JPanel();
        addsroce = new JButton("添加成绩");
        updatescore = new JButton("修改成绩");
        serachscore = new JButton("查询");
        spstu = new JScrollPane();
        classselect = new JComboBox();
        setStu_class();
        serachscore.addActionListener(this);
        addsroce.addActionListener(this);
        updatescore.addActionListener(this);
        classselect.addActionListener(this);
        searchlab = new JLabel("查询班级成绩  ");
        jTable=null;
    }
    private void setStu_class(){
        classselect.addItem("计算机科学与技术");
        classselect.addItem("网络工程");
        classselect.addItem("数字媒体技术");
        classselect.addItem("金融大数据");
        classselect.addItem("金融信息化");
    }
    private String Trans2eng(){
        String classname = classselect.getSelectedItem().toString();
        String engclassname;
        if(classname.equals("计算机科学与技术")){
            engclassname = "CS";
        }else if(classname.equals("网络工程")){
            engclassname = "NP";
        }else if(classname.equals("数字媒体技术")){
            engclassname = "DMT";
        }else if(classname.equals("金融大数据")){
            engclassname = "FB";
        }else if(classname.equals("金融信息化")){
            engclassname = "FI";
        }else{
            engclassname= "CS";
        }
        return engclassname;
    }

    private void setChar(){
        Font fontlab = new Font("Arial",Font.BOLD,18);
        Font fontbut = new Font("Times New Roman",Font.PLAIN,28);
        addsroce.setFont(fontbut);
        updatescore.setFont(fontbut);
        serachscore.setFont(fontbut);
        searchlab.setFont(fontlab);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("查询")){
            String sql ="select * from student where stu_class='"+Trans2eng()+"'";
            jTable = jdbcHelper.scrollpanelsocrce(sql);
            jTableHeader = jTable.getTableHeader();
            spstu.getViewport().add(jTable);
        }else if(e.getActionCommand().equals("添加成绩")||e.getActionCommand().equals("修改成绩")){
            int rows = jTable.getSelectedRow();
            try{
                new AddScore(jTable.getValueAt(rows, 0).toString());
            }catch (Exception nice){
                JOptionPane.showMessageDialog(null, "请选择学生信息", "提示", JOptionPane.WARNING_MESSAGE);
                //nice.printStackTrace();
            }
        }
    }
}