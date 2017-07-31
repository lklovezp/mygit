package com.hnjz.app.work.manager.nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.hzws.DbLog;
import com.hnjz.hzws.DbTodo;
import com.hnjz.hzws.TodoPara;
import com.hnjz.hzws.WorkParaBeanBase;
import com.hnjz.hzws.WorkflowService;
import com.hnjz.hzws.WorkflowServiceService;
import com.hnjz.hzws.WorkParaBeanBase.FlowVarMap;
import com.hnjz.hzws.WorkParaBeanBase.SelectAuthorMap;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 慧正工作流得调用操作
 * @author zhangqingfeng
 * @version $Id: WorkDto.java, v 0.1 Jan 30, 2017 10:45:25 AM zhangqingfeng Exp $
 */
@Service(value = "WorkFlowOperator")
public class WorkFlowOperator extends BaseNode {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private OrgManager orgManager;
	
	/**
	 * 关闭实例
	 * @param userid 当前任务对应操作人id
	 * @param workid 任务中存的shiliId
	 */
    public void close(String userid, String workid) {
        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        port.closeInstance(userid,workid,null);

    }
    
    /**
	 * 创建实例：结合系统中的派发任务页面进行相关的调用方法
	 * @param FlowId 当前任务需要调用的慧正工作流得对应工作流
	 */
    public String create(String FlowId, String userId)  {
    	TSysUser cur = CtxUtil.getCurUser();
    	//参数准备
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        if(StringUtils.isNotBlank(userId)){
        	workParaBeanBase.setUserId(userId);
        	TSysUser user = userManager.getUser(userId);
        	workParaBeanBase.setUserName(user.getName());
        }else{
        	workParaBeanBase.setUserId(cur.getId());
        	workParaBeanBase.setUserName(cur.getName());
        }
        workParaBeanBase.setFlowId(FlowId); //flowid uuid都可以"generalTask"
        workParaBeanBase.setFlowIdentifier("system");//固定值就行，不需要进行调整
        workParaBeanBase.setDataIdentifier("system");//固定值就行，不需要进行调整
        workParaBeanBase.setTenantCode("system");//固定值就行，不需要进行调整
        //workParaBeanBase.setSubmitflag("1");

        //创建实例
        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.createInstanceService(workParaBeanBase);
        System.out.println("\n=======================创建流程返回xml打印======================================================================================\n");
        System.out.println(result);
		return result;
    }
    /**
     * 打开实例
     * @param userid 当前任务对应操作人id
	 * @param workid 任务中存的shiliId
     */
    public String open(String userId, String workId) {
        //参数准备
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setUserId(userId);
        workParaBeanBase.setWorkId(workId);
        workParaBeanBase.setFlowIdentifier("system");
        workParaBeanBase.setDataIdentifier("system");
        workParaBeanBase.setTenantCode("system");

        //打开实例
        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.openInstanceService(workParaBeanBase);
        System.out.println(result);
		return result;
    }

