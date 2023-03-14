import java.util.*;

import java.io.*;

public class banqueli implements Serializable {
	private int nbreCompte; //Nbre de comptes créés dans cette banque
	private ArrayList<Comptebancaireli> comptes;

	public void verserInteret()
	{}
	// ------- Construction d'une banque -------
	public banqueli()
	{
		comptes = new ArrayList<Comptebancaireli>();
		this.nbreCompte = 0;
	}
	// ------- Fonction de verification  -------
	public void verfication()
	{
		File fich = new File("C:\\Users\\LENOVO\\eclipse-workspace\\BanqueG\\banque.txt");
		if (!fich.exists()) 
		{
			try
			{  			     fich.createNewFile();   }
			catch(IOException e)
			{				System.out.println(e.getMessage());   }
			return;
		}
		try
		{
			FileInputStream file = new FileInputStream("C:\\Users\\LENOVO\\eclipse-workspace\\BanqueG\\banque.txt");
			ObjectInputStream lt = new ObjectInputStream(file);
			comptes = (ArrayList<Comptebancaireli>)lt.readObject();
			 nbreCompte=comptes.size();			lt.close();
		}
		catch(Exception e)
		{
			System.err.println("Erreur de lecture " + e);		}
	}
	// ------- Fonction Ecrire -------
	public void ecrire()
	{
		FileOutputStream file = null;
		try
		{
			comptes.get(nbreCompte).setNumcomptes(nbreCompte);
			file = new FileOutputStream("C:\\Users\\LENOVO\\eclipse-workspace\\BanqueG\\banque.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(comptes);//.toString().getBytes());
			out.flush();
			out.close();
			file.close();
		}
		catch(IOException e)
		{		System.err.println("Erreur de lecture " + e.getMessage());		}
		
	}
	
	public int getNbreComptes()
	{
		return comptes.size();
	}
	public void setNumcomptes(int i)
	{
		comptes.get(i).numero=i;
	}
	
	public void creerCompte()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(" Creer un compte\n  ..normal 1  \n ...courant  2 \n  ..epargne  3 \n");
		System.out.print("Votre choix : ");
		int choix = sc.nextInt();
		switch (choix) 
		{
			case 1 :
				comptes.add(new Comptebancaireli());
				ecrire();
				break;
			case 2 :
				comptes.add(new Comptecourantli());
				ecrire();
				break;
			case 3 :
				comptes.add(new CompteEpargneli());
				ecrire();
				break;
		}
		nbreCompte++;
	}
	// ------- Affichage des comptes de la banque -------
	public String toString()
	{
		String st = "";
		for(int i = 0; i < comptes.size(); i++) 
        	st += "\n Compte N° "+(1+i)+"\n"+comptes.get(i).toString() + "Type  :"+comptes.get(i).getClass().getName()+"\n";
		return st;
	}
	// ------- Chercher un compte -------
	public int chercher()
	{
		int res = 0;
		int choix = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("--------- Chercher ---------  \n par num : 1 \n par nom : 2 \n----------");
		System.out.print("Votre choix : ");
		choix = sc.nextInt();
		switch (choix) 
		{
			case 1 :
				System.out.print("Entrer le num : ");
				long num = sc.nextLong();
				for (int i = 0; i < comptes.size(); i++) 
				{
					if (comptes.get(i).getNumero() == num)
					{
						res = i;
						comptes.get(i).operationsCompte();
						break;
					}
					else
						res = -1;	
				}
				break;
			case 2 :
				System.out.print("Entrer le nom : ");
				String nom = sc.next();
				for (int i = 0; i < comptes.size(); i++) 
				{
					if (comptes.get(i).getNom().equals(nom))
					{
						res = i;
						comptes.get(i).operationsCompte();
						break;
					}
					else
						res = -1;		
				}
				break;
		}
		return res;	
	}
	// ------- Opérations sur un compte de la banque -------
	public void operation()
	{
		int i = chercher();
		if (i == -1) 
			System.out.println("Ce compte n'existe pas !");
		else
		{
			System.out.println(comptes.get(i));
			comptes.get(i).operationsCompte();
		}
	}	
	// ------- Menu des opérations d'une banque -------
	public void  virerb(long cp, long cb){
		int i,j;
		System.out.println(" le nombre comptes  "+nbreCompte);
		for(i=0; i<nbreCompte && comptes.get(i).getNumero()!=cp; i++) {}
		if(i==nbreCompte)
			System.out.println(" le compte n\'existe pas ");
		else {
			for(j=0; j<nbreCompte && (comptes.get(i).getNumero())!=cb; j++) {}
			if(j==nbreCompte)
				System.out.println(" le compte beneficiaire n\'existe pas ");
			else {
				
			     if(comptes.get(i).getClass().getName().equals("Comptebancaireli"))
				{	   	 
			    	 System.out.print("Saisissez le montant a envoyer : ");
					 Scanner sc1=new Scanner(System.in);	
				     Double lg=sc1.nextDouble();
				     comptes.get(i).virer(comptes.get(j),lg);  }
			     else if(comptes.get(i).getClass().getName().equals("Comptecourantli")) 
				{
			    	 comptes.get(i).virer(comptes.get(j));
			    }
			     else
			     {
			    	 System.out.println("Echec de l\'operation, c\'est un  compte d\'epargne");
			     }
			     //sc1.close(); 
		}
}
}
	public void suppcompte() {
		//this=null;
		System.out.println(" compte supprimé "); 
	}
	int menuBanque() {
		Scanner sc2=new Scanner(System.in);
		
		System.out.println(" \n  ----------Operations bancaires ------------");
		System.out.println("	lister ----------------------1 ");
		System.out.println("	ajouter un compte  ----------2 ");
		System.out.println("	retirer un compte  ----------3 ");
		System.out.println("	operation sur un compte -----4 ");
		System.out.println("	virer d\'argent  ------------ 5 ");
		System.out.println("	gerer un compte d\'Epargne--- 6 ");
		System.out.println("	quitter   ------------------ 7 ");
		System.out.print("choisissez l'operation a effectuer :  ");
		return(sc2.nextInt());}
		// return (sc.next().charAt(0));
	
