package com.hnjz.app.common;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataLawdoc;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-9
 * ����������
		��������Manager��
 *
 */
public interface CommonManager {

	/**
	 * 
	 * �������ܣ���ѯ�������������б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Combobox> queryKzlxList();

	/**
	 * 
	 * �������ܣ�ͨ��״̬�����б� Y����Ч N����Ч
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Combobox> queryZtList();
	
	/**
	 * 
	 * �������ܣ���ҵ״̬�����б�0:��Ӫ�С�1:�ѹرա�2:��ͣ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Combobox> queryQyztList();

	/**
	 * 
	 * �������ܣ������ҵ���������б�
	
	 * ���������ִ����������
	
	 * ����ֵ��
	 */
	public List<Combobox> queryIndustryList(String lawobjType);

	/**
	 * 
	 * �������ܣ��������������б�
	
	 * ����������������ͱ�ţ�֧��ģ����ѯ
	
	 * ����ֵ��
	 */
	public List<Combobox> queryFjlxList(String enumName);

	/**
	 * 
	 * �������ܣ���ȡ��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<ComboboxTree> queryRegionTree();
	
	/**
	 * ��ȡִ������������
	 */
	public List<Combobox> queryLawobjtypeTree();
	/**
	 * 
	 * �������ܣ���ȡ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<ComboboxTree> queryOrgTree();
	
	/**
	 * 
	 * �������ܣ���ȡ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Combobox> queryWgTree();
	

	/**
	 * 
	 * �������ܣ���ȡΥ������������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<ComboboxTree> queryIllegalTypeList(String ids);
	
	/**
	 * �����������ͻ�ȡΥ������������
	 * @return
	 */
	public List<ComboboxTree> queryIllegalTypeByTasktypeList(String ids,String tasktype);
	
	/**
	 * 
	 * �������ܣ���ѯ�ֵ��������б�
	
	 * ����������ֵ�����
	
	 * ����ֵ��
	 */
	public List<Combobox> queryTSysDicList(String type);
	
	/**
	 * 
	 * �������ܣ�����type��code��ѯ�ֵ����ֶ�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getDicNameByTypeAndCode(String type,String code);

	/**
	 * 
	 * �������ܣ���ѯ������������������
	
	 * �����������
	
	 * ����ֵ����������������
	 */
	public List<Combobox> queryTaskTypeCombo();

	public List<ComboboxTree> queryTaskTypePubTree(String markId);

	public List<Combobox> queryAreaCombo(String areaId);
	
	public List<Combobox> queryAreaComboByAreaId(String areaId);

	/**
	 * 
	 * �������ܣ��Ƿ�״̬
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Combobox> querySfList();
	
	/**
	 * �������ܣ������ϴ����ļ�

	 * ���������
	 * @param filePo
	 * @param file
	 * @param pid
	 * @param fileenumtype
	 * @param path
 
	 * ����ֵ����
	 */
	public TDataFile saveFile(TDataFile filePo, MultipartFile file, String pid, String fileenumtype, UploadFileType path);

	/**
	 * �������ܣ��������ɵļ�鵥�ļ�
	 * 
	 * @param filePo
	 * @param file
	 * @param oName
	 * @param pid
	 * @param fileenumtype
	 * @param path
	 * @return
	 */
	public TDataFile saveFile(TDataFile filePo, File file, String oName, String pid, String fileenumtype, String filePath);
	/**
	 * �������ܣ��ϴ��������ļ�

	 * ���������
	 * @param is �ļ�������
	 * @param pid ��id
	 * @param fileenumtype ����ö������
	 * @param path ���·������װ�ļ�����鵥�ȣ�
	 * @param filename �ļ���ʵ���� ����չ����
	 * @param size �ļ���С
 
	 * ����ֵ����
	 */
	public TDataFile saveFile(InputStream is, String pid, String fileenumtype, String path, String filename, Long size);
	public TDataFile saveFile(InputStream is, String pid, String fileenumtype, String path, String filename, Long size,TSysUser giveUser);
	
	/**
	 * 
	 * �������ܣ���ȡ����״̬�����б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Combobox> queryRwztCombo();
	
	/**
	 * 
	 * �������ܣ�����ִ����������excel�ļ�������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void downTemplate(String lawObjType, HttpServletResponse res);

	/**
	 * 
	 * �������ܣ�����ִ������excel����
	
	 * ���������lawObjType ִ����������
	 * file excel�ļ�
	
	 * ����ֵ��
	 * @throws AppException 
	 */
	public void importTemplate(String lawObjType, MultipartFile file) throws AppException;

