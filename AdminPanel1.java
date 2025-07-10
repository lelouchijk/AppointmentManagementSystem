

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class AdminPanel1 extends JPanel{
	private JLabel lblDate = new JLabel("Date ");
	private JTextField txtToken = new JTextField();
	private JLabel lblTokenHolder = new JLabel("Token Holder Name ");
	private JTextField txtTokenHolder = new JTextField();
	private JLabel lblTokenDeperment = new JLabel("Deperment");
	private JTextField txtTokenDepartment = new JTextField();
	
	private JButton btnEnqueue = new JButton("Add");
	private JButton btnClear = new JButton("Clear");
	private JButton btnShow = new JButton("Show");
	
	private String[] departmentName = {"Administrative","Commercial Banking","Credit/lending","Customer service","Finance","Loan Servicing","Retail Banking"};
	private JComboBox cDpn= new JComboBox(departmentName);
	private ArrayCircularQueue<Depositors> acq ;
	private JTable showTable;
	private DefaultTableModel tableModel;
	private JScrollPane tableScrollPane;
	
	private int tokenCounter = 1;

	public AdminPanel1(ArrayCircularQueue<Depositors> acq) {
		this.acq = acq;
		setLayout(null);

		lblTokenHolder.setBounds(10, 80, 120, 30);
		txtTokenHolder.setBounds(150, 80, 200, 30);
		lblTokenDeperment.setBounds(10, 130, 100, 30);
		cDpn.setBounds(150, 130, 200, 30);
		
		btnEnqueue.setBounds(50, 180, 100, 30);
		btnClear.setBounds(200, 180, 100, 30);
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Token Number");
		tableModel.addColumn("Token Holder Name");
		tableModel.addColumn("Token Department");
		
		showTable = new JTable(tableModel);
		tableScrollPane = new JScrollPane(showTable);
		tableScrollPane.setBounds(400, 30, 380, 400);
		
		add(lblTokenHolder);
		add(lblTokenDeperment);
		
		add(txtTokenHolder);
		add(cDpn);
		add(btnEnqueue);
		add(btnClear);
		add(tableScrollPane);
		txtToken.setEnabled(false);
		

		 btnEnqueue.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	long startTime = System.nanoTime();
	                try {
	                    if (txtTokenHolder.getText().isEmpty()) {
	                        throw new DataExceptions();
	                    } else {
	                        Depositors dop = new Depositors();
	                        dop.setTokenNumber(tokenCounter); // Set token number
	                        dop.setTokenHolderName(txtTokenHolder.getText().trim());
	                        dop.setTokenDeperment(cDpn.getSelectedItem().toString());
	                        acq.enqeue(dop);
	                        
	                        tokenCounter++; // Increment token number for the next entry
	                    }
	                    JOptionPane.showMessageDialog(null, "Registered");
	                    updateTable();
	                } catch (Exception e1) {
	                    JOptionPane.showMessageDialog(null, e1.getMessage());
	                    txtToken.requestFocus();
	                }
	                txtTokenHolder.setText("");
	                long endTime = System.nanoTime();
					System.out.println("Enqueue Time: " + (endTime - startTime) + " ns");
	            }
	        });
		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtToken.setText("");
				txtTokenHolder.setText("");
				txtTokenDepartment.setText("");
				
			}
		});
		
		btnShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				long startTime = System.nanoTime();
				showTable();
				long endTime = System.nanoTime();
				System.out.println("Showing all queue Time: " + (endTime - startTime) + " ns");
			}
		});

		
	}
	public void showTable() {
		tableModel.setRowCount(0);
		
		if(acq.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Token Number exist \n Please Add");
            return;
		}
		for(int i = 0;i<acq.size();i++) {

			String tokenNumber = (acq.get(i).getTokenNumber());
			String tokenHolderName = (acq.get(i).getTokenHolderName());
			String tokenDeperment = (acq.get(i).getTokenDeperment());
			tableModel.addRow(new Object[]{tokenNumber,tokenHolderName, tokenDeperment});
		}

}
	private void updateTable() {
		tableModel.setRowCount(0);
		
		for(int i = 0;i<acq.size();i++) {

			String tokenNumber = (acq.get(i).getTokenNumber());
			String tokenHolderName = (acq.get(i).getTokenHolderName());
			String tokenDeperment = (acq.get(i).getTokenDeperment());
			tableModel.addRow(new Object[]{tokenNumber,tokenHolderName, tokenDeperment});
		}

}
}

