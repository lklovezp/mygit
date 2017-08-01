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
	 * 缩略图文件后缀
	 */
	public static String DEFAULT_BACKFIX = "_t";
	/**
	 * 是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
	 */
	public static Boolean DEFAULT_FORCE = false;

	/**
	 * Description: 根据图片路径生成缩略图
	 * 
	 * @param imagePath
	 *            原图片路径
	 * @param w
	 *            缩略图宽
	 * @param h
	 *            缩略图高
	 * @param prevfix
	 *            生成缩略图的前缀
	 * @param force
	 *            是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
	 * @throws IOException 
	 */
	public static void thumbnailImage(File imgFile, int w, int h,
			String prevfix, boolean force) throws IOException {
		Image img = ImageIO.read(imgFile);
		if (!force) {
			// 根据原图与要求的缩略图比例，找到最合适的缩略图比例
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
		// 将图片保存在原目录并加上前缀
		try {
			ImageIO.write(bi, "jpg", new File(imgFile.getPath() + prevfix));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}