
package controleurs;

import IHM.CIHMGestionEntreprises;
import bdd.CTableEntreprises;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import objets.CEntreprise;

public class CControleurGestionEntreprise {
    
    //accès à la table entreprise de la base de donnée
    private final CTableEntreprises tableBDD = new CTableEntreprises();

    //Collection des entreprises
    private final ArrayList<CEntreprise> collectionEntreprises = new CTableEntreprises().listeEntreprise();
    
    //tableau
    private final DefaultTableModel model;
    
    CIHMGestionEntreprises instance;
    
    private final JFrame ihmAjoutEntreprise;
    private final JFrame ihmAfficherEntreprise;
    private JFrame ihmEditerEntreprise;
    
    //Désigne le numéro de ligne sélectionnée
    private int selection;      

    public CControleurGestionEntreprise(CIHMGestionEntreprises instance) {
        this.instance=instance;
        this.model=instance.getModel();
        this.ihmAfficherEntreprise=instance.getIhmAfficherEntreprise();
        this.ihmAjoutEntreprise=instance.getIhmAjoutEntreprise();
        this.ihmEditerEntreprise=instance.getIhmEditerEntreprise();
        
    }

            
            /**************************************************
           *         Bouton ouvrir fenêtre ajouter           *
          **************************************************/ 
           public class OuvrirFenetreAjouter implements ActionListener{
               public void actionPerformed(ActionEvent arg0) {
                   
                   //On vide les champs de leurs contenu
                   instance.getNomR().setText("");
                   instance.getAdNumR().setText("");
                   instance.getAdRueR().setText("");
                   instance.getAdVilleR().setText("");
                   instance.getAdCPR().setText("");
                   instance.getTelR().setText("");
                   instance.getMailR().setText("");
                   instance.getSiretR().setText("");
                   instance.getApeR().setText("");
                  
                   ihmAjoutEntreprise.setVisible(true);
               }
           }
           
         
            /**************************************************
           *     Action après validation ajout entreprise    *
          **************************************************/

           public class ValiderAjoutEntreprise implements ActionListener{
               public void actionPerformed(ActionEvent arg0) {

                   //Enregistrement des données récupérées dans un tableau 
                      ArrayList<String> donneeNouvelleEntreprise = new ArrayList();
                      donneeNouvelleEntreprise.add(instance.getNomR().getText());
                      donneeNouvelleEntreprise.add(instance.getAdNumR().getText());
                      donneeNouvelleEntreprise.add(instance.getAdRueR().getText());
                      donneeNouvelleEntreprise.add(instance.getAdVilleR().getText());
                      donneeNouvelleEntreprise.add(instance.getAdCPR().getText());
                      donneeNouvelleEntreprise.add(instance.getTelR().getText());
                      donneeNouvelleEntreprise.add(instance.getMailR().getText());
                      donneeNouvelleEntreprise.add(instance.getSiretR().getText());
                      donneeNouvelleEntreprise.add(instance.getApeR().getText()); 

                  //Envoie du tableau pour enregistrement dans la BDD  
                     tableBDD.ajoutEntreprise(donneeNouvelleEntreprise);     
                      
                  //Envoie du tableau pour ajouter à la collection 
                      int lastId = collectionEntreprises.get(collectionEntreprises.size()-1).getId();
                      collectionEntreprises.add(new CEntreprise(lastId+1,donneeNouvelleEntreprise));

                  //mise a jour du tableau  
                      model.addRow(collectionEntreprises.get(collectionEntreprises.size()-1).getDonneesTab()); 
                        
                  //affiche message pour confirmer l'ajout de l'entreprise   
                     instance.getMessage().setText("L'entreperise "+ instance.getNomR().getText() + " à été ajouté");

                  //on referme la fenêtre ajout entreprise   
                     instance.getIhmAjoutEntreprise().dispose();
               }
            }
    
            /**************************************************
           *                Bouton afficher                  *
          **************************************************/ 
           public class OuvrirFenetreAfficher implements ActionListener{
               public void actionPerformed(ActionEvent e) {
                   
                   //on indique la ligne sélectionnée 
                   selection = instance.getTableauEntreprise().getSelectedRow();
                   
                   
                   //message pour indiquer l'entreprise selectionnée
                   instance.getMessage().setText("L'entreprise "+collectionEntreprises.get(selection).getNom()+" est sélectionnée" );                   
                   
                   //on ouvre la fenêtre
                   
                   ihmAfficherEntreprise.setVisible(true);
              }
           }
           
