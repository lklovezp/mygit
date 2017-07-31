package com.hnjz.app.work.unzip;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.data.xxgl.lawobj.LawobjManagerImpl;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.FileZipUtil;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;

/*
 * �ϴ�zip�ļ���Mangager
 * */

@Service("unzipManager")
public class UnzipManagerImpl extends ManagerImpl implements UnzipManager {
	private static final Log log = LogFactory.getLog(UnzipManagerImpl.class);
	@Value("#{settings['FILEPATH']}")
	private String filePath;

	@Autowired
	private OrgManager orgManager;

	@Autowired
	private LawobjManager lawobjManager;
    @Autowired
    private CommonManager commonManager;
	/*
	 * �ϴ�zip���ύ����
	 */

	@Override
	public TDataFile uploadFile(MultipartFile multipartFile,
			HttpServletRequest request) throws AppException {
		TDataFile tDataFile = null;
		String path ="";
		try {
			InputStream is = multipartFile.getInputStream();
			String pid = request.getParameter("pid");
			String fileType = request.getParameter("fileType");
			fileType = FileTypeEnums.getTypeByEnumName(fileType);
			path = UploadFileType.TEMP.getPath();

			String fileName = multipartFile.getOriginalFilename();
			Long size = multipartFile.getSize();
//            log.info("is���ݣ�==="+is+"pid���ݣ�==="+pid+"fileType���ݣ�==="+fileType+
//            		"path���ݣ�==="+path+"fileName���ݣ�==="+fileName+"size���ݣ�==="+size);
           
				tDataFile = saveFile(is, pid, fileType, path, fileName, size);
				try {
					unZipAndGetData(tDataFile,path,multipartFile);
				} catch (Exception e) {
					throw new AppException("�����ϴ��ļ�������");
				}
		 
		} catch (Exception e) {
			throw new AppException("�ϴ��ļ�������");
		}
		 return tDataFile;
		
	}

	/*
	 * �ϴ�zip���浽���ݿ�
	 */
	@Override
	public TDataFile saveFile(InputStream is, String pid, String fileenumtype,
			String path, String filename, Long size) {
		TDataFile filePo = null;
		filePo = new TDataFile();
		// �ϴ��ļ����������ݵ����ݿ�
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		uuid = uuid.substring(0, uuid.length() - 4);
		String zipName = uuid + ".zip";
		try {
			FileUpDownUtil.copyFile(is, zipName, path, "");
		} catch (IOException e) {
			log.error(e);
		}
		TSysUser curUser = CtxUtil.getCurUser();
		Date cur = new Date();
		filePo.setPid(pid);
		filePo.setName(filename);
		filePo.setExtension(FileUtil.getExtensionName(filename));
		filePo.setSize(size);
		filePo.setType(fileenumtype);
		filePo.setOsname(zipName);
		filePo.setRelativepath(path);
		filePo.setCreateby(curUser);
		filePo.setCreated(cur);
		filePo.setIsActive(YnEnum.Y.getCode());
		filePo.setOrderby(0);
		filePo.setUpdateby(curUser);
		filePo.setUpdated(cur);
		this.dao.save(filePo);
		return filePo;
	}
/*
 * unZipAndGetData����
 * ��ѹzip��ʽ�ļ�����ѯ���ݿ���ϴ��������ļ��н��жԱȲ��𼶼������ݿ��Ӧ����Ŀ
 * */    
	
