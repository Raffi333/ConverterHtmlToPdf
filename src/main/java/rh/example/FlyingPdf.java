package rh.example;

import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FlyingPdf {
    public static void main(String[] args) {
        String inputFile = "/home/raffi/IdeaProjects/ConverterHtmlToPdf/src/resource/html/test1.html";
        String outputFile = "/home/raffi/IdeaProjects/ConverterHtmlToPdf/src/resource/html/test1.pdf";

        generatePDF(inputFile, outputFile);

        System.out.println("Done!");
    }

    private static void generatePDF(String inputHtmlPath, String outputPdfPath) {
        try {
            String url = new File(inputHtmlPath).toURI().toURL().toString();
            System.out.println("URL: " + url);

            OutputStream out = Files.newOutputStream(Paths.get(outputPdfPath));

            //Flying Saucer part
            ITextRenderer renderer = new ITextRenderer();

            renderer.setDocument(url);
            renderer.layout();
            renderer.createPDF(out);

            out.close();
        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}