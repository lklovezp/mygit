package com.hnjz.app.work.unzip;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataFile;
import com.hnjz.common.AppException;
import com.hnjz.common.util.FileZipUtil;

public interface UnzipManager {
	/**
	 * 
	 * �������ܣ��ϴ��ļ�
	
	 * ���������multipartFile �ϴ����ļ�
	 * request http����
	
	 * ����ֵ��
	 * @throws AppException 
	 */
	public TDataFile uploadFile(MultipartFile multipartFile, HttpServletRequest request)throws AppException;
	
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
	public TDataFile saveFile(InputStream is, String pid, String fileenumtype,
			String path, String filename, Long size);
	/**
	 * ��ѹ·�� zipPath �µ������ļ��� outputPath
	 * 
	 * @param td
	 *            TDataFile
	 * @param outUnZipPath
	 *            ��ѹ������·��
	 * @return TDataFile ���ؽ�ѹ������
	 * 
	 */


	public TDataFile unZipAndGetData(TDataFile td, String outUnZipPath,
			MultipartFile multipartFile) throws AppException;
	public String findCompany(String fileName)throws AppException;

}