/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysUser;

/**
 * �û�����
 * 
 * @author wumi
 * @version $Id: UserManager.java, v 0.1 Jan 17, 2013 9:32:53 AM wumi Exp $
 */
public interface UserManager extends Manager {

	/**
	 * �����û��ĵ�¼����ѯ�������������û�����
	 * 
	 * @param name
	 *            �û���¼��
	 * @return �û����ƣ���","����
	 */
	public String getUserNames(List<String> names);

	/**
	 * �����û��ĵ�¼����ѯ�������������û���Ϣ(�����ĳ�һ��in��ѯ�ķ�ʽ)
	 * 
	 * @param name
	 *            �û���¼��
	 * @return �����������û���Ϣ
	 */
	public List<TSysUser> getUsersByLoginName(List<String> names);

	/**
	 * �����û��ĵ�¼����ѯ�������������û���Ϣ
	 * 
	 * @param name
	 *            �û���¼��
	 * @return �����������û���Ϣ
	 */
	public TSysUser getUserByLoginName(String name);

	/**
	 * ���������ɵ�ĳ������ʱ����ȡ��ǰ�����ܽ����������Ա�б���Ŀǰ�ǰ칫����Ա��
	 * 
	 * @param areaId
	 *            ����Id
	 * @return �������ɵ�ĳ������ʱ����ȡ��ǰ�����ܽ����������Ա�б�
	 */
	public List<TSysUser> getXpUsers(String areaId);

	/**
	 * ���Դ����ϱ��������Ա���ϣ�Ŀǰ�ǰ칫����Ա��
	 * 
	 * @return ���Դ����ϱ��������Ա����
	 */
	public List<TSysUser> getSbUsers();

	/**
	 * �����û�Id��ȡ�û�
	 * 
	 * @param id
	 *            �û�ID
	 * @return �û�
	 */
	public TSysUser getUser(String id);

	/**
	 * ��ѯ�û��б�
	 * 
	 * @param name
	 *            �������������԰����ƣ���ע����
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @param pageSize2 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public FyWebResult queryUser(String name, String isActive, String page, String pageSize,String orgid,String areaid)
			throws Exception;

	/**
	 * ����һ���û�
	 * 
	 * @param frm
	 *            {@link UserForm}
	 * @param file 
	 */
	public void saveUser(UserForm frm, MultipartFile file) throws AppException;

	/**
	 * ��������
	 * 
	 * @param frm
	 *            {@link UserForm}
	 */
	public void resetPas(String id) throws AppException;

	/**
	 * ��������
	 * 
	 * @param frm
	 *            {@link UserForm}
	 */
	public String savePas(UserForm frm) throws AppException;

	/**
	 * �����û�id��ȡ��ɫ����
	 * 
	 * @param userId
	 *            �û�Id
	 * @return ��ɫ����
	 */
	public List<TSysRole> getRolebyUser(String userId);

	/**
	 * �����û�id��ȡ����
	 * 
	 * @param userId
	 *            �û�Id
	 * @return ���Ŷ���
	 */
	public TSysOrg getOrgbyUser(String userId);

	/**
	 * ɾ���û����û��ͽ�ɫ��ϵ
	 * 
	 * @param id
	 *            �û�ID
	 */
	public void removeUser(String id) throws IOException;

	public void setMd5PasswordEncoder(Md5PasswordEncoder md5PasswordEncoder);

	public TSysUser getSj();
	
	/**
	 * �������������id����ѯ������������쵼
	 * �������ܣ�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public TSysUser getLeaderByAreaId(String areaId);

	public void afterPropertiesSet() throws Exception;

	/**
	 * ����ϵͳ�û����ϼ���
	 */
	void saveSys();

	/**
	 * �������ܣ��༭һ���û���Ϣ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void editUser(UserForm frm);

	/**
	 * �������ܣ�Ԥ��ͼƬ
	
	 * ���������id���û�id
	
	 * ����ֵ��
	 */
	public void previewImage(String id, HttpServletResponse res);
	
	/**
	 * ��ѯ�û�����
	 */
	public int queryUserNumber();
	/**
	 * ���������ѯ��ǰ����ִ���������û����ǹ���Ա
	 * 
	 * */
	public List<TSysUser> queryUsersByAreaid(TDataLawobjf lawobj)throws Exception;
	
	/**
	 * ����List<TSysUser>�û��������ѡ����һ���û�
	 * 
	 * */
	public TSysUser randomUser(List<TSysUser> users)throws Exception;
	
	/**
	 * ��ѯ�����û�
	 */
	public List<Map<String, String>> queryAllUser();
	public List<ComboboxTree> queryUserComboTree(String areaid);
}