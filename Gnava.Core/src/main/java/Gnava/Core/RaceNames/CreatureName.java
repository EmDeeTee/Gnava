package Gnava.Core.RaceNames;

public record CreatureName(String firstname, String lastname) {
    public String fullName() {
        return firstname + " " + lastname;
    }

    @Override
    public String toString() {
        return fullName();
    }
}
