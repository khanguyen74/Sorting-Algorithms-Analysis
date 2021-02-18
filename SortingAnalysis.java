/**
 * Author: Kha Nguyen
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.InputMethodListener;
import java.util.Random;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.lang.Math;

public class SortingAnalysis {

	private JFrame frame;
	private JTextField winningText;
	private JTextField arrayLengthText;
	private final ButtonGroup jRadioGroup = new ButtonGroup();
	private int arrayLength, arrayType;
	private int arrayList[];
	private JTextField lengthResult;
	private JTextField dataTypeResult;
	private JTextField sortNameResult;
	private JTextField numOfComparisons;
	private JTextField numOfMovements;
	private JTextField totalTime;
	private boolean listGenerated = false;
	long winningTime = 10000;
	String winningSort;
	String dataType = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortingAnalysis window = new SortingAnalysis();
					window.frame.setVisible(true);
					String message = "1. Choose a list property\n"
							+ "2. Adjust list length\n"
							+ "3. Press create list button\n"
							+ "4. Select sort algorithm";
					JOptionPane.showMessageDialog(null, message, "Instruction", JOptionPane.INFORMATION_MESSAGE	, null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SortingAnalysis() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel sortTypePanel = new JPanel();
		sortTypePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sortTypePanel.setBounds(0, 13, 308, 540);
		frame.getContentPane().add(sortTypePanel);
		sortTypePanel.setLayout(null);
		
		JButton btnInsertionSort = new JButton("Insertion Sort");

		btnInsertionSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnInsertionSort.setBounds(12, 54, 284, 68);
		sortTypePanel.add(btnInsertionSort);
		
		JButton btnSelectionSort = new JButton("Selection Sort");

		btnSelectionSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSelectionSort.setBounds(12, 135, 284, 68);
		sortTypePanel.add(btnSelectionSort);
		
		JButton btnQuickSort = new JButton("Quick Sort");
		btnQuickSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuickSort.setBounds(12, 216, 284, 68);
		sortTypePanel.add(btnQuickSort);
		
		JButton btnMergeSort = new JButton("Merge Sort");

		btnMergeSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMergeSort.setBounds(12, 297, 284, 68);
		sortTypePanel.add(btnMergeSort);
		
		JButton btnHeapSort = new JButton("Heap Sort");

		btnHeapSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHeapSort.setBounds(12, 378, 284, 68);
		sortTypePanel.add(btnHeapSort);
		
		JButton btn_RadixSort = new JButton("Radix Sort");

		btn_RadixSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_RadixSort.setBounds(12, 459, 284, 68);
		sortTypePanel.add(btn_RadixSort);
		
		JPanel winningDisplayPanel = new JPanel();
		winningDisplayPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Wining Algorithm", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		winningDisplayPanel.setBounds(320, 13, 450, 115);
		frame.getContentPane().add(winningDisplayPanel);
		winningDisplayPanel.setLayout(null);
		
		winningText = new JTextField();
		winningText.setBounds(12, 42, 426, 33);
		winningDisplayPanel.add(winningText);
		winningText.setColumns(10);
		
		JPanel propertiesPanel = new JPanel();
		propertiesPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "List Properties", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		propertiesPanel.setBounds(320, 141, 450, 209);
		frame.getContentPane().add(propertiesPanel);
		propertiesPanel.setLayout(null);
		
		JRadioButton rdbtnInorder = new JRadioButton("InOrder");
		rdbtnInorder.setSelected(true);
		jRadioGroup.add(rdbtnInorder);
		rdbtnInorder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnInorder.setBounds(8, 30, 127, 25);
		propertiesPanel.add(rdbtnInorder);
		
		JRadioButton rdbtnAlmostorder = new JRadioButton("AlmostOrder");
		jRadioGroup.add(rdbtnAlmostorder);
		rdbtnAlmostorder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnAlmostorder.setBounds(8, 60, 127, 25);
		propertiesPanel.add(rdbtnAlmostorder);
		
		JRadioButton rdbtnReverseorder = new JRadioButton("ReverseOrder");
		jRadioGroup.add(rdbtnReverseorder);
		rdbtnReverseorder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnReverseorder.setBounds(271, 31, 127, 25);
		propertiesPanel.add(rdbtnReverseorder);
		
		JRadioButton rdbtnRandom = new JRadioButton("Random");
		jRadioGroup.add(rdbtnRandom);
		rdbtnRandom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnRandom.setBounds(271, 61, 127, 25);
		propertiesPanel.add(rdbtnRandom);
		
		JSlider arrayLengthSlider = new JSlider();

		arrayLengthSlider.setValue(10000);
		arrayLengthSlider.setFont(new Font("Tahoma", Font.PLAIN, 15));
		arrayLengthSlider.setMaximum(100000);
		arrayLengthSlider.setMinimum(1);
		arrayLengthSlider.setBounds(12, 109, 269, 26);
		propertiesPanel.add(arrayLengthSlider);
		
		arrayLengthText = new JTextField();


		arrayLengthText.setText("10000");

		arrayLengthText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		arrayLengthText.setBounds(293, 109, 116, 22);
		propertiesPanel.add(arrayLengthText);
		arrayLengthText.setColumns(10);
		
		JButton btnCreateList = new JButton("Create The List");

		btnCreateList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCreateList.setBounds(22, 145, 376, 40);
		propertiesPanel.add(btnCreateList);
		
		JPanel experimentalResultPanel = new JPanel();
		experimentalResultPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Experimental Results", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		experimentalResultPanel.setBounds(320, 360, 450, 209);
		frame.getContentPane().add(experimentalResultPanel);
		experimentalResultPanel.setLayout(null);
		
		lengthResult = new JTextField();
		lengthResult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lengthResult.setBounds(209, 25, 146, 22);
		experimentalResultPanel.add(lengthResult);
		lengthResult.setColumns(10);
		
		JLabel lblN = new JLabel("N:");
		lblN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblN.setBounds(128, 28, 56, 16);
		experimentalResultPanel.add(lblN);
		
		JLabel lblDatatype = new JLabel("DataType:");
		lblDatatype.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatatype.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDatatype.setBounds(68, 57, 116, 16);
		experimentalResultPanel.add(lblDatatype);
		
		JLabel lblSort = new JLabel("Sort");
		lblSort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSort.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSort.setBounds(128, 86, 56, 16);
		experimentalResultPanel.add(lblSort);
		
		JLabel lblComparisons = new JLabel("Comparisons:");
		lblComparisons.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComparisons.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblComparisons.setBounds(68, 115, 116, 16);
		experimentalResultPanel.add(lblComparisons);
		
		JLabel lblMovements = new JLabel("Movements:");
		lblMovements.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMovements.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMovements.setBounds(78, 144, 106, 16);
		experimentalResultPanel.add(lblMovements);
		
		JLabel lblTotalTime = new JLabel("Total time:");
		lblTotalTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalTime.setBounds(68, 173, 116, 16);
		experimentalResultPanel.add(lblTotalTime);
		
		dataTypeResult = new JTextField();
		dataTypeResult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dataTypeResult.setColumns(10);
		dataTypeResult.setBounds(209, 54, 146, 22);
		experimentalResultPanel.add(dataTypeResult);
		
		sortNameResult = new JTextField();
		sortNameResult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sortNameResult.setColumns(10);
		sortNameResult.setBounds(209, 84, 146, 22);
		experimentalResultPanel.add(sortNameResult);
		
		numOfComparisons = new JTextField();
		numOfComparisons.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numOfComparisons.setColumns(10);
		numOfComparisons.setBounds(209, 113, 146, 22);
		experimentalResultPanel.add(numOfComparisons);
		
		numOfMovements = new JTextField();
		numOfMovements.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numOfMovements.setColumns(10);
		numOfMovements.setBounds(209, 142, 146, 22);
		experimentalResultPanel.add(numOfMovements);
		
		totalTime = new JTextField();
		totalTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		totalTime.setColumns(10);
		totalTime.setBounds(209, 171, 146, 22);
		experimentalResultPanel.add(totalTime);
		
		arrayLengthSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				arrayLengthText.setText(Integer.toString(arrayLengthSlider.getValue()));
			}
		});
		
		
		arrayLengthText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sliderUpdate(arrayLengthText.getText(), arrayLengthSlider);
				
			}
		});
		
		btnCreateList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arrayLength = Integer.parseInt(arrayLengthText.getText());
				if (rdbtnInorder.isSelected()) 
					arrayType = 0;
				else if (rdbtnAlmostorder.isSelected())
					arrayType = 1;
				else if (rdbtnReverseorder.isSelected())
					arrayType = 2;
				else if (rdbtnRandom.isSelected())
					arrayType = 3;
				generateArray(arrayType, arrayLength);
				listGenerated = true;
//				for (int i = 0; i< arrayLength; i++)
//					System.out.print(arrayList[i]+ " ");
//				System.out.println();
			}
		});
		
		btnInsertionSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listGenerated)
				{
					int[] temp = duplicateList(arrayList, arrayLength);

					SortingObject data = InsertionSort.insertionSort(temp, arrayLength);
					dataTypeResult.setText(dataType);
					lengthResult.setText(String.format("%,d", data.getLength()));
					sortNameResult.setText("Insertion Sort");
					numOfComparisons.setText(String.format("%,d", data.getComparisons()));
					numOfMovements.setText(String.format("%,d", data.getMovements()));
					totalTime.setText(Long.toString(data.getTotalTime()) + " ms");
					if (data.getTotalTime() < winningTime)
					{
						winningTime = data.getTotalTime();

						winningSort = "Insertion Sort";
						winningText.setText(winningSort);
					}
//					for (int i = 0; i< arrayLength; i++)
//						System.out.print(temp[i]+ " ");
//					System.out.println();
				}else 
					JOptionPane.showMessageDialog(null,"Create array first!");
			}
			
		});
		
		btnQuickSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listGenerated)
				{
					int[] temp = duplicateList(arrayList, arrayLength); // since array is passed by reference, create a new list with same elements before sorting to preserve original list
					SortingObject data = QuickSort.quickSort(temp, arrayLength);
					dataTypeResult.setText(dataType);
					lengthResult.setText(String.format("%,d", data.getLength()));
					sortNameResult.setText("Quick Sort");
					numOfComparisons.setText(String.format("%,d", data.getComparisons()));
					numOfMovements.setText(String.format("%,d", data.getMovements()));
					totalTime.setText(Long.toString(data.getTotalTime()) + " ms");
					if (data.getTotalTime() < winningTime)
					{
						winningTime = data.getTotalTime();
						winningSort = "Quick Sort";
						winningText.setText(winningSort);
					}
//					for (int i = 0; i< arrayLength; i++)
//						System.out.print(temp[i]+ " ");
//					System.out.println();
				}else 
					JOptionPane.showMessageDialog(null,"Create array first!");
			}
		});
		
		btnSelectionSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listGenerated)
				{
					int[] temp = duplicateList(arrayList, arrayLength);
					SortingObject data = SelectionSort.selectionSort(temp, arrayLength);
					dataTypeResult.setText(dataType);
					lengthResult.setText(String.format("%,d", data.getLength()));
					sortNameResult.setText("Selection Sort");
					numOfComparisons.setText(String.format("%,d",data.getComparisons()));
					numOfMovements.setText(String.format("%,d",data.getMovements()));
					totalTime.setText(Long.toString(data.getTotalTime()) + " ms");
					if (data.getTotalTime() < winningTime)
					{
						winningTime = data.getTotalTime();

						winningSort = "Selection Sort";
						winningText.setText(winningSort);
					}
//					for (int i = 0; i< arrayLength; i++)
//						System.out.print(temp[i]+ " ");
//					System.out.println();
				}else 
					JOptionPane.showMessageDialog(null,"Create array first!");
			}
		});
		btnMergeSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listGenerated)
				{
					int[] temp = duplicateList(arrayList, arrayLength);
					SortingObject data = MergeSort.mergeSort(temp);
					dataTypeResult.setText(dataType);
					lengthResult.setText(String.format("%,d",data.getLength()));
					sortNameResult.setText("Merge Sort");
					numOfComparisons.setText(String.format("%,d",data.getComparisons()));
					numOfMovements.setText(String.format("%,d", data.getMovements()));
					totalTime.setText(Long.toString(data.getTotalTime()) + " ms");
					if (data.getTotalTime() < winningTime)
					{
						winningTime = data.getTotalTime();

						winningSort = "Merge Sort";
						winningText.setText(winningSort);
					}
//					for (int i = 0; i< arrayLength; i++)
//						System.out.print(temp[i]+ " ");
//					System.out.println();
				}else 
					JOptionPane.showMessageDialog(null,"Create array first!");
			}
		});
		btnHeapSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listGenerated)
				{
					int[] temp = duplicateList(arrayList, arrayLength);
					SortingObject data = HeapSort.heapSort(temp);
					dataTypeResult.setText(dataType);
					lengthResult.setText(String.format("%,d",data.getLength()));
					sortNameResult.setText("Heap Sort");
					numOfComparisons.setText(String.format("%,d",data.getComparisons()));
					numOfMovements.setText(String.format("%,d", data.getMovements()));
					totalTime.setText(Long.toString(data.getTotalTime()) + " ms");
					if (data.getTotalTime() < winningTime)
					{
						winningTime = data.getTotalTime();

						winningSort = "Heap Sort";
						winningText.setText(winningSort);
					}
//					for (int i = 0; i< arrayLength; i++)
//						System.out.print(temp[i]+ " ");
//					System.out.println();
				}else 
					JOptionPane.showMessageDialog(null,"Create array first!");
			}
		});
		
		btn_RadixSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listGenerated)
				{
					int[] temp = duplicateList(arrayList, arrayLength);
					SortingObject data = RadixSort.sort(temp, temp.length);
					dataTypeResult.setText(dataType);
					lengthResult.setText(String.format("%,d",data.getLength()));
					sortNameResult.setText("Heap Sort");
					numOfComparisons.setText(String.format("%,d",data.getComparisons()));
					numOfMovements.setText(String.format("%,d", data.getMovements()));
					totalTime.setText(Long.toString(data.getTotalTime()) + " ms");
					if (data.getTotalTime() < winningTime)
					{
						winningTime = data.getTotalTime();

						winningSort = "Radix Sort";
						winningText.setText(winningSort);
					}
//					for (int i = 0; i< arrayLength; i++)
//						System.out.print(temp[i]+ " ");
//					System.out.println();
				}else 
					JOptionPane.showMessageDialog(null,"Create array first!");
			}
		});

	}
	private void sliderUpdate(String text, JSlider slider) {
		int value = Integer.parseInt(text);
		if ((value <= 100000) && (value >= 0))
		{
			slider.setValue(value);
		}
	}
	private void generateArray(int type, int length)
	{
		Random rand = new Random();
		winningTime = 10000; //reset winningTime
		winningText.setText("");
		int i=0;
		arrayList = new int[length];
		switch (type) {
		case 0: //in order
			for (i= 0; i<length; i++)
				arrayList[i] = i;
			dataType = "In order";

			break;
		case 1: //almost order

			for (i=0; i< Math.floor(length*0.2); i++)
				arrayList[i] = rand.nextInt(length);
			for (; i< length; i++)
				arrayList[i] = i;
			dataType = "Almost Order";

			break;
		case 2: // reverse order
			for (i = 0; i < length; i++)
				arrayList[i] = length-i-1;
			dataType = "Reverse order";

			break;
		case 3: //random

			for (i = 0; i< length; i++)
				arrayList[i] = rand.nextInt(length);
			dataType = "Random";

			break;
		default:
			break;
		}
	}
	
	private int[] duplicateList(int[] list, int length) {
		int[] temp = new int[length];
		for (int i = 0; i<length; i++)
			temp[i] = list[i];
		return temp;
	}
}
