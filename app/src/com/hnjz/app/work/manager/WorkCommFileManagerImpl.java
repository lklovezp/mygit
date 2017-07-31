package com.hnjz.app.work.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.enums.DataFileType;
import com.hnjz.app.work.dao.WorkCommFileDao;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.po.WorkCommFile;
import com.hnjz.app.work.po.WorkJcinfo;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.FileZipUtil;
import com.hnjz.common.util.FileUtil.FileSizeEnum;
import com.hnjz.sys.user.UserManager;

/**
 * 
 * 附件管理
 * 
 * @author xuguanghui
 * @version $Id: WorkCommFileManagerImpl.java, v 0.1 Jul 2, 2013 6:12:37 PM
 *          Administrator Exp $
 */
@Service(value = "commFileManager")
public class WorkCommFileManagerImpl {
    // private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private WorkCommFileDao       commFileDAO;
    @Autowired
    private WorkManagerImpl       workManager;
    @Autowired
    private WorkJcinfoManagerImpl jcInfoManager;
    @Autowired
    protected UserManager         userManager;
    private SimpleDateFormat      sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 获取对象
     * 
     * @param fileId
     * @return
     */
    public WorkCommFile get(String fileId) {
        return commFileDAO.get(fileId);
    }

    /**
     * 从附件集合中得到指定类型的附件
     * 
     * @param allList
     * @param type
     * @return
     */
    public List<WorkCommFile> getFileList(List<WorkCommFile> allList, DataFileType type) {
        List<WorkCommFile> re = new ArrayList<WorkCommFile>();
        for (WorkCommFile ele : allList) {
            if (StringUtils.equals(type.getCode(), ele.getType())) {
                re.add(ele);
            }
        }
        return re;
    }

    /**
     * 获取任务的附件集合
     * 
     * @param workId 任务Id
     * @param type 附件类型
     * @return
     */
    public List<WorkCommFile> getFileList(String workId, DataFileType type) {
        String hsql = "from WorkCommFile t  where t.work.id = ? and t.type = ?";
        if (null == type) {
            type = DataFileType.XUNWENBILU;
        }
        return commFileDAO.find(hsql, workId, type.getCode());
    }

