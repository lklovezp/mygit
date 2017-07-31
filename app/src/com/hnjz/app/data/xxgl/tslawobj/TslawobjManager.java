package com.hnjz.app.data.xxgl.tslawobj;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.app.data.po.TBizConfigcheck;
import com.hnjz.app.data.po.TBizConfigpf;
import com.hnjz.app.data.po.TBizConfigpfsj;
import com.hnjz.app.data.po.TBizHistoryconfigcheck;
import com.hnjz.app.data.po.TBizYearLawobj;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataSpeciallawobj;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.configCheckProportion.QuarterEnum;
import com.hnjz.sys.po.TBizCheckedLawobj;
import com.hnjz.sys.po.TDataQuarterChecktimeSet;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

public interface TslawobjManager extends Manager {
	/**
	 * ������������ҵ��Ϣ
	 * */
	public void saveSpecialLawobj(String ids,String names,String year,String quarter)throws Exception;
	/**
	 * ����
	 * ��ݣ����ȣ�ִ���������ƣ�ִ����������
	 * 
	 * ��ѯ��������ҵ
	 * */
	public FyWebResult querySpecialLawobj(String year,String quarter, String lawobjname, String lawobjtype,  String page, String pageSize)
			throws Exception ;
	/**
	 * ɾ����������ҵ
	 * @param id
	 * @throws AppException
	 */
	public void removeSpecialLawobj(String id) throws Exception;
	/**
	 * ��������������趨
	 * @param configCheckForm
	 * @throws Exception
	 */
	public void configCheckSave(ConfigCheckForm configCheckForm,String areaid)throws Exception;
	/**
	 * ��ѯ����������趨��Ϣ
	 */
    public ConfigCheckForm queryConfigCheck(String areaid)throws Exception;
    /**
	 * ����year ��quarter ��ѯ���ü��ȵ����ñ���
	 */
    public ConfigPfForm queryConfigPfForm(String year,String quarter,String areaid)throws Exception;
    /**
	 * �����ɷ����ñ���
	 */
    public void configPfSave(ConfigPfForm configPfForm,String areaid)throws Exception;
    
    /**
   	 * ����������ҵ�ɷ����ñ���
   	 */
    public void specliallConfigPfSave(ConfigPfForm configPfForm,String areaid)throws Exception;
    
    
    // ���صڼ����·ݣ����Ǽ���  
    // ����һ���ļ��� ��һ���ȣ�2��-4�£� �ڶ����ȣ�5��-7�£� �������ȣ�8��-10�£� ���ļ��ȣ�11��
    public int getQuarterInMonth(int month, boolean isQuarterStart);
    //����year ��quarter ��ѯ�����������
    public TBizConfigcheck queryTbizConfigCheck(String areaid)throws Exception;
    
    //��ѯû��ѡ�е��ص���ҵ
    public List<TDataLawobj> queryNoCheckedKeyLawobj(String year,String quarter,String type,String areaid)
    		throws Exception;
    //��ѯû��ѡ�е�һ����ҵ
    public List<TDataLawobj> queryNoCheckedLawobj(String year,String quarter,String type,String areaid)
    		throws Exception;

    /**
	 * ��ʼ��ѡ(����)
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 * @return ��ѡ���         
	 * @throws Exception
	 */
    public String saveStartQuarterCheck(String year, String quarter,String areaid,TDataQuarterChecktimeSet ele)throws Exception;
    public String saveStartQuarterChecknew(String year, String quarter, String areaid,TDataQuarterChecktimeSet ele)
			throws Exception;
    //��ѯ���������ܵ���ҵ
    public List<TDataLawobj> querySpecialLawobj(String year,String quarter,String areaid)
    		throws Exception;
    public List<TDataLawobjf> queryNoCheckedKeyLawobjnew(String year, String quarter,
			String type, String areaid) throws Exception;
    //����ݺͼ��Ȳ�ѯ����ǰ��ݺ͵�ǰ���ȵ��ɷ�ʱ������
    public List<TBizConfigpfsj> quarterConfigpfsj()throws Exception;
     
    //��ȡ���ɷ�
    public String saveCheckedandPf(TBizConfigpfsj tBizConfigpfsj)throws Exception;
    /**
	 * �����ص���ȾԴ�б�
	 * @throws Exception 
	 */
    public List<TDataLawobj> queryAllKeyLawobjListByAreaId(String areaId) throws Exception;
    
    public List<TDataLawobjf> queryAllKeyLawobjListByAreaIdnew(String areaId) throws Exception;
    /**
	 * ���з��ص���ȾԴ�б�
	 * @throws Exception 
	 */
    public List<TDataLawobj> queryNoKeyLawobjListByAreaId(String areaid) throws Exception;
    /**
	 * ������·ݲ�ѯ����ѡ�е���ҵ��Ȼ�����䷢����ѡ���ɷ�
	 * @throws Exception 
	 */
    
    public List<TBizCheckedLawobj> executePfLawobj(String year,String month,String areaid,TBizConfigpfsj tBizConfigpfsj)throws Exception;
    /**
     * ����������������ɷ�����ҵ������ܲ����쵼 
     * */
    public String saveCreateWorkToLeader(String year, String quarter,String month,String areaid,TBizConfigpfsj tBizConfigpfsj,String type)throws Exception;
    
