package vue;

import modele.Equipe;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;

public class Impression extends JPanel implements Printable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Equipe> equipes;
	private List<Float> points;
	private String nomTournoi;
	private List<String> textLines;
	private String date;

	public Impression(List<Equipe> equipes, List<Float> point, String nomTournoi, String date) {
		this.equipes = equipes;
		this.points = point;
		this.nomTournoi = nomTournoi;
		this.date = date;
		this.createRapport();
	}

	private void createRapport() {
		textLines = new ArrayList<>();

		for (int i = 0; i < equipes.size(); i++) {
			String equipe = equipes.get(i).getNom();
			String pointsStr = String.valueOf(points.get(i));
			String classement = String.valueOf(i + 1);

			// Ajuster dynamiquement la largeur des colonnes
			textLines.add(String.format("%-30s%-15s%-20s", equipe, pointsStr, classement));
		}
	}

	public int print(Graphics g, PageFormat pf, int pageIndex) {
		int y;
		int start;
		int end;
		int tabSize;
		Font titleFont = new Font("Consolas", Font.BOLD, 16);
		Font font = new Font("Consolas", Font.PLAIN, 12);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		int lineHeight = metrics.getHeight();
		double pageHeight = pf.getImageableHeight();
		int linesPerPage = ((int) pageHeight) / lineHeight;
		int numBreaks = (textLines.size() - 1) / linesPerPage;
		int[] pageBreaks = new int[numBreaks];
		for (int b = 0; b < numBreaks; b++) {
			pageBreaks[b] = (b + 1) * linesPerPage;
		}

		if (pageIndex > pageBreaks.length) {
			return NO_SUCH_PAGE;
		}

		y = 20;

		// Dessiner le titre centré
		String title = "Tournoi : " + this.nomTournoi;
		g.setFont(titleFont);
		FontMetrics titleMetrics = g.getFontMetrics(titleFont);
		int titleWidth = titleMetrics.stringWidth(title);
		int xTitle = (int) ((pf.getImageableWidth() - titleWidth) / 2);
		g.drawString(title, xTitle, y);

		// Dessiner le sous-titre
		y += titleMetrics.getHeight();
		String subTitle = "Rapport de classement du " + this.date;
		g.setFont(font);
		FontMetrics subTitleMetrics = g.getFontMetrics(font);
		g.drawString(subTitle, 50, y);

		y += subTitleMetrics.getHeight();
		g.drawString("\n", 50, y);
		y += subTitleMetrics.getHeight();
		// Table headers en gras
		Font headerFont = new Font("Consolas", Font.BOLD, 12); // Police en gras pour les titres du tableau
		g.setFont(headerFont);
		FontMetrics headerMetrics = g.getFontMetrics(headerFont);
		int xHeader = 50;

		String headerLine = String.format("%-30s%-15s%-20s", "Equipe", "Points", "Classement");
		g.drawString(headerLine, xHeader, y);

		y += headerMetrics.getHeight();
		start = pageIndex * linesPerPage;
		end = Math.min(start + linesPerPage, textLines.size());

		// Dessiner le contenu du tableau
		g.setFont(font);
		tabSize = 8; // Ajustez la taille de l'onglet selon vos besoins
		for (int line = start; line < end; line++) {
			y += lineHeight;
			String[] parts = textLines.get(line).split("\t");
			int x = 50;
			for (String part : parts) {
				g.drawString(part, x, y);
				x += tabSize;
			}
		}

		return PAGE_EXISTS;
	}
}
