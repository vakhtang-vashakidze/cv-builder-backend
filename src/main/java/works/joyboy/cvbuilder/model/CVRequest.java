package works.joyboy.cvbuilder.model;

import java.util.List;

public record CVRequest(String name,
                        String title,
                        String summary,
                        String email,
                        PhoneNumber phoneNumber,
                        Location location,
                        List<String> skills,
                        List<Experience> experiences) {
}