    /**
     * 获取任务的全部附件集合
     * 
     * @param workId 任务Id
     * @return 任务的全部附件集合
     */
    public List<WorkCommFile> getFileList(String workId) {
        String hsql = "from WorkCommFile t where t.work.id = ? ";
        return commFileDAO.find(hsql, workId);
    }
    /**
     * 获取任务的全部附件集合
     * 
     * @param workId 任务Id
     * @return 任务的全部附件集合
     */
    public FyWebResult getFileListJson(String workId,String page,String pageSize, String oper) {
        String hsql = "from WorkCommFile t where t.work.id = '"+workId+"' order by t.contentType";
        QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        FyResult pos = commFileDAO.find(hsql, data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<WorkCommFile> infects = pos.getRe();
        Map<String, String> row = null;
        for (WorkCommFile ele : infects) {
        	ele.setSize(FileUtil.sizeFormat(ele.getSize(), FileSizeEnum.B, FileSizeEnum.KB));
            row = new HashMap<String, String>();
            row.put("id", ele.getId());
            row.put("name", ele.getName());
            row.put("type", DataFileType.getType(ele.getType()).getName());
            row.put("size", String.valueOf(ele.getSize()));
            row.put("desc", ele.getDesc());
            row.put("oper", oper);
            rows.add(row);
        }
        fy.setRows(rows);
        
        return fy;
    }

    /**
     * 上传文件
     * 
     * @param applyId
     * @param file
     * @param name
     * @param desc
     * @param url
     * @param type
     *            附件类型
     * @throws Exception
     */
    public void saveFile(String applyId, MultipartFile file, String name, String desc,
                         DataFileType type, String extInfo) throws Exception {
        String fileName = file.getOriginalFilename();
        // 当自定义文件名时
        String suffix = "";
        if (StringUtils.isNotBlank(name)) {
            suffix = fileName.substring(fileName.lastIndexOf("."));
            fileName = name + suffix;
        }
        // 将文件上传至服务器
        // 上传至服务器的文件名使用日期生成
        String dateFileName = sdf.format(Calendar.getInstance().getTime())
                              + (100 + new Random().nextInt(900));
        // 保存文件信息
        Work work = workManager.get(applyId);
        String filePath = FileUpDownUtil.copyFile(file.getInputStream(), dateFileName,
            UploadFileType.WORK.getPath(), applyId);
        WorkCommFile commFile = new WorkCommFile();
        commFile.setWork(work);
        commFile.setName(fileName);
        commFile.setSize(file.getSize());
        commFile.setUrl(filePath);
        commFile.setDesc(desc);
        commFile.setContentType(file.getContentType());
        commFile.setSuffix(suffix);
        commFile.setType(type.getCode());
        commFile.setExtInfo(extInfo);
        commFile.setCreateTime(Calendar.getInstance().getTime());
        commFile.setCreateUser(CtxUtil.getCurUser());
        commFileDAO.save(commFile);
    }

    /**
     * 保存文件信息
     * 
     * @param
     * @throws Exception
     */
    public void saveWorkCommFile(WorkCommFile commFile) {
        commFileDAO.save(commFile);
    }

    /**
     * 删除文件
     * 
     * @param id
     * @throws Exception
     */
    public void removeFile(String id) {
        WorkCommFile tcommFile = commFileDAO.get(id);
        String url = tcommFile.getUrl();
        // 删除文件
        commFileDAO.remove(tcommFile);
        // 删除物理文件
        File file = new File(url);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 修改附件相关信息
     * 
     * @param id
     * @param name
     * @param desc
     * @param extInfo
     */
    public void updateFileInfo(String id, String name, String desc, String extInfo) {
        WorkCommFile commFile = commFileDAO.get(id);
        commFile.setName(name);
        commFile.setDesc(desc);
        commFile.setExtInfo(extInfo);
        commFileDAO.save(commFile);
    }

    /**
     * 
     * 得到打包文件路径
     * 
     * @param workId
     * @return
     * @throws Exception
     */
    public String getZipFile(String workId) throws Exception {
        Work work = workManager.get(workId);
        /*************** 文件处理 **************/
        // 创建一个名称为“任务名称”_compress的文件夹，将任务附件修改名称并移动到此文件夹中，然后生成压缩文件
        Long time = Calendar.getInstance().getTimeInMillis();
        String compressName = workId + "_" + time;
        String targetPath = FileUtil.getRealPath(UploadFileType.TEMP.getPath(), compressName);
        // 查出全部附件
        List<WorkCommFile> fileList = this.getFileList(workId);
//        String workType = work.getWorkType().getCode();
        String workType = "";
        if ("001".equals(workType)) {// 违法任务
            // 询问笔录
            List<WorkCommFile> list1 = this.getFileList(fileList, DataFileType.XUNWENBILU);
            for (WorkCommFile workCommFile : list1) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.XUNWENBILU.getName(),
                    workCommFile.getName());
            }
            // 勘查笔录
            List<WorkCommFile> list2 = this.getFileList(fileList, DataFileType.KANCHABILU);
            for (WorkCommFile workCommFile : list2) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.KANCHABILU.getName(),
                    workCommFile.getName());
            }
            // 证据信息
            List<WorkCommFile> list3 = this.getFileList(fileList, DataFileType.ZHENGJUXINXI);
            for (WorkCommFile workCommFile : list3) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.ZHENGJUXINXI.getName(),
                    workCommFile.getName());
            }
            // 现场勘查示意图
            List<WorkCommFile> list4 = this.getFileList(fileList, DataFileType.XIANCHANGKANCHATU);
            for (WorkCommFile workCommFile : list4) {
                FileUtil.copyFile(workCommFile.getUrl(),
                    targetPath + File.separator + DataFileType.XIANCHANGKANCHATU.getName(),
                    workCommFile.getName());
            }
            // 图片资料
            List<WorkCommFile> list5 = this.getFileList(fileList, DataFileType.TUPIAN);
            for (WorkCommFile workCommFile : list5) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.TUPIAN.getName(),
                    workCommFile.getName());
            }
            // 音频资料
            List<WorkCommFile> list6 = this.getFileList(fileList, DataFileType.YINPIN);
            for (WorkCommFile workCommFile : list6) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.YINPIN.getName(),
                    workCommFile.getName());
            }
            // 视频资料
            List<WorkCommFile> list7 = this.getFileList(fileList, DataFileType.SHIPIN);
            for (WorkCommFile workCommFile : list7) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.SHIPIN.getName(),
                    workCommFile.getName());
            }
            // 其他资料
            List<WorkCommFile> list8 = this.getFileList(fileList, DataFileType.QITA);
            for (WorkCommFile workCommFile : list8) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.QITA.getName(),
                    workCommFile.getName());
            }
        } else if ("002".equals(workType)) {// 日常任务
            List<WorkCommFile> list1 = this.getFileList(fileList,
                DataFileType.JIANCHAJILUSAOMIAOJIAN);// 检查记录扫描件
            for (WorkCommFile workCommFile : list1) {
                FileUtil.copyFile(workCommFile.getUrl(),
                    targetPath + File.separator + DataFileType.JIANCHAJILUSAOMIAOJIAN.getName(),
                    workCommFile.getName());
            }
            List<WorkCommFile> list2 = this.getFileList(fileList, DataFileType.XIANCHANGKANCHATU);// 现场勘查图
            for (WorkCommFile workCommFile : list2) {
                FileUtil.copyFile(workCommFile.getUrl(),
                    targetPath + File.separator + DataFileType.XIANCHANGKANCHATU.getName(),
                    workCommFile.getName());
            }
            List<WorkCommFile> list3 = this.getFileList(fileList, DataFileType.DIANZIFUJIAN);// 电子附件
            for (WorkCommFile workCommFile : list3) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.DIANZIFUJIAN.getName(),
                    workCommFile.getName());
            }

        } else {// 通用任务
            for (WorkCommFile po : fileList) {
                FileUtil.copyFile(po.getUrl(), targetPath, po.getName());
            }
        }

        // 检查单
        WorkJcinfo jcInfo = (WorkJcinfo) jcInfoManager.get(workId);
        if (jcInfo != null && StringUtils.isNotBlank(jcInfo.getUrl())) {
            FileUtil.copyFile(jcInfo.getUrl(), targetPath,
                jcInfo.getName().concat(".").concat(jcInfo.getSuffix()));
        }
        /*************** 文件处理 **************/
        String zipPath = "";
        // 判断目标文件夹下是否存在文件
        File targetFile = new File(targetPath);
        if (targetFile.exists() && targetFile.isDirectory()) {// 存在且是文件夹
            if (targetFile.list().length > 0) {
                // 最终生成的压缩文件
                zipPath = FileUtil.getRealPath(UploadFileType.TEMP.getPath(), work.getName() + "_" + time + ".zip");
                // 附件主目录下，以任务名称为压缩目录
                FileZipUtil fzu = new FileZipUtil();
                fzu.zipFolder(targetPath, zipPath);
            }
        }
        return zipPath;
    }

    /**
     * 单机版对接 解析压缩文件
     * 
     * @param workId
     *            任务id
     * @param filePath
     *            文件路径
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void executeParseZipFileInfo(String workId, String filePath) throws Exception {
        File unzip = new File(filePath);
        if (unzip.exists() && unzip.isDirectory()) {
            File[] fileArr = unzip.listFiles();// 文件夹内文件列表
            for (int i = 0; i < fileArr.length; i++) {
                if (fileArr[i].getName().equals("workInfo.xml")) {// 找到描述文件
                    SAXReader reader = new SAXReader();
                    Document doc = reader.read(fileArr[i]);
                    Element root = doc.getRootElement();
                    // 得到基本信息先不导入
                    //                    String wflxId = root.selectSingleNode("wflxbh").getText();// 违法类型
                    //                    String wflxName = root.selectSingleNode("wflxnr").getText();
                    //                    if (null != wflxId) {
                    //                        workManager.saveWflx(workId, wflxId, wflxName);
                    //                    }
                    //                    if (root.selectSingleNode("jlrid") != null) {
                    //                        String jlrId = root.selectSingleNode("jlrid").getText();
                    //                        User jlr = this.userManager.getUser(jlrId);
                    //                        if (null != jlr) {
                    //                            workManager.saveJlr(workId, jlrId);
                    //                        }
                    //                    }
                    // 得到附件信息
                    List<Node> xwblList = root.selectNodes("xwbl");// 询问笔录
                    saveFileInfo(xwblList, DataFileType.XUNWENBILU, filePath, workId);
                    List<Node> kcblList = root.selectNodes("kcbl");// 勘查笔录
                    saveFileInfo(kcblList, DataFileType.KANCHABILU, filePath, workId);
                    List<Node> xckcsytList = root.selectNodes("xckcsyt");// 现场勘查示意图
                    saveFileInfo(xckcsytList, DataFileType.XIANCHANGKANCHATU, filePath, workId);
                    List<Node> qitaList = root.selectNodes("qita");// 其他
                    saveFileInfo(qitaList, DataFileType.QITA, filePath, workId);
                    List<Node> tupianList = root.selectNodes("tupian");// 图片
                    saveFileInfo(tupianList, DataFileType.TUPIAN, filePath, workId);
                    List<Node> yinpinList = root.selectNodes("yinpin");// 音频
                    saveFileInfo(yinpinList, DataFileType.YINPIN, filePath, workId);
                    List<Node> luxiangList = root.selectNodes("luxiang");// 视频
                    saveFileInfo(luxiangList, DataFileType.SHIPIN, filePath, workId);
                    List<Node> lianfjList = root.selectNodes("lianfj");// 立案附件
                    saveFileInfo(lianfjList, DataFileType.QITA, filePath, workId);
                    List<Node> dczjList = root.selectNodes("dczj");// 调查终结报告
                    saveFileInfo(dczjList, DataFileType.QITA, filePath, workId);
                }
            }
        }

    }

    /**
     * 保存解析的文件
     * 
     * @param nodes
     * @param type
     * @param filePath
     * @param workId
     */
    @SuppressWarnings("unchecked")
    public void saveFileInfo(List<Node> nodes, DataFileType type, String filePath, String workId)
                                                                                                  throws IOException {
        if (null != nodes && nodes.size() > 0) {
            String s = File.separator;
            for (Node node : nodes) {
                List<Node> files = node.selectNodes("file");
                for (Node fileNode : files) {
                    String name = fileNode.selectSingleNode("name").getText();
                    String url = fileNode.selectSingleNode("url").getText();
                    String contenttype = fileNode.selectSingleNode("contenttype").getText();
                    String fullName = filePath.concat(s).concat(url).concat(s).concat(name);
                    File file = new File(fullName);
                    InputStream in = new FileInputStream(file);
                    String dateFileName = sdf.format(Calendar.getInstance().getTime())
                                          + (100 + new Random().nextInt(900));
                    Work work = workManager.get(workId);
                    String uploadfilePath = FileUpDownUtil.copyFile(in, dateFileName,
                        UploadFileType.WORK.getPath(), workId);
                    WorkCommFile commFile = new WorkCommFile();
                    commFile.setWork(work);
                    commFile.setName(name);
                    commFile.setSize(file.length());
                    commFile.setUrl(uploadfilePath);
                    commFile.setDesc("");
                    commFile.setContentType(contenttype);
                    commFile.setSuffix("");
                    commFile.setType(type.getCode());
                    commFile.setExtInfo(new JSONObject().toString());
                    commFile.setCreateTime(Calendar.getInstance().getTime());
                    commFile.setCreateUser(CtxUtil.getCurUser());
                    commFileDAO.save(commFile);
                }
            }
        }
    }

}
