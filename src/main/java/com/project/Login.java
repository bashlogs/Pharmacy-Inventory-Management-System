package com.project;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class InvalidException extends Exception
{
}

class Login extends JFrame implements ActionListener
{
	JLabel b;
	ImageIcon img;
	JFrame jf;
	JButton b1,b2,b3;
	JLabel l1,l2,l3,l4,l5,l6;
	JTextField t1,t2;
	JPasswordField p1;
	Font f;
	int cnt=0,cnt1=0;

	Login()
	{
        
		jf=new JFrame();
		// img=new ImageIcon("images/61804.jpg");
		img = new ImageIcon(getClass().getResource("/61804.jpg"));
		b=new JLabel("",img,JLabel.CENTER);
		b.setBounds(0,0,800,600);
		jf.add(b);
		//setVisible(true);
		
        f = new Font("Times New Roman",Font.BOLD,20);
		b.setLayout(null);

        l5 = new JLabel("MedTrack");l5.setFont(new Font("Times New Roman",Font.BOLD,30));
        l5.setBounds(290,100,300,40);l5.setForeground(Color.BLACK);
		b.add(l5);

		l3 = new JLabel(new ImageIcon(getClass().getResource("/users.png")));
		l3.setBounds(170,200,50,25);
		b.add(l3);

		l1 = new JLabel("UserName : "); l1.setFont(f);
		l1.setBounds(220,200,200,25);l1.setForeground(Color.BLACK);
		b.add(l1);

		t1 = new JTextField(20);
		t1.setBounds(370,200,200,25);
		t1.setToolTipText("Enter Username");
		 t1.setFont(new Font("Times New Roman",Font.BOLD,20));b.add(t1);
		 t1.setFont(new Font("Times New Roman",Font.BOLD,20));b.add(t1);

        l4 = new JLabel(new ImageIcon(getClass().getResource("/pass.png")));
		l4.setBounds(170,250,50,25);
		b.add(l4);

		l2 = new JLabel("Password   : "); l2.setFont(f);
		l2.setBounds(220,250,200,25);l2.setForeground(Color.BLACK);
		b.add(l2);

		p1 = new JPasswordField(20);
		p1.setBounds(370,250,200,25);
		p1.setToolTipText("Enter Password");
		 p1.setFont(new Font("Times New Roman",Font.BOLD,20));b.add(p1);

		b1 = new JButton("Login",new ImageIcon(getClass().getResource("/Login.png")));
		b1.setBounds(370,330,100,35);
		b.add(b1);b1.addActionListener(this);

		b2 = new JButton("Clear",new ImageIcon(getClass().getResource("/clear.png")));
		b2.setBounds(220,330,100,35);
		b.add(b2);b2.addActionListener(this);

		b3 = new JButton("Exit",new ImageIcon(getClass().getResource("/exit.png")));
		b3.setBounds(440,400,100,35);
		//b.add(b3);b3.addActionListener(this);

		jf.setTitle("Login");
		jf.setLocation(20,20);
		jf.setSize(800,600);
		jf.setResizable(false);
		
		jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			try
			{
				String s=t1.getText();
				String s1=new String(p1.getPassword());
				if((s.compareTo("admin")==0)&&(s1.compareTo("admin")==0))
				{
					//JOptionPane.showMessageDialog(null," Welcome !!! You are valid user ...!!!  ","WELCOME",JOptionPane.INFORMATION_MESSAGE);
			   	    jf.setVisible(false);
                       new MainMenu();
				}
				else
				{
					throw new InvalidException();
				}
			}
			catch(Exception e1)
			{
			    cnt++;
			    JOptionPane.showMessageDialog(null," Sorry !!! You are not valid user ...!!!","WARNING",JOptionPane.ERROR_MESSAGE);
			    t1.setText("");
			    p1.setText("");
			    if(cnt==3)
			    {
			         JOptionPane.showMessageDialog(null,"Sorry !!! Your 3 attempts are over ...!!!","WARNING",JOptionPane.ERROR_MESSAGE);
			         System.exit(0);
			    }
            }

		}

		else if(ae.getSource()==b2)
		{
			t1.setText("");
			p1.setText("");

		}

		else if(ae.getSource()==b3)
		{
		    System.exit(0);
		}
	}

	public static void main(String args[])
	{
		new Login();

	}

}
