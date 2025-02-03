package option2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class to read prospective driver data from a CSV file.
 */
public class CSVReader {

    private String filePath;

    /**
     * Constructs a CSVReader with the specified file path.
     *
     * @param filePath The path to the CSV file.
     */
    public CSVReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads prospective driver data from the specified CSV file.
     *
     * @param filePath The path to the CSV file.
     * @return A list of prospective drivers read from the CSV file.
     */
    public static List<ProspectiveDriver> readProspectiveDriversFromCSV(String filePath) {
        List<ProspectiveDriver> prospectiveDrivers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeaderRow = true;

            while ((line = br.readLine()) != null) {
                if (isHeaderRow) {
                    isHeaderRow = false;
                    continue;
                }

                String[] values = line.split(",");
                String firstName = values[0];
                String lastName = values[1];
                LocalDate dateOfBirth = parseDate(values[2]);
                Person driverInfo = new Person(firstName, lastName, dateOfBirth);
                Person driverName = new Person(firstName, lastName);

                String licenseFirstName = values[3];
                String licenseLastName = values[4];
                LocalDate licenseDateOfBirth = parseDate(values[5]);
                String licenseNumber = values[6];
                String streetAddress = values[7];
                String city = values[8];
                String state = values[9];
                String zipcode = values[10];
                Address address = new Address(streetAddress, city, state, zipcode);
                String countryOfIssuance = values[11];
                String stateOfIssuance = values[12];
                LocalDate issuanceDate = parseDate(values[13]);
                LocalDate expirationDate = parseDate(values[14]);
                Person driverNameOnLicense = new Person(licenseFirstName, licenseLastName);
                DriversLicense license = new DriversLicense(driverNameOnLicense, licenseDateOfBirth, licenseNumber, address,
                        countryOfIssuance, stateOfIssuance, issuanceDate, expirationDate);

                String vehicleMake = values[15];
                String vehicleModel = values[16];
                int vehicleYear = Integer.parseInt(values[17]);
                String vehicleColor = values[18];
                String vehicleLicensePlateNumber = values[19];
                String vehicleOwnerFirstName = values[20];
                String vehicleOwnerLastName = values[21];
                Person vehicleOwner = new Person(vehicleOwnerFirstName, vehicleOwnerLastName);
                Vehicle vehicle = new Vehicle(vehicleMake, vehicleModel, vehicleYear, vehicleColor, vehicleLicensePlateNumber, vehicleOwner);

                String insuranceOwnerFirstName = values[22];
                String insuranceOwnerLastName = values[23];
                Person insuranceOwner = new Person(insuranceOwnerFirstName, insuranceOwnerLastName);
                LocalDate insuranceExpirationDate = parseDate(values[24]);

                String additionalDriversString = values[25];
                Person[] additionalDrivers = null;
                if (!additionalDriversString.isEmpty()) {
                    String[] additionalDriverNames = additionalDriversString.split("/");
                    int numAdditionalDrivers = additionalDriverNames.length / 2 + (additionalDriverNames.length % 2);
                    additionalDrivers = new Person[numAdditionalDrivers];
                    for (int i = 0, j = 0; i < additionalDriverNames.length; i += 2, j++) {
                        String additionalDriverFirstName = additionalDriverNames[i];
                        String additionalDriverLastName = (i + 1 < additionalDriverNames.length) ? additionalDriverNames[i + 1] : "";
                        additionalDrivers[j] = new Person(additionalDriverFirstName, additionalDriverLastName);
                    }
                }

                Insurance insurance = new Insurance(insuranceOwner, additionalDrivers, insuranceExpirationDate);
                vehicle.setInsurance(insurance);

                LocalDate vehicleIncidentDate = parseDate(values[26]);
                String vehicleIncidentDriverFirstName = values[27];
                String vehicleIncidentDriverLastName = values[28];
                Person vehicleIncidentDriver = new Person(vehicleIncidentDriverFirstName, vehicleIncidentDriverLastName);
                int vehicleIncidentType = -1;
                try {
                    vehicleIncidentType = Integer.parseInt(values[29]);
                } catch (NumberFormatException e) {
                    // Handle the case where the string is not a valid integer
                }

                if (vehicleIncidentType == 1) {
                    CrashType crashType = CrashType.valueOf(values[30]);
                    vehicle.addVehicleHistory(new VehicleHistory(vehicleIncidentDate, vehicleIncidentDriver, null, crashType));
                } else if (vehicleIncidentType == 2) {
                    ViolationType violationType = ViolationType.valueOf(values[31]);
                    vehicle.addVehicleHistory(new VehicleHistory(vehicleIncidentDate, vehicleIncidentDriver, violationType, null));
                }

                ProspectiveDriver prospectiveDriver = new ProspectiveDriver(driverInfo, license);
                prospectiveDriver.addVehicle(vehicle);

                LocalDate driverViolationDate = LocalDate.MIN;
                ViolationType driverViolationType = null;

                if (values.length > 32) {
                    driverViolationDate = parseDate(values[32]);
                    if (!driverViolationDate.equals(LocalDate.MIN) && values.length > 33) {
                        driverViolationType = ViolationType.valueOf(values[33]);
                    }
                }

                if (!driverViolationDate.equals(LocalDate.MIN) && driverViolationType != null) {
                    prospectiveDriver.addDriverHistory(new DriverHistory(driverViolationDate, driverViolationType));
                }
                prospectiveDrivers.add(prospectiveDriver);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prospectiveDrivers;
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateString The date string to parse.
     * @return The LocalDate object parsed from the string, or LocalDate.MIN if the string is empty or not in the correct format.
     */

    private static LocalDate parseDate(String dateString) {
        if (dateString.isEmpty()) {
            return LocalDate.MIN;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            // Handle the case where the string is not a valid date
            return LocalDate.MIN;
        }
    }
}