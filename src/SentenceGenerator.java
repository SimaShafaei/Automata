import java.util.Random;

public class SentenceGenerator {

	private String derivation(String s,Grammer g, boolean probability){
		String[] sent=s.split(" ");
		for (int i=0;i<sent.length;++i){
			if (g.isVariable(sent[i])){
				//find rules with same leftside:
				Rule rule=new Rule();
				rule.setLeftSide(sent[i]);
				Rule[] options=g.findSameLeft(rule);
				
				//select one rule from options:
				int c;
				Random select=new Random();
				if (!probability){					
					c=select.nextInt(options.length);
				}
				else
				{
					float w= g.getWeight(sent[i]);
					float point= select.nextFloat()*w;
					float sum=options[0].getNum();
					c=0;
					while (sum<=point){
						c++;
						sum+=options[c].getNum();
					}
					
				}
				//creating new sentence by replace variable with right side of rule
				String newSent="";
				for (int k=0;k<i;++k)
					newSent=newSent+sent[k]+" ";
				
				for (int k=0;k<options[c].getRightSide().length;++k){
					newSent=newSent+options[c].getRightSide()[k]+" ";					
				}
				
				for (int k=i+1;k<sent.length;++k)
					newSent=newSent+sent[k]+" ";
								
				// recursive call for replacing next variable
				return derivation(newSent,g,probability);
				
			}
		}
		return s;
				
	}
	
	public String createRandSent1(Grammer g){
		//create sentence with same probability for each rule
		String s=derivation(g.start,g,false); 
		return s;
	}
	
	public String createRandSent2(Grammer g){
		//create sentence with different probability for each rule
		String s=derivation(g.start,g,true); 
		return s;
	}
	
	private String treeDerivation(String s,Grammer g,int space){		
		String[] sent=s.split(" ");
		String newSent="";
		int findVar=0;
		for (int i=0;i<sent.length;++i){			
			if (g.isVariable(sent[i].trim())){
				
				findVar=1;
				Rule rule=new Rule();
				rule.setLeftSide(sent[i]);
				
				Rule[] options=g.findSameLeft(rule);
				//select one rule from options:
				int c;
				Random select=new Random();
				
				float w = g.getWeight(sent[i]);
				float point= select.nextFloat()*w;
				float sum=options[0].getNum();
				c=0;
				while (sum<=point){
					c++;
					sum+=options[c].getNum();
				}
					
				//creating new sentence by replace variable with right side of rule
				
				sent[i]="("+sent[i]+"\n";
				for (int k=0;k<space;++k)
					sent[i]+="#";
				sent[i]+=" ";
				for (int k=0;k<options[c].getRightSide().length;++k)
					sent[i]=sent[i]+ options[c].getRightSide()[k]+" ";
				sent[i]+="\n";
				for (int k=0;k<(space-1);++k)
					sent[i]+="#";
				sent[i]+= ")\n";
				for (int k=0;k<(space-1);++k)
					sent[i]+="#";
				
							
			}
		}
//		 recursive call for replacing next variable
		
		for (int i=0;i<sent.length;++i)
			newSent+=sent[i]+" ";
		if(findVar==0){
			return s.replaceAll("#","      ");
			
		}
		else
		{
			
			return treeDerivation(newSent,g,space+1);
		}
	}
	
	public String createTree(Grammer g){
		String s=treeDerivation(g.start,g,1); 
		return s;
	}
	
	private String bracketDerivation(String s,Grammer g){
		String[] sent=s.split(" ");
		for (int i=0;i<sent.length;++i){
			if (g.isVariable(sent[i])){
				//find rules with same leftside:
				Rule rule=new Rule();
				rule.setLeftSide(sent[i]);
				Rule[] options=g.findSameLeft(rule);
				
				//select one rule from options:
				int c;
				Random select=new Random();
				float w= g.getWeight(sent[i]);
				float point= select.nextFloat()*w;
				float sum=options[0].getNum();
				c=0;
				while (sum<=point){
					c++;
					sum+=options[c].getNum();
				}
					
				
				//creating new sentence by replace variable with right side of rule
				String newSent="";
				for (int k=0;k<i;++k)
					newSent=newSent+sent[k]+" ";
				String right="";
				for (int k=0;k<options[c].getRightSide().length;++k){
					right+=options[c].getRightSide()[k]+" ";					
				}
				if (right.trim().equals("VP and VP")||right.trim().equals("IntransVerb NP")||right.trim().equals("PrepVerb PP")||right.trim().equals("ThatVerb that S"))
					newSent+=" [ "+right+" ] ";
				
				else if (right.trim().equals("NP and NP")||right.trim().equals("NP PP"))
					newSent+=" { "+right+" } ";
				
				else if (right.trim().equals("Adv Adj Noun")||right.trim().equals("Adj Noun"))
					newSent+=" ( "+right+" ) ";
				else
					newSent+=right;
				
				for (int k=i+1;k<sent.length;++k)
					newSent=newSent+sent[k]+" ";
								
				// recursive call for replacing next variable
				return bracketDerivation(newSent,g);
				
			}
		}
		return s;		
	}
	
	public String createBracketedSent(Grammer g){
		
		String s=bracketDerivation(g.start,g); 
		return s;
	}
	
	public SentenceGenerator() {
		super();
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Grammer g=new Grammer();
		g.getFromFile("test3.txt");
		
		SentenceGenerator sg= new SentenceGenerator();
		String s=sg.createBracketedSent(g);
		System.out.println(s);
	}
}
