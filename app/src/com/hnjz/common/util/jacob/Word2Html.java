package com.hnjz.common.util.jacob;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Word2Html {
	public static void wordToHtml(String wordPath, String htmlPath) {
		ActiveXComponent app = new ActiveXComponent("Word.Application");// ����word
		BufferedWriter writer = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			new File(htmlPath).deleteOnExit();
			
			app.setProperty("Visible", new Variant(false));
			// ����word���ɼ�
			Dispatch docs = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.invoke(
					docs,
					"Open",
					Dispatch.Method,
					new Object[] { wordPath, new Variant(false),
							new Variant(true) }, new int[1]).toDispatch();
			// ��word�ļ�
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
					htmlPath, new Variant(8) }, new int[1]);
			// ��Ϊhtml��ʽ���浽��ʱ�ļ�
			Variant f = new Variant(false);
			Dispatch.call(doc, "Close", f);

			/**
			 * ��html�е�vmlģ�͸�Ϊhtml image��ǩ
			 */
			String content = "";
			// ��ȡtxt����
			fr = new FileReader(htmlPath);
			// ���Ի��ɹ���Ŀ¼�µ������ı��ļ�
			br = new BufferedReader(fr);
			String s = null;
			content += "";
			// ��ȡԭhtml������
			while ((s = br.readLine()) != null) {
				content += s + "\r\n";
			}
			
			try {
				String shapetype = content.substring(content.indexOf("<v:shapetype"), content.indexOf("</v:shapetype>") + 14);
				content = content.replace(shapetype, "");
			} catch (Exception e){
				System.out.println(e.getMessage());
			}

			String aa[] = content.split("<v:shape");
			// �ı��ǩ���������
			String newContent = "";
			String style = "";
			String c = "";
			for (int i = 0; i < aa.length; i++) {
				if (i > 0) {
					// ȡ��ͼƬ��С��ʽ
					style = aa[i].substring(aa[i].indexOf("style"), aa[i].indexOf(">"));
					
					c = aa[i].substring(aa[i].indexOf(">") + 1, aa[i].length());
					// ��v:imagedata��ǩ��Ϊimage��ǩ ��Ӧ���������
					c = c.replace("<v:imagedata", "<image " + style);
					// ȥ��v:shapeβ��ǩ
					c = c.replace("</v:shape>", "");
					newContent += c;
				} else {
					newContent += aa[0];
				}
			}

			// ��������д�뵽html��
			writer = new BufferedWriter(new FileWriter(new File(htmlPath)));
			writer.write(newContent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
				br.close();
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			app.invoke("Quit", new Variant[] {});
		}
	}
}