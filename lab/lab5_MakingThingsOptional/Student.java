import java.util.HashMap;
import java.util.Map;

public class Student extends KeyableMap<String, Module> {
    public Student(String name) {
        super(name);
    }

    public Student(String name, HashMap<String, Module> modules) {
        super(name, modules);
    }

    public Student put(Module module) {
        HashMap<String, Module> temp = new HashMap<>();
        temp = this.item;
        /*
        for (Map.Entry<String, Module> entry : this.item.entrySet()) {
            temp.put(entry.getKey(), entry.getValue());
        }
        */
        temp.put(module.getKey(), module);
        return new Student(this.key, temp);
    }
}
