package com.hnjz.app.data.xxgl.lawobj;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.app.data.po.TDataLawobjeia;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-3
 * ����������
	ִ������Manager�ӿ�
 *
 */
public interface LawobjManager  extends Manager {

	/**
	 * 
	 * �������ܣ�ִ�������ۺ���Ϣ�б�
	 
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param lawobjType:ִ����������
	 * @param regionId:��������������
	 * @param orgId:������ܲ���id
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * @return
	 * @throws Exception
	 */
	public FyWebResult queryLawobjList(String name, String lawobjType, String regionId, String orgId, String curPage, String pageSize) throws Exception;
	/**
	 * 
	 * �������ܣ���ҵ��ȾԴ�б�
	
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param year:��ݣ���ȳ��ʱ������
	 * @param regionCode:��������������
	 * @param orgId:������ܲ��ű���
	 * @param qyzt:��ҵ״̬
	 * @param kzlx:��������
	 * @param isActive:�Ƿ���Ч
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryGywryList(String year,String quarter,String name, String regionId, String orgId, String qyzt,String kzlx, String isActive, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ��������¹�ҵ��ҵ��ȾԴ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public TDataLawobj saveOrUpdateLawobj(TDataLawobj lawobj, String jsxmid) throws Exception;

	public void saveUploadFile(MultipartFile file, String fileType);

	/**
	 * 
	 * �������ܣ���ȡ��ҵ��ȾԴ����
	
	 * ���������jsxmid:����ǽ�����Ŀת�����ģ����½�����Ŀ��Ӧ��ִ������id
	
	 * ����ֵ��
	 */
	public TDataLawobj getLawobjInfo(TDataLawobj lawobj);

	/**
	 * 
	 * �������ܣ�ִ��������Ϊ��Ч��ͨ�÷�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void removeLawobj(String id);

	/**
	 * 
	 * �������ܣ�������Ŀ�б�
	
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param year:��ݣ���ȳ��ʱ������
	 * @param regionCode:��������������
	 * @param orgId:������ܲ��ű���
	 * @param kzlx:��������
	 * @param isActive:�Ƿ���Ч
	 * @param isChoose:�Ƿ���ѡ��ִ������(Y/N)
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryJsxmList(String year,String quarter,String name, String regionId, String orgId, String lawobjId, String industryId, String isActive, String isChoose, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ����滷����Ϣ
	
	 * ���������hpxxForm��������Ϣ��������
	
	 * ����ֵ��
	 */
	public TDataLawobjeia saveOrUpdateHpxx(HpxxForm hpxxForm);

	/**
	 * 
	 * �������ܣ�������Ϣ�б�
	
	 * ���������pid��ִ������id��curPage:��ǰҳ pageSize��ÿҳ��ʾ����
	
	 * ����ֵ��
	 */
	public FyWebResult queryHpxxList(String pid, String curPage, String pageSize);

	/**
	 * 
	 * �������ܣ���û�����Ϣ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public HpxxForm getHpxxInfo(HpxxForm hpxxForm);

	/**
	 * 
	 * �������ܣ�ɾ��������Ϣ
	
	 * �������������id
	
	 * ����ֵ��
	 */
	public void removeHpxx(String id);

