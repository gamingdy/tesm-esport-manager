package vue;

import javax.swing.*;

import modele.Equipe;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;

public class Impression extends JPanel implements Printable {
    
    private List<Equipe> equipes;
    private List<Integer> points;
    private String nomTournoi;
    private List<String> textLines;
    private String date;

    public Impression(List<Equipe> equipes, List<Integer> point, String nomTournoi, String date) {
        this.equipes = equipes;
        this.points = point;
        this.nomTournoi = nomTournoi;
        this.date = date;
        this.createRapport();
    }

    private void createRapport() {
        String titre = "Tournoi : " + this.nomTournoi;
        String sousTitre = "Rapport de classement du " + this.date;
        textLines = new ArrayList<>();
        textLines.add(titre);
        textLines.add(sousTitre);
        textLines.add("\n\n");

        // Table headers
        textLines.add(String.format("%-20s%-10s%-15s", "Equipe", "Points", "Classement"));

        for (int i = 0; i < equipes.size(); i++) {
            textLines.add(String.format("%-20s%-10s%-15s",
                    equipes.get(i).getNom(), String.valueOf(points.get(i)), String.valueOf(i + 1)));
        }
        textLines.add("\n\n");
    }

    public int print( Graphics g, PageFormat pf, int pageIndex){
        Font font = new Font("Serif", Font.PLAIN, 16);
        FontMetrics metrics = g.getFontMetrics(font);
        int lineHeight = metrics.getHeight();
        double pageHeight = pf.getImageableHeight();
        int linesPerPage = ((int)pageHeight)/lineHeight;
        int numBreaks = (textLines.size()-1)/linesPerPage;
        int[] pageBreaks = new int[numBreaks];
        for (int b=0; b < numBreaks; b++) {
            pageBreaks[b] = (b+1)*linesPerPage;
        }

        if (pageIndex > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }
        int y = 20;


        int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
        int end   = (pageIndex == pageBreaks.length) ? textLines.size() : pageBreaks[pageIndex];
        for (int line=start; line<end; line++) {
            y += lineHeight;
            g.drawString(textLines.get(line), 50, y);
        }
        return PAGE_EXISTS;
    }
}