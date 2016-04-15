import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

class enbill extends JFrame implements ActionListener
{
    long mn;
    Statement s;
    Connection c;
    DateFormat df;
    JButton b1,b2;
    ResultSet r,rs;
    java.util.Date date;
    String sc,sm,sl,ss,si,sb;
    JComboBox c1,c2,c3,c4,c5;
    JLabel ll,l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    public enbill()
    {
        setLayout(null);
        ll=new JLabel("Mobile Billing System");
        ll.setFont(new Font("TimesRoman",Font.BOLD,24));
        ll.setBounds(280,40,240,40);
        add(ll);
        l1=new JLabel("Mobile No.:");
        l1.setBounds(250,100,100,30);
        add(l1);
        c1=new JComboBox();
        c1.setBounds(350,100,200,30);
        add(c1);
        c1.addActionListener(this);
        l2=new JLabel("Old Reading:");
        l2.setBounds(200,200,150,30);
        add(l2);
        l3=new JLabel("New Reading:");
        l3.setBounds(450,200,150,30);
        add(l3);
        l4=new JLabel("Local Calls:");
        l4.setBounds(50,250,150,30);
        add(l4);
        t2=new JTextField(10);
        t2.setBounds(200,250,150,30);
        add(t2);
        t2.setEnabled(false);
        t3=new JTextField(10);
        t3.setBounds(450,250,150,30);
        add(t3);
        l5=new JLabel("S.T.D Calls:");
        l5.setBounds(50,300,150,30);
        add(l5);
        t4=new JTextField(10);
        t4.setBounds(200,300,150,30);
        add(t4);
        t4.setEnabled(false);
        t5=new JTextField(10);
        t5.setBounds(450,300,150,30);
        add(t5);
        l6=new JLabel("I.S.D Calls:");
        l6.setBounds(50,350,150,30);
        add(l6);
        t6=new JTextField(10);
        t6.setBounds(200,350,150,30);
        add(t6);
        t6.setEnabled(false);
        t7=new JTextField(10);
        t7.setBounds(450,350,150,30);
        add(t7);
        b1=new JButton("OK");
        b1.setBounds(250,450,100,40);
        add(b1);
        b1.addActionListener(this);
        b2=new JButton("BACK");
        b2.setBounds(450,450,100,40);
        add(b2);
        b2.addActionListener(this);
        date=new java.util.Date();
        df=DateFormat.getDateInstance(DateFormat.FULL,Locale.US);
        sc=df.format(date);
        l7=new JLabel(sc);
        l7.setBounds(500,525,200,25);	add(l7);
        try
        {
            connect();
            rs=s.executeQuery("select Numbers from Connections");
            while(rs.next())
                c1.addItem(""+rs.getLong("Numbers"));
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
        if(e.getSource()==c1)
        {
            try
            {
                sm=c1.getSelectedItem().toString();
                connect();
                r=s.executeQuery("select * from MobileBill where Numbers='"+sm+"'");
                r.next();
                t2.setText(r.getString("Local"));
                t4.setText(r.getString("STD"));
                t6.setText(r.getString("ISD"));
            }
            catch(Exception ee)
            {
                System.out.println("error : " +ee);
            }
        }
        if(e.getSource()==b1)
        {
            if(t3.getText().equals("")||t5.getText().equals("")||t7.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Enter Complete Bill","OOPS!!!",JOptionPane.WARNING_MESSAGE);
                return;
            }
            try
            {
                connect();
                r=s.executeQuery("select * from MobileBill where Numbers='"+sm+"'");
                r.next();
                sl=t3.getText();
                ss=t5.getText();
                si=t7.getText();
                sb=String.valueOf(((Integer.parseInt(sl)-Integer.parseInt(r.getString("Local")))*1)+((Integer.parseInt(ss)-Integer.parseInt(r.getString("STD")))*2)+((Integer.parseInt(si)-Integer.parseInt(r.getString("ISD")))*5));
                s.executeUpdate("update MobileBill set Local='"+sl+"',STD='"+ss+"',ISD='"+si+"',Bill='"+sb+"' where Numbers='"+sm+"'");
                JOptionPane.showMessageDialog(this,"Bill is Entered","DONE!!! ",JOptionPane.INFORMATION_MESSAGE);
                enbill e2=new enbill();
                e2.setTitle("TO Enter Bill Detail.");
                e2.setSize(800,600);
                e2.setVisible(true);
                e2.setResizable(false);
                setVisible(false);
            }
            catch(Exception ee)
            {
                System.out.println("error : "+ee);
            }
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
        {System.out.println("error : " +ee);}
    }
}