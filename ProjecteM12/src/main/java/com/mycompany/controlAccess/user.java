/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlAccess;

import static com.mycompany.projectem12.App.connection;
import static com.mycompany.projectem12.App.usuari;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static java.sql.JDBCType.NULL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;

/**
 *
 * @author Maiol
 */
public class user {
    //Atributs
    private String dni;
    private String nom;
    private String nomUsuari;
    private String cognom;
    private String contrasenya;
    private String nacionalitat;
    private String telefon;
    private String email;
    private boolean validat;
    private boolean admin;
    private boolean logged;
    //Constructor
    public user() {
        this.logged = false;
    }

    public user(String dni, String nom, String nomUsuari, String cognom, String contrasenya, String nacionalitat, String telefon, String email) {
        this.dni = dni;
        this.nom = nom;
        this.nomUsuari = nomUsuari;
        this.cognom = cognom;
        this.contrasenya = contrasenya;
        this.nacionalitat = nacionalitat;
        this.telefon = telefon;
        this.email = email;
    }
    
    
    //Set i Get

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }
    public boolean getLogged() {
        return logged;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getNacionalitat() {
        return nacionalitat;
    }

    public void setNacionalitat(String nacionalitat) {
        this.nacionalitat = nacionalitat;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public boolean isValidat() {
        return validat;
    }

    public void setValidat(boolean validat) {
        this.validat = validat;
    }
    
    
    //Metodes
    //logejar usuari
    public void login(String nomUsuari, String contrasenya) throws UnsupportedEncodingException{
        String contrasenyaEncryptada=encriptarMD5(contrasenya);
        Statement stmt = connection.getStmt();
        //ResultSet rs = stmt.executeQuery ("select * from empleats where nomUsuari='"+nomUsuari+"'" and contrasenya="'"+contrasenya"'");
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM `empleats` WHERE `nomUsuari`='"+nomUsuari+"' and `contrasenya`='"+contrasenyaEncryptada+"'");
        } catch (SQLException ex) {
            System.out.println("Error al buscar usuari: "+ex.toString());
        }
        try {
            if(rs.next() == false){
                System.out.println("usuari no existenix");
            }else{
                System.out.println(rs.getString("validat"));
                if(rs.getString("validat")!=null){
                    System.out.println("usuari existenix");
                    
                    setDni(rs.getString("dni"));
                    setNom(rs.getString("nom"));
                    setCognom(rs.getString("cognoms"));
                    setNomUsuari(rs.getString(nomUsuari));
                    setContrasenya(contrasenyaEncryptada);
                    setNacionalitat(rs.getString("nacionalitat"));
                    setTelefon(rs.getString("telefon"));
                    setEmail(rs.getString("email"));
                    if(rs.getString("admin")!=null){
                        setAdmin(true);
                    }
                    else{
                        setAdmin(false);
                    }
                    setLogged(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Crear nou usuari
    public boolean crearUsuari(){
        Statement stmt = connection.getStmt();
        ResultSet rs = null;
        boolean funciona=false;
        try {
            rs = stmt.executeQuery("SELECT * FROM `empleats` WHERE `dni`='"+getDni()+"' or `nomUsuari`='"+getNomUsuari()+"' or `email`='"+getEmail()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(rs.next() == false){
                funciona=true;
                stmt.executeUpdate("INSERT INTO `empleats` (`dni`,`nom`,`cognoms`,`nomUsuari`,`contrasenya`,`nacionalitat`,`telefon`,`email`,`admin`,`validat`) VALUES ('"+getDni()+"','"+getNom()+"','"+getCognom()+"','"+getNomUsuari()+"','"+encriptarMD5(getContrasenya())+"','"+getNacionalitat()+"','"+getTelefon()+"','"+getEmail()+"','"+NULL+"','"+NULL+"')");
            }
        } catch (SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funciona;
    }
    //tancar sessio
    public void tancarSessio(){
        usuari.setAdmin(false);
        usuari.setCognom(null);
        usuari.setContrasenya(null);
        usuari.setDni(null);
        usuari.setEmail(null);
        usuari.setLogged(false);
        usuari.setNacionalitat(null);
        usuari.setNom(null);
        usuari.setNomUsuari(null);
        usuari.setTelefon(null);
        usuari.setValidat(false);
    }
    
    //Encriptar contrasenya per fer la comprovacio del login
    private String encriptarMD5(String contrasenya){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(contrasenya.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String contrasenyaEncryptada = number.toString(16);

            while (contrasenyaEncryptada.length() < 32) {
            contrasenyaEncryptada = "0" + contrasenyaEncryptada;
        }
        return contrasenyaEncryptada;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
}
    @Override
    public String toString() {
        return "user{" + "dni=" + dni + ", nom=" + nom + ", cognom=" + cognom + ", contrasenya=" + contrasenya + ", nacionalitat=" + nacionalitat + ", telefon=" + telefon + ", email=" + email + ", admin=" + admin + '}';
    }

    public void login(TextField usuariLogin, TextField contrasenyaLogin, Statement stmt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
