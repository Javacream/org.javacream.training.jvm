package org.javacream.training.jvm.simulation.application;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.javacream.training.jvm.simulation.memory.ApplicationSimulation;

public class SimpleApplicationSimulationGui {
	private ApplicationSimulation applicationSimulation;

	public void setApplicationSimulation(
			ApplicationSimulation applicationSimulation) {
		this.applicationSimulation = applicationSimulation;
	}

	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createGui(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}

	private void createGui(Container contentPane) {
		contentPane.setLayout(new GridLayout(5, 3));
		JButton login = new JButton("login");
		JButton logout = new JButton("logout");
		final JButton request = new JButton("request");
		JButton exit = new JButton("exit");

		final JTextField inputRequest = new JTextField();
		final JTextField inputSession = new JTextField();
		final JTextField inputApplication = new JTextField();
		contentPane.add(new JLabel());
		contentPane.add(login);
		contentPane.add(logout);
		contentPane.add(new JLabel("Request Memory"));
		contentPane.add(inputRequest);
		contentPane.add(new JLabel());
		contentPane.add(new JLabel("Session Memory"));
		contentPane.add(inputSession);
		contentPane.add(request);
		contentPane.add(new JLabel("Application Memory"));
		contentPane.add(inputApplication);
		contentPane.add(new JLabel());
		contentPane.add(new JLabel());
		contentPane.add(exit);

		request.setEnabled(false);
		login.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				applicationSimulation.login();
				request.setEnabled(true);
			}
		});
		logout.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				applicationSimulation.logout();
				request.setEnabled(false);
			}
		});

		request.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int requestMem = parse(inputRequest.getText());
				int sessionMem = parse(inputSession.getText());
				int applicationMem = parse(inputApplication.getText());
				applicationSimulation.request(requestMem, sessionMem,
						applicationMem);
			}
		});
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	private int parse(String intString) {
		int result;
		try {
			result = Integer.parseInt(intString);
		} catch (NumberFormatException e) {
			return 0;
		}
		return result;
	}
}
