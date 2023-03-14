import java.util.*;
import java.io.*;

public class Comptecourantli extends Comptebancaireli implements Serializable {
	private double NegatifAutorise;

	// -------- Constructeurs --------
	public Comptecourantli()
	{
		super(); //Appel au constructeur de la classe m�re
		Scanner sc = new Scanner(System.in);
		System.out.print("Seuil autoris� : ");
		this.NegatifAutorise = sc.nextDouble();
	}
	public Comptecourantli(double negf)
	{
		this.NegatifAutorise = negf;
	}

	public double getNegAutorise()
	{
		return this.NegatifAutorise;
	}
	// -------- Fonction d'affichage --------
	public String toString()
	{
		String st = super.toString();
		st += "Seuil autoris� : " + this.NegatifAutorise + "\n";
		return st;
	}
	// -------- Fonction retirer --------
	public void retirer()
	{
		double s = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Somme � retirer : ");
		s = sc.nextDouble();
		if (s < (this.solde + this.NegatifAutorise))
			this.solde -= s;
		else
			System.out.println("Solde insuffisant !");
	}
	public void retirer(double s)
	{
		if (s < (this.getSolde()) + this.NegatifAutorise)
			this.solde -= s;
		else
			System.out.println("Solde insuffisant !");
	}
}