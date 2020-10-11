import java.util.ArrayList;
import java.util.List;

/**
 * The Disease Outbreak Response Management System (DORMS) is a system
 * for tracking and managing disease outbreaks in Singapore.
 */
public class Dorms {

    /**
     * Currently, DORMS is managing the SARS-CoV-2 outbreak that causes
     * COVID-19.
     */
    private static final String LOG_STRING = "%%s %s %%.3f";
    private static final String CHECK_IN = "visits %s at time";
    private static final String CHECK_OUT = "leaves %s at time";
    private static final String TESTS_POSITIVE = "tests positive for %s " +
        "at time";
    private static final String TESTS_NEGATIVE = "test negative for %s " +
        "at time";
    private static final String CONTACT = "met %s at time";

    private final TraceTogether traceTogether = new TraceTogether();
    private final SafeEntry safeEntry = new SafeEntry();
    private final PersonDatabase personDatabase = new PersonDatabase();
    private final List<Contact> listOfContacts = new ArrayList<>();
    private final boolean verbose;

    /**
     * Initialises DORMS
     * @param verbose Initialises DORMS in verbose mode, i.e. log messages
     *                will be printed in verbose mode.
     */
    public Dorms(boolean verbose) {
        this.verbose = verbose;
    }

    /**
     * Indicates to DORMS that someone has checked in to a location
     * @param personName    The person's name
     * @param isWearingMask true if that person is wearing a mask
     * @param locationName  The name of the location
     * @param time          The time the person checks in
     */
    public final void checkIn(String personName,
            boolean isWearingMask,
            String locationName,
            double time) {
        // Get the most updated state of the Person
        Person person = personDatabase.getPerson(personName, isWearingMask);
        if (person.onSHN(time)) {
            return;
        }
        // Check the person in to the location to get the resulting contacts
        List<Contact> result = safeEntry.checkIn(person, locationName, time);

        // Update the state of the people from the contacts
        this.handleNewContacts(result);
        logCheckIn(person, locationName, time);
    }

    /**
     * Indicates to DORMS that someone has checked out of a location
     * @param personName    The person's name
     * @param locationName  The location's name
     * @param time          The time the person checked out
     */
    public final void checkOut(String personName,
            String locationName,
            double time) {
        // Get the most updated state of the Person
        Person person = personDatabase.getPerson(personName);
        if (person.onSHN(time)) {
            return;
        }

        // Check the Person out of the location
        safeEntry.checkOut(person, locationName);
        logCheckOut(person, locationName, time);
    }

    /**
     * Indicates to DORMS that two people had made contact
     * @param personA           The first person's name
     * @param isAWearingMask    true if the first person is wearing a mask
     * @param personB           The second person's name
     * @param isBWearingMask    true if the second person is wearing a mask
     * @param time              The time of contact
     */
    public final void contact(String personA,
            boolean isAWearingMask,
            String personB,
            boolean isBWearingMask,
            double time) {
        if (personA.equals(personB)) {
            return;
        }

        // Get the most updated state of the two people
        Person a = personDatabase.getPerson(personA, isAWearingMask);
        Person b = personDatabase.getPerson(personB, isBWearingMask);
        if (a.onSHN(time) || b.onSHN(time)) {
            return;
        }

        // Make the contact and update the state of the people
        this.handleNewContacts(List.of(traceTogether.contact(a, b, time)));
        logContact(a, b, time);
    }

    /**
     * Indicates to DORMS that someone has presented with symptoms of the
     * target virus
     * @param personName The person's name
     * @param time       The time this happened
     */
    public final void presentSymptoms(String personName,
            double time) {
        Person person = personDatabase.getPerson(personName);
        handleSickPerson(person, time);
    }

