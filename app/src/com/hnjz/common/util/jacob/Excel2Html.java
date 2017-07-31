package com.hnjz.common.util.jacob;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Excel2Html {
	// 线程方式调用转换 因为执行转换的excel.exe进程只有调用此进程的容器终止此进程才会结束 线程执行完毕后相当于容器不存在所以excel.exe才会结束
	public static Thread excelToHtml(final String xlsfile, final String htmlfile) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				exec(xlsfile, htmlfile);
			}
		});
		t.start();
		return t;
	}
	
	private synchronized static void exec(String xlsfile, String htmlfile){
		ActiveXComponent app = new ActiveXComponent("Excel.Application"); // 启动word
		try {
			File file = new File(htmlfile);
			if (file.exists()){
				file.delete();
			}
			app.setProperty("Visible", new Variant(false));
			Dispatch excels = app.getProperty("Workbooks").toDispatch();
			Dispatch excel = Dispatch.invoke(
					excels,
					"Open",
					Dispatch.Method,
					new Object[] { xlsfile, new Variant(false),
							new Variant(true) }, new int[1]).toDispatch();
			Dispatch.invoke(excel, "SaveAs", Dispatch.Method, new Object[] {
					htmlfile, new Variant(44) }, new int[1]);
			Variant f = new Variant(false);
			Dispatch.call(excel, "Close", f);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			app.invoke("Quit", new Variant[] {});
		}
		//System.out.println("转换成功！");
	}
}
