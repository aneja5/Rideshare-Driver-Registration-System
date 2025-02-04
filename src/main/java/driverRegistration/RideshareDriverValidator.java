package option2;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * This class validates prospective drivers based on their information and
 * manages a pool of accepted drivers.
 */
public class RideshareDriverValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file path containing prospective driver information: ");
        String filePath = scanner.nextLine();

        List<ProspectiveDriver> prospectiveDrivers = CSVReader.readProspectiveDriversFromCSV(filePath);
        AcceptedDriversPool acceptedDriversPool = new AcceptedDriversPool();

        for (ProspectiveDriver prospectiveDriver : prospectiveDrivers) {
            ProspectiveDriver newProspectiveDriver = new ProspectiveDriver(prospectiveDriver.getName(), prospectiveDriver.getLicense());
            for (Vehicle vehicle : prospectiveDriver.getVehicles()) {
                newProspectiveDriver.addVehicle(vehicle);
            }

            String firstName = prospectiveDriver.getName().getFirstName();
            String lastName = prospectiveDriver.getName().getLastName();
            Person driverInfo = new Person(firstName, lastName);
            LocalDate dateOfBirth = prospectiveDriver.getLicense().getDateOfBirthOnLicense();
            if (RegistrationValidator.validateRegistration(driverInfo, dateOfBirth, newProspectiveDriver)) {
                acceptedDriversPool.addDriver(newProspectiveDriver);
            }
        }

        while (true) {
            System.out.print("Enter the last name to search for drivers (or 'exit' to quit): ");
            String lastName = scanner.nextLine();
            if (lastName.equalsIgnoreCase("exit")) {
                break;
            }
            acceptedDriversPool.provideDriverInfo(lastName);
        }

        scanner.close();
    }
}