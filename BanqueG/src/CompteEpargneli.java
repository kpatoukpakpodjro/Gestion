import java.util.*;
import java.io.*;

class CompteEpargneli extends Comptebancaireli implements Serializable {
	private double tauxInteret;
	
	// -------- Constructeurs --------
	public CompteEpargneli()
	{
		super(); //Appel au constructeur de la classe mère
		this.tauxInteret=0.03;
	}
	
	// -------- Getter --------
	public double getTauxInteret()
	{
		return this.tauxInteret;
	}	
	public void setTaux_interet()
	{
		double somm;
		Scanner sc=new Scanner(System.in);
		System.out.println("Saisissez le taux d\'interet : ");
		somm=sc.nextDouble();
		this.tauxInteret=somm;
	}
	// -------- Fonction Verser Interet --------
	public void verserInteret()
	{
		//super.verser(getSolde()*tauxInteret);	
		solde+=(getSolde()*tauxInteret);	
	}
	public void OperaEpargne()
	{
		int choix = 0;
		Scanner sc = new Scanner(System.in);
		do {
		System.out.println(" Operations sur un compte d\'epargne : ");
		System.out.println("consulter ----------------------1 ");
		System.out.println("verser    ----------------------2 ");
		System.out.println("verserInteret   ----------------3 ");
		System.out.println("Regler le taux d\'interet    ---4 ");
		System.out.println("quitter   --------------------- 5 ");
		System.out.print("choisissez l'operation a effectuer :  ");
		choix = sc.nextInt();
		switch(choix)
		{
		case 1: System.out.println(this); break;
		case 2 : this.verser(); break;
		case 3 : this.verserInteret(); break;
		case 4 : this.setTaux_interet(); break;
		case 5 : System.out.println(" Au revoir \n ");
		}
		}while(choix!=5);
		System.out.println("Fin des operations sur le compte , Au revoir Mr "+this.getNom()+ "!!\n");
	}
}
