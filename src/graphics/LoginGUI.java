package graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI implements ActionListener {
	
	private static JLabel emailLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel success;
	private static JLabel createAccount;
	private static CreateAccountGUI create = new CreateAccountGUI();

	public static void main(String[] args) {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(350, 200);
		frame.setTitle("Login to Game");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);
		
		emailLabel = new JLabel("User", JLabel.CENTER);
		emailLabel.setBounds(10, 20 , 80, 25);
		emailLabel.setForeground(Color.WHITE);
		panel.add(emailLabel);
		
		userText = new JTextField();
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		passwordLabel = new JLabel("Password", JLabel.CENTER);
		passwordLabel.setBounds(10, 50, 80, 25);
		passwordLabel.setForeground(Color.WHITE);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);
		
		button = new JButton("Login");
		button.setBounds(10, 80, 80, 25);
		button.addActionListener(new LoginGUI());
		panel.add(button);
		
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);
		
		createAccount = new JLabel("Create Account");
		createAccount.setBounds(10, 140, 120, 25);
		createAccount.setForeground(Color.BLUE);
		createAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// the user clicks the label
				new CreateAccountGUI();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// the mouse has entered the label
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// the mouse has exited the label
			}
		});
		panel.add(createAccount);
		
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Login Successful!");
	}
}
