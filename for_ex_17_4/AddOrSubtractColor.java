package for_ex_17_4;

//Kristin Alise Jones
//January 26, 2015

//Six buttons will increase or decrease one each of the R, G, or B components of the
//background color of the content pane until the maximum or minimum
//is reached for each component.
//A reset button resets the background color of the content pane to black.

	import java.awt.*; // for original GUI stuff
	import java.awt.event.*; // for event handling

	import javax.swing.*; //for swing GUI stuff

	public class AddOrSubtractColor extends JFrame {

		private JButton redB,
					greenB,
					blueB;//user clicks these to add color to content pane's background
		private JButton lessRedB, lessGreenB, lessBlueB; //user clicks to decrease CP color
		private JButton resetB; // to reset all color components to 0
		private JTextField showRGBTF;//will display the RGB components of the color of the CP
		private Container myCP; //to hold a reference to the content pane of the JFrame

		public AddOrSubtractColor () {

			super("Increase Color Components");
			setSize(650, 300);
			setLocation(100,100);
			myCP = getContentPane();
			myCP.setLayout(null);//there is no layout manager
			myCP.setBackground(Color.BLACK);// red, green, and blue components are each 0

			redB = makeButton(30,75,120,50, "Increase Red",Color.RED, 
					(ActionEvent e) -> handleOneColor(Color.RED));
			greenB = makeButton(180,75,120,50, "Increase Green",Color.GREEN, 
					(ActionEvent e) -> handleOneColor(Color.GREEN));
			blueB = makeButton(330,75,120,50, "Increase Blue",Color.BLUE, 
					(ActionEvent e) -> handleOneColor(Color.BLUE));
			resetB = makeButton(500,75,120,50, "Reset",Color.BLACK, 
					(ActionEvent e) -> reset());
			lessRedB = makeButton(30, 200, 120, 50, "Decrease Red", Color.RED,
					(ActionEvent e) -> handleTheOtherColor(Color.RED));
			lessGreenB = makeButton(180, 200, 120, 50, "Decrease Green", Color.GREEN, 
					(ActionEvent e) -> handleTheOtherColor(Color.GREEN));
			lessBlueB = makeButton(330, 200, 120, 50, "Decrease Blue", Color.BLUE,
					(ActionEvent e) -> handleTheOtherColor(Color.BLUE));
					
			showRGBTF = new JTextField("R is 0, G is 0, and B is 0.");
			showRGBTF.setSize(200,30);
			showRGBTF.setLocation(225,150);
			showRGBTF.setEditable(false);
			myCP.add(showRGBTF);

			//This statement makes sure the window is visible on the screen
			setVisible(true);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}//windowClosing
			}); //end of definition of WindowAdapter and semicolon to end the line 

		} // AddorSubtractColor constructor 
		
		private JButton makeButton(int theX, int theY, int theWidth, int theHeight,
					String theText, Color theForeground, ActionListener theHandler){
			JButton toReturn = new JButton(theText);
			toReturn.setSize(theWidth, theHeight);
			toReturn.setLocation(theX, theY);
			toReturn.setForeground(theForeground);
			myCP.add(toReturn);
			toReturn.addActionListener(theHandler);
			return toReturn;
		}//makeButton

		private int handleIncrease(int theComponent, JButton theButton){
			if (theComponent + 20 > 255 ) {
				theComponent = 255;
				theButton.setEnabled(false);
			} else {
				theComponent = theComponent + 20;
			}//else
			return theComponent;
		}//handleIncrease
		
		private int handleDecrease(int theOtherComponent, JButton theOtherButton){
			if(theOtherComponent - 20 < 0) {
				theOtherComponent = 0;
				theOtherButton.setEnabled(false);
			} else {
				theOtherComponent = theOtherComponent - 20;
			}
			return theOtherComponent;
		}//handleDecrease
		
		
		private void checkIfDecreaseButtonNeedsEnabling(int theColorComponent, JButton theColorButton) {
			if(theColorComponent - 20 >= 0) {
				theColorButton.setEnabled(true);
			} 
		}//checkIfDecreaseButtonNeedsEnabling
		
		private void checkIfIncreaseButtonNeedsEnabling(int theOtherColorComponent, JButton theOtherColorButton) {
			if(theOtherColorComponent + 20 <= 255) {
				theOtherColorButton.setEnabled(true);
			} 
		}//checkIfIncreaseButtonNeedsEnabling
		
		private void handleOneColor(Color c){
			Color currentBackgroundColor = myCP.getBackground();
			int redComponent = currentBackgroundColor.getRed();
			int greenComponent = currentBackgroundColor.getGreen();
			int blueComponent = currentBackgroundColor.getBlue();
			if(c.equals(Color.RED)){
				redComponent = handleIncrease(redComponent, redB);
				checkIfDecreaseButtonNeedsEnabling(redComponent, lessRedB);
			} else if (c.equals(Color.GREEN)){
				greenComponent = handleIncrease(greenComponent, greenB);
				checkIfDecreaseButtonNeedsEnabling(greenComponent, lessGreenB);
			} else {
				blueComponent = handleIncrease(blueComponent, blueB);
				checkIfDecreaseButtonNeedsEnabling(blueComponent, lessBlueB);
			}//last else
			Color adjustedColor = new Color 
					(redComponent, greenComponent, blueComponent);
			myCP.setBackground(adjustedColor);
			showRGBTF.setText ("R is " + redComponent + ", G is " + greenComponent 
					+ ", B is " + blueComponent + "."); 
		}//handleOneColor
			
		private void handleTheOtherColor(Color otherC){
			Color otherCurrentBackgroundColor = myCP.getBackground();
			int otherRedComponent = otherCurrentBackgroundColor.getRed();
			int otherGreenComponent = otherCurrentBackgroundColor.getGreen();
			int otherBlueComponent = otherCurrentBackgroundColor.getBlue();
			if(otherC.equals(Color.RED)){
				otherRedComponent = handleDecrease(otherRedComponent, lessRedB);
				checkIfIncreaseButtonNeedsEnabling(otherRedComponent, redB);
			} else if (otherC.equals(Color.GREEN)){
				otherGreenComponent = handleDecrease(otherGreenComponent, lessGreenB);
				checkIfIncreaseButtonNeedsEnabling(otherGreenComponent, greenB);
			} else {
				otherBlueComponent = handleDecrease(otherBlueComponent, lessBlueB);
				checkIfIncreaseButtonNeedsEnabling(otherBlueComponent, blueB);
			}//last else
			Color adjustedColor = new Color 
					(otherRedComponent, otherGreenComponent, otherBlueComponent);
			myCP.setBackground(adjustedColor);
			showRGBTF.setText ("R is " + otherRedComponent + ", G is " + otherGreenComponent 
					+ ", B is " + otherBlueComponent + "."); 
		}//handleTheOtherColor
		
		private void reset(){
			myCP.setBackground(Color.BLACK);
			showRGBTF.setText ("R is 0, G is 0, B is 0.");
			redB.setEnabled(true);
			greenB.setEnabled(true);
			blueB.setEnabled(true);
			lessRedB.setEnabled(true);
			lessGreenB.setEnabled(true);
			lessBlueB.setEnabled(true); 
		}//reset
		
		public static void main (String args[]) {
			AddOrSubtractColor myAppF = new AddOrSubtractColor ();
		} //main

	} // AddOrSubtractColor
