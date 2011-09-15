package com.nilhcem.fakesmtp.gui;

import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import com.nilhcem.fakesmtp.gui.info.NbReceivedLabel;
import com.nilhcem.fakesmtp.gui.info.PortTextField;
import com.nilhcem.fakesmtp.gui.info.SaveMsgField;
import com.nilhcem.fakesmtp.gui.info.StartServerButton;
import com.nilhcem.fakesmtp.gui.tab.LastMailPane;
import com.nilhcem.fakesmtp.gui.tab.LogsPane;
import com.nilhcem.fakesmtp.gui.tab.MailsListPane;

public final class MainPanel {
	// Panel and layout
	private final MigLayout layout = new MigLayout(
		"", // Layout constraints
		"[] 10 [] [] [grow,fill]", // Column constraints
		"[] [] 5 [] 5 [grow,fill]"); // Row constraints
	private final JPanel mainPanel = new JPanel(layout);

	// Directory chooser
	private final DirChooser dirChooser = new DirChooser(mainPanel);

	// Port
	private final JLabel portLabel = new JLabel("Listening port:");
	private final PortTextField portText = new PortTextField();
	private final StartServerButton portBtn = new StartServerButton();

	// Messages received
	private final JLabel receivedLabel = new JLabel("Message(s) received:");
	private final NbReceivedLabel nbReceivedLabel = new NbReceivedLabel();

	// Save incoming messages to
	private final JLabel saveMessages = new JLabel("Save message(s) to: ");
	private final SaveMsgField saveMsgTextField = new SaveMsgField();

	// Tab pane
	private final JTabbedPane tabbedPane = new JTabbedPane();
	private final LogsPane logsPane = new LogsPane();
	private final MailsListPane mailsListPane = new MailsListPane();
	private final LastMailPane lastMailPane = new LastMailPane();

//	// Clear logs
//	private final JButton clearLogs = new JButton("Clear logs");

	public MainPanel(Observable menu) {
		addObservers(menu);
		buildGUI();
	}

	public JPanel get() {
		return mainPanel;
	}

	private void addObservers(Observable menu) {
		// Add observers
		menu.addObserver(dirChooser);
		saveMsgTextField.addObserver(dirChooser);

		portBtn.addObserver(portText);
		dirChooser.addObserver(saveMsgTextField);
	}

	private void buildGUI() {
		mainPanel.add(portLabel);
		mainPanel.add(portText.get(), "w 60!"); // width 60 (min:preferred:max)
		mainPanel.add(portBtn.get(), "span, w 166!");

		mainPanel.add(saveMessages);
		mainPanel.add(saveMsgTextField.get(), "span, w 230!");

		mainPanel.add(receivedLabel);
		mainPanel.add(nbReceivedLabel.get(), "span");

		// Tab pane
		tabbedPane.add(mailsListPane.get(), "Mails list");
		tabbedPane.add(logsPane.get(), "SMTP Log");
		tabbedPane.add(lastMailPane.get(), "Last message");
		mainPanel.add(tabbedPane, "span, grow");

//		// Clear logs
//		clearLogs.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				logsPane.clearLogs();
//			}
//		});
//		mainPanel.add(clearLogs, "span, grow, center");
	}
}
