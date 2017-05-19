package com.cts.localtour.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
@Component
public class PdfMaker {
	public boolean make(String tourNo, String html){
		try {
			/*File file = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".pdf");*/
			/*File file = new File("D://STSworkSpaces/localtour/src/main/webapp/resources/pdfTemp/"+tourNo+"reimbursement.pdf");*/
			File file = new File("/driver/apache-tomcat-8.0.39/webapps/localtour/resources/pdfTemp/"+tourNo+"reimbursement.pdf");
			file.createNewFile();
			Document document = new Document(PageSize.A4,20,20,25,20);  
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			/*BaseFont baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			Font font = new Font(baseFont); */
			/*String html = "<div style='color:green;font-size:20px;'>你好世界！hello world !</div>";*/
			Paragraph context = new Paragraph();
			html = URLDecoder.decode(html, "UTF-8");
			ElementList elementList =MyXMLWorkerHelper.parseToElementList(html, null);
			for (Element element : elementList) {
				context.add(element);
			}
			document.add(context);
			/*document.add(new Paragraph("中文测试。。 Itext! ",html));*/
			document.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
