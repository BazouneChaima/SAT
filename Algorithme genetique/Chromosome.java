import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Chromosome implements Comparable <Chromosome>{


	public int variable[];
	public int fitness;
	public boolean isSolution;


	public Chromosome(int problemSize){
		variable = new int[problemSize];
		fitness = 0;
		isSolution = false;
	}

	//this methode evaluate the solution 
	public boolean evaluate(int clause[][]){
		int cpt = 0;
		boolean sat1=true;
		boolean sat2=false;
		
		// int i=0;
		// int j=0;
		// while(i<clause.length && sat1==true ) {
		// 	while(j<clause[0].length && sat2==false) {
		// 		if(clause[i][j]==variable[j]) {
		// 			sat2=true;
		// 		}
		// 		j++;
		// 	}
		// 	if(sat2==false) {
		// 		sat1=false;
		// 	}else {
		// 		i++;
		// 		sat2=false;
		// 		j=0;
		// 		}
			
		// }

		for (int i =0;i<clause.length;i++){
			for(int j =0;j<clause[0].length;j++){
				if(clause[i][j]==variable[j]) {
					sat2=true;
				}
			}if(sat2==false) {
				sat1=false;
			}else{
				cpt++;
			}
			sat2 = false;
		}
		//cpt = i;
		fitness = cpt ;
		isSolution = sat1;
		return sat1;
	}


	//this methode generate a random solution
	public void Generate(){
		for(int i=0;i<this.variable.length;i++){
			this.variable[i] = (int) Math.round(Math.random());
		}
	}


	//this methode used for sort the chromosome by there fitness
	public int compareTo(Chromosome ch){
		return (this.fitness -ch.fitness);
	}

	//affiche
	public void printSol(){
		for(int i =0;i<this.variable.length;i++){
			System.out.print(this.variable[i]);
		}
		System.out.print(" fitness is :"+this.fitness);
		System.out.println();
	}

}