package com.company.clientdb.dao;

import com.company.clientdb.client.*;
import java.util.List;

public interface ClientDAO {
    public void init();
    public void add(Client c) throws IllegalAccessException;
    public boolean delete(Long id);
    public List<Client> getAll();
    public Client searchById(Long id);

}