	public void operationsBanque() {
		int c,l; long lg;
		Scanner sc=new Scanner(System.in);
		while((c=menuBanque())!=7)
		{
			switch(c) {
			case 1: System.out.println(this.toString());
			     break;
			case 2 : 
				this.creerCompte(); break;
			case 3 : suppcompte(); break;
			case 4: System.out.println(" resultat : "+chercher()); break;
			case 5 : System.out.print(" entrez le numero du compte sender ");
		      lg=sc.nextLong();
		     System.out.print(" entrez le numero du compte beneficiaiare  ");
		     long lg1=sc.nextLong();
		      virerb(lg,lg1);  this.ecrire(); break;
			case 6 : System.out.print(" entrez le numero du compte  ");
		      l=sc.nextInt();
		          comptes.get(l).OperaEpargne(); this.ecrire(); break;
		      default :  System.out.print(" choix invalide \n "); break;
			}
		}
	}
}
class Fibonacci
{
	int iter;
	public int iterative()
	{
		System.out.print(" donnez le nombre de termes de la suite : ");
		Scanner sc=new Scanner(System.in);
		  int nb=sc.nextInt();
		  int i;
		  int x=0,y=1,z;
		  z=1;
		  for(i=2; i<nb; i++)
		  {
			  z=x+y;
			  x=y; y=z;
		  }
		  return z+x;
   }
	public int recursive(int nbr)
	{
		if(nbr==0)
			return 0;
		else if(nbr==1)
			return 1;
		else
			return (recursive(nbr-1)+recursive(nbr-2));
	}
	 public void affiche(int g)
	 {
		 if(g==0)
			 System.out.println(0);
		 else 
		 {
			 affiche(g-1);
			 System.out.println(g);
		 }
	 }
}
/* Fibonacci exp=new Fibonacci();

int lg=exp.iterative();
System.out.println(" la suite iterative donne : "+lg);
int lg1=exp.recursive(6);
System.out.println(" la suite recursive donne : "+lg1);
 exp.affiche(5);*/
