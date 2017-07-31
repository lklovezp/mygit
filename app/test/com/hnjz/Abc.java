package com.hnjz;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class Abc {
	static int QIDONGYUEFEN =2; 
	static int QIYESHULIANG = 300;
	public static void main(String[] args) {
		//��ʽ��Map<�·�,HashSet<��ҵID>>
		Map<String, HashSet<String>> inMap = new HashMap<String, HashSet<String>>();
		Map<String, HashSet<String>> outMap = new HashMap<String, HashSet<String>>();

		/**
		 * ��һ������ʼ������
		 */


		//��ʼ������Map
		//�����������·��ǡ������·ݼ��Ժ��·ݡ�����ҵ�����
		for(int i = QIDONGYUEFEN;i<13;i++)
		{
			inMap.put(i+"��", initSet(QIYESHULIANG));
		}
		printMap(inMap);
		
		
		//��ʼ�����Map
		//Ҫ�����С����������·ݡ����ҡ�������Ѿ�����������ҵ�����
		initSet_out(outMap,inMap,QIYESHULIANG);
		System.out.println("��ʼ��outMap");
		printMap(outMap);
		/**
		 * �ڶ�������������
		 */
		while(true)
		{
			//�ó�������ҵ���·�
			String yuefen=getyuefen_zuishao(inMap,outMap);
			if(inMap.get(yuefen) ==null)
			{
				break;
			}
			
			String qiye =inMap.get(yuefen).iterator().next();
			
			//ȥ���������·��еĸ���ҵ
			queding_qiyejianchayufen(outMap,inMap,qiye,yuefen);
		}
		
		
		/**
		 * ���������������
		 */
		System.out.println("������");
		printMap(outMap);
		printMaplength(outMap);
	}
	
	/**
	 * �����ʼ����ҵ��Ϣ
	 * @param l  ��ʼ����ҵ����
	 * @return
	 */
	public static HashSet<String> initSet(int l){
		
		HashSet<String> set = new HashSet<String>();
		
		for(int i =1;i<=l;i++)
		{
			if(new Random().nextBoolean())
			{
				set.add(i+"��ҵ");
			}
		}
		return set;
	}
	
	
	/**
	 * �����ʼ����ҵ��Ϣ
	 * @param l  ��ʼ����ҵ����
	 * @return
	 */
	public static void initSet_out(Map<String,HashSet<String>> outMap,Map<String,HashSet<String>> inMap,int l){
		

		for(int i =1;i<13;i++){
			
			HashSet<String> set = new HashSet<String>();
			outMap.put(i+"��", set);
		}
		for(int i =1;i<=l;i++)
		{

			if(new Random().nextBoolean())
			{
				int a = new Random().nextInt(QIDONGYUEFEN-1)+1;
				queding_qiyejianchayufen(outMap,inMap,i+"��ҵ",a+"��");
				
			}

			
		}
	}
	
	public static void queding_qiyejianchayufen(Map<String,HashSet<String>> outMap,Map<String,HashSet<String>> inMap,String qiyeid,String yefen)
	{
		outMap.get(yefen).add(qiyeid);
		//ȥ���������·��еĸ���ҵ
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
	 * �ҳ�Ŀǰ������ҵ���·ݣ�������
	 * @param map
	 * @param outMap
	 * @return �����·ݵ�key
	 */
	public static String getyuefen_zuishao(Map<String,HashSet<String>> map,Map<String,HashSet<String>> outMap){
		

		String min = "";
		int maxsize=Integer.MAX_VALUE;
		for(String k:map.keySet())
		{
			int sizeall =map.get(k).size()+outMap.get(k).size();
			int size =map.get(k).size();
			if(k.equals("2��"))
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