
public class Tree {

	public Node root;
	
	public Tree(String value, boolean var) {
		root= new Node(value, var);
	}
	
	public void setValue(Node a, String val, boolean variable){
		a.setValue(val);
		a.setVar(variable);
	}
	
	public void insertChild(Node father, Node a ,String pos){
		if (pos.trim().equalsIgnoreCase("left"))
			father.setLchild(a);
		else
			father.setRchild(a);
	}
	
	public void remove(Node a,String pos){
		if (pos.trim().equalsIgnoreCase("left"))
			a.setLchild(null);
		else
			a.setRchild(null);
		
	}
	
	private String inorder(Node a){
		String p="";
		if (a!=null){
			p+="("+inorder(a.getLchild());
			p+=" "+a.getValue();
			p+=" "+inorder(a.getRchild())+")";
		}
		return p;
	}
	
	public String inorder(){
		return inorder(root);
	}
	
	private String preorder(Node a, int level){
		String p="";
		if (a!=null){
			p+=a.getValue();
			
			p+="\n";
			for(int i=0;i<level+1;++i) p+="\t";
			p+=preorder(a.getLchild(),level+1);
			
			p+="\n";
			for(int i=0;i<level+1;++i) p+="\t";
			p+=preorder(a.getRchild(),level+1);
		}
		return p;
	}
	
	public String preorder(){
		return preorder(root,0);
	}
	
	private String postorder(Node a){
		String p="";
		if (a!=null){
			p+="("+postorder(a.getLchild());
			p+=" "+postorder(a.getRchild());
			p+=" "+a.getValue()+")";
		}
		return p;
	}
	
	public String postorder(){
		return postorder(root);
	}
	
	public Node search(String value){
		
		return null;
	}
	
	public static void main(String[] args) {
		Tree t=new Tree("ROOT",true);
		Node a=new Node ("NP",true);
		Node b=new Node ("VP",true);
		Node c= new Node ("a",false);
		Node d=new Node ("ball",false);
		t.insertChild(t.root,a,"left");
		t.insertChild(a,b,"right");
		t.insertChild(a,c,"left");
		t.insertChild(c,d,"right");
		String s=t.preorder();
		System.out.println(s);
		s=t.postorder();
		System.out.println(s);
		s=t.inorder();
		System.out.println(s);
	}

}
