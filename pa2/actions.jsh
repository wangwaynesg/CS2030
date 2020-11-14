Function<List<Thing>, List<Thing>> takeSword = x -> {
    Optional<Sword> tempSword = x.stream()
        .filter(thing -> thing instanceof Sword)
        .map(thing -> (Sword) thing)
        .findFirst();
    List<Thing> tempList = new ArrayList<>(x);
    if (tempSword.isEmpty()) {
        System.out.println("--> There is no sword.");
    } else {
        Sword sword = (Sword) tempSword.get();
        if (sword.getTaken()) {
            System.out.println("--> You already have sword.");
            return tempList;
        } else if (!sword.getTaken()) {
            System.out.println("--> You have taken sword.");
            sword = sword.takeSword();
        }
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i) instanceof Sword) {
                tempList.set(i, sword);
            }
        }
    }
    return tempList;
}

Function<List<Thing>, List<Thing>> dropSword = x -> {
    Optional<Sword> tempSword = x.stream()
        .filter(thing -> thing instanceof Sword)
        .map(thing -> (Sword) thing)
        .findFirst();
    List<Thing> tempList = new ArrayList<>(x);
    if (tempSword.isEmpty()) {
        System.out.println("--> There is no sword.");
    } else {
        System.out.println("--> You have dropped sword.");
        Sword sword = (Sword) tempSword.get();
        sword = sword.dropSword();
        for (int i = 0; i < tempList.size(); i ++) {
            if (tempList.get(i) instanceof Sword) {
                tempList.set(i, sword);
            }
        }
    }
    return tempList;
}

Function<Thing, Thing> takeSword2 = x -> {    
    if (x instanceof Sword) {
        Sword sword = (Sword) x;
        if (sword.getTaken()) {
            System.out.println("--> You already have sword.");
            return sword;
        } else {
            System.out.println("--> You have taken sword.");
            return sword.takeSword();
        }
    } else {
        System.out.println("--> There is no sword.");
        return x;
    }
}

Function<Thing, Thing> killTroll2 = x -> {
    if (x instanceof Troll) {
        Troll troll = (Troll) x;
        return troll;
    } else {
        System.out.println("--> There is no troll");
        return x;
    }
}

Function<List<Thing>, List<Thing>> killTroll = x -> {
    Optional<Troll> tempTroll = x.stream()
        .filter(thing -> thing instanceof Troll)
        .map(thing -> (Troll) thing)
        .findFirst();
    Optional<Sword> tempSword = x.stream()
        .filter(thing -> thing instanceof Sword)
        .map(thing -> (Sword) thing)
        .findFirst();
    List<Thing> tempList = new ArrayList<>(x);
    if (tempTroll.isEmpty()) {
        System.out.println("--> There is no troll");
    } else if (tempSword.isEmpty()) {
        System.out.println("--> You have no sword.");
    } else {
        Sword sword = (Sword) tempSword.get();
        if (!sword.getTaken()) {
            System.out.println("--> You have no sword.");
            return tempList;
        }
        System.out.println("--> Troll is killed.");
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i) instanceof Troll) {
                tempList.remove(i);
                break;
            }
        }
    }
    return tempList;
}
