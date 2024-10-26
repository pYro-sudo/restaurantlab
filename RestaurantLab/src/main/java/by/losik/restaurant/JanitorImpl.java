package by.losik.restaurant;

public class JanitorImpl extends Staff implements Janitor {
    public JanitorImpl(double salary, Position position, String name) {
        super(salary, position, name);
    }

    @Override
    public String cleanRooms() {
        return "All rooms are clean";
    }
}
