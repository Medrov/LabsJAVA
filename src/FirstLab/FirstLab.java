package FirstLab;

import java.util.Random;

public class FirstLab {
    public static void main(String[] args) {
        //firstTask();
        //secondTask();
        thirdTask();
    }

    public static void firstTask() {
        int[] oldList = {4, 6, 7, 9, 1, 7};
        int[] list = new int[oldList.length];

        System.arraycopy(oldList, 0, list, 0, list.length);

        for (int i = 0; i < list.length / 2; i++) {
            int temp = list[i];
            list[i] = list[list.length - i - 1];
            list[list.length - i - 1] = temp;
        }

        for (int j : list) {
            System.out.print(j + " ");
        }
    }

    static void secondTask() {
        Random random = new Random();
        int[][] list = new int[5][5];
        for (int i = 0; i < list[0].length; i++) {
            for (int j = 0; j < list[1].length; j++) {
                list[i][j] = random.nextInt(10);
            }
        }
        for (int i = 0; i < list[0].length; i++) {
            for (int j = 0; j < list[1].length; j++) {
                System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }
        //sumOfDiagonals(list, list[0].length);
        sumOfPerimeter(list, list[0].length);
    }


    static void sumOfDiagonals(int[][] matrix, int N) {
        int Pd = 0, Sd = 0;
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                if (k == l) Pd += matrix[k][l];
                if ((k + l) == (N - 1)) Sd += matrix[k][l];
            }
        }
        System.out.println("Сумма по правой главной: " + Pd);
        System.out.println("Сумма по левой главной: " + Sd);
    }

    static void sumOfPerimeter(int[][] matrix, int N) {
        int result = 0;
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                if (k == 0) result += matrix[k][l];
                else if (l == 0 || l == matrix[0].length - 1) result += matrix[k][l];
                else if (k == matrix[0].length - 1) result += matrix[k][l];
            }
        }
        System.out.println("Result: " + result);
    }

    static void thirdTask() {
        Random random = new Random();
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        int[][] list = new int[8][8];
        for (int i = 0; i < list[0].length; i++) {
            for (int j = 0; j < list[1].length; j++) {
                list[i][j] = random.nextInt(10);
            }
        }
        for (int i = 0; i < list[0].length; i++) {
            for (int j = 0; j < list[1].length; j++) {
                System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        int result = 0;
        for (int i = 0; i < list.length; i++) {
            System.out.print(""+(i+1)+" ");
            for (int j = 0; j < list.length/2; j++) {
                if(i % 2 == 0) {
                    System.out.print("X   ");
                    System.out.print("Y   ");
                } else {
                    System.out.print("Y   ");
                    System.out.print("X   ");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < list.length; i++) {
            System.out.print("  "+chars[i]+" ");
        }

    }
}
