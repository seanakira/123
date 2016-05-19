package com.cts.localtour.pdf;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.parser.Line;

public class PdfCreatorImplement implements PdfCreator {
//	private String yaheiFontFile = "C:\\Windows\\Fonts\\msyh.ttc";
	public static final String dest = "C:/Pdf/test.pdf";
    public static final String logo = "C:/Users/Yaguang/git/localtour/src/main/webapp/resources/assets/images/pdf/logo.jpg";
    public static final String bar = "C:/Users/Yaguang/git/localtour/src/main/webapp/resources/assets/images/pdf/bar.png";
    public static final String h1 = "港中旅国际(山东)旅行社有限公司\n";
    public static final String h2 = "China Travel Service (Shandong) Co., Ltd\n";
    public static final String h3 = "-香港中旅控股企业-\n";
    public static final String h4 = "服务热线：40066-40077  官方网站：www.ctssd.com";

	@Override @Test
	public void createPdf() throws IOException, DocumentException {
		// TODO Auto-generated method stub
		//第一步 新建一个文档对象 并且创建输出地址
		Document document = new Document();
		document.setPageSize(PageSize.A4);
		document.setMargins(25f, 25f, 25, 25);
		
		FileOutputStream fileOutputStream = new FileOutputStream(dest);
		try {
			//第二步 创建writer
			 PdfWriter writer = PdfWriter.getInstance(document,fileOutputStream);
	        //第三步 打开文档对象
	        document.open();
	        //第四步 在pdf头部设置logo以及公司内容
	        PdfPTable tableHeader = new PdfPTable(3);
	        tableHeader.setWidthPercentage(100);
	        tableHeader.setWidths(new int[] {2,4,1});
	        
	        ArrayList<String> header = new ArrayList<String>();
	        header.add(h1);
	        header.add(h2);
	        header.add(h3);
	        header.add(h4);
	        
	        tableHeader.addCell(createImageCell(logo));
	        tableHeader.addCell(createHeaderCell(header));
	        tableHeader.addCell(createImageCell(bar));
	        
	        //第五部 在头部下方设置分割线以及小标题
	        
	        
	        // 设置pdf属性
	        // 设置作者  
	        document.addAuthor("港中旅国际(山东)旅行社有限公司");
	        // 设置创建者  
	        document.addCreator("港中旅国际(山东)旅行社有限公司");
	        // 设置主题  
	        document.addSubject("团队计划");
	        // 设置标题  
	        document.addTitle("团队计划回执单");
	        
	        // 设置pdf内内容
	        document.add(tableHeader);
	        document.add(this.createTitle());
//	        document.add(this.createSeparator());
//	        document.add(this.createTitle());
	        document.close();
	        writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	  public PdfPCell createImageCell(String path) throws DocumentException, IOException {
	        Image img = Image.getInstance(path);
	        PdfPCell cell = new PdfPCell(img, true);
	        cell.setBorder(Rectangle.BOTTOM);
	        return cell;
	  }
	  
	  public PdfPCell createTextCell(ArrayList<String> text) throws DocumentException, IOException {
		  	FontFactoryImp ffi = new FontFactoryImp(); 
	        ffi.registerDirectories();  
	        Font fontWRYH = ffi.getFont("微软雅黑",BaseFont.IDENTITY_H,BaseFont.EMBEDDED, 10, Font.UNDEFINED, BaseColor.DARK_GRAY);
	        Font fontST = ffi.getFont("宋体",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,10,Font.UNDEFINED,BaseColor.DARK_GRAY);
	        Font fontHWLS = ffi.getFont("华文隶书",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,14,Font.UNDEFINED,BaseColor.DARK_GRAY);
	        Font fontTNR = ffi.getFont("Times New Roman",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,10,Font.UNDEFINED,BaseColor.DARK_GRAY);
		  	PdfPCell cell = new PdfPCell();
		  	Paragraph p = new Paragraph(text.get(0),fontHWLS);
		  	p.setAlignment(Element.ALIGN_CENTER);
	        cell.addElement(p);
	        cell.setHorizontalAlignment(Element.ALIGN_BOTTOM);
	        cell.setVerticalAlignment(Element.ALIGN_CENTER);
//	        cell.setBorder(Rectangle.NO_BORDER);
		  	return cell;
	  }
	  
	  public PdfPCell createHeaderCell(ArrayList<String> text) throws DocumentException, IOException {
		  	FontFactoryImp ffi = new FontFactoryImp(); 
	        ffi.registerDirectories();  
	        Font fontST = ffi.getFont("宋体",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,9,Font.UNDEFINED,BaseColor.BLACK);
	        Font fontHWLS = ffi.getFont("华文隶书",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,15,Font.UNDEFINED,BaseColor.BLACK);
		  	PdfPCell cell = new PdfPCell();
		  	
		  	Paragraph h1 = new Paragraph(text.get(0),fontHWLS);
		  	Paragraph h2 = new Paragraph(text.get(1),fontST);
		  	Paragraph h3 = new Paragraph(text.get(2),fontHWLS);
		  	Paragraph h4 = new Paragraph(text.get(3),fontST);
		  	
		  	h1.setAlignment(Element.ALIGN_CENTER);
		  	h2.setAlignment(Element.ALIGN_CENTER);
		  	h3.setAlignment(Element.ALIGN_CENTER);
		  	h4.setAlignment(Element.ALIGN_CENTER);

		  	cell.addElement(h1);
		  	cell.addElement(h2);
		  	cell.addElement(h3);
		  	cell.addElement(h4);

	        cell.setHorizontalAlignment(Element.ALIGN_BOTTOM);
	        cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.BOTTOM);
		  	return cell;
	  }
	  
//	  public Paragraph createSeparator(){
//		  LineSeparator line = new LineSeparator(1, 100, BaseColor.DARK_GRAY, Element.ALIGN_TOP, -2);
//		  Paragraph pLine = new Paragraph();
//		  pLine.setAlignment(Element.ALIGN_TOP);
//		  pLine.add(line);
//		  return pLine;
//	  }
	  
	  public Paragraph createTitle(){
		  FontFactoryImp ffi = new FontFactoryImp(); 
	      ffi.registerDirectories();  
		  Font fontWRYH = ffi.getFont("微软雅黑",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,12,Font.UNDEFINED,BaseColor.BLACK);
		  Paragraph pTitle = new Paragraph("团队计划回执单",fontWRYH);
		  pTitle.setAlignment(Paragraph.ALIGN_CENTER);
		  return pTitle;
	  }
}
