package works.joyboy.cvbuilder.model;

import jakarta.validation.constraints.NotBlank;

public record Location(@NotBlank String city,
                       @NotBlank String country) {
    public String full() {
        return country + ", " + city;
    }
}
