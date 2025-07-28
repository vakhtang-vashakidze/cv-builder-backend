package works.joyboy.cvbuilder.model;

public record Location(String country, String city) {
    public String full() {
        return country + ", " + city;
    }
}
