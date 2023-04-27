package rh.example;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class Converter {

    public static boolean convert(String htmlFilePath, String pdfDirPath) {
        File html = new File(htmlFilePath);
        File pdf = new File(pdfDirPath + "convertedPdf" + System.currentTimeMillis() + ".pdf");
        try (FileOutputStream outputStream = new FileOutputStream(pdf)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(html);
            renderer.getFontResolver().addFont("src/resource/font/arnamu.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.getFontResolver().addFont("src/resource/font/arnamub.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.getFontResolver().addFont("src/resource/font/arnamubi.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.getFontResolver().addFont("src/resource/font/arnamui.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);


            renderer.layout();
            renderer.createPDF(outputStream);

        } catch (IOException | DocumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean convert(String htmlFilePath, String pdfDirPath, String pdfFileName) throws IOException {
        File html = new File(htmlFilePath);
        File pdf = new File(pdfDirPath + pdfFileName);
        Document doc = Jsoup.parse(html, "UTF-8");
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        try (FileOutputStream outputStream = new FileOutputStream(pdf)) {

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(doc.html());

            renderer.getFontResolver().addFont("src/resource/font/arnamu.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.getFontResolver().addFont("src/resource/font/arnamub.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.getFontResolver().addFont("src/resource/font/arnamubi.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.getFontResolver().addFont("src/resource/font/arnamui.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);


            renderer.layout();
            renderer.createPDF(outputStream);

        } catch (IOException | DocumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static void convert() throws Exception {
        File htmlFile = new File("/home/raffi/IdeaProjects/ConverterHtmlToPdf/src/resource/html/test1.html");
        final Document doc = Jsoup.parse(htmlFile, "UTF-8");
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        InputConverter.inputsConverter(doc);
        try (OutputStream os = new FileOutputStream("/home/raffi/IdeaProjects/ConverterHtmlToPdf/src/resource/pdf/convertedPdf.pdf")) {

            ITextRenderer renderer = new ITextRenderer();

            renderer.getFontResolver().addFont("src/resource/font/arnamu.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.getFontResolver().addFont("src/resource/font/arnamub.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.getFontResolver().addFont("src/resource/font/arnamubi.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.getFontResolver().addFont("src/resource/font/arnamui.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.setDocumentFromString(doc.html());
            renderer.layout();
            renderer.createPDF(os);

        }
    }

}
