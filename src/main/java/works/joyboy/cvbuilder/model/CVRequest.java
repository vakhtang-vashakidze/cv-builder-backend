package works.joyboy.cvbuilder.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CVRequest(@NotBlank String firstname,
                        @NotBlank String lastname,
                        @NotBlank String title,
                        @NotBlank String summary,
                        @NotBlank @Email String email,
                        @Valid @NotNull PhoneNumber phoneNumber,
                        @Valid @NotNull Location location,
                        @NotEmpty List<@NotBlank String> skills,
                        @NotEmpty List<@Valid Experience> experiences) {
}