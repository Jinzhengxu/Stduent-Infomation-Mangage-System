package com.jason.frame;

import com.jason.dao.JDBCHelper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Enroll extends JFrame implements ActionListener {
    private JLabel userlab,passlab,power,numberid,back;
    private JButton sign_in,reset;
    private JRadioButton tea,stu;
    private JTextField username,userid;
    private JPasswordField password ;
    private JPanel all,head,up,med,down,last;
    private ImageIcon imageIcon;
    private Image image;
    private Color background;
    ButtonGroup bg;

    static JDBCHelper jdbcHelper;
    static String sqlusername;
    static String sqluserpass;

    static Connection con = null;
    PreparedStatement ps =null;
    ResultSet rs =null;

    public Enroll(){
        jdbcHelper = new JDBCHelper();
        init();

        bg=new ButtonGroup();
        bg.add(tea);
        bg.add(stu);
        stu.setSelected(true);

        head.add(numberid);
        head.add(userid);
        all.add(head);

        sign_in.addActionListener(this);
        reset.addActionListener(this);
        setChar();
        up.add(userlab);
        up.add(username);
        all.add(up);

        med.add(passlab);
        med.add(password);
        all.add(med);

        last.add(power);
        last.add(tea);
        last.add(stu);
        all.add(last);

        down.add(sign_in);
        down.add(reset);
        all.add(down);

        this.add(all);
        this.setTitle("登录系统");
        this.setSize(600,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
    private void init(){
        back = new JLabel(imageIcon);
        numberid = new JLabel("教工号/学号");
        power = new JLabel("选择");
        userlab =new JLabel("用户名");
        passlab = new JLabel("密码");

        tea = new JRadioButton("教师");
        stu = new JRadioButton("学生");

        sign_in = new JButton("登录");
        reset = new JButton("重置");

        userid = new JTextField(10);
        username = new JTextField(10);
        password = new JPasswordField(10);

        all = new JPanel(new GridLayout(5,1));
        head = new JPanel();
        up = new JPanel();
        med = new JPanel();
        last = new JPanel();
        down = new JPanel(new GridLayout(1,2));

        bg = new ButtonGroup();
    }
    private void setChar(){
        //java.net.URL imgURL = TeacherPanel.class.getResource("/com/jason/img/qq_pic_merged_1542550853587.jpg");
        //imageIcon = new ImageIcon(imgURL);
        background = null;
        up.setSize(20,60);
        username.setBounds(30,30,40,50);
        Font fontlab = new Font("Arial",Font.BOLD,30);
        Font fontbut = new Font("Times New Roman",Font.PLAIN,28);
        numberid.setFont(fontlab);
        power.setFont(fontlab);
        tea.setFont(fontbut);
        stu.setFont(fontbut);
        userlab.setFont(fontlab);
        passlab.setFont(fontlab);
        userid.setFont(fontbut);
        username.setFont(fontbut);
        password.setFont(fontbut);
        sign_in.setFont(fontbut);
        reset.setFont(fontbut);

        tea.setBackground(background);
        stu.setBackground(background);
        userlab.setBackground(background);
        passlab.setBackground(background);
        sign_in.setBackground(background);
        reset.setBackground(background);
        head.setBackground(background);
        up.setBackground(background);
        med.setBackground(background);
        down.setBackground(background);
        last.setBackground(background);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("登录")) {
            jdbcHelper.ConnecttoSQL();
            //如果选中教师登录
            if(tea.isSelected()) {
                tealogin();
            }else if(stu.isSelected()) {
                stulogin();
            }

        }else if(e.getActionCommand().equals("重置")) {
            clear();
        }
    }

    private void stulogin(){
        if(username.getText().isEmpty() && password.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please enter your count information","Tip",JOptionPane.WARNING_MESSAGE);
        }else if(username.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please enter your username","Tip",JOptionPane.WARNING_MESSAGE);
        }else if(password.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please enter your password","Tip",JOptionPane.WARNING_MESSAGE);
        }else{
            String s2 = userid.getText();
            jdbcHelper.sqlquery("SELECT * FROM student WHERE stu_id=",s2,2,3);
            if(jdbcHelper.isSuccessfulsign()) {
                //System.out.print("no bug hereeeeeeeeeeeeeeee");
                sqluserpass = jdbcHelper.getUserpass();
                sqlusername = jdbcHelper.getUsername();
                //System.out.print("no bug here");
                if (username.getText().equals(sqlusername) && sqluserpass.equals(password.getText())) {
                    System.out.print("Successfully Sign In");
                    JOptionPane.showMessageDialog(null, "Successfully Sign In", "Tip", JOptionPane.WARNING_MESSAGE);
                    clear();
                    dispose();
                    new StudentPanel(s2);
                }else {
                    JOptionPane.showMessageDialog(null, "Couldn't find you count\n Try again", "Tip", JOptionPane.ERROR_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Couldn't find you count\n Try again", "Tip", JOptionPane.ERROR_MESSAGE);
                clear();
            }
        }
    }

    private void tealogin() {
        if (username.getText().isEmpty() && password.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your admin count information", "Tip", JOptionPane.WARNING_MESSAGE);
        } else if (username.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your admin username", "Tip", JOptionPane.WARNING_MESSAGE);
        } else if (password.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your admin password", "Tip", JOptionPane.WARNING_MESSAGE);
        } else {
            String s2 = userid.getText();
            jdbcHelper.sqlquery("SELECT * FROM admin WHERE admin_id=", s2, 2, 3);

            if (jdbcHelper.isSuccessfulsign()) {
                sqluserpass = jdbcHelper.getUserpass();
                sqlusername = jdbcHelper.getUsername();
                if (sqlusername.equals(username.getText()) && sqluserpass.equals(password.getText())) {
                    //System.out.print("Successfully Sign In");
                    JOptionPane.showMessageDialog(null, "Successfully admin Sign In", "Tip", JOptionPane.WARNING_MESSAGE);
                    clear();
                    dispose();
                    new TeacherPanel(s2);
                }else {
                    JOptionPane.showMessageDialog(null, "Couldn't find you count\n Try again", "Tip", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Couldn't find you admin count\n Try again", "Tip", JOptionPane.ERROR_MESSAGE);
                clear();
            }
        }
    }
    private void clear(){
        userid.setText("");
        username.setText("");
        password.setText("");
    }
}