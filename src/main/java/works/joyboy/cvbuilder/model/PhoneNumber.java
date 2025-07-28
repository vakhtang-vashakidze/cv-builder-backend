package works.joyboy.cvbuilder.model;

public record PhoneNumber(String prefix, String number) {
    public String full() {
        return prefix + " " + number;
    }
}
