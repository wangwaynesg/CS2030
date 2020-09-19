public void serveCruises(Cruise[] cruises) {
    // Initialize an array of 50 loaders and assign a dummy Cruise to each of them
    Loader[] loaders = new Loader [50];
    for (int j = 0; j < 50; j++) {
        loaders[j] = new Loader(j + 1, new Cruise(String.format("Loader%d", j + 1), 0, 0, 0));
    }

    int numOfLoadersRequired = 0;
    Cruise dummyCruise = null;
    int dummyCruiseArrivalTime = 0;

    for (int i = 0; i < cruises.length; i++) {
        numOfLoadersRequired = cruises[i].getNumOfLoadersRequired();

        for (int k = 0; k < 50; k++) {
            if (loaders[k].canServe(cruises[i]) && numOfLoadersRequired > 0) {
                if ((k + 1) % 3 == 0) {
                    loaders[k] = new RecycledLoader(loaders[k].getIdentifier(), loaders[k].getCruise());
                }
                loaders[k] = loaders[k].serve(cruises[i]);
                System.out.println(loaders[k].toString());
                numOfLoadersRequired--;
            }

            if (numOfLoadersRequired == 0) {
                k = 50;
            }
        }
    }
}
