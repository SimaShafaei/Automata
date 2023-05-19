import java.util.Random;

public class Grammer {

	Rule[] grammer= new Rule[1000];
	int ruleNum=0;
	String start="ROOT";
	
	public void getFromFile(String address){
		FileHandler fileHandler = new FileHandler();
        fileHandler.setFileName(address);
        String[] out = fileHandler.readLineByLine();
        for (int i = 0; i < fileHandler.getLineNumber(); ++i){
        	if (out[i].trim().length()>0 && !out[i].trim().startsWith("#")){// if this line is not comment 
        		out[i]=out[i].replaceAll("\t"," ");        		
        		if(out[i].indexOf("#")>0)
        			out[i]=out[i].substring(0,out[i].indexOf("#"));
        		String[] r=out[i].split(" ");
        		int j=0;
        		
        		
        		grammer[ruleNum]=new Rule();
        		if ((r[j].charAt(0)>='0' && r[j].charAt(0)<='9') || r[j].charAt(0)=='.'){
        			grammer[ruleNum].setNum(Float.parseFloat(r[j]));
        		}
        		
        		j++;
        		
        		grammer[ruleNum].setLeftSide(r[j]);
        		j++;
        		
        			
        		
        		
        		String[]rightS=new String[r.length-2];
        		int[]rightT=new int[r.length-2];
        		
        		for (int k=0;j<r.length;j++,k++) {
        			rightS[k]=r[j];
        			char first=rightS[k].charAt(0);
        			/*if (first>='A' && first <='Z')
        				rightT[k]=1;
        			else
        				rightT[k]=0;*/
        		}
        			
        		grammer[ruleNum].setRightSide(rightS);
        		
        		ruleNum++;
        		
        	}
        }
           
	}
	
	public void addRule(Rule r){
		
	}
	
	public void deleteRule(Rule r){
		
	}
	
	public float getWeight(String left){
		float w=0;
		for (int i=0;i<ruleNum;++i){			
			if (left.trim().equals(grammer[i].getLeftSide())){
				w+=grammer[i].getNum();
			}
		}
		return w;
	}
	
	private int numberOfSameLeft(String left){
		int n=0;
		for (int i=0;i<ruleNum;++i){			
			if (left.trim().equals(grammer[i].getLeftSide())){
				n++;
			}
		}
		return n;
	}

	public Rule[] findSameLeft(Rule r){
		
		int n=numberOfSameLeft(r.getLeftSide());
				
		Rule[] same=new Rule[n];
		for (int i=0,k=0;i<ruleNum;++i){			
			if (r.getLeftSide().trim().equals(grammer[i].getLeftSide())){
				same[k]=grammer[i];
				k++;
			}
		}
		return same;
	}

	public void printAllRules(){
		for (int i=0;i<ruleNum;++i){
			String s=grammer[i].getNum()+" ";
			s=s+grammer[i].getLeftSide()+" --> ";
			for (int k=0;k<grammer[i].getRightSide().length;++k)
				s=s+grammer[i].getRightSide()[k]+"  ";
			
			System.out.println(s);
		}
	}
	
	public void printRules(Rule[] rules){
		for (int i=0;i<rules.length;++i){
			String s=rules[i].getNum()+" ";
			s=s+rules[i].getLeftSide()+" --> ";
			for (int k=0;k<rules[i].getRightSide().length;++k)
				s=s+rules[i].getRightSide()[k]+"  ";
			
			System.out.println(s);
		}
	}
	
	public boolean isVariable(String a){
		
		Rule rule=new Rule();
		rule.setLeftSide(a);
		Rule[] r=findSameLeft(rule);
		
		if (r.length>0)
			return true;
		else 
			return false;
		
		
		/*
		char first=a.charAt(0);
		if (first>='A' && first <='Z')
			return true;
		else
			return false;
		*/
	}
	public Grammer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grammer g=new Grammer();
		g.getFromFile("test.txt");
		//g.printAllRules();
		Rule r=new Rule();
		r.setLeftSide("ROOT");
		
		Rule[] ruls=g.findSameLeft(r);
		g.printRules(ruls);
		
		float w=g.getWeight("Noun");
		
		Random select=new Random();
		
		System.out.println("\n\n___________  "+ select.nextFloat()*w);

	}

}
