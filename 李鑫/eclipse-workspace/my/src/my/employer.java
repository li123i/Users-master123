package my;

class Dept{
	private String name;
	private int age;
	private int grade;
	private String job;
	public Dept(String n,int m,int a,String str) {
		this.setName(n);
		this.setAge(m);
		this.setGrade(a);
		this.setjob(str);
	}
	public void setName(String n) {
		this.name=n;
	}
	public void setAge(int n) {
		this.age=n;
	}
	public void setGrade(int m)
	{
		this.grade=m;
	}
	public void setjob(String str) {
		this.job=str;
	}
public class employer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
