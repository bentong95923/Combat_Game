package main.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Exit extends GameState {

	private int gms = 0;	

	BufferedImage exitBg;
	
	public List<BufferedImage> exitStr = new ArrayList<BufferedImage>();
	public BufferedImage menu = null;
	
	BufferedImage ply1, ply2, ply1wintxt, ply2wintxt,drawtxt;
	boolean wait = true, ply1win = false, ply2win = false, draw = false;
	
	int scoreToDisplay = 0;
	
	int scoreply1, scoreply2;
	
	public Exit(StateManager sm, int gms, int scoreply1, int scoreply2) {
		this.sm = sm;
		this.gms = gms;
		this.scoreply1 = scoreply1;
		this.scoreply2 = scoreply2;
		
	}
	
	public void init() {
		
		menu = imgLoader.loadingImage("/font/exit/menu.png");
				
		if (scoreply1 > scoreply2) {
			ply1win = true;
			scoreToDisplay = scoreply1;
			
		} else if (scoreply1 < scoreply2) {
			ply2win = true;
			scoreToDisplay = scoreply2;
		} else {
			draw = true;
			scoreToDisplay = scoreply2;
		}
		
		// Singleplayer
		if (gms == 0) {			
			if (ply1win) {
				
				exitBg = imgLoader.loadingImage("/img/backgrounds/won.jpg");
				exitStr.add(imgLoader.loadingImage("/font/exit/single/youwon.png"));
				exitStr.add(imgLoader.loadingImage("/font/exit/single/congratulation.png"));
				
			} else if (ply2win) {
				exitBg = imgLoader.loadingImage("/img/backgrounds/lost.jpg");
				exitStr.add(imgLoader.loadingImage("/font/exit/single/youlost.png"));
				exitStr.add(imgLoader.loadingImage("/font/exit/single/dunbesad.png"));
			} else {
				exitBg = imgLoader.loadingImage("/img/backgrounds/gameover.jpg");
				exitStr.add(imgLoader.loadingImage("/font/exit/single/draw.png"));
				exitStr.add(imgLoader.loadingImage("/font/exit/single/keepitup.png"));
			}
		// Multiplayer
		} else if (gms == 1) {
			exitBg = imgLoader.loadingImage("/img/backgrounds/gameover.jpg");
			if (ply1win) {
				exitStr.add(imgLoader.loadingImage("/font/exit/multi/player1win.png"));
				exitStr.add(imgLoader.loadingImage("/font/exit/multi/congratulation.png"));
				
			} else if (ply2win) {
				exitStr.add(imgLoader.loadingImage("/font/exit/multi/player2win.png"));
				exitStr.add(imgLoader.loadingImage("/font/exit/multi/havingfun.png"));
			} else {
				exitStr.add(imgLoader.loadingImage("/font/exit/multi/draw.png"));
				exitStr.add(imgLoader.loadingImage("/font/exit/multi/keepitup.png"));
			}
		// Training
		} else if (gms == 2) {
			exitBg = imgLoader.loadingImage("/img/backgrounds/gameover.jpg");
			exitStr.add(imgLoader.loadingImage("/font/exit/training/gameover.png"));
			exitStr.add(imgLoader.loadingImage("/font/exit/training/practice.png"));
		}
		
	}

	public void tick() {
	
		// wait for the background image to load
		if (wait) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wait = false;
		}
	}

	public void render(Graphics g) {
		// Background image
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(exitBg, (int)0,(int)0, null);
		
		int posXtitle = 0, posYtitle = 0, posXtxt= 0, posYtxt = 0;		

		// Text positioning for exit screen
		if  (gms == 0) {
			if (ply1win) {
				posXtitle = 275; posYtitle = 200;
				posXtxt = 245; posYtxt = 400;
			} else if (ply2win) {
				posXtitle = 295; posYtitle = 200;
				posXtxt = 17; posYtxt = 400;
			} else {
				posXtitle = 355; posYtitle = 200;
				posXtxt = 400; posYtxt = 400;
			}
		} else if  (gms == 1) {
			if (ply1win) {
				posXtitle = 317; posYtitle = 200;
				posXtxt = 245; posYtxt = 400;
			} else if (ply2win) {
				posXtitle = 317; posYtitle = 200;
				posXtxt = 368; posYtxt = 400;
			} else {
				posXtitle = 355; posYtitle = 200;
				posXtxt = 400; posYtxt = 400;
			}
		} else if  (gms == 2) {
			posXtitle = 220; posYtitle = 150;
			posXtxt = 325; posYtxt = 400;			
		}
		g2d.drawImage(exitStr.get(0), posXtitle, posYtitle, null);
		// single and multiplayer
		g2d.drawImage(exitStr.get(1), posXtxt, posYtxt, null);
		
		g2d.drawImage(menu, 800, 600, null);
	}

	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_ENTER) {
			sm.setState(StateManager.MENU);
		}
		
	}

	public void keyReleased(KeyEvent k) {
		
	}

}