    /**
     * 流程操作 预提交 - 查询下一节点信息
     * @param workId
     * @param trackId
     * @param userId
     * @param userName
     * @return
     */
    public Map<String, Object> preSubmit(String workId, String trackId, String userId, String userName){
    	Document document = null;
    	Map<String, Object> map = new HashMap<String, Object>();
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setWorkId(workId);
        workParaBeanBase.setTrackId(trackId);
        workParaBeanBase.setUserId(userId);
        workParaBeanBase.setUserName(userName);
        workParaBeanBase.setTenantCode("system");
        workParaBeanBase.setFunname("submit");
        workParaBeanBase.setActionname("提交");
        workParaBeanBase.setActionClass("com.horizon.wf.action.ActionSubmit");

        workParaBeanBase.setDataIdentifier("system");
        workParaBeanBase.setFlowIdentifier("system");
        //1表示数据不提交到数据库，0表示数据提交到数据库
        workParaBeanBase.setSubmitflag("1");

        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.operatorService(workParaBeanBase);
        try {
            document = DocumentHelper.parseText(result);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElt = document.getRootElement();
        //预提交返回结果
        String res = rootElt.element("result").getText();
        //节点列表
        List nextNodeList = rootElt.element("nextNodesList").elements();
        Iterator it = rootElt.element("nextNodesList").elementIterator("NextNode");
 
        // 获取返回代码
     	// 0:成功 1：无法获取下一节点 2：提示选择节点 3：提示办理人为空 4：提示选择人员
     	// 5：流程结束 6：子流程未结束 7：流程暂停8：流程异常终止 9：其他错误提示
     	// 16:协办人提交 17:不满足合并节点的离开规则 22:进入规则调用异常
     	// 23：离开规则调用错误 25：启用规则调用错误
     	switch (Integer.parseInt(res)) {
     		case 0: {
     			if (nextNodeList.size()==1) {
     				Element e = (Element)it.next();
     				String nextNode = e.elementText("keyid");
     				if (nextNode.indexOf("End") >= 0) {
     					submit(userId, workId, trackId, "", "", "","");
     					map.put("success", "true");
     					map.put("type", "submited");
     					map.put("msg", "流程提交成功。");
     				} else {
     					map.put("success", "false");
     					map.put("msg", "流程提交失败。");
     				}
     			} else {
     				String strInfo = new String();
     				strInfo.format("流程异常，错误代码getResult = 0,但是有多个node。", res);
     				map.put("success", "false");
     				map.put("msg", strInfo);
     			}
     			break;
     		}
     		case 2: {// 提示选择节点
     			// 获取下一节点中所有办理节点
     	        List<Object> dataList = new ArrayList<Object>();
     	       for(int i=0; i<nextNodeList.size(); i++){
     	            Element e = (Element)it.next();
     	            Map<String, String> dataMap = new HashMap<String, String>();
     				dataMap.put("id", e.elementText("selectedIdStr"));
     				dataMap.put("name", e.elementText("showName"));
     	            dataList.add(dataMap);
     	        }
     			map.put("success", "true");
     			map.put("type", "nodes");
     			map.put("data", dataList);
     			break;
     		}
     		case 4: { // 提示选择人员
     	        String initAuthorMap = rootElt.element("initAuthorMap").getText();
     	        String selectAuthorMap = rootElt.element("selectAuthorMap").getText();
     	        Element e = (Element)it.next();
     	        String nextNode = e.elementText("keyid");
     	        if (!initAuthorMap.isEmpty()) {
     				// {Line2-Author=U_1823/D_1211;U_1826/D_1211}
     				// map = wr.getInitAuthorMap();
     				map.put("data", initAuthorMap+ nextNode + "-Author");
     			} else{
     				if(!selectAuthorMap.isEmpty()){
     					// 如果设置该节点为固定一人，从已选定的人员信息中获取
     					// map = result.getWorkParaBean().getSelectAuthorMap();
     					map.put("data", selectAuthorMap + nextNode + "-Author");
     					map.put("type", "users");	
     				}else{
     					map.put("success", "false");
     					map.put("msg", "请设置下一节点的办理人。");
     				}
     			}
     			break;
     		}
     		case 1: {
     			String strInfo = "流程异常，无法获取下一节点.";
     			map.put("success", "false");
     			map.put("msg", strInfo);
     			break;
     		}
     		case 3: {
     			map.put("success", "false");
     			map.put("msg", "流程异常，提示办理人为空.");
     			break;
     		}
     		default: {
     			String strInfo = new String();
     			strInfo.format("流程异常，错误代码%d", res);
     			map.put("success", "false");
     			map.put("msg", strInfo);
     			break;
     		}
     	}
     	System.out.println("\n=======================流程操作返回xml打印======================================================================================\n");
    	System.out.println(result);
    	return map;
    }
    
    /**
     * 流程查询下一节点信息
     * @param workId
     * @param trackId
     * @param userId
     * @param userName
     * @return
     */
    public Map<String, Object> preSubmitNode(String workId, String trackId, String userId, String userName){
    	Document document = null;
    	Map<String, Object> map = new HashMap<String, Object>();
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setWorkId(workId);
        workParaBeanBase.setTrackId(trackId);
        workParaBeanBase.setUserId(userId);
        workParaBeanBase.setUserName(userName);
        workParaBeanBase.setTenantCode("system");
        workParaBeanBase.setFunname("submit");
        workParaBeanBase.setActionname("提交");
        workParaBeanBase.setActionClass("com.horizon.wf.action.ActionSubmit");

        workParaBeanBase.setDataIdentifier("system");
        workParaBeanBase.setFlowIdentifier("system");
        //1表示数据不提交到数据库，0表示数据提交到数据库
        workParaBeanBase.setSubmitflag("1");

        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.operatorService(workParaBeanBase);
        try {
            document = DocumentHelper.parseText(result);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElt = document.getRootElement();
        //预提交返回结果
        String res = rootElt.element("result").getText();
        //节点列表
        List nextNodeList = rootElt.element("nextNodesList").elements();
        Iterator it = rootElt.element("nextNodesList").elementIterator("NextNode");
 
        // 获取返回代码
     	// 0:成功 1：无法获取下一节点 2：提示选择节点 3：提示办理人为空 4：提示选择人员
     	// 5：流程结束 6：子流程未结束 7：流程暂停8：流程异常终止 9：其他错误提示
     	// 16:协办人提交 17:不满足合并节点的离开规则 22:进入规则调用异常
     	// 23：离开规则调用错误 25：启用规则调用错误
     	switch (Integer.parseInt(res)) {
     		case 0: {
     			if (nextNodeList.size()==1) {
     				Element e = (Element)it.next();
     				String nextNode = e.elementText("keyid");
     				if (nextNode.indexOf("End") >= 0) {
     					submit(userId, workId, trackId, "", "", "","");
     					map.put("success", "true");
     					map.put("type", "submited");
     					map.put("msg", "流程提交成功。");
     				} else {
     					map.put("success", "false");
     					map.put("msg", "流程提交失败。");
     				}
     			} else {
     				String strInfo = new String();
     				strInfo.format("流程异常，错误代码getResult = 0,但是有多个node。", res);
     				map.put("success", "false");
     				map.put("msg", strInfo);
     			}
     			break;
     		}
     		case 2: {// 提示选择节点
     			// 获取下一节点中所有办理节点
     	        List<Object> dataList = new ArrayList<Object>();
     	       for(int i=0; i<nextNodeList.size(); i++){
     	            Element e = (Element)it.next();
     	            Map<String, String> dataMap = new HashMap<String, String>();
     				dataMap.put("id", e.elementText("selectedIdStr"));
     				dataMap.put("name", e.elementText("showName"));
     	            dataList.add(dataMap);
     	        }
     			map.put("success", "true");
     			map.put("type", "nodes");
     			map.put("data", dataList);
     			break;
     		}
     		case 4: { // 提示选择人员
     	        String initAuthorMap = rootElt.element("initAuthorMap").getText();
     	        String selectAuthorMap = rootElt.element("selectAuthorMap").getText();
     	        Element e = (Element)it.next();
     	        String nextNode = e.elementText("keyid");
     	        if (!initAuthorMap.isEmpty()) {
     				// {Line2-Author=U_1823/D_1211;U_1826/D_1211}
     				// map = wr.getInitAuthorMap();
     				map.put("data", initAuthorMap+ nextNode + "-Author");
     			} else{
     				if(!selectAuthorMap.isEmpty()){
     					// 如果设置该节点为固定一人，从已选定的人员信息中获取
     					// map = result.getWorkParaBean().getSelectAuthorMap();
     					map.put("data", selectAuthorMap + nextNode + "-Author");
     					map.put("type", "users");	
     				}else{
     					map.put("success", "false");
     					map.put("msg", "请设置下一节点的办理人。");
     				}
     			}
     			break;
     		}
     		case 1: {
     			String strInfo = "流程异常，无法获取下一节点.";
     			map.put("success", "false");
     			map.put("msg", strInfo);
     			break;
     		}
     		case 3: {
     			map.put("success", "false");
     			map.put("msg", "流程异常，提示办理人为空.");
     			break;
     		}
     		default: {
     			String strInfo = new String();
     			strInfo.format("流程异常，错误代码%d", res);
     			map.put("success", "false");
     			map.put("msg", strInfo);
     			break;
     		}
     	}
     	System.out.println("\n=======================流程操作返回xml打印======================================================================================\n");
    	System.out.println(result);
    	return map;
    }
    
    /**
     * 流程操作 提交
     * @param userId
     * @param workId
     * @param trackId
     * @param nextNodeId
     * @param nextUserId
     * @param userName
     * @return
     */
    public String submit(String userId, String workId, String trackId, String nextNodeId, String nextUserId, String userName, String oper){
    	//userId是登陆用户信息， nextUserId下一节点办理人信息
    	open(userId, workId);
        /*WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setWorkId(workId);
        workParaBeanBase.setTrackId(trackId);
        //用户id在慧正工作流中的形式
        TSysOrg org1 = orgManager.getOrgByUserid(userId);
        workParaBeanBase.setUserId("U_"+userId+"/D_"+ org1.getId());
        workParaBeanBase.setUserName(userName);
        workParaBeanBase.setTenantCode("system");
        workParaBeanBase.setFunname("submit");
        workParaBeanBase.setActionname("提交");
        workParaBeanBase.setActionClass("com.horizon.wf.action.ActionSubmit");*/
    	WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setWorkId(workId);
        workParaBeanBase.setTrackId(trackId);
        workParaBeanBase.setUserId(userId);
        workParaBeanBase.setUserName(userName);
        workParaBeanBase.setTenantCode("system");
        workParaBeanBase.setFunname("submit");
        workParaBeanBase.setActionname("提交");
        workParaBeanBase.setActionClass("com.horizon.wf.action.ActionSubmit");
        
        workParaBeanBase.setNextNodeId(nextNodeId);
        boolean endNode = false;
        //流程设计端设置办理人无效，若设计段没设置，这里也没设置，下一节点提交给自己(仅针对一条路由情况)
        //下一节点可以设置的前提：在设计端的路由条件允许走此节点
        // 判断是否是为：结束节点
		if (endNode) {
			// 读者
			//inparam.putSelectAuthor ("Reader",userId);
		} else {
			if(!("").equals(nextUserId)){
				com.hnjz.hzws.WorkParaBeanBase.SelectAuthorMap.Entry enAuthorMap= new com.hnjz.hzws.WorkParaBeanBase.SelectAuthorMap.Entry();
				//enAuthorMap.setKey(nextNodeId+"-Author");
				//nextUserId = "U_"+nextUserId+"/D_"+ org.getId(); 
				String[] node = null;
				if(StringUtils.isNotBlank(nextNodeId)){
					node = nextNodeId.split("~");
				}
				if(node[1].contains("End")){
					enAuthorMap.setKey(node[0]+"-Reader");
				}else{
					enAuthorMap.setKey(node[0]+"-Author");
				}
				TSysOrg org = orgManager.getOrgByUserid(nextUserId);
				enAuthorMap.setValue("U_"+nextUserId+"/D_"+ org.getId());
				SelectAuthorMap saMap=new SelectAuthorMap();
				saMap.getEntry().add(enAuthorMap);
				workParaBeanBase.setSelectAuthorMap(saMap);
		    }
		}
		
		//流程变量
		com.hnjz.hzws.WorkParaBeanBase.FlowVarMap.Entry enFlowVarMap= new com.hnjz.hzws.WorkParaBeanBase.FlowVarMap.Entry();
		enFlowVarMap.setKey("LY");
		enFlowVarMap.setValue(oper);
		FlowVarMap fvMap=new FlowVarMap();
		fvMap.getEntry().add(enFlowVarMap);
		workParaBeanBase.setFlowVarMap(fvMap);
		
		//流程设计端设置办理人无效，若设计段没设置，这里也没设置，下一节点提交给自己(仅针对一条路由情况)
        //下一节点可以设置的前提：在设计端的路由条件允许走此节点
        //workParaBeanBase.setNextNodeId("Line4~Node4");
		//HashMap<String, String> selectedUsers = new LinkedHashMap();
        //selectedUsers.put("Line2-Author","admin");
        //workParaBeanBase.setSelectAuthorMap(selectedUsers);//设定每条路径对应的办理人键值对

        //流程变量
        //HashMap<String,String> flowVarMap = new HashMap<String, String>();
        //flowVarMap.put("a","10");
        //workParaBeanBase.setFlowVarMap(flowVarMap);
        
        workParaBeanBase.setDataIdentifier("system");
        workParaBeanBase.setFlowIdentifier("system");
        //1表示数据不提交到数据库，0表示数据提交到数据库
        workParaBeanBase.setSubmitflag("0");

        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        //启动子流程，然后进行传值的submit，第一次执行这句会报错，第二次就正常执行，返回提交操作失败
        String result = port.operatorService(workParaBeanBase);

        System.out.println("\n=======================流程操作返回xml打印======================================================================================\n");
        System.out.println(result);
		return result;
    }
    
    /**
     * 流程操作 终止
     * 特别：设计端对应节点需要勾选终止权限
     * @return
     */
    public String operate_stop(String workId, String trackId, String userId, String userName) {
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setWorkId(workId); //实例id
        workParaBeanBase.setTrackId(trackId); //路径id
        workParaBeanBase.setUserId(userId);
        //workParaBeanBase.setNodeId("Node3"); //当前节点Id
        workParaBeanBase.setSubmitflag("0");//是否确认进行操作 0:确认 1:前后台交互
        workParaBeanBase.setActionClass("com.horizon.wf.action.ActionStop");
        workParaBeanBase.setFunname("stop"); //实例操作标识
        workParaBeanBase.setActionname(" 终止 "); //操作名称
        workParaBeanBase.setTenantCode("system"); //租户id
        workParaBeanBase.setDataIdentifier("system");
        workParaBeanBase.setFlowIdentifier("system");

        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.operatorService(workParaBeanBase);
        System.out.println(result);
		return result;
    }
    
    /**
     * 流程操作 启动子流程
     * @return
     */
    public String operate_subflow(String workId, String trackId, String userId, String flowId) {
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setWorkId(workId); //实例id
        workParaBeanBase.setTrackId(trackId); //路径id
        TSysUser cur = CtxUtil.getCurUser();
        String r = open(cur.getId(), workId);
        Document document = null;
        String nodeid = "";
        try {
            document = DocumentHelper.parseText(r);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElt = document.getRootElement();
        nodeid = rootElt.element("curTrackInfo").element("DBTrack").element("nodeid").getText();
        TSysOrg org = orgManager.getOrgByUserid(userId);
        workParaBeanBase.setUserId("U_"+userId+"/D_"+ org.getId());
        workParaBeanBase.setNodeId(nodeid); //当前节点Id
        workParaBeanBase.setSubmitflag("0");//是否确认进行操作 0:确认 1:前后台交互
        //workParaBeanBase.setNextNodeId("Node2");
        workParaBeanBase.setActionClass("com.horizon.wf.action.ActionStartMultiSubFlow");
        workParaBeanBase.setFunname("subflow"); //实例操作标识
        workParaBeanBase.setActionname("启动子流程"); //操作名称
        workParaBeanBase.setSelectSubFlowid(flowId);//需要启动的流程id
        workParaBeanBase.setTenantCode("system"); //租户id
        workParaBeanBase.setDataIdentifier("system");
        workParaBeanBase.setFlowIdentifier("system");
        
        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.operatorService(workParaBeanBase);
        System.out.println("\n=======================流程操作返回xml打印======================================================================================\n");
        System.out.println(result);
        return result;
    }

    /**
     * 流程操作 任意退回
     * @return
     */
    public String operate_jumpreject(String workId, String trackId, String userId) {
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setWorkId(workId); //实例id
        workParaBeanBase.setTrackId(trackId); //路径id
        workParaBeanBase.setUserId(userId);
        //workParaBeanBase.setNodeId("Node3"); //当前节点Id
        workParaBeanBase.setSubmitflag("0");//是否确认进行操作 0:确认 1:前后台交互
        workParaBeanBase.setNextNodeId("Node2");
        workParaBeanBase.setActionClass("com.horizon.wf.action.ActionRejectByJump");
        workParaBeanBase.setFunname("jumpreject"); //实例操作标识
        workParaBeanBase.setActionname("任意退回 "); //操作名称
        workParaBeanBase.setTenantCode("system"); //租户id
        workParaBeanBase.setDataIdentifier("system");
        workParaBeanBase.setFlowIdentifier("system");

        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.operatorService(workParaBeanBase);
        System.out.println(result);
		return result;
    }

    //获取待办，返回值为DbTodo的list
    public void getTodoList(String userId) {
        //参数准备
        TodoPara todoPara = new TodoPara();
        //workParaBeanBase.setAllUserids("admin");
        todoPara.setUserid(userId);
        todoPara.setIdentifier("system");
        todoPara.setTenantid("system");
        //todoPara.setWhere(" or 1 = 2 ");

        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        List<DbTodo> result = port.getTodoList(todoPara);
        //result.get(0).
        System.out.println(JSON.toJSONString(result.get(0)));
    }

    /*
    public void handoverWork(){
        String json = "{'workid':'HZ8187f05c856fb3015c85706ad60033', 'trackid':'HZ8187f05c856fb3015c85706afa0034', 'olduserid':'admin', 'newuserid':'00efa504-0a8b-4e8b-98b3-0ab6ba6f30e0'}";
        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        boolean b = port.handoverWork(json,false,"system","system");
        System.out.println(b);
    }*/

    //获取实例历史记录
    public void getHistory(){
        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        List<DbLog> list =  port.getHistory("HZ8187f05c856fb3015c85706ad60033","system");
        System.out.println(JSON.toJSON(list));
    }
}