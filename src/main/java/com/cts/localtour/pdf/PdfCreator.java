/**
 * 
 */
package com.cts.localtour.pdf;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

/**
 * @author Yaguang
 *
 */
public interface PdfCreator {
	public void createPdf() throws IOException, DocumentException;
}