	/**
	 * 
	 * �������ܣ�ҽԺ��Ϣ�б�
	
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param year:��ݣ���ȳ��ʱ������
	 * @param regionId:��������������
	 * @param orgId:������ܲ��ű���
	 * @param qyzt:��ҵ״̬
	 * @param isActive:�Ƿ���Ч
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryYyxxList(String year,String quarter,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ���¯��Ϣ�б�
	
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param year:��ݣ���ȳ��ʱ������
	 * @param regionId:��������������
	 * @param orgId:������ܲ��ű���
	 * @param qyzt:��ҵ״̬
	 * @param isActive:�Ƿ���Ч
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryGlxxList(String year,String quarter,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ�����������Ϣ�б�
	
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param year:��ݣ���ȳ��ʱ������
	 * @param regionId:��������������
	 * @param orgId:������ܲ��ű���
	 * @param qyzt:��ҵ״̬
	 * @param isActive:�Ƿ���Ч
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryJzgdList(String year,String quarter,String name, String regionId,String orgId,String qyzt, String isActive, String sgdwmc, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ�������Ϣ�б�
	
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param year:��ݣ���ȳ��ʱ������
	 * @param regionId:��������������
	 * @param orgId:������ܲ��ű���
	 * @param qyzt:��ҵ״̬
	 * @param isActive:�Ƿ���Ч
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryScxxList(String year,String quarter,String name, String regionId, String orgId,String qyzt,String industryId, String isActive, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ�����ҵ��Ϣ�б�
	
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param year:��ݣ���ȳ��ʱ������
	 * @param orgId:������ܲ��ű���
	 * @param qyzt:��ҵ״̬
	 * @param isActive:�Ƿ���Ч
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryFwyList(String year,String quarter,String name, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;
	
	
	/**
	 * 
	 * �������ܣ�����ҵ��Ϣ�б�
	
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param year:��ݣ���ȳ��ʱ������
	 * @param orgId:������ܲ��ű���
	 * @param qyzt:��ҵ״̬
	 * @param isActive:�Ƿ���Ч
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryZzyList(String year,String quarter,String name, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * �������ܣ�����ҵ��Ϣ�б�
	
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param year:��ݣ���ȳ��ʱ������
	 * @param orgId:������ܲ��ű���
	 * @param qyzt:��ҵ״̬
	 * @param isActive:�Ƿ���Ч
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryYlyList(String year,String quarter,String name,  String orgId,String qyzt, String isActive, String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * �������ܣ���ʳҵ��Ϣ�б�
	
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param year:��ݣ���ȳ��ʱ������
	 * @param orgId:������ܲ��ű���
	 * @param qyzt:��ҵ״̬
	 * @param isActive:�Ƿ���Ч
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryYsyList(String year,String quarter,String name,String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * �������ܣ�������ֳ��Ϣ�б�
	
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param year:��ݣ���ȳ��ʱ������
	 * @param regionId:��������������
	 * @param orgId:������ܲ��ű���
	 * @param qyzt:��ҵ״̬
	 * @param isActive:�Ƿ���Ч
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryXqyzList(String year,String quarter,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ����ɹ�ҵ��ȾԴ�ı༭����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public Map<String, StringBuffer> lawobjEditInnerHtml(List<TDataLawobjdic> list, TDataLawobj lawobj);

	/**
	 * 
	 * �������ܣ�ͨ���������� ��ȡ��Ӧ��ִ����������
	
	 * ���������lawobjtypeidִ����������  enumname����������ö�ٱ��
	
	 * ����ֵ��
	 */
	public String getColumnNameByEnumname(String enumname);

	/**
	 * 
	 * �������ܣ��Զ����� ��ȾԴ���������Ϣ���� ��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String lawobjDetailInnerHtml(List<TDataLawobjdic> list, TDataLawobj lawobj);

	/**
	 * 
	 * �������ܣ���ȡ������Ŀת����ִ������Ĺ�������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public JsxmForm getJsxmInfo(String id) throws Exception;

	/**
	 * 
	 * �������ܣ����潨����Ŀ��Ӧ��ִ������id
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveJsxmLawobjId(String jsxmid, TDataLawobj lawobj);

	/**
	 * 
	 * �������ܣ�����ö��ֵ��ѯ��Ӧ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<String> queryLawobjColumnByEnmu(String lawobjType, String... args);

	/**
	 * 
	 * �������ܣ�ͨ��ö��ֵ���ִ������ĳ���ֶ�ֵ
	
	 * ���������
	 * @param enumCode:ö��ֵ���
	 * @param lawobjid:ִ������id
	
	 * ����ֵ��
	 */
	public String getLawobjColumnValue(String enumCode, String lawobjid);

