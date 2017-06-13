
package bdd;
import objets.CEntreprise;
import java.sql.*;
import java.util.ArrayList;

public class CTableEntreprises {
    
    CBdd bdd = new CBdd();
    Connection conn = bdd.getCon();
    PreparedStatement pstm;
    Statement stm;
    ResultSet result;

    public CTableEntreprises()  {
        try {
        this.stm = conn.createStatement();
        }catch (Exception e) {
            System.err.println(e);
        }
    }    

     /***************************************************************
    *         Récupérer toutes les données de la table             *
   ***************************************************************/
    
    public ArrayList<CEntreprise> listeEntreprise() {
        
        ArrayList<CEntreprise> listeEntreprises = new ArrayList(); 

        try {
            
            ResultSet result = stm.executeQuery("SELECT * FROM entreprise");

            while(result.next()){  
                //conntruction d'une instance entreprise
                CEntreprise entreprise = new CEntreprise(result.getInt("id"),result.getString("nom"), result.getString("adNum"), result.getString("adRue"), result.getString("adVille"), result.getString("adCP"), result.getString("tel"), result.getString("mail"), result.getString("siret"), result.getString("ape") );
                //ajout de l'instance dans la liste d'entreprises
                listeEntreprises.add(entreprise);
           }
            
        } catch (Exception e) {
            System.err.println(e);
        }
        
          return listeEntreprises;
    }
    
     /***************************************************************
    *               Ajouter une nouvelle ligne                     *
   ***************************************************************/
    
    public void ajoutEntreprise(ArrayList<String> donneesNouvelleEntreprise){
           
        try {
     
            String query = " insert into entreprise (nom,adnum,adrue,adville,adcp,tel,mail,siret,ape)"
              + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstm = conn.prepareStatement(query);
            
            int i =0;
            for (String liste : donneesNouvelleEntreprise){
                pstm.setString (i+1, donneesNouvelleEntreprise.get(i));
                i++;
            }
           
            pstm.execute();     
        } catch (Exception e) {
            System.err.println(e);
        }
 
    }

     /***************************************************************
    *                      Modifier une ligne                      *
   ***************************************************************/
    
    public void modifEntreprise(ArrayList<String> donnee, int id){
           
        try {

           String query = "update entreprise set nom = ?, adnum = ?, adrue = ?, adville = ?, adcp = ?, tel = ?, mail = ?, siret = ?, ape = ? where id = ?";

            // create the mysql insert preparedstatement
            pstm = conn.prepareStatement(query);
            pstm.setString(1, donnee.get(0));
            pstm.setString(2, donnee.get(1));
            pstm.setString(3, donnee.get(2));
            pstm.setString(4, donnee.get(3));
            pstm.setString(5, donnee.get(4));
            pstm.setString(6, donnee.get(5));
            pstm.setString(7, donnee.get(6));
            pstm.setString(8, donnee.get(7));
            pstm.setString(9, donnee.get(8));
            pstm.setInt(10, id);
            
            pstm.executeUpdate();
            
        } catch (Exception e) {
            System.err.println(e);
        }
 
    }    

     /***************************************************************
    *                      Supprimer une ligne                     *
   ***************************************************************/
    
    public void suppEntreprise(int id){
           
        try {
            String query = " delete from entreprise where id = ?";
              
            pstm = conn.prepareStatement(query);
            
            pstm.setInt(1, id);

            pstm.execute();     
        } catch (Exception e) {
            System.err.println(e);
        }
 
    }    
 
}