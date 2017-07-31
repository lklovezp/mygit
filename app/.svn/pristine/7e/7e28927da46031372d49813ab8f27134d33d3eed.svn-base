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
 * 上传zip文件的Mangager
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
	 * 上传zip的提交方法
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
//            log.info("is数据：==="+is+"pid数据：==="+pid+"fileType数据：==="+fileType+
//            		"path数据：==="+path+"fileName数据：==="+fileName+"size数据：==="+size);
           
				tDataFile = saveFile(is, pid, fileType, path, fileName, size);
				try {
					unZipAndGetData(tDataFile,path,multipartFile);
				} catch (Exception e) {
					throw new AppException("解析上传文件出错！");
				}
		 
		} catch (Exception e) {
			throw new AppException("上传文件出错！");
		}
		 return tDataFile;
		
	}

	/*
	 * 上传zip保存到数据库
	 */
	@Override
	public TDataFile saveFile(InputStream is, String pid, String fileenumtype,
			String path, String filename, Long size) {
		TDataFile filePo = null;
		filePo = new TDataFile();
		// 上传文件并保存数据到数据库
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
 * unZipAndGetData方法
 * 解压zip格式文件，查询数据库和上传上来的文件夹进行对比并逐级加入数据库对应的栏目
 * */    
	
	@Override
	public TDataFile unZipAndGetData(TDataFile td, String outUnZipPath, MultipartFile multipartFile)
			throws AppException {
		FileZipUtil zipUtil = new FileZipUtil();
		String outPath = filePath + td.getRelativepath();
		// 先解压文件
		String absuloutPath = outPath + td.getOsname();
		try {
			absuloutPath = absuloutPath.replace("/", "\\");
			outPath = outPath.replace("/", "\\");
			outPath = zipUtil.unZip(absuloutPath, outPath);
		// 得到企业的文件夹名和和文件夹的路径
		} catch (IOException e) {
			throw new AppException(e.getMessage());
		}
		String companyName = GetPathNameUtil.getSingleName(outPath);
		
		// 根据用户Id获取部门Id
		TSysOrg org = orgManager.getOrgByUserid(CtxUtil.getUserId());
		String orgId = org.getId();
		// 根据部门Id和区域Id获得企业的信息
	    
		FyWebResult fr=null;
		try {
			//TSysArea area = (TSysArea) this.get(TSysArea.class,CtxUtil.getAreaId());
			 fr = lawobjManager.queryGywryList("","","","","", "", "", "Y", "-1", "-1");
					
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
        //将上传过来的文件名（企业名）和数据库对比
		outPath=outPath+"\\"+companyName;
		File file=new File(outPath);
		Map<String, String> pathAndNameMap=new HashMap<String, String>();
		List<Combobox> enumNamelist=new ArrayList<Combobox>();
		enumNamelist=FileTypeEnums.getTypeListByEnumName("GYWRY");
		List<Map<String, String>> listMap = fr.getRows();
		//log.info("获取所有企业名称：==="+listMap.toString());
		File[] files=file.listFiles();
		pathAndNameMap=GetPathNameUtil.getFilesNameAndPath(files, outPath);
		Iterator<Entry<String, String>> iterator=pathAndNameMap.entrySet().iterator();
        /*
         * 首先比较上传上来的以及文件夹（企业名称）和数据库对比是否存在
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
				         * 首先获取上传上来的文件夹二级目录（附件类型）和数据库的数据字典进行比较
				         * 按照对应的目录逐级加入附加
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
									throw new AppException("企业附件类型数据上传出错");
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
