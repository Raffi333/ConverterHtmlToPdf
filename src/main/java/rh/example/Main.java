package rh.example;

public class Main {

    public static void main(String[] args) throws Exception {
        String htmlFilePath = "src/resource/html/htmlPage.html";
        String pdfFilePath = "src/resource/pdf/";

        Converter.convert(htmlFilePath, pdfFilePath,"test1.pdf");
//        Converter.convert();

    }

}