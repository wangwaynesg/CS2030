Logger<Integer> add(Logger<Integer> a, int b) {
    return a.map(x -> x + b);
}

Logger<Integer> sum(int n) {
    if (n == 0) {
        return Logger.make(n);
    } else {
        return add(sum(n-1), n);
    }
}

Logger<Integer> f(int n) {
    Logger<Integer> result = Logger.make(n);
    while (result.get() != 1) {
        if (result.test(x -> x % 2 == 0)) {
            result = result.map(x -> x / 2);
        } else {
            result = result.map(x -> 3 * x);
            result = result.map(x -> x + 1);
        }
    }
    return result;
}
