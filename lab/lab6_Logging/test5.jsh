add(Logger.make(5), 6)
add(Logger.make(5), 6).printlog()
add(Logger.make(5).map(x -> x * 2), 6)
add(Logger.make(5).map(x -> x * 2), 6).printlog()

sum(0)
sum(0).printlog()
sum(5)
sum(5).printlog()

Logger.make(5).test(x -> x == 5)
Logger.make(5).map(x -> x + 1).test(x -> x % 2 != 0)
Logger.make("hello").test(x -> x.length() == 5)
f(16)
f(16).printlog()
f(10)
f(10).printlog()
/exit
