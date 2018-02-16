package com.round1.task557;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

    public class task557 {
        private static final String INPUT_FILE="src\\com\\round1\\task557\\input.txt";
        private static final String OUTPUT_FILE="src\\com\\round1\\task557\\output.txt";
        private static int lengthOfMatrixs;
        private static int countOfMatrix;
        private static int ROW_INDEX;
        private static int COL_INDEX;
        private static int maxDigitsForMatrix;
        private static int[][] array1;
        private static int[][] array2;

        public static void main(String[] args) throws IOException {
            writeDataToFile(multiplyMatirx(readDataFromFile()));
        }

        private static ArrayList<int[][]> readDataFromFile(){
            BufferedReader readBuff;
            try {
                readBuff = new BufferedReader(new FileReader(INPUT_FILE));
                String[] lenCountMatrix = readBuff.readLine().split(" ");
                lengthOfMatrixs = Integer.parseInt(lenCountMatrix[1]);
                countOfMatrix = Integer.parseInt(lenCountMatrix[0]);
                String[] indexOfFinalElement = readBuff.readLine().split(" ");
                ROW_INDEX = Integer.parseInt(indexOfFinalElement[0]) - 1;
                COL_INDEX = Integer.parseInt(indexOfFinalElement[1]) - 1;
                maxDigitsForMatrix = Integer.parseInt(readBuff.readLine());
                readBuff.readLine();
                return matrixReader(readBuff);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Ошибка, файл не найден!");
            } catch (IOException e) {
                throw new RuntimeException("Ошибка");
            }
        }

        private static void writeDataToFile(int finalElement) throws IOException {
            try (BufferedWriter writeBuff = new BufferedWriter(new FileWriter(OUTPUT_FILE, false))) {
                    writeBuff.write(String.valueOf(finalElement));
                }
            }

        private static ArrayList<int[][]> matrixReader(BufferedReader readBuff)
                throws IOException {
            ArrayList<int[][]> finalArrayList = new ArrayList<>(lengthOfMatrixs);
            String line;
            for (int i = 0 ; i <= countOfMatrix; i++) {
                finalArrayList.add(matrixLineReader(readBuff));
            }
            return finalArrayList;
        }

        private static int[][] matrixLineReader(BufferedReader readBuff) throws IOException {
            int[][] bufferMatrix =  new int[lengthOfMatrixs][lengthOfMatrixs];
            int colOfbuffMatris = 0;
            String line;
            while ((line = readBuff.readLine())!=null) {
                if (line.isEmpty()) break;
                    for (int j = 0 ; j < lengthOfMatrixs;j++) {
                        bufferMatrix[colOfbuffMatris][j] = Integer.parseInt(line.split(" ")[j]);
                    }
                    colOfbuffMatris++;
            }
            return bufferMatrix;
        }

        private static int multiplyMatirx(ArrayList<int[][]> arrayOfMatrixs){
            int finalyMatrix[][] =  arrayOfMatrixs.get(0);
            for (int arr = 1; arr < countOfMatrix; arr++){
                finalyMatrix = multyplyMatrixAlgorithm(finalyMatrix,arrayOfMatrixs.get(arr));
            }
            return finalyMatrix[ROW_INDEX][COL_INDEX];
        }

        private static int[][] multyplyMatrixAlgorithm(int[][] array1,int[][] array2) {
            int sum;
            int[][] buffMatrix = new int[lengthOfMatrixs][lengthOfMatrixs];
            for (int i = 0; i < lengthOfMatrixs; i++) {
                for (int j = 0; j <lengthOfMatrixs ; j++) {
                    sum=0;
                    for (int k = 0; k < lengthOfMatrixs; k++) {
                        sum += array1[i][k] * array2[k][j];
                        buffMatrix[i][j] = (sum >= maxDigitsForMatrix) ? sum % maxDigitsForMatrix : sum;
                    }
                }
            }
            return buffMatrix;
        }

    }
