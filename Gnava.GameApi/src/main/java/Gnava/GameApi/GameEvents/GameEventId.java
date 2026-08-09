package Gnava.GameApi.GameEvents;

public record GameEventId(String namespace, String name) {
    public GameEventId {
        namespace = requirePart(namespace, "namespace");
        name = requirePart(name, "name");
    }

    public static GameEventId parse(String value) {
        String[] parts = value.split(":", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Game event ids must use the form 'namespace:name'");
        }
        return new GameEventId(parts[0], parts[1]);
    }

    private static String requirePart(String value, String partName) {
        if (!value.matches("[a-z0-9_.-]+")) {
            throw new IllegalArgumentException(
                "Game event " + partName + " must contain only lowercase letters, digits, '_', '-' or '.'"
            );
        }
        return value;
    }

    @Override
    public String toString() {
        return namespace + ":" + name;
    }
}
