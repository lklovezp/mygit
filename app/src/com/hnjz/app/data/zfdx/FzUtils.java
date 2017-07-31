package com.hnjz.app.data.zfdx;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.hnjz.Car;

public class FzUtils {
	/**
	 * 
	 * @param �޸�ǰִ��������Ϣobj1
	 * @param �޸ĺ�ִ��������Ϣobj2
	 * @return
	 */
	  public static String contrastObj(Object obj1, Object obj2) {  
		     String notValue="";
	        if (obj1 instanceof Object && obj2 instanceof Object) {  
	            Object pojo1 = (Object) obj1;  
	            Object pojo2 = (Object) obj2;  
	            List<String> textList = new ArrayList<String>();  
	            try {  
	                Class clazz = pojo1.getClass();  
	                Field[] fields = pojo1.getClass().getDeclaredFields();  
	                for (Field field : fields) {  
	                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);  
	                    Method getMethod = pd.getReadMethod();  
	                    Object o1 = getMethod.invoke(pojo1);  
	                    Object o2 = getMethod.invoke(pojo2);  
	                    String s1 = o1 == null ? "" : o1.toString();//�����ָ���쳣  
	                    String s2 = o2 == null ? "" : o2.toString();//�����ָ���쳣  
	                    //˼������ע�͵���һ�У���bug�ģ���Ȼ��try catch�ˣ�����û���������ǽ������������Ҫ��  
	                    //if (!o1.toString().equals(o2.toString())) {  
	                    if (!s1.equals(s2)) {  
	                        textList.add( field.getName() + "[�޸�ǰ:" + s1 + ",�޸ĺ�:" + s2 + "] ");  
	                    }  
	                }  
	            } catch (Exception e) {  
	                System.out.println(e.getMessage());  
	            }  
	           
	            for (Object object : textList) {  
	                 notValue +=object;
	            }  
	        }
			return notValue;  
	    } 
	  
	  
		/**
	     * 
	     * <p>
	     * 
	     * @description ת��javabean ,��class2�е�����ֵ��ֵ��class1�����class1������ֵ���򲻸���
	     *              ��ǰ������������ͬ��������
	     *              </p>
	     * @param class1
	     *            ��׼��,����ֵ����
	     * @param class2
	     *            �ṩ���ݵĶ���
	     * @throws Exception
	     * @author ex_dingyongbiao
	     * @see
	     */
	    public static void converJavaBean(Object class1, Object class2) {
	        try {
	            Class<?> clazz1 = class1.getClass();
	            Class<?> clazz2 = class2.getClass();
	            // �õ�method����
	            Method[] method1 = clazz1.getMethods();
	            Method[] method2 = clazz2.getMethods();

	            int length1 = method1.length;
	            int length2 = method2.length;
	            if (length1 != 0 && length2 != 0) {
	                // ����һ��get�������飬ר�Ŵ��class2��get������
	                Method[] get = new Method[length2];
	                for (int i = 0, j = 0; i < length2; i++) {
	                    if (method2[i].getName().indexOf("get") == 0) {
	                        get[j] = method2[i];
	                        ++j;
	                    }
	                }

	                for (int i = 0; i < get.length; i++) {
	                    if (get[i] == null)// �����ʼ���ĳ��ȶ���get�����������������Ĳ�����null
	                        continue;
	                    // �õ�get������ֵ���ж�ʱ��Ϊnull�����Ϊnull�������һ��ѭ��
	                    Object value = get[i].invoke(class2, new Object[] {});
	                    if (null == value)
	                        continue;
	                    // �õ�get���������� ���磺getXxxx
	                    String getName = get[i].getName();
	                    // �õ�set������ʱ����Ĳ������ͣ�����get�����ķ�������
	                    Class<?> paramType = get[i].getReturnType();
	                    Method getMethod = null;
	                    try {
	                        // �ж���class1��ʱ����class2�е�get���������û�������쳣����ѭ��
	                        getMethod = clazz1.getMethod(getName, new Class[] {});
	                    } catch (NoSuchMethodException e) {
	                        continue;
	                    }
	                    // class1��get������Ϊ�ղ���class1��get�����õ���ֵΪ�գ����и�ֵ�����class1����ԭ����ֵ��������
	                    if (null == getMethod || null != getMethod.invoke(class1, new Object[] {}))
	                        continue;
	                    // ͨ��getName ����getXxxx ��ȡ��õ�Xxxx��Ȼ����ǰ�����set������װ��set�ķ�����
	                    String setName = "set" + getName.substring(3);
	                    // �õ�class1��set������������
	                    Method setMethod = clazz1.getMethod(setName, paramType);
	                    setMethod.invoke(class1, value);
	                }
	            }
	        } catch(Exception e) {
	        	System.out.println(e);
	        }
	    }

}