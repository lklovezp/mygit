package com.hnjz.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.State;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.common.AppException;
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
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.FileZipUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.common.util.excel.ExcelUtil;
import com.hnjz.common.util.jacob.Excel2Html;
import com.hnjz.common.util.jacob.Word2Html;
import com.hnjz.data.po.TDataFile;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysUser;

/**
 * 作者：时秋寒
 */
@Service("commonManager")
public class CommonManagerImpl extends ManagerImpl implements CommonManager ,ServletContextAware{

    @Autowired
    private ServletContext sc;

    public TDataFile saveFile(TDataFile filePo, MultipartFile file, String pid, String fileenumtype, UploadFileType path) {
        try {
            // 上传文件并保存数据到数据库
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            FileUpDownUtil.copyFile(file.getInputStream(), uuid, path.getPath(), "");
            TSysUser curUser = CtxUtil.getCurUser();
            Date cur = new Date();
            filePo.setPid(pid);
            filePo.setName(file.getOriginalFilename());
            filePo.setExtension(FileUtil.getExtensionName(file.getOriginalFilename()));
            filePo.setSize(file.getSize());
            filePo.setType(fileenumtype);
            filePo.setOsname(uuid);
            filePo.setRelativepath(path.getPath());
            filePo.setCreateby(curUser);
            filePo.setCreated(cur);
            filePo.setIsActive(YnEnum.Y.getCode());
            filePo.setOrderby(0);
            filePo.setUpdateby(curUser);
            filePo.setUpdated(cur);

            filePo = (TDataFile) this.dao.save(filePo);
            return filePo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TDataFile saveFile(TDataFile filePo, File file, String oName, String pid, String fileenumtype, String filePath) {
        // 保存数据到数据库
        TSysUser curUser = CtxUtil.getCurUser();
        Date cur = new Date();
        filePo.setPid(pid);
        filePo.setName(oName);
        filePo.setExtension(FileUtil.getExtensionName(oName));
        filePo.setSize(file.length());
        filePo.setType(fileenumtype);
        filePo.setOsname(file.getName());
        filePo.setRelativepath(filePath);
        filePo.setCreateby(curUser);
        filePo.setCreated(cur);
        filePo.setIsActive(YnEnum.Y.getCode());
        filePo.setOrderby(0);
        filePo.setUpdateby(curUser);
        filePo.setUpdated(cur);

        filePo = (TDataFile) this.dao.save(filePo);
        return filePo;
    }

    public TDataFile uploadFile(MultipartFile multipartFile,
                                HttpServletRequest request) throws AppException {
        TDataFile tDataFile = null;
        try {
            InputStream is = multipartFile.getInputStream();
            String pid = request.getParameter("pid");
            String fileType = request.getParameter("fileType");
            fileType = FileTypeEnums.getTypeByEnumName(fileType);
            String path = UploadFileType.getPathByEnumName(request.getParameter("path"));
            String fileName = multipartFile.getOriginalFilename();
            Long size = multipartFile.getSize();
            tDataFile = this.saveFile(is, pid, fileType, path, fileName, size);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("上传失败。");
        }
        return tDataFile;
    }

    public String getExtension(String fileType) {
        TSysDic dic = (TSysDic) this.dao.find("from TSysDic where code = ?", fileType).get(0);
        return dic.getDescribe();
    }

    public void downloadFile(String id, HttpServletResponse res) {
        TDataFile file = (TDataFile) this.get(TDataFile.class, id);
        try {
            String path = file.getRelativepath() + File.separator + file.getOsname();
            FileUpDownUtil.downloadFile(res, path, file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setServletContext(ServletContext arg0) {
        this.sc = arg0;
    }

    public void removeFile(String id) {
        try {
            TDataFile file = (TDataFile) this.get(TDataFile.class, id);
            if (file != null) {
                // 删除文件
                FileUpDownUtil.delFile(file.getRelativepath() + File.separator + file.getOsname());
                this.dao.remove(TDataFile.class, file.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFileByPid(String pid) {
        try {
            List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ?	", pid);
            if (listFile.size() > 0) {
                for (TDataFile file : listFile) {
                    // 删除文件
                    FileUpDownUtil.delFile(file.getRelativepath() + File.separator + file.getOsname());
                    this.dao.remove(TDataFile.class, file.getId());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeAllBlFileByPid(String pid) {
        try {
            List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and f.type not in ('2500','2501','2502') ", pid);
            if (listFile.size() > 0) {
                for (TDataFile file : listFile) {
                    this.dao.remove(TDataFile.class, file.getId());
                    // 删除文件
                    FileUpDownUtil.delFile(file.getRelativepath() + File.separator + file.getOsname());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void removeBlFileByPidAndEnumtypeNames(String pid, String enumtypeNames) {
        try {
            String[] enumtypeArr = enumtypeNames.split(",");//Enumname
            String[] enumtypeCodeArr = new String[enumtypeArr.length];//code
            for (int i = 0; i < enumtypeArr.length; i++) {
                enumtypeCodeArr[i] = FileTypeEnums.getTypeByEnumName(enumtypeArr[i]);
            }

            List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and f.type in(" + StringUtil.convertSqlInArray(enumtypeCodeArr) + ") ", pid);
            if (listFile.size() > 0) {
                for (TDataFile file : listFile) {
                    this.dao.remove(TDataFile.class, file.getId());
                    // 删除文件
                    FileUpDownUtil.delFile(file.getRelativepath() + File.separator + file.getOsname());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FyWebResult queryFileList(String pid, String canDel, String fileType, String page, String rows) {
        List<String> ext = new ArrayList<String>();
        ext.add(".jpg");
        ext.add(".png");
        ext.add(".bmp");
        ext.add(".jpeg");
        ext.add(".doc");
        ext.add(".docx");
        ext.add(".txt");
        Map<String, Object> condition = new HashMap<String, Object>();
        String sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ where d.type_='4' and pid_ = :pid";
        condition.put("pid", pid);

        //附件类型
        if (StringUtils.isNotBlank(fileType)) {
            sql += " and d.code_=:fileTypeCode ";
            fileType = FileTypeEnums.getTypeByEnumName(fileType);
            condition.put("fileTypeCode", fileType);
        }

        sql += " order by d.code_, f.CREATED_ desc";

        FyResult pos = this.dao.find(sql, Integer.parseInt(page), rows == null ? 0 : Integer.parseInt(rows), condition);
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
        fy.setRows(rowsList);
        List<Object[]> listLawobj = pos.getRe();
        Map<String, String> dataObject = null;
        String name = "";
        for (Object[] obj : listLawobj) {
            if (String.valueOf(obj[1]).contains(".")) {
                name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
            }
            dataObject = new HashMap<String, String>();
            dataObject.put("id", String.valueOf(obj[0]));
            dataObject.put("url", "/download.mo?id=" + String.valueOf(obj[0]));
            if (obj[2] != null && FileUtil.isImage(String.valueOf(obj[2]))) {
                dataObject.put("imageUrl", obj[0] == null ? "" : "/downThumbnailImage.mo?id=" + String.valueOf(obj[0]));
            }
            dataObject.put("filetype", name);
            dataObject.put("filename", String.valueOf(obj[2]));
            long filesize = Long.valueOf(String.valueOf(obj[3]));
            dataObject.put("filesize1", String.valueOf(obj[3]));
            dataObject.put("filesize", FileUtil.sizeFormat(filesize));
            String operate = "";
            if (String.valueOf(obj[2]).lastIndexOf(".") != -1 && ext.contains(String.valueOf(obj[2]).substring(String.valueOf(obj[2]).lastIndexOf("."), String.valueOf(obj[2]).length()).toLowerCase())) {
                operate = "<a class='b-link' onclick='review(this)' id='" + String.valueOf(obj[0]) + "," + String.valueOf(obj[3]) + "' >预览</a>";
            }
            if (canDel == null || canDel.equals("Y")) {
                operate += OperateUtil.getDloadOperate(String.valueOf(obj[0])) + OperateUtil.getDeleteOperate(String.valueOf(obj[0]));
            } else {
                operate += OperateUtil.getDloadOperate(String.valueOf(obj[0]));
            }

            dataObject.put("operate", operate);
            dataObject.put("filetypecode", String.valueOf(obj[5]));
            rowsList.add(dataObject);
        }
        return fy;
    }

    public FyWebResult queryFileList(String pid, String canDel, String fileType, String tableId) {
        String sql = null;
        String ypath = FileUpDownUtil.path;
        List<String> ext = new ArrayList<String>();
        List<String> ext1 = new ArrayList<String>();
        List<String> ext2 = new ArrayList<String>();
        ext.add(".jpg");
        ext.add(".png");
        ext.add(".bmp");
        ext.add(".jpeg");
        ext.add(".txt");
        ext1.add(".pdf");
        ext2.add(".doc");
        ext2.add(".docx");
        ext2.add(".xls");
        ext2.add(".xlsx");
        Map<String, Object> condition = new HashMap<String, Object>();
        sql = "select f.id_,f.name_,f.size_,f.pid_,f.type_,f.osname_,f.relativepath_ from t_data_file f  where pid_ = :pid";
        condition.put("pid", pid);
        sql += " order by f.CREATED_ desc";
        List<Object[]> fileList = this.dao.findBySql(sql, condition);
//		FyResult pos = this.dao.find(sql, Integer.parseInt(page), rows==null?0:Integer.parseInt(rows), condition);
        FyWebResult fy = new FyWebResult();
        List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
        fy.setRows(rowsList);
        Map<String, String> dataObject = null;
        for (Object[] obj : fileList) {
            dataObject = new HashMap<String, String>();
            dataObject.put("id", String.valueOf(obj[0]));
            dataObject.put("filename", String.valueOf(obj[1]));
            long filesize = Long.valueOf(String.valueOf(obj[2]));
            dataObject.put("filesize", FileUtil.sizeFormat(filesize));
            String operate = "";
            if (String.valueOf(obj[1]).lastIndexOf(".") != -1 && ext.contains(String.valueOf(obj[1]).substring(String.valueOf(obj[1]).lastIndexOf("."), String.valueOf(obj[1]).length()).toLowerCase())) {
                operate = "<a class='b-link' onclick='reviewImg(this)' id='" + String.valueOf(obj[0]) + "," + String.valueOf(obj[2]) + "' >预览</a>";
            }
            if (String.valueOf(obj[1]).lastIndexOf(".") != -1 && ext1.contains(String.valueOf(obj[1]).substring(String.valueOf(obj[1]).lastIndexOf("."), String.valueOf(obj[1]).length()).toLowerCase())) {
                String filepath = ypath + String.valueOf(obj[6]) + String.valueOf(obj[5]);
                operate = "<a class='b-link' onclick='pdfreview(this)' id='" + String.valueOf(obj[0]) + "," + String.valueOf(obj[2]) + "' >预览</a>";
//				operate = "<a class='media' target='_blank'  style='color:#0080FF' href='"+filepath+"' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[2])+"' >预览</a>";
            }
            if (String.valueOf(obj[1]).lastIndexOf(".") != -1 && ext2.contains(String.valueOf(obj[1]).substring(String.valueOf(obj[1]).lastIndexOf("."), String.valueOf(obj[1]).length()).toLowerCase())) {
                operate = "<a class='b-link' onclick='docreview(this)' id='" + String.valueOf(obj[0]) + "," + String.valueOf(obj[2]) + "' >预览</a>";
            }
            if (canDel == null || canDel.equals("Y")) {
                operate += OperateUtil.getDloadOperate(String.valueOf(obj[0])) + OperateUtil.getDeleteOperate(String.valueOf(obj[0]), tableId);
            } else {
                operate += OperateUtil.getDloadOperate(String.valueOf(obj[0]));
            }

            dataObject.put("operate", operate);
            rowsList.add(dataObject);
        }
        return fy;
    }

    //按附件类型和业务id查询附件
    public List<TDataFile> queryFileList(String pid, String fileType) {
        String sql = null;
        sql = "from TDataFile f where pid = ? and type = ?";
        fileType = FileTypeEnums.getTypeByEnumName(fileType);
        List<TDataFile> objList = this.dao.find(sql, pid, fileType);
        return objList;
    }

    //按业务id查询附件
    public List<TDataFile> queryFileList(String pid) {
        String sql = null;
        sql = "from TDataFile f where pid = ? ";
        List<TDataFile> objList = this.dao.find(sql, pid);
        return objList;
    }

    public FyWebResult queryFileListMulfileType(String pid, String canDel, String fileType, String page, String rows) {
        String sql = null;
        String[] canDelArr = canDel.split(",");//多附件对应删除操作
        String[] fileTypeArr = fileType.split(",");//多附件类型Enumname
        String[] fileTypeCodeArr = new String[fileTypeArr.length];//多附件类型code
        for (int i = 0; i < fileTypeArr.length; i++) {
            fileTypeCodeArr[i] = FileTypeEnums.getTypeByEnumName(fileTypeArr[i]);
        }

        Map<String, Object> condition = new HashMap<String, Object>();
        sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ where pid_ = :pid";
        condition.put("pid", pid);

        //附件类型
        if (StringUtils.isNotBlank(fileType)) {
            sql += " and d.code_ in(" + StringUtil.convertSqlInArray(fileTypeCodeArr) + ") ";
        }

        sql += " order by d.code_, f.CREATED_ desc";

        FyResult pos = this.dao.find(sql, Integer.parseInt(page), rows == null ? 0 : Integer.parseInt(rows), condition);
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
        fy.setRows(rowsList);
        List<Object[]> listLawobj = pos.getRe();
        Map<String, String> dataObject = null;
        String name = "";
        for (Object[] obj : listLawobj) {
            if (String.valueOf(obj[1]).contains(".")) {
                name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
            }
            dataObject = new HashMap<String, String>();
            dataObject.put("id", String.valueOf(obj[0]));
            dataObject.put("filetype", name);
            dataObject.put("filetypecode", String.valueOf(obj[5]));
            dataObject.put("filename", String.valueOf(obj[2]));
            Long filesize = Long.valueOf(String.valueOf(obj[3]));
            dataObject.put("filesize", FileUtil.sizeFormat(filesize));

            //type
            String typeCode = obj[5].toString();
            for (int i = 0; i < fileTypeCodeArr.length; i++) {
                if (typeCode.equals(fileTypeCodeArr[i].toString())) {
                    if (canDelArr[i] == null || canDelArr[i].equals("Y")) {
                        dataObject.put("operate", OperateUtil.getDloadOperate(String.valueOf(obj[0])) + OperateUtil.getDeleteOperate(String.valueOf(obj[0])));
                    } else {
                        dataObject.put("operate", OperateUtil.getDloadOperate(String.valueOf(obj[0])));
                    }
                }
            }

            rowsList.add(dataObject);
        }
        return fy;
    }

    public FyWebResult queryFileListMulfileType(String pid, String canDel, String fileType, String tableId, String page, String rows) {
        String sql = null;
        List<String> ext = new ArrayList<String>();
        ext.add(".jpg");
        ext.add(".png");
        ext.add(".bmp");
        ext.add(".jpeg");
        ext.add(".doc");
        ext.add(".docx");
        ext.add(".txt");
        String[] canDelArr = canDel.split(",");//多附件对应删除操作
        String[] fileTypeArr = fileType.split(",");//多附件类型Enumname
        String[] fileTypeCodeArr = new String[fileTypeArr.length];//多附件类型code
        for (int i = 0; i < fileTypeArr.length; i++) {
            fileTypeCodeArr[i] = FileTypeEnums.getTypeByEnumName(fileTypeArr[i]);
        }

        Map<String, Object> condition = new HashMap<String, Object>();
        sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_,f.createby_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ where d.type_='4'  and pid_ = :pid";
        condition.put("pid", pid);
        //附件类型
        if (StringUtils.isNotBlank(fileType)) {
            sql += " and d.code_ in(" + StringUtil.convertSqlInArray(fileTypeCodeArr) + ") ";
        }
        sql += " order by d.code_, f.CREATED_ desc";
        FyResult pos = this.dao.find(sql, Integer.parseInt(page), rows == null ? 0 : Integer.parseInt(rows), condition);
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
        fy.setRows(rowsList);
        List<Object[]> listLawobj = pos.getRe();
        Map<String, String> dataObject = null;
        String name = "";
        for (Object[] obj : listLawobj) {
            if (String.valueOf(obj[1]).contains(".")) {
                name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
            }
            dataObject = new HashMap<String, String>();
            dataObject.put("id", String.valueOf(obj[0]));
            dataObject.put("url", "/download.mo?id=" + String.valueOf(obj[0]));
            if (obj[2] != null && FileUtil.isImage(String.valueOf(obj[2]))) {
                dataObject.put("imageUrl", obj[0] == null ? "" : "/downThumbnailImage.mo?id=" + String.valueOf(obj[0]));
            }
            dataObject.put("filetype", name);
            dataObject.put("filename", String.valueOf(obj[2]));
            Long filesize = Long.valueOf(String.valueOf(obj[3]));
            dataObject.put("filesize", FileUtil.sizeFormat(filesize));

            //type
            String typeCode = obj[5].toString();
            //createby
            String createby = obj[6].toString();
            TSysUser user = CtxUtil.getCurUser();
            for (int i = 0; i < fileTypeCodeArr.length; i++) {
                if (typeCode.equals(fileTypeCodeArr[i].toString())) {
                    String operate = "";
                    List<String> operateList = new ArrayList<String>();
                    if (String.valueOf(obj[2]).lastIndexOf(".") != -1 && ext.contains(String.valueOf(obj[2]).substring(String.valueOf(obj[2]).lastIndexOf("."), String.valueOf(obj[2]).length()).toLowerCase())) {
                        operate = "<a class='b-link' onclick='review(this)' id='" + String.valueOf(obj[0]) + "," + String.valueOf(obj[3]) + "' >预览</a>";
                        operateList.add("yl");
                    }
                    if ((canDelArr[i] == null || canDelArr[i].equals("Y")) && (createby.equals(user.getId()))) {//只能删除自己上传的附件
                        operate += OperateUtil.getDloadOperate(String.valueOf(obj[0])) + OperateUtil.getDeleteOperate(String.valueOf(obj[0]), tableId);
                        operateList.add("xz");
                        operateList.add("sc");
                    } else {
                        operate += OperateUtil.getDloadOperate(String.valueOf(obj[0]));
                        operateList.add("xz");
                    }
                    dataObject.put("operate", operate);
                    String operateStr = "";
                    for (int m = 0; m < operateList.size(); m++) {
                        if (m < operateList.size() - 1) {
                            operateStr += operateList.get(m) + ",";
                        } else {
                            operateStr += operateList.get(m);
                        }
                    }
                    dataObject.put("operateStr", operateStr);
                }
            }

            rowsList.add(dataObject);
        }
        return fy;
    }


    public TDataFile uploadSingleFile(MultipartFile multipartFile,
                                      HttpServletRequest request) throws AppException {
        TDataFile tDataFile = null;
        try {
            InputStream is = multipartFile.getInputStream();
            String pid = request.getParameter("pid");
            String fileType = request.getParameter("fileType");
            fileType = FileTypeEnums.getTypeByEnumName(fileType);
            String path = UploadFileType.getPathByEnumName(request.getParameter("path"));
            String fileName = multipartFile.getOriginalFilename();
            Long size = multipartFile.getSize();

            // 先删除旧文件
            List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and type = ?", pid, fileType);
            if (listFile.size() > 0) {
                for (TDataFile filePo : listFile) {
                    String dirPath = FileUpDownUtil.path.concat(filePo.getRelativepath());
                    // 删除文件
                    new File(dirPath + "//" + filePo.getOsname()).delete();
                    this.dao.remove(TDataFile.class, filePo.getId());
                }
            }
            // 保存文件
            tDataFile = this.saveFile(is, pid, fileType, path, fileName, size);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("上传失败。");
        }
        return tDataFile;
    }


    public void downZipFile(List<String> ids, HttpServletResponse res) {
        try {
            String in = "";
            for (int i = 0; i < ids.size(); i++) {
                if (i > 0) {
                    in += ",";
                }
                in += "'" + ids.get(i) + "'";
            }
            List<Object[]> files = this.dao.find("select a.name, a.osname, a.type, a.relativepath, b.orderby, '' as num from TDataFile a, TSysDic b where b.type='4' and a.type = b.code and a.id in (" + in + ") order by b.orderby");

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String time = sdf.format(date);
            // 压缩文件夹路径
            String path = "";
            path = FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath() + File.separator + time;
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String sourcePath = "";
            // 将源文件拷贝到压缩目录下
            int num = 1;
            Object order = null;
            String flag = String.valueOf(files.get(0)[4]);
            for (int i = 0; i < files.size(); i++) {
                if (i > 0) {
                    if (!flag.equals(String.valueOf(files.get(i)[4]))) {
                        num++;
                        flag = String.valueOf(files.get(i)[4]);
                    }
                    if (num < 10) {
                        order = "0" + String.valueOf(num);
                    } else {
                        order = String.valueOf(num);
                    }
                } else {
                    order = "0" + String.valueOf(num);
                }
                files.get(i)[5] = order;
                sourcePath = FileUpDownUtil.path + File.separator + String.valueOf(files.get(i)[3]) + File.separator + String.valueOf(files.get(i)[1]);
                FileUtil.copyFile(sourcePath, path, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
            }
            // 压缩
            FileZipUtil zip = new FileZipUtil();
            zip.zipFolder(path, path + ".zip");
            // 下载
            FileUpDownUtil.downloadFile(res, path.substring(FileUpDownUtil.path.length() - 1, path.length()) + ".zip", time + ".zip");
            // 删除压缩文件
            FileUtil.removeFolder(path);
            FileUtil.removeFile(path + ".zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Object> preview(String id, HttpServletRequest request) {
        String projPath = request.getSession().getServletContext().getRealPath("/");
        String httpPath = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/preview.htm"));

        HashMap<String, Object> data = new HashMap<String, Object>();
        String content = null;
        String extension = null;
        try {
            TDataFile po = (TDataFile) this.dao.get(TDataFile.class, id);

            extension = po.getExtension().toLowerCase();
            if (extension.equals("docx") || extension.equals("doc")) {
                String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
                String htmlPath = projPath + "/static/temp/preview/" + po.getOsname() + ".html";
                Word2Html.wordToHtml(sourcePath, htmlPath);
                content = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n";
                content += "<html>\r\n";
                content += "<head>\r\n";
                content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n";
                content += "</HEAD>\r\n";
                content += "<body>\r\n";
                content += "<iframe src=\"" + httpPath + "/static/temp/preview/" + po.getOsname() + ".html" + "\" width=\"100%\" height=\"580\" align=\"middle\" frameborder=\"0\"></iframe>\r\n";
                content += "</body>\r\n";
                content += "</html>\r\n";
            } else if (extension.equals("xls") || extension.equals("xlsx")) {
                String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
                String htmlPath = projPath + "/static/temp/preview/" + po.getOsname() + ".html";

                Thread t = Excel2Html.excelToHtml(sourcePath.replace("/", "\\").replace("\\\\", "\\"), htmlPath.replace("/", "\\").replace("\\\\", "\\"));

                int i = 0;
                while (!t.getState().name().equals(State.TERMINATED.name())) {
                    Thread.sleep(200);
                    i += 1;
                    if (i == 100) {
                        // 当线程一直不结束 20秒之后直接中断循环
                        break;
                    }
                }
                content = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n";
                content += "<html>\r\n";
                content += "<head>\r\n";
                content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n";
                content += "</HEAD>\r\n";
                content += "<body>\r\n";
                content += "<iframe scrolling=\"no\" src=\"" + httpPath + "/static/temp/preview/" + po.getOsname() + ".html" + "\" width=\"100%\" height=\"580\" frameborder=\"0\"></iframe>\r\n";
                content += "</body>\r\n";
                content += "</html>\r\n";
            } else if (extension.equals("jpg") || extension.equals("png") || extension.equals("bmp") || extension.equals("jpeg")) {
                // 拷贝文件到static/temp/preview/pic下
                String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
                FileUtil.copyFile(sourcePath, projPath + "/static/temp/preview/", po.getOsname());
                content = "<html>\r\n";
                content += "<head>\r\n";
                content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n";
                content += "</HEAD>\r\n";
                content += "<body>\r\n";
                content += "<img src=\"" + httpPath + "/static/temp/preview/" + po.getOsname() + "\">\r\n";
                content += "</body>";
            } else if (extension.equals("txt")) {
                String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
                content = "<html>\r\n";
                content += "<head>\r\n";
                content += "<meta http-equiv=Content-Type content=\"text/html; charset=UTF-8\">\r\n";
                content += "</HEAD>\r\n";
                content += "<body>\r\n";
                // 读取txt内容
                FileReader fr = new FileReader(sourcePath);
                //可以换成工程目录下的其他文本文件
                BufferedReader br = new BufferedReader(fr);
                String s = null;
                content += "";
                while ((s = br.readLine()) != null) {
                    content += s.replaceAll(" ", "&nbsp;").replaceAll("	", "&nbsp;&nbsp;&nbsp;&nbsp;") + "<br>\r\n";
                }
                br.close();
                content += "</body>";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        data.put("content", content);
        data.put("ext", extension);
        return data;
    }

    public HashMap<String, Object> imgView(String id, HttpServletRequest request) {
        String projPath = request.getSession().getServletContext().getRealPath("/");
        String httpPath = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/imgView"));
        HashMap<String, Object> data = new HashMap<String, Object>();
        String filePath = null;
        try {
            TDataFile po = (TDataFile) this.dao.get(TDataFile.class, id);
            String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
            FileUtil.copyFile(sourcePath, projPath + "/static/temp/preview/", po.getOsname() + "." + po.getExtension());
            filePath = httpPath + "/static/temp/preview/" + po.getOsname() + "." + po.getExtension();
            Map<String, String> page = getFjPageById(id,po);
            data.put("filePath", filePath);
            data.put("next", page.get("next"));
            data.put("last", page.get("last"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    public HashMap<String, Object> docView(String id, HttpServletRequest request) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        try {
            TDataFile po = (TDataFile) this.dao.get(TDataFile.class, id);
            String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
            Map<String, String> page = getFjPageById(id,po);
            data.put("filePath", sourcePath);
            data.put("next", page.get("next"));
            data.put("last", page.get("last"));
            data.put("fileExtension", po.getExtension());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public HashMap<String,Object> pdfView(String id, HttpServletRequest request) {
        String projPath = request.getSession().getServletContext().getRealPath("/");
        String httpPath = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/pdfView"));
        HashMap<String, Object> data = new HashMap<String, Object>();
        String filePath = null;
        try {
            TDataFile po = (TDataFile) this.dao.get(TDataFile.class, id);
            String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
            FileUtil.copyFile(sourcePath, projPath + "/static/temp/preview/", po.getOsname() + "." + po.getExtension());
            filePath = httpPath + "/static/temp/preview/" + po.getOsname() + "." + po.getExtension();
            Map<String, String> page = getFjPageById(id,po);
            data.put("filePath", filePath);
            data.put("next", page.get("next"));
            data.put("last", page.get("last"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public HashMap<String, String> getFjPageById(String id,TDataFile po) {
        String sql = null;
        HashMap<String, String> content = new HashMap<String, String>();
        String lastPage = null;
        String nextPage = null;

        sql = "select e.id_,e.name_,e.extension_ from t_data_file e where e.id_=(select c.n from (select id_,lead(id_,1,0) over (order by created_) n from t_data_file where pid_='" + po.getPid() + "') c where c.id_='" + po.getId() + "')";
        List<Object[]> lastList = this.dao.findBySql(sql);

        lastPage = "&nbsp;";
        for (Object[] obj : lastList) {
            lastPage = "上一份附件：<a class='b-link' onclick='text12(this)' id='" + String.valueOf(obj[0]) + "," + String.valueOf(obj[2]) + "' >" + String.valueOf(obj[1]) + "</a>";
        }
        content.put("last", lastPage);

        sql = "select e.id_,e.name_,e.extension_ from t_data_file e where id_=(select c.p from (select id_,lag(id_,1,0) over (order by created_) p from t_data_file where pid_='" + po.getPid() + "') c where c.id_='" + po.getId() + "')";
        List<Object[]> nextList = this.dao.findBySql(sql);
        nextPage = "&nbsp;";

        for (Object[] obj : nextList) {
            nextPage = "下一份附件：<a  class='b-link' onclick='text12(this)' id='" + String.valueOf(obj[0]) + "," + String.valueOf(obj[2]) + "' >" + String.valueOf(obj[1]) + "</a>";
        }
        content.put("next", nextPage);

        return content;
    }

    public InputStream downFtpFile(String url, String path, String fileName, String userName, String passWord, int port) {
        InputStream is = null;
        FTPClient ftpClient = new FTPClient();
        try {
            String encoding = "UTF-8";
            int reply;
            ftpClient.setControlEncoding(encoding);
            // 连接ftp服务器
            ftpClient.connect(url, port);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.login(userName, passWord);// 登录
            // 设置文件传输类型为二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码
            reply = ftpClient.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
            }
            // 转移到FTP服务器目录至指定的目录下
            ftpClient.changeWorkingDirectory(new String(path.getBytes(encoding), encoding));
            // 获取文件列表
            /*FTPFile[] ffs = ftpClient.listFiles();
            for (FTPFile ff : ffs) {
				if (ff.getName().equals(fileName)) {
					is = ftpClient.retrieveFileStream(ff.getName());
					break;
				}
			}*/
            is = ftpClient.retrieveFileStream(fileName);
            // 连接不能注销
//			ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return is;
    }

    public TDataFile saveFile(InputStream is, String pid, String fileenumtype, String path, String filename, Long size) {
        TDataFile filePo = null;
        try {
            filePo = new TDataFile();
            // 上传文件并保存数据到数据库
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            FileUpDownUtil.copyFile(is, uuid, path, "");
            TSysUser curUser = CtxUtil.getCurUser();
            Date cur = new Date();
            filePo.setPid(pid);
            filePo.setName(filename);
            filePo.setExtension(FileUtil.getExtensionName(filename));
            filePo.setSize(size);
            filePo.setType(fileenumtype);
            filePo.setOsname(uuid);
            filePo.setRelativepath(path);
            filePo.setCreateby(curUser);
            filePo.setCreated(cur);
            filePo.setIsActive(YnEnum.Y.getCode());
            filePo.setOrderby(0);
            filePo.setUpdateby(curUser);
            filePo.setUpdated(cur);
            this.dao.save(filePo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePo;
    }

    public void downTemplate(HttpServletResponse res) {
    	/* List<TDataFileInfo> lawobjDic = this.dao.find("from TDataFileInfo where 1=1");
        String title = "收文登记";
        String title1 = "收发文登记";
    	HashMap<String, String> ret = new HashMap<String, String>();

		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        LinkedHashMap<String, List<LinkedHashMap<String, Object>>> data = this.getExeclData();
        // 生成excel模板
        InputStream is = ExcelUtil.genTemplate(lawobjDic, title, data);
        try {
        	 String classPath = this.getClass().getResource("").getPath();
			classPath = java.net.URLDecoder.decode(classPath, "utf-8");
			// 生成的路径
			String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
			// 保存数据库的名称
			String oName = null;
            res.setHeader("Content-Disposition", "attachment;filename=" + new String((title1 + "导入模板.xls").getBytes("GB2312"), "ISO-8859-1"));
            res.setContentType("application/x-msdownload");
            OutputStream os = res.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
            is.close();
            os.flush();
            os.close();*/
	        try {
	        	String realPath = sc.getRealPath(File.separator);	
	        	String path = realPath + "excel/sfwmb.xlsx";
	            FileUpDownUtil.exportFile(res, path, "收发文批量导入模板.xlsx");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
       /* } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    public void importTemplate(MultipartFile file) throws AppException, ParseException {
        // 读取用户上传的excel将其中的数据封装成listmap
        ArrayList<Map<String, Object>> data1 = ExcelUtil.readLawObjExcel(file);
        Map<String, Object> map = null;
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date sdate = new Date();
        for (int i = 0; i < data1.size(); i++) {
            map = new HashMap<String, Object>();
            map = data1.get(i);
            map.put("创建人", CtxUtil.getCurUser().getId());
            data.add(map);
        }
        // 去的当前表中查询所有的文件编号，用于校验上传的文件编号是否重复。
        List<String> names = getColumnValue();
        if (data.size() > 0) {
            ArrayList<Map<String, Object>> enlist = new ArrayList<Map<String, Object>>();
            Map<String, Object> enmap = null;
            // 循环数据map将map中中文key值和执法对象字典表对比替换成英文列名
            for (int i = 0; i < data.size(); i++) {
                enmap = new HashMap<String, Object>();
                // 如果数据map中key值有包含字典表中的中文字段名时
                if (data.get(i).containsKey("NUMBER_")) {
                    if (names.contains(String.valueOf(data.get(i).get("NUMBER_")))) {
                        throw new AppException("第" + String.valueOf(i + 1) + "行数据中文件编号重复，请重新检查后上传！");
                    }
                    if (StringUtils.isNotBlank(data.get(i).get("NUMBER_").toString())) {
                        String str = data.get(i).get("NUMBER_").toString();//获取上传的值
                        if (str.split("〕").length > 0) {
                            String str1 = str.split("〕")[0];//符合填写规范的然后进行分割，获取出来前面字典类型和年份值
                            String str2 = str.split("〕")[1];//符合填写规范的然后进行分割，获取出来后面的编号值
                            String str3 = str1.split("〔")[0];//获取出来的字典类型值
                            String str4 = str1.split("〔")[1];//获取出来的年份值
                            // 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
                            enmap.put("CODE_", str2);
                            // 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
                            enmap.put("YEAR_", str4);
                            //获取字典类型的值
                            QueryCondition cond = new QueryCondition();
                            StringBuilder sql = new StringBuilder();
                            sql.append("from TSysDic where name = :name");
                            cond.put("name", str3);
                            List<TSysDic> pos = dao.find(sql.toString(), cond);
                            for (TSysDic ele : pos) {
                                if (StringUtils.isNotBlank(ele.getId())) {
                                    // 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
                                    enmap.put("SOURCE_", ele.getId());
                                }
                                if (StringUtils.isNotBlank(ele.getPid())) {
                                    // 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
                                    enmap.put("SOURCEPID_", ele.getPid());
                                }
                            }
                        }else if(str.split("】").length > 0){
							String str1 = str.split("】")[0];//符合填写规范的然后进行分割，获取出来前面字典类型和年份值
							String str2 = str.split("】")[1];//符合填写规范的然后进行分割，获取出来后面的编号值
							String str3 = str1.split("【")[0];//获取出来的字典类型值
							String str4 = str1.split("【")[1];//获取出来的年份值
							// 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
							enmap.put("CODE_", str2);
							// 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
							enmap.put("YEAR_", str4);
							//获取字典类型的值
							QueryCondition cond = new QueryCondition();
							StringBuilder sql = new StringBuilder();
							sql.append("from TSysDic where name = :name");
							cond.put("name", str3);
							List<TSysDic> pos = dao.find(sql.toString(), cond);
							for (TSysDic ele : pos) {
								if(StringUtils.isNotBlank(ele.getId())){
									// 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
									enmap.put("SOURCE_", ele.getId());
								}
								if(StringUtils.isNotBlank(ele.getPid())){
									// 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
									enmap.put("SOURCEPID_", ele.getPid());
								}
							}
						}else if(str.split("]").length > 0){
							String str1 = str.split("]")[0];//符合填写规范的然后进行分割，获取出来前面字典类型和年份值
							String str2 = str.split("]")[1];//符合填写规范的然后进行分割，获取出来后面的编号值
							String str3 = str1.split("[")[0];//获取出来的字典类型值
							String str4 = str1.split("[")[1];//获取出来的年份值
							// 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
							enmap.put("CODE_", str2);
							// 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
							enmap.put("YEAR_", str4);
							//获取字典类型的值
							QueryCondition cond = new QueryCondition();
							StringBuilder sql = new StringBuilder();
							sql.append("from TSysDic where name = :name");
							cond.put("name", str3);
							List<TSysDic> pos = dao.find(sql.toString(), cond);
							for (TSysDic ele : pos) {
								if(StringUtils.isNotBlank(ele.getId())){
									// 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
									enmap.put("SOURCE_", ele.getId());
								}
								if(StringUtils.isNotBlank(ele.getPid())){
									// 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
									enmap.put("SOURCEPID_", ele.getPid());
								}
							}
						}
                    }
                    // 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
                    enmap.put("NUMBER_", data.get(i).get("NUMBER_"));
                }
                if (data.get(i).containsKey("SFWDTAE_")) {
                    // 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
                    enmap.put("SFWDTAE_", data.get(i).get("SFWDTAE_"));
                }
                if (data.get(i).containsKey("TITLE_")) {
                    // 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
                    enmap.put("TITLE_", data.get(i).get("TITLE_"));
                }
                if (data.get(i).containsKey("AUTOGRAPH_")) {
                    // 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
                    enmap.put("AUTOGRAPH_", data.get(i).get("AUTOGRAPH_"));
                }
                if (data.get(i).containsKey("POSITION_")) {
                    // 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
                    enmap.put("POSITION_", data.get(i).get("POSITION_"));
                }
                if (data.get(i).containsKey("TYPE_")) {
                    // 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
                    enmap.put("TYPE_", data.get(i).get("TYPE_"));
                }
                // 将map添加到list中
                enlist.add(enmap);
            }
            StringBuffer sqlBuf = new StringBuffer();
            String uuid = "";
            // 循环已经转化好的listmap按照记录条数拼接成插入sql 并循环插入数据库
            for (int i = 0; i < enlist.size(); i++) {
                sqlBuf = new StringBuffer();
                uuid = UUID.randomUUID().toString().replaceAll("-", "");
                // 插入固定的id和执法对象类型两列
                sqlBuf.append("insert into T_DATA_FILEINFO ( ID_, ISACTIVE_");
                Object[] fields = enlist.get(i).keySet().toArray();
                //String date = enlist.get(i).get("SFWDTAE_").toString();
                //sdate = formatter.parse(date);
                StringBuffer field = new StringBuffer(" ");
                StringBuffer value = new StringBuffer("'" + uuid + "', 'Y'");
                for (int j = 0; j < fields.length; j++) {
                    if ("SFWDTAE_".equals(fields[j])) {
                        field.append(", " + fields[j]);
                        value.append(", to_date('" + enlist.get(i).get(fields[j]) + "','yyyy-mm-dd hh24:mi:ss')");
                    } else {
                        field.append(", " + fields[j]);
                        value.append(", '" + enlist.get(i).get(fields[j]) + "'");
                    }
                }
                String[] oFileds = new String[]{"UPDATED_", "UPDATEBY_", "CREATED_", "CREATEBY_"};
                String[] oValues = new String[]{"sysdate", "'" + CtxUtil.getCurUser().getId() + "'", "sysdate", "'" + CtxUtil.getCurUser().getId() + "'"};
                for (int j = 0; j < oFileds.length; j++) {
                    field.append(", " + oFileds[j]);
                    value.append(", " + oValues[j]);
                }
                sqlBuf.append(field);
                sqlBuf.append(") values (");
                sqlBuf.append(value);
                sqlBuf.append(")");

                this.dao.exec(sqlBuf.toString());
            }
        }
    }

    public LinkedHashMap<String, List<LinkedHashMap<String, Object>>> getExeclData() {
        LinkedHashMap<String, List<LinkedHashMap<String, Object>>> data = new LinkedHashMap<String, List<LinkedHashMap<String, Object>>>();
        List<LinkedHashMap<String, Object>> lt = new ArrayList<LinkedHashMap<String, Object>>();
        //添加sheet页名称
        data.put("发文登记", lt);
        return data;
    }

    public List<String> getColumnValue() {
        String sql = "select NUMBER_ from T_Data_FileInfo where 1=1 ";
        List<String> names = this.dao.findBySql(sql);
        return names;
    }

    public List<Combobox> queryZtList() {
        List<Combobox> listResult = new ArrayList<Combobox>();
        listResult.add(new Combobox("Y", "有效"));
        listResult.add(new Combobox("N", "无效"));
        return listResult;
    }
}
