package works.joyboy.cvbuilder.service.impl;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;
import works.joyboy.cvbuilder.model.CVRequest;
import works.joyboy.cvbuilder.service.DocumentGenerator;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGenerator implements DocumentGenerator {

    private final TemplateEngine templateEngine;

    public PdfGenerator(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public byte[] generate(CVRequest request) {
        String html = loadTemplateHtml(templateEngine, "cv-template", request);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }
    }
}
