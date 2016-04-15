import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

class rmcon extends JFrame implements ActionListener
{
    long mn;
    Statement s;
    Connection c;
    JComboBox jc;
    JButton b1,b2;
    DateFormat df;
    ResultSet r,rs;
    String sc,ss,sm;
    java.util.Date date;
    JRadioButton r1,r2,r3,r4,r5,r6;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8;
    JLabel ll,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13;
    public rmcon()
    {
        setLayout(null);
        ll=new JLabel("Mobile Billing System");
        ll.setFont(new Font("TimesRoman",Font.BOLD,24));
        ll.setBounds(280,40,240,40);
        add(ll);
        l1=new JLabel("Name of Customer:");
        l1.setBounds(50,150,120,30);
        add(l1);
        t1=new JTextField(10);
        t1.setBounds(180,150,200,30);
        add(t1);
        t1.setEnabled(false);
        l2=new JLabel("Name of Father:");
        l2.setBounds(50,200,120,30);
        add(l2);
        t2=new JTextField(10);
        t2.setBounds(180,200,200,30);
        add(t2);
        t2.setEnabled(false);
        l3=new JLabel("Sex of Customer:");
        l3.setBounds(50,250,120,30);
        add(l3);
        r1=new JRadioButton("MALE");
        r1.setBounds(180,250,70,30);
        add(r1);
        r1.setEnabled(false);
        r2=new JRadioButton("FEMALE");
        r2.setBounds(250,250,80,30);
        add(r2);
        r2.setEnabled(false);
        l4=new JLabel("Date of Birth:");
        l4.setBounds(500,200,100,30);
        add(l4);
        t3=new JTextField(10);
        t3.setBounds(600,200,150,30);
        add(t3);
        t3.setEnabled(false);
        l5=new JLabel("E-mail Address:");
        l5.setBounds(50,300,120,30);
        add(l5);
        t4=new JTextField(10);
        t4.setBounds(180,300,200,30);
        add(t4);
        t4.setEnabled(false);
        l6=new JLabel("Profession:");
        l6.setBounds(50,350,100,30);
        add(l6);
        r3=new JRadioButton("Service");
        r3.setBounds(180,350,70,30);
        add(r3);
        r3.setEnabled(false);
        r4=new JRadioButton("Professional");
        r4.setBounds(250,350,100,30);
        add(r4);
        r4.setEnabled(false);
        r5=new JRadioButton("House Wife");
        r5.setBounds(350,350,100,30);
        add(r5);
        r5.setEnabled(false);
        r6=new JRadioButton("Student");
        r6.setBounds(450,350,70,30);
        add(r6);
        r6.setEnabled(false);
        l7=new JLabel("Permanent Address:");
        l7.setBounds(50,400,120,30);
        add(l7);
        t5=new JTextField(50);
        t5.setBounds(180,400,570,30);
        add(t5);
        t5.setEnabled(false);
        l9=new JLabel("PIN:");
        l9.setBounds(550,350,50,30);
        add(l9);
        t6=new JTextField(10);
        t6.setBounds(600,350,150,30);
        add(t6);
        t6.setEnabled(false);
        b1=new JButton("DELETE");
        b1.setBounds(200,475,150,40);
        add(b1);
        b1.addActionListener(this);
        b2=new JButton("BACK");
        b2.setBounds(450,475,150,40);
        add(b2);
        b2.addActionListener(this);
        l10=new JLabel("Mobile Number:");
        l10.setBounds(500,150,100,30);
        add(l10);
        jc=new JComboBox();
        jc.setBounds(600,150,150,30);
        add(jc);
        jc.addActionListener(this);
        date=new java.util.Date();
        df=DateFormat.getDateInstance(DateFormat.FULL,Locale.US);
        sc=df.format(date);
        l11=new JLabel(sc);
        l11.setBounds(500,525,200,25);
        add(l11);
        l12=new JLabel("ID Proof by:");
        l12.setBounds(500,250,100,30);
        add(l12);
        t7=new JTextField(10);
        t7.setBounds(600,250,150,30);
        add(t7);
        t7.setEnabled(false);
        l13=new JLabel("");
        l13.setBounds(500,300,100,30);
        add(l13);
        t8=new JTextField(10);
        t8.setBounds(600,300,150,30);
        add(t8);
        t8.setEnabled(false);
        try
        {
            connect();
            rs=s.executeQuery("select Numbers from Connections");
            while(rs.next())
                jc.addItem(""+rs.getLong("Numbers"));
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
        if(e.getSource()==jc)
        {
            try
            {
                connect();
                r=s.executeQuery("select * from Connections where Numbers='"+jc.getSelectedItem().toString()+"'");
                r.next();
                ss=r.getString("IDP");
                t1.setText(r.getString("Name"));
                t2.setText(r.getString("Father"));
                t3.setText(r.getString("Birth"));
                t4.setText(r.getString("Email"));
                t5.setText(r.getString("Address"));
                t6.setText(r.getString("Pin"));
                t7.setText(ss);
                t8.setText(r.getString("IDPN"));
                l13.setText(ss+" No.");
            }
            catch(Exception ee)
            {
                System.out.println("error : " +ee);
            }
        }
        if(e.getSource()==b1)
        {
            try
            {
                connect();
                r=s.executeQuery("select * from Connections");
                r.next();
                sm=jc.getSelectedItem().toString();
                s.executeUpdate("delete from Connections where Numbers='"+sm+"'");
                s.executeUpdate("update Avilable set Status='no' where Numbers='"+sm+"'");
                s.executeUpdate("update MobileBill set Local='0',STD='0',ISD='0' where Numbers='"+sm+"'");
                JOptionPane.showMessageDialog(this,"Mobile No. '"+sm+"' is Deleted","DONE!!! ",JOptionPane.INFORMATION_MESSAGE);
                rmcon r2=new rmcon();
                r2.setTitle("Remove Any Connection");
                r2.setSize(800,600);
                r2.setVisible(true);
                r2.setResizable(false);
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