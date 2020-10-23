import javax.swing.*;
import java.awt.*;


public class FutureValueFrame extends JFrame
{
	public FutureValueFrame()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			System.out.println(e);
		}
		
		setTitle("Future Value Calculator Friend");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0; c.gridy = 0;
		panel.add(new JLabel("Monthly Investment:"), c);
		
		c.gridx = 1; c.gridy = 0;
		JTextField investmentField = new JTextField(20);
		panel.add(investmentField, c);
		
		c.gridx = 0; c.gridy = 1;
		panel.add(new JLabel("Yearly Interest Rate:"), c);
		
		c.gridx = 1; c.gridy = 1;
		JTextField interestRateField = new JTextField(20);
		panel.add(interestRateField, c);
		
		c.gridx = 0; c.gridy = 2;
		panel.add(new JLabel("Years:"), c);
		
		c.gridx = 1; c.gridy = 2;
		JTextField yearsField = new JTextField(20);
		panel.add(yearsField, c);
		
		c.gridx = 0; c.gridy = 3;
		panel.add(new JLabel("Future Value:"), c);
		
		c.gridx = 0; c.gridy = 4;
		JButton calculateButton = new JButton("Calculate");
		panel.add(calculateButton, c);
		
		c.gridx = 1; c.gridy = 4;
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(e -> {System.exit(0);});

		panel.add(exitButton, c);
		
		
		
		setVisible(true);
	}
	public static void main(String[] args) 
	{
		java.awt.EventQueue.invokeLater(() ->
		{
			JFrame frame = new FutureValueFrame();
			//frame.setLayout(new FlowLayout());
		});
	}

}
