package vue;

import javax.swing.*;

import modele.Equipe;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Arrays;
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

    private void createRapport(){
        this.textLines = new ArrayList<>();
        String titre="Tournoi : "+this.nomTournoi;
        String sousTitre ="Rapport de classement du "+this.date;
        textLines.add(titre);
        textLines.add(sousTitre);
        textLines.add("\n\n");
        String[] ClassementLine;
        for(int i = 0; i < equipes.size(); i++){
            ClassementLine = new String[3];
            ClassementLine[0] = "Equipe "+equipes.get(i).getNom();
            ClassementLine[1] = "Points "+points.get(i);
            ClassementLine[2] = "Classement "+(i+1);
            textLines.addAll(Arrays.asList(ClassementLine));
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

/*btnImprimer.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        // Définit son contenu à imprimer
        job.setPrintable(new vue.Impression(equipes, point, nomTournoi, Date.now().toString()));
        // Affiche une boîte de choix d'imprimante
        if (job.printDialog()){
            try {
                // Effectue l'impression
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }

    }
});*/