package com.hnjz.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

/**
 * poi����Word�Ĺ�����
 * 
 * @author wumi
 * @version $Id: PoiUtil.java, v 0.1 Mar 28, 2013 8:40:25 AM wumi Exp $
 */
public class PoiUtil {

    /**��־*/
    private static final Log log = LogFactory.getLog(PoiUtil.class);

    /**
     * ����WORD
     * @param templatePath  ģ��·��
     * @param dirPath   Ŀ���ļ���
     * @param paraMap   ģ�����滻����
     * @return
     * @throws IOException
     */
    public static String createWord(String templatePath, String dirPath, Map<String, String> paraMap)
                                                                                                     throws IOException {
        File templateFile = new File(templatePath);
        //ģ���ļ�������ʱ����""
        if (!templateFile.exists()) {
            return "";
        }
        //Ŀ���ļ��в�����ʱ����
        File dirFilr = new File(dirPath);
        if (!dirFilr.exists()) {
            dirFilr.mkdirs();
        }
        //��ȡwordģ��
        FileInputStream in = new FileInputStream(templateFile);
        HWPFDocument hdt = new HWPFDocument(in);
        //��ȡword�ı�����
        Range range = hdt.getRange();
        //�滻�ı�����
        for (Map.Entry<String, String> entry : paraMap.entrySet()) {
            if (log.isDebugEnabled()) {
                log.debug(entry.getKey() + ":" + StringUtils.defaultIfBlank(entry.getValue(), ""));
            }
            range.replaceText(entry.getKey(), StringUtils.defaultIfBlank(entry.getValue(), ""));
        }
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String filePath = dirPath + "//" + uuid;
        FileOutputStream out = new FileOutputStream(filePath, true);
        hdt.write(ostream);
        //����ֽ���
        out.write(ostream.toByteArray());
        in.close();
        out.close();
        ostream.close();
        return filePath;
    }
    
  public static void main(String[] aa){
    	
    	try {
    		Map<String,String> f = new HashMap<String, String>();
    		f.put("$jiancheng$", "����ʡ������");
    		f.put("$year$", "2012");
			createWord("E:\\hjjc_yjs.doc","E:\\",f);
		} catch (IOException e) {
			
		}
    }
}