package userbot;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Graphics {

	private JFrame frame;
	private JTextField textField;
	JTextArea textArea = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	
		
		//this is using a thread runnable interface
		EventQueue.invokeLater(new Runnable() {			
			public void run() {
				try {
					//constructors do most of the work here
					Graphics window = new Graphics();
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
	public Graphics() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LanguageClass L1=new LanguageClass();
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 450, 310);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ASK ALINA");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 434, 36);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		textField.setForeground(new Color(0, 0, 0));
		textField.setBackground(new Color(204, 204, 204));
		textField.setBounds(0, 74, 434, 36);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
	
		textArea.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		JScrollPane sp = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textArea.setBounds(0, 165, 434, 100);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Ask Me");
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
		btnNewButton.setBounds(52, 121, 120, 23);
		//adds action to the ask me button
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				L1.sendResponse(textArea, textField);
				L1.ClearField(textField);
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Learn");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				L1.learnResponse(textArea, textField);
				L1.ClearField(textField);
			}
		});
		button.setForeground(Color.RED);
		button.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
		button.setBounds(260, 121, 120, 23);
		frame.getContentPane().add(button);
		
		
	}
	
	
}
