package com.hnjz.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;

import org.apache.commons.beanutils.BeanUtils;

import com.hnjz.app.data.po.TDataLawobj;

/**
 * Bean工具
 * @author zn
 * @version $Id: BeanUtil.java, v 0.1 2013-5-6 上午02:44:53 zn Exp $
 */
public class BeanUtil {

    /**
     * 属性拷贝
     * @param obj1
     * @param obj2
     * @return
     * @throws Exception
     */
    public static Object CopyBeanToBean(Object obj1, Object obj2) throws Exception {

        Method[] method1 = obj1.getClass().getMethods();

        Method[] method2 = obj2.getClass().getMethods();

        String methodName1;

        String methodFix1;

        String methodName2;

        String methodFix2;

        for (int i = 0; i < method1.length; i++) {

            methodName1 = method1[i].getName();

            methodFix1 = methodName1.substring(3, methodName1.length());

            if (methodName1.startsWith("get")) {

                for (int j = 0; j < method2.length; j++) {

                    methodName2 = method2[j].getName();

                    methodFix2 = methodName2.substring(3, methodName2.length());

                    if (methodName2.startsWith("set")) {

                        if (methodFix2.equals(methodFix1)) {

                            Object[] objs1 = new Object[0];

                            Object[] objs2 = new Object[1];

                            objs2[0] = method1[i].invoke(obj1, objs1);//激活obj1的相应的get的方法，objs1数组存放调用该方法的参数,此例中没有参数，该数组的长度为0

                            if (objs2[0] != null && !objs2[0].getClass().equals(Timestamp.class)) {
                                method2[j].invoke(obj2, objs2);//激活obj2的相应的set的方法，objs2数组存放调用该方法的参数
                            }
                            continue;

                        }

                    }

                }

            }

        }

        return obj2;

    }


    /**
     * 
     * 函数介绍：通过执法对象列名获得对应列的值
    
     * 输入参数：执法对象列名
    
     * 返回值：
     */
    public static Object getLawobjAttrValue(TDataLawobj lawobj,String columnName){
    	try {
			PropertyDescriptor pd = new PropertyDescriptor(StringUtil.transColumnToAttr(columnName),lawobj.getClass());
			Method getMethod = pd.getReadMethod();
			Object obj = getMethod.invoke(lawobj);
			return obj;
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    	return null;
    }
}
