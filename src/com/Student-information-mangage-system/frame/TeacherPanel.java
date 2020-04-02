package com.jason.frame;

import com.jason.dao.JDBCHelper;
import com.jason.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherPanel extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu stucontrol,GPAcontrol,profile;
    private JMenuItem addGPA, updateGPA, serachGPA, statGPA;
    private JMenuItem stucontrolitem, Signout,Personalcontrolitem;
    private Font font;
    private String signid;
    private ImageIcon img;
    private JLabel icon;

    public TeacherPanel(String s) {
        signid=s;
        init();
        setChar();
        this.setTitle("学生信息管理系统");
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300,1000);
        this.setResizable(false);
        //this.setAlwaysOnTop(true);
        setLocation(600,200);

        stucontrol.add(stucontrolitem);
        profile.add(Personalcontrolitem);
        GPAcontrol.add(addGPA);
        GPAcontrol.add(updateGPA);
        GPAcontrol.add(serachGPA);
        GPAcontrol.add(statGPA);
        menuBar.add(stucontrol);
        menuBar.add(GPAcontrol);
        menuBar.add(profile);
        menuBar.add(Signout);
        this.add(menuBar,BorderLayout.NORTH);
        this.add(icon,BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("学籍管理")){
            new ControlPanel();
        }else if(e.getActionCommand().equals("增加成绩")){
            new GPAControl();
        }else if(e.getActionCommand().equals("修改成绩")){
            new GPAControl();
        }else if(e.getActionCommand().equals("查询成绩")){
            new GPAControl();
        }else if(e.getActionCommand().equals("成绩统计")){
            new GPAstatic();
        }else if(e.getActionCommand().equals("个人中心")){
            new ProfileControl(signid,"tea");
        }else if(e.getActionCommand().equals("退出系统")){
            dispose();
        }
    }

    private void init(){
        java.net.URL imgURL = TeacherPanel.class.getResource("/com/jason/img/qq_pic_merged_1542550853587.jpg");
        java.awt.Font font = new java.awt.Font("宋体",Font.PLAIN,43);
        icon=new JLabel();
        img = new ImageIcon(imgURL);
        icon.setIcon(img);
        menuBar = new JMenuBar();
        stucontrol = new JMenu("学籍管理");
        GPAcontrol = new JMenu("成绩管理");
        addGPA = new JMenuItem("增加成绩");
        updateGPA = new JMenuItem("修改成绩");
        serachGPA = new JMenuItem("查询成绩");
        statGPA = new JMenuItem("成绩统计");
        Signout = new JMenuItem("退出系统");
        profile = new JMenu("个人中心");
        stucontrolitem = new JMenuItem("学籍管理");
        Personalcontrolitem = new JMenuItem("个人中心");

        GPAcontrol.addActionListener(this);
        addGPA.addActionListener(this);
        updateGPA.addActionListener(this);
        serachGPA.addActionListener(this);
        statGPA.addActionListener(this);
        Signout.addActionListener(this);
        stucontrolitem.addActionListener(this);
        Personalcontrolitem.addActionListener(this);
    }

    private void setChar(){
        GPAcontrol.setFont(font);
        addGPA.setFont(font);
        updateGPA.setFont(font);
        serachGPA.setFont(font);
        statGPA.setFont(font);
        stucontrolitem.setFont(font);
        Personalcontrolitem.setFont(font);
    }
}