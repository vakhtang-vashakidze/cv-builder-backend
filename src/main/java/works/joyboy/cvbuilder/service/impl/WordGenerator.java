package works.joyboy.cvbuilder.service.impl;

import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import works.joyboy.cvbuilder.model.CVRequest;
import works.joyboy.cvbuilder.service.DocumentGenerator;

import java.io.ByteArrayOutputStream;

@Service
public class WordGenerator implements DocumentGenerator {

    private final TemplateEngine templateEngine;

    public WordGenerator(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public byte[] generate(CVRequest request) {
        try {
            String html = loadTemplateHtml(templateEngine, "cv-template", request);

            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
            XHTMLImporterImpl xhtmlImporter = new XHTMLImporterImpl(wordMLPackage);
            wordMLPackage.getMainDocumentPart().getContent().addAll(
                    xhtmlImporter.convert(html, null));

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            wordMLPackage.save(outputStream);
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Word document", e);
        }
    }
}
