package blog.csdn.net.unix21;
import java.util.Scanner;
    class test {
		public interface Mylist{
		          public abstract void get(int idex);
		          public abstract void contains(int t);
		          public abstract void containsAll(int a[]);
		          public abstract void toArray();
		          public abstract void isEmpty();
		          public abstract void add(int t);
		          public abstract void add(int index,int e);
		          public abstract void addAll(int a[]);
		          public abstract void remove(int index);
		          public abstract void String();
	}
        public static class List implements Mylist{
	    	      int length,l;
	    	      int num=0;
	    	   	  int front=0;
	    	   	  int area=0;
	    	      int [] str=new int [100];
	    	      public List(int l1,int l2) {
	    	    	  length=l1;
	    	    	  l=l2;
	    	      }
	    	      public void push(int a) {
	    	    	  front=(front+1)%100;
	    	    	  str[front]=a;
	    	    	  num++;
	    	      }
	    	      public void get(int idex) {
	    	    	  System.out.println(str[idex]);
	    	      }
	    	      public void contains(int t) {
	    	    	  int s=1;
	    	    	  for(int i=0;i<length;i++) {
	    	    		  if(str[i]==t) {
	    	    			  System.out.println("true");
	    	    			  s=0;
	    	    			  break;
	    	    		  }
	    	    	}
	    	    	  if(s==1)
	    	    	      System.out.println("false");
	    	      }
	    	      public void containsAll(int a[]) {
	    	    	  int s=1;
	    	    	  for(int i=0;i<length;i++){
	    	    		  if(str[i]!=a[i]) {
	    	    			  System.out.println("false");
	    	    			  s=0;
	    	    			  break;
	    	    		  }
	    	    		  
	    	    	  }
	    	    	  if(s==1) {
	    	    			  System.out.println("true");
	    	    		  }
	    	      }
	    	      public void toArray() {
	    	    	  area=(area+1)%100;
	    	    	  --num;
	    	    	  System.out.print(str[area]);
	    	      }
	    	      public void isEmpty() {
	    	    	  if(num==0) {
	    	    		  System.out.println("is empty");
	    	    	  }else {
	    	    		  System.out.println("is not empty");
	    	    	  }
	    	    	  
	    	      }
	    	      public void add(int t) {
	    	    	  length=length+1;
	    	    	  str[length]=t;
	    	    	  front=(front+1)%100;
	    	    	  for(int i=0;i<length;i++) {
	    	    		  System.out.print(str[i+1]);
	    	    	  }
	    	      }
	    	      public void add(int index,int e) {
	    	    	  length=length+1;
	    	    	  front=(front+1)%100;
	    	    	  for(int i=length;i>=index;i--) {
	    	    		  str[i]=str[i-1];
	    	    	  }
	    	    	  str[index]=e;
	    	    	  for(int i=0;i<length;i++) {
	    	    		  System.out.print(str[i+1]);
	    	    	  }  
	    	      }
	    	      public void addAll(int a[]) {
	    	    	  for(int i=length,j=0;j<l;i++,j++) {
	    	    		  str[i+1]=a[j];
	    	    	  }
	    	    	  length=length+l;
	    	    	  for(int i=0;i<length;i++) {
	    	    		  System.out.print(str[i+1]);
	    	    	  }
	    	      }
	    	      public void remove(int index) {
	    	    	  for(int i=index;i<length;i++) {
	    	    		  str[i]=str[i+1];
	    	    	  }
	    	    	  length=length-1;
	    	    	  for(int i=0;i<length;i++) {
	    	    		  System.out.print(str[i+1]);
	    	    	  }
	    	      }
	    	      public void String() {
	    	    	  System.out.print("[");
	    	    	  for(int i=0;i<length;i++) {
	    	    		  if(i==length-1) {
	    	    			  System.out.print(str[i+1]);
	    	    			  break;
	    	    		  }
	    	    		  System.out.printf("%d, ",str[i+1]);
	    	    	  }
	    	    	  System.out.println("]");
	    	      }
	 }
	public static void main(String[] args) {
		Scanner cin=new Scanner(System.in);
        int n=cin.nextInt();
        int []str=new int [n];
        for(int i=0;i<n;i++) {
        	str[i]=cin.nextInt();
        }
        for(int i=0;i<n;i++) {
        	System.out.println(str[i]);
        }
	}
    }

