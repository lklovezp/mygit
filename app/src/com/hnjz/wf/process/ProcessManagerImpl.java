package com.hnjz.wf.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Participation;
import org.jbpm.api.task.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.user.UserManager;
import com.hnjz.wf.ApplyCommonPo;
import com.hnjz.wf.IApplyCommonPo;
import com.hnjz.wf.bean.ProcessArgsBean;
import com.hnjz.wf.bean.ProcessInfoBean;
import com.hnjz.wf.business.INextActions;
import com.hnjz.wf.business.NextActionsFactory;
import com.hnjz.wf.dao.WfApplyStepDao;
import com.hnjz.wf.dao.WfProcessBusinessDao;
import com.hnjz.wf.dao.WfProcessStepDao;
import com.hnjz.wf.entity.WfApplyStep;
import com.hnjz.wf.entity.WfProcessBusiness;
import com.hnjz.wf.entity.WfProcessStep;
import com.hnjz.wf.entity.Wfdb;
import com.hnjz.wf.enums.ProcessEnum;

/**
 * ��������ҵ�����
 * 
 * @author zn
 * @version $Id: ProcessManagerImpl.java, v 0.1 2013-1-9 ����04:01:39
 *          Administrator Exp $
 */
@Service(value = "processManager")
@Transactional
public class ProcessManagerImpl implements ProcessManager, ApplyProcessManager {
    private Logger               log = Logger.getLogger(this.getClass());
    @Autowired
    private WfProcessBusinessDao processBusinessDAO;
    @Autowired
    private WfProcessStepDao     processStepDAO;
    @Autowired
    private WfApplyStepDao       applyStepDAO;
    @Autowired
    private ProcessEngine        processEngine;
    @Autowired
    private UserManager          userManager;

    public List<String> getStepNameList(String realPath, String deploymentId) throws Exception {
        // �õ�"/WEB-INF/jsp/wf/process/step"�ļ����µ����в���ҳ��
        File fileDir = new File(realPath + "/WEB-INF/jsp/wf/process/step");
        List<String> stepNameList = new ArrayList<String>();
        if (fileDir.exists() && fileDir.isDirectory()) {
            for (String fileName : fileDir.list()) {
                stepNameList.add(fileName.substring(0, fileName.indexOf(".")));
            }
        }
        return stepNameList;
    }

    @Override
    public void savePdSet(String json) throws Exception {
        JSONObject jsonProcessBusinessObj = new JSONObject(json);
        WfProcessBusiness wfProcessBusiness = getProcessBusinessFromJson(jsonProcessBusinessObj);
        // ��ѯ�������Ƿ��Ѿ�����������
        List<Criterion> cList = new ArrayList<Criterion>();
        cList.add(Restrictions.eq("processKey", wfProcessBusiness.getProcessKey()));
        List<WfProcessBusiness> pbList = processBusinessDAO.findByCondition(-1, -1, cList, null);
        if (pbList.size() > 0) {// ɾ������
            for (int i = 0; i < pbList.size(); i++) {
                processBusinessDAO.remove(pbList.get(i));
            }
        }
        // ���桰����ҵ��
        processBusinessDAO.save(wfProcessBusiness);
        // ���桰���衱
        JSONArray jsonStepsArr = jsonProcessBusinessObj.getJSONArray("steps");
        WfProcessStep wfProcessStep = null;
        for (int i = 0; i < jsonStepsArr.length(); i++) {
            wfProcessStep = getProcessStepFromJson(jsonStepsArr.getJSONObject(i), wfProcessBusiness);
            processStepDAO.save(wfProcessStep);

        }
    }

    public WfProcessBusiness findProcessBusinessFromProcessKey(String deploymentId)
                                                                                   throws Exception {
        Criterion c = Restrictions.eq("processKey", deploymentId);
        List<Criterion> cList = new ArrayList<Criterion>();
        cList.add(c);
        Map<String, Boolean> orderMap = new HashMap<String, Boolean>();
        orderMap.put("sortNum", true);
        List<WfProcessBusiness> list = processBusinessDAO.findByCondition(-1, -1, cList, orderMap);
        WfProcessBusiness wfProcessBusiness = null;
        if (list.size() > 0) {
            wfProcessBusiness = list.get(0);
        }
        return wfProcessBusiness;
    }

