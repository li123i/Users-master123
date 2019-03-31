package library;

import java.util.Scanner;

public class demo1 {
	public static void main(String[] args) {
	//// TODO Auto-generated method stub
		for(int i=2;i<100;i++) {
			if(judge(i)) {
				System.out.println(i);
			}
		}
		for(int i=2;i<100;i++) {
			if(judge(i,fanhui(i))) {
				System.out.println(i);
			}
		}
		for(int i=2;i<100;i++) {
			if(judge(i,fanhui(i),i)) {
				System.out.println(i);
			}
		}
		
	}
	public static boolean judge(int n) {
		for(int i=2;i<n;i++) {
			if(n%i==0) {
				return false;
			}
		}
		return true;
	}
	public static boolean judge(int n,int l) {
		if(n==l&&(judge(n))) {
			return true;
		}
		return false;
	}
	public static boolean judge(int n,int l,int m) {
		if(judge(n)&&judge(l)) {
			return true;
		}
		return false;
	}
	public static int fanhui(int n) {
		int s=n;
		int y=0;
		while(s>0) {
			y=y*10+s%10;
			s=s/10;
		}
		return y;
	}
}
