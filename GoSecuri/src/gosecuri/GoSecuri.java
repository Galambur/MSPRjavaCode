package gosecuri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoSecuri {
    
    public static ArrayList<Thread> threads = new ArrayList<Thread>();    
    public static ArrayList<Staff> employes = new ArrayList<Staff>();
    public static String directory = "/D:/var/www/html/";


    public static void main(String[] args) throws Exception {
        // generation des fichiers de staff
        try {
            // récupérer la liste de staff
            getInformations();

            // todo : attendre que tous les threads aient fini pour générer la liste
            var threadsFinished = false;
            while(!threadsFinished){
                for( Thread t : threads){
                    if(t.isAlive()){
                        continue;
                    }
                }
                threadsFinished = true;
            }
            
            getListeEmployes();
            HtmlGenerator.generateIndexHtml(employes);
            
            // génération des fiches employé
        } catch (Exception e) {
            throw e;
        }

    }
    
    public static void getInformations() throws Exception {
        File file = new File(directory + "staff.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        var str = "";
        while ((str = br.readLine()) != null) {
            // todo : regarder si on peut pas trouver mieux
            var strTempo = str;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        var staff = generationFicheEmploye(strTempo);
                        
                        System.out.println(staff.toString());
                        
                        // boucle sur tous les staff pour créer le fichier html dans un dossier associé
                        staff.SetPathHtml(directory + "staff/" + strTempo + "/" + strTempo + ".html");
                        staff.SetPathImage(directory + "staff/" + strTempo + "/" + strTempo + ".jpg");
                        
                        // generer les fiches salaries
                        HtmlGenerator.generateHtml(staff);
                    } catch (Exception ex) {
                        Logger.getLogger(GoSecuri.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

            threads.add(thread);
            thread.start();
        }

        br.close();
    }

    public static Staff generationFicheEmploye(String str) throws Exception {
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
        while ((mat = brStaff.readLine()) != null) {
            materiels.add(mat);
        }

        var staff = new Staff(nom, prenom, poste, mdp, materiels);

        brStaff.close();

        return staff;
    }

    private static void getListeEmployes() throws Exception{
        var str = "";
        File file = new File(directory + "staff.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        while ((str = br.readLine()) != null) {
            
            
            var staff = generationFicheEmploye(str);
            
            staff.SetPathHtml(directory + "staff/" + str + "/" + str + ".html");
            staff.SetPathImage(directory + "staff/" + str + "/" + str + ".jpg");
            
            employes.add(staff);
        }
    }
}