    public final void printStatistics() {
        int numOfInfected = 0;
        List<? extends Person> listOfAllPeople = this.personDatabase
                .getAllPeople();
        for (Person p : listOfAllPeople) {
            if (p.test(SimulationParameters.TARGET_VIRUS)) {
                numOfInfected++;
            }
        }
        System.out.println("===== STATISTICS =====");
        System.out.println("Infected population: " + numOfInfected);
        System.out.println("Total Population: " + listOfAllPeople.size());
    }

    /**
     * This transmits viruses between two people sequentially, updating
     * the state of each Person at each iteration using 
     * {@link PersonDatabase#update}
     * @param listOfContacts The list of contacts to transmit viruses
     */
    private final void handleNewContacts(List<Contact> listOfContacts) {
        List<Contact> rebuild = new ArrayList<>();
        for (Contact c : listOfContacts) {
            // Get the updated state of the two people involved
            List<Person> peopleInvolved = c.getPeople();
            Person a = getUpdatedPerson(peopleInvolved.get(0).toString());
            Person b = getUpdatedPerson(peopleInvolved.get(1).toString());

            // Form the new contact and transmit viruses
            Contact d = new Contact(a, b, c.timeOfContact());

            // Update the state of the two people
            personDatabase.update(
                    d.transmit(RandomNumberGenerator.nextDouble()));
            rebuild.add(d);
        }
        this.listOfContacts.addAll(rebuild);
    }

    //////To implement DormsWithShn, you will need the following methods//////

    /**
     * Handles the sick person.
     * @param person The latest state of the sick Person
     * @param time   The time this is handled
     */
    void handleSickPerson(Person person, double time) {
        if (!person.test(SimulationParameters.TARGET_VIRUS)) {
            logNegativeTest(person, time);
        } else {
            logPositiveTest(person, time);
        }
    }

    /**
     * Queries all contacts involved with the {@code person}
     * @param personName The name of the person involved
     * @param time       The time the query was made
     * @return           The contacts that involved this Person that happened
     *                   after {@code time -} 
     *                   {@link SimulationParameters#TRACING_PERIOD} 
     */
    final List<? extends Contact> queryContacts(String personName, 
            double time) {
        List<Contact> result = new ArrayList<>();
        for (Contact c : listOfContacts) {
            List<Person> involved = c.getPeople();
            String firstName = involved.get(0).toString();
            String secondName = involved.get(1).toString();
            double timeOfContact = c.timeOfContact();
            boolean involvesPerson = firstName.equals(personName) ||
                    secondName.equals(personName);
            boolean isWithinTime = time - SimulationParameters.TRACING_PERIOD 
                    < timeOfContact;
            if (involvesPerson && isWithinTime) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     * Logs a string to the console when in verbose mode
     * @param s The string to log
     */
    final void log(String s) {
        if (this.verbose) {
            System.out.println(s);
        }
    }

    /**
     * Updates the state of a Person
     * @param p The updated state of the Person to update
     */
    final void updatePerson(Person p) {
        personDatabase.update(p);
    }

    /**
     * Retrieves the updated state of a person
     * @param personName The name of the person
     * @return           the updated state of the person
     */
    final Person getUpdatedPerson(String personName) {
        return personDatabase.getPerson(personName);
    }

    ///////You do not need the following methods for DormsWithShn////////

    private final void logCheckIn(Person p, String l, double time) {
        log(String.format(buildLog(CHECK_IN), p, l, time));
    }

    private final void logCheckOut(Person p, String l, double time) {
        log(String.format(buildLog(CHECK_OUT), p, l, time));
    }

    private final void logNegativeTest(Person person, double time) {
        log(String.format(buildLog(TESTS_NEGATIVE), 
                    person, SimulationParameters.TARGET_VIRUS, time));
    }

    private final void logPositiveTest(Person person, double time) {
        log(String.format(buildLog(TESTS_POSITIVE),
                    person, SimulationParameters.TARGET_VIRUS, time));
    }

    private final void logContact(Person a, Person b, double time) {
        log(String.format(buildLog(CONTACT), a, b, time));
    }

    private final String buildLog(String s) {
        return String.format(LOG_STRING, s);
    }

}

