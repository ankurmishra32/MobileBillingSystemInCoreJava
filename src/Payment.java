import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

class Payment extends JFrame implements ActionListener
{
    long mn;
    Statement s;
    ResultSet r;
    Connection c;
    JButton b1,b2;
    DateFormat df;
    java.util.Date date;
    String sa,sb,sc,sp,sr;
    JTextField tt,t1,t2,t3,t4;
    JLabel ll,lt,lm,l1,l2,l3,l4,l5;
    public Payment()
    {
        setLayout(null);
        ll=new JLabel("Mobile Billing System");
        ll.setFont(new Font("TimesRoman",Font.BOLD,24));
        ll.setBounds(280,40,240,40);
        add(ll);
        lt=new JLabel("Mobile No:");
        lt.setBounds(325,100,75,30);
        add(lt);
        lm=new JLabel(Analysis.sm);
        lm.setBounds(400,100,100,30);
        add(lm);
        l1=new JLabel("Bill to Pay:");
        l1.setBounds(100,250,100,30);
        add(l1);
        t1=new JTextField(10);
        t1.setBounds(200,250,100,30);
        add(t1);
        t1.setEnabled(false);
        l2=new JLabel("Amount Payed:");
        l2.setBounds(100,300,100,30);
        add(l2);
        t2=new JTextField(10);
        t2.setBounds(200,300,100,30);
        add(t2);
        l3=new JLabel("Advanced Payment:");
        l3.setBounds(425,250,125,30);
        add(l3);
        t3=new JTextField(10);
        t3.setBounds(550,250,100,30);
        add(t3);
        t3.setEnabled(false);
        l4=new JLabel("Remaining Payment:");
        l4.setBounds(425,300,125,30);
        add(l4);
        t4=new JTextField(10);
        t4.setBounds(550,300,100,30);
        add(t4);
        t4.setEnabled(false);
        b1=new JButton("Submit");
        b1.setBounds(200,450,100,40);
        add(b1);
        b1.addActionListener(this);
        b2=new JButton("BACK");
        b2.setBounds(500,450,100,40);
        add(b2);
        b2.addActionListener(this);
        date=new java.util.Date();
        df=DateFormat.getDateInstance(DateFormat.FULL,Locale.US);
        sc=df.format(date);
        l5=new JLabel(sc);
        l5.setBounds(500,525,200,25);	add(l5);
        try
        {
            connect();
            r=s.executeQuery("select * from MobileBill where Numbers='"+Analysis.sm+"'");
            r.next();
            t1.setText(r.getString("Bill"));
            t3.setText(r.getString("Advanced"));
            t4.setText(r.getString("Remaining"));
        }
        catch(Exception ee)
        {
            System.out.println("error : " +ee);
        }
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
        if(e.getSource()==b1)
        {
            sb=t1.getText();
            sp=t2.getText();
            if(Integer.parseInt(sb)<Integer.parseInt(sp))
            {
                sa=String.valueOf(Integer.parseInt(sp)-Integer.parseInt(sb));
                sr="0";
            }
            if(Integer.parseInt(sp)<Integer.parseInt(sb))
            {
                sa="0";
                sr=String.valueOf(Integer.parseInt(sb)-Integer.parseInt(sp));
            }
            if(Integer.parseInt(sb)==Integer.parseInt(sp))
            {
                sa="0";
                sr="0";
            }
            try
            {
                connect();
                r=s.executeQuery("select * from MobileBill where Numbers='"+Analysis.sm+"'");
                r.next();
                sa=String.valueOf(Integer.parseInt(sa)+Integer.parseInt(r.getString("Advanced")));
                sr=String.valueOf(Integer.parseInt(sr)+Integer.parseInt(r.getString("Remaining")));
                if(Integer.parseInt(sr)<Integer.parseInt(sa))
                {
                    sa=String.valueOf(Integer.parseInt(sa)-Integer.parseInt(sr));
                    sr="0";
                }
                if(Integer.parseInt(sa)<Integer.parseInt(sr))
                {
                    sr=String.valueOf(Integer.parseInt(sr)-Integer.parseInt(sa));
                    sa="0";
                }
                s.executeUpdate("update MobileBill set Bill='0',Advanced='"+sa+"',Remaining='"+sr+"' where Numbers='"+Analysis.sm+"'");
                JOptionPane.showMessageDialog(this,"Payed Successfully","DONE!!! ",JOptionPane.INFORMATION_MESSAGE);
                Analysis a2=new Analysis();
                a2.setTitle("To Check Mobile Bill.");
                a2.setSize(800,600);
                a2.setVisible(true);
                a2.setResizable(false);
                setVisible(false);
            }
            catch(Exception ee)
            {
                System.out.println("error : " +ee);
            }
        }
        if(e.getSource()==b2)
        {
            Analysis a2=new Analysis();
            a2.setTitle("To Check Mobile Bill.");
            a2.setSize(800,600);
            a2.setVisible(true);
            a2.setResizable(false);
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