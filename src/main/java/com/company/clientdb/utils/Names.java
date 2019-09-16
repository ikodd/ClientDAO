package com.company.clientdb.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Names {
    protected static final String directory = "/src/main/java/com/company/clientdb/utils/";
    protected static final File femaleNames = new File(System.getProperty("user.dir") + directory + "femaleFNames");
    protected static  final File maleNames = new File(System.getProperty("user.dir") + directory + "maleFNames");
    protected static final File lNames = new File(System.getProperty("user.dir") + directory + "lastNames");

    public Names() {
    }

    public static List<List<String>> getNames(){
    List<File> files = Arrays.asList(femaleNames,maleNames,lNames);
    List<List<String>> nameList = new ArrayList<>();
    for(File f : files){
        try(BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = null;
            List<String> al = new ArrayList<>();
            for(;(line = br.readLine()) != null;){
                al.add(line);
            }
            nameList.add(al);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return nameList;
}

}
