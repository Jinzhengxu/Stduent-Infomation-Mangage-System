package com.jason.model;

import com.jason.dao.JDBCHelper;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JFrame implements ActionListener{
    private JLabel serchlab;
    private JTableHeader jTableHeader;
    private JTable jTable;
    private JButton addstu,updatestu,deletstu,serachstu;
    private JPanel updatepanel,initpanel,head,body,foot;
    private JScrollPane spstu;
    private JComboBox classselect;
    private JDBCHelper jdbcHelper;
    private String[] title = {"学号","姓名","年龄","性别"};

    public ControlPanel(){
        init();
        setChar();
        this.setTitle("学籍管理");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,800);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        setLocation(680,320);
        this.spstu.getViewport().add(jTable);

        jdbcHelper.ConnecttoSQL();
        jTable=jdbcHelper.scrollpanel("select * from student");
        //jTable=jdbcHelper.classinfo("计算机科学与技术");
        jTableHeader = jTable.getTableHeader();
        spstu.getViewport().add(jTable);
        initpanel.setLayout(new BorderLayout());
        head.setLayout(new BorderLayout());
        head.add(serchlab,BorderLayout.WEST);
        head.add(classselect,BorderLayout.CENTER);
        head.add(serachstu,BorderLayout.EAST);
        initpanel.add(head,BorderLayout.NORTH);
        body.add(spstu);
        initpanel.add(body,BorderLayout.CENTER);
        foot.setLayout(new GridLayout(3,1));
        foot.add(addstu);
        foot.add(updatestu);
        foot.add(deletstu);
        initpanel.add(foot,BorderLayout.SOUTH);
        this.add(initpanel);

    }
    private void init(){

        jdbcHelper = new JDBCHelper();
        body = new JPanel();
        foot = new JPanel();
        head = new JPanel();
        initpanel = new JPanel();
        updatepanel = new JPanel();
        addstu = new JButton("添加学籍");
        updatestu = new JButton("修改学籍");
        deletstu = new JButton("删除学籍");
        serachstu = new JButton("查询");
        spstu = new JScrollPane();
        classselect = new JComboBox();
        setStu_class();
        serachstu.addActionListener(this);
        addstu.addActionListener(this);
        updatestu.addActionListener(this);
        deletstu.addActionListener(this);
        classselect.addActionListener(this);
        serchlab = new JLabel("查询班级学生  ");
        jTable = null;
        jTableHeader=null;

    }
    private void setStu_class(){
        classselect.addItem("计算机科学与技术");
        classselect.addItem("网络工程");
        classselect.addItem("数字媒体技术");
        classselect.addItem("金融大数据");
        classselect.addItem("金融信息化");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("查询")) {
            String sql ="select * from student where stu_class='"+Trans2eng()+"'";
            //jTable = jdbcHelper.classinfo(Trans2eng());
            jTable = jdbcHelper.scrollpanel(sql);
            jTableHeader = jTable.getTableHeader();
            spstu.getViewport().add(jTable);
        } else if (e.getActionCommand().equals("添加学籍")) {
            new Addstu(Trans2eng());
        } else if (e.getActionCommand().equals("修改学籍")) {
            int rows = jTable.getSelectedRow();
            try {
                new Updatestu(jTable.getValueAt(rows, 0).toString(), jTable.getValueAt(rows, 1).toString());
            } catch (Exception nice) {
                JOptionPane.showMessageDialog(null, "请选择学籍信息", "提示", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getActionCommand().equals("删除学籍")) {
            try {
                int rows = jTable.getSelectedRow();
                new Deletstuinfo(jTable.getValueAt(rows, 0).toString());
            } catch (Exception th) {
                JOptionPane.showMessageDialog(null, "请选择学籍信息", "提示", JOptionPane.WARNING_MESSAGE);
            }
        }
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
        addstu.setFont(fontbut);
        updatestu.setFont(fontbut);
        deletstu.setFont(fontbut);
        serachstu.setFont(fontbut);
        serchlab.setFont(fontlab);
    }
    public static void main(String[] args){
        new ControlPanel();
    }
}