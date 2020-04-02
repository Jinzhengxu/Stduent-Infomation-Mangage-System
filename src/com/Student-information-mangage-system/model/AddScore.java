package com.jason.model;

import com.jason.dao.JDBCHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddScore extends JFrame implements ActionListener{
    private JLabel idlab,id,namelab,name;
    private JLabel CALCU,LA,CPL,JPL,OS,CPTT,CN,DS;
    private JTextField getCALCU,getLA,getCPL,getJPL,getOS,getCPTT,getCN,getDS;
    private JPanel butp,namep,idp,pCALCU,pLA,pCPL,pJPL,pOS,pCPTT,pCN,pDS;
    private JButton right,back;
    private String oldid,oldname;
    private JDBCHelper jdbcHelper;
    public AddScore(String s){
        jdbcHelper = new JDBCHelper();
        jdbcHelper.ConnecttoSQL();
        oldid = s;
        jdbcHelper.sqlquery("SELECT * FROM student WHERE stu_id=",oldid,2,3);
        oldname = jdbcHelper.getUsername().toString();
        init();
        this.setTitle("修改成绩");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,800);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLayout(new GridLayout(11,1));
        setLocation(680,320);
        namep.add(namelab);namep.add(name);
        idp.add(idlab);idp.add(id);
        pCALCU.add(CALCU);pCALCU.add(getCALCU);
        pLA.add(LA);pLA.add(getLA);
        pCPL.add(CPL);pCPL.add(getCPL);
        pJPL.add(JPL);pJPL.add(getJPL);
        pOS.add(OS);pOS.add(getOS);
        pCPTT.add(CPTT);pCPTT.add(getCPTT);
        pCN.add(CN);pCN.add(getCN);
        pDS.add(DS);pDS.add(getDS);
        butp.add(right);butp.add(back);
        add(namep);add(idp);add(pCALCU);add(pLA);add(pCPL);add(pJPL);add(pOS);add(pCPTT);add(pCN);add(pDS);add(butp);
    }
    private void init(){
        idlab = new JLabel("学号: ");
        id = new JLabel(oldid);
        namelab= new JLabel("姓名: ");
        name=new JLabel(oldname);
        CALCU=new JLabel("高等数学");
        LA=new JLabel("线性代数");
        CPL=new JLabel("C语言程序设计");
        JPL=new JLabel("Java面向对象程序设计");
        OS=new JLabel("操作系统");
        CPTT=new JLabel("编译原理");
        CN=new JLabel("计算机网络");
        DS=new JLabel("数据结构");
        getCALCU=new JTextField(10);
        getLA=new JTextField(10);
        getCPL=new JTextField(10);
        getJPL=new JTextField(10);
        getOS=new JTextField(10);
        getCPTT=new JTextField(10);
        getCN=new JTextField(10);
        getDS=new JTextField(10);
        right = new JButton("确认修改");
        back = new JButton("放弃修改");
        namep=new JPanel();
        idp=new JPanel();
        pCALCU=new JPanel();
        pLA=new JPanel();
        pCPL=new JPanel();
        pJPL=new JPanel();
        pOS=new JPanel();
        pCPTT=new JPanel();
        pCN=new JPanel();
        pDS=new JPanel();
        butp = new JPanel();
        right.addActionListener(this);
        back.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("确认修改")){
            int[] score = new int[8];
            score[0]=Integer.parseInt(getCALCU.getText());
            score[1]=Integer.parseInt(getLA.getText());
            score[2]=Integer.parseInt(getCPL.getText());
            score[3]=Integer.parseInt(getJPL.getText());
            score[4]=Integer.parseInt(getOS.getText());
            score[5]=Integer.parseInt(getCPTT.getText());
            score[6]=Integer.parseInt(getCN.getText());
            score[7]=Integer.parseInt(getDS.getText());
            System.out.println("zhixing yu ci");
            jdbcHelper.Updatescore(oldid,score);
            dispose();
        }else if(e.getActionCommand().equals("放弃修改")){
            dispose();
        }
    }
}