package graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI {
	
	// attributes
	private static JLabel emailLabel;
	private static JLabel queueLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel success;
	private static JLabel createAccount;
	private static JLabel usersOnlineLabel;
	private static JLabel logout;
	private static int loginCount = 0;
	private static Queue<String> queue = new LinkedList<>();
	
	public LoginGUI() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(350, 200);
		frame.setTitle("Login to Game");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);
		
		usersOnlineLabel = new JLabel("Players Online: " + loginCount);
		usersOnlineLabel.setBounds(220, 140, 120, 25);
		usersOnlineLabel.setForeground(Color.WHITE);
		panel.add(usersOnlineLabel);
		
		
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
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// on the click of the button get hash map, email, and password from user
				HashMap<String, String> users = CreateAccountGUI.getUserAccounts();
				String email = userText.getText();
				String password = String.valueOf(passwordText.getPassword());
				
				// once the login count is two or more the players that try to login will be placed in a queue
				if(loginCount >= 2) {
					// add player to queue
					queue.add(email);
					
					// show that players are in queue
					queueLabel.setText("Users in queue: " + queue.size());
					success.setText("Placed in queue.");
				} else {
				
					// if the players email exists in the hash map log them in and then increase the login count to keep track of how many players are online
					if(users.containsKey(email)) {
						String enteredPassword = users.get(email);
						if(enteredPassword.equals(password)) {
							success.setText("Login Successful!");
							loginCount++;
							usersOnlineLabel.setText("Players Online: " + loginCount);
						}
					} else {
						success.setText("User does not exist.");
					}
				}
			}
		});
		panel.add(button);
		
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);
		
		queueLabel = new JLabel("");
		queueLabel.setBounds(220, 0, 120, 25);
		panel.add(queueLabel);
		
		createAccount = new JLabel("Create Account");
		createAccount.setBounds(10, 140, 120, 25);
		createAccount.setForeground(Color.BLUE);
		createAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// the user clicks the label call the create account gui and dispose the current frame
				new CreateAccountGUI();
				frame.setVisible(false);
				frame.dispose();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// the mouse has entered the label
				createAccount.setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// the mouse has exited the label
				createAccount.setForeground(Color.BLUE);
			}
		});
		panel.add(createAccount);
		
		logout = new JLabel("| Logout");
		logout.setBounds(110, 140, 120, 25);
		logout.setForeground(Color.BLUE);
		logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// the user clicks the label and opens up the logout gui and disposes the current frame
				new LogoutGUI();
				frame.setVisible(false);
				frame.dispose();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// the mouse has entered the label
				logout.setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// the mouse has exited the label
				logout.setForeground(Color.BLUE);
			}
		});
		panel.add(logout);
		
		
		frame.setVisible(true);
	}

	public static int getLoginCount() {
		return loginCount;
	}

	public static void setLoginCount(int loginCount) {
		LoginGUI.loginCount = loginCount;
	}
	
	public static Queue<String> getQueue() {
		return queue;
	}

	public static void setQueue(Queue<String> queue) {
		LoginGUI.queue = queue;
	}
}
