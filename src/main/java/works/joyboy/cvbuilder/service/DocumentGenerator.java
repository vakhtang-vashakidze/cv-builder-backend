package works.joyboy.cvbuilder.service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import works.joyboy.cvbuilder.model.CVRequest;
import works.joyboy.cvbuilder.model.Experience;

import java.util.Comparator;

public interface DocumentGenerator {
    byte[] generate(CVRequest request);

    default String loadTemplateHtml(TemplateEngine templateEngine, String templateName, CVRequest request) {
        Context context = new Context();
        context.setVariable("name", request.firstname() + " " + request.lastname());
        context.setVariable("title", request.title());
        context.setVariable("summary", request.summary());
        context.setVariable("email", request.email());
        context.setVariable("location", request.location().full());
        context.setVariable("phoneNumber", request.phoneNumber().full());
        context.setVariable("skills", request.skills());
        context.setVariable("experiences", request.experiences().stream()
                .sorted(Comparator.comparing(
                        Experience::endDate,
                        Comparator.nullsFirst(Comparator.naturalOrder())
                ))
                .toList());

        return templateEngine.process(templateName, context);
    }
}
