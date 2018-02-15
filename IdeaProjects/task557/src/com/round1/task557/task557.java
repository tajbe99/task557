package com.round1.task557;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

    public class task557 {
        private static String INPUT_FILE="src\\com\\round1\\task557\\input.txt";
        private static String OUTPUT_FILE="src\\com\\round1\\task557\\output.txt";
        private static int lengthOfMatrixs;
        private static int countOfMatrix;
        private static String indexOfFinalElement;
        private static int maxDigitsForMatrix;
        private static ArrayList<int[][]> arrayOfMatrixs;

        public static void main(String[] args) throws IOException {
            readDataFromFile();
            int[][] finalMatrix = MultiplyMatirx(arrayOfMatrixs, lengthOfMatrixs, countOfMatrix, maxDigitsForMatrix);
            writeDataToFile(indexOfFinalElement, finalMatrix);
            System.out.println(Arrays.deepToString
                        (MultiplyMatirx(arrayOfMatrixs,lengthOfMatrixs,countOfMatrix,maxDigitsForMatrix)));
        }

        private static void readDataFromFile(){
            BufferedReader readBuff;
            try {
                readBuff = new BufferedReader(new FileReader(INPUT_FILE));
                String[] lenCountMatrix = readBuff.readLine().split(" ");
                lengthOfMatrixs = Integer.parseInt(lenCountMatrix[1]);
                countOfMatrix = Integer.parseInt(lenCountMatrix[0]);
                indexOfFinalElement = readBuff.readLine();
                maxDigitsForMatrix = Integer.parseInt(readBuff.readLine());
                readBuff.readLine();
                arrayOfMatrixs = MatrixReader(readBuff, lengthOfMatrixs, countOfMatrix);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Ошибка, файл не найден!");
            } catch (IOException e) {
                throw new RuntimeException("Ошибка");
            }
        }

        private static void writeDataToFile(String indexOfFinalElement, int[][] finalMatrix) throws IOException {
            try (BufferedWriter writeBuff = new BufferedWriter(new FileWriter(OUTPUT_FILE, false))) {
                String[] indexesOfFinalMatrixElemet = indexOfFinalElement.split(" ");
                if (finalMatrix != null) {
                    int answerDigit =finalMatrix[Integer.parseInt(indexesOfFinalMatrixElemet[0]) - 1]
                            [Integer.parseInt(indexesOfFinalMatrixElemet[1]) - 1];
                    writeBuff.write(String.valueOf(answerDigit));
                }
            }
        }

        private static ArrayList<int[][]> MatrixReader(BufferedReader readBuff, int lengthOfMatrixs, int countOfMatrix)
                throws IOException {
            ArrayList<int[][]> finalArrayList = new ArrayList<>(lengthOfMatrixs);
            int colOfbuffMatris;
            int[][] bufferMatrix =  new int[lengthOfMatrixs][lengthOfMatrixs];
            String line;
            for (int i =0;i<=countOfMatrix;i++) {
                colOfbuffMatris = 0;
                while ((line=readBuff.readLine())!=null) {
                    if (line.length() == 0) break;
                        for (int j =0;j<lengthOfMatrixs;j++) {
                            bufferMatrix[colOfbuffMatris][j] = Integer.parseInt(line.split(" ")[j]);
                        }
                        colOfbuffMatris++;
                }
                finalArrayList.add(bufferMatrix);
                bufferMatrix = new int[lengthOfMatrixs][lengthOfMatrixs];
            }
            return finalArrayList;
        }

        private static int[][] MultiplyMatirx(ArrayList<int[][]> arrayOfMatrix,
                                              int lengthOfMatrixs,int countOfMatrix,int maxDigit){
            int sum;
            int[][] finalMatrix = new int[lengthOfMatrixs][lengthOfMatrixs];
            for (int i = 0; i < lengthOfMatrixs; i++) {
                System.arraycopy(arrayOfMatrix.get(0)[i], 0, finalMatrix[i], 0, lengthOfMatrixs);
            }
            for (int arr=1;arr<countOfMatrix;arr++){
                int[][] buffMatrix = new int[lengthOfMatrixs][lengthOfMatrixs];
                for (int i = 0; i < lengthOfMatrixs; i++) {
                    for (int j = 0; j <arrayOfMatrix.get(arr).length ; j++) {
                        sum=0;
                        for (int k = 0; k < lengthOfMatrixs; k++) {
                            sum += finalMatrix[i][k] * arrayOfMatrix.get(arr)[k][j];
                            buffMatrix[i][j] = (sum >= maxDigit) ? sum % maxDigit : sum;
                        }
                    }
                }
                finalMatrix=buffMatrix;
            }
            return finalMatrix;
        }

}
