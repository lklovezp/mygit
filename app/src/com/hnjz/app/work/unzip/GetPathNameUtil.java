package com.hnjz.app.work.unzip;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.hnjz.common.util.FileZipUtil;

public class GetPathNameUtil {
	
/*
 * ��ȡ�¼�Ŀ¼�ļ��е�name
 * */	
	public static String getSingleName(String path){
		//���ݾ���·����ȡ·���µ�����
				File file=new File(path);		   
				File test[]=file.listFiles();
				
				if(test.length<1){
					return null;
				}else{
					return test[0].getName();
				}
				
		
	};
	/*
	 * ��ȡ�¼�Ŀ¼�ļ��еľݶ�·��
	 * */	
	public static String getNextPath(String path,String name){
		
		
		return  path+"\\"+name;
		
		
	};
	
	/*
	 * ��ȡ��ǰĿ¼�µ������ļ����Լ��ļ��ľݶ�·��
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
	    	String outputPath="Y:\\xxgl\\a42f879a8b544989bcd624a3de6a\\�½���ҵ\\1����Ӫҵִ��";
	    	
	    	//outputPath=zip.unZip("Y:\\xxgl\\a42f879a8b544989bcd624a3de6a.zip","Y:\\xxgl");
	    	
	    	//���ݾ���·����ȡ·���µ�����
			
			String pathName=getPathNameUtil.getSingleName(outputPath);
			 //��ȡ�¼�Ŀ¼�ľݶ�·��
			//String nextPath=getPathNameUtil.getNextPath(outputPath,pathName);
			//��ȡ�¼�Ŀ¼�������ļ������ֺ;���·��
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
//				System.out.println("�����ļ�����"+files[i].getName()+"����·��"+getPathNameUtil.getNextPath(nextPath,files[i].getName())+"\r");
//			}			 	
//			
//			System.out.println("��ѹ�ļ���·����"+outputPath+"�¼��ļ�������"+pathName+"�¼��ļ���·��"+nextPath);
			
			
		}

}