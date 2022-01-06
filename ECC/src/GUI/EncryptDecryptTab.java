package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import algo.ECC;
import algo.Point;

public class EncryptDecryptTab extends JPanel {

	private JLabel lblYourPrivateKey;
	private JButton btnPrivate;
	private JLabel lblBtnPrivate;

	private JLabel lblReceiverPublicKey;
	private JButton btnPublic;
	private JLabel lblBtnPublic;
	
	private JLabel lblPlaintextFile;
	private JButton btnPlaintextFile;
	private JLabel lblBtnPlaintextFile;
	
	private JLabel lblCiphertextFile;
	private JButton btnCiphertextFile;
	private JLabel lblBtnCiphertextFile;

	private JLabel lblPlaintext;
	private JScrollPane textAreaPlaintextScrollPane;
	private JTextArea textAreaPlaintext;
	private JButton btnEncrypt;
	private JButton btnSavePlaintext;

	private JLabel lblCiphertext;
	private JScrollPane textAreaCiphertextScrollPane;
	private JTextArea textAreaCiphertext;
	private JButton btnDecrypt;
	private JButton btnSaveCiphertext;
	
	private BigInteger privateA, privateB, privateP, privateBaseX, privateBaseY, privateKey;
	private BigInteger publicA, publicB, publicP, publicBaseX, publicBaseY, publicKeyX, publicKeyY;
	private byte[] plaintext, ciphertext;

