public class Arrays {
    public static void main(String[] args) {
        int[][] Numbers = {{4, 9, 3, 6, 4}, {8, 3, 2, 1, 0}};

        for (int i = 0; i < Numbers.length; i++) {
            for (int j = 0; j < Numbers[i].length; j++) {
                System.out.println(Numbers[i][j]);
            }
        }
    }
}