
import java.util.ArrayList;
import java.util.Collections;

public class etat implements Comparable <etat>{
	
	/*
	C1=X1+!X2+X3
	C2=X2
	C3=!X1+!X2+X3
	C4=!X1+!X3
	 */
	
	//clause logic
	/*
	 X=1
	!X=0
	null=-1
	 */
	//{{1,0,1},{-1,1,-1},{0,0,1},{0,-1,0}}
	//public static int g[];
	public static int clause [][];
	public int [] variable =null;
	public etat pred;
	public boolean but;

	//objectif function
	public int fitness;
	
	
	public etat() {
		variable = new int[clause[0].length];
		
	}
	
	
	//init clause
	public static void initClause() {
		clause=new int[325][75];//hadi kont dayrtha clause=new int[325][75];
		for(int i =0;i<clause.length;i++){
			for(int j=0;j<clause[0].length;j++){
				clause[i][j]=-1;
			}
		}
		//g = new int[clause[0].length];

	}
	//pour genere les fils de l'etat pred
	public ArrayList<etat> Generer() {
		ArrayList<etat> temp = new ArrayList<>();
		for(int i=0;i<clause[0].length;i++) {
			
			if(variable[i]==0) {
			etat e=new etat();
			//cree le chenage avec pred
			e.pred=this;
			//copier le premier tableau dans le 2eme 
			System.arraycopy(variable, 0, e.variable, 0, clause[0].length);
			e.variable[i]=1;
			temp.add(e);
			}
		}
		
		return temp;
	}
	
	//la fonction g qui calcule le cout depuis etat initiale jusqu
	public int getG(){
		int c=0;
		for(int i =0; i<variable.length;i++){
			c=c+variable[i];
		}
		return c;
	}

	//evaluer les successeur generer
	public boolean Evaluer() {
		int cpt = 0;
		boolean sat1=true;
		boolean sat2=false;
		for (int i =0;i<clause.length;i++){
			for(int j =0;j<clause[0].length;j++){
				if(clause[i][j]==variable[j]) {
					sat2=true;
					break;
				}
			}if(sat2 == false) {
				sat1 = false;
			}else if(sat2 == true){
				cpt++;
			}
			sat2 = false;
		}

		
		fitness = cpt ;

		return sat1;
	}

	//fonction qui trier la liste ouvert selon la fonction fitness
	// public void trier (){

	// }

	public void afficher() {
		for(int i=0;i<variable.length;i++) {
			System.out.print("|" +variable[i] );
		}
		System.out.print("    =>"+fitness);
		System.out.println();
	}

	public int compareTo(etat e){
		return (this.fitness - e.fitness);
	}
	
		
}