package com.project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SearchMedicine extends JFrame implements ActionListener {
	JFrame jf;
	JLabel b;
	ImageIcon img;
	JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, ln;
	JButton b0, b1, b2;
	JComboBox msname;
	String s;
	Font f;
	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel model = new DefaultTableModel();
	JTable tabGrid = new JTable(model);
	JScrollPane scrlPane = new JScrollPane(tabGrid);

	SearchMedicine() {
		jf = new JFrame();
		img = new ImageIcon(getClass().getResource("/61804.jpg"));
		b = new JLabel("", img, JLabel.CENTER);
		b.setBounds(0, 0, 800, 600);
		jf.add(b);
		// setVisible(true);
		f = new Font("Arial", Font.PLAIN, 20);
		b.setLayout(null);

		ln = new JLabel("Search Medicine");
		ln.setFont(new Font("Arial", Font.BOLD, 25));
		ln.setForeground(Color.BLACK);
		ln.setHorizontalAlignment(JLabel.CENTER);
		ln.setBounds(0, 40, 900, 40);
		ln.setForeground(Color.black);
		b.add(ln);

		l1 = new JLabel("*Medicine Batch no: ");
		// l1.setFont(f);
		l1.setBounds(250, 150, 900, 40);
		l1.setFont(new Font("Arial", Font.PLAIN, 20));
		l1.setForeground(Color.BLACK);
		b.add(l1);

		t1 = new JTextField(20);
		t1.setBounds(450, 160, 170, 25);
		t1.setToolTipText("Enter medicine batch no to search");
		t1.setFont(new Font("Arial", Font.PLAIN, 20));
		t1.setForeground(Color.black);
		b.add(t1);

		l2 = new JLabel("*Medicine name: ");
		// l2.setFont(f);
		l2.setBounds(250, 200, 200, 25);
		l2.setFont(new Font("Arial", Font.PLAIN, 20));
		l2.setForeground(Color.BLACK);
		b.add(l2);

		t2 = new JTextField(20);
		t2.setBounds(450, 200, 170, 25);
		t2.setToolTipText("Enter medicine name to search");
		t2.setFont(new Font("Arial", Font.PLAIN, 20));
		t2.setForeground(Color.black);
		b.add(t2);

		b0 = new JButton("Search", new ImageIcon(getClass().getResource("/search.png")));
		b0.setBounds(300, 250, 110, 35);
		b0.setToolTipText("click to search medicine details");
		b.add(b0);
		b0.addActionListener(this);

		b1 = new JButton("Clear", new ImageIcon(getClass().getResource("/clear.png")));
		b1.setBounds(450, 250, 110, 35);
		b1.setToolTipText("click to clear all textfields");
		b.add(b1);
		b1.addActionListener(this);

		scrlPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrlPane.setBounds(0, 320, 920, 230);
		// scrlPane.setEditable(false);
		b.add(scrlPane);
		tabGrid.setFont(new Font("Arial", 0, 15));

		model.addColumn("M_BNO");
		model.addColumn("M_NAME");
		model.addColumn("M_COMPANY");
		model.addColumn("M_QUANTITY");
		model.addColumn("M_EXPDATE");
		model.addColumn("M_PURDATE");
		model.addColumn("M_TYPE");
		model.addColumn("M_SALEPRICE");
		model.addColumn("M_PURPRICE");
		model.addColumn("M_RACKNO");
		model.addColumn("M_SID");
		model.addColumn("M_SNAME");

		jf.setTitle("Search Medicine ");
		jf.setSize(920, 600);
		jf.setLocation(20, 20);
		jf.setResizable(false);
		jf.getContentPane().setBackground(Color.cyan);
		jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == b0) {// fetch
			try {
				if (((t1.getText()).equals("")) && ((t2.getText()).equals(""))) {
					JOptionPane.showMessageDialog(this, "Please enter medicine bno or name !", "Warning!!!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int foundrec = 0;

					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store", "root", "");
					System.out.println("Connected to database.");
					int r = 0;
					ps = con.prepareStatement(
							"select * from medicine where mname='" + t2.getText() + "' or mbno='" + t1.getText() + "'");
					rs = ps.executeQuery();
					Date date1 = new Date();
					Calendar calendar = new GregorianCalendar();
					// Calendar.setTime(date1);
					String strDate = calendar.get(Calendar.MONTH) + "-" + (calendar.get(Calendar.YEAR));
					SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");
					Date date = formatter.parse(strDate);
					while (rs.next()) {
						String sDate1 = rs.getString(5);
						Date date2 = formatter.parse(sDate1);
						int result = date.compareTo(date2);
						System.out.println(result);
						if (result < 0) {
							t2.setText(rs.getString(2));
							model.insertRow(r++,
									new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
											rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
											rs.getString(9),
											rs.getString(10), rs.getString(11), rs.getString(12) });
							foundrec = 1;
						} else {
							JOptionPane.showMessageDialog(null, "Medicine is available but expired", "Dialog",
									JOptionPane.WARNING_MESSAGE);
							foundrec = 2;
						}

					}
					if (foundrec == 0) {
						JOptionPane.showMessageDialog(null, "Record is not available", "Dialog",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				con.close();
			} catch (SQLException se) {
				System.out.println(se);
				JOptionPane.showMessageDialog(null, "SQL Error." + se);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Error." + e);
			}
		} else if (ae.getSource() == b1) {
			t1.setText("");
			t2.setText("");
		} else if (ae.getSource() == b2) {// list
			int r = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store", "root", "");
				System.out.println("Connected to database.");
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = stmt.executeQuery("SELECT * from medicine");
				while (rs.next()) {
					model.insertRow(r++,
							new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
									rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
									rs.getString(10), rs.getString(11), rs.getString(12) });

				}
				con.close();
			} catch (SQLException se) {
				System.out.println(se);
				JOptionPane.showMessageDialog(null, "SQL Error:" + se);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Error:" + e);
			}
		}
	}

	public static void main(String args[]) {
		new SearchMedicine();
	}
}
