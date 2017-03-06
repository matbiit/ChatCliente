package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopUpMessage extends JDialog  {
	
	private static final long serialVersionUID = 1L;
	private PopUpMessage instance = this;
	private JLabel iconClose;
	private int xSize, ySize, xLoc, yLoc;
	private Insets toolHeight;
	private Dimension scrSize;
	private boolean isSleeping = false;

	public PopUpMessage(String user){
		initialize(user);
		waitAndDown();
	}
	
	private void waitAndDown() {
		Runnable down = new Runnable() {
			@Override
			public void run() {
				try {
					down();
				} catch (Exception e) {
				}
			}
		};

		Runnable sleep = new Runnable() {
			@Override
			public void run() {
				try {
					sleep();
				} catch (Exception e) {
					
				}
			}

			
		};
		
		Thread t1 = new Thread(sleep);
		Thread t2 = new Thread(down);
		
		t1.start();
		t2.start();
		
		
	}
	
	private synchronized void sleep() throws InterruptedException {
		Thread.sleep(5000);
		isSleeping  = true;
		notify();
	}

	private synchronized void down() throws InterruptedException {
		while (!isSleeping) {
			wait();
		}
		slowDown(ySize, xLoc, yLoc);
		isSleeping = false;
		instance.dispose();
	}

	private void initialize( String user) {
		
		JPanel content = new JPanel();
		
		xSize = 300;
		ySize = 125;
		
		content.setSize(xSize,ySize);
		scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
		xLoc = scrSize.width - xSize;
		yLoc = scrSize.height - toolHeight.bottom - ySize;
		content.setLocation(xLoc, yLoc);
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
				instance.setVisible(false);
//				instance.dispose();
			}

		});
		content.add(iconClose);
		
		JLabel chatHeader = new JLabel("Chat GEC4");
		chatHeader.setForeground(new Color(34,167,240));
		chatHeader.setOpaque(false);
		chatHeader.setBounds(10, 12, 200, 10);
		chatHeader.setVisible(true);
		content.add(chatHeader);
		
		JLabel systemLogo = new JLabel();
		systemLogo.setIcon(new ImageIcon(this.getClass().getResource("/imgs/user/" + user.toLowerCase() + ".png")));
		systemLogo.setBounds(20, 40, 60, 60);
		systemLogo.setVisible(true);
		content.add(systemLogo);
		
		JLabel headerNot = new JLabel(user);
		headerNot.setForeground(new Color(34,167,240));
		headerNot.setOpaque(false);
		headerNot.setBounds(95, 40, 200, 10);
		headerNot.setVisible(true);
		content.add(headerNot);
		
		JLabel messageLabel = new JLabel("<html> acabou de entrar. </html>");
		messageLabel.setForeground(new Color(34,167,240));
		messageLabel.setBounds(95, 65, 200, 10);
		content.add(messageLabel);
		
		content.setBorder(BorderFactory.createLineBorder(new Color(191,191,191)));
		content.setVisible(true);
		
		this.setAlwaysOnTop(false);
		this.add(content);
		
		this.setSize(xSize,ySize);
		this.setUndecorated(true);
		this.setVisible(true);
		slowUp(ySize, xLoc, yLoc);
	}

	private void slowDown(int ySize, int xLoc, int yLoc) {
		for (int y = yLoc; y < scrSize.height; y += 5) {
			this.setLocation(xLoc, y);
			try {
				Thread.sleep(25);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		this.setLocation(xLoc, scrSize.height);
		
	}
	
	private void slowUp(int ySize, int xLoc, int yLoc) {
		for (int y = yLoc + ySize; y > yLoc; y -= 5) {
			this.setLocation(xLoc, y);
			try {
				Thread.sleep(25);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		this.setLocation(xLoc, yLoc);
	}
	
	public static void main(String[] args) {
		new PopUpMessage("Helama");
	}

}
