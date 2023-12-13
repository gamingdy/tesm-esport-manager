package dao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import modele.Categorie;
import modele.Pays;
import modele.CustomDate;
import modele.Niveau;

public class DBInsertTest {

    public static void main(String[] args) {
        Connexion co = Connexion.getConnexion();
        
        try {
        	
        	Statement st = co.getConnection().createStatement();
        	PreparedStatement ps;
        	/*
        	for (int i=1;i<=10;i++) {
        		//table équipe
        		String nomEquipe = "Equipe"+i;
        		int worldRank = i*i;
        		String pays = Pays.values()[i].name();
        		st.executeUpdate("INSERT INTO Equipe (Nom_Equipe,Pays_Equipe,World_rank) values ('"+nomEquipe+"','"+pays+"',"+worldRank+")");
        	}
        	
        	System.out.println("Insertion table Equipe terminée avec succès");
        	
        	for(int i =0; i<50; i++) {
        		//table joueur
        		String pseudo = "Joueur"+i;
        		String nomEquipe = "Equipe"+(Math.floorMod(i, 10)+1);
        		st.executeUpdate("INSERT INTO Joueur (Pseudo,Nom_Equipe) values ('"+pseudo+"','"+nomEquipe+"')");
        	}
        	
        	System.out.println("Insertion table Joueur terminée avec succès");
        	
        	for(int i =1; i<=10; i++) {
        		String nom = "Nom"+i;
        		String prenom = "Prenom"+i;
        		st.executeUpdate("INSERT INTO Arbitre (Nom,Prénom) values ('"+nom+"', '"+prenom+"')");
        	}
        	
        	System.out.println("Insertion table Arbitre terminée avec succès");
        	
        	for (int i = 1; i<=5; i++) {
        		int annee = 2002+i;
        		st.executeUpdate("INSERT INTO Saison (Annee) values ("+annee+")");
        	}
        	
        	System.out.println("Insertion table Saison terminée avec succès");
        	*/
        	st.executeUpdate("INSERT INTO Niveau (Libelle_Niveau,Coefficient) values ('"+Niveau.LOCAL.name()+"',"+Niveau.LOCAL.getCoefficient()+")");
        	st.executeUpdate("INSERT INTO Niveau (Libelle_Niveau,Coefficient) values ('"+Niveau.REGIONAL.name()+"',"+Niveau.REGIONAL.getCoefficient()+")");
        	st.executeUpdate("INSERT INTO Niveau (Libelle_Niveau,Coefficient) values ('"+Niveau.NATIONAL.name()+"',"+Niveau.NATIONAL.getCoefficient()+")");
        	st.executeUpdate("INSERT INTO Niveau (Libelle_Niveau,Coefficient) values ('"+Niveau.INTERNATIONAL.name()+"',"+Niveau.INTERNATIONAL.getCoefficient()+")");
        	st.executeUpdate("INSERT INTO Niveau (Libelle_Niveau,Coefficient) values ('"+Niveau.INTERNATIONAL_CLASSE.name()+"',"+Niveau.INTERNATIONAL_CLASSE.getCoefficient()+")");
        	
        	System.out.println("Insertion table Niveau terminée avec succès");
        	/*
        	for (int i = 1; i<=5; i++) {
        		int annee = 2002+i;
        		String nomTournoi = "Tournoi"+i;
        		String username =  "UsernameAnnee"+annee;
        		String mdp = "MotDePasseSolideDe"+annee;
        		CustomDate dB = new CustomDate(Timestamp.valueOf(""+(2002+i)+"-01-01 10:00:00"));
        		CustomDate dF = new CustomDate(Timestamp.valueOf(""+(2002+i)+"-01-01 18:00:00"));
        		ps = co.getConnection().prepareStatement("INSERT INTO Tournoi (Annee,Nom_Tournoi,Date_Début,Date_Fin,username,mdp,Libelle_Niveau) values (?,?,?,?,?,?,?)");
        		ps.setInt(1, annee);
        		ps.setString(2, nomTournoi);
        		ps.setTimestamp(3, dB.toSQL());
        		ps.setTimestamp(4, dF.toSQL());
        		ps.setString(5,username);
        		ps.setString(6,mdp);
        		ps.setString(7, Niveau.INTERNATIONAL_CLASSE.name());
        		ps.execute();
        		
        	}
        	
        	System.out.println("Insertion table Tournoi terminée avec succès");
        	
        	for (int i = 1; i<=5; i++) {
        		int nbParties = 5;
        		int calcul1 = 2*i-1;
        		int calcul2 = 2*i;
        		int annee = 2002+i;
        		String nomTournoi = "Tournoi"+i;
        		String nomEquipe1 = "Equipe"+calcul1;
        		String nomEquipe2 = "Equipe"+calcul2;
        		CustomDate dB = new CustomDate(Timestamp.valueOf(""+(2002+i)+"-01-01 10:00:00"));
        		String categorie = Categorie.DEMI_FINALE.toString();
        		ps = co.getConnection().prepareStatement("INSERT INTO Matche (Nombres_Parties_Max,Date_Matche_Debut,categorie,Nom_Equipe1,Nom_Equipe2,Annee,Nom_Tournoi) values (?,?,?,?,?,?,?)");
        		ps.setInt(1, nbParties);
        		ps.setTimestamp(2, dB.toSQL());
        		ps.setString(3, categorie);
        		ps.setString(4, nomEquipe1);
        		ps.setString(5, nomEquipe2);
        		ps.setInt(6, annee);
        		ps.setString(7, nomTournoi);
        		ps.execute();
        	}
        	
        	System.out.println("Insertion table Matche terminée avec succès");
        	
        	for(int i = 1; i<=5;i++) {
        		for(int y = 1; y<=3; y++) {
        			st.executeUpdate("INSERT INTO Partie(Id_Match,Id_Partie) values ("+i+","+y+")");
        		}
        	}
        	
        	System.out.println("Insertion table Partie terminée avec succès");
        	
        	for(int i = 1; i<=5;i++) {
        		for(int y = 1; y<=4; y++) {
        			char libellePoule = (char)(64+y);
        			int annee = 2002+i;
        			String nomTournoi = "Tournoi"+i;
        			st.executeUpdate("INSERT INTO Poule(Annee,Nom_Tournoi,Libelle) values ("+annee+", '"+nomTournoi+"','"+libellePoule+"')");
        		}
        	}
        	
        	System.out.println("Insertion table Poule terminée avec succès");
        	
        	for(int i = 1; i<=5;i++) {
        		for(int y = 1; y<=10; y++) {
        			String nomEquipe = "Equipe"+y;
        			int annee = 2002+i;
        			st.executeUpdate("INSERT INTO Inscription (Nom_Equipe,Annee) values ('"+nomEquipe+"',"+annee+")");
        		}
        	}
        	
        	System.out.println("Insertion table Inscription terminée avec succès");
        	
        	for(int i = 1; i<=5;i++) {
        		for(int y = 1; y<=4; y++) {
        			int idArbitre = i+y;
        			int annee = 2002+i;
        			st.executeUpdate("INSERT INTO Selection (Id_Arbitre,Annee) values ("+idArbitre+","+annee+")");
        		}
        	}
        	
        	System.out.println("Insertion table Selection terminée avec succès");
        	
        	for(int i = 1; i<=5;i++) {
        		for(int y = 1; y<=4; y++) {
        			int annee = 2002+i;
        			String nomTournoi = "Tournoi"+i;
        			int idArbitre = i+y;
        			st.executeUpdate("INSERT INTO Arbitrage (Annee,Nom_Tournoi,Id_arbitre) values ("+annee+", '"+nomTournoi+"',"+idArbitre+")");
        		}
        	}
        	
        	System.out.println("Insertion table Arbitrage terminée avec succès");
        	
        	for(int i = 1; i<=5;i++) {
        		for(int y = 1; y<=4; y++) {
        			char libellePoule = (char)(64+y);
        			String nomEquipe = "Equipe"+y;
        			int annee = 2002+i;
        			String nomTournoi = "Tournoi"+i;
        			st.executeUpdate("INSERT INTO Appartenance (Nom_Equipe,Annee,Nom_Tournoi,Libelle) values ('"+nomEquipe+"',"+annee+", '"+nomTournoi+"','"+libellePoule+"')");
        		}
        	}
        	
        	System.out.println("Insertion table Appartenance terminée avec succès");
        	
        	System.out.println("Toutes les insertions ont été réalisées avec succès");
        	
        	
			*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            co.stop();
        }
    }
}
