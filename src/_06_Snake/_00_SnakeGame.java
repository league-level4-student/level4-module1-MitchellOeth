package _06_Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

// Go through the methods and complete the steps in this class
// and the Snake class

public class _00_SnakeGame implements ActionListener, KeyListener {
	public static final Color BORDER_COLOR = Color.WHITE;
	public static final Color BACKGROUND_COLOR = Color.BLACK;
	public static final Color FOOD_COLOR = Color.RED;
	public static final int WIDTH = 15;
	public static final int HEIGHT = 12;
	public static final int WINDOW_SCALE = 50;
	public static final int WINDOW_WIDTH = WINDOW_SCALE * WIDTH;
	public static final int WINDOW_HEIGHT = WINDOW_SCALE * HEIGHT;

	private JFrame window;
	private JPanel panel;

	private Snake snake;

	private Timer timer;

	private Location foodLocation;

	public _00_SnakeGame() {
		snake = new Snake(new Location(WIDTH / 2, HEIGHT / 2));

		window = new JFrame("Snake");
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;

				g2.setColor(BACKGROUND_COLOR);
				g2.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

				g2.setColor(FOOD_COLOR);
				g2.drawOval(foodLocation.x * WINDOW_SCALE, foodLocation.y * WINDOW_SCALE, Snake.BODY_SIZE,
						Snake.BODY_SIZE);
				snake.draw(g);
			}
		};

		panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		window.add(panel);

		timer = new Timer(0, this);

		window.pack();
		window.addKeyListener(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		setFoodLocation();

		startGame();
	}

	public void startGame() {
		//1. Save the instructions for the game in the following string variable.
		String instructions = "If you don't already know how to play this game, you shouldn't need instructions to tell you how to play. Suck it up.";
		
		String[] options = new String[] { "Expert", "Moderate", "Beginner" };
		int input = JOptionPane.showOptionDialog(null, instructions, "Snake", 0, -1, null, options, 0);

		String choice = options[input];
		
		//2. Use a switch statement to determine which difficulty was chosen.
		//   Use timer.setDelay(delay) with different numbers to change the speed
		//   of the game. The smaller the number, the faster it goes.
		switch(choice) {
			case "Beginner": 
				timer.setDelay(100);
				break;
			case "Moderate":
				timer.setDelay(50);
				break;
			case "Expert":
				timer.setDelay(25);
		}
		//3. start the timer
		timer.start();
	}

	public static void main(String[] args) {
		new _00_SnakeGame();
	}
	
	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		//1. Use a switch statement on e.getKeyCode()
		//   to determine which key was pressed.

		// if an arrow key is pressed, set the snake's 
		// direction accordingly
		
		// if the space key is pressed, call the snake's feed method
		
		switch(e.getKeyCode()) {
			case 37:
				//Left Arrow
				snake.setDirection(Direction.LEFT);
				break;
			case 38:
				//Up Arrow
				snake.setDirection(Direction.UP);
				break;
			case 39:
				//Right Arrow
				snake.setDirection(Direction.RIGHT);
				break;
			case 40:
				//Down Arrow
				snake.setDirection(Direction.DOWN);
				break;
			case 32:
				//Spacebar
				snake.feed();
				
		}
	}

	private void setFoodLocation() {
		//1. Create a new Location object that is set to a random location
		Random randy = new Random();
		Location randLoc = new Location(randy.nextInt(15), randy.nextInt(12));
		//2. set the foodLocation variable equal to the Location object you just created.
		//   use the snake's isLocationOnSnake method to make sure you don't put the food on the snake
		foodLocation = randLoc;
		if (snake.isLocationOnSnake(foodLocation) == true) {
			setFoodLocation();
		}
	}

	private void gameOver() {
		
		//1. stop the timer
		timer.stop();
		//2. tell the user their snake is dead
		JOptionPane.showMessageDialog(null, "You've killed yourself, well done.");
		//3. ask them if they want to play again.
		String[] options = { "Play Again", "No Thanks"};
		int input = JOptionPane.showOptionDialog(null, null, "Game Over", 0, -1, null, options, 0);
		String choice = options[input];
		//4. if they want to play again
		//   reset the snake and the food and start the timer
		//   else, exit the game
		switch(choice) {
			case "Play Again":
				snake = new Snake(new Location(WIDTH / 2, HEIGHT / 2)); 
				//reset food
				timer.restart();
				break;
			case "No Thanks":
				System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void actionPerformed(ActionEvent e) {
		//1. update the snake
		snake.update();
		//2. if the snake is colliding with its own body 
		//   or if the snake is out of bounds, call gameOver
		if (snake.isHeadCollidingWithBody() == true ) {
			gameOver();
		}
		if (snake.isOutOfBounds() == true ) {
			gameOver();
		}
		//3. if the location of the head is equal to the location of the food,
		// 	 feed the snake and set the food location
		if (snake.getHeadLocation() == foodLocation) {
			snake.feed();
			setFoodLocation();
		}
		//4. call panel.repaint();
		panel.repaint();
	}
}
