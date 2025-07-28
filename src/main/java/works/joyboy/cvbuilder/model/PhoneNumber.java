package works.joyboy.cvbuilder.model;

import jakarta.validation.constraints.NotBlank;

public record PhoneNumber(@NotBlank String prefix,
                          @NotBlank String number) {
    public String full() {
        return prefix + " " + number;
    }
}
