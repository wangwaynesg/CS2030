new Shop(2)
Shop shops = new Shop(List.of(new Server(1, true, false, 0), new Server(2, false, false, 1.0)))
shops
shops.find(x -> x.isAvailable())
new Shop(2).find(x -> x.isAvailable())
shops.find(x -> x.isAvailable()).ifPresent(System.out::println)
Server s = new Server(1, false, false, 2.0)
shops.replace(s)
shops.replace(s).find(x -> x.isAvailable())
shops
/exit