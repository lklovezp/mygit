package com.hnjz.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

public class PreviewUtil {

	public String doc2Html(InputStream is, final String project) throws TransformerException,
			IOException, ParserConfigurationException {
		HWPFDocument wordDocument = new HWPFDocument(is);
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.newDocument());
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			public String savePicture(byte[] content, PictureType pictureType,
					String suggestedName, float widthInches, float heightInches) {
				return project + "/static/temp/preview/pic/" + suggestedName;
			}
		});
		wordToHtmlConverter.processDocument(wordDocument);
		List pics = wordDocument.getPicturesTable().getAllPictures();
		String path = this.getClass().getClassLoader().getResource("/").getPath();
		path = path.substring(0, path.lastIndexOf("WEB-INF/classes/")) + "static//temp//preview//pic//";
		path = path.replaceAll("%20", " ");
		if (pics != null) {
			for (int i = 0; i < pics.size(); i++) {
				Picture pic = (Picture) pics.get(i);
				try {
					pic.writeImageContent(new FileOutputStream(path + pic.suggestFullFileName()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		Document htmlDocument = wordToHtmlConverter.getDocument();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(out);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "ISO8859-1");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
		out.close();

		return new String(out.toByteArray());
	}
	
	public String docx2Html(InputStream is, String project, String httpPath) {
		String htmlpath = "";
		try {
			XWPFDocument doc = new XWPFDocument(is);
			String root = project + "//static//temp//preview//pic//";
			String fileOutName = root + "preDocx.html";
			XHTMLOptions options = XHTMLOptions.create();// .indent( 4 );
			// Extract image
			File imageFolder = new File(root);
			options.setExtractor(new FileImageExtractor(imageFolder));
			// URI resolver
			OutputStream out = new FileOutputStream(new File(fileOutName));
			XHTMLConverter.getInstance().convert(doc, out, options);
			htmlpath = fileOutName.replace(project, httpPath).replaceAll("//", "/");
		} catch (Exception e){
			e.printStackTrace();
		}
		return htmlpath;
	}
}
