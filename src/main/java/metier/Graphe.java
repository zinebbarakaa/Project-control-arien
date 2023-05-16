package metier;

public class Graphe {

	// Declaration de variable
	
	private int [][] U;
	private boolean [] valid; // sommet valide veut dire qu'il existe dans le graphe
	public final static int ALPHA_NOTDEF = -999 ;// on met final psk c'est une constante
	
	
	 // Contructeur par defaut:
	
	public Graphe () {
		int NMax = 1000;
		U = new int [NMax][NMax];
		valid = new boolean [NMax];
		for (int i=0 ; i<U.length ; i++){
			valid[i] = false;
			for (int j=0 ; j<U[i].length ; j++){
				U[i][j] = ALPHA_NOTDEF;
			}
		}
	}
	// constructeur via une matrice adjacence:
	
	public Graphe (int [][] mat){
		int NMax = mat.length;
		//int NMax = 1000 + mat.length;
		U = new int [NMax][NMax];
		valid= new boolean [NMax];
		for (int i = 0 ; i<U.length ; i++){
			if (i < mat.length){
				valid [i] = true;
			}
			else {
				valid[i] = false;
			}
			for (int j = 0 ; j<U.length ; j++){
				if ((j<mat.length) && (i<mat.length)){
					U[i][j] = mat [i][j];
				}
				else {
					U[i][j] = ALPHA_NOTDEF;
				}
			}
		}
	}
	
	public boolean existeSommet(int i){
		boolean res = false;
		if (!((i > valid.length) || (i<0))){
			res = valid[i];
		}
		return res;
	}
	
	public int nbSommet(){
		int nb = 0;
		for (int i = 0 ; i<U.length ; i++){
			if (valid[i]){ nb ++;}
		}
		return nb;
	}
	
	public boolean existeArc(int i, int j){
		return ((existeSommet(i)) && (existeSommet(j)) && (U[i][j] != ALPHA_NOTDEF));
	}
	
	public int getValArc(int i, int j){
		if ((i<0) || (i >= U.length) || (j<0) || (j >= U.length)){
			System.out.println("Graphe::getValArc : Erreur: hors de la matrice.");
			System.exit(-1);
		}
		if (!existeArc( i, j)){
			System.out.println("Graphe::getValArc : Erreur: aucun arc.");
			System.exit(-1);
		}
		return U[i][j];
	}
	
	
	
	
	public boolean ajoutSommet(int i){
		if ((i<0) || (i >= U.length)){
			System.out.println("Graphe::degre : Erreur: hors de la matrice.");
			System.exit(-1);
		}
		if (existeSommet(i)){
			return false;
		}
		valid[i]=true;
		for (int k=0 ; k<U.length ; k++){
			U[i][k] = ALPHA_NOTDEF;
			U[k][i] = ALPHA_NOTDEF;
		}
		return true;
	}
	
	 public boolean supprimeSommet(int i){
         if((i<0)||(i>=U.length))
         {
         System.out.println(" i n'appartient pas � [0;U.length]");
         System.exit(-1);
         }
                 if(!existeSommet(i))
                 {
                    return false;
                 }
                 else 
                 {
                         if( degre(i)!=0)
                      {
                System.out.println("le sommet "+i+" est encore connect�");
                System.exit(-1);
                 }
                                                          
                valid[i]=false;
                       } 
                                                              
          return true;
	 }
	
	
	public boolean ajoutArc(int i, int j, int val){
		if (!(existeSommet(i) && (existeSommet(j)))){
			return false;
		}
		if (existeArc(i,j)){
			return false;
		}
		
		U[i][j]=val;
		return true;
		
	}
	
	public boolean supprimerArc(int i,int j){
		if (!(existeSommet(i) && (existeSommet(j)))){
			return false;
		}
		U[i][j]=ALPHA_NOTDEF;
		return true;
	}
	
	public int degreEntrant(int i){
		int x = 0;
		if ((i<0) || (i >= U.length)){
			System.out.println("Graphe::degreEntrant : Erreur: hors de la matrice.");
			System.exit(-1);
		}
		if (!existeSommet(i)){
			System.out.println("Graphe::degreEntrant : Erreur: ce sommet n'existe pas.");
			System.exit(-1);
		}
		for (int k=0 ; k<U.length ; k++){
			if (U[i][k] != ALPHA_NOTDEF){
				x++;
			}
		}
		return x;
	}
	
	public int degreSortant(int i){
		int x = 0;
		if ((i<0) || (i >= U.length)){
			System.out.println("Graphe::degreSortant : Erreur: hors de la matrice.");
			System.exit(-1);
		}
		if (!existeSommet(i)){
			System.out.println("Graphe::degreSortant : Erreur: ce sommet n'existe pas.");
			System.exit(-1);
		}
		for (int k=0 ; k<U.length ; k++){
			if (U[k][i] != ALPHA_NOTDEF){
				x++;
			}
		}
		return x;
	}
	
	public int degre(int i){
		if ((i<0) || (i >= U.length)){
			System.out.println("Graphe::degre : Erreur: hors de la matrice.");
			System.exit(-1);
		}
		if (!existeSommet(i)){
			System.out.println("Graphe::degre : Erreur: ce sommet n'existe pas.");
			System.exit(-1);
		}
		return (degreEntrant(i)+degreSortant(i));
	}
	
	public int [] lst_succ(int i){
		if ((i<0) || (i >= U.length)){
			System.out.println("Graphe::lst_succ : Erreur: hors de la matrice.");
			System.exit(-1);
		}
		if (!existeSommet(i)){
			System.out.println("Graphe::lst_succ : Erreur: ce sommet n'existe pas.");
			System.exit(-1);
		}
		int[] tab = new int[nbSommet()];
		int y=0;
		while (y < nbSommet()){
			for (int j=0; j<U[i].length;j++)//oubli� dans le code
				if (U[i][j]!=ALPHA_NOTDEF){
					tab[y]=U[i][j];
					y++;
				}
		}
		return tab;	//oubli� dans le code		
	}
	
	public String toString(){
		int x;
		int i=0;
		int j=0;
		x = nbSommet();
		String chaine="";
		while(j<x){
			chaine += "[";
			if (valid[j]){
				while (i<x){
					if (valid[i]){
						chaine += U[i][j];
					}
					i++;	
				}
				System.out.print("]");
			}
			j++;
			System.out.println();
		}
		return chaine;
	
	}

	public int[][] getU() {
		return U;
	}

	public void setU(int[][] u) {
		U = u;
	}

	public boolean[] getValid() {
		return valid;
	}

	public void setValid(boolean[] valid) {
		this.valid = valid;
	}
	
	
}
