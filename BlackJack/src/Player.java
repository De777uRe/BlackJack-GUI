import javax.swing.JLabel;
import javax.swing.JPanel;


public class Player {

	String pName;
	int pNumber;
	int pHand1;
	int pHand2;
	int pScore;
	
	int aceCount;
	
	JLabel pLabel;
	JLabel pCard1;
	JLabel pCard2;
	
	JPanel pPanel;
	
	boolean busted;
	
	public Player(String name, int number, JPanel pPanel, JLabel pLabel, JLabel card1, JLabel card2) {
		pName = name;
		pNumber = number;
		pHand1 = 0;
		pHand2 = 0;
		pScore = 0;
		aceCount = 0;
		busted = false;
		
		this.pPanel = pPanel;
		
		this.pLabel = pLabel;
		this.pCard1 = card1;
		this.pCard2 = card2;
	}
	
	public JPanel getpPanel() {
		return pPanel;
	}

	public void setpPanel(JPanel pPanel) {
		this.pPanel = pPanel;
	}

	public JLabel getpCard1() {
		return pCard1;
	}

	public void setpCard1(JLabel pCard1) {
		this.pCard1 = pCard1;
	}

	public JLabel getpCard2() {
		return pCard2;
	}

	public void setpCard2(JLabel pCard2) {
		this.pCard2 = pCard2;
	}
	

}
