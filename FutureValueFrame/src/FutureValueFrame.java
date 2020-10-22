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
		setSize(400, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);

		JPanel panel = new JPanel();
		add(panel);
		
		panel.add(new JLabel("Monthly Investment:"));
		JTextField investmentField = new JTextField(20);
		panel.add(investmentField);
		panel.add(new JLabel("Yearly Interest Rate:"));
		panel.add(new JLabel("Years:"));
		JTextField yearsField = new JTextField(10);
		panel.add(yearsField);
		panel.add(new JLabel("Future Value:"));
		
		JButton calculateButton = new JButton("Calculate");
		panel.add(calculateButton);
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(e -> {System.exit(0);});

		panel.add(exitButton);
		
		
		
		setVisible(true);
	}
	public static void main(String[] args) 
	{
		java.awt.EventQueue.invokeLater(() ->
		{
			JFrame frame = new FutureValueFrame();
		});
	}

}
