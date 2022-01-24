package gosecuri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GoSecuri {

    public static void main(String[] args) throws Exception {
        // generation des fichiers de staff
        var directory = "/D:/var/www/html/";
        var employes = new ArrayList<Staff>();
        
        // récupérer la liste de staff
        try {
            File file = new File(directory + "staff.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            String str;
            while ((str = br.readLine()) != null){
                // str = le nom du staff
                File staffFile = new File(directory + "/staff/" + str + "/" + str + ".txt");
                BufferedReader brStaff = new BufferedReader(new FileReader(staffFile));
                
                var nom = brStaff.readLine();
                var prenom = brStaff.readLine();
                var poste = brStaff.readLine();
                var mdp = brStaff.readLine();
                
                // saut de ligne
                brStaff.readLine();
                List<String> materiels = new ArrayList<>();
                String mat;
                while ((mat = brStaff.readLine()) != null){
                    materiels.add(mat);
                }

                var staff = new Staff(nom, prenom, poste, mdp, materiels);
                System.out.println(staff.toString());
                
                // boucle sur tous les staff pour créer le fichier html dans un dossier associé
                staff.SetPathHtml(directory + "staff/" + str + "/" + str + ".html");
                staff.SetPathImage(directory + "staff/" + str + "/" + str + ".jpg");
                
                // generer les fiches salaries
                HtmlGenerator.generateHtml(staff);
                
                employes.add(staff);
                brStaff.close();
            }
            // generer l'index
            HtmlGenerator.generateIndexHtml(employes);
            
            br.close();
        } catch(Exception e){
            throw e;
        }
        
        
    }
}
