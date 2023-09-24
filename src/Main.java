import java.util.HashSet;

public class Main {


    public static int missingNumberFinder(HashSet<Integer> set) {

        int[] oneToTen = {1,2,3,4,5,6,7,8,9};
        int notIncluded = 0;
        int notIncludedNumber = 0;

        // if only one number is not included then notIncludedNumber will only be initialized ONCE ,
        // we need to return that number

        for( int i : oneToTen) {

            if(!set.contains(i)) {
                notIncluded++;
                notIncludedNumber = i;
            }

        }

        if(notIncluded != 1) {
            return 0;
        } else {
            return notIncludedNumber;
        }

    }

    public static int tryToSolve(int[][] sudokuArray , int rowNumber , int columnNumber) {

        HashSet<Integer> currentRow = new HashSet<>();
        HashSet<Integer> currentColumn = new HashSet<>();
        HashSet<Integer> currentField = new HashSet<>();

        for(int i = 0; i < 9 ; i++) {
            currentRow.add(sudokuArray[rowNumber][i]);
        }

        for(int i = 0; i < 9; i++) {
            currentColumn.add(sudokuArray[i][columnNumber]);
        }

        int blockStartRow = (rowNumber / 3) * 3;
        int blockStartCol = (columnNumber / 3) * 3;

        for(int i = blockStartRow; i < blockStartRow +3;i++) {
            for(int j = blockStartCol; j < blockStartCol+3;j++) {
                currentField.add(sudokuArray[i][j]);
            }
        }

        HashSet<Integer> finalSet = new HashSet<>();

        finalSet.addAll(currentRow);
        finalSet.addAll(currentColumn);
        finalSet.addAll(currentField);

        return missingNumberFinder(finalSet);
    }

    public static boolean isFinished(int[][] sudokuArray) {

        boolean isFinished = true;

        for(int i = 0; i < sudokuArray.length ; i++) {
            for(int j = 0; j < sudokuArray[i].length ; j++) {
                if(sudokuArray[i][j] == 0) {
                    isFinished = false;
                    break;
                }
            }
        }

        return isFinished;

    }

    public static void main(String[] args) {

        // INPUT SUDOKU PUZZLE HERE

        int[][] sudokuArray =
                {
                        {4,2,0,0,0,1,0,6,3},
                        {0,0,5,0,0,0,8,0,0},
                        {0,6,0,9,0,4,0,7,0},
                        {5,0,1,8,0,0,7,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,7,0,0,5,3,0,4},
                        {0,3,0,4,0,6,0,1,0},
                        {0,0,6,0,0,0,4,0,0},
                        {2,5,0,7,0,0,0,9,8}
                };


        while (!isFinished(sudokuArray)) {

            for(int i = 0 ; i < sudokuArray.length ; i++) {
                for(int j = 0 ; j < sudokuArray[i].length;j++) {

                    if(sudokuArray[i][j] == 0) {

                        int result = tryToSolve(sudokuArray,i,j);

                        if(result != 0) {
                            sudokuArray[i][j] = result;
                        }

                    }

                }
            }

        }

        for(int i = 0 ; i < sudokuArray.length ; i++) {
            for(int j = 0; j < sudokuArray[i].length;j++) {

                System.out.print(sudokuArray[i][j] + " ");

            }
            System.out.println();
        }

    }

}