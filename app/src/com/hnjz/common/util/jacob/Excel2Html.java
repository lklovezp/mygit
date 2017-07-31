package com.hnjz.common.util.jacob;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Excel2Html {
	// �̷߳�ʽ����ת�� ��Ϊִ��ת����excel.exe����ֻ�е��ô˽��̵�������ֹ�˽��̲Ż���� �߳�ִ����Ϻ��൱����������������excel.exe�Ż����
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
		ActiveXComponent app = new ActiveXComponent("Excel.Application"); // ����word
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
		//System.out.println("ת���ɹ���");
	}
}