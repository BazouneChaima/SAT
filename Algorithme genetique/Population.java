import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Population {

	public ArrayList <Chromosome> population;
	public int popSize;
	public int problemSize;

	//default constructor 
	public Population(int popSize, int problemSize){
		this.population = new ArrayList<>();
		this.problemSize = problemSize;
		this.popSize = popSize;
	}

	public void Init(int popSize, int problemSize){
		this.population = new ArrayList<>();
		this.problemSize = problemSize;
		this.popSize = popSize;
		int i = 0;
		while(i<popSize){
			Chromosome ch = new Chromosome(problemSize);
			ch.Generate();
			if (!exist(ch)){
				this.population.add(ch);
				i++;
			}
		}
	}

	//test si ce tableau exist ou pas 
	public boolean exist(Chromosome ch ){
		if (this.population == null){
			return false;
		}

		for(Chromosome c: this.population){
			if(Arrays.equals(c.variable,ch.variable)){
				return true;
			}
		}
		return false;
	}


	//evaluation de la pupulation 
	public boolean evaluatePop(int clause[][]){
		for(Chromosome ch: this.population){
			 if(ch.evaluate(clause)){
			 	return true;
			 }
		}
		return false;
	}


	//this methdod will sort the population
	private void rankIt(){
		Collections.sort(this.population);
		Collections.reverse(this.population);
	}


	//this methode will select the best solution 
	public Chromosome getBest(){
		this.rankIt();
		return this.population.get(0);
	}

	//methode for select parents N/2
	public  Population SelectParents(int popSize,int problemSize){
		Population parents =  new Population(popSize,problemSize);
		//this.rankIt();

		for(int i =0;i<popSize/2;i++){

			//parents.population.add(this.population.get((int)(Math.random()*20)));
			parents.population.add(this.population.get(i));
		}
		return parents;
	}

	

	//this methode will crossover between two parents 
	public  Chromosome CrossOver(Chromosome parent_1, Chromosome parent_2, double pCrossOver){
		int posCross = (int)(Math.random()*74); //for testing a little variables;
		Chromosome ch = new Chromosome(problemSize);
		// if(pCrossOver > Math.random()){
			
		// }	
		//System.arraycopy(parent_1.variable,1,ch.variable,1,2);
		//System.arraycopy(parent_2.variable,0,ch.variable,0,1);


		//System.arraycopy(parent_1.variable,20,ch.variable,20,55);
		//System.arraycopy(parent_2.variable,0,ch.variable,0,20);
		System.arraycopy(parent_1.variable,posCross,ch.variable,posCross,75-posCross);
		System.arraycopy(parent_2.variable,0,ch.variable,0,posCross);
		return ch;
	}


	//this methode do the mutation
	public void Mutaion(Chromosome ch, double pMutation){
		int posMutation = (int)(Math.random()*74);
		if(pMutation > Math.random()){
			if(ch.variable[posMutation] == 1){
				ch.variable[posMutation] = 0;
			}else{
				ch.variable[posMutation] = 1;
			}
		}
		this.population.add(ch);
	}


	public void Repalce(Population childs){
		Population p = new Population(popSize,problemSize);
		p.population.addAll(childs.population);
		int temp = p.population.size();
		int i = 0;
		for(Chromosome ch:this.population){
			if(i<popSize-temp){
				p.population.add(ch);
				i++;
			}else{
				break;
			}
		}

		this.population.clear();
		this.population.addAll(p.population);
	}
	
	//this methode print the population 
	public void Affiche(){
		for(Chromosome ch:this.population){
			ch.printSol();
		}
	}


}