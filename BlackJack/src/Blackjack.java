import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/*
 * Main Class
 */

public class Blackjack extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -1446525828686311095L;

	private JButton btnTwoPlayers   = new JButton("Two");
    private JButton btnThreePlayers = new JButton("Three");
    private JButton btnFourPlayers  = new JButton("Four");
    
    JPanel selectionPanel;
	
    /*
     * Build the blackjack GUI
     */
    
	Blackjack(){
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        selectionPanel = new JPanel();
        selectionPanel.setPreferredSize(new Dimension(100, 100));
        selectionPanel.add(btnTwoPlayers);
        selectionPanel.add(btnThreePlayers);
        selectionPanel.add(btnFourPlayers);
        btnTwoPlayers.addActionListener(this);
        btnThreePlayers.addActionListener(this);
        btnFourPlayers.addActionListener(this);
        btnTwoPlayers.setEnabled(true);
        btnThreePlayers.setEnabled(true);
        btnFourPlayers.setEnabled(true);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(selectionPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        /*
         * Allow user to pick how many players will be playing
         */
        JOptionPane.showMessageDialog(null, "Select the number of players", "Player Count", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		new Blackjack();
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * Initialize game based on how many players are playing
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnTwoPlayers){
			this.setVisible(false);
			this.dispose();
			new GamePanel(2);
		}
		else if(e.getSource() == btnThreePlayers){
			this.setVisible(false);
			this.dispose();
			new GamePanel(3);
		}
		else if(e.getSource() == btnFourPlayers){
			this.setVisible(false);
			this.dispose();
			new GamePanel(4);
		}
	}

}