	/**
	 * 
	 * �������ܣ��ϴ��ļ�
	
	 * ���������multipartFile �ϴ����ļ�
	 * request http����
	
	 * ����ֵ��
	 * @throws AppException 
	 */
	public TDataFile uploadFile(MultipartFile multipartFile,
			HttpServletRequest request) throws AppException;

	/**
	 * 
	 * �������ܣ����ݸ������ͻ�ȡ��չ��
	
	 * ���������fileType ��������
	
	 * ����ֵ����չ��
	 */
	public String getExtension(String fileType);

	/**
	 * 
	 * �������ܣ��ļ����ع��÷���
	
	 * ���������
	
	 * ����ֵ��
	 * @param applyId 
	 */
	public void downloadFile(String id, HttpServletResponse res);
	/**
	 * 
	 * �������ܣ���������ͼ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void downThumbnailImage(String id, HttpServletResponse res);
	/**
	 * 
	 * �������ܣ�ɾ�������ļ����÷���
	
	 * ����������ļ�id
	
	 * ����ֵ��
	 */
	public void removeFile(String id);
	
	/**
	 * 
	 * �������ܣ�ɾ������ļ����÷���
	
	 * ���������pid
	
	 * ����ֵ��
	 */
	public void removeFileByPid(String pid);
	
	/**
	 * 
	 * �������ܣ�ɾ������ļ����÷��������������и�����
	
	 * ���������pid:����id;fileenumtype:��������;
	
	 * ����ֵ��
	 */
	public void removeFileByPidAndFileType(String pid,String fileenumtype);
	
	/**
	 * 
	 * �������ܣ�ɾ��������������ļ����������������и��������������ɷ�ת�ɵ��������񸽼���
	
	 * ���������pid:����id;
	
	 * ����ֵ��
	 */
	public void removeAllBlFileByPid(String pid);
	
	/**
	 * 
	 * �������ܣ�
	�฽��ɾ�������������еĸ�����
	 * ���������
	enumtypeNames����������ö������
	 * ����ֵ��
	 */
	public void removeBlFileByPidAndEnumtypeNames(String pid,String enumtypeNames);
	
	/**
	 * 
	 * �������ܣ�ͨ��pid��ѯ�����б�
	
	 * ���������pid��page��ǰҳ��rowsÿҳ��ʾ����
	
	 * ����ֵ��
	 * @param rows2 
	 */
	public FyWebResult queryFileList(String pid, String canDel,String fileType, String page, String rows);

	/**
	 * 
	 * �������ܣ�ͨ��pid��ѯ�����б�
	
	 * ���������pid��page��ǰҳ��rowsÿҳ��ʾ����
	
	 * ����ֵ��
	 * @param rows2 
	 */
	public FyWebResult queryFileList(String pid, String canDel,String fileType,String tableId, String page, String rows);
	
	/**
	 * 
	 * �������ܣ�ͨ��pid��ѯ�����б�
	
	 * ���������pid��page��ǰҳ��rowsÿҳ��ʾ����
	
	 * ����ֵ��
	 * @param rows2 
	 */
	public FyWebResult queryFileListMulfileType(String pid, String canDel,String fileType, String page, String rows);
	
	/**
	 * 
	 * �������ܣ�ͨ��pid��ѯ�����б�
	
	 * ���������pid��page��ǰҳ��rowsÿҳ��ʾ����
	
	 * ����ֵ��
	 * @param rows2 
	 */
	public FyWebResult queryFileListMulfileType(String pid, String canDel,String fileType,String tableId, String page, String rows);

	/**
	 * 
	 * �������ܣ���������ѡ���ִ������
	
	 * ����������ɹ�success�������Ϣ
	
	 * ����ֵ��
	 */
	public String saveChoseeLawobj(String rwid, String lawobjtype, JSONArray array, TSysUser user);
	
	/**
	 * 
	 * �������ܣ���ȡ����ѡ�е�ִ�������б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<TaskandlawobjForm> querySelectLawobjList(String rwid,String lawobjtype);

	/**
	 * 
	 * �������ܣ������ļ���ָ���ļ�id��
	
	 * ���������lawobj-ִ���ļ�����is-�ļ�����fileenumtype-�������ͣ�path-�ļ�·����filename-�ļ���ʵ���ƣ�size-�ļ���С
	
	 * ����ֵ��
	 */
	public TDataFile saveFile(TDataLawdoc lawobj, InputStream is, String fileenumtype, String path, String filename, Long size);