    public WfProcessStep findProcessStepFromTask(String taskId, String key) throws Exception {
        String hql = "from WfProcessStep m where m.wfProcessBusiness.businessKey=? and m.stepName=?";
        String stepName = processEngine.getTaskService().getTask(taskId).getActivityName();
        List<WfProcessStep> list = processStepDAO.find(hql, key, stepName);
        WfProcessStep wfProcessStep = null;
        if (list.size() > 0) {
            wfProcessStep = list.get(0);
        }
        return wfProcessStep;
    }

    public List<ProcessDefinition> getAllPdList() throws Exception {
        return processEngine.getRepositoryService().createProcessDefinitionQuery().list();
    }

    public List<ProcessInstance> getAllPiList() throws Exception {
        return processEngine.getExecutionService().createProcessInstanceQuery().list();
    }

    public void saveProcess(String key) throws Exception {
        ProcessEnum pe = ProcessEnum.getByKey(key);
        if (pe != null) {
            NewDeployment nd = null;
            if (StringUtils.isNotBlank(pe.getJpdlZipPath())) {
                File file = ResourceUtils.getFile("classpath:" + pe.getJpdlZipPath());
                if (file.exists()) {
                    nd = processEngine.getRepositoryService().createDeployment()
                        .addResourcesFromZipInputStream(
                            new ZipInputStream(new FileInputStream(file)));
                } else {
                    nd = processEngine.getRepositoryService().createDeployment()
                        .addResourceFromClasspath(pe.getJpdlPath());
                }
            } else {
                nd = processEngine.getRepositoryService().createDeployment()
                    .addResourceFromClasspath(pe.getJpdlPath());
            }
            nd.deploy();
        } else {
            throw new Exception("����δ���뵽ProcessEnum��");
        }
    }

    public void removeProcess(String deploymentId) throws Exception {
        /**************����ʱ�������ݱ�ʹ�ã���ʽʱ��Ҫ����ע��**************/
        //        String key = processEngine.getRepositoryService().createProcessDefinitionQuery()
        //            .deploymentId(deploymentId).uniqueResult().getKey();
        //        ProcessEnum pe = ProcessEnum.getByKey(key);
        //        if (pe != null) {
        //            //ɾ��������Ϣ
        //            StringBuffer delMainBuffer = new StringBuffer();
        //            delMainBuffer.append("delete from ").append(pe.getApplyPo().getName());
        //            int countMain = processBusinessDAO.execHQL(delMainBuffer.toString());
        //            log.debug("ɾ��" + pe.getApplyPo().getName() + "���ݣ�" + countMain);
        //            //ɾ������
        //            StringBuffer delStepBuffer = new StringBuffer();
        //            delStepBuffer.append("delete from ").append(WfApplyStep.class.getName());
        //            int countStep = processStepDAO.execHQL(delStepBuffer.toString());
        //            log.debug("ɾ��" + WfApplyStep.class.getName() + "���ݣ�" + countStep);
        //        }
        /**************����ʱ�������ݱ�ʹ�ã���ʽʱ��Ҫ����ע��**************/
        processEngine.getRepositoryService().deleteDeploymentCascade(deploymentId);
    }

    public void removeProcessIns(String piId) throws Exception {
        ProcessInstance t = processEngine.getExecutionService().createProcessInstanceQuery()
            .processInstanceId(piId).uniqueResult();
        if (null == t) {
            return;
        }
        String processDefinitionId = t.getProcessDefinitionId();
        String key = processEngine.getRepositoryService().createProcessDefinitionQuery()
            .processDefinitionId(processDefinitionId).uniqueResult().getKey();
        ProcessEnum pe = ProcessEnum.getByKey(key);
        processEngine.getExecutionService().deleteProcessInstanceCascade(piId);
        // �������̱�Ų�ѯ�����뵥
        String hql = "from " + pe.getApplyPo().getName() + " m where m.processId=?";
        List<? extends ApplyCommonPo> list = processBusinessDAO.find(hql, piId);
        StringBuffer delStepBuffer = null;
        for (int i = 0; i < list.size(); i++) {
            // ɾ��������Ϣ
            processBusinessDAO.remove(list.get(i));
            // ɾ������
            delStepBuffer = new StringBuffer();
            delStepBuffer.append("delete from ").append(WfApplyStep.class.getName()).append(
                " m where m.applyId='").append(list.get(i).getId()).append("'");
            int countStep = processStepDAO.execHQL(delStepBuffer.toString());
            log.debug("ɾ��" + pe.getApplyPo().getName() + ":" + list.get(i).getName() + ";" + "ɾ��"
                      + WfApplyStep.class.getName() + "���ݣ�" + countStep);
        }
    }

    public ProcessDefinition findPdByDeploymentId(String deploymentId) throws Exception {
        return processEngine.getRepositoryService().createProcessDefinitionQuery().deploymentId(
            deploymentId).uniqueResult();
    }

