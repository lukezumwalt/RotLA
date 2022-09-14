package Characters;

public interface Entity {
    // Attributes
    String entityType = "";
    int[] position = {};
    // Methods
    void move();
    boolean fight(Entity target);
}
