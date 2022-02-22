package gosecuri;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.MessageDigest;

public class GoSecuri {
    
    public static ArrayList<Thread> threads = new ArrayList<Thread>();    
    public static ArrayList<Staff> employes = new ArrayList<Staff>();
    public static String directory = "/var/www/html/";


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

        
        // generation des mots de passe
        createPasswordFile();
    }
    
    private static void createPasswordFile() throws Exception{
        File f = new File("/etc/apache2/.htpasswd");
        
        BufferedWriter bw = new BufferedWriter (new FileWriter(f));
        for(Staff s : employes){
            bw.write(s.Identifiant + ":" + hashingPassword(s.Identifiant, s.Mdp));
        }
        bw.close();
    }
    
    private static String hashingPassword(String user, String password) throws Exception{
        var realm = "$apr1$";
        byte b[] = java.security.MessageDigest.getInstance("MD5").digest((user + ":" + realm + ":" + password).getBytes());
        java.math.BigInteger bi = new java.math.BigInteger(1, b);
        String s = bi.toString(16);
        while (s.length() < 32)
            s = "0" + s; // La chaîne s contient le mot de passe chiffré
        return realm + s + ".";
    }
    
    private static void getInformations() throws Exception {
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
                        var staff = createStaffFromName(strTempo);
                        
                        // boucle sur tous les staff pour créer le fichier html dans un dossier associé
                        staff.SetPathHtml("/staff/" + strTempo + "/" + strTempo + ".html");
                        staff.SetPathImage("/staff/" + strTempo + "/" + strTempo + ".jpg");
                        
                        // generer les fiches salaries
                        HtmlGenerator.generateHtml(directory, staff);
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

    private static Staff createStaffFromName(String identifiant) throws Exception {
        // str = le nom du staff
        File staffFile = new File(directory + "/staff/" + identifiant + "/" + identifiant + ".txt");
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

        var staff = new Staff(identifiant, nom, prenom, poste, mdp, materiels);

        brStaff.close();
        return staff;
    }

    private static void getListeEmployes() throws Exception{
        var str = "";
        File file = new File(directory + "staff.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        while ((str = br.readLine()) != null) {
            var staff = createStaffFromName(str);
            
            staff.SetPathHtml("/staff/" + str + "/" + str + ".html");
            staff.SetPathImage("/staff/" + str + "/" + str + ".jpg");
            
            employes.add(staff);
        }
    }
}
