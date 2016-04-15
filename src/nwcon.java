import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

class nwcon extends JFrame implements ActionListener
{
    Statement s;
    ResultSet r;
    Connection c;
    JButton b1,b2;
    DateFormat df;
    JComboBox jb,jc;
    java.util.Date date;
    JRadioButton r1,r2,r3,r4,r5,r6;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    String sc,sb,sm,sn,sf,ss,sd,se,sp,sa,pin,ipn;	
    JLabel ll,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13;	
    public nwcon()
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
        l2=new JLabel("Name of Father:");
        l2.setBounds(50,200,120,30);
        add(l2);
        t2=new JTextField(10);
        t2.setBounds(180,200,200,30);
        add(t2);
        l3=new JLabel("Sex of Customer:");
        l3.setBounds(50,250,120,30);
        add(l3);
        r1=new JRadioButton("MALE");
        r1.setBounds(180,250,70,30);
        add(r1);
        r1.addActionListener(this);
        r2=new JRadioButton("FEMALE");
        r2.setBounds(250,250,80,30); 
        add(r2);
        r2.addActionListener(this);
        ButtonGroup bg1=new ButtonGroup();
        bg1.add(r1);   bg1.add(r2);
        l4=new JLabel("Date of Birth:");
        l4.setBounds(500,200,100,30);
        add(l4);
        t3=new JTextField(10);
        t3.setBounds(600,200,150,30);
        add(t3);
        l5=new JLabel("E-mail Address:");
        l5.setBounds(50,300,120,30);
        add(l5);
        t4=new JTextField(10);
        t4.setBounds(180,300,200,30);
        add(t4);
        l6=new JLabel("Profession:");
        l6.setBounds(50,350,100,30);
        add(l6);
        r3=new JRadioButton("Service");
        r3.setBounds(180,350,70,30);
        add(r3);
        r3.addActionListener(this);
        r4=new JRadioButton("Professional");
        r4.setBounds(250,350,100,30);
        add(r4);
        r4.addActionListener(this);
        r5=new JRadioButton("House Wife");
        r5.setBounds(350,350,100,30);
        add(r5);
        r5.addActionListener(this);
        r6=new JRadioButton("Student");
        r6.setBounds(450,350,70,30);
        add(r6);
        r6.addActionListener(this);
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(r3);
        bg2.add(r4);
        bg2.add(r5);
        bg2.add(r6);
        l7=new JLabel("Permanent Address:");
        l7.setBounds(50,400,120,30);
        add(l7);
        t5=new JTextField(50);
        t5.setBounds(180,400,570,30);
        add(t5);
        l9=new JLabel("PIN:");
        l9.setBounds(550,350,50,30);
        add(l9);
        t6=new JTextField(10);
        t6.setBounds(600,350,150,30);
        add(t6);
        b1=new JButton("SUBMIT");
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
        jb=new JComboBox();
        jb.setBounds(600,250,150,30);
        jb.addItem("Rashan Card");
        jb.addItem("Driving Lisence");
        jb.addItem("Passport");
        add(jb);
        jb.addActionListener(this);
        l13=new JLabel("");
        l13.setBounds(500,300,100,30);
        add(l13);
        l13.setText("Rashan Card No.");
        t7=new JTextField(10);
        t7.setBounds(600,300,150,30);
        add(t7);
        try
        {
            connect();
            r=s.executeQuery("select Numbers from Avilable where Status='no'");
            while(r.next())
                jc.addItem(""+r.getLong("Numbers"));
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
        if(e.getSource()==jb)
        {
            sb=jb.getSelectedItem().toString();
            l13.setText(jb.getSelectedItem().toString()+" No.");
        }
        if(e.getSource()==jc)
            sm=jc.getSelectedItem().toString();
        if(e.getSource()==r1)
            ss="MALE";
        if(e.getSource()==r2)
            ss="FEMALE";
        if(e.getSource()==r3)
            sp="Service";
        if(e.getSource()==r4)
            sp="Professional";
        if(e.getSource()==r5)
            sp="House Wife";
        if(e.getSource()==r6)
            sp="Student";
        if(e.getSource()==b1)
        {
            if(t1.getText().equals("")||t2.getText().equals("")||t3.getText().equals("")||t4.getText().equals("")||t5.getText().equals("")||t6.getText().equals("")||t7.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Enter Complete Data","OOPS!!!",JOptionPane.WARNING_MESSAGE);
                return;
            }
            try
            {
                connect();
                sn=t1.getText();
                sf=t2.getText();
                sd=t3.getText();
                se=t4.getText();
                sa=t5.getText();
                pin=t6.getText();
                ipn=t7.getText();
                r=s.executeQuery("select * from Connections");	r.next();
                s.executeUpdate("insert into Connections(Numbers,Name,Father,Sex,Birth,Email,Profession,Address,Pin,IDP,IDPN,Created) values('"+sm+"','"+sn+"','"+sf+"','"+ss+"','"+sd+"','"+se+"','"+sp+"','"+sa+"','"+pin+"','"+sb+"','"+ipn+"','"+sc+"')");
                s.executeUpdate("update Avilable set Status='yes' where Numbers='"+sm+"'");
                JOptionPane.showMessageDialog(this,"U are Successively Registered","DONE!!! ",JOptionPane.INFORMATION_MESSAGE);
                nwcon n2=new nwcon();
                n2.setTitle("Create New Connection");
                n2.setSize(800,600);
                n2.setVisible(true);
                n2.setResizable(false);
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
        {
            System.out.println("error : " +ee);
        }
    }
}