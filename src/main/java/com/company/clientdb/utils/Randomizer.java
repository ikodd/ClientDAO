package com.company.clientdb.utils;

import java.util.ArrayList;
import java.util.List;

public class Randomizer {

    /*Random name format: "FirsName LastName"
    * Input list format: {female name list},{male name list}, {last name list}
    * */
    public static List<String> randName(int num, List<List<String>> nameList){
        List<String> randNames = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int rnd = 0;
        boolean male = true; // First name switch

        for(int i = 0; i < num; i++){
            int cnt = 1;

            for(List<String> list : nameList){
                if(male & (cnt%2 != 0 & cnt%3 != 0)){ // if male name should be and female name list happens
                cnt++;
                    continue;
                }else if(!male & cnt%2 == 0){
                    cnt++;
                    continue;
                }
                rnd = (int)(Math.random()*(list.size() - 1)); // Random index for name list
                sb.append(list.get(rnd)).append(" ");
                if(cnt%3 == 0)male = !male; // First name, male or female, switch
                cnt++;
                }

            sb.deleteCharAt(sb.length() - 1);
            randNames.add(sb.toString());
            sb.delete(0,sb.length());
            }
            return randNames;
}
public static int[] randAge(int num){
        int[] ages = new int[num];
        int min = 18;
        int max = 90;
     for(int i = 0; i < num; i++){
         ages[i] = (int)(Math.random()*(max - min) + min);
     }
     return ages;
}
public static List<Long> randPhoneNum(String prefix, int callNum, int num){
        List<Long> phNumbers = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String format = "%" + "0" + callNum + "d";
      for(int i = 1; i <= num; i++){
          int rand = (int)(Math.random()*(int)Math.pow(10,callNum));
          sb.append(prefix).append(String.format(format,rand));
          phNumbers.add(Long.parseLong(sb.toString()));
          sb.delete(0,sb.length());
      }
      return phNumbers;
}

}
