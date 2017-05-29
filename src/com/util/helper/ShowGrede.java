package com.util.helper;

public class ShowGrede {
	public static String getGradePath(float score){
		int i=(int) (score/10);
		if(i==0){
			return "image/grade/1.gif";
		}else if(i>10){
			return "image/grade/10.gif";
		}
		else{
			return "image/grade/"+i+".gif";
		}
	}

}