	/**
	 * 
	 * �������ܣ��������html�������� ��������ֶζ��õ�ֵ�����ݿ���Ϊid�Ļ��name����ʾ��������
	
	 * ���������ִ�������ֵ�po��ִ������po
	
	 * ����ֵ��
	 */
	public String detailInnerHtmlHelp(TDataLawobjdic tDataLawobjdic, TDataLawobj lawobj);
	
	/**
	 * 
	 * �������ܣ��ж�ִ�����������Ƿ��ظ�(�ظ�����true)
	
	 * ���������
	
	 * ����ֵ��
	 */
	public Boolean checkLawobjname(TDataLawobj lawobj);

	/**
	 * 
	 * �������ܣ���ѯִ����ʷ��¼
	
	 * ���������ִ������id
	
	 * ����ֵ��
	 */
	public FyWebResult queryLawHistoryList(String id, String page, String pageSize);
	/**
	 * 
	 * @param lawobjtypeid ִ������Id
	 * @param lawobjId	ִ������id
	 * @param code	�������ֶα���
	 * @param userId ������id
	 * @throws Exception
	 */
	public void transferCjr(String lawobjtypeid, String lawobjId,String code,String userId)  throws Exception;
	
	/**
	 * ����û�б����е���ȾԴ�б�
	 */
	public List<TDataLawobj> queryNoCheckedList(String year) throws Exception;
	
	/**
	 * �����ص���ȾԴ�б�
	 */
	public List<TDataLawobj> queryAllKeyLawobjList() throws Exception;
	
	/**
	 * ���з��ص���ȾԴ�б�
	 */
	public List<TDataLawobj> queryNoKeyLawobjList() throws Exception;
	
	/**
	 * ������ȾԴ�õ���ȾԴ������ܲ���id
	 * @throws Exception 
	 */
	public String getOrgidByLawobj(TDataLawobj lawobj) throws Exception;
	
	/**
	 * ������ȾԴ�õ���ȾԴ����
	 * @throws Exception 
	 */
	public String getNameByLawobj(TDataLawobj lawobj) throws Exception;
	
	/**
	 * ����ִ������õ�ִ����������״̬
	 * @throws Exception 
	 */
	public String getScztByLawobj(TDataLawobj lawobj) throws Exception;
	
	/**
	 * ������ȾԴ�õ���ȾԴ�Ŀ�������
	 * @throws Exception 
	 */
	public String getKzlxByLawobj(TDataLawobj lawobj) throws Exception;
	
	/**
	 * ��ѯ����Υ�����������ҵ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryIllegalLawobjList(String year) throws ParseException, Exception;
	
	/**
	 * ��ѯ����һ����ҵ�б�(���з��ص㹤ҵ��ȾԴ����������������ҵ)
	 * @return
	 * @throws Exception
	 */
	public List<TDataLawobj> queryAllNormalList() throws Exception;
	/**
	 * �����������ִ��������е�ֵ
	 */
	public String getValueByColumnName(TDataLawobj lawobj,String columnName);
	
	/**
	 * ������ȾԴ�õ���ȾԴ����������
	 * @throws Exception 
	 */
	public String getRegionIdByLawobj(TDataLawobj lawobj) throws Exception;
	

	/**
	 * ������ҵ���ͻ�ȡ��ҵ��Ӧ���ֵ�ֵ
	 * t_data_lawobjdic
	 * @throws Exception 
	 */
	public Map<String, String> getLawobjByType(String type) throws Exception;
	
	/**
	 * ����ִ��������Ϣ
	 * @param name
	 * @param lawobjType
	 * @param regionId
	 * @param orgId
	 * @param res
	 * @throws Exception 
	 */
	public void exportLawobjList(String name,String lawobjType,String regionId, String regionName, String orgId,String orgName,HttpServletResponse res) throws Exception;
	
}