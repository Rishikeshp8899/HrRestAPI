package com.mindgate.main.service;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.font.FontProvider;

import jakarta.mail.Header;
@Service
public class PdfGenerator 
{
    public  byte[] generatedPDF(String content) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter write=new PdfWriter(byteArrayOutputStream);
        
        PdfDocument pdfDocument=new PdfDocument(write);
        Document document=new Document(pdfDocument);
        FontProvider fontProvider = new FontProvider();
        fontProvider.addStandardPdfFonts();
        document.setFontProvider(fontProvider);
        Paragraph paragraph=new Paragraph(content);
        document.add(paragraph);
        Header header=new Header("header", "Offer Letter");
        document.add(paragraph);
        document.close();
        return byteArrayOutputStream.toByteArray();
    }
}
