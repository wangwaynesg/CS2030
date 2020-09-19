Booking findBestBooking(Request request, Driver[] drivers) {
    Booking bestBooking;
    Booking currentBooking;
    
    bestBooking = new Booking(drivers[0], request);
    for (int i = 0; i < drivers.length; i++) {
        currentBooking = new Booking(drivers[i], request);
        if (currentBooking.compareTo(bestBooking) < 0) {
            bestBooking = currentBooking;
        }
    }

    return bestBooking;
}
