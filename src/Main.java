public class Main {
    public static void main(String[] args) {
        // Test integer array
        DynamicArray<Integer> intArray = new DynamicArray<>();
        intArray.add(5);
        intArray.add(10);
        intArray.add(3);
        System.out.println("Integer Array: " + intArray);

        // Test string array
        DynamicArray<String> stringArray = new DynamicArray<>(new String[]{"Hello", "World"});
        System.out.println("String Array: " + stringArray);

        // Test custom class array
        Person person1 = new Person("John", 25);
        Person person2 = new Person("Jane", 30);
        DynamicArray<Person> personArray = new DynamicArray<>();
        personArray.add(person1);
        personArray.add(person2);
        System.out.println("Person Array: " + personArray);

        // Test matrix
        int[][] matrix = generateRandomMatrix(5, 6, -100, 100);
        System.out.println("Matrix:");
        printMatrix(matrix);

        // Test positive and negative arrays
        DynamicArray<Integer> positiveArray = getPositiveElements(matrix);
        DynamicArray<Integer> negativeArray = getNegativeElements(matrix);
        System.out.println("Positive Array: " + positiveArray);
        System.out.println("Negative Array: " + negativeArray);

        // Test reading lines from a file
        DynamicArray<Integer> lineLengths = getLineLengthsFromFile("input.txt");
        System.out.println("Line Lengths: " + lineLengths);
        printLongestLines(lineLengths, 2);
    }

    // Helper method to generate a random matrix
    private static int[][] generateRandomMatrix(int rows, int cols, int min, int max) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = (int) (Math.random() * (max - min + 1) + min);
            }
        }
        return matrix;
    }

    // Helper method to print a matrix
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    // Helper method to get positive elements from a matrix
    private static DynamicArray<Integer> getPositiveElements(int[][] matrix) {
        DynamicArray<Integer> positiveArray = new DynamicArray<>();
        for (int[] row : matrix) {
            for (int element : row) {
                if (element > 0) {
                    positiveArray.add(element);
                }
            }
        }
        return positiveArray;
    }

    // Helper method to get negative elements from a matrix
    private static DynamicArray<Integer> getNegativeElements(int[][] matrix) {
        DynamicArray<Integer> negativeArray = new DynamicArray<>();
        for (int[] row : matrix) {
            for (int element : row) {
                if (element < 0) {
                    negativeArray.add(element);
                }
            }
        }
        return negativeArray;
    }

    // Helper method to read line lengths from a file into a dynamic array
    private static DynamicArray<Integer> getLineLengthsFromFile(String filename) {
        DynamicArray<Integer> lineLengths = new DynamicArray<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineLengths.add(line.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineLengths;
    }

    // Helper method to print the numbers of the longest lines
    private static void printLongestLines(DynamicArray<Integer> lineLengths, int count) {
        System.out.println("Longest Lines:");
        for (int i = 0; i < count && i < lineLengths.size(); i++) {
            int maxLength = Integer.MIN_VALUE;
            int longestLineIndex = -1;
            for (int j = 0; j < lineLengths.size(); j++) {
                int length = lineLengths.get(j);
                if (length > maxLength) {
                    maxLength = length;
                    longestLineIndex = j;
                }
            }
            System.out.println("Line " + (longestLineIndex + 1) + ": Length = " + maxLength);
            lineLengths.remove(longestLineIndex);
        }
    }
}
