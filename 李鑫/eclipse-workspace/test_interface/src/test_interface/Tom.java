package test_interface;

public class Tom implements test{

	@Override
	public int getSum(int x) {
		// TODO Auto-generated method stub
		int sum=0;
		for(int i=1;i<=x;i++) {
			sum+=i;
		}
		return sum;
	}
	

}
