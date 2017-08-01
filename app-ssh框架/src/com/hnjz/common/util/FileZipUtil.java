package com.hnjz.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;


/**
 * 压缩文件的工具类
 * 
 * @author wumi
 * @version $Id: FileZipUtil.java, v 0.1 Feb 18, 2013 2:23:36 PM wumi Exp $
 */
@SuppressWarnings("unchecked")
public class FileZipUtil {

    /**
     * 压缩某一文件夹下的文件
     * 
     * @param dirPath 带压缩的文件路径
     * @param filePath 文件压缩后的路径
     */
    public void zipFolder(String dirPath, String filePath) {
        File zipFile = new File(filePath);
        //如果存在，删除
        if (!zipFile.exists()) {
            zipFile.delete();
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            throw new RuntimeException(dirPath + "不存在");
        }
        Project project = new Project();
        Zip zip = new Zip();
        zip.setProject(project);
        zip.setDestFile(zipFile);

        FileSet fileSet = new FileSet();
        fileSet.setProject(project);
        fileSet.setDir(dir);
        zip.addFileset(fileSet);

        zip.execute();
    }

    /**
     * 解压路径 zipPath 下的所有文件到 outputPath
     * 
     * @param zipPath 待压缩文件的路径
     * @param outputPath 压缩后的输出路径
     * @return  解压后的文件的路径
     * @throws IOException
     */
    public String unZip(String zipPath, String outputPath) throws IOException {
        String zipName = zipPath.substring(zipPath.lastIndexOf("\\"));
        ZipFile zipFile = new ZipFile(zipPath);
        Enumeration<ZipEntry> e = zipFile.getEntries();
        ZipEntry zipEntry = null;
        String dirPath = "";
        while (e.hasMoreElements()) {
            zipEntry = (ZipEntry) e.nextElement();
            if (zipEntry.isDirectory()) {
                FileUtil.createDir(outputPath, zipName.substring(0, zipName.lastIndexOf(".")),
                    zipEntry.getName());
            } else {
                if (zipEntry.getName().lastIndexOf("/") != -1) {
                    dirPath = zipEntry.getName().substring(0, zipEntry.getName().lastIndexOf("/"));
                } else if(zipEntry.getName().lastIndexOf("\\") != -1){
                    dirPath = zipEntry.getName().substring(0, zipEntry.getName().lastIndexOf("\\"));
                }
                FileUtil.createDir(outputPath, zipName.substring(0, zipName.lastIndexOf(".")),
                    dirPath);
                File file = new File(outputPath.concat(File.separator).concat(
                    zipName.substring(0, zipName.lastIndexOf("."))).concat(File.separator).concat(
                    zipEntry.getName()));
                file.createNewFile();
                InputStream in = zipFile.getInputStream(zipEntry);
                FileOutputStream out = new FileOutputStream(file);
                int c;
                byte[] by = new byte[1024];
                while ((c = in.read(by)) != -1) {
                    out.write(by, 0, c);
                }
                out.close();
                in.close();
            }
        }
        return outputPath.concat(zipName.substring(0, zipName.lastIndexOf(".")));
    }

}
