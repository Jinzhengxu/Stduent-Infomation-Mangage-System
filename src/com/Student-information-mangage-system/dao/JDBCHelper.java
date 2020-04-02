package com.jason.dao;
import com.mysql.cj.xdevapi.SqlDataResult;

import javax.swing.*;
import java.math.BigInteger;
import java.sql.*;
import java.util.Vector;

public class JDBCHelper {
    //JDBC driver and database URL
    private static boolean Successfulsign;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/info";

    //database user name and password
    static final String USER = "teacher";
    static final String PASS = "teacher";

    static String username;
    static String userpass;
    static String[] title = {"学号","姓名","性别","班级"};
    static String[] title2 ={"学号","姓名","高等数学","线性代数","C程序语言设计","Java面向对象程序设计","操作系统","编译原理","计算机网络","数据结构"};


    public static String getUsername() {
        return username;
    }

    public static String getUserpass() {
        return userpass;
    }

    static PreparedStatement ps = null;
    static Connection con = null;
    static ResultSet rs = null;

    public void ConnecttoSQL() {
        try {
            //enroll the JDBC driver
            Class.forName(JDBC_DRIVER);
            // open URL;
            con = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sqlquery(String s, String s2, int x, int y) {
        //System.out.print(s);
        try {
            ps = con.prepareStatement(s + s2);
            rs = ps.executeQuery();
            if (rs.next()) {
                username = rs.getString(x);
                userpass = rs.getString(y);
                System.out.print("Successfully get user and password from sql");
                System.out.print(username + "\t" + userpass + "\t");
                Successfulsign = true;
            }else{
                Successfulsign = false;
            }

        } catch (SQLException e1) {
            //e1.printStackTrace();
        }
    }

    public static boolean isSuccessfulsign() {
        return Successfulsign;
    }

    public static JTable scrollpanel(String sql) {
        JTable ret = null;
        ResultSetMetaData rsmd;
        Vector colum = new Vector();
        Vector rows = new Vector();
        try {
            //String sql = "select * from student";
            rs = ps.executeQuery(sql);
            rsmd = rs.getMetaData();
            for(int i=0;i<title.length;i++){
                colum.addElement(title[i]);
            }
            while(rs.next()) {
                Vector currow = new Vector();
                currow.addElement(rs.getString(1));
                currow.addElement(rs.getString(2));
                currow.addElement(rs.getString(4));
                String engclassname = rs.getString(5);
                currow.addElement(Tans2cn(engclassname));
                rows.addElement(currow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ret = new JTable(rows,colum);
        return ret;
    }

    public static JTable scrollpanelsocrce(String sql) {

        JTable ret ;
        Vector colum = new Vector();
        Vector rows = new Vector();
        try {
            rs = ps.executeQuery(sql);
            for(int i=0;i<title2.length;i++){
                colum.addElement(title2[i]);
            }
            while(rs.next()) {
                Vector currow = new Vector();
                currow.addElement(rs.getString(1));
                currow.addElement(rs.getString(2));
                currow.addElement(rs.getString(6));
                currow.addElement(rs.getString(7));
                currow.addElement(rs.getString(8));
                currow.addElement(rs.getString(9));
                currow.addElement(rs.getString(10));
                currow.addElement(rs.getString(11));
                currow.addElement(rs.getString(12));
                currow.addElement(rs.getString(13));
                rows.addElement(currow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ret = new JTable(rows,colum);
        return ret;
    }

    public static JTable scrollpanelsocrcestat(String sql) {
        int[] score=new int[8];
        int count=0;
        JTable ret ;
        Vector colum = new Vector();
        Vector rows = new Vector();
        try {
            rs = ps.executeQuery(sql);
            for(int i=0;i<title2.length;i++){
                colum.addElement(title2[i]);
            }
            while(rs.next()) {
                count++;
                Vector currow = new Vector();
                currow.addElement(rs.getString(1));
                currow.addElement(rs.getString(2));
                currow.addElement(rs.getString(6));
                score[0]+=Integer.parseInt(rs.getString(6));
                currow.addElement(rs.getString(7));
                score[1]+=Integer.parseInt(rs.getString(7));
                currow.addElement(rs.getString(8));
                score[2]+=Integer.parseInt(rs.getString(8));
                currow.addElement(rs.getString(9));
                score[3]+=Integer.parseInt(rs.getString(9));
                currow.addElement(rs.getString(10));
                score[4]+=Integer.parseInt(rs.getString(10));
                currow.addElement(rs.getString(11));
                score[5]+=Integer.parseInt(rs.getString(11));
                currow.addElement(rs.getString(12));
                score[6]+=Integer.parseInt(rs.getString(12));
                currow.addElement(rs.getString(13));
                score[7]+=Integer.parseInt(rs.getString(13));
                rows.addElement(currow);
            }
            Vector curr = new Vector();
            curr.addElement("平均成绩");
            curr.addElement("");
            for(int i=0;i<7;i++){
                score[i] /= count;
                curr.addElement(score[i]);
            }
            rows.addElement(curr);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "请补全学生信息", "提示", JOptionPane.WARNING_MESSAGE);
        }
        ret = new JTable(rows,colum);
        return ret;
    }

    private static String Tans2cn(String engname) {
        String cnname;
        String engclassname;
        if (engname.equals("CS"))
            cnname = "计算机科学与技术";
        else if (engname.equals("NP"))
            cnname = "网络工程";
        else if (engname.equals("DMT"))
            cnname = "数字媒体技术";
        else if (engname.equals("FB"))
            cnname = "金融大数据";
        else if (engname.equals("FI"))
            cnname = "金融信息化";
        else if (engname.equals("M"))
            cnname = "男";
        else if (engname.equals("F"))
            cnname = "女";
        else
            cnname = engname;

        return cnname;
    }

    public static void addstuinfo(String stu_name,String stu_sex,String stu_id,String stu_class){
        try {
            ps = con.prepareStatement("insert into student values(?,?,?,?,?)");
            ps.setString(1,stu_id);
            ps.setString(2,stu_name);
            ps.setString(3,stu_name);
            ps.setString(4,stu_sex);
            ps.setString(5,stu_class);
            ps.executeUpdate();
            System.out.print("insert have");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void updatestu(String oldid,String stu_name,String stu_sex,String stu_id,String stu_class){
        try{
            //ps = con.prepareStatement("update student set stu_id=?,stu_sex=?,stu_pass=?,stu_naem=?,stu_class=? where stu_id=?");
            ps = con.prepareStatement("update student set stu_sex=?,stu_name=?,stu_class=?,stu_pass=?,stu_id=? where stu_id=?");
            ps.setString(1,stu_sex);
            ps.setString(2,stu_name);
            ps.setString(3,stu_class);
            ps.setString(4,stu_name);
            ps.setString(5,stu_id);
            ps.setString(6,oldid);
            ps.executeUpdate();
            //ps.setString(2,stu_sex);
            //ps.setString(3,stu_name);
            //ps.setString(4,stu_name);
            //ps.setString(5,stu_class);
            //ps.setString(6,oldid);
            //ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deletstuinfo(String oldid){
        try{
            ps = con.prepareStatement("delete from student where stu_id=?");
            ps.setString(1,oldid);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"删除成功","提示",JOptionPane.WARNING_MESSAGE);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void UpdatePass(String s,String signid,String newpass){
        String sql;
        if(s.equals("admin")){
            sql="UPDATE "+s+" SET "+s+"_pass=? WHERE "+s+"_id=?";
        }else {
            sql = "UPDATE " + "student" + " SET " + s + "_pass=? WHERE " + s + "_id=?";
        }
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,newpass);
            ps.setString(2,signid);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.WARNING_MESSAGE);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void Updatescore(String s,int[] a){
        try{
            String sql="UPDATE student SET CALCU="+a[0]+",LA="+a[1]+",CPL="+a[2]+",JPL="+a[3]
                    +",OS="+a[4]+",CPTT="+a[5]+",CN="+a[6]+",DS="+a[7]+" WHERE stu_id="+s;
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
           // JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.WARNING_MESSAGE);
        }catch (SQLException e){
          //  e.printStackTrace();
        }
    }
}