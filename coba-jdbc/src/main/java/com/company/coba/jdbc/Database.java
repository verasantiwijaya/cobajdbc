/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.coba.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Database {
    protected Connection connection = null;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    
    public Database (){
        try {
            String url = "jdbc:mysql://localhost/pbo_perpustakaan";
            String username = "root";
            String password = "";
            
            this.connection = DriverManager.getConnection(url, username,password);
            this.statement = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            System.out.println("Mysql succesfully connected");
            
            //tambah data
//            int result = this.statement.executeUpdate(
//               "INSERT INTO member VALUES (null, 'Farhan', 'farhanmail.com', '090909')"
//            );
//            if (result > 0) {
//                System.out.println("Berhasil menambhkan member");
//            }


            //read data
            this.resultSet = this.statement.executeQuery("SELECT * FROM member");
            this.resultSet.absolute(1);
            String nama = this.resultSet.getString("nama");
            System.out.println("Nama:" + nama);
            
            //hapus data
            this.preparedStatement = this.connection.prepareStatement("DELETE FROM member WHERE id=?" );
            this.preparedStatement.setInt(1,5);
            int result = this.preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Berhasil Dihapus");
            }else{
                System.out.println("Tidak berhasil dihapus");
            }
            this.closeConnection();
            
            
            
        } catch (SQLException ex) {
            System.out.println("SQLException: "+ ex.getMessage());
        }    
                    
    }
    
   //close datbase 
    protected final void closeConnection(){
        try {
            if (this.resultSet != null) this.resultSet.close();
            if (this.statement != null) this.statement.close();
            if (this.preparedStatement != null) this.preparedStatement.close();
            if (this.connection != null) this.connection.close();
            
            this.resultSet = null;
            this.statement = null;
            this.preparedStatement = null;
            this.connection = null;
        } catch (SQLException ex) {}
    }
}
