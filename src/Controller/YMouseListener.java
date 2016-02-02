package Controller;

import javax.swing.*;
import View.DiceView;
import View.GamePanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/** 
 * YMouseListener extends MouseAdapter and implements only one method
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 2, 2015
 */

public class YMouseListener extends MouseAdapter {
	private ImageIcon icon; 

	/** 
	 * This implemented method triggers an event by using panelEvent and PanelListener. The source, DiceView, is passing on its information to to Model
	 * by calling the method panelEventOccurred
	 * 
	 * @param MouseEvent e
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		DiceView local = (DiceView)e.getSource();
		if(local.isSavedDice()){
			String diceID = local.getDiceID();
			PanelListener panelListener = GamePanel.getGamePanelListener();
			PanelEvent panelEvent = new PanelEvent(panelListener, diceID, local.isSavedDice());
			panelListener.panelEventOccurred(panelEvent);
		} else {
			String diceID = local.getDiceID();
			icon = (ImageIcon) local.getIcon();
			PanelListener panelListener = GamePanel.getGamePanelListener();
			PanelEvent panelEvent = new PanelEvent(panelListener,icon, diceID, local.getPosition());
			panelListener.panelEventOccurred(panelEvent);
			local.setVisible(false);
		}
	}
}
