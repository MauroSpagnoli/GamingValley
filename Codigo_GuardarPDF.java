/*
 * 
 * 
 * 
 */
package pdfcreator;

/**
 *
 * @author mauro
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
public class PDFCreator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
      FileOutputStream archivo = new FileOutputStream("C:\\Users\\mauro\\Desktop\\Nueva carpeta\\Hola.pdf");
      Document documento = new Document();
      PdfWriter.getInstance(documento, archivo);
      documento.open();
      documento.add(new Paragraph("Hola Mundo!"));
      documento.add(new Paragraph("PDF creado a modo de prueba"));
      documento.close();
    }
    
}
