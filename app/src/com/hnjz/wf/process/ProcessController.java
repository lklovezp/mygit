package com.hnjz.wf.process;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hnjz.common.JsonResultUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;
import com.hnjz.wf.AbsJbpmController;
import com.hnjz.wf.IApplyCommonPo;
import com.hnjz.wf.bean.ProcessInfoBean;
import com.hnjz.wf.entity.WfApplyStep;
import com.hnjz.wf.entity.WfProcessBusiness;
import com.hnjz.wf.entity.WfProcessStep;
import com.hnjz.wf.enums.ProcessEnum;

@Controller
@RequestMapping(value = "wf/process")
public class ProcessController extends AbsJbpmController {

    @Autowired
    private ProcessManager processManager;
    @Autowired
    private UserManager    userManager;

    /**
     * 已完成流程列表
     * 
     * @param model
     */
    @RequestMapping(value = "/pdList", method = { RequestMethod.POST, RequestMethod.GET })
    public void pdList(ModelMap model) {

    }

    /**
     * 已定义流程
     * 
     * @param model
     */
    @RequestMapping(value = "/defineProcedure.json", produces = "application/json")
    public void defineProcedure(ModelMap model) {
        try {
            ProcessEnum[] pes = ProcessEnum.values();
            List<ProcessEnum> list = Arrays.asList(pes);
            List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < list.size(); i++) {
                listMap.add(list.get(i).toPesMap());
            }
            map.put("total", list.size());
            map.put("rows", listMap);
            JsonResultUtil.show(model, map);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 已定义流程
     * 
     * @param model
     */
    @RequestMapping(value = "/releaseProcedure.json", produces = "application/json")
    public void releaseProcedure(ModelMap model) {
        try {
            List<ProcessDefinition> pdList = processManager.getAllPdList();
            //  model.addAttribute("pdList", pdList);
            List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < pdList.size(); i++) {
                listMap.add(toPdMap(pdList.get(i)));

            }
            map.put("total", pdList.size());
            map.put("rows", listMap);
            JsonResultUtil.show(model, map);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public Map<String, Object> toPdMap(ProcessDefinition pdList) {
        // <a href="javascript:deployProcess('${pe.processKey }')">发布流程</a>
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer str = new StringBuffer();
        str.append("<a href=\"#\" onclick=\"delProcess('").append(pdList.getDeploymentId()).append(
            "')\">").append("删除").append("</a>&nbsp;");
        map.put("name", pdList.getName());
        map.put("id", pdList.getId());
        map.put("key", pdList.getKey());
        map.put("deploymentId", pdList.getDeploymentId());
        map.put("version", pdList.getVersion());
        map.put("nextActions", str.toString());
        return map;
    }

    /**
     * 进行中流程定义列表
     * 
     * @param model
     */
    @RequestMapping(value = "/pjxList", method = { RequestMethod.POST, RequestMethod.GET })
    public void pjxList(ModelMap model) {
        try {

        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 正在进行的任务
     * 
     * @param model
     */
    @RequestMapping(value = "/CarryOnTask.json", produces = "application/json")
    public void CarryOnTask(ModelMap model) {
        try {
            List<Task> taskList = processManager.getAllTaskList();
            List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < taskList.size(); i++) {
                listMap.add(toTaskMap(taskList.get(i)));

            }
            map.put("total", taskList.size());
            map.put("rows", listMap);
            JsonResultUtil.show(model, map);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public Map<String, Object> toTaskMap(Task task) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", task.getName());
        map.put("executionId", task.getExecutionId());
        map.put("assignee", task.getAssignee());

        return map;
    }

    /**
     * 已定义流程
     * 
     * @param model
     */
    @RequestMapping(value = "/CarryOnProcedure.json", produces = "application/json")
    public void CarryOnProcedure(ModelMap model) {
        try {
            List<ProcessInstance> piList = processManager.getAllPiList();
            //  model.addAttribute("pdList", pdList);
            List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < piList.size(); i++) {
                listMap.add(toPiMap(piList.get(i)));

            }
            map.put("total", piList.size());
            map.put("rows", listMap);
            JsonResultUtil.show(model, map);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public Map<String, Object> toPiMap(ProcessInstance piList) {
        // <a href="javascript:deployProcess('${pe.processKey }')">发布流程</a>
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer str = new StringBuffer();
        str.append("<a href=\"javascript:flowChart('").append(piList.getId()).append("')\">")
            .append("流程图").append("</a>&nbsp;&nbsp;&nbsp;").append(
                "<a href=\"javascript:delProcessIns('").append(piList.getId()).append("')\">")
            .append("删除").append("</a>&nbsp;");
        map.put("id", piList.getId());

        map.put("nextActions", str.toString());
        return map;
    }

    /**
     * 流程设置
     * 
     * @param deploymentId
     *            流程发布编号
     * @param req
     */
    @RequestMapping(value = "/deploySet/{deploymentId}")
    public String pdSet(@PathVariable String deploymentId, HttpServletRequest req, ModelMap model) {
        try {
            ProcessDefinition pd = processManager.findPdByDeploymentId(deploymentId);
            model.addAttribute("pd", pd);
            model.addAttribute("processes", ProcessEnum.values());
            // 得到可选择页面集合
            List<String> stepNameList = processManager.getStepNameList(req.getSession()
                .getServletContext().getRealPath("/"), deploymentId);
            model.addAttribute("stepNameList", stepNameList);
            // 得到已经设置数据
            WfProcessBusiness wfProcessBusiness = processManager
                .findProcessBusinessFromProcessKey(pd.getKey());
            if (wfProcessBusiness != null) {
                model.addAttribute("wfProcessBusiness", wfProcessBusiness);
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return "wf/process/deploySet";
    }

    /**
     * 流程设置保存
     * 
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = "/pdSetSave", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
    public void pdSetSave(HttpServletRequest req, ModelMap model) {
        String dataJson = req.getParameter("dataJson");
        try {
            processManager.savePdSet(dataJson);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }

    /**
     * 流程发布
     * 
     * @param key
     *            流程KEY
     * @param model
     * @return
     */
    @RequestMapping(value = "/pdDeploy/{key}")
    public void processDeploy(@PathVariable String key, ModelMap model) {
        try {
            processManager.saveProcess(key);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }

    /**
     * 流程删除
     * 
     * @param deploymentId
     *            流程发布编号
     * @param model
     * @return
     */
    @RequestMapping(value = "/pdDel/{deploymentId}")
    public void processDel(@PathVariable String deploymentId, ModelMap model) {
        try {
            processManager.removeProcess(deploymentId);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }

    /**
     * 流程实例删除
     * 
     * @param piId
     *            流程实例编号
     * @param model
     */
    @RequestMapping(value = "/piDel")
    public void processInsDel(String piId, ModelMap model) {
        try {
            processManager.removeProcessIns(piId);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }

    /**
     * 我的任务页面
     * 
     * @param req
     * @param model
     */
    @RequestMapping(value = "/mine")
    public void processForMine(ModelMap model) {

    }

    /**
     * 我的任务数据
     * @param model
     */
    @RequestMapping(value = "/mineData", produces = "application/json")
    public void mineData(ModelMap model) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<ProcessInfoBean> processInfoBeanList = processManager.getUserTask(findLgUser()
                .getUsername());
            Collections.sort(processInfoBeanList, new Comparator<ProcessInfoBean>() {

                public int compare(ProcessInfoBean arg0, ProcessInfoBean arg1) {

                    return arg0.getEndTime().compareTo(arg1.getEndTime());

                }

            });
       
            map.put("total", processInfoBeanList.size());
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < processInfoBeanList.size(); i++) {
                list.add(processInfoBeanList.get(i).toMap());
            }
            if (log.isDebugEnabled()) {
                log.debug("processInfoBeanList.size():" + processInfoBeanList.size());
            }
            map.put("rows", list);
            JsonResultUtil.show(model, map);
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.show(model, map);
        }
    }

    /**
     * 流程步骤-启动
     * 
     * @param key
     *            流程KEY
     * @param applyId
     *            申请单编号
     * @param res
     */
    @RequestMapping(value = "/start/{key}/{applyId}")
    public void taskStart(@PathVariable String key, @PathVariable String applyId, ModelMap model) {
        log.info("启动一个 " + key + " 流程实体");
        ProcessInstance pi = null;
        try {
            // 启动的流程是否已经发布(传入当前用户名作为此任务的创建人)
            ProcessEnum pe = ProcessEnum.getByKey(key);
            pi = processManager.startProcess(key, "zhuna", pe.getApplyPo(), applyId);
            if (pi != null) {
                Map<String, Object> varMap = new HashMap<String, Object>();
                varMap.put("myRole", "1");
                varMap.put("myDay", 2);
                processManager.execProcess(processManager.getActTaskFromPiId(pi.getId()).getId(),
                    "", varMap);
                JsonResultUtil.suncess(model, "操作成功！");
            } else {// 此流程未发布
                JsonResultUtil.fail(model, "操作失败！此流程未发布");
            }
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }

    /**
     * 流程执行
     * 
     * @param taskId
     *            任务编号
     * @param model
     * @return
     */
    @RequestMapping(value = "/exec/{taskId}")
    public void taskExec(@PathVariable String taskId, ModelMap model) {
        log.info("执行任务： " + taskId);
        try {
            Map<String, Object> varMap = new HashMap<String, Object>();
            varMap.put("myRole", "1");
            varMap.put("myDay", 2);
            processManager.execProcess(taskId, "", varMap);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }

    /**
     * 初稿
     * 
     * @param key
     *            流程KEY
     * @param model
     */
    @RequestMapping(value = "/step/draft/{key}")
    public String processDraft(@PathVariable String key, ModelMap model) {
        try {
            ProcessEnum pe = ProcessEnum.getByKey(key);
            model.addAttribute("pe", pe);
        } catch (Exception e) {
            log.error("", e);
        }
        return "wf/process/step/draft";
    }

    /**
     * 任务执行
     * 
     * @param piId
     *            流程实例编号
     * @param taskId
     *            任务编号
     * @param applyId
     *            申请单编号
     * @param model
     * @return
     */
    @RequestMapping(value = "/step/exec/{key}/{taskId}/{applyId}")
    public String processExec(@PathVariable String key, @PathVariable String taskId,
                              @PathVariable String applyId, ModelMap model) {
        try {
            ProcessEnum pe = ProcessEnum.getByKey(key);
            model.addAttribute("pe", pe);
            model.addAttribute("applyId", applyId);
            model.addAttribute("taskId", taskId);
            // 根据流程配置得到跳转页面
            WfProcessStep wfProcessStep = processManager.findProcessStepFromTask(taskId, key);
            if (wfProcessStep != null) {
                return "wf/process/step/" + wfProcessStep.getPageName();
            } else {
                return "wf/process/step/exec";
            }

        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 进入流程图页面
     * 
     * @param key
     *            流程KEY
     * @param taskId
     *            任务编号
     * @return
     */
    @RequestMapping(value = "/flowChart")
    public String flowChart(String piId, ModelMap model) {
        model.addAttribute("piId", piId);
        // 得到当前节点信息
        try {
            List<String> userNameList = processManager.getAssignee(piId);
            List<TSysUser> userList = userManager.getUsersByLoginName(userNameList);
            StringBuffer nameBuffer = new StringBuffer();
            for (int i = 0; i < userList.size(); i++) {
                nameBuffer.append(userList.get(i).getName());
                if (i < userList.size() - 1) {
                    nameBuffer.append(",");
                }
            }
            model.addAttribute("assignee", nameBuffer);
            model.addAttribute("position", processManager.getActPosition(piId));
        } catch (Exception e) {
            log.error("JSONException", e);
        }
        return "wf/process/flowChart";
    }

    /**
     * 得到流程图
     * 
     * @param key
     *            流程KEY值
     * @param taskId
     *            任务编号
     * @param res
     * @return
     */
    @RequestMapping(value = "/getFlowChart")
    public String getFlowChart(String piId, HttpServletResponse res, ModelMap model) {
        try {
            InputStream is = processManager.getFlowChart(piId.split("\\.")[0]);
            if (is != null) {
                // 这个inputstream中的内容就是图片，直接输出到页面中
                byte[] pic = new byte[1024];
                int len = -1;
                while ((len = is.read(pic)) != -1) {
                    res.getOutputStream().write(pic, 0, len);
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 流程历史
     * 
     * @param key
     *            流程KEY
     * @param applyId
     *            申请单编号
     * @param model
     * @return
     */
    @RequestMapping(value = "/processHis/{key}/{applyId}")
    public String processHis(@PathVariable String key, @PathVariable String applyId, ModelMap model) {
        try {
            ProcessEnum pe = ProcessEnum.getByKey(key);
            IApplyCommonPo applyPo = processManager.getApply(pe.getApplyPo(), applyId);
            List<WfApplyStep> applyStepList = processManager.findApplyStepWithApplyId(applyId);
            model.addAttribute("applyPo", applyPo);
            model.addAttribute("applyStepList", applyStepList);
        } catch (Exception e) {
            log.error("", e);
        }
        return "wf/process/processHis";
    }

}