            /**************************************************
           *                  Bouton éditer                  *
          **************************************************/ 
          public  class EditerEntreprise implements ActionListener{
               public void actionPerformed(ActionEvent e) { 

                   //Message pour indiquer l'entreprise en cours d'édition
                   instance.getMessage().setText("Edition de l'entreprise "+collectionEntreprises.get(selection).getNom() );                   
                   
                   //On remplie le formulaire avec les données actuelles de l'entreprise 
                   instance.getNomR().setText(collectionEntreprises.get(selection).getNom());
                   instance.getAdNumR().setText(collectionEntreprises.get(selection).getAdNum());
                   instance.getAdRueR().setText(collectionEntreprises.get(selection).getAdRue());
                   instance.getAdVilleR().setText(collectionEntreprises.get(selection).getAdVille());
                   instance.getAdCPR().setText(collectionEntreprises.get(selection).getAdCP());
                   instance.getTelR().setText(collectionEntreprises.get(selection).getTel());
                   instance.getMailR().setText(collectionEntreprises.get(selection).getMail());
                   instance.getSiretR().setText(collectionEntreprises.get(selection).getSiret());
                   instance.getApeR().setText(collectionEntreprises.get(selection).getApe());
                   
                   //On affiche la fenêtre 
                   ihmEditerEntreprise.setVisible(true);
              }
           }
 
           /********************************************************
          *    Action après validation modification entreprise    *
         ********************************************************/

          public  class ModifierEntreprise implements ActionListener{
               public void actionPerformed(ActionEvent arg0) {                 
                    
                   //Enregistrement des données récupérées dans un tableau
                      ArrayList<String> donneeModif = new ArrayList();
                      donneeModif.add(instance.getNomR().getText());
                      donneeModif.add(instance.getAdNumR().getText());
                      donneeModif.add(instance.getAdRueR().getText());
                      donneeModif.add(instance.getAdVilleR().getText());
                      donneeModif.add(instance.getAdCPR().getText());
                      donneeModif.add(instance.getTelR().getText());
                      donneeModif.add(instance.getMailR().getText());
                      donneeModif.add(instance.getSiretR().getText());
                      donneeModif.add(instance.getApeR().getText());
                    
                    //Modification dans la base de donnée
                    tableBDD.modifEntreprise(donneeModif, instance.getCollectionEntreprises().get(selection).getId());
                    
                   //Modification dans la collection 
                     int id = collectionEntreprises.get(selection).getId();
                     collectionEntreprises.set(selection, new CEntreprise(id,donneeModif));
           
                  //mise a jour du tableau 
                     model.setValueAt(collectionEntreprises.get(selection).getNom(), selection, 0);
                     model.setValueAt(collectionEntreprises.get(selection).getAdVille(), selection, 1);
                     model.setValueAt(collectionEntreprises.get(selection).getAdCP(), selection, 2);
                     model.setValueAt(collectionEntreprises.get(selection).getTel(), selection, 3);
                               
                  //affiche message pour confirmer mofification de l'entreprise   
                     instance.getMessage().setText("L'entreperise "+ instance.getCollectionEntreprises().get(selection).getNom() + " à été modifiée");
                     
                  //on referme la fenêtre ajout entreprise   
                  ihmEditerEntreprise.dispose();
                  ihmAfficherEntreprise.dispose();
   
               }
            }            

           
            /**************************************************
           *               Bouton supprimer                  *
          **************************************************/ 
          public class SuppEntreprise implements ActionListener{
               public void actionPerformed(ActionEvent e) {       
          
                   instance.getIhmSuppEntreprise().setVisible(true);
              }
           } 
           
           
            /**************************************************
           *         Bouton valider suppression              *
          **************************************************/ 
          public  class ConfSuppEntreprise implements ActionListener{
               public void actionPerformed(ActionEvent e) { 

                  //Suppression dans la BDD  
                     tableBDD.suppEntreprise(collectionEntreprises.get(selection).getId());  
                     
                  //Suppression dans la collection
                     String nomEntrepriseSupp = collectionEntreprises.get(selection).getNom();
                     collectionEntreprises.remove(selection);
                     
                  //mise a jour du tableau 
                     model.removeRow(selection);   
                        
                  //affiche message pour confirmer suppression de l'entreprise   
                     instance.getMessage().setText("L'entreprise "+nomEntrepriseSupp+" à été supprimée");
                     
                  //on referme la fenêtre supp entreprise   
                     ihmAfficherEntreprise.dispose();
                     instance.getIhmSuppEntreprise().dispose();
 
              }
           }  
           
 
            /**************************************************
           *             Bouton fermer affichage             *
          **************************************************/ 
           public class FermerEntreprise implements ActionListener{
               public void actionPerformed(ActionEvent e) { 
               
                  //message vide   
                     instance.getMessage().setText("");
                     
                  //on referme la fenêtre affichage   
                     ihmAfficherEntreprise.dispose();
              }
           } 
}
