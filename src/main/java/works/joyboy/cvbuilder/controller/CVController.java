package works.joyboy.cvbuilder.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import works.joyboy.cvbuilder.model.CVRequest;
import works.joyboy.cvbuilder.service.DocumentGenerator;

@Validated
@RestController
@RequestMapping("/cv")
@CrossOrigin(value = "*")
public class CVController {

    private final DocumentGenerator pdfGenerator;
    private final DocumentGenerator wordGenerator;

    public CVController(@Qualifier("pdfGenerator") DocumentGenerator pdfGenerator,
                        @Qualifier("wordGenerator") DocumentGenerator wordGenerator) {
        this.pdfGenerator = pdfGenerator;
        this.wordGenerator = wordGenerator;
    }

    @PostMapping("/download/pdf")
    public ResponseEntity<byte[]> downloadPdf(@Valid @RequestBody CVRequest request) {
        byte[] file = pdfGenerator.generate(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cv.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }

    @PostMapping("/download/word")
    public ResponseEntity<byte[]> downloadWord(@Valid @RequestBody CVRequest request) {
        byte[] file = wordGenerator.generate(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cv.docx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(file);
    }
}
