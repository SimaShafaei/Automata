
public class Node {

	private boolean var;
	private String value;
	private Node rchild;
	private Node lchild;
	
	public Node(String value, boolean var) {
	      this.value = value;
	      this.var = var;
	      this.rchild=null;
	      this.lchild=null;
	}

	
	public Node getLchild() {
		return lchild;
	}


	public void setLchild(Node lchild) {
		this.lchild = lchild;
	}


	public Node getRchild() {
		return rchild;
	}


	public void setRchild(Node rchild) {
		this.rchild = rchild;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isVar() {
		return var;
	}

	public void setVar(boolean var) {
		this.var = var;
	}
	
	

	
}
