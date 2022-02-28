package gosecuri;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GoSecuriTest {
    
    public GoSecuriTest() {
    }
    
    @Test
    public void SetPathHtml() {
        var staff = new Staff("pnom", "nom", "prenom", "poste", "mdp", new ArrayList<String>());
        staff.SetPathHtml("path");
        
        var expectedResult = "path";

        assertEquals(expectedResult, staff.PathHtml);
    }
    
    @Test
    public void SetPathImage() {
        var staff = new Staff("pnom", "nom", "prenom", "poste", "mdp", new ArrayList<String>());
        staff.SetPathImage("path");
        
        var expectedResult = "path";

        assertEquals(expectedResult, staff.PathImage);
    }
    
    @Test
    public void staffToString(){
        List<String> materiels = new ArrayList<String>();
        materiels.add("mat1");
        materiels.add("mat2");

        var staff = new Staff("pnom", "nom", "prenom", "poste", "mdp", materiels);
        var result = staff.toString();
        
        var expectedResult = "Staff{" + "Nom=nom, Prenom=prenom, Poste=poste, Mdp=mdp, Materiel=" + materiels + "}";

        assertEquals(expectedResult, result);
    }
}
