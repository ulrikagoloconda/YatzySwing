package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import Controller.PanelEvent;
import Controller.PanelListener;

/** 
 * StartPanel extending JPanel and is the first view shown when the game starts. 
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 6, 2015
 */
public class StartPanel extends JPanel{
	private  PanelListener panelListener;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JButton okBtn;
	private JButton submitBtn; 
	private  JLabel headLineLabel;
	private JTextArea textArea; 
	private JPanel pageStartPanel;
	private JPanel lineStartPanel; 
	private ArrayList<String> names;

	/** 
	 * The constructor of the class, takes no parameters.
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public StartPanel(){
		super();
		initStartPanel();
		names = new ArrayList<>();
	}

	/** 
	 * Initializes the start panel.
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void initStartPanel(){
		setLayout(new BorderLayout());
		setBackground(Color.WHITE); 
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(200,200));
		Border border = BorderFactory.createTitledBorder("Spelare: ");
		textArea.setEditable(false);
		textArea.setBorder(border);
		setVisible(true);


		////// PageStart/////////////

		headLineLabel = new JLabel("Y A T Z Y ! ");
		Font font = new Font("Serif", Font.BOLD,25);
		headLineLabel.setFont(font);
		headLineLabel.setPreferredSize(new Dimension (150,100));
		pageStartPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 0; 
		pageStartPanel.add(headLineLabel, gb);
		add(pageStartPanel, BorderLayout.PAGE_START);

		//////// LineStart///////////

		nameLabel = new JLabel("Vad heter du: "); 
		nameTextField = new JTextField(15);
		Border textFfieldBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		nameTextField.setBorder(textFfieldBorder);
		nameTextField.setPreferredSize(new Dimension(30,30));
		okBtn = new JButton("Lägg till spelaren!");
		okBtn.setName("LäggTill" );
		submitBtn = new JButton("Submit");
		submitBtn.setName("SubmitKnapp");
		lineStartPanel = new JPanel();
		lineStartPanel.setLayout(new GridBagLayout());
		Border lineStartBorder = BorderFactory.createTitledBorder("Ange spelarnamn: ");
		lineStartPanel.setBorder(lineStartBorder);

		gb.anchor = GridBagConstraints.LAST_LINE_END;
		gb.weighty = 0.5;
		lineStartPanel.add(nameLabel,gb);

		gb.gridx++;
		gb.anchor = GridBagConstraints.FIRST_LINE_END;

		lineStartPanel.add(nameTextField,gb);
		gb.gridy++;
		gb.weighty = 3; 
		lineStartPanel.add(okBtn,gb);

		gb.gridy ++;
		lineStartPanel.add(submitBtn, gb);
		add(lineStartPanel, BorderLayout.LINE_START);


		okBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				names.add(name);
				PanelEvent ev = new PanelEvent(this, name);
				nameTextField.setText("");
				if (panelListener != null){
					panelListener.panelEventOccurred(ev);
				}
			}

		});

		submitBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PanelEvent ev = new PanelEvent(this, names);
				if (panelListener != null){
					panelListener.panelEventOccurred(ev);
				}
			}
		});


		///// Ceneter//////

		add(textArea, BorderLayout.CENTER);
	}
	/** 
	 * Appends text to the text the text area
	 * 
	 * @param String name
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void addTextToArea(String name){
		textArea.append(name);
	}

	/** 
	 * Sets the panelListener of the StartPanel
	 * 
	 * @param PanelListener
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void setPanelListener(PanelListener panelListener) {
		this.panelListener = panelListener;

	}
}
