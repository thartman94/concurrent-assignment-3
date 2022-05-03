import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Collections;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** ... */
/**
 * 
 * Main class for the program.
 * 
 * @param args
 * @throws Exception
 */

public class App implements ActionListener {
	private static final int WIDTH = 800;
	private static final int HEIGHT = WIDTH / 16 / 9;

	private JLabel label = new JLabel("Primes Found: ");
	private JFrame frame = new JFrame("Prime Finder");
	private JButton submit = new JButton("Submit");
	private JButton cancel = new JButton("Cancel");
	private JTextField start = new JTextField(12);
	private JTextField end = new JTextField(12);

	private void Calculate(int end, int start) {
		Scanner sc = new Scanner(System.in);
		UI ui = new UI(sc);
		List<Integer> synPrimes = Collections.synchronizedList(new ArrayList<Integer>());
		Map<Integer, List<Integer>> synNonPrimes = Collections
				.synchronizedMap(new LinkedHashMap<Integer, List<Integer>>());

		double startTime = System.currentTimeMillis();
		new GuiBounded(synPrimes, synNonPrimes, end, 0);
		double endTime = System.currentTimeMillis();
		double time = endTime - startTime;
		ui.printResults("Bounded", end, time, synPrimes, synNonPrimes);
	}

	public App() {
		submit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						submit.setEnabled(false);
						label.setText(start.getText());
						Calculate(Integer.valueOf(end.getText()), Integer.valueOf(start.getText()));
					}
				});
		cancel.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						submit.setEnabled(true);
						label.setText(end.getText());

					}
				});

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 2));
		panel.add(new JLabel("Start:"));
		panel.add(start);
		panel.add(new JLabel("End:"));
		panel.add(end);
		panel.add(submit);
		panel.add(cancel);
		panel.add(label);

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		new App();
		UI ui = new UI(sc);
		int operation = 0;
		boolean running = true;

		while (running) {
			operation = ui.getOpperation();

			List<Integer> primes = new ArrayList<Integer>();
			Map<Integer, List<Integer>> nonPrimes = new LinkedHashMap<Integer, List<Integer>>();

			if (operation == 1) {
				int input = ui.getInput();
				double startTime = System.currentTimeMillis();

				for (int i = 1; i <= input; i++) {
					SingleThread st = new SingleThread(primes, nonPrimes, i);
					st.run();
				}
				double endTime = System.currentTimeMillis();
				double time = endTime - startTime;

				ui.printResults("Single-threaded", input, time, primes, nonPrimes);
			}

			else if (operation == 2) {
				List<Integer> synPrimes = Collections.synchronizedList(primes);
				Map<Integer, List<Integer>> synNonPrimes = Collections.synchronizedMap(nonPrimes);
				int input = ui.getInput();
				double startTime = System.currentTimeMillis();

				for (int i = 1; i <= input; i++) {
					MultiThread mt = new MultiThread(synPrimes, synNonPrimes, i);
					mt.start();
				}

				while (synPrimes.size() + synNonPrimes.size() != input) {
				}

				double endTime = System.currentTimeMillis();
				double time = endTime - startTime;

				ui.printResults("Multi-threaded", input, time, synPrimes, synNonPrimes);
			}

			else if (operation == 3) {
				List<Integer> synPrimes = Collections.synchronizedList(primes);
				Map<Integer, List<Integer>> synNonPrimes = Collections.synchronizedMap(nonPrimes);

				int threads = ui.getThreads();
				int input = ui.getInput();

				double startTime = System.currentTimeMillis();
				new Bounded(synPrimes, synNonPrimes, input, threads);
				double endTime = System.currentTimeMillis();
				double time = endTime - startTime;
				ui.printResults("Bounded", input, time, synPrimes, synNonPrimes);
			}

			else {
				System.out.println("Exiting...");
				sc.close();
				running = false;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
