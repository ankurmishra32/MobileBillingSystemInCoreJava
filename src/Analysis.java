import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

class Analysis extends JFrame implements ActionListener
{
    long mn;
    Statement s;
    Connection c;
    JComboBox c1;
    JButton b1,b2;
    DateFormat df;
    ResultSet r,rs;
    java.util.Date date;
    static String sc,sm;
    JTextField tt,t1,t2,t3,t4,t5,t6;
    JLabel ll,lt,l1,l2,l3,l4,l5,l6,l7;

    public Analysis()
    {
        setLayout(null);
        ll=new JLabel("Mobile Billing System");
        ll.setFont(new Font("TimesRoman",Font.BOLD,24));
        ll.setBounds(280,40,240,40);
        add(ll);
        lt=new JLabel("Mobile No:");
        lt.setBounds(250,100,75,30);
        add(lt);
        c1=new JComboBox();
        c1.setBounds(350,100,200,30);
        add(c1);
        c1.addActionListener(this);
        l1=new JLabel("Total Local Call:");
        l1.setBounds(100,250,100,30);
        add(l1);
        t1=new JTextField(10);
        t1.setBounds(200,250,100,30);
        add(t1);
        t1.setEnabled(false);
        l2=new JLabel("Total STD Call:");
        l2.setBounds(100,300,100,30);
        add(l2);
        t2=new JTextField(10);
        t2.setBounds(200,300,100,30);
        add(t2);
        t2.setEnabled(false);
        l3=new JLabel("Total ISD Call:");
        l3.setBounds(100,350,100,30);
        add(l3);
        t3=new JTextField(10);
        t3.setBounds(200,350,100,30);
        add(t3);
        t3.setEnabled(false);
        l4=new JLabel("Advanced Payment:");
        l4.setBounds(425,250,125,30);
        add(l4);
        t4=new JTextField(10);
        t4.setBounds(550,250,100,30);
        add(t4);
        t4.setEnabled(false);
        l5=new JLabel("Remaining Payment:");
        l5.setBounds(425,300,125,30);
        add(l5);
        t5=new JTextField(10);
        t5.setBounds(550,300,100,30);
        add(t5);
        t5.setEnabled(false);
        l6=new JLabel("Bill to Pay:");
        l6.setBounds(450,350,100,30);
        add(l6);
        t6=new JTextField(10);
        t6.setBounds(550,350,100,30);
        add(t6);
        t6.setEnabled(false);
        b1=new JButton("PAY");
        b1.setBounds(200,450,100,40);
        add(b1);
        b1.addActionListener(this);
        b2=new JButton("BACK");
        b2.setBounds(500,450,100,40);
        add(b2);
        b2.addActionListener(this);
        date=new java.util.Date();
        df=DateFormat.getDateInstance(DateFormat.FULL,Locale.UK);
        sc=df.format(date);
        l7=new JLabel(sc);
        l7.setBounds(500,525,200,25);
        add(l7);

        try
        {
            connect();
            rs=s.executeQuery("select Numbers from Connections");
            while(rs.next())
                c1.addItem(""+rs.getLong("Numbers"));
        }
        catch(Exception ee)
        {System.out.println("error : " +ee);}

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==c1)
        {
            try
            {
                connect();
                sm=c1.getSelectedItem().toString();
                r=s.executeQuery("select * from MobileBill where Numbers='"+sm+"'");
                r.next();
                t1.setText(r.getString("Local"));
                t2.setText(r.getString("STD"));
                t3.setText(r.getString("ISD"));
                t4.setText(r.getString("Advanced"));
                t5.setText(r.getString("Remaining"));
                t6.setText(r.getString("Bill"));
            }
            catch(Exception ee)
            {
                System.out.println("error : " +ee);
            }
        }
        if(e.getSource()==b1)
        {
            Payment p1=new Payment();
            p1.setTitle("Mobile Billing System");
            p1.setSize(800,600);
            p1.setVisible(true);
            p1.setResizable(false);
            setVisible(false);
        }
        if(e.getSource()==b2)
        {
            mobile m=new mobile();
            m.setTitle("Mobile Billing System");
            m.setSize(800,600);
            m.setVisible(true);
            m.setResizable(false);
            setVisible(false);
        }
    }

    void connect()
    { 
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            c=DriverManager.getConnection("Jdbc:Odbc:Mobile");
            s=c.createStatement();
        }
        catch(Exception ee)
        {
            System.out.println("error : " +ee);
        }
    }
}