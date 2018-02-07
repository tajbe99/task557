package com.round1.task557;

import java.io.*;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;

public class task557 {
    public static void main(String[] args) throws IOException {
        String line;
        BufferedReader readBuff = null;
        try {
            readBuff = new BufferedReader(new FileReader("src\\com\\round1\\task557\\input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert readBuff != null;
            String firstLine = readBuff.readLine();
            Integer lengthOfMatrixs = Integer.parseInt(firstLine.split(" ")[1]);
            Integer countOfMatrix = Integer.parseInt(firstLine.split(" ")[0]);
            String indexOfFinalElement = readBuff.readLine();
            Integer maxDigitsForMatrix = Integer.parseInt(readBuff.readLine());
            readBuff.readLine();
            ArrayList<Integer[][]> arrayOfMatrixs = MatrixReader(readBuff, lengthOfMatrixs, countOfMatrix);
            Integer[][] finalMatrix = ((lengthOfMatrixs <= 130 && lengthOfMatrixs >= 1) == (countOfMatrix <= 130 && countOfMatrix >= 1)) ? MultiplyMatirx(arrayOfMatrixs, lengthOfMatrixs, countOfMatrix) : null;
            finalMatrix = (finalMatrix != null && maxDigitsForMatrix < 1000) ? CheckConstraitsn(finalMatrix, maxDigitsForMatrix) : null;
            try (BufferedWriter writeBuff = new BufferedWriter(new FileWriter("src\\com\\round1\\task557\\output.txt", false))) {
                if (finalMatrix != null) {
                    writeBuff.write(finalMatrix[Integer.parseInt(indexOfFinalElement.split(" ")[0]) - 1][Integer.parseInt(indexOfFinalElement.split(" ")[1]) - 1].toString());
                } else {
                    writeBuff.write("Значения в входном фале не подходят по условию");
                }
            }
            System.out.println(Arrays.deepToString(MultiplyMatirx(arrayOfMatrixs,lengthOfMatrixs,countOfMatrix)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            assert readBuff != null;
            readBuff.close();
        }
    }

    private static ArrayList<Integer[][]> MatrixReader(BufferedReader readBuff, Integer lengthOfMatrixs, Integer countOfMatrix) throws IOException {
        ArrayList<Integer[][]> finalArrayList = new ArrayList<>(lengthOfMatrixs);
        Integer colOfbuffMatris;
        Integer[][] bufferMatrix;
        String line;
        for (int i =0;i<=countOfMatrix;i++) {
            bufferMatrix = new Integer[lengthOfMatrixs][lengthOfMatrixs];
            colOfbuffMatris = 0;
        while ((line=readBuff.readLine())!=null) {
            if (line.length() != 0) {
                    for (int j =0;j<lengthOfMatrixs;j++) {
                        bufferMatrix[colOfbuffMatris][j] = Integer.parseInt(line.split(" ")[j]);
                    }
                colOfbuffMatris++;
            }
            else{
                break;
            }
        }
            finalArrayList.add(bufferMatrix);
        }
        finalArrayList.remove(finalArrayList.size()-1);
        return finalArrayList;
    }

    private static Integer[][] MultiplyMatirx(ArrayList<Integer[][]> arrayOfMatrix, Integer lengthOfMatrixs,Integer countOfMatrix){
        Integer indexOfMatrix=0;
        Integer sum;
        Integer[][] finalMatrix = new Integer[lengthOfMatrixs][lengthOfMatrixs];
        for (int i = 0; i < lengthOfMatrixs; i++) {
            System.arraycopy(arrayOfMatrix.get(0)[i], 0, finalMatrix[i], 0, lengthOfMatrixs);
        }
        for (int arr=1;arr<countOfMatrix;arr++){
            Integer[][] buffMatrix = new Integer[lengthOfMatrixs][lengthOfMatrixs];
            for (int i = 0; i < lengthOfMatrixs; i++) {
                for (int j = 0; j <arrayOfMatrix.get(arr).length ; j++) {
                    sum=0;
                    for (int k = 0; k < lengthOfMatrixs; k++) {
                        sum += finalMatrix[i][k] * arrayOfMatrix.get(arr)[k][j];
                        buffMatrix[i][j] = sum;
                    }
                }
            }
            finalMatrix=buffMatrix;
        }
        return finalMatrix;
    }

    private static Integer[][] CheckConstraitsn(Integer[][] finalMatrix,Integer maxDigits){
        Integer[][] buffMatrix = new Integer[finalMatrix.length][finalMatrix.length];
        for (int i = 0; i < finalMatrix.length; i++) {
            System.arraycopy(finalMatrix[i], 0, buffMatrix[i], 0, finalMatrix.length);
        }
        for (int i=0;i<finalMatrix.length;i++){
            for (int j=0;j<finalMatrix.length;j++){
                if (finalMatrix[i][j]>=maxDigits){
                    buffMatrix[i][j]%=maxDigits;
                }
            }
        }
        return buffMatrix;
    }

}
