package com.round1.task557;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
            Integer lengthOfMatrixs = Integer.parseInt(readBuff.readLine().split(" ")[1]);
            String indexOfFinalElement = readBuff.readLine();
            Integer maxDigitsForMatrix = Integer.parseInt(readBuff.readLine());
            ArrayList<Integer[][]> arrayOfMatrixs = MatrixReader(readBuff,lengthOfMatrixs);
            for (Integer[][] buffMatrix:arrayOfMatrixs) {
                System.out.print("\n");
                for (int i =0;i<lengthOfMatrixs;i++){
                    for (int j =0;j<lengthOfMatrixs;j++){
                        System.out.print(buffMatrix[i][j] + " ");
                        if (j==lengthOfMatrixs-1) {
                            System.out.print("\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            assert readBuff != null;
            readBuff.close();
        }
    }

    private static ArrayList<Integer[][]> MatrixReader(BufferedReader readBuff, Integer lengthOfMatrixs) throws IOException {
        ArrayList<Integer[][]> finalArrayList = new ArrayList<>();
        Integer chistiuCode;
        Integer[][] bufferMatrix;
        String line;
        readBuff.readLine();
        for (int i =0;i<=lengthOfMatrixs;i++) {
            bufferMatrix = new Integer[lengthOfMatrixs][lengthOfMatrixs];
            chistiuCode = 0;
        while ((line=readBuff.readLine())!=null) {
            if (line.length() != 0) {
                    for (int j =0;j<lengthOfMatrixs;j++) {
                        bufferMatrix[chistiuCode][j] = Integer.parseInt(line.split(" ")[j]);
                    }
                    chistiuCode++;
            }
            else{
                break;
            }
        }
            finalArrayList.add(bufferMatrix);
        }
        return finalArrayList;
    }

}
