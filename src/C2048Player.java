import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class C2048Player extends JFrame implements KeyListener {
	C2048 obj;

	public C2048Player() {
		obj = new C2048(); // C2048 객체 생성
		setTitle("2048 Game"); // 창 제목 설정
		setSize(400, 400); // 창 크기 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this); // KeyListener 추가
		setVisible(true); // 창 표시
		obj.display();
	}

	public static void main(String[] args) {
		new C2048Player();
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
