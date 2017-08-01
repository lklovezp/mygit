package com.hnjz.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hnjz.app.iMsgServer2000;
import com.hnjz.common.AppException;

/**
 * 金格word处理
 * 
 * @author fengjian
 * 
 */
public class WebOfficeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(this.getClass());
    private iMsgServer2000 msgObj;
    private byte[] mFileBody;
    private String mOption;
    //以下为t_jbpm_assigndoc的对应字段
    private String wordFilePath;
    public WebOfficeServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	ExecuteRun(request,response);
    }
    private void ExecuteRun(HttpServletRequest request, HttpServletResponse response){
    	log.info("开始生成流程批文模板");
        try {
            if (request.getMethod().equalsIgnoreCase("POST")) {
                msgObj.Load(request);
                // if(msgObj!=null){
                     /**暂时不验证数据包完整性 因为有的浏览器发送的数据包老是提示失败*/
                if (msgObj.GetMsgByName("DBSTEP").equalsIgnoreCase("DBSTEP")) { // 判断是否是合法的信息包，或者数据包信息是否完整
                	mOption = msgObj.GetMsgByName("OPTION");
                	if(mOption.equalsIgnoreCase("LOADFILE")){
                		msgObj.MsgTextClear();						//清除文本信息
                		  //调入文档
                		  wordFilePath=request.getParameter("wordFilePath");
                		  if (msgObj.MsgFileLoad(wordFilePath)){
                			  msgObj.SetMsgByName ("STATUS","打开成功!" );  	//设置状态信息
                			  msgObj.MsgError("");			 			//清除错误信息
                		  }else{
                			  msgObj.MsgError("打开失败!");				//设置错误信息
                		  }

                	}else if (mOption.equalsIgnoreCase("SAVETEMPLATE")){
                		/**==== 保存word内存=====  */
                		/**
                		mFileBody = msgObj.MsgFileBody(); // 取得文档内容
                        msgObj.MsgTextClear();
                        ByteArrayInputStream instream = new ByteArrayInputStream(
                                mFileBody);
                        //拷贝附件
                        String fileRealPath = FileUpDownUtil.copyFile(instream,
                                fileName, UploadFileType.Assigndoc);
                        
                        if (StringUtil.isNotBlank(fileRealPath)) { 
                            //业务逻辑
                            msgObj.SetMsgByName("STATUS", "保存成功!"); // 设置状态信息
                            msgObj.MsgError(""); // 清除错误信息
                        } else {
                            msgObj.MsgError("保存模板失败!"); // 设置错误信息
                            throw new AppException("无法生成编辑后的模板！");
                        }
                        msgObj.MsgFileClear();
                         */
                	}else{
                		msgObj.MsgError(mOption+"未定义！");
                        msgObj.MsgTextClear();
                        msgObj.MsgFileClear();
                		throw new AppException(mOption+"未定义！");
                	}
                   
                } else {
                    msgObj.MsgError("客户端发送数据包错误,建议使用IE8浏览器打开");
                    msgObj.MsgTextClear();
                    msgObj.MsgFileClear();
                    throw new AppException("客户端发送数据包错误,建议使用IE8浏览器打开!");
                }
            } else {
                msgObj.MsgError("请使用Post方法");
                msgObj.MsgTextClear();
                msgObj.MsgFileClear();
                throw new AppException("请使用Post方法");
            }
            log.info("保存流程批文模板成功");
        } catch (Exception e) {
            log.error("保存流程批文模板失败",e);
            msgObj.MsgError("保存模板失败!");
            msgObj.MsgTextClear();
            msgObj.MsgFileClear();
        }
        msgObj.Send(response); // 8.1.0.2新版后台类新增的功能接口，返回信息包数据
    }
    public void init() throws ServletException {
        WebApplicationContext wac = WebApplicationContextUtils
                .getRequiredWebApplicationContext(this.getServletContext());
        msgObj=(iMsgServer2000) wac.getBean("iMsgServer2000");
    }

}
