

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
import javax.swing.table.DefaultTableModel;

public class EditingPanel1 extends JPanel {
	private JLabel lblToken = new JLabel("TokenNumber ");
	private JTextField txtToken = new JTextField();
	private JLabel lblTokenHolder = new JLabel("Token Holder Name ");
	private JTextField txtTokenHolder = new JTextField();
	private JLabel lblTokenDeperment = new JLabel("Deperment");
	private JTextField txtTokenDeperment = new JTextField();
	
	private JButton btnUpdate = new JButton("Update");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnShow = new JButton("Show");
	private JButton btnCancel = new JButton("Cancel");
	
	private String[] departmentName = {"Administrative","Commercial Banking","Credit/lending","Customer service","Finance","Loan Servicing","Retail Banking"};
	private JComboBox cDpn= new JComboBox(departmentName);
	
	private ArrayCircularQueue<Depositors> acq = new ArrayCircularQueue<Depositors>();
	private AdminPanel1 adP;

	private JTable showTable;
	private DefaultTableModel tableModel;
	private JScrollPane tableScrollPane;

	public EditingPanel1(ArrayCircularQueue<Depositors> acq,  AdminPanel1 adP) {
		this.acq = acq;
		this.adP = adP;
		setLayout(null);
		lblToken.setBounds(10, 30, 100, 30);
		txtToken.setBounds(150, 30, 200, 30);
		lblTokenHolder.setBounds(10, 80, 100, 30);
		txtTokenHolder.setBounds(150, 80, 200, 30);
		lblTokenDeperment.setBounds(10, 130, 100, 30);
		cDpn.setBounds(150, 130, 200, 30);
		
		btnUpdate.setBounds(50, 180, 100, 30);
		btnDelete.setBounds(200, 180, 100, 30);
		btnShow.setBounds(125, 250, 100, 30);
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Token Number");
		tableModel.addColumn("Token Holder Name");
		tableModel.addColumn("Token Deperment");
		
		showTable = new JTable(tableModel);
		tableScrollPane = new JScrollPane(showTable);
		tableScrollPane.setBounds(400, 30, 380, 400);
		
		add(lblToken);
		add(lblTokenHolder);
		add(lblTokenDeperment);
		add(txtToken);
		add(txtTokenHolder);
		add(cDpn);
		add(btnUpdate);
		add(btnDelete);
		add(btnShow);
		add(tableScrollPane);
		add(btnCancel);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
		txtTokenHolder.setEditable(false);
		cDpn.setEnabled(false);
		
		btnShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);
				String searchToken = txtToken.getText().trim();
				boolean found = false;
				
				if(searchToken.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a Token Number to search.");
					btnUpdate.setEnabled(false);
					btnDelete.setEnabled(false);
		            return;
				}
				for (int i = 0; i < acq.size(); i++) {
		            Depositors depositor = acq.get(i);
		            if (depositor.getTokenNumber().equals(searchToken)) {
		                String tokenNumber = depositor.getTokenNumber().trim();
		                String tokenHolderName = depositor.getTokenHolderName().trim();
		                String tokenDeperment = depositor.getTokenDeperment().trim();
		                tableModel.addRow(new Object[]{tokenNumber, tokenHolderName, tokenDeperment});
		                found = true;
		                btnUpdate.setEnabled(true);
						btnDelete.setEnabled(true);
						txtTokenHolder.setEditable(true);
						cDpn.setEnabled(true);
		                break; 
		            }
		        }
				
				if (!found) {
		            JOptionPane.showMessageDialog(null, "No record found with the given Token Number.");
		            btnUpdate.setEnabled(false);
					btnDelete.setEnabled(false);
					cDpn.setEnabled(false);
		        }
				
				
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTable();
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				cDpn.setEnabled(false);
				txtTokenHolder.setEditable(false);
				adP.showTable();
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteTable();
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				findTable();
				txtTokenHolder.setEditable(false);
				cDpn.setEnabled(false);
				adP.showTable();
			}
		});

	}
	
	private void findTable() {
		tableModel.setRowCount(0);
		String searchToken = txtToken.getText().trim();
		boolean found = false;
		
		if(searchToken.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a Token Number to search.");
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
            return;
		}
		for (int i = 0; i < acq.size(); i++) {
            Depositors depositor = acq.get(i);
            if (depositor.getTokenNumber().equals(searchToken)) {
                String tokenNumber = depositor.getTokenNumber().trim();
                String tokenHolderName = depositor.getTokenHolderName().trim();
                String tokenDeperment = depositor.getTokenDeperment().trim();
                tableModel.addRow(new Object[]{tokenNumber, tokenHolderName, tokenDeperment});
                found = true;
                break; 
            }
        }
		if (!found) {
            JOptionPane.showMessageDialog(null, "No record found with the given Token Number.");
            btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
        }

}
	
	private void deleteTable() {
	    String searchToken = txtToken.getText().trim();
	    boolean found = false;
	    long startTime = System.nanoTime();
	    if (searchToken.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Please enter a Token Number to search.");
	        btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
	        return;
	    }
	    try {
			ArrayCircularQueue<Depositors> temp = new ArrayCircularQueue<Depositors>();
			int size = acq.size();
			
			for (int i = 0; i < size; i++) {
			    Depositors depositor = acq.dequeue();
			    if (depositor.getTokenNumber().equals(searchToken)) {
			        found = true;
			       
			    } else {

			    	temp.enqeue(depositor);
			    }
			}
			
			while(!temp.isEmpty()) {
				acq.enqeue(temp.dequeue());
			}

			if (found) {
			    JOptionPane.showMessageDialog(null, "Record deleted successfully.");
			    txtToken.setText("");
				txtTokenHolder.setText("");
				txtTokenDeperment.setText("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No record found with the given Token Number.");
	        txtToken.setText("");
			txtTokenHolder.setText("");
			txtTokenDeperment.setText("");
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
			txtTokenHolder.setEditable(false);
		}
	    long endTime = System.nanoTime();
	    System.out.println("Delete Time: " + (endTime - startTime) + " ns");
	}



	private void updateTable() {
		tableModel.setRowCount(0);
		String searchToken = txtToken.getText().trim();
		boolean found = false;
		long startTime = System.nanoTime();
		if(searchToken.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a Token Number to search.");
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
			txtTokenHolder.setEditable(false);
            return;
		}
		for (int i = 0; i < acq.size(); i++) {
			Depositors depositor = acq.get(i);
	        if (depositor.getTokenNumber().equals(searchToken)) {
	            
	            depositor.setTokenHolderName(txtTokenHolder.getText());
	            depositor.setTokenDeperment(cDpn.getSelectedItem().toString());

	            String tokenNumber = depositor.getTokenNumber();
	            String tokenHolderName = depositor.getTokenHolderName();
	            String tokenDeperment = depositor.getTokenDeperment();

	            tableModel.addRow(new Object[]{tokenNumber, tokenHolderName, tokenDeperment});

	            JOptionPane.showMessageDialog(null, "Given information is updated.");

	            found = true;
	            break; 
	            
            }
        }
		if (!found) {
            JOptionPane.showMessageDialog(null, "No record found with the given Token Number.");
        }
		long endTime = System.nanoTime();
		System.out.println("Update Time: " + (endTime - startTime) + " ns");
		
		txtToken.setText("");
		txtTokenHolder.setText("");
		txtTokenDeperment.setText("");
	}
	
	

}
