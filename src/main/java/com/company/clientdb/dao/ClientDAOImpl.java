package com.company.clientdb.dao;

import com.company.clientdb.client.*;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private final Connection conn;
    private String table;

    public ClientDAOImpl(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void init(){
    try(Statement st = conn.createStatement()) {
        st.execute("DROP TABLE IF EXISTS " + table);
        String sql = "CREATE TABLE " + table + " (id BIGINT SIGNED NOT NULL AUTO_INCREMENT  PRIMARY KEY UNIQUE, name VARCHAR(100) NOT NULL, phone BIGINT(12)) ENGINE InnoDB CHARACTER SET utf8";
        st.execute(sql);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
    public void add(Client c){
        Class cls = c.getClass();
        Field[] fields = cls.getDeclaredFields();
        Field id = null;
        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for(Field f : fields){
            if(f.isAnnotationPresent(Id.class)){
                id = f;
                continue;
            }
                   names.append(f.getName()).append(",");
                   f.setAccessible(true);
            try {
                values.append("\"").append(f.get(c)).append("\",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        names.deleteCharAt(names.length() - 1);
        values.deleteCharAt(values.length() - 1);
        String sql = "INSERT INTO " + table +
                " (" + names.toString() + ") " +
                "VALUES (" + values.toString() +
                ")";
try(Statement st = conn.createStatement()){
    st.execute(sql);
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    @Override
    public boolean delete(Long id) {
        if(searchById(id) == null)return false;
       try(Statement st = conn.createStatement()) {
st.execute("DELETE FROM " + table + " WHERE id=\"" + id + "\"");
return true;
       } catch (SQLException e) {
           e.printStackTrace();
       }
        return false;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try(Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery("SELECT * FROM " + table);

            for(;rs.next();){
                ResultSetMetaData md = rs.getMetaData();
                Long id = null;
                String name = null;
                long phone = 0;
                for(int i = 1; i <= md.getColumnCount(); i++){
                    String columnN = md.getColumnName(i);
switch (columnN){
    case "id":
        id =  (Long)rs.getObject(columnN);
        break;
    case "name":
        name = (String)rs.getObject(columnN);
        break;
    case "phone":
        phone = (long)rs.getObject(columnN);
        break;
    default:
        break;
}
                }
                clients.add(new Client(id,name,phone));
            }
          return clients;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Client searchById(Long id){
        try(Statement st = conn.createStatement()){
           ResultSet rs = st.executeQuery("SELECT * FROM " + table + " WHERE id = \"" + id +"\"");
                for(;rs.next();){
                    ResultSetMetaData md = rs.getMetaData();
                    String name = null;
                    long phone = 0;
                    for(int i = 1; i <= md.getColumnCount(); i++){
                        String columnN = md.getColumnName(i);
                        switch (columnN){
                            case "name":
                                name = (String)rs.getObject(columnN);
                                break;
                            case "phone":
                                phone = (long)rs.getObject(columnN);
                                break;
                            default:
                                break;
                        }
                    }
                    Client cli = new Client(id,name,phone);
                    return cli;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
