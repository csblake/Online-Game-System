package graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccountGUI {
	
	// attributes
	private static JLabel emailLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel success;
	private static JLabel createAccount;
	private static JLabel viewAccounts;
	static HashMap<String, String> userAccounts = new HashMap<>();

	public CreateAccountGUI() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(350, 200);
		frame.setTitle("Create Account");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);
		
		emailLabel = new JLabel("Email", JLabel.CENTER);
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
		
		button = new JButton("Create Account");
		button.setBounds(10, 80, 120, 25);
		button.addActionListener(new ActionListener() {
			// method has input validation for email and password to make sure that the email is valid and the password contains a special character
			@Override
			public void actionPerformed(ActionEvent e) {
				// get the email and password that was entered
				String email = userText.getText();
				String password = String.valueOf(passwordText.getPassword());
				
				// if the user account entered isn't in the hash map enter the email and password into the hash map
				if(userAccounts.get(email) == null) {
					boolean containsSpecial = false;
					
					// for loop to check for special char in password
					for(int counter = 0; counter < password.length(); counter++) {
						char current = password.charAt(counter);
						if(!Character.isDigit(current) && !Character.isWhitespace(current) && !Character.isLetter(current)) {
							containsSpecial = true;
						}
					}
					// on the click of the button
					if(email.contains("@") && containsSpecial) {
						success.setText("Your Account was created!");
						
						// enter the email and password into the active user accounts
						userAccounts.put(email, password);
					} else {
						success.setText("Need valid email and password with special.");
					}
					
				} else {
					success.setText("Email already exists. Try again.");
				}
			}
		});
		panel.add(button);
		
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);
		
		createAccount = new JLabel("Login");
		createAccount.setBounds(10, 140, 120, 25);
		createAccount.setForeground(Color.BLUE);
		createAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// the user clicks the label and calls the login gui while also closing the current gui
				new LoginGUI();
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
		
		viewAccounts = new JLabel("| ViewAccounts");
		viewAccounts.setBounds(50, 140, 120, 25);
		viewAccounts.setForeground(Color.BLUE);
		viewAccounts.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewAccounts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// the user clicks the label and pops up a message in the same frame showing the accounts
				JOptionPane.showMessageDialog(frame, sortUsersByEmail(userAccounts));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// the mouse has entered the label
				viewAccounts.setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// the mouse has exited the label
				viewAccounts.setForeground(Color.BLUE);
			}
		});
		panel.add(viewAccounts);
		
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Account Created!");
	}
	
	public static HashMap<String, String> getUserAccounts() {
		return userAccounts;
	}

	public static void setUserAccounts(HashMap<String, String> userAccounts) {
		CreateAccountGUI.userAccounts = userAccounts;
	}
	

	// this is the sorting algorithm I used to sort a hash map of strings to be in alphabetical order
	static String result;
	public static String sortUsersByEmail(HashMap<String, String> hash) {
		// make the set
		result = "";
		Set<Entry<String, String>> entrySet = userAccounts.entrySet();
		
		// make the list to sort set
		List<Entry<String, String>> list = new ArrayList<>(entrySet);
		
		Collections.sort(list, new Comparator<Entry<String, String>>() {

			@Override
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
			
		});
				
		list.forEach(s ->{
			result += s.getKey() + "\t" + s.getValue() + "\n";
		});
		
		return result;
	}
}
