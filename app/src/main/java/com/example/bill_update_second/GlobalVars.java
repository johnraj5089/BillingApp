package com.example.bill_update_second;


public class GlobalVars {
    public static int[] numbers = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static int[] prices = {10, 35, 45, 45, 40, 15, 10, 12, 15};

    public static String[] items = {"Idly", "Plain Dosa", "Masala Dosa", "Podi Dosa", "Pongal", "Poori", "Vada", "Tea", "Coffee"};

    public static float total = 0.00F;

    public static float cal_tot(){
        for (int i=0; i < numbers.length; i++) {
            total += numbers[i] * prices[i];
        }
        return total;
    }
}

