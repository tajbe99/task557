package com.round1.task557;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayDeque;
import java.util.Arrays;

    public class task557 {
        private static final String INPUT_FILE="src\\com\\round1\\task557\\input.txt";
        private static final String OUTPUT_FILE="src\\com\\round1\\task557\\output.txt";
        private static int lengthOfMatrixs;
        private static int countOfMatrix;
        private static int rowIndex;
        private static int colIndex;
        private static int maxDigitsForMatrix;

        public static void main(String[] args) throws IOException {
            writeDataToFile(multiplyMatirx(readDataFromFile()));
        }

        private static ArrayDeque<int[][]> readDataFromFile(){
            BufferedReader readBuff;
            try {
                readBuff = new BufferedReader(new FileReader(INPUT_FILE));
                String[] lenCountMatrix = readBuff.readLine().split(" ");
                lengthOfMatrixs = Integer.parseInt(lenCountMatrix[1]);
                countOfMatrix = Integer.parseInt(lenCountMatrix[0]);
                String[] indexOfFinalElement = readBuff.readLine().split(" ");
                rowIndex = Integer.parseInt(indexOfFinalElement[0]) - 1;
                colIndex = Integer.parseInt(indexOfFinalElement[1]) - 1;
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

        private static ArrayDeque<int[][]> matrixReader(BufferedReader readBuff)
                throws IOException {
            ArrayDeque<int[][]> finalArrayList = new ArrayDeque<>(lengthOfMatrixs);
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

        private static int multiplyMatirx(ArrayDeque<int[][]> arrayOfMatrixs){
            while (arrayOfMatrixs.size()>2){
                arrayOfMatrixs.push(multyplyMatrixAlgorithm(arrayOfMatrixs.pop(),arrayOfMatrixs.pop()));
            }
            return arrayOfMatrixs.pop()[rowIndex][colIndex];
        }

        private static int[][] multyplyMatrixAlgorithm(int[][] array1,int[][] array2) {
            int[][] buffMatrix = new int[lengthOfMatrixs][lengthOfMatrixs];
            for (int i = 0; i < lengthOfMatrixs; i++) {
                for (int j = 0; j <lengthOfMatrixs ; j++) {
                    int sum=0;
                    for (int k = 0; k < lengthOfMatrixs; k++) {
                        sum += array1[i][k] * array2[k][j];
                        buffMatrix[i][j] = (sum >= maxDigitsForMatrix) ? sum % maxDigitsForMatrix : sum;
                    }
                }
            }
            return buffMatrix;
        }

    }
