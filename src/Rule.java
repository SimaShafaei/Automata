
public class Rule {
	
	private float num;
	private String leftSide;
	private String[] rightSide=new String [100];
	private int[] rightType=new int [100];   //1=variable and 0=constant
	
	public String getLeftSide() {
		return leftSide;
	}
	public void setLeftSide(String leftSide) {
		this.leftSide = leftSide;
	}
	public float getNum() {
		return num;
	}
	public void setNum(float num) {
		this.num = num;
	}
	public int[] getRightType() {
		return rightType;
	}
	public void setRightType(int[] rightType) {
		this.rightType = rightType;
	}
	public String[] getRightSide() {
		return rightSide;
	}
	public void setRightSide(String[] rightSide) {
		this.rightSide = rightSide;
	}
	
}
