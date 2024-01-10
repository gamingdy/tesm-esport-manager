package vue;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSX {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream(new File("chemin/vers/votre/fichier.xlsx"));

            // Créer le classeur de travail à partir du fichier
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Sélectionner la première feuille de calcul
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Créer des listes pour chaque colonne
            List<String> niveaux = new ArrayList<>();
            List<String> datesDebut = new ArrayList<>();
            List<String> datesFin = new ArrayList<>();
            List<String> nomsEquipe = new ArrayList<>();
            List<Integer> worldRankings = new ArrayList<>();
            List<String> paysEquipe = new ArrayList<>();
            List<String> pseudosJoueur = new ArrayList<>();

            // Itérer sur chaque ligne de la feuille de calcul
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Ignorer la première ligne (en-têtes)
                if (row.getRowNum() == 0) continue;

                // Itérer sur chaque cellule de la ligne
                Iterator<Cell> cellIterator = row.iterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    // Ajouter la valeur de la cellule à la liste correspondante
                    switch (cell.getColumnIndex()) {
                        case 0:
                            niveaux.add(cell.getStringCellValue());
                            break;
                        case 1:
                            datesDebut.add(cell.getStringCellValue());
                            break;
                        case 2:
                            datesFin.add(cell.getStringCellValue());
                            break;
                        case 3:
                            nomsEquipe.add(cell.getStringCellValue());
                            break;
                        case 4:
                            worldRankings.add((int) cell.getNumericCellValue());
                            break;
                        case 5:
                            paysEquipe.add(cell.getStringCellValue());
                            break;
                        case 6:
                            pseudosJoueur.add(cell.getStringCellValue());
                            break;
                    }
                }
            }

            // Fermer le fichier
            file.close();

            // Afficher les listes
            System.out.println("Niveaux : " + niveaux);
            System.out.println("Dates de début : " + datesDebut);
            System.out.println("Dates de fin : " + datesFin);
            System.out.println("Noms d'équipe : " + nomsEquipe);
            System.out.println("World Rankings : " + worldRankings);
            System.out.println("Pays d'équipe : " + paysEquipe);
            System.out.println("Pseudos de joueur : " + pseudosJoueur);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}