package com.company.clientdb.utils;

import com.company.clientdb.client.*;
import com.company.clientdb.connection.ConnectionFactory;
import com.company.clientdb.dao.*;
import com.company.clientdb.servlets.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "password";
    private static ClientDAO dao;
    private static Connection conn;

    public static void main(String[] args){
        conn =  (new ConnectionFactory(DB_CONNECTION,DB_USER,DB_PASSWORD)).getConnection();
        dao = new ClientDAOImpl(conn,"Clients");
        dao.init();
        List<String> names = new ArrayList<>();
        List<Long> phones = new ArrayList<>();
        names = Randomizer.randName(10,Names.getNames());
        phones = Randomizer.randPhoneNum("380",9,10);
        System.out.println(names);
        System.out.println(phones);
        int i = 0;
        for(String name : names){
            try{
                dao.add(new Client(name,phones.get(i)));
                i++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
