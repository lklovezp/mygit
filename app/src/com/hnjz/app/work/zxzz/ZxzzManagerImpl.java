package com.hnjz.app.work.zxzz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.po.TBizAutomoniter;
import com.hnjz.app.work.po.TBizBlmbcs;
import com.hnjz.app.work.po.TBizTaskandtasktype;
import com.hnjz.app.work.po.TBizTaskuser;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.BlMainForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.PoiUtil;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

@Service("zxzzManagerImpl")
public class ZxzzManagerImpl extends ManagerImpl implements ZxzzManager ,ServletContextAware {
	
	/** ��־ */
	private static final Log log = LogFactory.getLog(FileUpDownUtil.class);

	public String path;
	
	private ServletContext sc;
	
	@Autowired
    private CommonManager                 commonManager;
	@Autowired
	private CommWorkManager commWorkManager;
	@Autowired
    protected WorkManagerImpl workManager;
	@Autowired
	private UserManager userManager;
	
	@Override
	public void saveXwbl(ZxzzForm zxzzForm,String applyId,String wflx){
		TSysUser cur = CtxUtil.getCurUser();
		JSONArray b = new JSONArray();
		List<String> list = new ArrayList<String>();
		if(wflx.equals("101") || wflx.equals("201") || wflx.equals("301")){
			list.add(zxzzForm.getSf10000001());
			list.add(zxzzForm.getSf10000002());
			list.add(zxzzForm.getSf10000003());
			list.add(zxzzForm.getSf10000004());
			list.add(zxzzForm.getSf10000005());
			list.add(zxzzForm.getSf10000006());
			list.add(zxzzForm.getSf10000007());
			list.add(zxzzForm.getSf10000008());
			list.add(zxzzForm.getSf10000009());
			list.add(zxzzForm.getSf10000010());
			list.add(zxzzForm.getSf10000011());
			list.add(zxzzForm.getSf10000012());
			list.add(zxzzForm.getSf10000013());
			list.add(zxzzForm.getSf10000014());
			list.add(zxzzForm.getSf10000015());
			list.add(zxzzForm.getSf10000016());
			list.add(zxzzForm.getSf10000017());
			list.add(zxzzForm.getSf10000018());
			list.add(zxzzForm.getSf10000019());
			list.add(zxzzForm.getSf10000020());
			list.add(zxzzForm.getSf10000021());
			list.add(zxzzForm.getSf10000022());
			list.add(zxzzForm.getSf10000023());
			list.add(zxzzForm.getSf10000024());
			list.add(zxzzForm.getSf10000025());
			list.add(zxzzForm.getSf10000026());
			list.add(zxzzForm.getSf10000027());
			list.add(zxzzForm.getSf10000028());
			list.add(zxzzForm.getSf10000029());
			list.add(zxzzForm.getSf10000030());
			list.add(zxzzForm.getSf10000031());
			list.add(zxzzForm.getSf10000032());
			list.add(zxzzForm.getSf10000033());
			list.add(zxzzForm.getSf10000034());
			list.add(zxzzForm.getSf10000035());
			list.add(zxzzForm.getSf10000036());
			list.add(zxzzForm.getSf10000037());
			list.add(zxzzForm.getSf10000038());
			list.add(zxzzForm.getSf10000039());
			list.add(zxzzForm.getSf10000040());
			list.add(zxzzForm.getSf10000041());
			list.add(zxzzForm.getSf10000042());
			list.add(zxzzForm.getSf10000043());
			list.add(zxzzForm.getSf10000044());
			list.add(zxzzForm.getSf10000045());
			list.add(zxzzForm.getSf10000046());
			list.add(zxzzForm.getSf10000047());
			list.add(zxzzForm.getSf10000048());
			list.add(zxzzForm.getSf10000049());
			list.add(zxzzForm.getSf10000050());
			list.add(zxzzForm.getSf10000051());
			list.add(zxzzForm.getSf10000052());
			try {
				for(int k=0; k<list.size();k++){
					JSONObject a =  new JSONObject();
					if(list.get(k) != null && list.get(k) != ""){
						a.put("ans", list.get(k).toString());
						b.put(a);
					}else{
						a.put("ans", "999");
						b.put(a);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int j = 10000000;
			TBizAutomoniter tBizAutomoniter=null;
			List<TBizAutomoniter> tBizAutomoniterList= this.getList(applyId, wflx);
			if(tBizAutomoniterList != null && tBizAutomoniterList.size() == 52){
				for(int d = 0; d<tBizAutomoniterList.size();d++){
					tBizAutomoniter = tBizAutomoniterList.get(d);
					try {
						tBizAutomoniter.setAns(b.getJSONObject(d).getString("ans"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע�޸ĵ�ֵ
					tBizAutomoniter.setUpdateby(cur);
					tBizAutomoniter.setUpdated(new Date());
					dao.save(tBizAutomoniter);
				}
			}else{
				for(int i=0; i<b.length();i++){
					j++;
					try {
						JSONObject c = b.getJSONObject(i);
						//��һ������ֵ
						tBizAutomoniter = new TBizAutomoniter();
						tBizAutomoniter.setCreateby(cur);
						tBizAutomoniter.setCreated(new Date());
						tBizAutomoniter.setUpdateby(cur);
						tBizAutomoniter.setUpdated(new Date());
						tBizAutomoniter.setAnsType("2");//"1 ��� 2 ��ѡ 3 ��ѡ"
						tBizAutomoniter.setTemplateCode(wflx);//��Ӧ��ģ����
						tBizAutomoniter.setTaskId(applyId);//��Ӧ������id
						tBizAutomoniter.setIsActive(YnEnum.Y.getCode());
						tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע
						tBizAutomoniter.setVersion(0);
						if(StringUtils.isNotBlank(c.getString("ans"))){
							tBizAutomoniter.setAns(c.getString("ans"));
						}
							tBizAutomoniter.setQueseCode(String.valueOf(j));
							dao.save(tBizAutomoniter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		} else if(wflx.equals("102") || wflx.equals("202") || wflx.equals("302")){
			list.add(zxzzForm.getSf10000001());
			list.add(zxzzForm.getSf10000002());
			list.add(zxzzForm.getSf10000003());
			list.add(zxzzForm.getSf10000004());
			list.add(zxzzForm.getSf10000005());
			list.add(zxzzForm.getSf10000006());
			list.add(zxzzForm.getSf10000007());
			list.add(zxzzForm.getSf10000008());
			list.add(this.CheckSpace(zxzzForm.getSf10000009()));
			list.add(this.CheckSpace(zxzzForm.getSf10000010()));
			list.add(this.CheckSpace(zxzzForm.getSf10000011()));
			list.add(this.CheckSpace(zxzzForm.getSf10000012()));
			list.add(this.CheckSpace(zxzzForm.getSf10000013()));
			list.add(this.CheckSpace(zxzzForm.getSf10000014()));
			list.add(this.CheckSpace(zxzzForm.getSf10000015()));
			list.add(this.CheckSpace(zxzzForm.getSf10000016()));
			list.add(this.CheckSpace(zxzzForm.getSf10000017()));
			list.add(this.CheckSpace(zxzzForm.getSf10000018()));
			list.add(this.CheckSpace(zxzzForm.getSf10000019()));
			list.add(this.CheckSpace(zxzzForm.getSf10000020()));
			list.add(this.CheckSpace(zxzzForm.getSf10000021()));
			list.add(this.CheckSpace(zxzzForm.getSf10000022()));
			
			try {
				for(int k=0; k<list.size();k++){
					JSONObject a =  new JSONObject();
					if(list.get(k) != null && list.get(k) != ""){
						a.put("ans", list.get(k).toString());
						b.put(a);
					}else{
						a.put("ans", "999");
						b.put(a);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int j = 10000052;
			TBizAutomoniter tBizAutomoniter=null;
			List<TBizAutomoniter> tBizAutomoniterList= this.getList(applyId, wflx);
			if(tBizAutomoniterList != null && tBizAutomoniterList.size() == 22){
				for(int d = 0; d<tBizAutomoniterList.size();d++){
					tBizAutomoniter = tBizAutomoniterList.get(d);
					try {
						tBizAutomoniter.setAns(b.getJSONObject(d).getString("ans"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע�޸ĵ�ֵ
					tBizAutomoniter.setUpdateby(cur);
					tBizAutomoniter.setUpdated(new Date());
					dao.save(tBizAutomoniter);
				}
			}else{
				for(int i=0; i<b.length();i++){
					j++;
					try {
						JSONObject c = b.getJSONObject(i);
						//��һ������ֵ
						tBizAutomoniter = new TBizAutomoniter();
						tBizAutomoniter.setCreateby(cur);
						tBizAutomoniter.setCreated(new Date());
						tBizAutomoniter.setUpdateby(cur);
						tBizAutomoniter.setUpdated(new Date());
						if(c.getString("ans").equals("true") || c.getString("ans").equals("false") || c.getString("ans").equals("999")){
							tBizAutomoniter.setAnsType("2");//"1 ��� 2 ��ѡ 3 ��ѡ"
						} else {
							tBizAutomoniter.setAnsType("3");//"1 ��� 2 ��ѡ 3 ��ѡ"
						}
						tBizAutomoniter.setTemplateCode(wflx);//��Ӧ��ģ����
						tBizAutomoniter.setTaskId(applyId);//��Ӧ������id
						tBizAutomoniter.setIsActive(YnEnum.Y.getCode());
						tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע
						tBizAutomoniter.setVersion(0);
						if(StringUtils.isNotBlank(c.getString("ans"))){
							tBizAutomoniter.setAns(c.getString("ans"));
						}
						tBizAutomoniter.setQueseCode(String.valueOf(j));
						dao.save(tBizAutomoniter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		} else if(wflx.equals("103")){
			list.add(zxzzForm.getSf10000001());
			list.add(zxzzForm.getSf10000002());
			list.add(zxzzForm.getSf10000003());
			list.add(zxzzForm.getSf10000004());
			list.add(zxzzForm.getSf10000005());
			list.add(zxzzForm.getSf10000006());
			list.add(zxzzForm.getSf10000007());
			list.add(zxzzForm.getSf10000008());
			list.add(zxzzForm.getSf10000009());
			list.add(zxzzForm.getSf10000010());
			list.add(zxzzForm.getSf10000011());
			list.add(zxzzForm.getSf10000012());
			list.add(this.CheckSpace(zxzzForm.getSf10000013()));
			list.add(this.CheckSpace(zxzzForm.getSf10000014()));
			list.add(this.CheckSpace(zxzzForm.getSf10000015()));
			list.add(this.CheckSpace(zxzzForm.getSf10000016()));
			list.add(this.CheckSpace(zxzzForm.getSf10000017()));
			list.add(this.CheckSpace(zxzzForm.getSf10000018()));
			list.add(this.CheckSpace(zxzzForm.getSf10000019()));
			list.add(this.CheckSpace(zxzzForm.getSf10000020()));
			list.add(this.CheckSpace(zxzzForm.getSf10000021()));
			list.add(this.CheckSpace(zxzzForm.getSf10000022()));
			list.add(this.CheckSpace(zxzzForm.getSf10000023()));
			list.add(this.CheckSpace(zxzzForm.getSf10000024()));
			list.add(this.CheckSpace(zxzzForm.getSf10000025()));
			try {
				for(int k=0; k<list.size();k++){
					JSONObject a =  new JSONObject();
					if(list.get(k) != null && list.get(k) != ""){
						a.put("ans", list.get(k).toString());
						b.put(a);
					}else{
						a.put("ans", "999");
						b.put(a);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int j = 10000074;
			TBizAutomoniter tBizAutomoniter=null;
			List<TBizAutomoniter> tBizAutomoniterList= this.getList(applyId, wflx);
			if(tBizAutomoniterList != null && tBizAutomoniterList.size() == 25){
				for(int d = 0; d<tBizAutomoniterList.size();d++){
					tBizAutomoniter = tBizAutomoniterList.get(d);
					try {
						tBizAutomoniter.setAns(b.getJSONObject(d).getString("ans"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע�޸ĵ�ֵ
					tBizAutomoniter.setUpdateby(cur);
					tBizAutomoniter.setUpdated(new Date());
					dao.save(tBizAutomoniter);
				}
			}else{
				for(int i=0; i<b.length();i++){
					j++;
					try {
						JSONObject c = b.getJSONObject(i);
						//��һ������ֵ
						tBizAutomoniter = new TBizAutomoniter();
						tBizAutomoniter.setCreateby(cur);
						tBizAutomoniter.setCreated(new Date());
						tBizAutomoniter.setUpdateby(cur);
						tBizAutomoniter.setUpdated(new Date());
						if(c.getString("ans").equals("true") || c.getString("ans").equals("false") || c.getString("ans").equals("999")){
							tBizAutomoniter.setAnsType("2");//"1 ��� 2 ��ѡ 3 ��ѡ"
						} else {
							tBizAutomoniter.setAnsType("3");//"1 ��� 2 ��ѡ 3 ��ѡ"
						}
						tBizAutomoniter.setTemplateCode(wflx);//��Ӧ��ģ����
						tBizAutomoniter.setTaskId(applyId);//��Ӧ������id
						tBizAutomoniter.setIsActive(YnEnum.Y.getCode());
						tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע
						tBizAutomoniter.setVersion(0);
						if(StringUtils.isNotBlank(c.getString("ans"))){
							tBizAutomoniter.setAns(c.getString("ans"));
						}
						tBizAutomoniter.setQueseCode(String.valueOf(j));
						dao.save(tBizAutomoniter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(wflx.equals("203") || wflx.equals("304")){
			list.add(zxzzForm.getSf10000001());
			list.add(zxzzForm.getSf10000002());
			list.add(zxzzForm.getSf10000003());
			list.add(zxzzForm.getSf10000004());
			list.add(zxzzForm.getSf10000005());
			list.add(zxzzForm.getSf10000006());
			list.add(zxzzForm.getSf10000007());
			list.add(zxzzForm.getSf10000008());
			list.add(zxzzForm.getSf10000009());
			list.add(zxzzForm.getSf10000010());
			list.add(zxzzForm.getSf10000011());
			list.add(this.CheckSpace(zxzzForm.getSf10000012()));
			list.add(this.CheckSpace(zxzzForm.getSf10000013()));
			list.add(this.CheckSpace(zxzzForm.getSf10000014()));
			list.add(this.CheckSpace(zxzzForm.getSf10000015()));
			list.add(this.CheckSpace(zxzzForm.getSf10000016()));
			list.add(this.CheckSpace(zxzzForm.getSf10000017()));
			list.add(this.CheckSpace(zxzzForm.getSf10000018()));
			list.add(this.CheckSpace(zxzzForm.getSf10000019()));
			list.add(this.CheckSpace(zxzzForm.getSf10000020()));
			list.add(this.CheckSpace(zxzzForm.getSf10000021()));
			list.add(this.CheckSpace(zxzzForm.getSf10000022()));
			list.add(this.CheckSpace(zxzzForm.getSf10000023()));
			list.add(this.CheckSpace(zxzzForm.getSf10000024()));
			list.add(this.CheckSpace(zxzzForm.getSf10000025()));
			list.add(this.CheckSpace(zxzzForm.getSf10000026()));
			list.add(this.CheckSpace(zxzzForm.getSf10000027()));
			list.add(this.CheckSpace(zxzzForm.getSf10000028()));
			list.add(this.CheckSpace(zxzzForm.getSf10000029()));
			list.add(this.CheckSpace(zxzzForm.getSf10000030()));
			list.add(this.CheckSpace(zxzzForm.getSf10000031()));
			list.add(this.CheckSpace(zxzzForm.getSf10000032()));
			try {
				for(int k=0; k<list.size();k++){
					JSONObject a =  new JSONObject();
					if(list.get(k) != null && list.get(k) != ""){
						a.put("ans", list.get(k).toString());
						b.put(a);
					}else{
						a.put("ans", "999");
						b.put(a);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int j = 10000099;
			TBizAutomoniter tBizAutomoniter=null;
			List<TBizAutomoniter> tBizAutomoniterList= this.getList(applyId, wflx);
			if(tBizAutomoniterList != null && tBizAutomoniterList.size() == 32){
				for(int d = 0; d<tBizAutomoniterList.size();d++){
					tBizAutomoniter = tBizAutomoniterList.get(d);
					try {
						tBizAutomoniter.setAns(b.getJSONObject(d).getString("ans"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע�޸ĵ�ֵ
					tBizAutomoniter.setUpdateby(cur);
					tBizAutomoniter.setUpdated(new Date());
					dao.save(tBizAutomoniter);
				}
			}else{
				for(int i=0; i<b.length();i++){
					j++;
					try {
						JSONObject c = b.getJSONObject(i);
						//��һ������ֵ
						tBizAutomoniter = new TBizAutomoniter();
						tBizAutomoniter.setCreateby(cur);
						tBizAutomoniter.setCreated(new Date());
						tBizAutomoniter.setUpdateby(cur);
						tBizAutomoniter.setUpdated(new Date());
						if(c.getString("ans").equals("true") || c.getString("ans").equals("false") || c.getString("ans").equals("999")){
							tBizAutomoniter.setAnsType("2");//"1 ��� 2 ��ѡ 3 ��ѡ"
						} else {
							tBizAutomoniter.setAnsType("3");//"1 ��� 2 ��ѡ 3 ��ѡ"
						}
						tBizAutomoniter.setTemplateCode(wflx);//��Ӧ��ģ����
						tBizAutomoniter.setTaskId(applyId);//��Ӧ������id
						tBizAutomoniter.setIsActive(YnEnum.Y.getCode());
						tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע
						tBizAutomoniter.setVersion(0);
						if(StringUtils.isNotBlank(c.getString("ans"))){
							tBizAutomoniter.setAns(c.getString("ans"));
						}
						tBizAutomoniter.setQueseCode(String.valueOf(j));
						dao.save(tBizAutomoniter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(wflx.equals("303")){
			list.add(zxzzForm.getSf10000001());
			list.add(zxzzForm.getSf10000002());
			list.add(zxzzForm.getSf10000003());
			list.add(zxzzForm.getSf10000004());
			list.add(zxzzForm.getSf10000005());
			list.add(zxzzForm.getSf10000006());
			list.add(zxzzForm.getSf10000007());
			list.add(zxzzForm.getSf10000008());
			list.add(zxzzForm.getSf10000009());
			list.add(zxzzForm.getSf10000010());
			list.add(zxzzForm.getSf10000011());
			list.add(zxzzForm.getSf10000012());
			list.add(this.CheckSpace(zxzzForm.getSf10000013()));
			list.add(this.CheckSpace(zxzzForm.getSf10000014()));
			list.add(this.CheckSpace(zxzzForm.getSf10000015()));
			list.add(this.CheckSpace(zxzzForm.getSf10000016()));
			list.add(this.CheckSpace(zxzzForm.getSf10000017()));
			list.add(this.CheckSpace(zxzzForm.getSf10000018()));
			list.add(this.CheckSpace(zxzzForm.getSf10000019()));
			list.add(this.CheckSpace(zxzzForm.getSf10000020()));
			list.add(this.CheckSpace(zxzzForm.getSf10000021()));
			list.add(this.CheckSpace(zxzzForm.getSf10000022()));
			list.add(this.CheckSpace(zxzzForm.getSf10000023()));
			list.add(this.CheckSpace(zxzzForm.getSf10000024()));
			list.add(this.CheckSpace(zxzzForm.getSf10000025()));
			list.add(this.CheckSpace(zxzzForm.getSf10000026()));
			list.add(this.CheckSpace(zxzzForm.getSf10000027()));
			list.add(this.CheckSpace(zxzzForm.getSf10000028()));
			list.add(this.CheckSpace(zxzzForm.getSf10000029()));
			list.add(this.CheckSpace(zxzzForm.getSf10000030()));
			list.add(this.CheckSpace(zxzzForm.getSf10000031()));
			try {
				for(int k=0; k<list.size();k++){
					JSONObject a =  new JSONObject();
					if(list.get(k) != null && list.get(k) != ""){
						a.put("ans", list.get(k).toString());
						b.put(a);
					}else{
						a.put("ans", "999");
						b.put(a);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int j = 10000131;
			TBizAutomoniter tBizAutomoniter=null;
			List<TBizAutomoniter> tBizAutomoniterList= this.getList(applyId, wflx);
			if(tBizAutomoniterList != null && tBizAutomoniterList.size() == 31){
				for(int d = 0; d<tBizAutomoniterList.size();d++){
					tBizAutomoniter = tBizAutomoniterList.get(d);
					try {
						tBizAutomoniter.setAns(b.getJSONObject(d).getString("ans"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע�޸ĵ�ֵ
					tBizAutomoniter.setUpdateby(cur);
					tBizAutomoniter.setUpdated(new Date());
					dao.save(tBizAutomoniter);
				}
			}else{
				for(int i=0; i<b.length();i++){
					j++;
					try {
						JSONObject c = b.getJSONObject(i);
						//��һ������ֵ
						tBizAutomoniter = new TBizAutomoniter();
						tBizAutomoniter.setCreateby(cur);
						tBizAutomoniter.setCreated(new Date());
						tBizAutomoniter.setUpdateby(cur);
						tBizAutomoniter.setUpdated(new Date());
						if(c.getString("ans").equals("true") || c.getString("ans").equals("false") || c.getString("ans").equals("999")){
							tBizAutomoniter.setAnsType("2");//"1 ��� 2 ��ѡ 3 ��ѡ"
						} else {
							tBizAutomoniter.setAnsType("3");//"1 ��� 2 ��ѡ 3 ��ѡ"
						}
						tBizAutomoniter.setTemplateCode(wflx);//��Ӧ��ģ����
						tBizAutomoniter.setTaskId(applyId);//��Ӧ������id
						tBizAutomoniter.setIsActive(YnEnum.Y.getCode());
						tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע
						tBizAutomoniter.setVersion(0);
						if(StringUtils.isNotBlank(c.getString("ans"))){
							tBizAutomoniter.setAns(c.getString("ans"));
						}
						tBizAutomoniter.setQueseCode(String.valueOf(j));
						dao.save(tBizAutomoniter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		} else if(wflx.equals("204")){
			list.add(zxzzForm.getSf10000001());
			list.add(zxzzForm.getSf10000002());
			list.add(zxzzForm.getSf10000003());
			list.add(zxzzForm.getSf10000004());
			list.add(zxzzForm.getSf10000005());
			list.add(zxzzForm.getSf10000006());
			list.add(zxzzForm.getSf10000007());
			list.add(this.CheckSpace(zxzzForm.getSf10000008()));
			list.add(this.CheckSpace(zxzzForm.getSf10000009()));
			list.add(this.CheckSpace(zxzzForm.getSf10000010()));
			list.add(this.CheckSpace(zxzzForm.getSf10000011()));
			list.add(this.CheckSpace(zxzzForm.getSf10000012()));
			list.add(this.CheckSpace(zxzzForm.getSf10000013()));
			list.add(this.CheckSpace(zxzzForm.getSf10000014()));
			list.add(this.CheckSpace(zxzzForm.getSf10000015()));
			list.add(this.CheckSpace(zxzzForm.getSf10000016()));
			list.add(this.CheckSpace(zxzzForm.getSf10000017()));
			list.add(this.CheckSpace(zxzzForm.getSf10000018()));
			list.add(this.CheckSpace(zxzzForm.getSf10000019()));
			list.add(this.CheckSpace(zxzzForm.getSf10000020()));
			
			try {
				for(int k=0; k<list.size();k++){
					JSONObject a =  new JSONObject();
					if(list.get(k) != null && list.get(k) != ""){
						a.put("ans", list.get(k).toString());
						b.put(a);
					}else{
						a.put("ans", "999");
						b.put(a);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int j = 10000162;
			TBizAutomoniter tBizAutomoniter=null;
			List<TBizAutomoniter> tBizAutomoniterList= this.getList(applyId, wflx);
			if(tBizAutomoniterList != null && tBizAutomoniterList.size() == 20){
				for(int d = 0; d<tBizAutomoniterList.size();d++){
					tBizAutomoniter = tBizAutomoniterList.get(d);
					try {
						tBizAutomoniter.setAns(b.getJSONObject(d).getString("ans"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע�޸ĵ�ֵ
					tBizAutomoniter.setUpdateby(cur);
					tBizAutomoniter.setUpdated(new Date());
					dao.save(tBizAutomoniter);
				}
			}else{
				for(int i=0; i<b.length();i++){
					j++;
					try {
						JSONObject c = b.getJSONObject(i);
						//��һ������ֵ
						tBizAutomoniter = new TBizAutomoniter();
						tBizAutomoniter.setCreateby(cur);
						tBizAutomoniter.setCreated(new Date());
						tBizAutomoniter.setUpdateby(cur);
						tBizAutomoniter.setUpdated(new Date());
						if(c.getString("ans").equals("true") || c.getString("ans").equals("false") || c.getString("ans").equals("999")){
							tBizAutomoniter.setAnsType("2");//"1 ��� 2 ��ѡ 3 ��ѡ"
						} else {
							tBizAutomoniter.setAnsType("3");//"1 ��� 2 ��ѡ 3 ��ѡ"
						}
						tBizAutomoniter.setTemplateCode(wflx);//��Ӧ��ģ����
						tBizAutomoniter.setTaskId(applyId);//��Ӧ������id
						tBizAutomoniter.setIsActive(YnEnum.Y.getCode());
						tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע
						tBizAutomoniter.setVersion(0);
						if(StringUtils.isNotBlank(c.getString("ans"))){
							tBizAutomoniter.setAns(c.getString("ans"));
						}
						tBizAutomoniter.setQueseCode(String.valueOf(j));
						dao.save(tBizAutomoniter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(wflx.equals("205") || wflx.equals("305")){
			list.add(zxzzForm.getSf10000001());
			list.add(zxzzForm.getSf10000002());
			list.add(zxzzForm.getSf10000003());
			list.add(zxzzForm.getSf10000004());
			list.add(zxzzForm.getSf10000005());
			list.add(zxzzForm.getSf10000006());
			list.add(zxzzForm.getSf10000007());
			list.add(zxzzForm.getSf10000008());
			list.add(zxzzForm.getSf10000009());
			list.add(zxzzForm.getSf10000010());
			list.add(zxzzForm.getSf10000011());
			list.add(this.CheckSpace(zxzzForm.getSf10000012()));
			list.add(this.CheckSpace(zxzzForm.getSf10000013()));
			list.add(this.CheckSpace(zxzzForm.getSf10000014()));
			list.add(this.CheckSpace(zxzzForm.getSf10000015()));
			list.add(this.CheckSpace(zxzzForm.getSf10000016()));
			list.add(this.CheckSpace(zxzzForm.getSf10000017()));
			list.add(this.CheckSpace(zxzzForm.getSf10000018()));
			list.add(this.CheckSpace(zxzzForm.getSf10000019()));
			list.add(this.CheckSpace(zxzzForm.getSf10000020()));
			list.add(this.CheckSpace(zxzzForm.getSf10000021()));
			list.add(this.CheckSpace(zxzzForm.getSf10000022()));
			list.add(this.CheckSpace(zxzzForm.getSf10000023()));
			list.add(this.CheckSpace(zxzzForm.getSf10000024()));
			list.add(this.CheckSpace(zxzzForm.getSf10000025()));
			list.add(this.CheckSpace(zxzzForm.getSf10000026()));
			try {
				for(int k=0; k<list.size();k++){
					JSONObject a =  new JSONObject();
					if(list.get(k) != null && list.get(k) != ""){
						a.put("ans", list.get(k).toString());
						b.put(a);
					}else{
						a.put("ans", "999");
						b.put(a);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int j = 10000182;
			TBizAutomoniter tBizAutomoniter=null;
			List<TBizAutomoniter> tBizAutomoniterList= this.getList(applyId, wflx);
			if(tBizAutomoniterList != null && tBizAutomoniterList.size() == 26){
				for(int d = 0; d<tBizAutomoniterList.size();d++){
					tBizAutomoniter = tBizAutomoniterList.get(d);
					try {
						tBizAutomoniter.setAns(b.getJSONObject(d).getString("ans"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע�޸ĵ�ֵ
					tBizAutomoniter.setUpdateby(cur);
					tBizAutomoniter.setUpdated(new Date());
					dao.save(tBizAutomoniter);
				}
			}else{
				for(int i=0; i<b.length();i++){
					j++;
					try {
						JSONObject c = b.getJSONObject(i);
						//��һ������ֵ
						tBizAutomoniter = new TBizAutomoniter();
						tBizAutomoniter.setCreateby(cur);
						tBizAutomoniter.setCreated(new Date());
						tBizAutomoniter.setUpdateby(cur);
						tBizAutomoniter.setUpdated(new Date());
						if(c.getString("ans").equals("true") || c.getString("ans").equals("false") || c.getString("ans").equals("999")){
							tBizAutomoniter.setAnsType("2");//"1 ��� 2 ��ѡ 3 ��ѡ"
						} else {
							tBizAutomoniter.setAnsType("3");//"1 ��� 2 ��ѡ 3 ��ѡ"
						}
						tBizAutomoniter.setTemplateCode(wflx);//��Ӧ��ģ����
						tBizAutomoniter.setTaskId(applyId);//��Ӧ������id
						tBizAutomoniter.setIsActive(YnEnum.Y.getCode());
						tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע
						tBizAutomoniter.setVersion(0);
						if(StringUtils.isNotBlank(c.getString("ans"))){
							tBizAutomoniter.setAns(c.getString("ans"));
						}
						tBizAutomoniter.setQueseCode(String.valueOf(j));
						dao.save(tBizAutomoniter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		} else if(wflx.equals("206") || wflx.equals("306")){
			list.add(zxzzForm.getSf10000001());
			list.add(zxzzForm.getSf10000002());
			list.add(zxzzForm.getSf10000003());
			list.add(zxzzForm.getSf10000004());
			list.add(zxzzForm.getSf10000005());
			list.add(zxzzForm.getSf10000006());
			list.add(zxzzForm.getSf10000007());
			list.add(zxzzForm.getSf10000008());
			list.add(zxzzForm.getSf10000009());
			list.add(zxzzForm.getSf10000010());
			list.add(zxzzForm.getSf10000011());
			list.add(zxzzForm.getSf10000012());
			list.add(zxzzForm.getSf10000013());
			list.add(zxzzForm.getSf10000014());
			list.add(this.CheckSpace(zxzzForm.getSf10000015()));
			list.add(this.CheckSpace(zxzzForm.getSf10000016()));
			list.add(this.CheckSpace(zxzzForm.getSf10000017()));
			list.add(this.CheckSpace(zxzzForm.getSf10000018()));
			list.add(this.CheckSpace(zxzzForm.getSf10000019()));
			list.add(this.CheckSpace(zxzzForm.getSf10000020()));
			list.add(this.CheckSpace(zxzzForm.getSf10000021()));
			list.add(this.CheckSpace(zxzzForm.getSf10000022()));
			list.add(this.CheckSpace(zxzzForm.getSf10000023()));
			list.add(this.CheckSpace(zxzzForm.getSf10000024()));
			list.add(this.CheckSpace(zxzzForm.getSf10000025()));
			list.add(this.CheckSpace(zxzzForm.getSf10000026()));
			list.add(this.CheckSpace(zxzzForm.getSf10000027()));
			list.add(this.CheckSpace(zxzzForm.getSf10000028()));
			list.add(this.CheckSpace(zxzzForm.getSf10000029()));
			list.add(this.CheckSpace(zxzzForm.getSf10000030()));
			list.add(this.CheckSpace(zxzzForm.getSf10000031()));
			list.add(this.CheckSpace(zxzzForm.getSf10000032()));
			try {
				for(int k=0; k<list.size();k++){
					JSONObject a =  new JSONObject();
					if(list.get(k) != null && list.get(k) != ""){
						a.put("ans", list.get(k).toString());
						b.put(a);
					}else{
						a.put("ans", "999");
						b.put(a);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int j = 10000208;
			TBizAutomoniter tBizAutomoniter=null;
			List<TBizAutomoniter> tBizAutomoniterList= this.getList(applyId, wflx);
			if(tBizAutomoniterList != null && tBizAutomoniterList.size() == 32){
				for(int d = 0; d<tBizAutomoniterList.size();d++){
					tBizAutomoniter = tBizAutomoniterList.get(d);
					try {
						tBizAutomoniter.setAns(b.getJSONObject(d).getString("ans"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע�޸ĵ�ֵ
					tBizAutomoniter.setUpdateby(cur);
					tBizAutomoniter.setUpdated(new Date());
					dao.save(tBizAutomoniter);
				}
			}else{
				for(int i=0; i<b.length();i++){
					j++;
					try {
						JSONObject c = b.getJSONObject(i);
						//��һ������ֵ
						tBizAutomoniter = new TBizAutomoniter();
						tBizAutomoniter.setCreateby(cur);
						tBizAutomoniter.setCreated(new Date());
						tBizAutomoniter.setUpdateby(cur);
						tBizAutomoniter.setUpdated(new Date());
						if(c.getString("ans").equals("true") || c.getString("ans").equals("false") || c.getString("ans").equals("999")){
							tBizAutomoniter.setAnsType("2");//"1 ��� 2 ��ѡ 3 ��ѡ"
						} else {
							tBizAutomoniter.setAnsType("3");//"1 ��� 2 ��ѡ 3 ��ѡ"
						}
						tBizAutomoniter.setTemplateCode(wflx);//��Ӧ��ģ����
						tBizAutomoniter.setTaskId(applyId);//��Ӧ������id
						tBizAutomoniter.setIsActive(YnEnum.Y.getCode());
						tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע
						tBizAutomoniter.setVersion(0);
						if(StringUtils.isNotBlank(c.getString("ans"))){
							tBizAutomoniter.setAns(c.getString("ans"));
						}
						tBizAutomoniter.setQueseCode(String.valueOf(j));
						dao.save(tBizAutomoniter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		} else if(wflx.equals("207") || wflx.equals("307")){
			list.add(zxzzForm.getSf10000001());
			list.add(zxzzForm.getSf10000002());
			list.add(zxzzForm.getSf10000003());
			list.add(zxzzForm.getSf10000004());
			list.add(this.CheckSpace(zxzzForm.getSf10000005()));
			list.add(this.CheckSpace(zxzzForm.getSf10000006()));
			list.add(this.CheckSpace(zxzzForm.getSf10000007()));
			list.add(this.CheckSpace(zxzzForm.getSf10000008()));
			try {
				for(int k=0; k<list.size();k++){
					JSONObject a =  new JSONObject();
					if(list.get(k) != null && list.get(k) != ""){
						a.put("ans", list.get(k).toString());
						b.put(a);
					}else{
						a.put("ans", "999");
						b.put(a);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int j = 10000240;
			TBizAutomoniter tBizAutomoniter=null;
			List<TBizAutomoniter> tBizAutomoniterList= this.getList(applyId, wflx);
			if(tBizAutomoniterList != null && tBizAutomoniterList.size() == 8){
				for(int d = 0; d<tBizAutomoniterList.size();d++){
					tBizAutomoniter = tBizAutomoniterList.get(d);
					try {
						tBizAutomoniter.setAns(b.getJSONObject(d).getString("ans"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע�޸ĵ�ֵ
					tBizAutomoniter.setUpdateby(cur);
					tBizAutomoniter.setUpdated(new Date());
					dao.save(tBizAutomoniter);
				}
			}else{
				for(int i=0; i<b.length();i++){
					j++;
					try {
						JSONObject c = b.getJSONObject(i);
						//��һ������ֵ
						tBizAutomoniter = new TBizAutomoniter();
						tBizAutomoniter.setCreateby(cur);
						tBizAutomoniter.setCreated(new Date());
						tBizAutomoniter.setUpdateby(cur);
						tBizAutomoniter.setUpdated(new Date());
						if(c.getString("ans").equals("true") || c.getString("ans").equals("false") || c.getString("ans").equals("999")){
							tBizAutomoniter.setAnsType("2");//"1 ��� 2 ��ѡ 3 ��ѡ"
						} else {
							tBizAutomoniter.setAnsType("3");//"1 ��� 2 ��ѡ 3 ��ѡ"
						}
						tBizAutomoniter.setTemplateCode(wflx);//��Ӧ��ģ����
						tBizAutomoniter.setTaskId(applyId);//��Ӧ������id
						tBizAutomoniter.setIsActive(YnEnum.Y.getCode());
						tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע
						tBizAutomoniter.setVersion(0);
						if(StringUtils.isNotBlank(c.getString("ans"))){
							tBizAutomoniter.setAns(c.getString("ans"));
						}
						tBizAutomoniter.setQueseCode(String.valueOf(j));
						dao.save(tBizAutomoniter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		} else if(wflx.equals("104") || wflx.equals("208") || wflx.equals("308")){
			list.add(zxzzForm.getSf10000001());
			list.add(zxzzForm.getSf10000002());
			list.add(zxzzForm.getSf10000003());
			list.add(this.CheckSpace(zxzzForm.getSf10000004()));
			list.add(this.CheckSpace(zxzzForm.getSf10000005()));
			list.add(this.CheckSpace(zxzzForm.getSf10000006()));
			list.add(this.CheckSpace(zxzzForm.getSf10000007()));
			list.add(this.CheckSpace(zxzzForm.getSf10000008()));
			list.add(this.CheckSpace(zxzzForm.getSf10000009()));
			try {
				for(int k=0; k<list.size();k++){
					JSONObject a =  new JSONObject();
					if(list.get(k) != null && list.get(k) != ""){
						a.put("ans", list.get(k).toString());
						b.put(a);
					}else{
						a.put("ans", "999");
						b.put(a);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int j = 10000248;
			TBizAutomoniter tBizAutomoniter=null;
			List<TBizAutomoniter> tBizAutomoniterList= this.getList(applyId, wflx);
			if(tBizAutomoniterList != null && tBizAutomoniterList.size() == 9){
				for(int d = 0; d<tBizAutomoniterList.size();d++){
					tBizAutomoniter = tBizAutomoniterList.get(d);
					try {
						tBizAutomoniter.setAns(b.getJSONObject(d).getString("ans"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע�޸ĵ�ֵ
					tBizAutomoniter.setUpdateby(cur);
					tBizAutomoniter.setUpdated(new Date());
					dao.save(tBizAutomoniter);
				}
			}else{
				for(int i=0; i<b.length();i++){
					j++;
					try {
						JSONObject c = b.getJSONObject(i);
						//��һ������ֵ
						tBizAutomoniter = new TBizAutomoniter();
						tBizAutomoniter.setCreateby(cur);
						tBizAutomoniter.setCreated(new Date());
						tBizAutomoniter.setUpdateby(cur);
						tBizAutomoniter.setUpdated(new Date());
						if(c.getString("ans").equals("true") || c.getString("ans").equals("false") || c.getString("ans").equals("999")){
							tBizAutomoniter.setAnsType("2");//"1 ��� 2 ��ѡ 3 ��ѡ"
						} else {
							tBizAutomoniter.setAnsType("3");//"1 ��� 2 ��ѡ 3 ��ѡ"
						}
						tBizAutomoniter.setTemplateCode(wflx);//��Ӧ��ģ����
						tBizAutomoniter.setTaskId(applyId);//��Ӧ������id
						tBizAutomoniter.setIsActive(YnEnum.Y.getCode());
						tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע
						tBizAutomoniter.setVersion(0);
						if(StringUtils.isNotBlank(c.getString("ans"))){
							tBizAutomoniter.setAns(c.getString("ans"));
						}
						tBizAutomoniter.setQueseCode(String.valueOf(j));
						dao.save(tBizAutomoniter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(wflx.equals("105") || wflx.equals("209") || wflx.equals("309")){
			list.add(zxzzForm.getSf10000001());
			list.add(zxzzForm.getSf10000002());
			list.add(zxzzForm.getSf10000003());
			list.add(zxzzForm.getSf10000004());
			list.add(zxzzForm.getSf10000005());
			list.add(zxzzForm.getSf10000006());
			list.add(zxzzForm.getSf10000007());
			list.add(zxzzForm.getSf10000008());
			list.add(zxzzForm.getSf10000009());
			list.add(zxzzForm.getSf10000010());
			list.add(zxzzForm.getSf10000011());
			list.add(zxzzForm.getSf10000012());
			list.add(zxzzForm.getSf10000013());
			list.add(zxzzForm.getSf10000014());
			list.add(zxzzForm.getSf10000015());
			list.add(zxzzForm.getSf10000016());
			list.add(zxzzForm.getSf10000017());
			list.add(zxzzForm.getSf10000018());
			list.add(zxzzForm.getSf10000019());
			list.add(zxzzForm.getSf10000020());
			list.add(zxzzForm.getSf10000021());
			list.add(zxzzForm.getSf10000022());
			list.add(zxzzForm.getSf10000023());
			list.add(zxzzForm.getSf10000024());
			list.add(zxzzForm.getSf10000025());
			list.add(zxzzForm.getSf10000026());
			list.add(zxzzForm.getSf10000027());
			list.add(zxzzForm.getSf10000028());
			list.add(zxzzForm.getSf10000029());
			list.add(zxzzForm.getSf10000030());
			list.add(zxzzForm.getSf10000031());
			list.add(zxzzForm.getSf10000032());
			list.add(zxzzForm.getSf10000033());
			list.add(zxzzForm.getSf10000034());
			list.add(zxzzForm.getSf10000035());
			list.add(zxzzForm.getSf10000036());
			list.add(zxzzForm.getSf10000037());
			list.add(zxzzForm.getSf10000038());
			list.add(zxzzForm.getSf10000039());
			list.add(zxzzForm.getSf10000040());
			list.add(zxzzForm.getSf10000041());
			list.add(zxzzForm.getSf10000042());
			list.add(zxzzForm.getSf10000043());
			list.add(zxzzForm.getSf10000044());
			list.add(zxzzForm.getSf10000045());
			list.add(zxzzForm.getSf10000046());
			list.add(zxzzForm.getSf10000047());
			list.add(zxzzForm.getSf10000048());
			list.add(zxzzForm.getSf10000049());
			list.add(zxzzForm.getSf10000050());
			list.add(zxzzForm.getSf10000051());
			list.add(zxzzForm.getSf10000052());
			list.add(zxzzForm.getSf10000053());
			list.add(zxzzForm.getSf10000054());
			list.add(zxzzForm.getSf10000055());
			list.add(zxzzForm.getSf10000056());
			list.add(zxzzForm.getSf10000057());
			list.add(zxzzForm.getSf10000058());
			list.add(zxzzForm.getSf10000059());
			list.add(zxzzForm.getSf10000060());
			list.add(zxzzForm.getSf10000061());
			list.add(zxzzForm.getSf10000062());
			list.add(zxzzForm.getSf10000063());
			list.add(zxzzForm.getSf10000064());
			list.add(zxzzForm.getSf10000065());
			list.add(zxzzForm.getSf10000066());
			list.add(zxzzForm.getSf10000067());
			list.add(zxzzForm.getSf10000068());
			list.add(zxzzForm.getSf10000069());
			list.add(zxzzForm.getSf10000070());
			list.add(zxzzForm.getSf10000071());
    		if(wflx.equals("105")){
    			list.add(zxzzForm.getSf10000072());
    			list.add(zxzzForm.getSf10000073());
    		}
    		list.add(zxzzForm.getSf10000074());//˳���
    		list.add(zxzzForm.getSf10000075());//���۱���
			try {
				for(int k=0; k<list.size();k++){
					JSONObject a =  new JSONObject();
					a.put("ans", list.get(k).toString());
					b.put(a);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int j = 10000257;
			TBizAutomoniter tBizAutomoniter=null;
			List<TBizAutomoniter> tBizAutomoniterList= this.getList(applyId, wflx);
			if(tBizAutomoniterList != null && tBizAutomoniterList.size() > 70){
				for(int d = 0; d<tBizAutomoniterList.size();d++){
					tBizAutomoniter = tBizAutomoniterList.get(d);
					try {
						tBizAutomoniter.setAns(b.getJSONObject(d).getString("ans"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע�޸ĵ�ֵ
					tBizAutomoniter.setUpdateby(cur);
					tBizAutomoniter.setUpdated(new Date());
					dao.save(tBizAutomoniter);
				}
			}else{
				for(int i=0; i<b.length();i++){
					j++;
					try {
						JSONObject c = b.getJSONObject(i);
						//��һ������ֵ
						tBizAutomoniter = new TBizAutomoniter();
						tBizAutomoniter.setCreateby(cur);
						tBizAutomoniter.setCreated(new Date());
						tBizAutomoniter.setUpdateby(cur);
						tBizAutomoniter.setUpdated(new Date());
						tBizAutomoniter.setAnsType("1");//"1 ��� 2 ��ѡ 3 ��ѡ"
						tBizAutomoniter.setTemplateCode(wflx);//��Ӧ��ģ����
						tBizAutomoniter.setTaskId(applyId);//��Ӧ������id
						tBizAutomoniter.setIsActive(YnEnum.Y.getCode());
						tBizAutomoniter.setRemark(zxzzForm.getRemark());//��ע
						tBizAutomoniter.setVersion(0);
						if(StringUtils.isNotBlank(c.getString("ans"))){
							tBizAutomoniter.setAns(c.getString("ans"));
						}
						tBizAutomoniter.setQueseCode(String.valueOf(j));
						dao.save(tBizAutomoniter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public void saveShengchengXwbl(String applyId,String wflx){
		try{
			Work work = workManager.get(applyId);
			List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
			TBizAutomoniter xwbl=new TBizAutomoniter();
			
			//��ѯ�����ǵڼ����������ģ��
			String fileCount = "";
			TBizBlmbcs tBizBlmbcs= this.getMaxMbcs(applyId, wflx);
			if(tBizBlmbcs==null){
				fileCount = String.valueOf(1);
			}else{
				fileCount = String.valueOf(tBizBlmbcs.getTimes()+1);
			}
			
			//������������λ
			if (null != applyId && !"".equals(applyId.trim())) {
				String szdw="";//���������ڵ�λ
				String jcsj="";//����������ѡ��ļ��ʱ��
				String jcdw="";//����������ѡ��ļ�������ڵĵ�λ
				String jcrIds = "";//����������ѡ��ļ����
				String jcrid = "";//�����id,��ȡ��λ����
				String jcr = "";//����������ѡ��ļ����
				String kzlx="";//��������
				String remark="";//��ע
				//�鿴�������Ӧģ��������ֵ
				List<TBizAutomoniter> xwbllList = this.getList(applyId, wflx);
				if (xwbllList != null && xwbllList.size() > 0) {//�м�¼
					xwbl = xwbllList.get(0);
				} else {
					xwbl = new TBizAutomoniter();
				}
				
				//�����
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.ZDJK_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blZdjkForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blZdjkForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcr += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcr += taskuserlist1.get(k).getUsername();
						jcrid = taskuserlist1.get(0).getUserid();
					}
				}
				
				//��鵥λ(���ݼ����id��ȡ���ڲ��ţ�Ȼ����ݲ��Ż�ȡ����Ӧ�ĵ�λ)
				TSysOrg	jcrOrg = userManager.getOrgbyUser(jcrid);
				jcdw = jcrOrg.getUnitname();
				
				
				//���ʱ��
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.ZDJK.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					jcsj = DateUtil.getDate(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1());// ���ʱ��
				}
				
				/********************��ʼ��ֵ****************/
				szdw=zfdxlistMap.get(0).get("lawobjname");//����鵥λ����
				remark=xwbl.getRemark();
				long start = System.currentTimeMillis();
				//System.out.println("��poi����word��ʼʱ�䣺" + start);
//            	if(null==xwblfile) {
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
				String tempPath = "";
				String fileName = "";
				if(wflx.equals("101")){
					fileName = "������ȾԴ�Զ������ʩ���м���";//��ʱд����������ǵ������ֵ�����ֵ��޸ģ����Ը�������ѯ
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfq101.doc";
				}
				if(wflx.equals("102")){
					fileName = "������ȾԴ�Զ������ʩ������Ԫ�ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfq102.doc";
				}
				if(wflx.equals("103")){
					fileName = "�����̶���ȾԴ�����Զ������ʩCEMS�ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfq103.doc";
				}
				if(wflx.equals("104")){
					fileName = "�������ݲɼ����������ص����"; 
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfq104.doc";
				}
				if(wflx.equals("105")){
					fileName = "�����̶���ȾԴ�����������ϵͳ�ֳ��˲��";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfq105.doc";
				}
				if(wflx.equals("201")){
					fileName = "ˮ��UV������ȾԴ�Զ������ʩ���м���";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs201.doc";
				}
				if(wflx.equals("202")){
					fileName = "ˮ��UV������ȾԴ�Զ������ʩ������Ԫ�ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs202.doc";
				}
				if(wflx.equals("203")){
					fileName = "ˮ��UV�������л�̼��TOC����ȾԴ�Զ������ʩ�ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs203.doc";
				}
				if(wflx.equals("204")){
					fileName = "ˮ��UV�������⣨UV������ˮ���Զ�������ص����"; 
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs204.doc";
				}
				if(wflx.equals("205")){
					fileName = "ˮ��UV����������ȾԴ�Զ������ʩ�ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs205.doc";
				}
				if(wflx.equals("206")){
					fileName = "ˮ��UV�����ؽ�����ȾԴ�Զ������ʩ�ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs206.doc";
				}
				if(wflx.equals("207")){
					fileName = "ˮ��UV�����������ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs207.doc";
				}
				if(wflx.equals("208")){
					fileName = "ˮ��UV�������ݲɼ����������ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs208.doc";
				}
				if(wflx.equals("209")){
					fileName = "ˮ��UV�����̶���ȾԴ��ˮ�Զ����ϵͳ�ֳ��˲��";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs209.doc";
				}
				if(wflx.equals("301")){
					fileName = "ˮ���ظ���ط�����ȾԴ�Զ������ʩ���м���";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs301.doc";
				}
				if(wflx.equals("302")){
					fileName = "ˮ���ظ���ط�����ȾԴ�Զ������ʩ������Ԫ�ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs302.doc";
				}
				if(wflx.equals("303")){
					fileName = "ˮ���ظ���ط�����ѧ��������CODCr����ȾԴ�Զ������ʩ�ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs303.doc";
				}
				if(wflx.equals("304")){
					fileName = "ˮ���ظ���ط������л�̼��TOC����ȾԴ�Զ������ʩ�ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs304.doc";
				}
				if(wflx.equals("305")){
					fileName = "ˮ���ظ���ط���������ȾԴ�Զ������ʩ�ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs305.doc";
				}
				if(wflx.equals("306")){
					fileName = "ˮ���ظ���ط����ؽ�����ȾԴ�Զ������ʩ�ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs306.doc";
				}
				if(wflx.equals("307")){
					fileName = "ˮ���ظ���ط����������ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs307.doc";
				}
				if(wflx.equals("308")){
					fileName = "ˮ���ظ���ط������ݲɼ����������ص����";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs308.doc";
				}
				if(wflx.equals("309")){
					fileName = "ˮ���ظ���ط����̶���ȾԴ��ˮ�Զ����ϵͳ�ֳ��˲��";
					tempPath=sc.getRealPath(File.separator) + "excel/zxmb/zxmbfs309.doc";
				}
				//poiʵ��word����
				Map<String, String> param = new HashMap<String, String>();
//				param.put("$area_dept$", user.getArea().getDeptName());
				param.put("$szdw$", szdw);
				param.put("$jcsj$", jcsj);
				param.put("$jcdw$", jcdw);
				param.put("$jcr$", jcr);
				//������ȾԴ�Զ������ʩ���м���
				if(wflx.equals("101") || wflx.equals("201") || wflx.equals("301")){
					int a = 0;
					for(int k = 0; k<xwbllList.size();k++){
						a++;
						String biaoshi = "$"+a+"$";
						if(xwbllList.get(k).getAns().equals("true")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("false")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("999") || xwbllList.get(k).getAns().equals(null)){
							param.put(biaoshi, "�ǡ������");
						}
					}
				}
				//������ȾԴ�Զ������ʩ������Ԫ�ص����
				if(wflx.equals("102") || wflx.equals("202") || wflx.equals("302")){
					int a = 0;
					for(int k = 0; k<xwbllList.size();k++){
						a++;
						String biaoshi = "$"+a+"$";
						if(xwbllList.get(k).getAns().equals("true")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("false")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("999") || xwbllList.get(k).getAns().equals(null)){
							param.put(biaoshi, "�ǡ������");
						}else if(xwbllList.get(k).getAns().equals("1")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("0")){
							param.put(biaoshi, "��");
						}
					}
				}
				//�̶���ȾԴ�����Զ������ʩCEMS�ص����
				if(wflx.equals("103")){
					int a = 0;
					for(int k = 0; k<xwbllList.size();k++){
						a++;
						String biaoshi = "$"+a+"$";
						if(xwbllList.get(k).getAns().equals("true")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("false")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("999") || xwbllList.get(k).getAns().equals(null)){
							param.put(biaoshi, "�ǡ������");
						}else if(xwbllList.get(k).getAns().equals("1")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("0")){
							param.put(biaoshi, "��");
						}
					}
				}
				//���л�̼��TOC����ȾԴ�Զ������ʩ�ص����
				if(wflx.equals("203") || wflx.equals("304")){
					int a = 0;
					for(int k = 0; k<xwbllList.size();k++){
						a++;
						String biaoshi = "$"+a+"$";
						if(xwbllList.get(k).getAns().equals("true")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("false")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("999") || xwbllList.get(k).getAns().equals(null)){
							param.put(biaoshi, "�ǡ������");
						}else if(xwbllList.get(k).getAns().equals("1")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("0")){
							param.put(biaoshi, "��");
						}
					}
				}
				//��ѧ��������CODCr����ȾԴ�Զ������ʩ�ص����
				if(wflx.equals("303")){
					int a = 0;
					for(int k = 0; k<xwbllList.size();k++){
						a++;
						String biaoshi = "$"+a+"$";
						if(xwbllList.get(k).getAns().equals("true")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("false")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("999") || xwbllList.get(k).getAns().equals(null)){
							param.put(biaoshi, "�ǡ������");
						}else if(xwbllList.get(k).getAns().equals("1")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("0")){
							param.put(biaoshi, "��");
						}
					}
				}
				//���⣨UV������ˮ���Զ�������ص����
				if(wflx.equals("204")){
					int a = 0;
					for(int k = 0; k<xwbllList.size();k++){
						a++;
						String biaoshi = "$"+a+"$";
						if(xwbllList.get(k).getAns().equals("true")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("false")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("999") || xwbllList.get(k).getAns().equals(null)){
							param.put(biaoshi, "�ǡ������");
						}else if(xwbllList.get(k).getAns().equals("1")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("0")){
							param.put(biaoshi, "��");
						}
					}
				}
				//������ˮ�İ�����ȾԴ�Զ������ʩ�ص����
				if(wflx.equals("205") || wflx.equals("305")){
					int a = 0;
					for(int k = 0; k<xwbllList.size();k++){
						a++;
						String biaoshi = "$"+a+"$";
						if(xwbllList.get(k).getAns().equals("true")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("false")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("999") || xwbllList.get(k).getAns().equals(null)){
							param.put(biaoshi, "�ǡ������");
						}else if(xwbllList.get(k).getAns().equals("1")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("0")){
							param.put(biaoshi, "��");
						}
					}
				}
				//������ˮ���ؽ�����ȾԴ�Զ������ʩ�ص����
				if(wflx.equals("206") || wflx.equals("306")){
					int a = 0;
					for(int k = 0; k<xwbllList.size();k++){
						a++;
						String biaoshi = "$"+a+"$";
						if(xwbllList.get(k).getAns().equals("true")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("false")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("999") || xwbllList.get(k).getAns().equals(null)){
							param.put(biaoshi, "�ǡ������");
						}else if(xwbllList.get(k).getAns().equals("1")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("0")){
							param.put(biaoshi, "��");
						}
					}
				}
				//������ˮ���������ص����
				if(wflx.equals("207") || wflx.equals("307")){
					int a = 0;
					for(int k = 0; k<xwbllList.size();k++){
						a++;
						String biaoshi = "$"+a+"$";
						if(xwbllList.get(k).getAns().equals("true")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("false")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("999") || xwbllList.get(k).getAns().equals(null)){
							param.put(biaoshi, "�ǡ������");
						}else if(xwbllList.get(k).getAns().equals("1")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("0")){
							param.put(biaoshi, "��");
						}
					}
				}
				//�������ݲɼ����������ص����
				if(wflx.equals("104") || wflx.equals("208") || wflx.equals("308")){
					int a = 0;
					for(int k = 0; k<xwbllList.size();k++){
						a++;
						String biaoshi = "$"+a+"$";
						if(xwbllList.get(k).getAns().equals("true")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("false")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("999") || xwbllList.get(k).getAns().equals(null)){
							param.put(biaoshi, "�ǡ������");
						}else if(xwbllList.get(k).getAns().equals("1")){
							param.put(biaoshi, "��");
						}else if(xwbllList.get(k).getAns().equals("0")){
							param.put(biaoshi, "��");
						}
					}
				}
				//�����÷ּ���
				if(wflx.equals("105") || wflx.equals("209") || wflx.equals("309")){
					String kzlxcode = this.getKzlx(zfdxlistMap.get(0).get("lawobjid"));//�������ҵ�Ŀ������Ͳ�ѯ��Ҫ��id
					if("1".equals(kzlxcode)){
	        			kzlx = "����";
	        		}else if("2".equals(kzlxcode)){
	        			kzlx = "ʡ��";
	        		}else if(!"1".equals(kzlxcode) && !"2".equals(kzlxcode)){
	        			kzlx = "����";
	        		}
					//�������� + �˲鲿�ţ�1ʡ����2�м���3�ؼ���+ ��ȾԴ���ͣ�1��ˮ��2������+ ��� + ˳���
	            	String code = "";
	            	String xzqm = "";
	            	String bmbh = "";//�˲鲿�ţ�1ʡ����2�м���3�ؼ���
	            	String wrlx = "";
	            	String nf = "";
	            	//Ĭ����ʾ��������Ϣ
	        		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
	        		TSysArea tSysArea =  tSysOrg.getArea();
	        		if("0".equals(tSysArea.getType())){
	        			bmbh="01";
	        		}else if("1".equals(tSysArea.getType())){
	        			bmbh="02";
	        		}else if("2".equals(tSysArea.getType())){
	        			bmbh="03";
	        		}
	        		if(tSysOrg!=null){
	        			xzqm = tSysOrg.getArea().getCode();//�������������
	        		}
	        		if(wflx.equals("105")){
	        			wrlx = "01";
	        		}else{
	        			wrlx = "02";
	        		}
	        		Calendar date=Calendar.getInstance();
	        		nf = String.valueOf(date.get(Calendar.YEAR));
	        		//��ҵ��������
	        		//TDataLawobj lawobj = manager.getLawobjInfo(lawobj);
	        		//������б�
	        		List<Combobox> jscrList = commWorkManager.ryghCombo(applyId);
					String jcrNames = "";
					for (int k = 0; k < jscrList.size(); k++) {
						if (k > 0) {
							jcrNames += ",";
						}
						jcrNames += jscrList.get(k).getName();
					}
					//����������
					BlMainForm blMainForm = commWorkManager.getBlMainFormData(applyId);
					param.put("$hbfzr$", blMainForm.getBlZfdxForm().getBjcr());
	    			param.put("$hcry$", jcrNames);
	        		param.put("$kzlx$", kzlx);
	        		int a = 0;
	        		if(wflx.equals("105")){
	        			for(int k = 0; k<xwbllList.size();k++){
							a++;
							String biaoshi = "$"+a+"$";
							param.put(biaoshi, xwbllList.get(k).getAns());
							if(k == 73){
								if(StringUtils.isNotBlank(xwbllList.get(73).getAns())){
									code = xzqm + bmbh + wrlx + nf + xwbllList.get(73).getAns();
								}else{
									code = xzqm + bmbh + wrlx + nf;
								}
								code = xzqm + bmbh + wrlx + nf + xwbllList.get(73).getAns();
								param.put("$wjbh$", code);
							}
							if(k == 74){
								param.put("$75$", xwbllList.get(74).getAns());
							}
						}		
	        		}else{
	        			for(int k = 0; k<xwbllList.size();k++){
							a++;
							String biaoshi = "$"+a+"$";
							param.put(biaoshi, xwbllList.get(k).getAns());
							if(k == 71){
								if(StringUtils.isNotBlank(xwbllList.get(71).getAns())){
									code = xzqm + bmbh + wrlx + nf + xwbllList.get(71).getAns();
								}else{
									code = xzqm + bmbh + wrlx + nf;
								}
								param.put("$wjbh$", code);
							}
							if(k == 72){
								param.put("$73$", xwbllList.get(72).getAns());
							}
						}		
	        		}
				}
				
				param.put("$remark$", remark);
				String newfile = PoiUtil.createWord(tempPath, dirPath, param);
				//////////////////////////////�����������ͣ�����Ӧ�Ĵ���(���������ĵ�)///////////////////////////////////
				//Υ����
				if ("19".equals(TaskTypeCode.ZDJK.getCode())) {
					File file = new File(newfile);
					if (file.exists()) {
						InputStream is = new FileInputStream(file);
						
						//��ɾ���ɵģ��ٱ����µģ�
						//commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.ZDJKJCJL.getCode());
						TDataFile filepo = commonManager.saveFile(is, applyId, FileTypeEnums.ZDJKJCJL.getCode(), "work", fileName+ "����"+fileCount+"�Σ�.doc", ((Integer) is.available()).longValue());
						this.saveBlmbcs(applyId, wflx, filepo.getId(), fileCount);
					}
					long end = System.currentTimeMillis();
					//System.out.println("��poi����word����ʱ�䣺" + start);
					//System.out.println("��poi����word��ʱ*******************��" + (end - start) + "ms");
				}
			}
		}catch (Exception e) {
			//System.out.println("���ɵ���ѯ�ʱ�¼doc�ļ�������"+ e);
        }
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
	public List<TBizAutomoniter> getList(String applyId, String mblx) {
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizAutomoniter where taskid_=:taskid and templatecode_=:mblx order by quesecode_");
		data.put("taskid", applyId);
		data.put("mblx", mblx);
		List<TBizAutomoniter> list = this.dao.find(sql.toString(),data);
		return list;
	}
	
	@Override
	public FyWebResult queryZxzzFileList(String pid, String canDel,String fileType,String page, String rows){
		List<String> ext = new ArrayList<String>();
		ext.add(".jpg");
		ext.add(".png");
		ext.add(".bmp");
		ext.add(".jpeg");
		ext.add(".doc");
		ext.add(".docx");
		ext.add(".txt");
		Map<String,Object> condition = new HashMap<String,Object>();
		String sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_,m.tmplateid_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ "
			   		+"left join T_BIZ_BLMBCS m on f.id_ = m.FILEID_ where f.pid_ = :pid";
		condition.put("pid", pid);
		
		//��������
		if(StringUtils.isNotBlank(fileType)){
			sql+=" and (d.code_=:fileTypeCode or d.code_ = '1914')";
			fileType = FileTypeEnums.getTypeByEnumName(fileType);
			condition.put("fileTypeCode", fileType);
		}
		
		sql+=" order by m.times_ asc";
		
		FyResult pos = this.dao.find(sql, -1, -1, condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		fy.setRows(rowsList);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String name = "";
		for (Object[] obj : listLawobj) {
			if (String.valueOf(obj[1]).contains(".")){
				name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
			}
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("url", "/download.mo?id="+String.valueOf(obj[0]));
			if(obj[2] != null && FileUtil.isImage(String.valueOf(obj[2]))){
				dataObject.put("imageUrl", obj[0] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[0]));
			}
			dataObject.put("filetype", name);
			dataObject.put("filename", String.valueOf(obj[2]));
			long filesize = Long.valueOf(String.valueOf(obj[3]));
			dataObject.put("filesize", FileUtil.sizeFormat(filesize));
			String operate = "";
			if (String.valueOf(obj[2]).lastIndexOf(".")!=-1 && ext.contains(String.valueOf(obj[2]).substring(String.valueOf(obj[2]).lastIndexOf("."), String.valueOf(obj[2]).length()).toLowerCase())) {
				operate = "<a class='b-link' onclick='review(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >Ԥ��</a>";
			}
			if (canDel==null || canDel.equals("Y")) {
				operate += OperateUtil.getDloadOperate(String.valueOf(obj[0]))+OperateUtil.getDeleteOperate(String.valueOf(obj[0]));
			} else {
				operate += OperateUtil.getDloadOperate(String.valueOf(obj[0]));
			}
			
			dataObject.put("operate", operate);
			dataObject.put("filetypecode", String.valueOf(obj[5]));
			rowsList.add(dataObject);
		}
		return fy;
	}
	
	public String CheckSpace(String canshu){
		String a = "";
		if(StringUtils.isNotBlank(canshu)){
			a = canshu;
		}else{
			a = "0";
		}
		return a;
	}
	
	public String getKzlx(String kzlx){
		String kzlxCode = "";
		StringBuffer sql = new StringBuffer();
		sql.append(" from t_data_lawobj where id_ =? ");
		TDataLawobj kzlxList = (TDataLawobj) this.dao.get(TDataLawobj.class, kzlx);
		kzlxCode = kzlxList.getColumn21();
		return kzlxCode;
	}

	public List<TBizBlmbcs> getMbcsList(String applyId, String templateId) {
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizBlmbcs where taskid_=:taskid and tmplateid_ =:templateId order by times_");
		data.put("taskid", applyId);
		data.put("templateId", templateId);
		List<TBizBlmbcs> list = this.dao.find(sql.toString(),data);
		return list;
	}
	
	//�õ��������ļ���¼
	public TBizBlmbcs getMaxMbcs(String applyId, String templateId) {
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizBlmbcs where taskid_=:taskid and tmplateid_ =:templateId order by times_ desc");
		data.put("taskid", applyId);
		data.put("templateId", templateId);
		List<TBizBlmbcs> list = this.dao.find(sql.toString(),data);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public void saveBlmbcs(String taskId, String tmplateId, String fileId, String fileCount){
		TBizBlmbcs tbizBlmbcs = null;
		try {
			tbizBlmbcs = new TBizBlmbcs();
			TSysUser curUser = CtxUtil.getCurUser();
			Date cur = new Date();
			tbizBlmbcs.setTaskId(taskId);//��Ӧ�����id
			tbizBlmbcs.setTmplateId(tmplateId);//��Ӧģ�����͵�code
			tbizBlmbcs.setFileId(fileId);//���ɵ�ģ��id���浽tdataFile����
			tbizBlmbcs.setTimes(Integer.parseInt(fileCount));//���ɵ�ģ�����
			tbizBlmbcs.setIsActive("Y");
			tbizBlmbcs.setCreateby(curUser);
			tbizBlmbcs.setCreated(cur);
			this.dao.save(tbizBlmbcs);
		} catch (Exception e) {
			//System.out.println("���������ĵ��ļ�¼����������"+ e);
		}
	}
	
	@Override
	public List<Map<String, String>> getZxzzFiles(String applyId, String bllx) {
		List<Map<String, String>> kcxwblFileMap = null;
		//�Զ����������������¼
		FyWebResult re2 = this.queryZxzzFileList(applyId, "N",
			FileTypeEnums.ZDJKJCJL.getCode(), "1", null);
			List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
			rowsList2 = re2.getRows();
			kcxwblFileMap = rowsList2;
		return kcxwblFileMap;
	}
	
	@Override
	public String saveCopyFile(String applyId, String bllx){
		List<String> ext = new ArrayList<String>();
		List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
		ext.add(".jpg");
		ext.add(".png");
		ext.add(".bmp");
		ext.add(".jpeg");
		ext.add(".doc");
		ext.add(".docx");
		ext.add(".txt");
		String fileType = "";
		Map<String,Object> condition = new HashMap<String,Object>();
		String sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ "
			   		+"where f.pid_ = :pid";
		condition.put("pid", applyId);
		//��������
		if(StringUtils.isNotBlank(bllx)){
			sql+=" and d.code_=:fileTypeCode ";
			fileType = FileTypeEnums.getTypeByEnumName(bllx);
			condition.put("fileTypeCode", fileType);
		}
		FyResult pos = this.dao.find(sql, -1, -1, condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		fy.setRows(rowsList);
		List<Object[]> listLawobj = pos.getRe();
		String in = "";
		int a = 0;
		for (Object[] obj : listLawobj) {
			if(a > 0){
				in += ",";
			}
			in += "'" + String.valueOf(obj[0]) + "'";
			a++;
		}
		List<Object[]> files = this.dao.find("select a.name, a.osname, a.type, a.relativepath, b.orderby, '' as num from TDataFile a, TSysDic b where b.type='4' and a.type = b.code and a.id in (" + in + ") order by b.orderby");
		String path = FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath();
		File dir = new File(path);
		if (!dir.exists()){
			dir.mkdir();
		}
		String sourcePath = "";
		Object order = null;
		List list  = new ArrayList();
		for (int i = 0; i < files.size(); i++) {
			files.get(i)[5] = order;
			sourcePath = FileUpDownUtil.path + File.separator + String.valueOf(files.get(i)[3]) + File.separator + String.valueOf(files.get(i)[1]);
			try {
				FileUtil.copyFile(sourcePath, path, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(sourcePath);
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(date);
		String num = String.valueOf((int)((Math.random()*9+1)*100000));
		String filesrc = FileUpDownUtil.path;
		this.uniteDoc(list,filesrc+UploadFileType.TEMP.getPath()+zfdxlistMap.get(0).get("lawobjname")+"����¼.doc");
		File word = new File(filesrc+UploadFileType.TEMP.getPath()+zfdxlistMap.get(0).get("lawobjname")+"����¼.doc");
		TDataFile wordfile = null;
		try {
			//��ɾ���ɵģ��ٱ����µģ�
			commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.ZDJKHBWD.getCode());
			wordfile = commonManager.saveFile(new FileInputStream(
					word), applyId, "1914",
					UploadFileType.WORK.getPath(), zfdxlistMap.get(0).get("lawobjname")+"����¼.doc",
					word.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wordfile.getId();
	}
	
	public void uniteDoc(List fileList, String savepaths){
        if (fileList.size() == 0 || fileList == null) {
            return;
        }
        //��word  
        ActiveXComponent app = new ActiveXComponent("Word.Application");//����word
        try {
            // ����word���ɼ�  
            app.setProperty("Visible", new Variant(false));
            //���documents����
            Object docs = app.getProperty("Documents").toDispatch();
            //�򿪵�һ���ļ�
            Object doc = Dispatch.invoke((Dispatch) docs, "Open", Dispatch.Method, new Object[] { (String) fileList.get(0), new Variant(false), new Variant(true) }, new int[3]).toDispatch();  
            //׷���ļ�
            for (int i = 1; i < fileList.size(); i++) {
                Dispatch.invoke(app.getProperty("Selection").toDispatch(),  
                    "insertFile", Dispatch.Method, new Object[] {
                            (String) fileList.get(i), "",
                            new Variant(false), new Variant(false),
                            new Variant(false) }, new int[3]);
            }
            //�����µ�word�ļ�
            Dispatch.invoke((Dispatch) doc, "SaveAs", Dispatch.Method, 
                new Object[] { savepaths, new Variant(1) }, new int[3]);
            Variant f = new Variant(false);
            Dispatch.call((Dispatch) doc, "Close", f);
        } catch (Exception e) {
            throw new RuntimeException("�ϲ�word�ļ�����.ԭ��:" + e);
        } finally {
            app.invoke("Quit", new Variant[] {});
        }
    }
	
}