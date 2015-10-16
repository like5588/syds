package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomText {
	
	public static void main(String args[]){
		int a =0;
		int b =0;
		int c =0;
		int d =0;
		int e =0;
		int f =0;
		int g =0;
		int h =0;
		int k =0;
		int j =0;
//		for(int i =0 ;i<100;i++){
//			int r = (int)(Math.random()*10);
//			if(r == 0){
//				a += 1;
//			}
//			else if(r == 1){
//				b += 1;
//			}
//			else if(r == 2){
//				c += 1;
//			}
//			else if(r == 3){
//				d += 1;
//			}
//		}
//		
		
		int m = 0;
		Random ran = new Random();
		for(int i=0;i<1000;i++){
			double y = ran.nextDouble()*100;
			if(y<=0.1){
				System.out.println("-----------y="+y);
			}
			int r = ran.nextInt(1000);
			int n = ran.nextInt(1000);
			if(r == n){
				System.out.println(r+"...."+n);
				m += 1;
			}
			if(r == 0){
				a += 1;
			}
			else if(r == 1){
				b += 1;
			}
			else if(r == 2){
				c += 1;
			}
			else if(r == 3){
				d += 1;
			}
			else if(r == 4){
				e += 1;
			}
			else if(r == 5){
				f += 1;
			}
			else if(r == 6){
				g += 1;
			}
			else if(r == 7){
				h += 1;
			}
			else if(r == 8){
				k += 1;
			}
			else if(r == 9){
				j += 1;
			}
		}
		
		
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("c="+c);
		System.out.println("d="+d);
		System.out.println("e="+e);
		System.out.println("f="+f);
		System.out.println("g="+g);
		System.out.println("h="+h);
		System.out.println("k="+k);
		System.out.println("j="+j);
		System.out.println("m="+m);
		
		double x = 123.4d;
		System.out.println(Math.ceil(x));
		
		System.out.println(ran.nextDouble()*100);
	}

}
