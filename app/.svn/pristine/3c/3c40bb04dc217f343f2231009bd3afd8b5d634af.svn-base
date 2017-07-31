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
		ActiveXComponent app = new ActiveXComponent("Word.Application");// 启动word
		BufferedWriter writer = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			new File(htmlPath).deleteOnExit();
			
			app.setProperty("Visible", new Variant(false));
			// 设置word不可见
			Dispatch docs = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.invoke(
					docs,
					"Open",
					Dispatch.Method,
					new Object[] { wordPath, new Variant(false),
							new Variant(true) }, new int[1]).toDispatch();
			// 打开word文件
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
					htmlPath, new Variant(8) }, new int[1]);
			// 作为html格式保存到临时文件
			Variant f = new Variant(false);
			Dispatch.call(doc, "Close", f);

			/**
			 * 将html中的vml模型改为html image标签
			 */
			String content = "";
			// 读取txt内容
			fr = new FileReader(htmlPath);
			// 可以换成工程目录下的其他文本文件
			br = new BufferedReader(fr);
			String s = null;
			content += "";
			// 读取原html中内容
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
			// 改变标签后的新内容
			String newContent = "";
			String style = "";
			String c = "";
			for (int i = 0; i < aa.length; i++) {
				if (i > 0) {
					// 取出图片大小样式
					style = aa[i].substring(aa[i].indexOf("style"), aa[i].indexOf(">"));
					
					c = aa[i].substring(aa[i].indexOf(">") + 1, aa[i].length());
					// 将v:imagedata标签改为image标签 适应所有浏览器
					c = c.replace("<v:imagedata", "<image " + style);
					// 去掉v:shape尾标签
					c = c.replace("</v:shape>", "");
					newContent += c;
				} else {
					newContent += aa[0];
				}
			}

			// 将新内容写入到html中
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