    public ProcessInstance startProcess(String key, String owner,
                                        Class<? extends ApplyCommonPo> applyClass, String applyId)
                                                                                                  throws Exception {
        Long count = processEngine.getRepositoryService().createProcessDefinitionQuery()
            .processDefinitionKey(key).count();
        ProcessInstance pi = null;
        if (count > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("owner", owner);
            pi = processEngine.getExecutionService().startProcessInstanceByKey(key, map);
            IApplyCommonPo iApplyCommonPo = (IApplyCommonPo) processBusinessDAO.get(applyClass,
                applyId);
            iApplyCommonPo.setProcessId(pi.getId());
            iApplyCommonPo.setTaskId(getActTaskFromPiId(pi.getId()).getId());
            iApplyCommonPo.setIsOver(false);
            processBusinessDAO.save(iApplyCommonPo);
        }
        return pi;
    }

    public void execProcess(String taskId, String opinion, Map<String, Object> varMap)
                                                                                      throws Exception {
        ProcessEnum pe = ProcessEnum.getByKey(processEngine.getTaskService().getTask(taskId)
            .getExecutionId().split("\\.")[0]);
        // ���������Ų�ѯ�����뵥
        String hql = "from " + pe.getApplyPo().getName() + " m where m.taskId like ?";
        List<? extends ApplyCommonPo> list = processBusinessDAO.find(hql, "%" + taskId + ",%");
        IApplyCommonPo iApplyCommonPo = null;
        if (list.size() > 0) {
            iApplyCommonPo = (IApplyCommonPo) list.get(0);
        }
        Task task = processEngine.getTaskService().getTask(taskId);
        // ����ִ�в���
        WfApplyStep wfApplyStep = new WfApplyStep();
        wfApplyStep.setApplyId(iApplyCommonPo.getId());
        wfApplyStep.setCreateTime(Calendar.getInstance().getTime());
        // wfApplyStep.setCreateUser(createUser);
        wfApplyStep.setExecUser(task.getAssignee());
        wfApplyStep.setIsActive("Y");
        wfApplyStep.setNote("");
        wfApplyStep.setOpinion(opinion);
        wfApplyStep.setResult("ͨ��");
        wfApplyStep.setOrder(task.getProgress());
        wfApplyStep.setTaskId(taskId);
        wfApplyStep.setTaskName(task.getName());
        applyStepDAO.save(wfApplyStep);
        // ��������
        processEngine.getTaskService().completeTask(taskId, varMap);
        /********************** �����������ŵ����뵥 ******************/
        Task currentTask = getActTaskFromPiId(iApplyCommonPo.getProcessId());
        if (currentTask != null) {// �õ���ǰ�������������ŷ������뵥
            iApplyCommonPo.setTaskId(currentTask.getId());
        } else {// ���̽�����¼��ʶ
            iApplyCommonPo.setIsOver(true);
        }
        processBusinessDAO.save(iApplyCommonPo);
    }

    public List<ProcessInfoBean> getUserTask(String userName) throws Exception {
        List<Task> taskList = processEngine.getTaskService().createTaskQuery().candidate(userName)
            .orderDesc(TaskQuery.PROPERTY_CREATEDATE).list();
        List<ProcessInfoBean> processInfoBeanList = new ArrayList<ProcessInfoBean>();
        ProcessInfoBean processInfoBean = null;
        for (int i = 0; i < taskList.size(); i++) {
            processInfoBean = this.getProcessInfoFromTask(taskList.get(i).getExecutionId().split(
                "\\.")[0], taskList.get(i).getId());
            if (processInfoBean == null) {
                continue;
            }
            processInfoBeanList.add(processInfoBean);
        }
        return processInfoBeanList;
    }

    public Task getActTaskFromPiId(String piId) throws Exception {
        Task task = null;
        List<Task> taskList = processEngine.getTaskService().createTaskQuery().processInstanceId(
            piId).list();
        if (taskList.size() > 0) {
            task = taskList.get(0);
        }
        return task;
    }

    public InputStream getFlowChart(String key) throws Exception {
        if (StringUtils.isNotBlank(key)) {
            ProcessDefinition pd = processEngine.getRepositoryService()
                .createProcessDefinitionQuery().processDefinitionKey(key).list().get(0);
            ProcessEnum pe = ProcessEnum.getByKey(key);
            return processEngine.getRepositoryService().getResourceAsStream(pd.getDeploymentId(),
                pe.getProcessImgName());
        }
        return null;
    }

