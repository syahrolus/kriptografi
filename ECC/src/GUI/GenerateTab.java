package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import algo.ECC;
import algo.Point;

public class GenerateTab extends JPanel{
	
	private JTextField paramA;
	private JTextField paramB;
	private JTextField paramP;
	private JButton button;
	private JLabel label;
	private JTextArea privateKeyTextArea;
	private JButton button_2;
	private JButton btnNewButton_5;
	private JLabel lblNewLabel;
	private JTextArea publicKeyTextArea;
	private JButton btnNewButton;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblBasePoint;
	private JScrollPane privateKeyScrollPane;
	private JScrollPane publicKeyScrollPane;
	private JButton btnGeneratePublicFrom;
	private JButton generatePointsButton;
	private JComboBox<Point> basePointList;

	public GenerateTab() {
		this.setLayout(null);
		
		button = new JButton("Generate new keys");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generatePrivateKey(new BigInteger(paramP.getText()));
			}
		});
		
		button.setBounds(10, 118, 331, 23);
		this.add(button);
		
		label = new JLabel("Your private key:");
		label.setBounds(10, 152, 108, 14);
		this.add(label);
		
		privateKeyTextArea = new JTextArea();
		// privateKeyTextArea.setBounds(10, 136, 331, 136);
		
		privateKeyScrollPane = new JScrollPane(privateKeyTextArea);
		privateKeyScrollPane.setBounds(10, 177, 331, 136);
		add(privateKeyScrollPane);
		
		button_2 = new JButton("Save private key");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
			        try {
			            FileWriter fw = new FileWriter(fc.getSelectedFile() + ".pri");
			            fw.write(paramA.getText() + " " + paramB.getText() + " " + paramP.getText() + "\n");
			            
			            Point basePoint = (Point) basePointList.getSelectedItem();
			            
			            fw.write(basePoint.x + " " + basePoint.y + "\n");
			            fw.write(privateKeyTextArea.getText() + "\n");
			            fw.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		button_2.setBounds(10, 324, 201, 23);
		this.add(button_2);
		
		lblNewLabel = new JLabel("Your public key:");
		lblNewLabel.setBounds(351, 152, 108, 14);
		this.add(lblNewLabel);
		
		publicKeyTextArea = new JTextArea();
		// publicKeyTextArea.setBounds(351, 136, 331, 136);
		// this.add(publicKeyTextArea);
		publicKeyTextArea.setEditable(false);
		
		publicKeyScrollPane = new JScrollPane(publicKeyTextArea);
		publicKeyScrollPane.setBounds(351, 177, 331, 136);
		add(publicKeyScrollPane);
		
		btnNewButton = new JButton("Save public key");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
			        try {
			            FileWriter fw = new FileWriter(fc.getSelectedFile() + ".pub");
			            fw.write(paramA.getText() + " " + paramB.getText() + " " + paramP.getText() + "\n");
			            
			            Point basePoint = (Point) basePointList.getSelectedItem();
			            
			            fw.write(basePoint.x + " " + basePoint.y + "\n");
			            fw.write(publicKeyTextArea.getText() + "\n");
			            fw.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		btnNewButton.setBounds(351, 324, 201, 23);
		this.add(btnNewButton);
		
		lblNewLabel_4 = new JLabel("a:");
		lblNewLabel_4.setBounds(10, 11, 46, 14);
		this.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("b:");
		lblNewLabel_5.setBounds(177, 11, 46, 14);
		this.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("p:");
		lblNewLabel_6.setBounds(352, 11, 46, 14);
		this.add(lblNewLabel_6);
		
		lblBasePoint = new JLabel("Base point (x, y): ");
		lblBasePoint.setBounds(518, 11, 164, 14);
		this.add(lblBasePoint);
		
		paramA = new JTextField();
		paramA.setBounds(10, 33, 157, 20);
		this.add(paramA);
		paramA.setColumns(10);
		
		paramB = new JTextField();
		paramB.setBounds(177, 33, 164, 20);
		this.add(paramB);
		paramB.setColumns(10);
		
		paramP = new JTextField();
		paramP.setBounds(352, 33, 156, 20);
		this.add(paramP);
		paramP.setColumns(10);
		
		btnGeneratePublicFrom = new JButton("Generate public key from private key");
		btnGeneratePublicFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generatePublicKey(new BigInteger(privateKeyTextArea.getText()));
			}
		});
		btnGeneratePublicFrom.setBounds(351, 118, 331, 23);
		add(btnGeneratePublicFrom);
		
		generatePointsButton = new JButton("Generate Points");
		generatePointsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Point> points = generateDropDown();
				basePointList.setModel(new BasePointComboBoxModel(points));
			}
		});
		generatePointsButton.setBounds(518, 32, 164, 23);
		add(generatePointsButton);
		
		basePointList = new JComboBox<Point>();
		basePointList.setModel(new DefaultComboBoxModel(new String[] {"No points found."}));
		basePointList.setBounds(518, 66, 164, 23);
		add(basePointList);
	}
	
	private void generatePrivateKey(BigInteger p) {
		// test tambah
		// ECC.setParam(1, 6, 11, new Float(0, 0));
		// ECC.add(new Float(2, 4), new Float(5, 9));
		
		// test kali
		// ECC.setParam(2, 1, 5, new Point(0, 1));
		// System.out.println(ECC.times(2, new Point(0, 1)));
		
		// set param
		
		ECC.setParam(
			new BigInteger(paramA.getText()), 
			new BigInteger(paramB.getText()), 
			new BigInteger(paramP.getText()), 
			(Point) basePointList.getSelectedItem()
		);
		
		BigInteger privateKey = new BigInteger(192, new Random()).mod(ECC.p.subtract(BigInteger.ONE)).add(BigInteger.ONE);
		privateKeyTextArea.setText(privateKey.toString());
		generatePublicKey(privateKey);
	}
	
	private void generatePublicKey(BigInteger privateKey){
		// set param
		ECC.setParam(
			new BigInteger(paramA.getText()), 
			new BigInteger(paramB.getText()), 
			new BigInteger(paramP.getText()), 
			(Point) basePointList.getSelectedItem()
		);
		
		Point publicKey = ECC.times(privateKey, ECC.basePoint);
		
		publicKeyTextArea.setText(publicKey.x + " " + publicKey.y);
	}
	
	BigInteger sqrt(BigInteger n) {
		BigInteger a = BigInteger.ONE;
		BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
		while(b.compareTo(a) >= 0) {
			BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
			if(mid.multiply(mid).compareTo(n) > 0) 
				b = mid.subtract(BigInteger.ONE);
			else 
				a = mid.add(BigInteger.ONE);
	}
	return a.subtract(BigInteger.ONE);
	}
	
	private ArrayList<Point> generateDropDown(){
		ArrayList<Point> result = new ArrayList<Point>();
		
		BigInteger a = new BigInteger(paramA.getText());
		BigInteger b = new BigInteger(paramB.getText());
		BigInteger p = new BigInteger(paramP.getText());
		
		for(BigInteger x = BigInteger.ZERO;
				x.compareTo(p) < 0;
				x = x.add(BigInteger.ONE)){
			
			BigInteger y2 = x.multiply(x).multiply(x).add(a.multiply(x)).add(b);
			BigInteger y = sqrt(y2);
			if(y.multiply(y).equals(y2)){ // has solution
				result.add(new Point(x, y.mod(p)));
				result.add(new Point(x, y.multiply(new BigInteger("-1")).mod(p)));
			} // else no solution
		}
		return result;
	}
}
