package view;


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel iconClose;
	private int xSize, ySize;
	private JTextField txtUserName;
	private JPasswordField password;
	private JLabel iconUser, lblUserError, iconPassword, lblPasswordError, lblLoginError;
	private Color color;
	private int gap;
	private static int intUser= 0;
	private static int intPass= 0;
	private JPanel content, signInPanel;
	private static Login instance = new Login();
	private String id, name;
	private boolean  isLogin;
	
	private Login(){
		
		instance = this;
		
		content = new JPanel();
		
		xSize = 350;
		ySize = 300;
		
		this.setSize(xSize,ySize);
		this.setLocationRelativeTo(null);
		content.setBackground(new Color(245,250,253));
		content.setLayout(null);
		
		iconClose = new JLabel("");
		iconClose.setIcon(new ImageIcon(this.getClass().getResource("/imgs/inactive/close_10x10.png")));
		iconClose.setBounds(xSize-20, -5, 28, 40);
		iconClose.setVisible(true);
		iconClose.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				iconClose.setIcon(new ImageIcon(this.getClass().getResource("/imgs/active/close_10x10.png")));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				iconClose.setIcon(new ImageIcon(this.getClass().getResource("/imgs/inactive/close_10x10.png")));
			}
			
			@Override
			public void mouseClicked(final MouseEvent e) {
				instance.dispose();
			}

		});
		content.add(iconClose);
		
		JLabel lblChatImg = new JLabel();
		lblChatImg.setIcon(new ImageIcon(this.getClass().getResource("/imgs/chat.png")));
		lblChatImg.setBounds(70,20,50,50);
		lblChatImg.setVisible(true);
		content.add(lblChatImg);
		
		JLabel lblChat = new JLabel("Chat");
		lblChat.setForeground(new Color(34,167,240));
		lblChat.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblChat.setBounds(125,25,150,40);
		lblChat.setVisible(true);
		content.add(lblChat);
		
		JLabel lblCentral = new JLabel("GEC4");
		lblCentral.setForeground(new Color(37,116,169));
		lblCentral.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblCentral.setBounds(190,25,150,40);
		lblCentral.setVisible(true);
		content.add(lblCentral);
		
		JPanel userPanel = new JPanel();
		userPanel.setBounds(50, 105, 250, 35);
		userPanel.setLayout(null);
		userPanel.setBackground(new Color(137,196,244));
		
		iconUser = addIcon("/imgs/user_16x16.png");
		iconUser.setBounds(5, 8, 28, 20);
		userPanel.add(iconUser);
		
		txtUserName = new JTextField("seu usuário");
		txtUserName.setBorder(null);
		txtUserName.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
		txtUserName.setForeground(Color.WHITE);
		txtUserName.setCaretColor(Color.WHITE);
		txtUserName.setBackground(new Color(137,196,244));
		txtUserName.setColumns(10);
		txtUserName.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				lblUserError.setVisible(false);
				lblLoginError.setVisible(false);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if(intUser == 0){
					txtUserName.setForeground(Color.WHITE);
					txtUserName.setText(new Character(c).toString());
					password.setText("");
				}
				intUser++;
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		txtUserName.setBounds(30, 0, 215, 35);
		userPanel.add(txtUserName);
		
		lblUserError = new JLabel("Username can not be empty!");
		lblUserError.setForeground(new Color(198, 86, 94));
		lblUserError.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblUserError.setLocation(50, 140);
		lblUserError.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserError.setVerticalAlignment(SwingConstants.TOP);
		lblUserError.setSize(300, 30);
		lblUserError.setVisible(false);
		content.add(lblUserError);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setBounds(50, 165, 250, 35);
		passwordPanel.setLayout(null);
		passwordPanel.setBackground(new Color(137,196,244));
		
		iconPassword = addIcon("/imgs/password_16x16.png");
		iconPassword.setBounds(5, 8, 28, 20);
		passwordPanel.add(iconPassword);
		
		password = new JPasswordField("12345678");
		password.setBorder(null);
		password.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
		password.setForeground(Color.WHITE);
		password.setCaretColor(Color.WHITE);
		password.setBackground(new Color(137,196,244));
		password.setColumns(10);
		password.setBounds(30, 0, 215, 35);
		password.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		password.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				password.setForeground(Color.WHITE);
				lblPasswordError.setVisible(false);
				lblLoginError.setVisible(false);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if(intPass == 0){
					password.setForeground(Color.WHITE);
					password.setText(new Character(c).toString());
				}
				intPass++;
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		passwordPanel.add(password);
		
		lblPasswordError = new JLabel("Password can not be empty!");
		lblPasswordError.setForeground(new Color(198, 86, 94));
		lblPasswordError.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPasswordError.setLocation(50, 200);
		lblPasswordError.setHorizontalAlignment(SwingConstants.LEFT);
		lblPasswordError.setVerticalAlignment(SwingConstants.TOP);
		lblPasswordError.setSize(300, 30);
		lblPasswordError.setVisible(false);
		content.add(lblPasswordError);
		
		signInPanel = new JPanel();
		signInPanel.setBounds(50, 230, 250, 35);
		signInPanel.setLayout(null);
		signInPanel.setBackground(new Color(31,58,147));
		color = Color.WHITE;
		gap = 10;
		signInPanel.setBorder(new Border() {
			
			@Override
	        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	            Graphics2D g2d = (Graphics2D) g.create();
	            g2d.setColor(color);
	            g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, gap, gap));
	            g2d.dispose();
	        }

	        @Override
	        public Insets getBorderInsets(Component c) {
	            return (getBorderInsets(c, new Insets(gap, gap, gap, gap)));
	        }

	        public Insets getBorderInsets(Component c, Insets insets) {
	            insets.left = insets.top = insets.right = insets.bottom = gap / 2;
	            return insets;
	        }

	        @Override
	        public boolean isBorderOpaque() {
	            return false;
	        }
		});
		signInPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isLogin)
					content.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				else
					content.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!isLogin)
					content.setCursor(new Cursor(Cursor.HAND_CURSOR));
				else
					content.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isLogin)
					login();
			}

			
		});
		
		JLabel lblSignIn = new JLabel("Login");
		lblSignIn.setForeground(Color.WHITE);
		lblSignIn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSignIn.setBounds(100,0,70,30);
		lblSignIn.setVisible(true);
		signInPanel.add(lblSignIn);
		
		lblLoginError = new JLabel();
		lblLoginError.setForeground(new Color(198, 86, 94));
		lblLoginError.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblLoginError.setLocation(50, 265);
		lblLoginError.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoginError.setVerticalAlignment(SwingConstants.TOP);
		lblLoginError.setSize(300, 30);
		lblLoginError.setVisible(true);
		content.add(lblLoginError);
		
		content.add(userPanel);
		content.add(passwordPanel);
		content.add(signInPanel);
		
		content.setVisible(true);
		
		this.setAlwaysOnTop(true);
		this.add(content);
		
		content.setBorder(BorderFactory.createLineBorder(new Color(191,191,191)));
		this.setSize(xSize,ySize);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	private void login() {
		if((txtUserName.getText().equals("")) || (txtUserName.getText().equals("seu usuário")))
			lblUserError.setVisible(true);
		else if(new String(password.getPassword()).equals(""))
			lblPasswordError.setVisible(true);
		else{
			instance.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			content.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			txtUserName.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			password.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			isLogin = true;
			//TODO Login
			
		}
	}
	
	
	/**
	 * Add an icon into the text field.
	 * 
	 * @param path
	 *            path to the file.
	 * @return a JLabel with the icon.
	 */
	private JLabel addIcon(String path) {
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(this.getClass().getResource(path)));
		return icon;
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public static Login getInstance(){
		return instance;
	}
}
