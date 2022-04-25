package graphics;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AccountsViewGUI {
	private static JLabel users;
	private static JLabel label;
	
	public AccountsViewGUI() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(350, 200);
		frame.setTitle("Game Accounts");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);
		
		label = new JLabel("User Accounts", JLabel.CENTER);
		label.setBounds(10, 20, 110, 25);
		label.setForeground(Color.WHITE);
		panel.add(label);
		
		users = new JLabel(CreateAccountGUI.sortUsersByEmail(CreateAccountGUI.getUserAccounts()), JLabel.CENTER);
		users.setBounds(10, 50 , 80, 25);
		users.setForeground(Color.WHITE);
		panel.add(users);
		
		frame.setVisible(true);
	}
	
	
}
