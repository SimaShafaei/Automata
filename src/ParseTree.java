import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

import java.lang.Object;
public class ParseTree {

	 
	public Node sentenceGenerator(Grammer g, Node r){
		
		String key=r.getValue();
		if (g.isVariable(key)){			
			Rule rule=new Rule();
			rule.setLeftSide(key);
			Rule[] options=g.findSameLeft(rule);
			
			//select one rule from options:
			int c;
			Random select=new Random();
			float w= g.getWeight(key);
			float point= select.nextFloat()*w;
			float sum=options[0].getNum();
			c=0;
			while (sum<=point){
				c++;
				sum+=options[c].getNum();
			}
			Node first=new Node(options[c].getRightSide()[0],g.isVariable(options[c].getRightSide()[0]));
			Node a=first;
			for (int k=1;k<options[c].getRightSide().length;++k){
				Node b=new Node(options[c].getRightSide()[k],g.isVariable(options[c].getRightSide()[k]));
				a.setRchild(sentenceGenerator(g,b));
				a=b;				
			}
			r.setLchild(sentenceGenerator(g,first));			
		}		
		return r;
	}
	
	public Tree sentenceGenerator(Grammer g){
		Tree t= new Tree("ROOT",true);
		sentenceGenerator(g,t.root);
		return t;
	}
	
	public String getSentence(Node a){
		String p="";
		if (a!=null){
			if (a.getLchild()==null)
				p+=a.getValue()+" ";
			p+=getSentence(a.getLchild());
			p+=getSentence(a.getRchild());
			
		}
		return p;	
	}
	
	public String getTreeRepresent(Node a,int level){
		String p="";
		if (a!=null){
			if (a.getLchild()!=null){
				for (int i=0;i<level;++i)  p+="    ";
				p+="("+a.getValue()+"\n";
				Node b=a.getLchild();
				while (b!=null){					
					p+=getTreeRepresent(b,level+1)+"\n";
					b=b.getRchild();
				}
				
				for (int i=0;i<level;++i)  p+="    ";
				p+=")";
			}
			else{
				for (int i=0;i<level;++i)  p+="    ";
				p+=a.getValue();//+"\n";
				
			}
			
		}
		return p;
	}
	
	public String getTreeRepresent(Tree t){
		return getTreeRepresent(t.root,0);
	}
	
	public String getHighlightedSentence(Tree t){
		return "";
	}
	
	public Tree[] parseSentence(String s){
		return null;
	}
	
	public ParseTree() {
		
	}

	public static void main(String[] args) {
		Grammer g=new Grammer();
		g.getFromFile("test4_a_an.txt");
		ParseTree pt= new ParseTree();
		for(int i=0;i<20;++i){
			Tree t=pt.sentenceGenerator(g);
			String s;
			s=pt.getSentence(t.root);
			System.out.println(s);
		}
		//s=pt.getTreeRepresent(t);
		//System.out.println(s);
	}

}
