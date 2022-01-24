package gosecuri;

import java.util.List;

public class Staff {
    public String Nom;
    public String Prenom;
    public String Poste;
    public String Mdp;
    public List<String> Materiel;
    public String PathHtml;
    public String PathImage;

    public Staff(String nom, String prenom, String poste, String mdp, List<String> materiels) {
        this.Nom = nom;
        this.Prenom = prenom;
        this.Poste = poste;
        this.Mdp = mdp;
        this.Materiel = materiels;
    }

    @Override
    public String toString() {
        return "Staff{" + "Nom=" + Nom + ", Prenom=" + Prenom + ", Poste=" + Poste + ", Mdp=" + Mdp + ", Materiel=" + Materiel + '}';
    }
    
    public void SetPathHtml(String path) {
        this.PathHtml = path;
    }
    
    public void SetPathImage(String path){
        this.PathImage = path;
    }
}
