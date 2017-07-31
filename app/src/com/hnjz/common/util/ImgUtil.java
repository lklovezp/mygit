package com.hnjz.common.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgUtil {
	/**
	 * ����ͼ�ļ���׺
	 */
	public static String DEFAULT_BACKFIX = "_t";
	/**
	 * �Ƿ�ǿ�ư��տ�����������ͼ(���Ϊfalse����������ѱ�������ͼ)
	 */
	public static Boolean DEFAULT_FORCE = false;

	/**
	 * Description: ����ͼƬ·����������ͼ
	 * 
	 * @param imagePath
	 *            ԭͼƬ·��
	 * @param w
	 *            ����ͼ��
	 * @param h
	 *            ����ͼ��
	 * @param prevfix
	 *            ��������ͼ��ǰ׺
	 * @param force
	 *            �Ƿ�ǿ�ư��տ�����������ͼ(���Ϊfalse����������ѱ�������ͼ)
	 * @throws IOException 
	 */
	public static void thumbnailImage(File imgFile, int w, int h,
			String prevfix, boolean force) throws IOException {
		Image img = ImageIO.read(imgFile);
		if (!force) {
			// ����ԭͼ��Ҫ�������ͼ�������ҵ�����ʵ�����ͼ����
			int width = img.getWidth(null);
			int height = img.getHeight(null);
			if ((width * 1.0) / w < (height * 1.0) / h) {
				if (width > w) {
					h = Integer.parseInt(new java.text.DecimalFormat(
							"0").format(height * w / (width * 1.0)));
				}
			} else {
				if (height > h) {
					w = Integer.parseInt(new java.text.DecimalFormat(
							"0").format(width * h / (height * 1.0)));
				}
			}
		}
		BufferedImage bi = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
		g.dispose();
		// ��ͼƬ������ԭĿ¼������ǰ׺
		try {
			ImageIO.write(bi, "jpg", new File(imgFile.getPath() + prevfix));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}