package com.jason.model;

import com.jason.dao.JDBCHelper;
import com.jason.frame.TeacherPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileControl extends JFrame implements ActionListener {
    private JPanel body, pass, head, headleft, headright, foot, namepanel, idpanel;
    private JButton right, back;
    private JLabel namelab, naem, idlab, id, oldpasslab, newpasslab, icon;
    private ImageIcon image;
    private String signid, isteaorstu;
    private JDBCHelper jdbcHelper;
    private JTextField oldpass, newpass;

    public ProfileControl(String s, String s2) {
        isteaorstu = s2;
        signid = s;
        init();
        setChar();
        this.setTitle("个人中心");
        this.setLayout(new GridLayout(2, 1));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(640, 400);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        setLocation(1220, 720);

        idpanel.add(idlab);
        idpanel.add(id);
        headright.add(idpanel);
        namepanel.add(namelab);
        namepanel.add(naem);
        headright.add(namepanel);
        head.add(headright);
        headleft.add(icon);
        head.add(headleft);
        pass.add(oldpasslab);
        pass.add(oldpass);
        pass.add(newpasslab);
        pass.add(newpass);
        body.add(pass);
        foot.add(right);
        foot.add(back);
        body.add(foot);
        add(head);
        add(body);

    }

    public void init() {
        jdbcHelper = new JDBCHelper();
        jdbcHelper.ConnecttoSQL();
        if (isteaorstu.equals("tea")) {
            namelab = new JLabel("教师姓名: ");
            idlab = new JLabel("教工号: ");
            jdbcHelper.sqlquery("SELECT * FROM admin WHERE admin_id=", signid, 2, 3);
            naem = new JLabel(jdbcHelper.getUsername().toString());
            id = new JLabel(signid);
        } else {
            namelab = new JLabel("学生姓名: ");
            idlab = new JLabel("学号: ");
            jdbcHelper.sqlquery("SELECT * FROM student WHERE stu_id=", signid, 2, 3);
            naem = new JLabel(jdbcHelper.getUsername().toString());
            id = new JLabel(signid);
        }
        oldpass = new JTextField(20);
        newpass = new JTextField(20);
        oldpasslab = new JLabel("      旧密码:");
        newpasslab = new JLabel("      新密码:");
        namepanel = new JPanel();
        body = new JPanel(new GridLayout(2, 1));
        idpanel = new JPanel();
        headleft = new JPanel();
        headright = new JPanel();
        head = new JPanel(new GridLayout(1, 2));
        foot = new JPanel();
        pass = new JPanel(new GridLayout(2, 2));
        right = new JButton("确认修改");
        back = new JButton("退出修改");
        right.addActionListener(this);
        back.addActionListener(this);
        icon = new JLabel();
        java.net.URL imgURL = ProfileControl.class.getResource("/com/jason/img/download.jpg");
        image = new ImageIcon(imgURL);
        icon.setIcon(image);
    }

    public void setChar() {
        Font fontlab = new Font("Arial", Font.BOLD, 30);
        Font fontbut = new Font("Times New Roman", Font.PLAIN, 28);
        right.setFont(fontbut);
        back.setFont(fontbut);
        namelab.setFont(fontlab);
        naem.setFont(fontlab);
        idlab.setFont(fontlab);
        id.setFont(fontlab);
        oldpasslab.setFont(fontlab);
        newpasslab.setFont(fontlab);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("确认修改")) {
            if (oldpass.getText().isEmpty() || newpass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "无效操作", "提示", JOptionPane.ERROR_MESSAGE);
            } else {
                String sql;
                if (isteaorstu.equals("tea")) {
                    sql = "admin";
                } else {
                    sql = "stu";
                }
                jdbcHelper.sqlquery(sql, signid, 2, 3);
                if (oldpass.getText().equals(jdbcHelper.getUserpass())) {
                    String newpasswprd = newpass.getText().toString();
                    jdbcHelper.UpdatePass(sql, signid, newpasswprd);
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误", "提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("退出修改")) {
            dispose();
        }
    }
}