import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class crudOperation {

	private JFrame frame;
	private JTextField txtBName;
	private JTextField txtAName;
	private JTextField txtPrice;
	private JTextField txtSearchId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					crudOperation window = new crudOperation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public crudOperation() {
		initialize();
		connect();
		table_load();
	}
	
	Connection con=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private JTable table;
	
	public void connect() {
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=shailu");
		
		
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
	
	}
	
	
	
	 public void table_load()
	    {
	     try
	     {
	    pstmt = con.prepareStatement("select * from crudjava.book");
	    rs = pstmt.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	}
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }
	    }
	 
	 
	 

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1004, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "Register Book", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 128)));
		panel_1_1.setBounds(0, 0, 466, 341);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(32, 37, 102, 40);
		panel_1_1.add(lblNewLabel_1);
		
		txtBName = new JTextField();
		txtBName.setBounds(144, 37, 259, 32);
		panel_1_1.add(txtBName);
		txtBName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Author Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(32, 109, 102, 40);
		panel_1_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(32, 185, 102, 40);
		panel_1_1.add(lblNewLabel_1_1_1);
		
		txtAName = new JTextField();
		txtAName.setColumns(10);
		txtAName.setBounds(144, 109, 259, 32);
		panel_1_1.add(txtAName);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(144, 185, 128, 32);
		panel_1_1.add(txtPrice);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition;
				int price;
				bname = txtBName.getText();
				edition = txtAName.getText();
				price =Integer.parseInt(txtPrice.getText());
				
				try {
				pstmt = con.prepareStatement("insert into crudjava.book(name,author,price) values(?,?,?)");
				pstmt.setString(1, bname);
				pstmt.setString(2, edition);
				pstmt.setInt(3, price);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
				table_load();
				          
				txtBName.setText("");
				txtAName.setText("");
				txtPrice.setText("");
				txtBName.requestFocus();
				   }
				 
				catch (SQLException e1)
				        {
				e1.printStackTrace();
				}
				
				
			}
		});
		btnSave.setBackground(new Color(255, 250, 240));
		btnSave.setForeground(new Color(0, 128, 0));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(32, 264, 77, 46);
		panel_1_1.add(btnSave);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setForeground(new Color(0, 128, 0));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBackground(new Color(255, 250, 240));
		btnExit.setBounds(177, 264, 77, 46);
		panel_1_1.add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtBName.setText("");
				txtAName.setText("");
				txtPrice.setText("");
				txtSearchId.setText("");
				txtBName.requestFocus();
				
			}
		});
		btnClear.setForeground(new Color(0, 128, 0));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBackground(new Color(255, 250, 240));
		btnClear.setBounds(326, 264, 87, 46);
		panel_1_1.add(btnClear);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 128)));
		panel_2.setBounds(10, 374, 466, 78);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Book ID");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(28, 21, 102, 40);
		panel_2.add(lblNewLabel_1_2);
		
		txtSearchId = new JTextField();
		txtSearchId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				try {
			          
		            String id = txtSearchId.getText();
		 
		                pstmt = con.prepareStatement("select name,author,price from crudjava.book where id = ?");
		                pstmt.setString(1, id);
		                ResultSet rs = pstmt.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String name = rs.getString(1);
		                String author = rs.getString(2);
		                String price = rs.getString(3);
		                
		                txtBName.setText(name);
		                txtAName.setText(author);
		                txtPrice.setText(price);
		                
		                
		            }  
		            else
		            {
		            	txtBName.setText("");
		            	txtAName.setText("");
		            	txtPrice.setText("");
		                
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }
				
				
			}
		});
		txtSearchId.setColumns(10);
		txtSearchId.setBounds(134, 27, 259, 32);
		panel_2.add(txtSearchId);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bname,edition,bid;
				int price;
				bname = txtBName.getText();
				edition = txtAName.getText();
				price =Integer.parseInt(txtPrice.getText());
				bid = txtSearchId.getText();
				
				try {
				pstmt = con.prepareStatement("update crudjava.book set name=?, author=?,price=? where id=?");
				pstmt.setString(1, bname);
				pstmt.setString(2, edition);
				pstmt.setInt(3, price);
				pstmt.setString(4, bid);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Updateddd!!!!!");
				table_load();
				          
				txtBName.setText("");
				txtAName.setText("");
				txtPrice.setText("");
				txtSearchId.setText("");
				txtSearchId.requestFocus();
				   }
				 
				catch (SQLException e1)
				        {
				e1.printStackTrace();
				}
			}
		});
		btnUpdate.setForeground(new Color(0, 128, 0));
		btnUpdate.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnUpdate.setBackground(new Color(255, 250, 240));
		btnUpdate.setBounds(529, 389, 112, 50);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String bid;
	
				bid = txtSearchId.getText();
				
				try {
				pstmt = con.prepareStatement("delete from crudjava.book where id=?");
				pstmt.setString(1, bid);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Deleted!!!!!");
				table_load();
				          
				txtBName.setText("");
				txtAName.setText("");
				txtPrice.setText("");
				txtSearchId.setText("");
				txtSearchId.requestFocus();
				   }
				 
				catch (SQLException e1)
				        {
				e1.printStackTrace();
				}
				
				
			}
		});
		btnDelete.setForeground(new Color(255, 0, 0));
		btnDelete.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnDelete.setBackground(new Color(255, 250, 240));
		btnDelete.setBounds(712, 389, 117, 50);
		panel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(476, 11, 499, 325);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
