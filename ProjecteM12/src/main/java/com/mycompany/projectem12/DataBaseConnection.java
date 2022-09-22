package com.mycompany.projectem12;



import java.sql.*;

public class DataBaseConnection {
    //Atibuts
    private String bd="hotel";
    private String port="3306  ";
    private String user="root";
    private String password="";
    private String ip="localhost";
    private String driver="jdbc:mysql://"
            ;
    private Connection conn = null;
    private Statement stmt = null;
    //metodes
    //Crear conexio
    public void createConnection(){
        try{
            String connectionString = getDriver()+getIp()+":"+getPort()+"/"+getBd();
            setConn(DriverManager.getConnection(connectionString,getUser(),getPassword()));
            System.out.println("Conexio establerta: "+connectionString);
        }
        catch (Exception e){
            System.out.println("Error al conectar a la base de dades: "+e.toString());
        }
        try{
            setStmt(getConn().createStatement()); 
        }
        catch(Exception e){
            System.out.println("Error al Crear la declaracio: "+e.toString());
        }
    }
    //Set And Get

    public String getBd() {
        return bd;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getIp() {
        return ip;
    }

    public String getDriver() {
        return driver;
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }
    
    
}
