import java.util.List;
import java.util.ArrayList;

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
import javax.swing.SwingWorker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

/** ... */
/**
 * 
 * Main class for the program.
 * 
 * @param args
 * @throws Exception
 */

public class App implements ActionListener {

	private JLabel label = new JLabel("Primes Found: ");
	private JFrame frame = new JFrame("Prime Finder");
	private JButton submit = new JButton("Submit");
	private JButton cancel = new JButton("Cancel");
	private JTextField start = new JTextField(12);
	private JTextField end = new JTextField(12);
	SwingWorker<Object, Integer> Worker;

	static class Task implements Callable<List<Integer>> {
		private int number;

		public Task(int number) {
			this.number = number;
		}

		@Override
		public List<Integer> call() throws Exception {
			Factors fc = new Factors();
			List<Integer> factors = fc.getFactors(number);
			return factors;
		}
	}

	void findPrimes() {
		Worker = new SwingWorker<Object, Integer>() {
			int count = 0;
			ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			List<Future<List<Integer>>> allFutures = new ArrayList<>();

			@Override
			protected Object doInBackground() throws Exception {
				submit.setEnabled(false);
				for (int i = Integer.valueOf(start.getText()); i <= Integer.valueOf(end.getText()); i++) {
					if (!isCancelled()) {
						Future<List<Integer>> future = service.submit(new Task(i));
						allFutures.add(future);
					}
				}

				for (int j = Integer.valueOf(start.getText()); j <= Integer.valueOf(end.getText()); j++) {
					if (!isCancelled()) {
						Future<List<Integer>> future = allFutures.get(j);
						try {
							List<Integer> factors = future.get();
							if (factors.size() == 2) {
								count++;
								publish(count);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				return null;
			}

			@Override
			protected void process(List<Integer> chunks) {
				label.setText("Primes Found: " + chunks.get(chunks.size() - 1));
			}

			@Override
			protected void done() {
				submit.setEnabled(true);
			}
		};
		Worker.execute();
	};

	public App() {
		submit.addActionListener(this);
		cancel.addActionListener(this);

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

		new App();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			findPrimes();
		} else if (e.getSource() == cancel) {
			Worker.cancel(true);
		}
	}
}
