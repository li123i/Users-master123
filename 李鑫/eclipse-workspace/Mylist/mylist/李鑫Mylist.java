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
        System.out.println("请输入数组长度：");
        int l;
        l=cin.nextInt();
        int [] a=new int [100];
        for(int i=0;i<l;i++)
        {
      	 a[i]=cin.nextInt();
        }
        System.out.println("请输入队列中储存的长度和数字：");
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
        
        /*获取 list 第 index 个元素*/
        int idex1;
        System.out.println("请输入第几个元素:");
        idex1=cin.nextInt();
        p.get(idex1);
        
        /*获取 list 中是否包含某个元素*/
	    int t;
	    t=cin.nextInt();
	    p.contains(t);
	    
	    /*获取 list 是否包含集合的所有元素*/
	    p.containsAll(a);
	    
	    /*获取 list 的元素个数*/
        System.out.println(p.num);
        
        /*获取一个元素和 list 一样的数组  */
        System.out.println("与list一样的数组 :");
        for(int i=0;i<length;i++) {
        	 p.toArray();
        }
        System.out.print(" ");
        /*获取 list 是否空*/
        p.isEmpty();
        
        /*向 list 中添加一个元素*/
        int number1;
        System.out.print("添加的新元素为：");
        number1=cin.nextInt();
        System.out.println("添加之后的新数组为：");
        for(int i=0;i<length;i++){
      	  p.push(a1[i]);
        }
        p.add(number1);
        
        /*向list 中添加一个元素指定插入到第index 位置*/
        int index1;int e;
        System.out.println("添加位置为：");
        index1=cin.nextInt();
        System.out.println("添加的数字为：");
        e=cin.nextInt();
        p.add(index1,e);
        
        /*将 e 中所有元素添加到 list 中*/
        p.addAll(a);
        
        /*从 list 中删除第index 个位置的元素*/
        int index3;
        index3=cin.nextInt();
        p.remove(index3);
        
        /*返回 list 中每一个元素组成的字符串*/
        p.String();
        }
        
        
   }

