package com.digicore.digicore_banking_web_application;

public class PerfectSquareCount {

    public static int countPerfectSquares(int length, int width){
        int sum = 0;

        if(length == 1 && width == 1) return 1;

        while (length != 1 && width !=1){

            sum += length * width;
            length--;
            width--;
        }

        if(width != 1) sum += width;
        else if(length != 1) sum+=length;


        return sum;
    }


    public static void main(String[] args) {

        //::::TEST CASES

        System.out.println(" For Length = 1 and Width = 1: Result is>> "+countPerfectSquares(1,1));
        System.out.println(" For Length = 3 and Width = 3: Result is>> "+countPerfectSquares(3,3));
        System.out.println(" For Length = 4 and Width = 5: Result is>> "+countPerfectSquares(4,5));

    }
}
