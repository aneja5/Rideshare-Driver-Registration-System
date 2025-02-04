package option2;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Validates the registration of prospective drivers and their vehicles.
 */
public class RegistrationValidator {
    private static final List<ProspectiveDriver> acceptedProspectiveDrivers = new ArrayList<>();

    /**
     * Main method for prospective driver registration.
     *
     * @param args unused command line arguments
     * @throws Exception for unexpected errors during user interaction
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                displayMenu();
                int choice = getChoice(scanner);

                switch (choice) {
                    case 1:
                        registerDriver(scanner);
                        break;
                    case 2:
                        System.out.println("Exiting ProspectiveDriver Registration");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Displays the registration menu.
     */
    private static void displayMenu() {
        System.out.println("ProspectiveDriver Registration");
        System.out.println("1. Register ProspectiveDriver");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Gets validated menu choice as an integer.
     *
     * @param scanner the Scanner object used for user input
     * @return the valid integer choice entered by the user
     */
    private static int getChoice(Scanner scanner) {
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        scanner.nextLine();
        return choice;
    }

    /**
     * Guides user through driver registration, validates, and adds to accepted list.
     *
     * @param scanner the Scanner object used for user input
     */
    private static void registerDriver(Scanner scanner) {
        System.out.println("Welcome to the ProspectiveDriver Registration System!");

        String firstName = getNotEmptyInput(scanner, "Enter driver's first name: ");
        String lastName = getNotEmptyInput(scanner, "Enter driver's last name: ");
        LocalDate dateOfBirth = getDateInput(scanner, "Enter driver's date of birth (yyyy-MM-dd):");

        Person driverInfo = new Person(firstName, lastName);

        DriversLicense license = getDriversLicenseInformation(scanner);
        ProspectiveDriver newProspectiveDriver = new ProspectiveDriver(driverInfo, license);

        boolean moreVehicles = true;
        while (moreVehicles) {
            Vehicle vehicle = getVehicleInformation(scanner);
            Insurance insurance = getInsuranceInformation(scanner);
            vehicle.setInsurance(insurance);

            collectVehicleHistory(scanner, vehicle);

            newProspectiveDriver.addVehicle(vehicle);

            moreVehicles = promptForMoreVehicles(scanner);
        }

        collectDriverHistory(scanner, newProspectiveDriver);

        if (validateRegistration(driverInfo, dateOfBirth, newProspectiveDriver)) {
            acceptedProspectiveDrivers.add(newProspectiveDriver);
            System.out.println("ProspectiveDriver registration complete!");
        } else {
            System.out.println("ProspectiveDriver registration application rejected.");
        }
    }

    /**
     * Collects driver's license details.
     *
     * @param scanner scanner for user input
     * @return DriversLicense object
     */
    private static DriversLicense getDriversLicenseInformation(Scanner scanner) {
        System.out.println("Enter license details-");
        String licenseFirstName = getNotEmptyInput(scanner, "First name: ");
        String licenseLastName = getNotEmptyInput(scanner, "Last name: ");
        Person driverNameOnLicense = new Person(licenseFirstName, licenseLastName);
        LocalDate licenseDateOfBirth = getDateInput(scanner, "Date of birth (yyyy-MM-dd): ");
        String licenseNumber = getInput(scanner, "License number: ").toUpperCase();
        Address address = getAddressInformation(scanner);
        String countryOfIssuance = getInput(scanner, "Country of license issuance: ").toUpperCase();
        String stateOfIssuance = getInput(scanner, "State of license issuance: ").toUpperCase();
        LocalDate issuanceDate = getDateInput(scanner, "License issuance date (yyyy-MM-dd): ");
        LocalDate expirationDate = getDateInput(scanner, "License expiration date (yyyy-MM-dd): ");

        return new DriversLicense(driverNameOnLicense, licenseDateOfBirth, licenseNumber, address,
                countryOfIssuance, stateOfIssuance, issuanceDate, expirationDate);
    }

    /**
     * Collects driver's address.
     *
     * @param scanner scanner for user input
     * @return Address object
     */
    private static Address getAddressInformation(Scanner scanner) {
        System.out.println("Enter driver's address:");
        String streetAddress = getInput(scanner, "Street: ");
        String city = getInput(scanner, "City: ");
        String state = getInput(scanner, "State: ").toUpperCase();
        String zipcode = getInput(scanner, "Zip-code: ").toUpperCase();

        return new Address(streetAddress, city, state, zipcode);
    }

    /**
     * Collects vehicle details.
     *
     * @param scanner scanner for user input
     * @return Vehicle object
     */
    private static Vehicle getVehicleInformation(Scanner scanner) {
        System.out.println("Enter the vehicle details");
        String make = getInput(scanner, "Make: ");
        String model = getInput(scanner, "Model: ");
        int year = getIntInput(scanner, "Vehicle year: ");
        String color = getInput(scanner, "Color : ");
        String licensePlateNumber = getInput(scanner, "License plate number: ").toUpperCase();
        String ownerFirstName = getInput(scanner, "Vehicle owner's first name: ");
        String ownerLastName = getInput(scanner, "Vehicle owner's last name: ");

        Person vehicleOwner = new Person(ownerFirstName, ownerLastName);
        return new Vehicle(make, model, year, color, licensePlateNumber, vehicleOwner);
    }

    /**
     * Prompts the user to decide whether to add another vehicle.
     *
     * @param scanner The Scanner object used to receive user input.
     * @return true if the user chooses to add another vehicle, false otherwise.
     */
    private static boolean promptForMoreVehicles(Scanner scanner) {
        System.out.println("Do you want to add another vehicle? (yes/no):");
        String decision = scanner.nextLine().trim().toLowerCase();
        while (!decision.equals("yes") && !decision.equals("no")) {
            System.out.println("Invalid input. Please enter 'yes' or 'no':");
            decision = scanner.nextLine().trim().toLowerCase();
        }
        return decision.equals("yes");
    }

    /**
     * Collects vehicle insurance details.
     *
     * @param scanner scanner for user input
     * @return Insurance object
     */
    private static Insurance getInsuranceInformation(Scanner scanner) {
        System.out.println("Enter insurance details");
        String ownerFirstName = getInput(scanner, "Owner's first name: ");
        String ownerLastName = getInput(scanner, "Owner's last name: ");
        Person insuranceOwner = new Person(ownerFirstName, ownerLastName);
        LocalDate insuranceExpirationDate = getDateInput(scanner, "Enter insurance expiration date (yyyy-MM-dd):");

        Person[] additionalDrivers = getAdditionalDrivers(scanner);

        return new Insurance(insuranceOwner, additionalDrivers, insuranceExpirationDate);
    }

    /**
     * Retrieves information about additional drivers added in the insurance, if any, from the user.
     *
     * @param scanner The Scanner object used to receive user input.
     * @return An array of Person objects representing the additional drivers, or null if there are none.
     */
    private static Person[] getAdditionalDrivers(Scanner scanner) {
        System.out.println("Are there additional drivers added in the insurance? (yes/no):");
        String additionalDriversChoice = scanner.nextLine().toLowerCase();

        if (!additionalDriversChoice.equals("yes")) {
            return null;
        }

        System.out.println("Enter the number of additional drivers:");
        int numAdditionalDrivers = getIntInput(scanner, "");

        Person[] additionalDrivers = new Person[numAdditionalDrivers];
        for (int i = 0; i < numAdditionalDrivers; i++) {
            String firstName = getInput(scanner, "Enter additional driver " + (i + 1) + "'s first name:");
            String lastName = getInput(scanner, "Enter additional driver " + (i + 1) + "'s last name:");
            additionalDrivers[i] = new Person(firstName, lastName);
        }

        return additionalDrivers;
    }

    /**
     * Collects vehicle incident history (if any).
     *
     * @param scanner scanner for user input
     * @param vehicle vehicle to associate history with
     */
    private static void collectVehicleHistory(Scanner scanner, Vehicle vehicle) {
        String hasHistory = getNotEmptyInput(scanner, "Does this vehicle have a history of incidents? (yes/no):").toLowerCase();
        if (hasHistory.equals("no")) {
            return;
        }

        int numIncidents = getIntInput(scanner, "Enter number of incidents involving the vehicle:");
        for (int i = 0; i < numIncidents; i++) {
            System.out.println("Details for incident " + (i + 1) + ":");
            LocalDate date = getDateInput(scanner, "Enter date of incident (yyyy-MM-dd):");
            String firstName = getNotEmptyInput(scanner, "First name of the driver involved in the incident: ");
            String lastName = getNotEmptyInput(scanner, "Last name of the driver involved in the incident: ");
            Person incidentDriver = new Person(firstName, lastName);

            int incidentType = getIntInput(scanner, "Enter incident type (1 for Crash, 2 for Violation):");
            if (incidentType == 1) {
                handleCrashInput(scanner, vehicle, date, incidentDriver);
            } else {
                handleVehicleViolationCategoryInput(scanner, vehicle, date, incidentDriver);
            }
        }
    }

    /**
     * Handles user input to select the type of crash and adds the corresponding vehicle history.
     *
     * @param scanner        The Scanner object used to receive user input.
     * @param vehicle        The Vehicle object involved in the crash.
     * @param date           The date of the crash.
     * @param incidentDriver The Person object representing the driver involved in the crash.
     */
    private static void handleCrashInput(Scanner scanner, Vehicle vehicle, LocalDate date, Person incidentDriver) {
        System.out.println("Select crash type:");
        CrashType[] crashTypes = CrashType.values();
        for (int i = 0; i < crashTypes.length; i++) {
            System.out.println((i + 1) + ". " + crashTypes[i]);
        }
        int crashTypeIndex = getIntInput(scanner, "Choose the number for crash type: ") - 1;

        if (crashTypeIndex >= 0 && crashTypeIndex < crashTypes.length) {
            CrashType selectedCrashType = crashTypes[crashTypeIndex];
            vehicle.addVehicleHistory(new VehicleHistory(date, incidentDriver, selectedCrashType, null));
        } else {
            System.out.println("Invalid selection. Please choose a valid crash type.");
            handleCrashInput(scanner, vehicle, date, incidentDriver); // Recursive call for correct input
        }
    }

    /**
     * Handles user input to select the type of violation category for the vehicle and directs to
     * the appropriate method based on the selection.
     *
     * @param scanner        The Scanner object used to receive user input.
     * @param vehicle        The Vehicle object involved in the violation.
     * @param date           The date of the violation.
     * @param incidentDriver The Person object representing the driver involved in the violation.
     */
    private static void handleVehicleViolationCategoryInput(Scanner scanner, Vehicle vehicle, LocalDate date, Person incidentDriver) {
        System.out.println("Enter type of vehicle violation (1 for Moving, 2 for Non-Moving):");
        int violationCategory = getIntInput(scanner, "");

        if (violationCategory == 1) {
            handleVehicleMovingViolation(scanner, vehicle, date, incidentDriver);
        } else if (violationCategory == 2) {
            handleVehicleNonMovingViolation(scanner, vehicle, date, incidentDriver);
        } else {
            System.out.println("Invalid input. Please enter '1' for Moving or '2' for Non-Moving violations.");
            handleVehicleViolationCategoryInput(scanner, vehicle, date, incidentDriver);
        }
    }

    /**
     * Handles user input to select the type of moving violation for the vehicle and adds the
     * corresponding vehicle history.
     *
     * @param scanner        The Scanner object used to receive user input.
     * @param vehicle        The Vehicle object involved in the violation.
     * @param date           The date of the violation.
     * @param incidentDriver The Person object representing the driver involved in the violation.
     */
    private static void handleVehicleMovingViolation(Scanner scanner, Vehicle vehicle, LocalDate date, Person incidentDriver) {
        System.out.println("Select moving violation type:");
        ViolationType.MovingViolation[] movingViolations = ViolationType.MovingViolation.values();
        for (int i = 0; i < movingViolations.length; i++) {
            System.out.println((i + 1) + ". " + movingViolations[i]);
        }
        int choice = getIntInput(scanner, "") - 1;

        if (choice >= 0 && choice < movingViolations.length) {
            ViolationType.MovingViolation violationType = movingViolations[choice];
            vehicle.addVehicleHistory(new VehicleHistory(date, incidentDriver, violationType, null));
        } else {
            System.out.println("Invalid selection. Please choose a valid violation type.");
            handleVehicleMovingViolation(scanner, vehicle, date, incidentDriver);
        }
    }

    /**
     * Handles user input to select the type of non-moving violation for the vehicle and adds the
     * corresponding vehicle history.
     *
     * @param scanner        The Scanner object used to receive user input.
     * @param vehicle        The Vehicle object involved in the violation.
     * @param date           The date of the violation.
     * @param incidentDriver The Person object representing the driver involved in the violation.
     */
    private static void handleVehicleNonMovingViolation(Scanner scanner, Vehicle vehicle, LocalDate date, Person incidentDriver) {
        System.out.println("Select non-moving violation type:");
        ViolationType.NonMovingViolation[] nonMovingViolations = ViolationType.NonMovingViolation.values();
        for (int i = 0; i < nonMovingViolations.length; i++) {
            System.out.println((i + 1) + ". " + nonMovingViolations[i]);
        }
        int choice = getIntInput(scanner, "") - 1;

        if (choice >= 0 && choice < nonMovingViolations.length) {
            ViolationType.NonMovingViolation violationType = nonMovingViolations[choice];
            vehicle.addVehicleHistory(new VehicleHistory(date, incidentDriver, violationType, null));
        } else {
            System.out.println("Invalid selection. Please choose a valid violation type.");
            handleVehicleNonMovingViolation(scanner, vehicle, date, incidentDriver);
        }
    }

    /**
     * Collects driver's violation history (if any).
     *
     * @param scanner           scanner for user input
     * @param prospectiveDriver driver to associate history with
     */
    private static void collectDriverHistory(Scanner scanner, ProspectiveDriver prospectiveDriver) {
        String hasViolations = getNotEmptyInput(scanner, "Does this prospectiveDriver have a history of violations? (yes/no):").toLowerCase();
        if (hasViolations.equals("no")) {
            return;
        }

        int numViolations = getIntInput(scanner, "Enter the number of violations committed by the prospectiveDriver:");
        for (int i = 0; i < numViolations; i++) {
            LocalDate date = getDateInput(scanner, "Enter date of violation (yyyy-MM-dd):");
            handleViolationCategoryInput(scanner, prospectiveDriver, date);
        }
    }

    /**
     * Handles user input to select the type of violation category for the prospective driver and
     * directs to the appropriate method based on the selection.
     *
     * @param scanner           The Scanner object used to receive user input.
     * @param prospectiveDriver The ProspectiveDriver object for whom the violation is being handled.
     * @param date              The date of the violation.
     */
    private static void handleViolationCategoryInput(Scanner scanner, ProspectiveDriver prospectiveDriver, LocalDate date) {
        System.out.println("Enter type of violation (1 for Moving, 2 for Non-Moving):");
        int violationCategory = getIntInput(scanner, "");

        if (violationCategory == 1) {
            handleMovingViolation(scanner, prospectiveDriver, date);
        } else if (violationCategory == 2) {
            handleNonMovingViolation(scanner, prospectiveDriver, date);
        } else {
            System.out.println("Invalid input. Please enter '1' for Moving or '2' for Non-Moving violations.");
            handleViolationCategoryInput(scanner, prospectiveDriver, date);
        }
    }

    /**
     * Handles user input to select the type of moving violation for the prospective driver and
     * adds the corresponding driver history.
     *
     * @param scanner           The Scanner object used to receive user input.
     * @param prospectiveDriver The ProspectiveDriver object for whom the violation is being handled.
     * @param date              The date of the violation.
     */
    private static void handleMovingViolation(Scanner scanner, ProspectiveDriver prospectiveDriver, LocalDate date) {
        System.out.println("Select moving violation type:");
        ViolationType.MovingViolation[] movingViolations = ViolationType.MovingViolation.values();
        for (int i = 0; i < movingViolations.length; i++) {
            System.out.println((i + 1) + ". " + movingViolations[i]);
        }
        int choice = getIntInput(scanner, "") - 1;

        if (choice >= 0 && choice < movingViolations.length) {
            ViolationType.MovingViolation violationType = movingViolations[choice];
            prospectiveDriver.addDriverHistory(new DriverHistory(date, violationType));
        } else {
            System.out.println("Invalid selection. Please choose a valid violation type.");
            handleMovingViolation(scanner, prospectiveDriver, date); // Recursive call for correct input
        }
    }

    /**
     * Handles user input to select the type of non-moving violation for the prospective driver and
     * adds the corresponding driver history.
     *
     * @param scanner           The Scanner object used to receive user input.
     * @param prospectiveDriver The ProspectiveDriver object for whom the violation is being handled.
     * @param date              The date of the violation.
     */
    private static void handleNonMovingViolation(Scanner scanner, ProspectiveDriver prospectiveDriver, LocalDate date) {
        System.out.println("Select non-moving violation type:");
        ViolationType.NonMovingViolation[] nonMovingViolations = ViolationType.NonMovingViolation.values();
        for (int i = 0; i < nonMovingViolations.length; i++) {
            System.out.println((i + 1) + ". " + nonMovingViolations[i]);
        }
        int choice = getIntInput(scanner, "") - 1;

        if (choice >= 0 && choice < nonMovingViolations.length) {
            ViolationType.NonMovingViolation violationType = nonMovingViolations[choice];
            prospectiveDriver.addDriverHistory(new DriverHistory(date, violationType));
        } else {
            System.out.println("Invalid selection. Please choose a valid violation type.");
            handleNonMovingViolation(scanner, prospectiveDriver, date);
        }
    }


    /**
     * Retrieves a string input from the user via the provided Scanner object and displays the given prompt.
     *
     * @param scanner The Scanner object used to receive user input.
     * @param prompt  The prompt message to display to the user.
     * @return A string containing the user's input.
     */
    private static String getInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Retrieves a non-empty string input from the user via the provided Scanner object and displays the given prompt.
     * Continues to prompt the user until a non-empty input is provided.
     *
     * @param scanner The Scanner object used to receive user input.
     * @param prompt  The prompt message to display to the user.
     * @return A non-empty string containing the user's input.
     */
    private static String getNotEmptyInput(Scanner scanner, String prompt) {
        String input = "";
        System.out.print(prompt);

        while (input.isEmpty()) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine().trim();
            }
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                System.out.print(prompt);
            }
        }
        return input;
    }

    /**
     * Retrieves an integer input from the user via the provided Scanner object and displays the given prompt.
     * Continues to prompt the user until a valid integer input is provided.
     *
     * @param scanner The Scanner object used to receive user input.
     * @param prompt  The prompt message to display to the user.
     * @return An integer representing the user's input.
     */
    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Retrieves a LocalDate input from the user via the provided Scanner object and displays the given prompt.
     * Continues to prompt the user until a valid LocalDate input is provided in the format yyyy-MM-dd.
     *
     * @param scanner The Scanner object used to receive user input.
     * @param prompt  The prompt message to display to the user.
     * @return A LocalDate object representing the user's input.
     */
    private static LocalDate getDateInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
            }
        }
    }

    /**
     * Validates the registration of a prospective driver based on their information, license, and vehicles.
     *
     * @param driversInfo       Information provided by the prospective driver.
     * @param dateOfBirth       Prospective driver's date of birth.
     * @param prospectiveDriver The prospective driver to be validated.
     * @return True if the registration is valid, false otherwise.
     */
    public static boolean validateRegistration(Person driversInfo, LocalDate dateOfBirth,
                                               ProspectiveDriver prospectiveDriver) {

        // Validate driver's license
        if (!validateDriverLicense(prospectiveDriver.getLicense(), driversInfo, dateOfBirth)) {
            return false;
        }

        // Validate driver's vehicles
        for (Vehicle vehicle : prospectiveDriver.getVehicles()) {
            if (!validateVehicle(vehicle)) {
                return false;
            }
        }

        // Validate driver's history
        if (!validateDriverHistory(prospectiveDriver.getDriverHistory())) {
            return false;
        }

        return true;
    }

    /**
     * Validates a driver's license based on various criteria including validity, age requirement,
     * issuance/expiration dates, and country of issuance.
     *
     * @param license     The driver's license to be validated.
     * @param driversInfo Information provided by the prospective driver.
     * @param dateOfBirth Prospective driver's date of birth.
     * @return True if the license is valid, false otherwise.
     */
    protected static boolean validateDriverLicense(DriversLicense license, Person driversInfo, LocalDate dateOfBirth) {
        if (!license.isValidDriver(driversInfo, dateOfBirth)) {
            System.out.println("Prospective Driver's credential doesn't match.");
            return false;
        }

        if (!license.isValidAge()) {
            System.out.println("ProspectiveDriver must be at least 21 years old.");
            return false;
        }

        if (!license.isValidCountryOfIssuance()) {
            System.out.println("Currently drivers license issued in US and Canada are accepted.");
            return false;
        }

        if (!license.isValidIssuanceDate()) {
            System.out.println("License issuance date must be more than 6 months ago.");
            return false;
        }

        if (!license.isValidExpirationDate()) {
            System.out.println("License has expired.");
            return false;
        }

        return true;
    }

    /**
     * Validates a vehicle based on its age, valid insurance, and owner match with insurance.
     *
     * @param vehicle The vehicle to be validated.
     * @return True if the vehicle is valid, false otherwise.
     */
    protected static boolean validateVehicle(Vehicle vehicle) {
        if (!vehicle.isValidVehicle()) {
            System.out.println("Vehicle must be less than 15 years old and meet other criteria.");
            return false;
        }

        if (vehicle.getInsurance() == null || !vehicle.getInsurance().isInsuranceValid() ||
                !vehicle.isVehicleOwnerAndInsuranceOwnerNameSame()) {
            System.out.println("Vehicle insurance is not valid or does not match the vehicle owner.");
            return false;
        }

        // Check vehicle history for recent crashes or moving violations
        for (VehicleHistory vHistory : vehicle.getVehicleHistory()) {
            if (vHistory.getDate().isAfter(LocalDate.now().minusMonths(6)) && (
                    vHistory.getCrashType() != null || vHistory.getViolationType() == ViolationType.MOVING_VIOLATION)) {
                System.out.println("Vehicle involved in crashes or moving violations in the last six months.");
                return false;
            }
        }

        return true;
    }

    /**
     * Validates a driver's history for critical moving violations within the past year.
     *
     * @param driverHistory The driver's history to be validated.
     * @return True if the driver's history is clean, false otherwise.
     */
    private static boolean validateDriverHistory(List<DriverHistory> driverHistory) {
        for (DriverHistory history : driverHistory) {
            if (history.getDate().isAfter(LocalDate.now().minusYears(1)) && (
                    history.getViolationType() == ViolationType.MovingViolation.RECKLESS_DRIVING ||
                            history.getViolationType() == ViolationType.MovingViolation.SPEEDING ||
                            history.getViolationType() == ViolationType.MovingViolation.DUI ||
                            history.getViolationType() == ViolationType.MovingViolation.INVALID_LICENSE_INSURANCE)) {
                System.out.println("Prospective Driver has critical moving violations in the past year.");
                return false;
            }
        }

        return true;
    }
}
