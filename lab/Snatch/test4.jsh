/open Request.java
/open Service.java
/open JustRide.java
/open TakeACab.java
/open ShareARide.java
/open Driver.java
/open NormalCab.java
/open PrivateCar.java
/open Booking.java

new ShareARide()
new PrivateCar("SMA7890", 5)
new Booking(new PrivateCar("SMA7890", 5), new Request(20, 3, 1000))
new PrivateCar("SLA5678", 10)
new Booking(new PrivateCar("SLA5678", 10), new Request(10, 1, 900))
Booking b1 = new Booking(new PrivateCar("SMA7890", 5), new Request(10, 1, 900))
Booking b2 = new Booking(new PrivateCar("SLA5678", 10), new Request(10, 1, 900))
b1.compareTo(b2) < 0
/exit
