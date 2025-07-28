package works.joyboy.cvbuilder.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record Experience(@NotBlank String company,
                         @NotBlank String position,
                         @NotNull LocalDate startDate,
                         LocalDate endDate,
                         boolean stillWorking,
                         @NotBlank String description) {
}
