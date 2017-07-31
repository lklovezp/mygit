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
 * ��������
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
     * ��ȡ����
     * 
     * @param fileId
     * @return
     */
    public WorkCommFile get(String fileId) {
        return commFileDAO.get(fileId);
    }

    /**
     * �Ӹ��������еõ�ָ�����͵ĸ���
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
     * ��ȡ����ĸ�������
     * 
     * @param workId ����Id
     * @param type ��������
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
     * ��ȡ�����ȫ����������
     * 
     * @param workId ����Id
     * @return �����ȫ����������
     */
    public List<WorkCommFile> getFileList(String workId) {
        String hsql = "from WorkCommFile t where t.work.id = ? ";
        return commFileDAO.find(hsql, workId);
    }
    /**
     * ��ȡ�����ȫ����������
     * 
     * @param workId ����Id
     * @return �����ȫ����������
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
     * �ϴ��ļ�
     * 
     * @param applyId
     * @param file
     * @param name
     * @param desc
     * @param url
     * @param type
     *            ��������
     * @throws Exception
     */
    public void saveFile(String applyId, MultipartFile file, String name, String desc,
                         DataFileType type, String extInfo) throws Exception {
        String fileName = file.getOriginalFilename();
        // ���Զ����ļ���ʱ
        String suffix = "";
        if (StringUtils.isNotBlank(name)) {
            suffix = fileName.substring(fileName.lastIndexOf("."));
            fileName = name + suffix;
        }
        // ���ļ��ϴ���������
        // �ϴ������������ļ���ʹ����������
        String dateFileName = sdf.format(Calendar.getInstance().getTime())
                              + (100 + new Random().nextInt(900));
        // �����ļ���Ϣ
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
     * �����ļ���Ϣ
     * 
     * @param
     * @throws Exception
     */
    public void saveWorkCommFile(WorkCommFile commFile) {
        commFileDAO.save(commFile);
    }

    /**
     * ɾ���ļ�
     * 
     * @param id
     * @throws Exception
     */
    public void removeFile(String id) {
        WorkCommFile tcommFile = commFileDAO.get(id);
        String url = tcommFile.getUrl();
        // ɾ���ļ�
        commFileDAO.remove(tcommFile);
        // ɾ�������ļ�
        File file = new File(url);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * �޸ĸ��������Ϣ
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
     * �õ�����ļ�·��
     * 
     * @param workId
     * @return
     * @throws Exception
     */
    public String getZipFile(String workId) throws Exception {
        Work work = workManager.get(workId);
        /*************** �ļ����� **************/
        // ����һ������Ϊ���������ơ�_compress���ļ��У������񸽼��޸����Ʋ��ƶ������ļ����У�Ȼ������ѹ���ļ�
        Long time = Calendar.getInstance().getTimeInMillis();
        String compressName = workId + "_" + time;
        String targetPath = FileUtil.getRealPath(UploadFileType.TEMP.getPath(), compressName);
        // ���ȫ������
        List<WorkCommFile> fileList = this.getFileList(workId);
//        String workType = work.getWorkType().getCode();
        String workType = "";
        if ("001".equals(workType)) {// Υ������
            // ѯ�ʱ�¼
            List<WorkCommFile> list1 = this.getFileList(fileList, DataFileType.XUNWENBILU);
            for (WorkCommFile workCommFile : list1) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.XUNWENBILU.getName(),
                    workCommFile.getName());
            }
            // �����¼
            List<WorkCommFile> list2 = this.getFileList(fileList, DataFileType.KANCHABILU);
            for (WorkCommFile workCommFile : list2) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.KANCHABILU.getName(),
                    workCommFile.getName());
            }
            // ֤����Ϣ
            List<WorkCommFile> list3 = this.getFileList(fileList, DataFileType.ZHENGJUXINXI);
            for (WorkCommFile workCommFile : list3) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.ZHENGJUXINXI.getName(),
                    workCommFile.getName());
            }
            // �ֳ�����ʾ��ͼ
            List<WorkCommFile> list4 = this.getFileList(fileList, DataFileType.XIANCHANGKANCHATU);
            for (WorkCommFile workCommFile : list4) {
                FileUtil.copyFile(workCommFile.getUrl(),
                    targetPath + File.separator + DataFileType.XIANCHANGKANCHATU.getName(),
                    workCommFile.getName());
            }
            // ͼƬ����
            List<WorkCommFile> list5 = this.getFileList(fileList, DataFileType.TUPIAN);
            for (WorkCommFile workCommFile : list5) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.TUPIAN.getName(),
                    workCommFile.getName());
            }
            // ��Ƶ����
            List<WorkCommFile> list6 = this.getFileList(fileList, DataFileType.YINPIN);
            for (WorkCommFile workCommFile : list6) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.YINPIN.getName(),
                    workCommFile.getName());
            }
            // ��Ƶ����
            List<WorkCommFile> list7 = this.getFileList(fileList, DataFileType.SHIPIN);
            for (WorkCommFile workCommFile : list7) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.SHIPIN.getName(),
                    workCommFile.getName());
            }
            // ��������
            List<WorkCommFile> list8 = this.getFileList(fileList, DataFileType.QITA);
            for (WorkCommFile workCommFile : list8) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.QITA.getName(),
                    workCommFile.getName());
            }
        } else if ("002".equals(workType)) {// �ճ�����
            List<WorkCommFile> list1 = this.getFileList(fileList,
                DataFileType.JIANCHAJILUSAOMIAOJIAN);// ����¼ɨ���
            for (WorkCommFile workCommFile : list1) {
                FileUtil.copyFile(workCommFile.getUrl(),
                    targetPath + File.separator + DataFileType.JIANCHAJILUSAOMIAOJIAN.getName(),
                    workCommFile.getName());
            }
            List<WorkCommFile> list2 = this.getFileList(fileList, DataFileType.XIANCHANGKANCHATU);// �ֳ�����ͼ
            for (WorkCommFile workCommFile : list2) {
                FileUtil.copyFile(workCommFile.getUrl(),
                    targetPath + File.separator + DataFileType.XIANCHANGKANCHATU.getName(),
                    workCommFile.getName());
            }
            List<WorkCommFile> list3 = this.getFileList(fileList, DataFileType.DIANZIFUJIAN);// ���Ӹ���
            for (WorkCommFile workCommFile : list3) {
                FileUtil.copyFile(workCommFile.getUrl(), targetPath + File.separator
                                                         + DataFileType.DIANZIFUJIAN.getName(),
                    workCommFile.getName());
            }

        } else {// ͨ������
            for (WorkCommFile po : fileList) {
                FileUtil.copyFile(po.getUrl(), targetPath, po.getName());
            }
        }

        // ��鵥
        WorkJcinfo jcInfo = (WorkJcinfo) jcInfoManager.get(workId);
        if (jcInfo != null && StringUtils.isNotBlank(jcInfo.getUrl())) {
            FileUtil.copyFile(jcInfo.getUrl(), targetPath,
                jcInfo.getName().concat(".").concat(jcInfo.getSuffix()));
        }
        /*************** �ļ����� **************/
        String zipPath = "";
        // �ж�Ŀ���ļ������Ƿ�����ļ�
        File targetFile = new File(targetPath);
        if (targetFile.exists() && targetFile.isDirectory()) {// ���������ļ���
            if (targetFile.list().length > 0) {
                // �������ɵ�ѹ���ļ�
                zipPath = FileUtil.getRealPath(UploadFileType.TEMP.getPath(), work.getName() + "_" + time + ".zip");
                // ������Ŀ¼�£�����������Ϊѹ��Ŀ¼
                FileZipUtil fzu = new FileZipUtil();
                fzu.zipFolder(targetPath, zipPath);
            }
        }
        return zipPath;
    }

    /**
     * ������Խ� ����ѹ���ļ�
     * 
     * @param workId
     *            ����id
     * @param filePath
     *            �ļ�·��
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void executeParseZipFileInfo(String workId, String filePath) throws Exception {
        File unzip = new File(filePath);
        if (unzip.exists() && unzip.isDirectory()) {
            File[] fileArr = unzip.listFiles();// �ļ������ļ��б�
            for (int i = 0; i < fileArr.length; i++) {
                if (fileArr[i].getName().equals("workInfo.xml")) {// �ҵ������ļ�
                    SAXReader reader = new SAXReader();
                    Document doc = reader.read(fileArr[i]);
                    Element root = doc.getRootElement();
                    // �õ�������Ϣ�Ȳ�����
                    //                    String wflxId = root.selectSingleNode("wflxbh").getText();// Υ������
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
                    // �õ�������Ϣ
                    List<Node> xwblList = root.selectNodes("xwbl");// ѯ�ʱ�¼
                    saveFileInfo(xwblList, DataFileType.XUNWENBILU, filePath, workId);
                    List<Node> kcblList = root.selectNodes("kcbl");// �����¼
                    saveFileInfo(kcblList, DataFileType.KANCHABILU, filePath, workId);
                    List<Node> xckcsytList = root.selectNodes("xckcsyt");// �ֳ�����ʾ��ͼ
                    saveFileInfo(xckcsytList, DataFileType.XIANCHANGKANCHATU, filePath, workId);
                    List<Node> qitaList = root.selectNodes("qita");// ����
                    saveFileInfo(qitaList, DataFileType.QITA, filePath, workId);
                    List<Node> tupianList = root.selectNodes("tupian");// ͼƬ
                    saveFileInfo(tupianList, DataFileType.TUPIAN, filePath, workId);
                    List<Node> yinpinList = root.selectNodes("yinpin");// ��Ƶ
                    saveFileInfo(yinpinList, DataFileType.YINPIN, filePath, workId);
                    List<Node> luxiangList = root.selectNodes("luxiang");// ��Ƶ
                    saveFileInfo(luxiangList, DataFileType.SHIPIN, filePath, workId);
                    List<Node> lianfjList = root.selectNodes("lianfj");// ��������
                    saveFileInfo(lianfjList, DataFileType.QITA, filePath, workId);
                    List<Node> dczjList = root.selectNodes("dczj");// �����սᱨ��
                    saveFileInfo(dczjList, DataFileType.QITA, filePath, workId);
                }
            }
        }

    }

    /**
     * ����������ļ�
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