    public Map<String, Object> getActPosition(String piId) throws Exception {
        // �õ����̶���
        ProcessInstance pi = processEngine.getExecutionService().createProcessInstanceQuery()
            .processInstanceId(piId).uniqueResult();
        if (pi != null) {
            ProcessDefinition pd = processEngine.getRepositoryService()
                .createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId())
                .uniqueResult();
            // �õ���ǰ����
            Task task = getActTaskFromPiId(piId);
            // �õ���ǰ����λ����Ϣ
            ActivityCoordinates act = processEngine.getRepositoryService().getActivityCoordinates(
                pd.getId(), task.getActivityName());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("x", act.getX());
            map.put("y", act.getY());
            map.put("w", act.getWidth());
            map.put("h", act.getHeight());
            return map;
        }
        return null;
    }

    public List<String> getAssignee(String piId) throws Exception {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isNotBlank(piId) && piId.indexOf(".") != -1) {
            Task task = getActTaskFromPiId(piId);
            if (null == task) {
                return list;
            }
            List<Participation> participationList = processEngine.getTaskService()
                .getTaskParticipations(task.getId());
            for (int i = 0; i < participationList.size(); i++) {
                list.add(participationList.get(i).getUserId());
            }
            String assignee = getActTaskFromPiId(piId).getAssignee();
            if (StringUtils.isNotBlank(assignee)) {
                list.add(assignee);
            }
        }
        return list;
    }

    public List<Task> getAllTaskList() throws Exception {
        return processEngine.getTaskService().createTaskQuery().list();
    }

    public List<WfApplyStep> findApplyStepWithApplyId(String applyId) throws Exception {
        // ����
        Criterion c = Restrictions.eq("applyId", applyId);
        List<Criterion> cList = new ArrayList<Criterion>();
        cList.add(c);
        // ����
        Map<String, Boolean> orderMap = new HashMap<String, Boolean>();
        orderMap.put("createTime", false);
        return applyStepDAO.findByCondition(-1, -1, cList, orderMap);
    }

    public IApplyCommonPo getApply(Class<? extends ApplyCommonPo> applyClass, String applyId)
                                                                                             throws Exception {
        return (IApplyCommonPo) processBusinessDAO.get(applyClass, applyId);
    }

    /**
     * ����JSON�õ�����ҵ�����
     * 
     * @param jsonObj
     * @return
     * @throws JSONException
     */
    private WfProcessBusiness getProcessBusinessFromJson(JSONObject jsonObj) throws JSONException {
        String deploymentId = jsonObj.getString("deploymentId");
        String business = jsonObj.getString("business");
        return new WfProcessBusiness(deploymentId, business);
    }

    /**
     * ����JSON�õ����̲������
     * 
     * @param jsonObj
     * @param deploymentId
     * @return
     * @throws JSONException
     */
    private WfProcessStep getProcessStepFromJson(JSONObject jsonObj,
                                                 WfProcessBusiness wfProcessBusiness)
                                                                                     throws JSONException {
        String stepName = jsonObj.getString("name");
        String pageName = jsonObj.getString("page");
        return new WfProcessStep(wfProcessBusiness, stepName, pageName);
    }

    /**
     * ���������Ϣ
     * @param key   ����KEY
     * @param taskId    ������
     * @return
     * @throws Exception
     */
    private ProcessInfoBean getProcessInfoFromTask(String key, String taskId) throws Exception {
        // �õ�������Ϣ
        ProcessEnum pe = ProcessEnum.getByKey(key);
        // ���������Ų�ѯ�����뵥����
        String hql = "from " + pe.getApplyPo().getName() + " m where m.taskId like ?";
        List<? extends ApplyCommonPo> list = processBusinessDAO.find(hql, "%" + taskId + ",%");
        IApplyCommonPo model = null;
        if (list.size() > 0) {
            model = list.get(0);
        }
        if (model == null) {// δ��ѯ�����󷵻�NULL
            return null;
        }
        // �õ���ǰ����
        Task task = processEngine.getTaskService().getTask(taskId);
        // �õ��²�����
        INextActions nextActions = NextActionsFactory.getInstance(key);
        return new ProcessInfoBean(model.getId(), model.getName(), "",
            model.getProcessId(), pe.getProcessKey(), taskId, task.getName(), nextActions
                .getActions(model.getNextActions() == null ? "" : model.getNextActions()), model
                .getCode(), list.get(0).getEndTime());
    }

    /************************����ʹ��*************************/
    public ProcessInstance saveStart(String key, IApplyCommonPo applyPo, ProcessArgsBean bean)
                                                                                              throws Exception {
        Long count = processEngine.getRepositoryService().createProcessDefinitionQuery()
            .processDefinitionKey(key).count();
        ProcessInstance pi = null;
        if (count > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("argsBean", bean);
            pi = processEngine.getExecutionService().startProcessInstanceByKey(key, map);
            log.debug("�������� pi:" + pi.getId());
            applyPo.setProcessId(pi.getId());
            applyPo.setIsOver(false);
            saveCommonPo(applyPo);
        } else {
            throw new Exception("δ��������");
        }
        return pi;
    }

    public void saveNext(String key, String taskId, IApplyCommonPo applyPo, ProcessArgsBean bean)
                                                                                                 throws Exception {
        // �õ���ǰ����
        Task currentTask = processEngine.getTaskService().getTask(taskId);
        // ����ִ�в���
        WfApplyStep wfApplyStep = new WfApplyStep();
        wfApplyStep.setApplyId(applyPo.getId());
        wfApplyStep.setCreateTime(Calendar.getInstance().getTime());
        wfApplyStep.setCreateUser(bean.getCurrentOper());
        if (CtxUtil.getCurUser() != null) {
            wfApplyStep.setExecUser(CtxUtil.getCurUser().getName());
        }
        wfApplyStep.setIsActive("Y");
        wfApplyStep.setNote("");
        wfApplyStep.setOpinion(bean.getOpinion());
        wfApplyStep.setResult(bean.getResult());
        if(null != currentTask && null != currentTask.getProgress()){
        	wfApplyStep.setOrder(currentTask.getProgress());
        }
        wfApplyStep.setTaskId(taskId);
        if(null != currentTask && null != currentTask.getName()){
        	wfApplyStep.setTaskName(currentTask.getName());
        }
        applyStepDAO.save(wfApplyStep);
        //������ǰ����
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("argsBean", bean);
        processEngine.getTaskService().completeTask(taskId, map);
        //�ı����뵥��������Ϣ
        saveCommonPo(applyPo);
    }

    public void saveAddGroupUser(String taskId, String[] oldUserNames, String[] newUserNames)
                                                                                             throws Exception {
        for (int i = 0; i < oldUserNames.length; i++) {
            processEngine.getTaskService().removeTaskParticipatingUser(taskId, oldUserNames[i],
                Participation.CANDIDATE);
        }
        for (int i = 0; i < newUserNames.length; i++) {
            processEngine.getTaskService().addTaskParticipatingUser(taskId, newUserNames[i],
                Participation.CANDIDATE);
        }
    }

    /**
     * ���������е�������Ϣ
     * @param applyPo
     * @throws Exception
     */
    private void saveCommonPo(IApplyCommonPo applyPo) throws Exception {
        List<Task> taskList = processEngine.getTaskService().createTaskQuery().processInstanceId(
            applyPo.getProcessId()).list();
        Set<String> ids = new HashSet<String>();
        for(Task ele : taskList){
            if (log.isDebugEnabled()) {
                log.debug("TaskId:"+ele.getId());
            }
            ids.add(ele.getId());
        }
        if (taskList.size() > 0) {//��������
            List<String> operList = new ArrayList<String>();
            Wfdb db = new Wfdb();
            for (String taskId : ids) {
                db = new Wfdb();
                db.setTaskId(Integer.parseInt(taskId));
                db.setWorkId(applyPo.getId());
                this.applyStepDAO.save(db);
                operList.addAll(getAssignee(applyPo.getProcessId()));
            }
            String names = userManager.getUserNames(operList);
            applyPo.setNextOper(names);
        } else {//�޻�������̽���������������ʶ�������뵥����,��ձ�Wfdb
            String hsql = "from Wfdb where workId = ?";
            this.applyStepDAO.removeFindObjs(hsql, applyPo.getId());
            applyPo.setTaskId(null);
            applyPo.setNextOper(null);
            //applyPo.setProcessId(null);
            applyPo.setNextActions(null);
            //applyPo.setIsOver(true);
        }
        this.applyStepDAO.save(applyPo);
    }
    
    /**
     * 
     * �������ܣ���ȡ��һ��������name
    
     * ���������
    
     * ����ֵ��
     * @throws Exception 
     */
    public String getNextOperName(IApplyCommonPo applyPo) throws Exception{
    	List<String> operList = new ArrayList<String>();
        operList.addAll(getAssignee(applyPo.getProcessId()));
        String names = userManager.getUserNames(operList);
    	return names;
    }
}