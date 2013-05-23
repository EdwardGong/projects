package gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import time.Date.Month;

public class AddTaskPanel extends JPanel {

	private static final Integer[] oneToTen = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	private static final Integer[] zeroToTen = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10 };
	private static final Integer[] multOfFive = { 0, 5, 10, 15, 20, 25, 30, 35,
			40, 45, 50, 55, };
	private static final Integer[] days = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
			12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
			29, 30, 31 };

	private static final Month[] months = Month.values();

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new AddTaskPanel());
		frame.pack();
		frame.setVisible(true);
	}

	public AddTaskPanel() {
		BoxLayout bl = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		this.setLayout(bl);
		add(freeTime());
		add(taskDescription());

	}

	public JPanel freeTime() {
		JPanel freeTime = new JPanel();
		JLabel freeTimeLabel = new JLabel("Available Time");
		JTextField freeTimeField = new JTextField(20);
		freeTimeLabel.setLabelFor(freeTimeField);
		JButton okayButton = new JButton("Okay");
		freeTime.add(freeTimeLabel);
		freeTime.add(freeTimeField);
		freeTime.add(okayButton);
		return freeTime;
	}

	public JPanel taskDescription() {
		JPanel description = new JPanel();
		BoxLayout bl = new BoxLayout(description, BoxLayout.PAGE_AXIS);
		description.setLayout(bl);

		JPanel name = new JPanel();
		JLabel taskName = new JLabel("Task Name");
		JTextField taskNameField = new JTextField(20);
		taskName.setLabelFor(taskNameField);

		name.add(taskName);
		name.add(taskNameField);

		JLabel weight = new JLabel("Importance of Task");
		JComboBox weightCombo = new JComboBox(oneToTen);
		weight.setLabelFor(weightCombo);

		description.add(name);
		description.add(lengthPanel());
		description.add(weightPanel());
		description.add(deadLinePanel());

		return description;
	}

	public JPanel weightPanel() {
		JPanel weightPanel = new JPanel();

		JLabel weight = new JLabel("Importance of Task");
		JComboBox weightCombo = new JComboBox(oneToTen);
		weight.setLabelFor(weightCombo);

		weightPanel.add(weight);
		weightPanel.add(weightCombo);

		return weightPanel;
	}

	public JPanel lengthPanel() {
		JPanel lengthPanel = new JPanel();

		JPanel group1 = new JPanel();
		JPanel group2 = new JPanel();

		BoxLayout bl = new BoxLayout(lengthPanel, BoxLayout.PAGE_AXIS);
		lengthPanel.setLayout(bl);
		JLabel title = new JLabel("Estimated Time to Complete Task");
		JLabel hour = new JLabel("Hours");
		JComboBox hourCombo = new JComboBox(zeroToTen);
		JLabel minute = new JLabel("Minutes");
		JComboBox minuteCombo = new JComboBox(multOfFive);

		group1.add(hour);
		group1.add(hourCombo);
		group2.add(minute);
		group2.add(minuteCombo);

		lengthPanel.add(title);
		lengthPanel.add(group1);
		lengthPanel.add(group2);

		return lengthPanel;
	}

	public JPanel deadLinePanel() {
		JPanel dlPanel = new JPanel();
		JPanel dlSub1 = new JPanel();
		JPanel dlSub2 = new JPanel();

		JPanel group1 = new JPanel();
		JPanel group2 = new JPanel();
		JPanel group3 = new JPanel();
		JPanel group4 = new JPanel();

		BoxLayout bl = new BoxLayout(dlPanel, BoxLayout.PAGE_AXIS);
		dlPanel.setLayout(bl);
		JLabel deadLine = new JLabel("Deadline");
		JLabel monthLabel = new JLabel("Month");
		JComboBox monthCombo = new JComboBox(months);
		JLabel dayLabel = new JLabel("Day");
		JComboBox dayCombo = new JComboBox(days);

		group1.add(monthLabel);
		group1.add(monthCombo);
		group2.add(dayLabel);
		group2.add(dayCombo);
		dlSub1.add(group1);
		dlSub1.add(group2);

		JLabel hourLabel = new JLabel("Hour");
		JComboBox hour = new JComboBox(zeroToTen);
		JLabel minuteLabel = new JLabel("Minute");
		JComboBox minute = new JComboBox(multOfFive);

		group3.add(hourLabel);
		group3.add(hour);
		group4.add(minuteLabel);
		group4.add(minute);
		dlSub2.add(group3);
		dlSub2.add(group4);

		dlPanel.add(deadLine);
		dlPanel.add(group1);
		dlPanel.add(group2);
		dlPanel.add(group3);
		dlPanel.add(group4);

		return dlPanel;
	}
}
