import java.util.Arrays;

public class C2048 {
	int value[][] = new int[4][4];
	boolean merged[][] = new boolean[4][4];
	boolean hasMoved = false;

	C2048() {
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
