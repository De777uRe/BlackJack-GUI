import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ControlBoard extends JFrame{
	
	JPanel mPanel;
	
	/*
	 * Generate panel that contains buttons and deck/last card
	 */
	ControlBoard(JButton hit, JButton stay, JButton deal, JButton reset, JPanel cPanel){
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        mPanel = new JPanel();
        JPanel s1Panel = new JPanel();
        s1Panel.setPreferredSize(new Dimension(100, 50));
        JPanel s2Panel = new JPanel();
        s2Panel.setPreferredSize(new Dimension(100, 50));
        mPanel.setPreferredSize(new Dimension(200, 200));
        mPanel.add(hit);
        mPanel.add(stay);
        mPanel.add(deal);
        mPanel.add(reset);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mPanel, BorderLayout.PAGE_START);
        getContentPane().add(s1Panel, BorderLayout.LINE_START);
        getContentPane().add(reset, BorderLayout.CENTER);
        getContentPane().add(s2Panel, BorderLayout.LINE_END);
        getContentPane().add(cPanel, BorderLayout.PAGE_END);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}

}
