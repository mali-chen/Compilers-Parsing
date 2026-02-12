class Main extends Lib {
	int x;
	public void main() {
		int y = 4;
		int z = x + y;
	}
	public int add(int z, int b) {
		for (int i = 0; i < 100; i++) {
			z = z + i;
		}
		return z + b;
	}
}
