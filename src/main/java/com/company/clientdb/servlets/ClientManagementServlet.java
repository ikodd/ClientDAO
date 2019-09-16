package com.company.clientdb.servlets;

import com.company.clientdb.client.*;
import com.company.clientdb.connection.ConnectionFactory;
import com.company.clientdb.dao.ClientDAO;
import com.company.clientdb.dao.ClientDAOImpl;
import com.company.clientdb.utils.Main;
import com.company.clientdb.utils.Names;
import com.company.clientdb.utils.Randomizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.LocalTime.*;

@WebServlet(name = "ClientManagement")
public class ClientManagementServlet extends HttpServlet {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "password";
    private static ClientDAO dao;
    private static Connection conn;

    @Override
    public void init() throws ServletException {
            super.init();
            conn =  (new ConnectionFactory(DB_CONNECTION,DB_USER,DB_PASSWORD)).getConnection();
            dao = new ClientDAOImpl(conn,"Clients");
    }

    /*Invoked from list.jsp (form with radio boxes) and add.jsp (text field form)*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String command = request.getParameter("command");
String fName = request.getParameter("fName");
long phone = 0;
StringBuilder sb = new StringBuilder();
if(request.getParameter("phone") != null)phone = Long.parseLong(request.getParameter("phone"));
Long id = null;
Boolean success = false;
RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
if(request.getParameter("id") != null)id = Long.parseLong(request.getParameter("id"));
        System.out.println(now() + " command:" +  command);
switch (command){
    case "add":
        if("".equals(fName) || fName == null || phone == 0){
            request.setAttribute("command",command);
            request.setAttribute("success",success);
            request.setAttribute("reason","One of the input fields is empty");
            rd.forward(request,response);
    }else{
            Client c = new Client(fName,phone);
            try {
                dao.add(c);
                success = true;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            request.setAttribute("command",command);
            request.setAttribute("success",success);
            request.setAttribute("client",c);
            rd.forward(request,response);
    }
        break;
    default:
            Pattern pat = Pattern.compile("^(delete)([0-9]{1,20})$");
            Matcher mat = pat.matcher(command);
            if(mat.find()){
                Client c1 = (Client)dao.searchById(Long.parseLong(mat.group(2)));
                request.setAttribute("client",c1);
                success = (Boolean)dao.delete(Long.parseLong(mat.group(2)));
                request.setAttribute("command",mat.group(1));
                request.setAttribute("success", success);
                rd.forward(request,response);
            }else{
                request.setAttribute("command",mat.group(1));
                Client c = (Client)dao.searchById(Long.parseLong(mat.group(2)));
                request.setAttribute("success", success);
                request.setAttribute("reason","Wrong command!");
                rd.forward(request,response);
            }
        break;
}
    }

   /*Invoked from index.jsp by "Client list" ("get_all" command) and "Delete" ("list_selection" command) links*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        System.out.println(now() + " command:" +  command);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/list.jsp");
        switch (command){
            case "get_all":
                List<Client> clients = dao.getAll();
                request.setAttribute("clients",clients);
                rd.forward(request,response);
                break;
            case "list_selection":
                clients = dao.getAll();
                request.setAttribute("isDelete",true);
                request.setAttribute("clients",clients);
                rd.forward(request,response);
                break;
            default:
                response.sendRedirect("/index.jsp");
                break;
        }
    }
}
