package mylist;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
	public class DateTime implements IDateTime,Serializable,Cloneable,Comparable<DateTime> {
		/**
		 * clone与new+异常+索引+equal与=+拷贝+序列化
		 */
		private static final long serialVersionUID = -6874281979398820235L;
		private  int year;
		private  int month;
		private  int day;
		private  int hour;
		private  int minute;
		private  int seconds;
		private String dateformat;
		
		public boolean checkDate() {
			if(year>9999||year<0||month>12||month<0||day>31||day<0||hour<0||hour>24||minute>60||minute<0||seconds>60||seconds<0)
				return true;
			else return false;
		}
		
		DateTime() {
			Calendar temp=Calendar.getInstance();
		    year=temp.get(Calendar.YEAR);
		    month=temp.get(Calendar.MONTH)+1;
		    day=temp.get(Calendar.DATE);
		    hour=temp.get(Calendar.HOUR_OF_DAY);
		    minute=temp.get(Calendar.MINUTE);
		    seconds=temp.get(Calendar.SECOND);
		}
		
		
		DateTime(int year,int month,int day,int hour,int minute,int seconds)throws Exception {
			this.year=year;
			this.month=month;
			this.day=day;
			this.hour=hour;
			this.minute=minute;
			this.seconds=seconds;
			if(checkDate()==true) 
				throw new Exception("日期输入错误！");
		}
		
		
		
		DateTime(int year,int month,int day)throws Exception{
			this.year=year;
			this.month=month;
			this.day=day;
			if(checkDate()==true) 
				throw new Exception("日期输入错误！");
		}
		
		DateTime(String date)throws Exception{
			int len=date.length();
			String s=date.substring(0, 4);
			this.year=Integer.parseInt(s);
			s=date.substring(5, 7);
			this.month=Integer.parseInt(s);
			s=date.substring(8, 10);
			this.day=Integer.parseInt(s);
			if(len>10) {
				s=date.substring(11,13);
				this.hour=Integer.parseInt(s);
				s=date.substring(14,16);
				this.minute=Integer.parseInt(s);
				s=date.substring(17,19);
				this.seconds=Integer.parseInt(s);
			}
			if(checkDate()==true) 
				throw new Exception("日期输入错误！");
		}
		
		public int getYear() {
			return year;
		}
		
		public int getMonth() {
			return month;
		}

		public int getDay() {
			return day;
		}

		public int getHour() {
			return hour;
		}

		public int getMinute() {
			return minute;
		}

		public int getSecond() {
			return seconds;
		}
		
		public void addDays(int days)   {
			this.day+=days;
			int sumDays;
			try {
				sumDays = getDaysInMonth(this.year, this.month);
				if(this.day>sumDays) {
					this.month+=day/sumDays;
					this.day%=sumDays;
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		public void addMonths(int months) {
			this.month+=months;
			if(this.month>12) {
				this.year+=month/12;
				this.month%=12;
			}
		}

		public void addYears(int years) {
			this.year+=years;
		}

		public void addHour(int hour) {
			this.hour+=hour;
			if(this.hour>24) {
				this.day+=hour/24;
				this.hour%=24;
			}
		}

		public void addMinute(int minute) {
			this.minute+=minute;
			if(this.minute>60) {
				this.hour+=minute/60;
				this.minute%=60;
			}
		}

		public void addSecond(int second) {
			this.seconds+=second;
			if(this.seconds>60) {
				this.minute+=second/60;
				this.seconds%=60;
			}
		}
		
		
		/*
		 * 设定日期格式：例(yyyy-MM-dd)
		 */
		public void setDateFormat(String format) {
			dateformat=format;
		}

		/*
		 * 设定日期时间格式，例(yyyy-MM-dd HH:mm:ss)
		 */
		public void setDateTimeFormat(String format) {
			dateformat=format;
		}
		
		/*
		 * 按照dateFormat输出当前日期
		 */
		public String dateToString() {
			String temp=null;
			String m=String.format("%02d", this.month);
			String d=String.format("%02d", this.day);
			temp=dateformat.replace("yyyy",Integer.toString(this.year)).replace("MM",m ).replace("dd", d);
			return temp;
		}

		/*
		 * 按照dateTimeFormat输出当前日期时间
		 */
		public String dateTimeToString() {
			String temp=null;
			String m=String.format("%02d", this.month);
			String d=String.format("%02d", this.day);
			String h=String.format("%02d", this.hour);
			String mi=String.format("%02d", this.minute);
			String s=String.format("%02d", this.seconds);
			temp=dateformat.replaceAll("yyyy", Integer.toString(this.year)).replaceAll("MM", m).replaceAll("dd", d)
					.replaceAll("HH", h).replaceAll("mm", mi).replaceAll("ss", s);
			return temp;
		}

		/*
		 * 判断自己是否是闰年ֵ
		 */
		public static boolean isThisLeapYear(int year)throws Exception{
			if(year<0||year>9999)throw new Exception("年份超限！");
			if((year%400==0)||(year%4==0&&year%100!=0))
				return true;
			else 
				return false;
		}

		/*
		 * 获取指定年月的天数
		 */
		public int getDaysInMonth(int year, int month)throws Exception {
			if(year<0||year>9999||month<0||month>12)
				throw new Exception("年份或月份有误！");
			boolean flag=false;
			if(month==2) {
				if((year%400==0)||(year%4==0&&year%100!=0)) return 29;
			    else  return 28;
			}else {
				switch (month) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					flag=true;
					break;
				default:
					break;
				}
			}
			if(flag==true)return 31;
			else return 30;
		}

		/*
		 * 得到俩个日期间的间隔天数
		 */
		public static int getTwoDay(DateTime date1, DateTime date2){
			//判断日期较靠后者
			DateTime max=date2;
			if(date1.compareTo(date2)==0)return 0;
			else if(date1.compareTo(date2)==-1) {
				date1=date2;
				date1=max;
			}
			
			//存入平年某个月与一月份以前的天数
			final int[] daysOfMonth= {0,31,59,90,120,151,181,212,243,273,304,334,365};
			int years=date1.year-date2.year;
			int months=daysOfMonth[date1.month]-daysOfMonth[date2.month];
			int days=date1.day-date2.day;
			//4年为一闰，100的倍数不为闰年，400的倍数再算为闰年  
			int totalDays=years*365+years/4-years/100+years/400+months+days;
			return totalDays;
			
		}

		

		/*
		 * 调用此方法的对象和指定日期是否相等
		 */
		public boolean equals(Object date) {
			boolean flag=false;
			try {
				DateTime tDate=(DateTime) date;//强制类型转换
				if(tDate.year==this.year&&tDate.month==this.month
						&&tDate.day==this.day&&tDate.hour==this.hour
						        &&tDate.minute==this.minute&&tDate.seconds==this.seconds)
					flag=true;
				else flag=false;
			}catch (ClassCastException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return flag;
		}

		public Object clone(){
			try{
				return super.clone();//深拷贝
			}catch (CloneNotSupportedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return -1;
		}
public static void main (String[] args) throws Exception {
	DateTime a =new DateTime(2017, 6, 19);
    //DateTime b=new DateTime(2018, 6, 19);
	a.setDateFormat("yyyy-MM-dd");
	a.dateToString();
    System.out.println(a.dateToString());
    System.out.println(a.getDay());
   /* List<DateTime> list = new ArrayList<DateTime>(); 
    list.add(new DateTime(1970,1,1,1,1,1));
    list.add(new DateTime());
    list.add(new DateTime(1999,9,9,9,9,9)); 
    Collections.sort(list);
    for(DateTime s:list) {
    	s.setDateFormat("yyyy-MM-dd");
    	System.out.println(s.dateToString());
    }*/
    
}
}

