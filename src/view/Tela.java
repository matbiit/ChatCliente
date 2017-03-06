package view;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tela {

	private JFrame frame;
	private JLabel lblMinimize;
	private Point clicked;
	private String user = Login.getInstance().getUser().toLowerCase();
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public Tela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		mainPanel.setBackground(new Color(245,250,253));
		mainPanel.setBorder(BorderFactory.createLineBorder(new Color(30,30,30)));
		mainPanel.setLayout(null);

		JLabel photo;
		int size = 60;
		
		try {
			photo = drawPhoto(size);
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
		
		JPanel conversaPanel = new JPanel();
		conversaPanel.setBounds(200, 35, 550, 445);
		conversaPanel.setBackground(Color.WHITE);
		conversaPanel.setBorder(BorderFactory.createLineBorder(new Color(191,191,191)));
		conversaPanel.setLayout(null);
		
		mainPanel.setOpaque(false);
		
		frame.add(mainPanel);
		frame.add(cabecalhoPanel);
		frame.add(conversaPanel);
		
		frame.setVisible(true);
		
	}
	
	private JLabel drawPhoto(int size) throws IOException {
		
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString();
		path = path.concat(File.separator + "src" + File.separator + "imgs" + File.separator + "users" + File.separator);
		File image = new File( path + user.toLowerCase() + ".png");
		
		BufferedImage master = scale(ImageIO.read(image),size,size);
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
	
}
