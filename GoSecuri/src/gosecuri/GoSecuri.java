package gosecuri;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class GoSecuri {

    public static void main(String[] args) throws Exception {
        var directory = "D:\\Documents\\EPSI\\B3\\MSPRs\\Concevoir_et_tester_des_solutions_applicatives\\RepoGit\\";
        var employes = new ArrayList<Staff>();
        
        // récupérer la liste de staff
        try {
            File file = new File(directory + "staff.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            String str;
            while ((str = br.readLine()) != null){
                // str = le nom du staff
                File staffFile = new File(directory + "\\staff\\" + str + "\\" + str + ".txt");
                BufferedReader brStaff = new BufferedReader(new FileReader(staffFile));
                
                var nom = brStaff.readLine();
                var prenom = brStaff.readLine();
                var poste = brStaff.readLine();
                var mdp = brStaff.readLine();
                
                List<String> materiels = new ArrayList<>();
                String mat;
                while ((mat = brStaff.readLine()) != null){
                    materiels.add(mat);
                }

                var staff = new Staff(nom, prenom, poste, mdp, materiels);
                System.out.println(staff.toString());
                employes.add(staff);
                brStaff.close();
            }
            br.close();
        } catch(Exception e){
            throw e;
        }
        
        // récupérer la liste de matériel
        /// on va dans le dossier staff
        
        
        
        
        // boucle sur tous les staff pour créer le fichier txt dans un dossier associé
        
        
        
        
        
        
        
        
        
        
        
        
        // scratch creation fichier
        /* File f = new File("source.html");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("<html><body><h1>Blah, Blah!</h1>");
            bw.write("<textarea cols=75 rows=10>");
            for (int ii=0; ii<20; ii++) {
                bw.write("Blah blah..");
            }
            bw.write("</textarea>");
            bw.write("</body></html>");
            bw.close();
        } catch(Exception e){
            throw e;
        }*/
    }
}
