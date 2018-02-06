package com.round1.task557;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
            ArrayList<Integer[][]> arrayOfMatrixs = MatrixReader(readBuff,lengthOfMatrixs,countOfMatrix);

//            for (Integer[][] buffMatrix:arrayOfMatrixs) {
//                System.out.print("\n");
//                for (int i =0;i<lengthOfMatrixs;i++){
//                    for (int j =0;j<lengthOfMatrixs;j++){
//                        System.out.print(buffMatrix[i][j] + " ");
//                        if (j==lengthOfMatrixs-1) {
//                            System.out.print("\n");
//                        }
//                    }
//                }
//            }
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

}