	/**
	 * 
	 * �������ܣ�����ִ���ļ�
	
	 * ���������id-�ļ�id��is-�ļ�����pid-��id��fileenumtype-�������ͣ�path-�ļ�·����filename-�ļ���ʵ���ƣ�size-�ļ���С
	
	 * ����ֵ��
	 */
	public TDataFile saveLawdoc(InputStream is, String pid, String fileenumtype, String path, String filename, Long size);

	/**
	 * 
	 * �������ܣ��������ϴ�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public TDataFile uploadSingleFile(MultipartFile multipartFile,
			HttpServletRequest request) throws AppException;

	/**
	 * 
	 * �������ܣ�����ļ�������
	
	 * ���������ids���ļ�id��
	
	 * ����ֵ��
	 */
	public void downZipFile(List<String> ids, HttpServletResponse res);

	/**
	 * 
	 * �������ܣ�Ԥ���ļ�
	 * 	��ѯ�ļ��������ļ���
	
	 * ���������ids���ļ�id��
	
	 * ����ֵ��
	 * @param request 
	 */
	public HashMap<String, Object> preview(String id, HttpServletRequest request);

	/**
	 * 
	 * �������ܣ����ִ������ĳһ�ֶε�����ֵ�����磺��ҵ��ȾԴ���������ƣ�
	
	 * ���������lawobjtype-ִ���������ͣ�enumCode-�ֶ�ö��ֵ
	
	 * ����ֵ��
	 */
	public List<String> getColumnValue(String lawobjtype,String enumCode);

	public List<String> findBySql(String sql, Object... canshu);
	

	/**
	 * ftp����
	 * 
	 * @param url ftp��ַ �磺192.168.0.1
	 * @param path �ļ����������ftp��Ŀ¼������·�� �磺folder1/folder2/folder3
	 * @param fileName �ļ��� �磺file.jpg
	 * @param userName ftp�û���
	 * @param passWord ftp����
	 * @param passWord ftp�˿�
	 * @return
	 */
	public InputStream downFtpFile(String url, String path, String fileName, String userName, String passWord, int port);

	/**
	 * 
	 * �������ܣ��жϸ��������Ƿ����
	
	 * ���������ö�����ƣ���,������
	
	 * ����ֵ��
	 */
	public List<Map<String, String>> getIsBtlist(String fileType);

	public List<Combobox> getJcmbByTaskType(String tasktype);
	/**
	 * ��ѯ��ǰ�û������µ�����
	 * 
	 * @return
	 */
	public List<Combobox> queryAreaComboWithCur(String areaId);
	
	/**
	 * ��ѯ��ǰ�õ�����,�����admin��ѯ���е�����
	 */
	public List<Combobox> queryAreaCur(String areaId);
	/**
	 * 
	 * �������ܣ�ִ���������������б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Combobox> queryzfdxmcList();
	/**
	 * 
	 * �������ܣ�ִ���������������б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public LinkedHashMap<String, List<LinkedHashMap<String, Object>>> getExeclData(String lawObjType);
	/**
	 * ���������ͺ�ҵ��id��ѯ����
	 * @param pid
	 * @param fileType
	 * @return
	 */
	public List<TDataFile> queryFileList(String pid,String fileType);
	
	/**
	 * ��ҵ��id��ѯ����
	 * @param pid
	 * @param fileType
	 * @return
	 */
	public List<TDataFile> queryFileList(String pid);
	
	/**
	 * ��ҵ��id��ѯ����,ȥ��ĳ�����͸���
	 * @param pid
	 * @param fileType
	 * @return
	 */
	public List<TDataFile> queryNoSomeFileList(String pid,String fileType1,String fileType2);
	
	public TDataFile saveFile(TDataFile t);
	
	/**
	 * 
	 * �������ܣ��ļ�������������Ϣ�Ĺ��÷���
	 * ���������
	 * ����ֵ��
	 * @param applyId 
	 */
	public void exportFile(String id, String applyId, HttpServletResponse res);
	
	/**
	 *
	 * �������ܣ�Ԥ��ͼƬ
	 * 	��ѯͼƬ������ͼƬ��ַ ����һ�ݸ�������һ�ݸ�����
	 * ���������id���ļ�id
	 * ����ֵ��ͼƬ·��
	 * @param request
	 */
	public HashMap<String, Object> imgView(String id, HttpServletRequest request);

}