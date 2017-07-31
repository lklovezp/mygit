package com.hnjz.app.work.comUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import sun.misc.BASE64Encoder;

/**
 * 生成word文件
 * 
 * @author wum
 * @version $Id: VelocityUtil.java, v 0.1 2016-1-26 下午03:33:17 Administrator Exp $
 */
public class VelocityUtil {

    /**日志*/
    private static final Log log = LogFactory.getLog(VelocityUtil.class);
    /**
     * 根据模板生成WORD文档
     * @param vc VelocityContext：velocity的上下文context
     * @param templatePath vm模板目录
     * @param templateName vm模板文件名
     * @param createPath 生成word文档的物理路径
     * @return
     * @throws Exception
     */
    public static String createDoc(VelocityContext vc, String templatePath,String templateName,String createPath) throws Exception {
        Properties ps = new Properties();
        ps.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, templatePath);// 这是模板所在路径
        // 初始化并取得Velocity引擎 
        VelocityEngine ve = new VelocityEngine();
        ve.init(ps);
       // ve.init();
        Template template = ve.getTemplate(templateName, "GBK");
        String wordPath=createPath+UUID.randomUUID().toString().replace("-", "");
        File srcFile = new File(wordPath);//输出路径
        FileOutputStream fos = new FileOutputStream(srcFile);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "GBK"));
        template.merge(vc, writer);
        writer.flush();
        writer.close();
        fos.close();
        return wordPath;
    }
    /** 
     * 根据图片路径将图片转换成base64位编码 
     *  
     * @param imgUrl 
     *            图片路径 
     * @return base64位编码 
     * @throws Exception 
     */  
    public static String codeConve(String imgUrl) throws Exception {  
        byte[] data = null;  
        InputStream in = new FileInputStream(imgUrl);  
        data = new byte[in.available()];  
        in.read(data);  
        in.close();  
        // 对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串  
    }  
  /*  public static void main(String[] args) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("name", "sdfsdf");
        velocityContext.put("no", "男");
        List<Stu> dds = new ArrayList<Stu>();
        Stu stu=new Stu();
        stu.setName("测试数据1");
        dds.add(stu);
        stu=new Stu();
        stu.setName("测试数据2");
        dds.add(stu);
        velocityContext.put("dds", dds);
        try {
            VelocityUtil.createDoc(velocityContext, "D:/project/xjbt/test/", "whpMb.vm", "D:/");;
        } catch (Exception e) {
            log.error("", e);
        }
    }*/

}
