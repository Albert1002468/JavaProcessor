import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class JavaProcessor extends JFrame implements ActionListener
{
	private static final int WIDTH = 360;
	private static final int HEIGHT = 160;
	private JPanel mainPanel;
	private JTextField inputFile,outputFile;
	private static ArrayList<String> textArray = new ArrayList<String>();

	public JavaProcessor()
	{
		super();
		setSize(WIDTH, HEIGHT);
		setLocation(530,150);
		setTitle("JavaProcessor");

		addWindowListener(new WindowDestroyer());

		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
	  
		
		
		JPanel textPanel1 = new JPanel();
	   	JLabel enterInputFile = new JLabel(" Input file",JLabel.TRAILING);
		inputFile = new JTextField(20);
		textPanel1.add(enterInputFile);
		textPanel1.add(inputFile);

		JPanel textPanel2 = new JPanel();
	   	JLabel enterOutputFile = new JLabel("Output file",JLabel.TRAILING);
		outputFile = new JTextField(20);
		textPanel2.add(enterOutputFile);
		textPanel2.add(outputFile);
		
		JPanel mainTextPanel = new JPanel();
		mainTextPanel.add(textPanel1);
		mainTextPanel.add(textPanel2);
		

		
	    JButton submitButton = new JButton("Submit");
	    submitButton.setToolTipText("Takes the input file and corrects it's syntax errors and exports the results to the export file");
	    submitButton.addActionListener(this);
	    
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new FlowLayout());
	    buttonPanel.add(submitButton);
	   	
	    
	    
		mainPanel.add(mainTextPanel,BorderLayout.CENTER);
	    mainPanel.add(buttonPanel,BorderLayout.PAGE_END);

	    }
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Submit"))
		{
			inputOutput();
			inputFile.setText("");
			outputFile.setText("");

		} 
		else
			System.out.println("Error in button interface");
	}
	
	public static void main(String[] args) 
	{
		JavaProcessor javaProcessorGUI = new JavaProcessor();
		javaProcessorGUI.setVisible(true);
	}
	
	public void inputOutput()
	{
		String fileName = "";
		try
		{
			fileName = inputFile.getText();
			
			Scanner inputStream = new Scanner(new File(fileName));
			while (inputStream.hasNextLine())
			{
				textArray.add(inputStream.nextLine());
			}
			inputStream.close();
			
			JavaProcessor processor = new JavaProcessor();
			processor.correctSemicolonSnytax();
			processor.correctCompoundStatementSyntax();
			processor.correctCommentSyntax();
			processor.correctIndentation();
			fileName = outputFile.getText();
			PrintWriter outputStream = new PrintWriter(new FileOutputStream(fileName));
			for (int i=0;i<textArray.size();i++)
				outputStream.println(textArray.get(i));
			outputStream.close();
			JOptionPane.showMessageDialog(mainPanel, "Success!");
		}
		
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(mainPanel, "File "+ fileName + " not found.");
		
		}
		
	}
	
	public void correctSemicolonSnytax()
	{
		//Matt's part
	}
	
	public void correctCompoundStatementSyntax()
	{
		int leftCurlyBraceCount = 0;
		int rightCurlyBraceCount = 0;
		for (int i = 0; i<textArray.size();i++)
		{
			String temp = textArray.get(i);
			for (int z =0; z<temp.length();z++)
			{
				if(temp.charAt(z)=='{')
					leftCurlyBraceCount++;
				
				else if(temp.charAt(z)=='}')
					rightCurlyBraceCount++;
			}

		}
		if(leftCurlyBraceCount!=rightCurlyBraceCount)
		{
			for(int i = 0; i<leftCurlyBraceCount-rightCurlyBraceCount;i++)
				textArray.set(textArray.size()-1,textArray.get(textArray.size()-1)+"}");
		}
	}
	
	public void correctCommentSyntax()
	{
		//Matt's part
	}
	
	public void correctIndentation()
	{
		//Matt's part
	}

}
class WindowDestroyer extends WindowAdapter
{
	public void windowClosing(WindowEvent e)//window destroyer method
	{
	    System.exit(0);
	}
	
}
