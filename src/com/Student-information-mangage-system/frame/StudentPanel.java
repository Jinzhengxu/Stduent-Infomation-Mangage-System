package com.jason.frame;

import com.jason.model.GPAPanel;
import com.jason.model.ProfileControl;
import com.jason.model.lessonControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentPanel extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu lessoncontrol, profile, GPAcontrol;
    private JMenuItem serachGPA,statGPA;
    private JMenuItem lessoncontrolitem, Personalcontrolitem,SignOut;
    private Font font;
    private ImageIcon img;
    private JLabel icon;
    private String signid;

    public StudentPanel(String s) {
        signid =s;
        init();
        setChar();
        this.setTitle("学生信息管理系统");
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1270,800);
        this.setResizable(false);
        setLocation(600,200);

        lessoncontrol.add(lessoncontrolitem);
        GPAcontrol.add(serachGPA);
        GPAcontrol.add(statGPA);
        profile.add(Personalcontrolitem);
        menuBar.add(lessoncontrol);
        menuBar.add(GPAcontrol);
        menuBar.add(profile);
        this.add(menuBar,BorderLayout.NORTH);
        this.add(icon,BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("课程管理")){
            new lessonControl();
        }else if(e.getActionCommand().equals("个人中心")){
            new ProfileControl(signid,"stu");
        }else if(e.getActionCommand().equals("查询成绩")){
            new GPAPanel();
        }else if(e.getActionCommand().equals("成绩统计")){
            new GPAPanel();
        }else if(e.getActionCommand().equals("退出系统")){
            dispose();
        }

    }

    private void init(){
        java.net.URL imgURL = TeacherPanel.class.getResource("/com/jason/img/qq_pic_merged_1542552134500.jpg");
        java.awt.Font font = new java.awt.Font("宋体",Font.PLAIN,43);
        icon=new JLabel();
        img = new ImageIcon(imgURL);
        icon.setIcon(img);
        menuBar = new JMenuBar();
        lessoncontrol = new JMenu("课程管理");
        profile = new JMenu("个人中心");
        GPAcontrol = new JMenu("成绩管理");
        serachGPA = new JMenuItem("查询成绩");
        statGPA = new JMenuItem("成绩统计");
        lessoncontrolitem = new JMenuItem("课程管理");
        Personalcontrolitem = new JMenuItem("个人中心");
        SignOut = new JMenuItem("退出系统");
        serachGPA.addActionListener(this);
        statGPA.addActionListener(this);
        lessoncontrolitem.addActionListener(this);
        Personalcontrolitem.addActionListener(this);
        SignOut.addActionListener(this);
    }

    private void setChar(){
        lessoncontrol.setFont(font);
        profile.setFont(font);
        GPAcontrol.setFont(font);
        serachGPA.setFont(font);
        statGPA.setFont(font);
        lessoncontrolitem.setFont(font);
        Personalcontrolitem.setFont(font);
    }
}