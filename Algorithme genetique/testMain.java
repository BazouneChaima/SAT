import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class testMain{
	public static void main(String[] args) {
		//input
		int popSize = 1000; //4
		int sizeProblem = 75; //3
		double pCrossOver = 1.0;
		double pMutation = 1.0;
		boolean succes = false;

		//output
		Chromosome sGbest = null;

		//initialiser les donnees 
		//int [][]clause ={{1,0,1},{-1,1,-1},{0,0,1},{0,-1,0}};
		int [][]clause=new int[325][75];
		//init with -1
		for(int i =0;i<clause.length;i++){
			for(int j=0;j<clause[0].length;j++){
				clause[i][j]=-1;
			}
		}
		int iteration = 2000;
		int i = 0;


		try {
			File dataSet=new File("uf75-01.txt");
			//System.out.println("hello");
			Scanner reader=new Scanner(dataSet);
			
			String s = null;
			int k=0;
			while(reader.hasNextLine()){
				s = reader.nextLine();
				s = s.trim();
				String []s2 = s.split(" ");
				if(s2.length==4){
					for(int r = 0;r<s2.length-1;r++){
						try {
							int []tab = new int[s2.length];
							tab[r]=Integer.parseInt(s2[i]); 
							if(tab[r]<0){
								int temp= (tab[r]*(-1))-1;//index

								clause[k][temp]=0;
								//etat.g[temp]++;
							}else{
								clause[k][tab[r]-1]=1;
								//etat.g[tab[i]-1]++;
							}
							//System.out.print(tab[i]+" ");

						}catch(NumberFormatException e){}
					
					}
					//System.out.println();
					k++;
				}
				
				
			}
			reader.close();
		}catch(Exception e) {
			System.out.println("can not open the file here");
		}


		/****************ALGO_GENETIQUE*****************/
		Population population = new Population(popSize, sizeProblem);
		population.Init(popSize, sizeProblem);
		succes = population.evaluatePop(clause);
		sGbest = population.getBest();

		//System.out.println("-------Population--------");
		//population.Affiche();
		Chromosome sBest = new Chromosome (sizeProblem);
		Population parents = new Population(popSize, sizeProblem);
		//Population childs = new Population(popSize, sizeProblem);
		while(!succes && (i<iteration)){
			//System.out.println("-------Iteration "+ i +"--------");
			parents = population.SelectParents(popSize, sizeProblem);
			Population childs = new Population(popSize, sizeProblem);
			int j = 0;
			int k = j+1;
			while(k<popSize/2){
				Chromosome child_1 = new Chromosome (sizeProblem);
				Chromosome child_2 = new Chromosome (sizeProblem);
				child_1 = parents.CrossOver(parents.population.get(j), parents.population.get(k), pCrossOver);
				child_2 = parents.CrossOver(parents.population.get(k), parents.population.get(j), pCrossOver);
				childs.Mutaion(child_1,pMutation);
				childs.Mutaion(child_2,pMutation);
				j = j+2;
				k = j+1;
			}
			succes = childs.evaluatePop(clause);
			sBest = childs.getBest();
			if (sBest.fitness> sGbest.fitness){
				sGbest = sBest;
			}
			population.Repalce(childs);
			//System.out.println("-------CHilds--------");
			//population.Affiche();
			i++;
		}

		sGbest.printSol();

	}
}