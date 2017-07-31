package com.hnjz;

import com.alibaba.fastjson.JSON;
import com.hnjz.hzws.DbLog;
import com.hnjz.hzws.DbTodo;
import com.hnjz.hzws.TodoPara;
import com.hnjz.hzws.WorkParaBeanBase;
import com.hnjz.hzws.WorkflowService;
import com.hnjz.hzws.WorkflowServiceService;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import java.util.List;

/**
 * Created by AlexanderJin on 2017/2/22.
 */
public class Webservice_Demo {
    //参数准备
    String userid = "admin"; //微信端登陆的用户id
    String flowid = "bx"; //flowid uuid都可以
    String nodeid = "Node2"; //开始节点后 第一个节点的id，如"Node2"
    String result = ""; //创建流程是否成功标志，成功为100
    String backMsg = ""; //创建流程是否成功返回语句
    String instanceXML = "";//创建实例返回的xml
    String workid = "";  //解析instanceXML取出workid
    String trackid ="";

    //联合测试
    @Test
    public void test() {
        create();  //创建完已打开流程
        operate();
        close();
    }



    //创建实例
    @Test
    public void create()  {
        //参数准备
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setUserId("admin");
        workParaBeanBase.setFlowId("HZ8a8a835c39276e015c3ebdf2320223"); //flowid uuid都可以
        workParaBeanBase.setUserName("管理员dsdsd");
        workParaBeanBase.setFlowIdentifier("system");
        workParaBeanBase.setDataIdentifier("system");
        workParaBeanBase.setTenantCode("system");
        //workParaBeanBase.setSubmitflag("1");

        //创建实例
        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.createInstanceService(workParaBeanBase);
        System.out.println("\n=======================创建流程返回xml打印======================================================================================\n");
        System.out.println(result);


    }

    private void getWorkidAndTrackid() {
        Document document = null;
        try {
            document = DocumentHelper.parseText(instanceXML);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElt = document.getRootElement();

        workid = rootElt.element("workid").getText();
        trackid = rootElt.element("curTrackInfo").element("DBTrack").element("id").getText();
    }


    //打开实例
    @Test
    public void open() {
        //参数准备
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setUserId("admin");
        workParaBeanBase.setWorkId("HZ8a8a835c6173b8015c63ef571701ed");
        workParaBeanBase.setFlowIdentifier("system");
        workParaBeanBase.setDataIdentifier("system");
        workParaBeanBase.setTenantCode("system");

        //打开实例
        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.openInstanceService(workParaBeanBase);
        System.out.println(result);
    }

    //关闭实例
    public void close() {

        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        port.closeInstance(userid,workid,null);

    }

    //流程操作 提交
    @Test
    public void operate(){
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setWorkId("HZ8a8a835c2ee02b015c2ee287030035");
        workParaBeanBase.setTrackId("HZ8a8a835c2ee02b015c2ee2885a0036");
        workParaBeanBase.setUserId("admin");
        workParaBeanBase.setTenantCode("system");
        workParaBeanBase.setFunname("submit");
        workParaBeanBase.setActionname("提交");
        workParaBeanBase.setActionClass("com.horizon.wf.action.ActionSubmit");

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
        workParaBeanBase.setSubmitflag("0");

        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.operatorService(workParaBeanBase);

        System.out.println("\n=======================流程操作返回xml打印======================================================================================\n");
        System.out.println(result);
    }

    //流程操作 终止
    //特别：设计端对应节点需要勾选终止权限
    @Test
    public void operate_stop() {
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setWorkId("HZ8a8a835bedae77015bedb1bec80034"); //实例id
        workParaBeanBase.setTrackId("HZ8a8a835bedae77015bedb1bedb0035"); //路径id
        workParaBeanBase.setUserId("admin");
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
    }

    //流程操作 启动子流程
    @Test
    public void operate_subflow() {
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setWorkId("HZ8a8a835c58ff9d015c5dbce98b02ca"); //实例id
        workParaBeanBase.setTrackId("HZ8a8a835c58ff9d015c5dbce98d02cb"); //路径id
        workParaBeanBase.setUserId("admin");
        //workParaBeanBase.setNodeId("Node3"); //当前节点Id
        //workParaBeanBase.setSubmitflag("0");//是否确认进行操作 0:确认 1:前后台交互
        //workParaBeanBase.setNextNodeId("Node2");
        workParaBeanBase.setActionClass("com.horizon.wf.action.ActionStartSubFlow");
        workParaBeanBase.setFunname("subflow"); //实例操作标识
        workParaBeanBase.setActionname("启动子流程"); //操作名称
        workParaBeanBase.setSelectSubFlowid("r");
        workParaBeanBase.setTenantCode("system"); //租户id
        workParaBeanBase.setDataIdentifier("system");
        workParaBeanBase.setFlowIdentifier("system");

        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.operatorService(workParaBeanBase);
        System.out.println(result);
    }

    //流程操作 任意退回
    @Test
    public void operate_jumpreject() {
        WorkParaBeanBase workParaBeanBase = new WorkParaBeanBase();
        workParaBeanBase.setWorkId("HZ8a8a835bedae77015bedc8072c00a2"); //实例id
        workParaBeanBase.setTrackId("HZ8a8a835bedae77015bedc8073600a3"); //路径id
        workParaBeanBase.setUserId("admin");
        //workParaBeanBase.setNodeId("Node3"); //当前节点Id
        //workParaBeanBase.setSubmitflag("0");//是否确认进行操作 0:确认 1:前后台交互
        workParaBeanBase.setNextNodeId("Node2");
        workParaBeanBase.setActionClass("com.horizon.wf.action.ActionRejectByJump");
        workParaBeanBase.setFunname("jumpreject"); //实例操作标识
        workParaBeanBase.setActionname(" 任意退回 "); //操作名称
        workParaBeanBase.setTenantCode("system"); //租户id
        workParaBeanBase.setDataIdentifier("system");
        workParaBeanBase.setFlowIdentifier("system");

        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        String result = port.operatorService(workParaBeanBase);
        System.out.println(result);
    }

    //获取待办，返回值为DbTodo的list
    @Test
    public void getTodoList() {
        //参数准备
        TodoPara todoPara = new TodoPara();
        //workParaBeanBase.setAllUserids("admin");
        todoPara.setUserid("admin");
        todoPara.setIdentifier("system");
        todoPara.setTenantid("system");
        //todoPara.setWhere(" or 1 = 2 ");


        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        List<DbTodo> result = port.getTodoList(todoPara);
        //result.get(0).
        System.out.println(JSON.toJSONString(result.get(0)));
    }

    /*@Test
    public void handoverWork(){
        String json = "{'workid':'HZ8187f05c856fb3015c85706ad60033', 'trackid':'HZ8187f05c856fb3015c85706afa0034', 'olduserid':'admin', 'newuserid':'00efa504-0a8b-4e8b-98b3-0ab6ba6f30e0'}";
        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        boolean b = port.handoverWork(json,false,"system","system");
        System.out.println(b);
    }*/

    //获取实例历史记录
    @Test
    public void getHistory(){
        WorkflowServiceService service = new WorkflowServiceService();
        WorkflowService port = service.getWorkflowServicePort();
        List<DbLog> list =  port.getHistory("HZ8187f05c856fb3015c85706ad60033","system");
        System.out.println(JSON.toJSON(list));
    }
}