	@Override
	public TDataFile unZipAndGetData(TDataFile td, String outUnZipPath, MultipartFile multipartFile)
			throws AppException {
		FileZipUtil zipUtil = new FileZipUtil();
		String outPath = filePath + td.getRelativepath();
		// �Ƚ�ѹ�ļ�
		String absuloutPath = outPath + td.getOsname();
		try {
			absuloutPath = absuloutPath.replace("/", "\\");
			outPath = outPath.replace("/", "\\");
			outPath = zipUtil.unZip(absuloutPath, outPath);
		// �õ���ҵ���ļ������ͺ��ļ��е�·��
		} catch (IOException e) {
			throw new AppException(e.getMessage());
		}
		String companyName = GetPathNameUtil.getSingleName(outPath);
		
		// �����û�Id��ȡ����Id
		TSysOrg org = orgManager.getOrgByUserid(CtxUtil.getUserId());
		String orgId = org.getId();
		// ���ݲ���Id������Id�����ҵ����Ϣ
	    
		FyWebResult fr=null;
		try {
			//TSysArea area = (TSysArea) this.get(TSysArea.class,CtxUtil.getAreaId());
			 fr = lawobjManager.queryGywryList("","","","","", "", "", "Y", "-1", "-1");
					
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
        //���ϴ��������ļ�������ҵ���������ݿ�Ա�
		outPath=outPath+"\\"+companyName;
		File file=new File(outPath);
		Map<String, String> pathAndNameMap=new HashMap<String, String>();
		List<Combobox> enumNamelist=new ArrayList<Combobox>();
		enumNamelist=FileTypeEnums.getTypeListByEnumName("GYWRY");
		List<Map<String, String>> listMap = fr.getRows();
		//log.info("��ȡ������ҵ���ƣ�==="+listMap.toString());
		File[] files=file.listFiles();
		pathAndNameMap=GetPathNameUtil.getFilesNameAndPath(files, outPath);
		Iterator<Entry<String, String>> iterator=pathAndNameMap.entrySet().iterator();
        /*
         * ���ȱȽ��ϴ��������Լ��ļ��У���ҵ���ƣ������ݿ�Ա��Ƿ����
         * */		
		for (Map<String, String> map : listMap) {
			if(map.get("name").equals(companyName)){
				
				if (!file.exists())
				{		       
				   return null;
				}
				
					while(iterator.hasNext()){
						Entry<String, String>  entry=iterator.next();
						/*
				         * ���Ȼ�ȡ�ϴ��������ļ��ж���Ŀ¼���������ͣ������ݿ�������ֵ���бȽ�
				         * ���ն�Ӧ��Ŀ¼�𼶼��븽��
				         * */
						for(Combobox combobox:enumNamelist){
						  if(combobox.getName().equals(entry.getKey())){
							file=new File(entry.getValue());
						    if (!file.exists())
							{		       
							   return null;
							}else{
						    files=file.listFiles();
							pathAndNameMap=GetPathNameUtil.getFilesNameAndPath(files, entry.getValue());
							for(Entry<String, String> entryLast:pathAndNameMap.entrySet()){
							   if(entryLast.getKey().isEmpty()){
								   return null;
							   }else{
								   							  
								   try {
									   File imgFile=new File(entryLast.getValue());
									   InputStream steam=new FileInputStream(imgFile);
										td= commonManager.saveFile(steam, map.get("id"),combobox.getId(), "/xxgl", entryLast.getKey(),imgFile.length());
									} catch (IOException e) {
									throw new AppException("��ҵ�������������ϴ�����");
									}
							   }							
								
							}					
							
							}       	

						}
					}
				
				}
				
			}			
			
		}
		return null;
	}

	@Override
	public String findCompany(String fileName) throws AppException {
		 String qyName=fileName;
         qyName=qyName.substring(0, qyName.length()-4);
       
         FyWebResult fr=null;
 		try {
 			//TSysArea area = (TSysArea) this.get(TSysArea.class,CtxUtil.getAreaId());
 			 fr = lawobjManager.queryGywryList("","","","","", "", "", "Y", "-1", "-1");
 					
 		} catch (Exception e) {
 			throw new AppException(e.getMessage());
 		}
 		List<Map<String, String>> listMap = fr.getRows();
 		List<String> allName=new ArrayList<String>();
 		for (Map<String, String> map : listMap){
 			allName.add(map.get("name"));
 		}
 		
 		if(allName.contains(qyName)==false){
 			return "false";
		}else{
            return "true";
		}
	}
	
	
	
}