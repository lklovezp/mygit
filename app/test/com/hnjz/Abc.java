package com.hnjz;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class Abc {
	static int QIDONGYUEFEN =2; 
	static int QIYESHULIANG = 300;
	public static void main(String[] args) {
		//格式：Map<月份,HashSet<企业ID>>
		Map<String, HashSet<String>> inMap = new HashMap<String, HashSet<String>>();
		Map<String, HashSet<String>> outMap = new HashMap<String, HashSet<String>>();

		/**
		 * 第一步：初始化过程
		 */


		//初始化输入Map
		//把所有生产月份是“启动月份及以后月份”的企业查出来
		for(int i = QIDONGYUEFEN;i<13;i++)
		{
			inMap.put(i+"月", initSet(QIYESHULIANG));
		}
		printMap(inMap);
		
		
		//初始化输出Map
		//要把所有“设置生产月份”并且“本年度已经监察过”的企业查出来
		initSet_out(outMap,inMap,QIYESHULIANG);
		System.out.println("初始化outMap");
		printMap(outMap);
		/**
		 * 第二步：处理过程
		 */
		while(true)
		{
			//得出最少企业的月份
			String yuefen=getyuefen_zuishao(inMap,outMap);
			if(inMap.get(yuefen) ==null)
			{
				break;
			}
			
			String qiye =inMap.get(yuefen).iterator().next();
			
			//去除掉所有月份中的该企业
			queding_qiyejianchayufen(outMap,inMap,qiye,yuefen);
		}
		
		
		/**
		 * 第三步：输出过程
		 */
		System.out.println("处理后");
		printMap(outMap);
		printMaplength(outMap);
	}
	
	/**
	 * 随机初始化企业信息
	 * @param l  初始化企业数量
	 * @return
	 */
	public static HashSet<String> initSet(int l){
		
		HashSet<String> set = new HashSet<String>();
		
		for(int i =1;i<=l;i++)
		{
			if(new Random().nextBoolean())
			{
				set.add(i+"企业");
			}
		}
		return set;
	}
	
	
	/**
	 * 随机初始化企业信息
	 * @param l  初始化企业数量
	 * @return
	 */
	public static void initSet_out(Map<String,HashSet<String>> outMap,Map<String,HashSet<String>> inMap,int l){
		

		for(int i =1;i<13;i++){
			
			HashSet<String> set = new HashSet<String>();
			outMap.put(i+"月", set);
		}
		for(int i =1;i<=l;i++)
		{

			if(new Random().nextBoolean())
			{
				int a = new Random().nextInt(QIDONGYUEFEN-1)+1;
				queding_qiyejianchayufen(outMap,inMap,i+"企业",a+"月");
				
			}

			
		}
	}
	
	public static void queding_qiyejianchayufen(Map<String,HashSet<String>> outMap,Map<String,HashSet<String>> inMap,String qiyeid,String yefen)
	{
		outMap.get(yefen).add(qiyeid);
		//去除掉所有月份中的该企业
		for(String a:inMap.keySet())
		{
			inMap.get(a).remove(qiyeid);
		}
	}
	
	
	public static void printMap(Map<String,HashSet<String>> map){
		
		for(String k:map.keySet())
		{
			System.out.print(k+":");
			for(String l:map.get(k))
			{
				System.out.print(l+",");
			}
			System.out.println();
		}
	}
	public static void printMaplength(Map<String,HashSet<String>> map){
		
		for(String k:map.keySet())
		{
			System.out.print(k+":"+map.get(k).size());
			
			System.out.println();
		}
	}
	
	/**
	 * 找出目前最少企业的月份，并返回
	 * @param map
	 * @param outMap
	 * @return 最少月份的key
	 */
	public static String getyuefen_zuishao(Map<String,HashSet<String>> map,Map<String,HashSet<String>> outMap){
		

		String min = "";
		int maxsize=Integer.MAX_VALUE;
		for(String k:map.keySet())
		{
			int sizeall =map.get(k).size()+outMap.get(k).size();
			int size =map.get(k).size();
			if(k.equals("2月"))
			{
				sizeall = sizeall*2;
			}
//			System.out.println("size:"+size);
			if(size!=0 && sizeall<maxsize)
			{
				min = k;
				maxsize =sizeall;
			}
			
		}
		return min;
	}
	
}
