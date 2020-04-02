package com.jason.model;

import com.jason.dao.JDBCHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Updatestu extends JFrame implements ActionListener {
    private JLabel stu_id,stu_name,stu_class,stu_sex;
    private JTextField getstu_id,getstu_name;
    private JComboBox getstu_class,getstu_sex;
    private JButton add,reset;
    private JPanel p1,p2,p3,p4,p5;
    private JDBCHelper jdbcHelper;
    private String cardid;

    public Updatestu(String stuid,String oldname) {
        cardid=stuid;
        init();
        setChar();
        this.setTitle("修改学籍");
        this.setLayout(new GridLayout(5,1));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 400);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        setLocation(1560, 320);
        getstu_id.setText(cardid);
        getstu_name.setText(oldname);
        p1.add(stu_id);p1.add(getstu_id);add(p1);
        p2.add(stu_name);p2.add(getstu_name);add(p2);
        p3.add(stu_sex);p3.add(getstu_sex);add(p3);
        p4.add(stu_class);p4.add(getstu_class);add(p4);
        p5.add(add);p5.add(reset);add(p5);
    }

    public void init() {
        jdbcHelper = new JDBCHelper();
        stu_id= new JLabel("学号");
        stu_name = new JLabel("姓名");
        stu_class = new JLabel("班级");
        stu_sex = new JLabel("性别");
        getstu_id = new JTextField(10);
        getstu_name =new JTextField(10);
        getstu_class = new JComboBox();
        getstu_sex = new JComboBox();
        getstu_class.addItem("计算机科学与技术");
        getstu_class.addItem("网络工程");
        getstu_class.addItem("数字媒体技术");
        getstu_class.addItem("金融大数据");
        getstu_class.addItem("金融信息化");
        getstu_sex.addItem("男");
        getstu_sex.addItem("女");
        add = new JButton("确认修改");
        reset = new JButton("清空重置");
        add.addActionListener(this);
        reset.addActionListener(this);
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        p4=new JPanel();
        p5=new JPanel();

    }
    private void setChar(){
        Font fontlab = new Font("Arial",Font.BOLD,18);
        Font fontbut = new Font("Times New Roman",Font.PLAIN,18);
        add.setFont(fontbut);
        reset.setFont(fontbut);
        stu_sex.setFont(fontlab);
        stu_id.setFont(fontlab);
        stu_name.setFont(fontlab);
        stu_class.setFont(fontlab);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("确认修改")){
            jdbcHelper.ConnecttoSQL();
            String id = getstu_id.getText();
            String name = getstu_name.getText();
            String s = getstu_class.getSelectedItem().toString();
            String classname = Trans2eng(s);
            String sex = Trans2eng(getstu_sex.getSelectedItem().toString());
            jdbcHelper.updatestu(cardid,name,sex,id,classname);
            JOptionPane.showMessageDialog(null,"修改信息成功","提示",JOptionPane.WARNING_MESSAGE);
            dispose();
        }else if(e.getActionCommand().equals("清空重置")){
            getstu_id.setText("");
            getstu_name.setText("");
        }
    }
    private String Trans2eng(String s){
        String classname = s;
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
        }else if(classname.equals("男")){
            engclassname = "M";
        }else if(classname.equals("女")){
            engclassname = "F";
        }else {
            engclassname= "CS";
        }
        return engclassname;
    }

}