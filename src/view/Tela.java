package view;



import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.Core;


public class Tela {

	private JFrame frame;
	private JLabel lblMinimize, lblNoMessage, lblNoChats;
	private Point clicked;
	private JTextArea txtMessage;
	private LightScrollPane scrollPane;
	private JPanel mainPanel, conversaPanel, conversacaoPanel;
	private int contUsers = 0;
	private int countMessages = 0;
	private int intMessage = 0;
	private ArrayList<String> users = new ArrayList<>();
	private String user = Login.getInstance().getUser();
	private String userConversation;
	private static Tela instance = new Tela();
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	private Tela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 480);
		frame.getContentPane().setBackground(new Color(245,250,253));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setBackground(new Color(248,248,255));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSystemBorder(false); 
				
		JPanel cabecalhoPanel = new JPanel();
		cabecalhoPanel.setBounds(0, 0, 750, 36);
		cabecalhoPanel.setBackground(Color.WHITE);
		cabecalhoPanel.setBorder(BorderFactory.createLineBorder(new Color(191,191,191)));
		cabecalhoPanel.setLayout(null);
		
		lblMinimize = new JLabel("");
		lblMinimize.setIcon(new ImageIcon(this.getClass().getResource("/imgs/inactive/minimize_10x10.png")));
		lblMinimize.setBounds(700, 19, 16, 9);
		lblMinimize.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblMinimize.setIcon(new ImageIcon(this.getClass().getResource("/imgs/inactive/minimize_10x10.png")));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMinimize.setIcon(new ImageIcon(this.getClass().getResource("/imgs/active/minimize_10x10.png")));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});
		cabecalhoPanel.add(lblMinimize);
		
		
		JLabel lblClose = new JLabel("");
		lblClose.setIcon(new ImageIcon(this.getClass().getResource("/imgs/inactive/close_10x10.png")));
		lblClose.setBounds(725, 9, 16, 14);
		lblClose.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblClose.setIcon(new ImageIcon(this.getClass().getResource("/imgs/inactive/close_10x10.png")));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblClose.setIcon(new ImageIcon(this.getClass().getResource("/imgs/active/close_10x10.png")));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0); 
				
			}
		});
		cabecalhoPanel.add(lblClose);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		mainPanel.setBackground(new Color(245,250,253));
		mainPanel.setBorder(BorderFactory.createLineBorder(new Color(30,30,30)));
		mainPanel.setLayout(null);

		JLabel photo;
		int size = 60;
		
		try {
			photo = drawPhoto(user, size);
		} catch (IOException e1) {
			System.out.println("ERROR to upload photo");
			photo = new JLabel();
			photo.setIcon(new ImageIcon(this.getClass().getResource("/imgs/users/user.png")));
		}
		
		photo.setBounds(20, 50, size, size);
		photo.setVisible(true);
		mainPanel.add(photo);
		
		JLabel lblUserName = new JLabel(user.substring(0, 1).toUpperCase() + user.substring(1));
		lblUserName.setForeground(Color.BLACK);
		lblUserName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblUserName.setBounds(85,57,70,30);
		lblUserName.setVisible(true);
		mainPanel.add(lblUserName);
		
		JLabel lblStatus = new JLabel("Online");
		lblStatus.setForeground(new Color(38,194,129));
		lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblStatus.setBounds(85,75,70,30);
		lblStatus.setVisible(true);
		mainPanel.add(lblStatus);
		
		JLabel lblDeslogar = new JLabel("Sair");
		lblDeslogar.setForeground(new Color(231,76,60));
		lblDeslogar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDeslogar.setBounds(175,30,70,30);
		lblDeslogar.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Core.getInstance().deslogar(user);
				System.exit(0);
			}
		});
		lblDeslogar.setVisible(true);
		mainPanel.add(lblDeslogar);
		
		JLabel lblContatos = new JLabel("CONTATOS");
		lblContatos.setForeground(Color.BLACK);
		lblContatos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblContatos.setBounds(15,175,100,30);
		lblContatos.setVisible(true);
		mainPanel.add(lblContatos);
		
		JLabel lblSeparador = new JLabel("____________________________");
		lblSeparador.setForeground(new Color(191,191,191));
		lblSeparador.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSeparador.setBounds(0,185,210,30);
		lblSeparador.setVisible(true);
		mainPanel.add(lblSeparador);
		
		JPanel servidorChatPanel = createUserPanel("servidor");
		mainPanel.add(servidorChatPanel);
		
		conversaPanel = new JPanel();
		conversaPanel.setBounds(200, 35, 550, 445);
		conversaPanel.setBackground(Color.WHITE);
		conversaPanel.setBorder(BorderFactory.createLineBorder(new Color(191,191,191)));
		conversaPanel.setLayout(null);

		lblNoMessage = new JLabel();
		lblNoMessage.setIcon(new ImageIcon(this.getClass().getResource("/imgs/no-message.png")));
		lblNoMessage.setBounds(150, 30, 256,256);
		lblNoMessage.setVisible(true);
		conversaPanel.add(lblNoMessage);
		
		lblNoChats = new JLabel("Sem mensagens. Abra um chat, por favor!");
		lblNoChats.setForeground(Color.BLACK);
		lblNoChats.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNoChats.setBounds(100,300,350,30);
		lblNoChats.setVisible(true);
		conversaPanel.add(lblNoChats);
		
		mainPanel.setOpaque(false);
		
		frame.add(mainPanel);
		frame.add(cabecalhoPanel);
		frame.add(conversaPanel);
		
		frame.setVisible(true);
		
	}

	private void openChat(String userConversation) {
		this.userConversation = userConversation;
		
		JLabel photoUserConversation;
		int sizeUserConversation = 70;
		
		try {
			photoUserConversation = drawPhoto(userConversation, sizeUserConversation);
		} catch (IOException e1) {
			System.out.println("ERROR to upload photo");
			photoUserConversation = new JLabel();
			photoUserConversation.setIcon(new ImageIcon(this.getClass().getResource("/imgs/users/user.png")));
		}
		
		photoUserConversation.setBounds(15, 20, sizeUserConversation, sizeUserConversation);
		photoUserConversation.setVisible(true);
		conversaPanel.add(photoUserConversation);
		
		JLabel lblUserNameConversation = new JLabel(userConversation.substring(0, 1).toUpperCase() + userConversation.substring(1));
		lblUserNameConversation.setForeground(Color.BLACK);
		lblUserNameConversation.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblUserNameConversation.setBounds(95,25,150,30);
		lblUserNameConversation.setVisible(true);
		conversaPanel.add(lblUserNameConversation);
		
		JLabel lblStatusConversation = new JLabel("Online");
		lblStatusConversation.setForeground(new Color(38,194,129));
		lblStatusConversation.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblStatusConversation.setBounds(95,45,70,30);
		lblStatusConversation.setVisible(true);
		conversaPanel.add(lblStatusConversation);
		
		conversacaoPanel = new JPanel();
		conversacaoPanel.setBackground(Color.white);
		conversacaoPanel.setLayout(null);
		
		scrollPane = new LightScrollPane(conversacaoPanel);
		scrollPane.setBorder(null);
		scrollPane.setLocation(5, 110);
		scrollPane.setSize(535,250);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		conversacaoPanel.revalidate();

		conversaPanel.add(scrollPane);
		
		
		JLabel lblSeparadorConversationTxtArea = new JLabel("_______________________________________________________________________________________________________");
		lblSeparadorConversationTxtArea.setForeground(new Color(191,191,191));
		lblSeparadorConversationTxtArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSeparadorConversationTxtArea.setBounds(1,350,558,20);
		lblSeparadorConversationTxtArea.setVisible(true);
		conversaPanel.add(lblSeparadorConversationTxtArea);
		
		txtMessage = new JTextArea("Digite sua mensagem aqui...");
		txtMessage.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		txtMessage.setForeground(Color.BLACK);
		txtMessage.setCaretColor(Color.BLACK);
		txtMessage.setLineWrap(true);
		txtMessage.setWrapStyleWord(true);
		txtMessage.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
				    e.consume();
				    sendMessage(txtMessage.getText());
				}
			}
		});
		txtMessage.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtMessage.setText("");
			}
		});
		JScrollPane areaScrollPane = new JScrollPane(txtMessage);
		areaScrollPane.setVerticalScrollBarPolicy(
			                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		areaScrollPane.setPreferredSize(new Dimension(550, 60));
		areaScrollPane.setBounds(5, 380, 500, 60);
		areaScrollPane.setBorder(null);
		areaScrollPane.setVisible(true);
		conversaPanel.add(areaScrollPane);
		
		JLabel lblSend = new JLabel();
		lblSend.setIcon(new ImageIcon(this.getClass().getResource("/imgs/send.png")));
		lblSend.setBounds(510, 395, 30, 30);
		lblSend.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sendMessage(txtMessage.getText());
			}
		});
		conversaPanel.add(lblSend);
	}

	private void sendMessage(String message) {
		JTextArea messageSent = new JTextArea(" " + message);
		messageSent.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		messageSent.setForeground(Color.BLACK);
		messageSent.setBackground(new Color(107,185,240));
		messageSent.setEditable(false);
		messageSent.setLineWrap(true);
		messageSent.setWrapStyleWord(true);
		int width = message.length() * 10;
		if(width > 260)
			width = 230;
		int height = ((message.length() / 40) + 1)*23;
		messageSent.setBounds(250+(250-width)+20, countMessages * (height + 20), width, height);
		conversacaoPanel.add(messageSent);
		
		JLabel timeMessage = new JLabel(LocalTime.now().toString().substring(0, LocalTime.now().toString().indexOf(".")));
		timeMessage.setForeground(Color.BLACK);
		timeMessage.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		timeMessage.setBounds(480,countMessages * (height + 20)+height -5,60,30);
		timeMessage.setVisible(true);
		conversacaoPanel.add(timeMessage);
		
		conversacaoPanel.setPreferredSize(new Dimension((int) 535,
				countMessages * (height + 20)));
		
		conversacaoPanel.repaint();
		conversacaoPanel.revalidate();
		JScrollBar sb = scrollPane.getVerticalScrollBar();
		sb.setValue( sb.getMaximum() );
		txtMessage.setText("");
		countMessages++;
		Core.getInstance().enviarMensagem(message, this.userConversation, user);
	}
	
	public void receiveMessage(String message) {
		JTextArea messageReceive = new JTextArea(" " + message);
		messageReceive.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		messageReceive.setForeground(Color.BLACK);
		messageReceive.setBackground(new Color(197,239,247));
		messageReceive.setEditable(false);
		messageReceive.setLineWrap(true);
		messageReceive.setWrapStyleWord(true);
		int width = message.length() * 10;
		int height = ((message.length() / 40) + 1)*23;
		if(width > 260){
			width = 250;
			height+= 23;
		}
		messageReceive.setBounds(5, countMessages * (height + 20), width, height);
		conversacaoPanel.add(messageReceive);
		
		JLabel timeMessage = new JLabel(LocalTime.now().toString().substring(0, LocalTime.now().toString().indexOf(".")));
		timeMessage.setForeground(Color.BLACK);
		timeMessage.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		timeMessage.setBounds(5,countMessages * (height + 20)+height -5,60,30);
		timeMessage.setVisible(true);
		conversacaoPanel.add(timeMessage);
		
		conversacaoPanel.setPreferredSize(new Dimension((int) 535,
				countMessages * (height + 30)));
		
		conversacaoPanel.repaint();
		conversacaoPanel.revalidate();
		JScrollBar sb = scrollPane.getVerticalScrollBar();
		sb.setValue( sb.getMaximum() );
		txtMessage.setText("");
		countMessages++;
	}

	private JPanel createUserPanel(String user) {
		
		JPanel userChatPanel = new JPanel();
		userChatPanel.setBounds(1, 218 + (70*contUsers++), 195, 70);
		userChatPanel.setBackground(new Color(245,250,253));
		userChatPanel.setLayout(null);
		userChatPanel.setToolTipText("Abra o chat com " + user.substring(0, 1).toUpperCase() + user.substring(1));
		userChatPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				conversaPanel.removeAll();
				conversaPanel.repaint();
				conversaPanel.revalidate();
				openChat(user);
			}
		});
		
		JLabel userPhoto;
		int userSize = 50;
		
		try {
			userPhoto = drawPhoto(user, userSize);
		} catch (IOException e1) {
			System.out.println("ERROR to upload photo");
			userPhoto = new JLabel();
			userPhoto.setIcon(new ImageIcon(this.getClass().getResource("/imgs/users/user.png")));
		}
		
		userPhoto.setBounds(20, 10, userSize, userSize);
		userPhoto.setVisible(true);
		userChatPanel.add(userPhoto);
		
		JLabel lblUserNameChat = new JLabel(user.substring(0, 1).toUpperCase() + user.substring(1));
		lblUserNameChat.setForeground(Color.BLACK);
		lblUserNameChat.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblUserNameChat.setBounds(85,10,70,30);
		lblUserNameChat.setVisible(true);
		userChatPanel.add(lblUserNameChat);
		
		JLabel lblStatus = new JLabel("Online");
		lblStatus.setForeground(new Color(38,194,129));
		lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblStatus.setBounds(85,30,70,30);
		lblStatus.setVisible(true);
		userChatPanel.add(lblStatus);
		return userChatPanel;
	}
	
	private JLabel drawPhoto(String user, int size) throws IOException {

		BufferedImage master = scale(ImageIO.read(this.getClass().getResource("/imgs/users/" + user + ".png")),size,size);
	    int diameter = Math.min(master.getWidth(), master.getHeight());
	    BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = mask.createGraphics();
	    applyQualityRenderingHints(g2d);
	    g2d.fillOval(0, 0, diameter - 1, diameter - 1);
	    g2d.dispose();

	    BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	    g2d = masked.createGraphics();
	    applyQualityRenderingHints(g2d);
	    int x = (diameter - master.getWidth()) / 2;
	    int y = (diameter - master.getHeight()) / 2;
	    g2d.drawImage(master, x, y, null);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
	    g2d.drawImage(mask, 0, 0, null);
	    g2d.dispose();
	    
	    JLabel photo = new JLabel(new ImageIcon(masked));
	    photo.setVisible(true);
	    
	    return photo;
	    
	}
	
	public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            applyQualityRenderingHints(graphics2D);
            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        return scaledImage;
    }
	
	public static void applyQualityRenderingHints(Graphics2D g2d) {

	    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	}
	
	/**
	 * If b is true, it will show the systems border, if false, the system's
	 * border will not show up.
	 * 
	 * @param b
	 */
	private void setSystemBorder(boolean b) {
		if (!b) {
			//log.info("Deactivating system border...");
			frame.setUndecorated(true);
			frame.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					clicked = e.getPoint();
					frame.getComponentAt(clicked);
				}
			});

			frame.addMouseMotionListener(new MouseAdapter() {

				@Override
				public void mouseDragged(MouseEvent e) {
					// get location of Window
					int thisX = frame.getLocation().x;
					int thisY = frame.getLocation().y;

					// Determine how much the mouse moved since the initial
					// click
					int xMoved = (thisX + e.getX()) - (thisX + clicked.x);
					int yMoved = (thisY + e.getY()) - (thisY + clicked.y);

					// Move window to this position
					int X = thisX + xMoved;
					int Y = thisY + yMoved;
					frame.setLocation(X, Y);
				}
			});
		}
	}
	
	public void addUser(String user){
		users.add(user);
		mainPanel.add(createUserPanel(user));
		mainPanel.repaint();
		mainPanel.revalidate();
	}
	
	public ArrayList<String> getUsers(){
		return this.users;
	}
	
	public static Tela getInstance(){
		return instance;
	}
	
}
