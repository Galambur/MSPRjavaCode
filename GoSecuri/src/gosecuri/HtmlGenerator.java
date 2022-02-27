package gosecuri;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class HtmlGenerator {

    // partie HTML index
    public static void generateIndexHtml(ArrayList<Staff> employes) throws Exception {
        employes.sort((Staff s1, Staff s2) -> s1.Nom.compareTo(s2.Nom));

        File f = new File("/var/www/html/index.html");

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("<html>\n"
                    + getHeader()
                    + "<h1>GoSecuri</h1>\n"
                    + "<h2>Liste des employes</h2>"
                    + "\n"
                    + "    <div class=\"liste-staff\">\n"
                    + "        <ul>\n" + generateEmployeListeHtml(employes)
                    + "        </ul>\n"
                    + "    </div>\n"
                    + "\n"
                    + "\n"
                    + "</html>");
            bw.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public static String generateEmployeListeHtml(ArrayList<Staff> employes) {
        var str = "";
        for (Staff emp : employes) {
            str += "<li><a href=\"" + emp.PathHtml + "\">" + emp.Nom + " " + emp.Prenom + "</a></li>";
        }
        return str;
    }

    public static String getHeader() {
        return "<header>"
                + "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n"
                + "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n"
                + "<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300;500&display=swap\" rel=\"stylesheet\"> "
                + "</header>  "
                + "    <style>\n"
                + "        h1 {\n"
                + "             font-family: 'Roboto', sans-serif;"
                + "             color: #379EC1;"
                + "             font-weight:medium;\n"
                + "        }\n"
                + "\n"
                + "        a {\n"
                + "            font-family: 'Roboto', sans-serif;\n"
                + "            text-decoration: none;\n"
                + "            color:black;\n"
                + "            font-weight:lighter;\n"
                + "        }\n"
                + "\n"
                + "        a:hover{\n"
                + "            color: #659224;\n"
                + "            font-weight:light;\n"
                + "        }\n"
                + "        h2 {\n"
                + "             font-family: 'Roboto', sans-serif;\n"
                + "             font-weight:medium;\n"
                + "             color: #379EC1;"
                + "        }\n"
                + "    </style>\n";
    }

    // partie HTML employe
    public static void generateHtml(String directory, Staff staff) throws Exception {
        File f = new File(directory + staff.PathHtml);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("<html>\n"
                    + getHeader()
                    + "    <div class='page'>\n"
                    + "        <div class='nom'>\n" + staff.Nom + " " + staff.Prenom + "</div>\n"
                    + "        <div class='image'>\n"
                    + "            <img src='" + staff.PathImage + "'>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "    \n"
                    + "    <div class='spacer'></div>\n"
                    + "    \n"
                    + "    <div class='liste-mat'>\n"
                    + "       <ul>\n"
                    + generateMaterielListe(staff)
                    + "       <ul>\n"
                    + "    </div>\n"
                    + "\n"
                    + "    <style>\n"
                    + "        .page{\n"
                    + "            display: flex;\n"
                    + "            margin: 10px;\n"
                    + "            justify-content: space-between;\n"
                    + "        }\n"
                    + "        \n"
                    + "        .nom{\n"
                    + "            font-family: 'Roboto', sans-serif;\n"
                    + "            font-weight:medium;\n"
                    + "            color: #659224;"
                    + "            width: 75px;\n"
                    + "            height: 50px;\n"
                    + "            border: solid 1px #379EC1;\n"
                    + "            padding: 5px;\n"
                    + "        }\n"
                    + "        \n"
                    + "        .spacer {\n"
                    + "        	height: 250px;\n"
                    + "        }\n"
                    + "        \n"
                    + "        img{\n"
                    + "        	width: 200px;\n"
                    + "        }\n"
                    + "        \n"
                    + "        .liste-mat{\n"
                    + "            display: flex;\n"
                    + "            justify-content: center;\n"
                    + "        }\n"
                    + "\n"
                    + "        ul {\n"
                    + "            list-style: none; \n"
                    + "            padding: 0;\n"
                    + "            margin: 0;\n"
                    + "        }\n"
                    + "\n"
                    + "        li {\n"
                    + "            text-align: right;\n"
                    + "            font-family: 'Roboto', sans-serif;\n"
                    + "            font-weight: lighter;\n"
                    + "        }\n"
                    + "    </style>\n"
                    + "</html>");
            bw.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public static String generateMaterielListe(Staff staff) {
        var str = "";
        for (String mat : staff.Materiel) {
            str += "<li>" + mat + "<input type=\"checkbox\" checked disabled></li>";
        }
        return str;
    }
}
