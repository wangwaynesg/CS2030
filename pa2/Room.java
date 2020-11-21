import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Optional;

public class Room {
    private final String roomName;
    private final List<Thing> things;
    private final Optional<Room> previousRoom;

    public Room(String roomName) {
        this.roomName = roomName;
        this.things = new ArrayList<>();
        this.previousRoom = Optional.empty();
    }

    public Room(String roomName, List<Thing> things) {
        this.roomName = roomName;
        this.things = things;
        this.previousRoom = Optional.empty();
    }

    public Room(String roomName, List<Thing> things, Optional<Room> previousRoom) {
        this.roomName = roomName;
        this.things = things;
        this.previousRoom = previousRoom;
    }

    public Room(String roomName, Room room) {
        this.roomName = roomName;
        this.things = new ArrayList<>(room.things);
        this.previousRoom = Optional.empty();
    }

    public Room add(Thing thing) {
        List<Thing> temp = new ArrayList<>(things);
        temp.add(thing);
        return new Room(this.roomName, temp, this.previousRoom);
    }

    public Room tick() {
        List<Thing> temp = things.stream()
            .map(Thing::nextState)
            .collect(Collectors.toList());
        return new Room(this.roomName, temp, this.previousRoom);
    }

    public Room tick(Function<List<Thing>, List<Thing>> action) {
        List<Thing> temp = action.apply(this.things)
            .stream()
            .map(Thing::nextState)
            .collect(Collectors.toList());
        return new Room(this.roomName, temp, this.previousRoom);
    }

    public Room go(Function<Room, Room> function) {
        Room nextRoom = function.apply(this);
        Room thisRoom = new Room(this.roomName, new ArrayList<>(things), this.previousRoom);

        List<Thing> thingsInRoom = getThingsToBring();
        for (Thing thing : thingsInRoom) {
            thisRoom.things.remove(thing);
        }
        for (Thing thing : nextRoom.things) {
            thingsInRoom.add(thing);
        }
        return new Room(nextRoom.roomName, thingsInRoom, Optional.of(thisRoom));
    }

    public Room back() {
        if (this.previousRoom.isEmpty()) {
            return this;
        }
        Room tempPreviousRoom = this.previousRoom.get().tick();
        List<Thing> thingsToBring = new ArrayList<>(tempPreviousRoom.things);
        for (Thing thing : getThingsToBring()) {
            thingsToBring.add(thing);
        }
        return new Room(tempPreviousRoom.roomName, thingsToBring, tempPreviousRoom.previousRoom);
    }

    public List<Thing> getThingsToBring() {
        List<Thing> thingsInRoom = new ArrayList<>();
        for (Thing thing : this.things) {
            if (thing instanceof Sword) {
                Sword sword = (Sword) thing;
                if (sword.getTaken()) {
                    thingsInRoom.add(sword);
                }
            }
        }
        return thingsInRoom;
    }

    @Override
    public String toString() {
        if (things.size() == 0) return "@" + this.roomName;
        String result = "@" + this.roomName + "\n";
        for (int i = 0; i < things.size() - 1; i++) {
            result += things.get(i).toString() + "\n";
        }
        result += things.get(things.size() - 1).toString();
        return result;
    }
}
