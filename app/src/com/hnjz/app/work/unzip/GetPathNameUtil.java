package com.hnjz.app.work.unzip;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.hnjz.common.util.FileZipUtil;

public class GetPathNameUtil {
	
/*
 * 获取下级目录文件夹的name
 * */	
	public static String getSingleName(String path){
		//根据绝对路劲获取路径下的名字
				File file=new File(path);		   
				File test[]=file.listFiles();
				
				if(test.length<1){
					return null;
				}else{
					return test[0].getName();
				}
				
		
	};
	/*
	 * 获取下级目录文件夹的据对路径
	 * */	
	public static String getNextPath(String path,String name){
		
		
		return  path+"\\"+name;
		
		
	};
	
	/*
	 * 获取当前目录下的所有文件名以及文件的据对路径
	 * */
	public static Map<String,String> getFilesNameAndPath(File files[],String path){
		Map<String,String> hashMap=new HashMap<String, String>();
		for(int i=0;i<files.length;i++){
			
			if(!files[i].getName().contains(".db")){
				hashMap.put(files[i].getName(), getNextPath(path,files[i].getName()));
			
			}
		}
		
		return hashMap;
	};
	
	
	
	 public static void main(String[] args) throws IOException {
	    	//FileZipUtil zip = new FileZipUtil();
		 GetPathNameUtil getPathNameUtil=new GetPathNameUtil();
	    	String outputPath="Y:\\xxgl\\a42f879a8b544989bcd624a3de6a\\新疆企业\\1工商营业执照";
	    	
	    	//outputPath=zip.unZip("Y:\\xxgl\\a42f879a8b544989bcd624a3de6a.zip","Y:\\xxgl");
	    	
	    	//根据绝对路劲获取路径下的名字
			
			String pathName=getPathNameUtil.getSingleName(outputPath);
			 //获取下级目录的据对路径
			//String nextPath=getPathNameUtil.getNextPath(outputPath,pathName);
			//获取下级目录的所有文件夹名字和绝对路径
			File file=new File(outputPath);
			if (!file.exists())
			{		       
			        return;
			}
			File[] files=file.listFiles();
			
			//System.out.println(getPathNameUtil.getFilesNameAndPath(files, outputPath).toString());
			
			
			
			
			
			
			
			
			
//			for(int i=0;i<files.length;i++){
//				
//				
//				System.out.println("所有文件夹名"+files[i].getName()+"绝对路径"+getPathNameUtil.getNextPath(nextPath,files[i].getName())+"\r");
//			}			 	
//			
//			System.out.println("解压文件的路径："+outputPath+"下级文件夹名："+pathName+"下级文件夹路径"+nextPath);
			
			
		}

}
