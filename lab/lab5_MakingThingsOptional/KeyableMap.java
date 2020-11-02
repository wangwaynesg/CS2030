import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KeyableMap<K, V> implements Keyable {
    protected String key;
    protected HashMap<K, V> item;

    public KeyableMap(String key, HashMap<K, V> item) {
        this.key = key;
        this.item = item;
    }

    public KeyableMap(String key) {
        this.key = key;
        this.item = new HashMap<K, V>();
    }

    public String getKey() {
        return this.key;
    }
/*
    public V get(String key) {
        return item.get(key);
    }
*/
    public Optional<V> get(String key) {
        return Optional.ofNullable(item.get(key));
    }

	@Override
    public String toString() {
        String output = "";
        for (Map.Entry<K, V> entry : this.item.entrySet()) {
            output += entry.getValue().toString();
            output += ", ";
        }

        if (output.contains(",")) {
            output = output.substring(0, output.lastIndexOf(", "));
        }
        return this.key + ": {" + output + "}";
    }
}
