package com.jason.model;

import com.jason.dao.JDBCHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deletstuinfo extends JFrame implements ActionListener {
    private JButton right,no;
    private JLabel tip;
    private JPanel but,lab;
    private JDBCHelper jdbcHelper;
    private String arg;
    public Deletstuinfo(String oldid){
        arg = oldid;
        init();
        setChar();
        setLayout(new GridLayout(2,1));
        this.setTitle("学籍管理");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300,200);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        setLocation(780,720);
        lab.add(tip);
        add(lab);
        but.add(right);
        but.add(no);
        add(but);
    }
    private void init(){
        lab= new JPanel();
        jdbcHelper = new JDBCHelper();
        but = new JPanel();
        right = new JButton("是");
        no = new JButton("否");
        tip = new JLabel("确认删除这条信息？");
        right.addActionListener(this);
        no.addActionListener(this);
    }
    private void setChar(){
        Font fontlab = new Font("Arial",Font.BOLD,25);
        Font fontbut = new Font("Times New Roman",Font.PLAIN,22);
        right.setFont(fontbut);
        no.setFont(fontbut);
        tip.setFont(fontlab);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("是")){
            jdbcHelper.ConnecttoSQL();
            jdbcHelper.deletstuinfo(arg);
            dispose();
        }else if(e.getActionCommand().equals("否")){
            dispose();
        }
    }
}