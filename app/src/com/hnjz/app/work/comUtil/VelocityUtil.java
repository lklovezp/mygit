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
 * ����word�ļ�
 * 
 * @author wum
 * @version $Id: VelocityUtil.java, v 0.1 2016-1-26 ����03:33:17 Administrator Exp $
 */
public class VelocityUtil {

    /**��־*/
    private static final Log log = LogFactory.getLog(VelocityUtil.class);
    /**
     * ����ģ������WORD�ĵ�
     * @param vc VelocityContext��velocity��������context
     * @param templatePath vmģ��Ŀ¼
     * @param templateName vmģ���ļ���
     * @param createPath ����word�ĵ�������·��
     * @return
     * @throws Exception
     */
    public static String createDoc(VelocityContext vc, String templatePath,String templateName,String createPath) throws Exception {
        Properties ps = new Properties();
        ps.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, templatePath);// ����ģ������·��
        // ��ʼ����ȡ��Velocity���� 
        VelocityEngine ve = new VelocityEngine();
        ve.init(ps);
       // ve.init();
        Template template = ve.getTemplate(templateName, "GBK");
        String wordPath=createPath+UUID.randomUUID().toString().replace("-", "");
        File srcFile = new File(wordPath);//���·��
        FileOutputStream fos = new FileOutputStream(srcFile);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "GBK"));
        template.merge(vc, writer);
        writer.flush();
        writer.close();
        fos.close();
        return wordPath;
    }
    /** 
     * ����ͼƬ·����ͼƬת����base64λ���� 
     *  
     * @param imgUrl 
     *            ͼƬ·�� 
     * @return base64λ���� 
     * @throws Exception 
     */  
    public static String codeConve(String imgUrl) throws Exception {  
        byte[] data = null;  
        InputStream in = new FileInputStream(imgUrl);  
        data = new byte[in.available()];  
        in.read(data);  
        in.close();  
        // ���ֽ�����Base64����  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);// ����Base64��������ֽ������ַ���  
    }  
  /*  public static void main(String[] args) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("name", "sdfsdf");
        velocityContext.put("no", "��");
        List<Stu> dds = new ArrayList<Stu>();
        Stu stu=new Stu();
        stu.setName("��������1");
        dds.add(stu);
        stu=new Stu();
        stu.setName("��������2");
        dds.add(stu);
        velocityContext.put("dds", dds);
        try {
            VelocityUtil.createDoc(velocityContext, "D:/project/xjbt/test/", "whpMb.vm", "D:/");;
        } catch (Exception e) {
            log.error("", e);
        }
    }*/

}