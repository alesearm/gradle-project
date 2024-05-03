/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package test;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.PseudoText;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import com.google.gson.Gson;
import com.indvd00m.ascii.render.Render;

public class App {
    public String getGreeting() {
        return "Hello World! My name is Armin";
    }
    

    public static void main(String[] args) throws IOException {
        System.out.println(new App().getGreeting());

    //ASCII Render
        IRender render = new Render();
        IContextBuilder builder = render.newBuilder();
        builder.width(120).height(20);
        builder.element(new PseudoText("DevOps-Armin"));
        ICanvas canvas = render.render(builder.build());
        String s = canvas.getText();
        System.out.println(s);


    //PDFBox
        PDDocument helloPdf = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        helloPdf.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(helloPdf, page);
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 36);
        contentStream.newLineAtOffset(10, 100);
        contentStream.showText("Hello PDF World. My Name is Armin!!!");
        contentStream.endText();
        contentStream.close();

        helloPdf.save(new File("C:\\Users\\armin\\DevOps\\PDFBox\\simple.pdf"));
        helloPdf.close();


        User user = new User("Armin Alesevic", 30);

        Gson gson = new Gson();

        // Java-Objekt zu JSON konvertieren
        String json = gson.toJson(user);
        System.out.println("JSON representation: " + json);

        // JSON zu Java-Objekt konvertieren
        User newUser = gson.fromJson(json, User.class);
        System.out.println("User: " + newUser.getName() + ", Age: " + newUser.getAge());

    }
}
