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
	 * @param 修改前执法对象信息obj1
	 * @param 修改后执法对象信息obj2
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
	                    String s1 = o1 == null ? "" : o1.toString();//避免空指针异常  
	                    String s2 = o2 == null ? "" : o2.toString();//避免空指针异常  
	                    //思考下面注释的这一行：会bug的，虽然被try catch了，程序没报错，但是结果不是我们想要的  
	                    //if (!o1.toString().equals(o2.toString())) {  
	                    if (!s1.equals(s2)) {  
	                        textList.add( field.getName() + "[修改前:" + s1 + ",修改后:" + s2 + "] ");  
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
	     * @description 转换javabean ,将class2中的属性值赋值给class1，如果class1属性有值，则不覆盖
	     *              ，前提条件是有相同的属性名
	     *              </p>
	     * @param class1
	     *            基准类,被赋值对象
	     * @param class2
	     *            提供数据的对象
	     * @throws Exception
	     * @author ex_dingyongbiao
	     * @see
	     */
	    public static void converJavaBean(Object class1, Object class2) {
	        try {
	            Class<?> clazz1 = class1.getClass();
	            Class<?> clazz2 = class2.getClass();
	            // 得到method方法
	            Method[] method1 = clazz1.getMethods();
	            Method[] method2 = clazz2.getMethods();

	            int length1 = method1.length;
	            int length2 = method2.length;
	            if (length1 != 0 && length2 != 0) {
	                // 创建一个get方法数组，专门存放class2的get方法。
	                Method[] get = new Method[length2];
	                for (int i = 0, j = 0; i < length2; i++) {
	                    if (method2[i].getName().indexOf("get") == 0) {
	                        get[j] = method2[i];
	                        ++j;
	                    }
	                }

	                for (int i = 0; i < get.length; i++) {
	                    if (get[i] == null)// 数组初始化的长度多于get方法，所以数组后面的部分是null
	                        continue;
	                    // 得到get方法的值，判断时候为null，如果为null则进行下一个循环
	                    Object value = get[i].invoke(class2, new Object[] {});
	                    if (null == value)
	                        continue;
	                    // 得到get方法的名称 例如：getXxxx
	                    String getName = get[i].getName();
	                    // 得到set方法的时候传入的参数类型，就是get方法的返回类型
	                    Class<?> paramType = get[i].getReturnType();
	                    Method getMethod = null;
	                    try {
	                        // 判断在class1中时候有class2中的get方法，如果没有则抛异常继续循环
	                        getMethod = clazz1.getMethod(getName, new Class[] {});
	                    } catch (NoSuchMethodException e) {
	                        continue;
	                    }
	                    // class1的get方法不为空并且class1中get方法得到的值为空，进行赋值，如果class1属性原来有值，则跳过
	                    if (null == getMethod || null != getMethod.invoke(class1, new Object[] {}))
	                        continue;
	                    // 通过getName 例如getXxxx 截取后得到Xxxx，然后在前面加上set，就组装成set的方法名
	                    String setName = "set" + getName.substring(3);
	                    // 得到class1的set方法，并调用
	                    Method setMethod = clazz1.getMethod(setName, paramType);
	                    setMethod.invoke(class1, value);
	                }
	            }
	        } catch(Exception e) {
	        	System.out.println(e);
	        }
	    }

}
