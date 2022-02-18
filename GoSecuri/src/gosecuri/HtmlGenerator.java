package gosecuri;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class HtmlGenerator {
    // partie HTML index
    public static void generateIndexHtml(ArrayList<Staff> employes) throws Exception {
        employes.sort((Staff s1, Staff s2) -> s1.Nom.compareTo(s2.Nom));
        
        File f = new File("/D:/var/www/html/index.html");
        
        try {
            
            BufferedWriter bw = new BufferedWriter (new FileWriter(f));
            bw.write("<html>\n" +
                    "    <h1>GoSecuri</h1>\n" +
                    "\n" +
                    "    <div class=\"liste-staff\">\n" +
                    "        <ul>\n" + generateEmployeListeHtml(employes) +
                    "        </ul>\n" +
                    "    </div>\n" +
                    "\n" +
                    "\n" +
                    "    <style>\n" +
                    "        h1 {\n" +
                    "            font-family: 'Roboto-Medium', sans-serif;\n" +
                    "        }\n" +
                    "\n" +
                    "        a {\n" +
                    "            text-decoration: none;\n" +
                    "            font-family: 'Roboto-Light', sans-serif;\n" +
                    "            color:black;\n" +
                    "        }\n" +
                    "\n" +
                    "        a:hover{\n" +
                    "            color: #659224;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</html>");
            bw.close();
        } catch (Exception e){
            throw e;
        }
    }
    
    
    public static String generateEmployeListeHtml(ArrayList<Staff> employes){
        var str = "";
        for (Staff emp : employes) {
            String path = emp.PathHtml;
            str += "<li><a href=\"" + path + "\">" + emp.Nom + " " + emp.Prenom + "</a></li>";
        }
        return str;
    }
    
    
    
    
    // partie HTML employe
    public static void generateHtml(Staff staff) throws Exception {
        File f = new File(staff.PathHtml);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("<html>\n" +
                    "    <div class='page'>\n" +
                    "        <div class='nom'>\n" + staff.Nom + " " + staff.Prenom + "</div>\n" +
                    "        <div class='image'>\n" +
                    "            <img src='" + staff.PathImage + "'>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    \n" +
                    "    <div class='spacer'></div>\n" +
                    "    \n" +
                    "    <div class='liste-mat'>\n" +
                    "       <ul>\n" +
                                   generateMaterielListe(staff)
                                 +
                    "       <ul>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <style>\n" +
                    "        .page{\n" +
                    "            display: flex;\n" +
                    "            margin: 10px;\n" +
                    "            justify-content: space-between;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .nom{\n" +
                    "            width: 75px;\n" +
                    "            height: 50px;\n" +
                    "            border: solid 1px black;\n" +
                    "            padding: 5px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .spacer {\n" +
                    "        	height: 250px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        img{\n" +
                    "        	width: 200px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .liste-mat{\n" +
                    "            display: flex;\n" +
                    "            justify-content: center;\n" +
                    "        }\n" +
                    "\n" +
                    "        ul {\n" +
                    "            list-style: none; \n" +
                    "            padding: 0;\n" +
                    "            margin: 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        li {\n" +
                    "            text-align: right;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</html>");
            bw.close();
        } catch(Exception e){
            throw e;
        }
    }
    
    public static String generateMaterielListe(Staff staff){
        var str = "";
        for (String mat : staff.Materiel) {
            str += "<li>"+ mat + "<input type=\"checkbox\" checked disabled></li>";
        }
        return str;
    }
}
