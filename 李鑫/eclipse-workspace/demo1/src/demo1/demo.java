package demo1;

import java.util.Scanner;

public class demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		Long a=s.nextLong();
		if((getSize(a)>=13&&getSize(a)<=16)&&
				(isValid(sumOfDouble(a,getSize(a)),getOdd(a,getSize(a))))){
				System.out.println(a+"is valid");
			}else{
				System.out.println(a+"is invalid");
			}
	}
	/**判断卡号长度**/
	public static int getSize(Long a){
		int l=a.toString().length();
		return l;
	}
	/**Step 1&2**/
	public static int sumOfDouble(Long a,int a1){
		int sum=0;
		String s=a.toString();
		for(int i=a1-2;i>=0;i=i-1){
			int s1=s.charAt(i);
			int b=(s1-'0')*2;
			
			if(b>=10){
				int b1=b/10;
				sum=sum+b1+b-b1*10;
			}else{
				sum=sum+b;
			}
			
		}
		return sum;
	}
	/**step3**/
	public static int getOdd(Long a,int a1){
		int sum=0;
		String s=a.toString();
		for(int i=a1-1;i>=0;i=i-2){
			int s1=s.charAt(i);
			int b=(s1-'0');
			sum=sum+b;
		}
		return sum;
	}
	/**step final**/
	public static boolean isValid(int a,int b){
		if((a+b)%10==0){
			return true;
		}
		return false;
	}
}	