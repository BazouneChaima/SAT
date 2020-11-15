
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;

public class testMain {

	public static void main(String[] args) {
		
		etat.initClause();
		try {
			File dataSet=new File("uf75-01.txt");
			System.out.println("hello");
			Scanner reader=new Scanner(dataSet);
			
			String s = null;
			int k=0;
			while(reader.hasNextLine()){
				s = reader.nextLine();
				s = s.trim();
				String []s2 = s.split(" ");
				if(s2.length==4){
					for(int i = 0;i<s2.length-1;i++){
						try {
							int []tab = new int[s2.length];
							tab[i]=Integer.parseInt(s2[i]); 
							if(tab[i]<0){
								int temp= (tab[i]*(-1))-1;//index

								etat.clause[k][temp]=0;
								//etat.g[temp]++;
							}else{
								etat.clause[k][tab[i]-1]=1;
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
		
		int iteration=0;
		
		etat e= new etat();
		ArrayList<etat> ouvert=new ArrayList<>();
		ArrayList<etat> fermer= new ArrayList<>();
		ArrayList<etat> listGenerer= new ArrayList<>();
		etat goal=null;
		//e.afficher();
		boolean success =e.Evaluer();
		if(success) {
			goal=e;
		}
		ouvert.add(e);
		//System.out.println(b);
		ArrayList <etat>tmp = new ArrayList<>();
			ArrayList <etat>tmp2 = new ArrayList<>();

		while(ouvert.isEmpty()==false && success==false) {
			System.out.println("------------------------------------------");
			System.out.println("ITERATION "+iteration);
			// for(etat n: ouvert){
			// 	n.afficher();
			// }

			//BFS and DFS
			etat e1=ouvert.remove(0);

			fermer.add(e1);
			listGenerer=e1.Generer();
			
			
			tmp2.addAll(ouvert);
			tmp2.addAll(fermer);
			tmp.addAll(listGenerer);
			for(etat n:listGenerer) {
				for(etat n2:tmp2) {
					if(Arrays.equals(n.variable, n2.variable) ) {
						tmp.remove(n);
					}
				}
			}
			listGenerer.clear();
			listGenerer.addAll(tmp);
			tmp.clear();
			tmp2.clear();
			

			for(etat n:listGenerer) {
				success=n.Evaluer();
				n.afficher();
				
				//System.out.println(n.Evaluer());
				//success=n.Evaluer();

				if(success) {
					n.but=true;
					goal=n;
					break;
				}
			}
			
			//DFS
			//ouvert.addAll(0,listGenerer);
			
			//BFS
			//ouvert.addAll(listGenerer);

			//trier la liste if A*
			ouvert.addAll(0,listGenerer);

			Collections.sort(ouvert);
			Collections.reverse(ouvert);
			

			listGenerer.clear();
			
			iteration++;

		}
		
		if(goal==null) {
			System.out.println("echec");
		}else {
			System.out.println("donc la solution qui satisfait toute les clause est: ");
			goal.afficher();
		}
		
		
	}

}
