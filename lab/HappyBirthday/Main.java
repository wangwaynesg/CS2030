class Main {
    public static String checkEarlierDate(int year1, int month1, int day1,
                                          int year2, int month2, int day2) {
        if (year1 < year2) {
            return("Earlier By Year");
        } else if (year1 == year2 && month1 < month2) {
            return("Earlier By Month");
        } else if (year1 == year2 && month1 == month2 && day1 < day2) {
            return("Earlier By Day");
        } else {
            return("First Birthday Not Earlier Than Second");
        }
    }
}
