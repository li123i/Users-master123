package mylist;

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
        System.out.println("���������鳤�ȣ�");
        int l;
        l=cin.nextInt();
        int [] a=new int [100];
        for(int i=0;i<l;i++)
        {
      	 a[i]=cin.nextInt();
        }
        System.out.println("����������д���ĳ��Ⱥ����֣�");
        int length;
        length=cin.nextInt();
        int [] a1=new int [length];
        List p=new List(length,l);
        for(int i=0;i<length;i++)
        {
      	  a1[i]=cin.nextInt();
        }
        for(int i=0;i<length;i++) {
        	p.push(a1[i]);
        }
        
        /*��ȡ list �� index ��Ԫ��*/
        int idex1;
        System.out.println("������ڼ���Ԫ��:");
        idex1=cin.nextInt();
        p.get(idex1);
        
        /*��ȡ list ���Ƿ����ĳ��Ԫ��*/
	    int t;
	    t=cin.nextInt();
	    p.contains(t);
	    
	    /*��ȡ list �Ƿ�������ϵ�����Ԫ��*/
	    p.containsAll(a);
	    
	    /*��ȡ list ��Ԫ�ظ���*/
        System.out.println(p.num);
        
        /*��ȡһ��Ԫ�غ� list һ��������  */
        System.out.println("��listһ�������� :");
        for(int i=0;i<length;i++) {
        	 p.toArray();
        }
        System.out.print(" ");
        /*��ȡ list �Ƿ��*/
        p.isEmpty();
        
        /*�� list �����һ��Ԫ��*/
        int number1;
        System.out.print("��ӵ���Ԫ��Ϊ��");
        number1=cin.nextInt();
        System.out.println("���֮���������Ϊ��");
        for(int i=0;i<length;i++){
      	  p.push(a1[i]);
        }
        p.add(number1);
        
        /*��list �����һ��Ԫ��ָ�����뵽��index λ��*/
        int index1;int e;
        System.out.println("���λ��Ϊ��");
        index1=cin.nextInt();
        System.out.println("��ӵ�����Ϊ��");
        e=cin.nextInt();
        p.add(index1,e);
        
        /*�� e ������Ԫ����ӵ� list ��*/
        p.addAll(a);
        
        /*�� list ��ɾ����index ��λ�õ�Ԫ��*/
        int index3;
        index3=cin.nextInt();
        p.remove(index3);
        
        /*���� list ��ÿһ��Ԫ����ɵ��ַ���*/
        p.String();
        }
        
        
   }

