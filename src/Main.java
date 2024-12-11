import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Main implements KeyListener {
	static Game obj = new Game();

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLocation(500, 200);
		frame.setPreferredSize(new Dimension(470, 490));
		frame.getContentPane().add(obj);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(new Main());
		obj.display();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			obj.left();
			break;
		case KeyEvent.VK_RIGHT:
			obj.right();
			break;
		case KeyEvent.VK_UP:
			obj.up();
			break;
		case KeyEvent.VK_DOWN:
			obj.down();
			break;
		}
		obj.display();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
