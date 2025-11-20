package index;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Main {
	
	public static void main(String[] args)
	{
		new Main();
	}
		
	public void createUI()
	{
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.black);

		// Cookie label
		cookiesLabel.setText("Cookies: " + cookies.getCookies());
		cookiesLabel.setBounds(10, -30, 200, 100);
		cookiesLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		cookiesLabel.setForeground(Color.white);
		
		// Cookies per second label
		cookiesPerSecondLabel.setText(rats.cookiesPerSecond + " per second");
		cookiesPerSecondLabel.setBounds(10, -5, 300, 100);
		cookiesPerSecondLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		cookiesPerSecondLabel.setForeground(Color.white);
		
		// Machine Price Label
		machinePriceLabel.setBounds(250, -30, 300, 100);
		machinePriceLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		machinePriceLabel.setForeground(Color.white);
		
		// Machine Description Label
		machineDescriptionLabel.setBounds(250, -10, 300, 100);
		machineDescriptionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		machineDescriptionLabel.setForeground(Color.white);

		// Cookie button per click
		cookieButton.setPreferredSize(new Dimension(150, 100));
		cookieButton.setBounds(120, 200, 250, 250);
		cookieButton.setBackground(Color.black);
		cookieButton.setBorder(null);
		cookieButton.setFocusPainted(false);

		ImageIcon cookieIcon = new ImageIcon(getClass().getResource("/resources/cookie.png"));
		cookieButton.setIcon(cookieIcon);
		
		// Rats machine button
		ratsButton.setText("Buy Rats!");
		ratsButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		ratsButton.setPreferredSize(new Dimension(150, 100));
		ratsButton.setBounds(10, 70, 150, 100);
		
		// Grandpa machine button
		grandpaButton.setText("Buy Grandpa!");
		grandpaButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		grandpaButton.setPreferredSize(new Dimension(150, 100));
		grandpaButton.setBounds(180, 70, 150, 100);
		
		// Frame component
		frame.add(cookiesLabel);
		frame.add(cookiesPerSecondLabel);
		frame.add(machinePriceLabel);
		frame.add(machineDescriptionLabel);
		frame.add(cookieButton);
		frame.add(ratsButton);
		frame.add(grandpaButton);
				
		// Frame styling
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void buttonMethod()
	{
		
		// Logic cookie button
		cookieButton.addActionListener(e -> {
			cookies.addCookie(new BigDecimal(1));
			cookiesLabel.setText("Cookies: " + cookies.getCookies().setScale(2, RoundingMode.HALF_UP));
		});
		
		// Logic rats button
		
		ratsButton.addActionListener(e -> {
			if(cookies.getCookies().compareTo(rats.price) >= 0) {
				cookies.subtractCookie(rats.price);
				rats.count = rats.count.add(BigDecimal.ONE);
				rats.price = rats.price.multiply(new BigDecimal(1.15));
				rats.cookiesPerSecond = rats.count.multiply(new BigDecimal(0.1));		
			}			
		});
		
		ratsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				machinePriceLabel.setText("Price: " + rats.price.setScale(1, RoundingMode.HALF_UP));
				machineDescriptionLabel.setText("Rats: (" + rats.count + ")");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				machinePriceLabel.setText(null);
				machineDescriptionLabel.setText(null);
			}
		});
		
		// Logic grandpa button
		grandpaButton.addActionListener(e -> {
			if(cookies.getCookies().compareTo(grandpa.price) >= 0){
				cookies.subtractCookie(grandpa.price);
				grandpa.count = grandpa.count.add(BigDecimal.ONE);
				grandpa.price = grandpa.price.multiply(new BigDecimal(1.35).setScale(1, RoundingMode.HALF_UP));
				grandpa.cookiesPerSecond = grandpa.cookiesPerSecond = grandpa.count.multiply(new BigDecimal(1));
			}
		});
		
		grandpaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				machinePriceLabel.setText("Price: " + grandpa.price.setScale(1, RoundingMode.HALF_UP));
				machineDescriptionLabel.setText("Grandpa (" + grandpa.count + ")");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				machinePriceLabel.setText(null);
				machineDescriptionLabel.setText(null);
			}
		});
	}
	
	// Variables initiate place //
	JFrame frame = new JFrame();
	JLabel cookiesLabel = new JLabel();
	JLabel machinePriceLabel = new JLabel();
	JLabel machineDescriptionLabel = new JLabel();
	JLabel cookiesPerSecondLabel = new JLabel();
	
	Cookies cookies = new Cookies(new BigDecimal(0));
	Rats rats = new Rats();
	Grandpa grandpa = new Grandpa();
	
	JButton cookieButton = new JButton();
	JButton ratsButton = new JButton();
	JButton grandpaButton = new JButton();
	
	public void updateCookies()
	{
		cookies.addCookie(rats.cookiesPerSecond);
		cookies.addCookie(grandpa.cookiesPerSecond);
	}
	
	public Main()
	{
		createUI();
		buttonMethod();
		
	    Timer timer = new Timer(10, e -> {
	        updateCookies();
	        cookiesLabel.setText("Cookies: " + cookies.getCookies().setScale(2, RoundingMode.HALF_UP));
	        
	        BigDecimal totalCookiesPerSecond = BigDecimal.ZERO;
	        totalCookiesPerSecond = totalCookiesPerSecond.add(rats.cookiesPerSecond);
	        totalCookiesPerSecond = totalCookiesPerSecond.add(grandpa.cookiesPerSecond);
	        cookiesPerSecondLabel.setText(totalCookiesPerSecond.setScale(1, RoundingMode.HALF_UP) + " per second");
	    });
	    timer.start();
	}
}
