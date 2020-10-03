import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.function.Function;

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

    public ImmutableList<T> filter(Predicate<? super T> p) {
        ArrayList<T> arrayList = new ArrayList<>(this.list);
        ArrayList<T> temp = new ArrayList<T>();
        for (T t : arrayList) {
            if (p.test(t)) {
                temp.add(t);
            }
        }
        return new ImmutableList<T>(temp);
    }

    public <R> ImmutableList<R> map(Function<? super T, ? extends R> f) {
        ArrayList<T> arrayList = new ArrayList<>(this.list);
        ArrayList<R> temp = new ArrayList<>();
        for (T t : arrayList) {
            temp.add(f.apply(t));
        }
        return new ImmutableList<R>(temp);
    }

    public Object[] toArray() {
        ArrayList<Object> temp = new ArrayList<Object>(this.list);
        return temp.toArray();
    }

    public <T> T[] toArray(T[] a) throws ArrayStoreException, NullPointerException {
        if (a == null) {
            throw new NullPointerException("Input array cannot be null");
        }
        try {
            return this.list.toArray(a);
        } catch (Exception e) {
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
        }
    }

    public List<T> getList() {
        return this.list;
    }

    public ImmutableList<T> add(T t) {
        ArrayList<T> temp = new ArrayList<T>(this.list);
        temp.add(t);
        return new ImmutableList<T>(temp);
    }

    public ImmutableList<T> remove(T t) {
        ArrayList<T> temp = new ArrayList<T>(this.list);
        temp.remove(t);
        return new ImmutableList<T>(temp);
    }

    public ImmutableList<T> replace(T t1, T t2) {
        ArrayList<T> temp = new ArrayList<T>(this.list);
        if (t1 == t2) {
            return new ImmutableList<T>(temp);
        }
        while (temp.indexOf(t1) != -1) {
            temp.set(temp.indexOf(t1), t2);
        }
        return new ImmutableList<T>(temp);
    }

    public ImmutableList<T> limit(long n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException("limit size < 0");
        }
        ArrayList<T> temp = new ArrayList<T>(this.list);
        if (temp.size() <= n) {
            return new ImmutableList<T>(temp);
        }
        return new ImmutableList<T>(temp.subList(0,(int)n));
    }

    public boolean equals(ImmutableList<T> list) {   
        return this.list.equals(list.getList());
    }

    public ImmutableList<T> sorted(Comparator<T> c) throws NullPointerException {
        if (c == null) {
            throw new NullPointerException("Comparator is null");
        }
        ArrayList<T> temp = new ArrayList<T>(this.list);
        temp.sort(c);
        return new ImmutableList<T>(temp);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ImmutableList) {
            return this.getList().equals(((ImmutableList) obj).getList());
        } else {
            return false;
        }
    }
}
