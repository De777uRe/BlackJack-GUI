import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 6484085339344515089L;

	int numPlayers;
	
	public LinkedList<Player> playerList = new LinkedList<Player>();
	
	protected JPanel gameScreen = new JPanel(new BorderLayout(100, 100));
	
	public Deck deck;
	
	protected JPanel dealerPanel = new JPanel(new BorderLayout());
	protected JPanel dealerCardsPanel = new JPanel(new BorderLayout());
	protected JPanel pDupPanel = new JPanel(new BorderLayout());
	protected JPanel p1Panel = new JPanel(new BorderLayout());
	protected JPanel p1CardsPanel = new JPanel(new BorderLayout());
	protected JPanel p2CardsPanel = new JPanel(new BorderLayout());
	protected JPanel p3CardsPanel = new JPanel(new BorderLayout());
	protected JPanel p4CardsPanel = new JPanel(new BorderLayout());
	protected JPanel p2Panel = new JPanel(new BorderLayout());
	protected JPanel p4Panel = new JPanel(new BorderLayout());
	protected JPanel p3Panel = new JPanel(new BorderLayout());
	
	protected JLabel lblDealer;
	protected JLabel lblDealerCard1;
	protected JLabel lblDealerCard2;
	protected JLabel lblP1;
	protected JLabel lblP1Card1;
	protected JLabel lblP1Card2;
	protected JLabel lblP2;
	protected JLabel lblP2Card1;
	protected JLabel lblP2Card2;
	protected JLabel lblP3;
	protected JLabel lblP3Card1;
	protected JLabel lblP3Card2;
	protected JLabel lblP4;
	protected JLabel lblP4Card1;
	protected JLabel lblP4Card2;
	protected JLabel nextCard;
	protected JButton btnHit;
	protected JButton btnStay;
	protected JButton btnDeal;
	protected JButton btnReset;
	
	protected Boolean isContinued = false;
	
	public String dealer = "Dealer";
	public int dealerHand1 = 0;
	public int dealerHand2 = 0;
	public int dealerAceCount = 0;
	public int turn = 1;
	
	public Player currentPlayer;
	
	public Timer timer = new Timer(1500, this);
	
	/*
	 * Initialize number of players and build GUI
	 */
	public GamePanel(int numPlayers) {
		this.numPlayers = numPlayers;
		
		buildGUI();
	}
	
	public void buildGUI(){
		setTitle("Blackjack");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gameScreen.setPreferredSize(new Dimension(1000, 1000));
		
		/*
		 * Create and shuffle deck
		 */
		deck = new Deck();
		deck.shuffle();
		
		/*
		 * Set up dealerFrame
		 */
        lblDealer = new JLabel(dealer + " Hand: " + dealerHand1);
        JPanel dealerCardsPanel = new JPanel();
        URL dealerCard1URL = this.getClass().getResource("Images/dealerBackVertical.png");
        ImageIcon dealerCard1Img = new ImageIcon(dealerCard1URL);
        lblDealerCard1 = new JLabel(dealerCard1Img);
        URL dealerCard2URL = this.getClass().getResource("Images/dealerBackVertical.png");
        ImageIcon dealerCard2Img = new ImageIcon(dealerCard2URL);
        lblDealerCard2 = new JLabel(dealerCard2Img);
        lblDealer.setHorizontalAlignment(JLabel.CENTER);
        lblDealer.setVerticalAlignment(JLabel.CENTER);
        dealerPanel.add(lblDealer, BorderLayout.PAGE_START);
        dealerCardsPanel.add(lblDealerCard1);
        dealerCardsPanel.add(lblDealerCard2);
        dealerPanel.add(dealerCardsPanel, BorderLayout.CENTER);
        gameScreen.add(dealerPanel, BorderLayout.PAGE_START);
        
        
        /*
         * Set up Player 1
         */
        JPanel p1Panel = new JPanel(new BorderLayout());
        JPanel p1CardsPanel = new JPanel();
        URL p1Card1URL = this.getClass().getResource("Images/playerBackVertical.png");
        ImageIcon p1Card1Img = new ImageIcon(p1Card1URL);
        lblP1Card1 = new JLabel(p1Card1Img);
        URL p1Card2URL = this.getClass().getResource("Images/playerBackVertical.png");
        ImageIcon p1Card2Img = new ImageIcon(p1Card2URL);
        lblP1Card2 = new JLabel(p1Card2Img);
        lblP1 = new JLabel("Player 1 Hand: 0 Score: 0");
        lblP1.setForeground(Color.MAGENTA);
        Player player1 = new Player("Player 1", 1, p1CardsPanel, lblP1, lblP1Card1, lblP1Card2);
        playerList.add(player1);
        p1Panel.add(lblP1, BorderLayout.PAGE_START);
        p1CardsPanel.add(lblP1Card1);
        p1CardsPanel.add(lblP1Card2);
        p1Panel.add(p1CardsPanel, BorderLayout.CENTER);
        
        /*
         * Set up Player 2
         */
        JPanel p2Panel = new JPanel(new BorderLayout());
        JPanel p2CardsPanel = new JPanel();
        URL p2Card1URL = this.getClass().getResource("Images/playerBackVertical.png");
        ImageIcon p2Card1Img = new ImageIcon(p2Card1URL);
        lblP2Card1 = new JLabel(p2Card1Img);
        URL p2Card2URL = this.getClass().getResource("Images/playerBackVertical.png");
        ImageIcon p2Card2Img = new ImageIcon(p2Card2URL);
        lblP2Card2 = new JLabel(p2Card2Img);
        lblP2 = new JLabel("Player 2 Hand: 0 Score: 0");
        Player player2 = new Player("Player 2", 2, p2CardsPanel, lblP2, lblP2Card1, lblP2Card2);
        playerList.add(player2);
        p2Panel.add(lblP2, BorderLayout.PAGE_START);
        p2CardsPanel.add(lblP2Card1);
        p2CardsPanel.add(lblP2Card2);
        p2Panel.add(p2CardsPanel, BorderLayout.CENTER);
        
        pDupPanel.add(p1Panel, BorderLayout.WEST);
        pDupPanel.add(p2Panel, BorderLayout.EAST);
        
        gameScreen.add(pDupPanel, BorderLayout.PAGE_END);
        
        /*
         * Set up Player 3
         */
        if(numPlayers >= 3){
        	JPanel p3CardsPanel = new JPanel();
        	URL p3Card1URL = this.getClass().getResource("Images/playerBackVertical.png");
        	ImageIcon p3Card1Img = new ImageIcon(p3Card1URL);
        	lblP3Card1 = new JLabel(p3Card1Img);
        	URL p3Card2URL = this.getClass().getResource("Images/playerBackVertical.png");
        	ImageIcon p3Card2Img = new ImageIcon(p3Card2URL);
        	lblP3Card2 = new JLabel(p3Card2Img);
        	lblP3 = new JLabel("Player 3 Hand: 0 Score: 0");
        	Player player3 = new Player("Player 3", 3, p3CardsPanel, lblP3, lblP3Card1, lblP3Card2);
        	playerList.add(player3);
        	p3Panel.add(lblP3, BorderLayout.PAGE_START);
        	p3CardsPanel.add(lblP3Card1);
        	p3CardsPanel.add(lblP3Card2);
        	p3Panel.add(p3CardsPanel, BorderLayout.CENTER);
        	gameScreen.add(p3Panel, BorderLayout.WEST);
        }
        
        /*
         * Set up Player 4
         */
        if(numPlayers == 4){
        	JPanel p4CardsPanel = new JPanel();
        	URL p4Card1URL = this.getClass().getResource("Images/playerBackVertical.png");
        	ImageIcon p4Card1Img = new ImageIcon(p4Card1URL);
        	lblP4Card1 = new JLabel(p4Card1Img);
        	URL p4Card2URL = this.getClass().getResource("Images/playerBackVertical.png");
        	ImageIcon p4Card2Img = new ImageIcon(p4Card2URL);
        	lblP4Card2 = new JLabel(p4Card2Img);
        	lblP4 = new JLabel("Player 4 Hand: 0 Score: 0");
        	Player player4 = new Player("Player 4", 4, p4CardsPanel, lblP4, lblP4Card1, lblP4Card2);
        	playerList.add(player4);
        	p4Panel.add(lblP4, BorderLayout.PAGE_START);
        	p4CardsPanel.add(lblP4Card1);
        	p4CardsPanel.add(lblP4Card2);
        	p4Panel.add(p4CardsPanel, BorderLayout.CENTER);
        	gameScreen.add(p4Panel, BorderLayout.EAST);
        }
        
        /*
         * Set up Control Board
         */
        btnHit = new JButton("Hit");
        btnHit.addActionListener(this);
        btnStay = new JButton("Stay");
        btnStay.addActionListener(this);
        btnDeal = new JButton("Deal");
        btnDeal.addActionListener(this);
        btnReset = new JButton("Reset");
        btnReset.addActionListener(this);
        JPanel deckPanel = new JPanel();
        URL nextCardURL = this.getClass().getResource("Images/J1.png");
        ImageIcon nextCardImg = new ImageIcon(nextCardURL);
        nextCard = new JLabel(nextCardImg);
        URL deckURL = this.getClass().getResource("Images/dealerBackVertical.png");
        ImageIcon deckImg = new ImageIcon(deckURL);
        JLabel deck = new JLabel(deckImg);
        deckPanel.add(nextCard);
        deckPanel.add(deck);
        JFrame ctrlbrd = new ControlBoard(btnHit, btnStay, btnDeal, btnReset, deckPanel);
        ctrlbrd.setAlwaysOnTop(true);
        
        btnHit.setEnabled(false);
        btnStay.setEnabled(false);
        
		getContentPane().setLayout(new BorderLayout());
        getContentPane().setPreferredSize(new Dimension(1000, 750));
        setMaximumSize(new Dimension(1000, 750));
        setMinimumSize(new Dimension(1000, 750));
        setPreferredSize(new Dimension(1000, 750));
        getContentPane().add(gameScreen, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnHit){
			/*
			 * Draw card and place on panel
			 */
			Card dCard = deck.dealFrom();
			URL dURL = this.getClass().getResource("Images/" + Integer.toString(dCard.getValue()) + Character.toString(dCard.getSuit()) + ".png");
			ImageIcon dImg = new ImageIcon(dURL);
			nextCard.setIcon(dImg);
			/*
			 * Calculate player hand
			 */
			currentPlayer.pHand1 += dCard.getScore();
			JLabel lblNCard = new JLabel(dImg);
			currentPlayer.pPanel.add(lblNCard);
			/*
			 * If hit BLACKJACK, display
			 */
			if(currentPlayer.pHand1 == 21){
				currentPlayer.pLabel.setText(currentPlayer.pName + " BLACKJACK" + " Score: " + currentPlayer.pScore);
				changeTurns();
			}
			/*
			 * If the card hit was an ace, add it to count
			 */
			if(dCard.getValue() == 14){
				currentPlayer.aceCount++;
			}
			/*
			 * If the player busts, check for aces and adjust
			 */
			if(currentPlayer.pHand1 > 21){
				while(currentPlayer.aceCount > 0 && currentPlayer.pHand1 > 21){
					currentPlayer.pHand1 -= 10;
					currentPlayer.aceCount--;
				}
				if(currentPlayer.pHand1 > 21){
					currentPlayer.busted = true;
					currentPlayer.pLabel.setText(currentPlayer.pName + " BUSTED" + " Score: " + currentPlayer.pScore);
					changeTurns();
				}
				/*
				 * If player hand is BLACKJACK after adjustment, display it
				 */
				else if(currentPlayer.pHand1 == 21){
					currentPlayer.pLabel.setText(currentPlayer.pName + " BLACKJACK" + " Score: " + currentPlayer.pScore);
				}
				/*
				 * If nothing else, update hand
				 */
				else{
					currentPlayer.pLabel.setText(currentPlayer.pName + " Hand: " + currentPlayer.pHand1 + " Score: " + currentPlayer.pScore);
				}
			}
			else{
				currentPlayer.pLabel.setText(currentPlayer.pName + " Hand: " + currentPlayer.pHand1 + " Score: " + currentPlayer.pScore);
			}
		}
		
		if(e.getSource() == btnStay){
			/*
			 * If player stays, change turns
			 */
			changeTurns();
		}
		
		if(e.getSource() == btnDeal){
			/*
			 * If the panels are full, refresh them to clear
			 */
			for(Player player : playerList){
				while(player.pPanel.getComponentCount() > 2){
					player.pPanel.remove(player.pPanel.getComponent(2));
				}
			}

			/*
			 * Update Control Board
			 */
			URL nextCardURL = this.getClass().getResource("Images/J1.png");
			ImageIcon nextCardImg = new ImageIcon(nextCardURL);
	        nextCard.setIcon(nextCardImg);
			playerList.getFirst().pLabel.setForeground(Color.MAGENTA);
			URL dealerCard2URL = this.getClass().getResource("Images/dealerBackVertical.png");
			ImageIcon dealerCard2Img = new ImageIcon(dealerCard2URL);
	        lblDealerCard2.setIcon(dealerCard2Img);
			
			/*
			 * Shuffle deck
			 */
			deck.shuffle();
			
			currentPlayer = playerList.get(0);
			
			/*
			 * Give dealer first card
			 */
			Card dealtCard = deck.dealFrom();
			URL dealtURL = this.getClass().getResource("Images/" + Integer.toString(dealtCard.getValue()) + Character.toString(dealtCard.getSuit()) + ".png");
			ImageIcon dealtImg = new ImageIcon(dealtURL);
			lblDealerCard1.setIcon(dealtImg);
			if(dealtCard.getValue() == 14){
				dealerAceCount++;
			}
			dealerHand1 += dealtCard.getScore();
			lblDealer.setText(dealer + " Hand: " + dealerHand1);
			
			//Deal first card to players
			for(Player player : playerList){
				Card dCard = deck.dealFrom();
				URL dURL = this.getClass().getResource("Images/" + Integer.toString(dCard.getValue()) + Character.toString(dCard.getSuit()) + ".png");
				ImageIcon dImg = new ImageIcon(dURL);
				player.pCard1.setIcon(dImg);
				if(dCard.getValue() == 14){
					player.aceCount++;
				}
				player.pHand1 += dCard.getScore();
				player.pLabel.setText(player.pName + " Hand: " + player.pHand1 + " Score: " + player.pScore);
			}
			
			//Deal second card to players
			for(Player player : playerList){
				Card dCard = deck.dealFrom();
				URL dURL = this.getClass().getResource("Images/" + Integer.toString(dCard.getValue()) + Character.toString(dCard.getSuit()) + ".png");
				ImageIcon dImg = new ImageIcon(dURL);
				player.pCard2.setIcon(dImg);
				if(dCard.getValue() == 14 && player.aceCount == 0){
					player.aceCount++;
					player.pHand1 += dCard.getScore();
					if(player.pHand1 > 21){
						player.pHand1 -= 10;
						player.aceCount--;
					}
				}
				else if(dCard.getValue() == 14 && player.aceCount == 1){
					player.pHand1 = 12;
					player.aceCount++;
				}
				else{
					player.pHand1 += dCard.getScore();
				}
				if(player.pHand1 == 21){
					player.pLabel.setText(player.pName + " BLACKJACK" + " Score: " + player.pScore);
					if(player.pNumber == 1){
						changeTurns();
					}
				}
				else{
					player.pLabel.setText(player.pName + " Hand: " + player.pHand1 + " Score: " + player.pScore);
				}
			}
			
			/*
			 * Disable buttons while round is occurring
			 */
			btnDeal.setEnabled(false);
			btnHit.setEnabled(true);
			btnStay.setEnabled(true);
			btnReset.setEnabled(false);
		}
		
		/*
		 * Button allows reset of scores
		 */
		if(e.getSource() == btnReset){
			for(Player player : playerList){
				player.pScore = 0;
				player.pLabel.setText(player.pName + " Hand: " + player.pHand1 + " Score: " + player.pScore);
			}
		}
		
		/*
		 * Timer begins after round is over and marks the start of the dealer's turn
		 */
		if(timer.isRunning()){
			/*
			 * Force the dealer to draw while his hand is less than 17
			 */
			if(dealerHand1 < 17){
				Card dCard = deck.dealFrom();
				URL dURL = this.getClass().getResource("Images/" + Integer.toString(dCard.getValue()) + Character.toString(dCard.getSuit()) + ".png");
				ImageIcon dImg = new ImageIcon(dURL);
				nextCard.setIcon(dImg);
				JLabel lblNCard = new JLabel(dImg);
				dealerCardsPanel.add(lblNCard);
				if(dCard.getValue() == 14){
					dealerAceCount++;
				}
				dealerHand1 += dCard.getScore();
				if(dealerHand1 > 21){
					while(dealerAceCount > 0 && dealerHand1 > 21){
						dealerHand1 -= 10;
						dealerAceCount--;
					}
					if(dealerHand1 > 21){
						lblDealer.setText(dealer + " BUSTED");
					}
					else{
						lblDealer.setText(dealer + " Hand: " + dealerHand1);
					}
				}
				else{
					lblDealer.setText(dealer + " Hand: " + dealerHand1);
				}
			}
			else{
				/*
				 * Dealer is done drawing
				 */
			if(dealerHand1 > 21){
				lblDealer.setText(dealer + " BUSTED");
			}
			/*
			 * Stop the timer and end the round
			 */
			timer.stop();
			calculateScores();
			newRound();
			}
		}
	}
	
	public void changeTurns(){
		/*
		 * Label text color marks change of player turn
		 */
		Player turnedPlayer = null;
		if(currentPlayer.pNumber == 1){
			currentPlayer.pLabel.setForeground(Color.BLACK);
			turnedPlayer = playerList.get(1);
			turnedPlayer.pLabel.setForeground(Color.MAGENTA);
			currentPlayer = turnedPlayer;
			/*
			 * If we reach a player who already has blackjack, we skip their turn
			 */
			if(currentPlayer.pHand1 == 21){
				changeTurns();
			}
		}
		else if(currentPlayer.pNumber == 2){
			currentPlayer.pLabel.setForeground(Color.BLACK);
			if(currentPlayer.pNumber < playerList.size()){
				turnedPlayer = playerList.get(2);
				turnedPlayer.pLabel.setForeground(Color.MAGENTA);
				currentPlayer = turnedPlayer;
				if(currentPlayer.pHand1 == 21){
					changeTurns();
				}
			}
			else{
				endRound();
			}
		}
		else if(currentPlayer.pNumber == 3){
			currentPlayer.pLabel.setForeground(Color.BLACK);
			if(currentPlayer.pNumber < playerList.size()){
				turnedPlayer = playerList.get(3);
				turnedPlayer.pLabel.setForeground(Color.MAGENTA);
				currentPlayer = turnedPlayer;
				if(currentPlayer.pHand1 == 21){
					changeTurns();
				}
			}
			else{
				endRound();
			}
		}
		else if(currentPlayer.pNumber == 4){
			currentPlayer.pLabel.setForeground(Color.BLACK);
			endRound();
		}
	}
	
	public void endRound(){
		/*
		 * Occurs after all turns are over
		 * We now reset the entire game
		 */
		btnHit.setEnabled(false);
		btnStay.setEnabled(false);
		Card dCard = deck.dealFrom();
		URL dURL = this.getClass().getResource("Images/" + Integer.toString(dCard.getValue()) + Character.toString(dCard.getSuit()) + ".png");
		ImageIcon dImg = new ImageIcon(dURL);
		lblDealerCard2.setIcon(dImg);
		if(dCard.getValue() == 14 && dealerAceCount == 0){
			dealerAceCount++;
			if(dealerHand1 > 21){
				dealerHand1 -= 10;
				dealerAceCount--;
			}
			dealerHand1 += dCard.getScore();
		}
		else if(dCard.getValue() == 14 && dealerAceCount == 1){
			dealerHand1 = 12;
			dealerAceCount++;
		}
		else{
			dealerHand1 += dCard.getScore();
		}
		lblDealer.setText(dealer + " Hand: " + dealerHand1);
		timer.start();
	}
	
	public void calculateScores(){
		/*
		 * Occurs after round is over
		 */
		for(Player player : playerList){
			if(player.pHand1 >= dealerHand1 && player.pHand1 <= 21){
				player.pScore++;
				player.pLabel.setText(player.pName + " Hand: " + player.pHand1 + " Score: " + player.pScore);
			}
			if(dealerHand1 > 21 && player.pHand1 <= 21){
				player.pScore++;
				player.pLabel.setText(player.pName + " Hand: " + player.pHand1 + " Score: " + player.pScore);
			}
		}
	}
	
	public void newRound(){
		/*
		 * Resets player and dealer values to mark new round
		 */
		isContinued = true;
		btnDeal.setEnabled(true);
		btnReset.setEnabled(true);
		dealerHand1 = 0;
		dealerAceCount = 0;
		for(Player player : playerList){
			player.pHand1 = 0;
			player.aceCount = 0;
		}
	}
	
}
