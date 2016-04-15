import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class mobile extends JFrame implements ActionListener
{
	int res;
	Statement s;
	ResultSet r;
	Connection c;
	JTextField t1;
	String sc,su,sp;
	JPasswordField pw;
	JLabel l1,l2,l3,l4;
	JButton b1,b2,b3,b4,b5,b6;
	java.util.Date date;
	DateFormat df;

	public mobile()
	{
		setLayout(null);
		l1=new JLabel("Mobile Billing System");
		l1.setFont(new Font("TimesRoman",Font.BOLD,24));
		l1.setBounds(280,40,240,40);
		add(l1);
		l2=new JLabel("ADMIN NAME:");
		l2.setBounds(200,100,100,30);
		add(l2);
		t1=new JTextField(10);
		t1.setBounds(300,100,200,30);
		add(t1);
		l3=new JLabel("PASSWORD  :");
		l3.setBounds(200,150,100,30);
		add(l3);
		pw=new JPasswordField(10);
		pw.setBounds(300,150,200,30);	
		pw.setEchoChar('*');
		add(pw);
		b1=new JButton("Create New Connection");
		b1.setBounds(150,250,175,40);
		add(b1);
		b1.addActionListener(this);
		b2=new JButton("Remove Any Connection");
		b2.setBounds(400,250,175,40);
		add(b2);
		b2.addActionListener(this);
		b3=new JButton("TO Enter Bill Detail.");
		b3.setBounds(150,350,175,40);
		add(b3);
		b3.addActionListener(this);
		b4=new JButton("TO Update Bill Detail");
		b4.setBounds(400,350,175,40);
		add(b4);
		b4.addActionListener(this);
		b5=new JButton("To Check Mobile Bill.");
		b5.setBounds(150,450,175,40);
		add(b5);
		b5.addActionListener(this);
		b6=new JButton("To Exit To System");
		b6.setBounds(400,450,175,40);
		add(b6);
		b6.addActionListener(this);
		date=new java.util.Date();
		df=DateFormat.getDateInstance(DateFormat.FULL,Locale.UK);
		sc=df.format(date);
		l4=new JLabel(sc);
		l4.setBounds(500,525,200,25);
		add(l4);
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
		if(e.getSource()==b1||e.getSource()==b2||e.getSource()==b3||e.getSource()==b4||e.getSource()==b5)
		{
			try
			{
				connect();
				su=t1.getText();
				sp=pw.getText();
				r=s.executeQuery("select * from Login");
				r.next();
				if(su.equals(r.getString("Admin"))&&sp.equals(r.getString("Password")))
				{
					if(e.getSource()==b1)
					{
						nwcon n1=new nwcon();
						n1.setTitle("Create New Connection");
						n1.setSize(800,600);
						n1.setVisible(true);
						n1.setResizable(false);
						setVisible(false);
					}
					if(e.getSource()==b2)
					{
						rmcon r1=new rmcon();
						r1.setTitle("Remove Any Connection");
						r1.setSize(800,600);
						r1.setVisible(true);
						r1.setResizable(false);
						setVisible(false);
					}
					if(e.getSource()==b3)
					{
						enbill e1=new enbill();
						e1.setTitle("TO Enter Bill Detail.");
						e1.setSize(800,600);
						e1.setVisible(true);
						e1.setResizable(false);
						setVisible(false);
					}
					if(e.getSource()==b4)
					{
						upbill u1=new upbill();
						u1.setTitle("TO Update Bill Detail");
						u1.setSize(800,600);
						u1.setVisible(true);
						u1.setResizable(false);
						setVisible(false);
					}
					if(e.getSource()==b5)
					{
						Analysis a1=new Analysis();
						a1.setTitle("To Check Mobile Bill.");
						a1.setSize(800,600);
						a1.setVisible(true);
						a1.setResizable(false);
						setVisible(false);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invalid User name & Password","OOPS!!!",JOptionPane.ERROR_MESSAGE);
					t1.setText("");
					pw.setText("");
					t1.requestFocus();
				}
			}
			catch(Exception ee)
			{
				System.out.println("error : " +ee);
			}
		}
		if(e.getSource()==b6)
		{
			res=JOptionPane.showConfirmDialog(null,"Do U Really Want To Exit ?");	
			switch(res)
			{
				case 0:
				setVisible(false);
				System.exit(0);
				break;
			}
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

	public static void main(String args[])
	{
		mobile m=new mobile();
		m.setTitle("Mobile Billing System");
		m.setSize(800,600);
		m.setVisible(true);
		m.setResizable(false);
	}
}