import java.util.List;
import java.util.ArrayList;

class ImmutableList<T> {
    private final List<T> list;

    @SafeVarargs
    public ImmutableList(T ... t) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T i : t) {
            arrayList.add(i);
        }
        this.list = arrayList;
    }

    public ImmutableList(List<T> list) {
        this.list = new ArrayList<T>(list);
    }

    public List<T> add(T t) {
        return new ImmutableList<T>(list).add(t);
    }

    public List<T> remove(T t) {
        return new ImmutableList<T>(list).remove(t);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
