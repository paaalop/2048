import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;

public class Game extends JPanel {
	int value[][] = new int[4][4];
	boolean merged[][] = new boolean[4][4];
	boolean hasMoved = false;

	Game() {
		init();
	}

	void init() {
		addRandomTile();
		addRandomTile();

	}

	void update() {
		addRandomTile();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				merged[i][j] = false;
			}
		}
		repaint();

	}

	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				drawTile(g, i, j, value[i][j]);
			}
		}
	}

	void addRandomTile() {
		while (true) {
			int i = (int) (Math.random() * 4);
			int j = (int) (Math.random() * 4);
			if (value[i][j] == 0) {
				value[i][j] = ((int) (Math.random() * 2) + 1) * 2;
				break;
			}
		}
	}

	Color getColor(int value) {
		switch (value) {
		case 2:
			return new Color(238, 228, 218);
		case 4:
			return new Color(237, 224, 200);
		case 8:
			return new Color(242, 177, 121);
		case 16:
			return new Color(245, 149, 99);
		case 32:
			return new Color(246, 124, 95);
		case 64:
			return new Color(246, 94, 59);
		case 128:
			return new Color(237, 207, 114);
		case 256:
			return new Color(237, 204, 97);
		case 512:
			return new Color(237, 204, 0);
		case 1024:
			return new Color(237, 197, 63);
		case 2048:
			return new Color(237, 194, 46);
		default:
			return Color.LIGHT_GRAY;
		}
	}

	void drawTile(Graphics g, int row, int col, int value) {
		int x = col * 100 + 10 * col;
		int y = row * 100 + 10 * row;

		g.setColor(getColor(value));
		g.fillRect(x + 10, y + 10, 100, 100);

		g.setColor(Color.black);
		g.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		if (value != 0) {
			String text = String.valueOf(value);
			int stringWidth = g.getFontMetrics().stringWidth(text);
			int stringHeight = g.getFontMetrics().getHeight();

			int stringX = x + (120 - stringWidth) / 2;
			int stringY = y + (110 + stringHeight) / 2 - g.getFontMetrics().getDescent();

			g.drawString(String.valueOf(value), stringX, stringY);
		}

	}

	void swap(int x1, int y1, int x2, int y2) {
		int temp = value[x1][y1];
		value[x1][y1] = value[x2][y2];
		value[x2][y2] = temp;
	}

	boolean merge(int x1, int y1, int x2, int y2) {
		if (x1 >= 0 && x1 <= 3 && y1 >= 0 && y1 <= 3 && x2 >= 0 && x2 <= 3 && y2 >= 0 && y2 <= 3) {
			if (value[x1][y1] == value[x2][y2] && !merged[x2][y2]) {
				value[x2][y2] *= 2;
				value[x1][y1] = 0;
				merged[x2][y2] = true;
				return true;
			}
		}
		return false;
	}

	void left() {
		hasMoved = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				if (value[i][j] != 0) {
					int pivot = j - 1;

					while (pivot > -1 && value[i][pivot] == 0)
						--pivot;

					if (merge(i, j, i, pivot))
						hasMoved = true;

					else if (pivot + 1 != j) {
						swap(i, j, i, pivot + 1);
						hasMoved = true;
					}
				}
			}
		}
		if (hasMoved)
			update();

		display();
	}

	void right() {
		hasMoved = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j >= 0; j--) {
				if (value[i][j] != 0) {
					int pivot = j + 1;

					while (pivot < 4 && value[i][pivot] == 0)
						++pivot;

					if (merge(i, j, i, pivot))
						hasMoved = true;

					else if (pivot - 1 != j) {
						swap(i, j, i, pivot - 1);
						hasMoved = true;
					}
				}
			}
		}
		if (hasMoved)
			update();

		display();
	}

	void up() {
		hasMoved = false;
		for (int i = 1; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (value[i][j] != 0) {
					int pivot = i - 1;

					while (pivot > -1 && value[pivot][j] == 0)
						--pivot;

					if (merge(i, j, pivot, j))
						hasMoved = true;

					else if (pivot + 1 != i) {
						swap(i, j, pivot + 1, j);
						hasMoved = true;
					}
				}
			}
		}
		if (hasMoved)
			update();

		display();
	}

	void down() {
		hasMoved = false;
		for (int i = 2; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				if (value[i][j] != 0) {
					int pivot = i + 1;

					while (pivot < 4 && value[pivot][j] == 0)
						++pivot;

					if (merge(i, j, pivot, j))
						hasMoved = true;

					else if (pivot - 1 != i) {
						swap(i, j, pivot - 1, j);
						hasMoved = true;
					}
				}
			}
		}
		if (hasMoved)
			update();

		display();

	}

	void display() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (value[i][j] == 0)
					System.out.printf("   |");
				else
					System.out.printf("%3d|", value[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
