

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class LogInASA extends JFrame {
	final int FRM_WIDTH = 350, FRM_HEIGHT = 230;
	Toolkit tk = getToolkit();
	int SCR_WIDTH = (int) (tk.getScreenSize().getWidth());
	int SCR_HEIGHT = (int) (tk.getScreenSize().getHeight());
	int frmX = SCR_WIDTH/2 - FRM_WIDTH/2;
	int frmY = SCR_HEIGHT/2 - FRM_HEIGHT/2;
	
	private JLabel lblName = new JLabel("Name");
	private JTextField txtName = new JTextField(20);
	private JLabel lblPw = new JLabel("Password");
	private JPasswordField txtPw = new JPasswordField(20);
	
//	private final char defaultChar = txtPw.getEc
		
	private JButton btnLogin = new JButton("Log in");
	private JButton btnClr = new JButton("Clear");
	
	
	private JPanel panel = new JPanel();

	public LogInASA() {
		setVisible(true);
		setTitle("Appointment System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(frmX,frmY);
		setSize(FRM_WIDTH,FRM_HEIGHT);
		
		setLayout(null);
		lblName.setBounds(15, 35, 70, 30);
		add(lblName);

		txtName.setBounds(80, 40, 200, 25);
		add(txtName);


		lblPw.setBounds(10,80,70,30);
		add(lblPw);

		txtPw.setBounds(80, 85, 200, 25);
		add(txtPw);
		
//		btnGp.add(rdoAdm);
//		btnGp.add(rdoUser);
//		panel.add(rdoAdm);
//		panel.add(rdoUser);
		
		panel.setBounds(80,100,180,50);
		add(panel);

		btnLogin.setBounds(80, 150, 80, 25);
		btnLogin.setBackground(Color.white);

		btnClr.setBounds(170, 150, 70, 25);
		btnClr.setBackground(Color.white);
		add(btnLogin);
		add(btnClr);
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtName.getText().isEmpty()|| txtPw.getText().isEmpty())
						throw new DataExceptions();
				
					if(txtName.getText().equalsIgnoreCase("@dmin")&& txtPw.getText().equalsIgnoreCase("@dmin123")){
							JOptionPane.showMessageDialog(null, "Log in Success!", "Promt Message", JOptionPane.PLAIN_MESSAGE);
							new MainFrame1("Admin");
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Invalid user name or password", "Log in error", JOptionPane.ERROR_MESSAGE);
						}
					

					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					txtName.requestFocus();
				}
				
			}
		});
		btnClr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtName.requestFocus(true);
				txtName.setName("abcd");
				txtPw.setName(null);
				
			}
		});
	}
	public static void main(String[] args) {
		new LogInASA();
	}
	
}