    /**
     * ����������������ɷ������˽���״̬
     * */
    public String saveCreateWorkToUser(String year, String quarter,String month,String areaid,TBizConfigpfsj tBizConfigpfsj,String type)throws Exception;
    
    
    /**
     * ����������������ɷ��������Լ�ѡ��Э���˽���״̬
     * */
    public String saveCreateWorkToUserAndXbr(String year, String quarter,String areaid,TBizConfigpfsj tBizConfigpfsj)throws Exception;
    
    /**
     * �������ѯͳ��List<CheckedCountForm>
     * */
    public List<CheckedCountForm> queryCheckedCountFormList(String year,String quarter,String month,String areaid)throws Exception;
    /**
     * �������ѯͳ��CheckedCountForm
     * */
    public CheckedCountForm queryCheckedCountForm(String year,String quarter, String month,String areaid,String danwei) throws Exception;
    /**
     * �������������ͳ�Ʊ�
     * */
    public void exportCheckedCountForm(String year, String quarter,String areaid, HttpServletResponse res)throws Exception;
    /**
     * ����year ��quarter ��ѯ�䷢������ͱ���
     * */
    public String queryLowPfbl(String year, String quarter,String areaid)throws Exception;
    /**
	 * ������������ʷ�����趨
     * @return 
	 * @throws Exception
	 */
	public String saveHistoryConfigCheck(String year,String quarter,TBizConfigcheck tc)throws Exception;
	/**
	 * ���������year��monthȥ��ѯ��ѡ����
	 * @throws Exception
	 */
	public TBizHistoryconfigcheck queryTBizHistoryconfigcheck(String year,String month,String areaid)throws Exception;
	  /**
		 * ����year ��quarter type month��ѯ���ü��ȵ����ñ���
		 */
	//public ConfigPfForm queryConfigPfForm(String year,String quarter,String type,String month,String areaid)throws Exception;
	  /**
		* ����year ,quarter,month, type,areaid ��ѯ���ü��ȵ����ñ���
		*/
    public ConfigPfForm specliallQueryConfigPfForm(String year,String quarter, String month,String type,String areaid)throws Exception;
    
    /**
	* ����year,month,type,areaid�����ѯ���ü��ȵ����ñ���
	*/
    public ConfigPfForm queryConfigPfFormByType(String year,String month,String type,String areaid)throws Exception;
    
    
    /**
	* ���ݼ��Ȳ�ѯ�������ȵ��·�
	*/
    
    public  List<Combobox> queryMonthByQuarter(String quarter)throws Exception; 
    /**
     * ����������ҵ�ɷ�������������ɷ������˽���״̬
     * */
    //public String saveCreateSpecialWorkToUser(String year, String quarter,String areaid,TBizConfigpfsj tBizConfigpfsj)throws Exception;
    /**
     * ͨ��id�ҵ�TbizConfigpf
     * */
    public TBizConfigpf queryTbizConfigpfById(String id);
    
  //��ѯ����Ҫ�ɷ������������ܵ���ҵ
    public List<TBizCheckedLawobj> querySpecialLawobjByPfbl(String year,String quarter,String month,String areaid,TBizConfigpfsj tBizConfigpfsj)throws Exception;

    /**
     * ����һ���Υ������ҵ���ӵ���������ҵ����
     * */
    public void saveForYeartoSpecail(String year)throws Exception;
    

    /**
	 * ����
	 * ���
	 * 
	 * ��ѯ����ȵ���������ҵ
	 * */
	public List<TDataSpeciallawobj> queryForSpecialLawobj(String year)throws Exception ;
	/**
	 * ������ҵareaid, quarter,year,��ѯ����ѡ�����ҵ��Ϣ
	 * 
	 * */
	public FyWebResult queryCountLawObjList(String areaid,String year,String quarter,String type, String page, String pageSize)throws Exception;
	/**
	 * ������ҵareaid, quarter,year,��ѯ����ѡ�����ҵ��Ϣ
	 * 
	 * */
	public FyWebResult queryCountWFLawObjList(String areaid,String year,String quarter,String type, String page, String pageSize)throws Exception;
	/**
	 * ������ҵareaid, ��ѯ���ڱ��ڸڵ�������Ա��
	 * 
	 * */
	public List<TSysUser> queryUserByAreaidAndIsZaiBianZaiGang(String areaid)throws Exception;
	/**
     * ����������������ɷ������Ÿ����Լ�ѡ��Э���˽���״̬
     * */
    public String saveCreateWorkToOrgUser(String year, String quarter,String month,String areaid,TBizConfigpfsj tBizConfigpfsj,String type)throws Exception;
    /**
	 * 
	 * �������ܣ�����������ۺ���Ϣ�б�
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
	public FyWebResult querySjccLawobjList(String name, String lawobjType, String regionId, String orgId, String curPage, String pageSize) throws Exception;
	/**
	 * ����ִ������������Ϣ
	 * @param name
	 * @param lawobjType
	 * @param regionId
	 * @param orgId
	 * @param res
	 * @throws Exception 
	 */
	public void exportYbLawobjList(String name,String lawobjType,String regionId, String regionName, String orgId,String orgName,HttpServletResponse res) throws Exception;
}