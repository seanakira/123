package com.cts.localtour.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfCreatorImplement implements PdfCreator {

	@Override
	public void createPdf(String dest) throws IOException, DocumentException {
		// TODO Auto-generated method stub
	    Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
        Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
        Chunk chunk = new Chunk("港中旅国际山东旅行社有限公司", chapterFont);
        Chapter chapter = new Chapter(new Paragraph(chunk), 1);
        chapter.setNumberDepth(0);
        chapter.add(new Paragraph("团队路线测试", paragraphFont));
        document.add(chapter);
        document.close();
	}

	public static void main(String[] args) throws IOException, DocumentException {
		String dest = "C:/Pdf/test.pdf";
        File file = new File(dest);
        file.getParentFile().mkdirs();
        new PdfCreatorImplement().createPdf(dest);
    }
}