	public EncryptDecryptTab() {
		this.setLayout(null);
		
		// public key
		lblReceiverPublicKey = new JLabel("Public key:");
		lblReceiverPublicKey.setBounds(10, 11, 157, 14);
		this.add(lblReceiverPublicKey);
		
		btnPublic = new JButton("Browse file...");
		btnPublic.setBounds(10, 36, 157, 23);
		this.add(btnPublic);
		
		lblBtnPublic = new JLabel("No file selected.");
		lblBtnPublic.setBounds(177, 40, 141, 14);
		this.add(lblBtnPublic);
		
		// private key
		lblYourPrivateKey = new JLabel("Private key:");
		lblYourPrivateKey.setBounds(332, 11, 189, 14);
		this.add(lblYourPrivateKey);
		
		btnPrivate = new JButton("Browse file...");
		btnPrivate.setBounds(332, 36, 157, 23);
		this.add(btnPrivate);
		
		lblBtnPrivate = new JLabel("No file selected.");
		lblBtnPrivate.setBounds(499, 40, 161, 14);
		this.add(lblBtnPrivate);
		
		// plaintext file
		lblPlaintextFile = new JLabel("Plaintext file:");
		lblPlaintextFile.setBounds(10, 80, 70, 14);
		this.add(lblPlaintextFile);
		
		btnPlaintextFile = new JButton("Browse file...");
		btnPlaintextFile.setBounds(10, 105, 157, 23);
		this.add(btnPlaintextFile);
		
		lblBtnPlaintextFile = new JLabel("No file selected.");
		lblBtnPlaintextFile.setBounds(177, 109, 141, 14);
		this.add(lblBtnPlaintextFile);
		
		// ciphertext file
		lblCiphertextFile = new JLabel("Ciphertext file:");
		lblCiphertextFile.setBounds(332, 80, 100, 14);
		this.add(lblCiphertextFile);
		
		btnCiphertextFile = new JButton("Browse file...");
		btnCiphertextFile.setBounds(332, 105, 157, 23);
		this.add(btnCiphertextFile);
		
		lblBtnCiphertextFile = new JLabel("No file selected.");
		lblBtnCiphertextFile.setBounds(499, 109, 141, 14);
		this.add(lblBtnCiphertextFile);
		
		// Plaintext area
		lblPlaintext = new JLabel("Plaintext:");
		lblPlaintext.setBounds(10, 139, 46, 14);
		this.add(lblPlaintext);
		
		textAreaPlaintext = new JTextArea();
		textAreaPlaintext.setBounds(10, 164, 308, 126);
		textAreaPlaintext.setLineWrap(true);
		textAreaPlaintext.setWrapStyleWord(true);
		textAreaPlaintextScrollPane = new JScrollPane(textAreaPlaintext);
		textAreaPlaintextScrollPane.setBounds(10, 164, 308, 126);
		add(textAreaPlaintextScrollPane);

		// Ciphertext area
		lblCiphertext = new JLabel("Ciphertext:");
		lblCiphertext.setBounds(332, 139, 70, 14);
		this.add(lblCiphertext);
		
		textAreaCiphertext = new JTextArea();
		textAreaCiphertext.setBounds(332, 164, 328, 126);
		textAreaCiphertext.setLineWrap(true);
		textAreaCiphertext.setWrapStyleWord(true);
		textAreaCiphertextScrollPane = new JScrollPane(textAreaCiphertext);
		textAreaCiphertextScrollPane.setBounds(332, 164, 328, 126);
		add(textAreaCiphertextScrollPane);
		
		// Plaintext buttons
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setBounds(10, 301, 100, 39);
		this.add(btnEncrypt);
		
		btnSavePlaintext = new JButton("Save plaintext");
		btnSavePlaintext.setBounds(210, 301, 108, 39);
		add(btnSavePlaintext);
		
		// Ciphertext buttons
		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setBounds(332, 301, 100, 39);
		this.add(btnDecrypt);
		
		btnSaveCiphertext = new JButton("Save ciphertext");
		btnSaveCiphertext.setBounds(552, 301, 108, 39);
		this.add(btnSaveCiphertext);
		
		// Set up listeners
		btnPrivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
			        try {
			        	 Scanner sc = new Scanner(fc.getSelectedFile());
			        	 privateA = sc.nextBigInteger();
			        	 privateB = sc.nextBigInteger();
			        	 privateP = sc.nextBigInteger();
			        	 privateBaseX = sc.nextBigInteger();
			        	 privateBaseY = sc.nextBigInteger();
			        	 privateKey = sc.nextBigInteger();
			        	 sc.close();
			        	 lblBtnPrivate.setText(fc.getSelectedFile().getName());
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		
		btnPublic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
			        try {
			        	 Scanner sc = new Scanner(fc.getSelectedFile());
			        	 publicA = sc.nextBigInteger();
			        	 publicB = sc.nextBigInteger();
			        	 publicP = sc.nextBigInteger();
			        	 publicBaseX = sc.nextBigInteger();
			        	 publicBaseY = sc.nextBigInteger();
			        	 publicKeyX = sc.nextBigInteger();
			        	 publicKeyY = sc.nextBigInteger();
			        	 sc.close();
			        	 lblBtnPublic.setText(fc.getSelectedFile().getName());
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		
		btnPlaintextFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
			        try {
			        	plaintext = Files.readAllBytes(fc.getSelectedFile().toPath());
			        	textAreaPlaintext.setText(new String(plaintext, "UTF-8"));
			        	lblBtnPlaintextFile.setText(fc.getSelectedFile().getName());
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		
		btnCiphertextFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
			        try {
			        	ciphertext = Files.readAllBytes(fc.getSelectedFile().toPath());
			        	textAreaCiphertext.setText(byteArrayToHex(ciphertext));
			        	lblBtnCiphertextFile.setText(fc.getSelectedFile().getName());
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encrypt();
			}
		});

		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decrypt();
			}
		});

		btnSavePlaintext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save(plaintext);
			}
		});

		btnSaveCiphertext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save(ciphertext);
			}
		});
	}

	public void encrypt(){
		long startTime = System.nanoTime();
		String ciphertextString = "";
		
		BigInteger k = new BigInteger(192, new Random()).mod(publicP.subtract(BigInteger.ONE)).add(BigInteger.ONE);		
//		System.out.println(k);
		ECC.setParam(publicA, publicB, publicP, new Point (publicBaseX, publicBaseY));
		ciphertextString += ECC.times(k, new Point (publicBaseX, publicBaseY)).toString();
		ciphertextString += "\n";
		for (byte b : plaintext) {
			ciphertextString += ECC.add(ECC.messageToPoint(new BigInteger(String.valueOf(b+128))), ECC.times(k, new Point(publicKeyX, publicKeyY))) + "\n";
//			System.out.println("public base: " + publicBaseX + " " + publicBaseY);
//			System.out.println("private key: " + privateKey);
//			System.out.println(ECC.messageToPoint(b+128));
//			System.out.println(ECC.times(k, ECC.times(privateKey, new Point (publicBaseX, publicBaseY))));
//			System.out.println(ECC.times(privateKey, ECC.times(k, new Point (publicBaseX, publicBaseY))));
//			System.out.println(ECC.times(privateKey.multiply(k), new Point (publicBaseX, publicBaseY)));
		}
		try {
			ciphertext = ciphertextString.getBytes("UTF-8");
			textAreaCiphertext.setText(byteArrayToHex(ciphertext));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println((System.nanoTime()-startTime)*(Math.pow(10, -9)));
	}
	public void decrypt(){
		long startTime = System.nanoTime();
		String ciphertextString = "";
		try {
			ciphertextString = new String (ciphertext, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Byte> byteList= new ArrayList<Byte>();
		Scanner sc = new Scanner(ciphertextString);
		Point firstPoint = new Point(sc.nextBigInteger(), sc.nextBigInteger());
		ECC.setParam(privateA, privateB, privateP, new Point (privateBaseX, privateBaseY));
		while (sc.hasNext()) {
			Point secondPoint = new Point(sc.nextBigInteger(), sc.nextBigInteger());
			byteList.add(ECC.pointToMessage(ECC.minus(secondPoint, ECC.times(privateKey, firstPoint))).subtract(new BigInteger("128")).byteValue());
//			System.out.println(ECC.times(privateKey, firstPoint));
//			System.out.println(secondPoint);
//			System.out.println(ECC.minus(secondPoint, ECC.times(privateKey, firstPoint)));
//			System.out.println(ECC.add(ECC.times(privateKey, firstPoint), ECC.minus(secondPoint, ECC.times(privateKey, firstPoint))));
//			System.out.println();
		}
		sc.close();
		plaintext = new byte[byteList.size()];
		int i=0;
		for (Byte b : byteList) {
			plaintext[i] = byteList.get(i);
			i++;
		}
    	try {
			textAreaPlaintext.setText(new String(plaintext, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println((System.nanoTime()-startTime)*(Math.pow(10, -9)));
	}
	
	public void save(byte[] byteArray){
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
	        try {
	            Files.write(fc.getSelectedFile().toPath(), byteArray);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder(a.length * 2);
		for(byte b: a)
			sb.append(String.format("%02x", b & 0xff));
		return sb.toString();
	}
	
	public static void main(String args[]){
		
		// ECC.setParam(1, 1, 8009, new Point(0, 1));
		// System.out.println(ECC.times(5854*6647, new Point(0, 1)));
		
		
		
		/*
		String ciphertextString = "";
		BigInteger k = (BigInteger)(1 + (Math.random() * (5 - 1)));
		System.out.println(k);
		
		String plaintexts = "test";
		byte[] plaintext = plaintexts.getBytes();
		
		BigInteger publicA = 2;
		BigInteger publicB = 1;
		BigInteger publicP = 5;
		BigInteger publicBaseX = 0;
		BigInteger publicBaseY = 1;
		
		for (byte b : plaintext) {
			ECC.setParam(publicA, publicB, publicP, new Point (publicBaseX, publicBaseY));
			System.out.println("public base: " + publicBaseX + " " + publicBaseY);
			ciphertextString += ECC.times(k, new Point (publicBaseX, publicBaseY)).toString();
			ciphertextString += " " + ECC.add(ECC.messageToPoint(b+128), ECC.times(k, new Point(publicKeyX, publicKeyY)));
			ciphertextString += "\n";
			//System.out.println(ECC.messageToPoint(b+128));
			System.out.println(ECC.times(k, ECC.times(privateKey, new Point (publicBaseX, publicBaseY))));
			System.out.println(ECC.times(privateKey, ECC.times(k, new Point (publicBaseX, publicBaseY))));
			System.out.println(ECC.times(k*privateKey, new Point (publicBaseX, publicBaseY)));
		}
		try {
			ciphertext = ciphertextString.getBytes("UTF-8");
			textAreaCiphertext.setText(byteArrayToHex(ciphertext));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
