import java.util.*;
import java.io.*;

public class Comptebancaireli implements Serializable {
	protected String nom; 
	protected long numero;
	protected  double solde;
	static long nbreComptes =0 ;

	void verserInteret()
	{}
	public void setNumcomptes(int i)
	{
		this.numero=i;
	}
	// ----------- Constructeur -----------
	public Comptebancaireli()
	{
		Scanner sc2 = new Scanner(System.in);
		System.out.print("Nom : ");
		this.nom = sc2.nextLine();
		 this.numero = ++nbreComptes;
		 
	}
	public  void OperaEpargne() { 
		//return 0.0;
	}
	// ----------- Getters -----------
	public long getNumero() 
	{
		return this.numero;
	}
	public String getNom() 
	{
		return this.nom;
	}
	public  double getSolde() 
	{
		return solde;
	}
	public  long getNbreComptes() 
	{
		return nbreComptes;
	}
	// ---------- Opérations bancaires ----------
	/*----- Verser -----*/
	public void verser(double solde)
	{
		solde += solde;
	}
	public void verser()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrer la somme à verser : ");
		solde += sc.nextDouble();
	}
	/*----- Retirer -----*/
	public void retirer(double s)
	{
		if (solde >= s) 
			solde -= s;
		else
			System.out.println("Solde insuffisant !");
	}
	public void retirer()
	{
		double s;
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrer la somme à retirer : ");
		s = sc.nextDouble();
		if (solde >= s) 
			solde -= s;
		else
			System.out.println("Solde insuffisant !");
	}
	/*----- Affichage des données du compte -----*/
	public String toString()
	{
		String lt = "Proprietaire : " + this.nom + "\n";
		lt += "Numero : " + this.numero + "\n";
		lt += "Solde : " + solde + "\n";
		return lt;
	}
	public void virer(Comptebancaireli b,double sl)
	{
		if(this.solde<sl)
			System.out.println(" \n solde insuffisant oo !!!\n");
		else {
			this.retirer(sl);
			b.verser(sl);
		}
	}
	// peut être activée lorque le compilateur rejete le virement
	public void virer(Comptebancaireli b)
	{System.out.println("virement ");}

	public int saisir() {
		Scanner ss=new Scanner(System.in);
		int ch1;
    	ch1=ss.nextInt();
		//ss.close();
		return (ch1);
	}
	// ---------- Menu des opérations d'un compte ----------
	public char menu()
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("************ Menu d'un Compte *************");
		System.out.println("-     Afficher 		 ------------- 1 --");
		System.out.println("+     Verser   		 ------------- 2 --");
		System.out.println("-     Retirer  		 ------------- 3 --");
		System.out.println("->     Verser Intérêt  ------------- 4 --");
		System.out.println("-->   Quiter   		 ------------- 5 --");
		System.out.println("**************************************");
		System.out.print("Entrer votre choix : ");

		return (sc.next().charAt(0));
	}
	public void operationsCompte()
	{
		char choix = this.menu();
		do{
			switch (choix) 
			{
				case '1' :
					System.out.println(this);
					break;
				case '2' :
					this.verser();
					break;
				case '3' :
					this.retirer();
					break;
				case '4' :
					if (this.getClass().getName().equals("CompteEpargneli"))
						{
							this.verserInteret();
							System.out.println("Votre nouveau solde de " + this.getNom() + "  : " + this.getSolde());
						}
					else
						System.out.println("Ce n'est pas un compte epargne !");
					break;
			}
		} while((choix = this.menu()) != '5');
	System.out.println("Fin des operations sur le compte , Au revoir Mr "+this.getNom()+ "!!\n");
	}


}