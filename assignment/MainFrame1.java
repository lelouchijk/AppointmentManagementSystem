package assignment;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame1 extends JFrame {
	
	final int FRM_WIDTH = 800, FRM_HEIGHT = 500;
	Toolkit tk = getToolkit();
	int SCR_WIDTH = (int) (tk.getScreenSize().getWidth());
	int SCR_HEIGHT = (int) (tk.getScreenSize().getHeight());
	int frmX = SCR_WIDTH/2 - FRM_WIDTH/2;
	int frmY = SCR_HEIGHT/2 - FRM_HEIGHT/2;
	
	private JPanel panel1 = new JPanel();
	private JPanel panelTop = new JPanel();
	private JPanel currentPanel = panel1 ;
	
	private AdminPanel1 adP;
	private EditingPanel1 edP;
	
	private JTextField txtToken = new JTextField();
	private JTextField txtTokenHolder = new JTextField();
	private JTextField txtTokenDepartment = new JTextField();
	
	private JButton btnDeque = new JButton("Checked");
	private JButton btnNext = new JButton("Next Customer");
	
	private JMenuBar menuBar=new JMenuBar();
	private JMenu fileMenu=new JMenu("Menu");
	private JMenuItem adminDisplay=new JMenuItem("Adding Customer");
	private JMenuItem userDisplay = new JMenuItem("Customer Token Screen");
	private JMenuItem updataDisplay=new JMenuItem("Editing Customer Information");
	private JMenuItem exit=new JMenuItem("Log Out");
	
	private ArrayCircularQueue<Depositors> acqMain = new ArrayCircularQueue<Depositors>();
    
	
	public MainFrame1(String userType) {
		adP = new AdminPanel1(acqMain); 
		edP = new EditingPanel1(acqMain, adP);
		setVisible(true);
		setLocation(frmX, frmY);
		setSize(FRM_WIDTH,FRM_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(userType+"mode");
		setJMenuBar(menuBar);
		setResizable(false);
		setLayout(null);
		
		
		if(userType.equals("Admin")) {
			adminMode();
		}
		menuBar.add(fileMenu);
		fileMenu.add(adminDisplay);
		fileMenu.add(userDisplay);
		fileMenu.add(updataDisplay);
		fileMenu.add(exit);
		
		panel1.setBounds(0, 50, 800, 450);
		panelTop.setBounds(0, 0, 800, 50);
		add(panelTop);
		panel1.setLayout(null);
		
		txtToken.setBounds(120, 30, 400, 60);
		txtTokenHolder.setBounds(120, 100, 400, 60);
		txtTokenDepartment.setBounds(120, 170, 400, 60);
		txtToken.setEditable(false);
		txtTokenHolder.setEditable(false);
		txtTokenDepartment.setEditable(false);
		btnDeque.setEnabled(false);
		
		panel1.add(txtToken);
		panel1.add(txtTokenHolder);
		panel1.add(txtTokenDepartment);	
		
		btnDeque.setBounds(150, 260, 150, 50);
		panel1.add(btnDeque);
		
		btnNext.setBounds(340,260,150,50);
		panel1.add(btnNext);
		if(userType.equals("User")) {
			adminDisplay.setEnabled(false);
			updataDisplay.setEnabled(false);
			add(panel1);
		}
			
		btnDeque.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				long startTime = System.nanoTime();
				acqMain.dequeue();
				JOptionPane.showMessageDialog(null, "Next Token Incoming in next 15 Minutes!!");
				btnNext.setEnabled(true);
				btnDeque.setEnabled(false);
				txtToken.setText("");
				txtTokenDepartment.setText("");
				txtTokenHolder.setText("");
				adP.showTable();
				long endTime = System.nanoTime();
				System.out.println("Dequeue Time: " + (endTime - startTime) + " ns");
				
				
			}
		});
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(acqMain.size() >0) {
					
					txtToken.setText(acqMain.first().getTokenNumber().trim());
					txtTokenHolder.setText(String.valueOf(acqMain.first().getTokenHolderName().trim()));
					txtTokenDepartment.setText(String.valueOf(acqMain.first().getTokenDeperment().trim()));
					btnDeque.setEnabled(true);
					btnNext.setEnabled(false);
					}
					
					else {
						 
						 JOptionPane.showMessageDialog(null, "No Data Left Add More!");
						 txtToken.setText("");
						 txtTokenHolder.setText("");
						 txtTokenDepartment.setText("");btnDeque.setEnabled(false);
					}
				
			}
		});
		
		adminDisplay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				repaint();
				currentPanel = adP;
				currentPanel.setBounds(0, 50, 800, 450);
				add(currentPanel);
			}
		});
		userDisplay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				repaint();
				currentPanel = panel1;
				currentPanel.setBounds(0, 50, 800, 450);
				add(currentPanel);
				
			}
		});
		updataDisplay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				repaint();
				currentPanel = edP;
				currentPanel.setBounds(0, 50, 800, 450);
				add(currentPanel);
				
			}
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LogInASA(); dispose();
				
			}
		});
		
	}

	private void adminMode() {
		remove(currentPanel);
		repaint();
		currentPanel = adP;
		currentPanel.setBounds(0, 50, 800, 450);
		add(currentPanel);
	}
}
