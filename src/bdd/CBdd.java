
package bdd;

import java.sql.*;

public class CBdd {
    
   Connection con;
   
    public CBdd(){
    try{
    Class.forName("com.mysql.jdbc.Driver");
    }catch(ClassNotFoundException e){
    System.err.println(e);
//pour afficher l erreur
    }
    try{
    //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gds","root","");
    con=DriverManager.getConnection("jdbc:mysql://mysql-ocami.alwaysdata.net:3306/ocami_gds","ocami","nathalie35");
    }catch(SQLException e){System.err.println(e);}
    }

    public Connection getCon() {
        return con;
    }

}
    


