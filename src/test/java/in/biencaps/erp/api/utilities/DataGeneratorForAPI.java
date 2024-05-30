package in.biencaps.erp.api.utilities;

import java.time.*;
import java.time.format.*;
import java.time.temporal.WeekFields;
import java.util.*;

import com.github.javafaker.Faker;

public class DataGeneratorForAPI {
	private static Faker faker = new Faker();
	private static Random random = new Random();

	public static String generateFakeEmail() {
		return faker.internet().emailAddress();
	}

	public static String generateFakePassword() {
		return faker.internet().password();
	}

	public static String generateFakePasswordWithSpecificLength(int min, int max) {
		return faker.internet().password(min, max);
	}

	public static String generateFakeFullName() {
		return faker.name().fullName();
	}

	public static String generateFakeTaskTitle() {
		return faker.book().title();
	}

	public static String generateFakePriority() {
		return faker.animal().name();
	}

	public static String generateFakeStatus() {
		return faker.country().name();
	}

	public static String generateFakeDepartment() {
		return faker.company().industry();
	}

	public static String generateFakeRole() {
		return faker.country().capital();
	}

	public static String generateFakeDesignation() {
		return faker.company().profession();
	}

	public static String generateFakeProjet() {
		return faker.app().name();
	}

	public static String generateFakeColor() {
		return faker.color().name();
	}

	public static String generateFakeColorCode() {
		return faker.color().hex(true);
	}

	public static int generateFakeNumberWithRange(int min, int max) {
		return faker.number().numberBetween(min, max);
	}

	public static String generateRandomMobileNumber() {
		// Generate a random starting digit (7, 8, or 9)
		int startDigit = faker.number().numberBetween(7, 10);

		// Generate the remaining 9 digits
		String remainingDigits = faker.number().digits(9);

		// Concatenate the starting digit and remaining digits
		return startDigit + remainingDigits;
	}

	public static String generateRandomInvalidDigitMobileNumber(int stringMinLength, int stringMaxLength) {
		int minLength = stringMinLength;
		int maxLength = stringMaxLength; // You can adjust this as per your requirement
		int length = minLength + new Random().nextInt(maxLength - minLength);
		String chars = "0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int index = new Random().nextInt(chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();
	}

	public static int generateCurrentYearInInteger() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Get the current year as a number
		int currentYearValue = currentDate.getYear();

		return currentYearValue;
	}

	public static int generateCurrentMonthInInteger() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Get the current month as a number
		int currentMonthValue = currentDate.getMonthValue();

		return currentMonthValue;
	}

	public static int generateCurrentWeekOfCurrentMonthInInteger() {
		LocalDate currentDate = LocalDate.now();

		// Get the first day of the current month
		LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);

		// Get the week fields for the default locale
		WeekFields weekFields = WeekFields.of(Locale.getDefault());

		// Get the week number of the first day of the month
		int firstWeekOfMonth = firstDayOfMonth.get(weekFields.weekOfMonth());

		// Get the week number of the current date within the month
		int currentWeekOfMonth = currentDate.get(weekFields.weekOfMonth());

		int currentWeekNumberInMonth = (currentWeekOfMonth - firstWeekOfMonth + 1);

		if (currentWeekNumberInMonth == 5) {
			currentWeekNumberInMonth = 4;

			return currentWeekNumberInMonth;
		}

		return currentWeekNumberInMonth;
	}

	public static String generateRandomFutureDate() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Generate a random number of days between 1 and 365
		int randomDays = faker.number().numberBetween(1, 366);

		// Calculate the future date
		LocalDate futureDate = currentDate.plusDays(randomDays);

		// Format the date to yyyy/MM/dd
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return futureDate.format(formatter);
	}

	public static String generateRandomPastDate() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Generate a random number of days between 1 and 365
		int randomDays = faker.number().numberBetween(1, 366);

		// Calculate the future date
		LocalDate futureDate = currentDate.minusDays(randomDays);

		// Format the date to yyyy/MM/dd
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return futureDate.format(formatter);
	}

	public static String generateRandomDateRangeForDOB(String dateFormat) {
		// Set the start and end dates
		LocalDate startDate = LocalDate.of(1980, 1, 1);
		LocalDate endDate = LocalDate.of(2010, 12, 31);

		// Calculate the range of days between the start and end dates
		long range = startDate.until(endDate).getDays();

		// Generate a random number of days between the start and end dates
		int randomDays = faker.number().numberBetween(0, (int) range);

		// Calculate the random date
		LocalDate randomDate = startDate.plusDays(randomDays);

		// Format the date to yyyy/MM/dd
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		return randomDate.format(formatter);
	}

	public static String generateRandomDateRangeForTask(String dateFormat) {
		// Set the start and end dates
		LocalDate startDate = LocalDate.of(2024, 3, 2);
		LocalDate endDate = LocalDate.of(2024, 12, 31);

		// Calculate the range of days between the start and end dates
		long range = startDate.until(endDate).getDays();

		// Generate a random number of days between the start and end dates
		int randomDays = faker.number().numberBetween(5, (int) range);

		// Calculate the random date
		LocalDate randomDate = startDate.plusDays(randomDays);

		// Format the date to yyyy/MM/dd
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		return randomDate.format(formatter);
	}

	public static String generateRandomDateForGetAssignedTaskInfo(String dateFormat) {
		LocalDate startDate = LocalDate.of(2024, 2, 20);
		LocalDate endDate = LocalDate.now();

		// Calculate the range of days between the start and end dates
		long range = startDate.until(endDate).getDays();

		// Generate a random number of days to add to the start date
		int randomDays = faker.number().numberBetween(5, (int) range);

		// Calculate the random date
		LocalDate randomDate = startDate.plusDays(randomDays);

		// Format the random date as yyyy/mm/dd
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String randomFormattedDate = randomDate.format(formatter);
		return randomFormattedDate;
	}

	public static String getCurrentDate(String dateFormat) {
		LocalDate currentDate = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String currentDateInStringFormat = currentDate.format(formatter);
		return currentDateInStringFormat;
	}

	public static String getCurrentDatePlusDays(String dateFormat, int day) {
		LocalDate currentDate = LocalDate.now();

		int randomDays = random.nextInt(day) + 1;

		// Calculate the new date by adding the random number of days
		LocalDate randomDate = currentDate.plusDays(randomDays);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String currentDatePlusDaysInStringFormat = randomDate.format(formatter);
		return currentDatePlusDaysInStringFormat;
	}

	public static String generateRandomValidProjectStartDate() {
		LocalDate currentDate = LocalDate.now();

		int randomDays = random.nextInt(15) + 1;

		// Calculate the new date by adding the random number of days
		LocalDate randomDate = currentDate.plusDays(randomDays);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fakeProjectStartDate = randomDate.format(formatter);
		return fakeProjectStartDate;
	}

	public static String generateRandomInvalidProjectEndDate() {
		LocalDate currentDate = LocalDate.now();

		int randomDays = random.nextInt(30) + 1;

		// Calculate the new date by adding the random number of days
		LocalDate randomDate = currentDate.minusDays(randomDays);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fakeProjectStartDate = randomDate.format(formatter);
		return fakeProjectStartDate;
	}

	public static String generateRandomProjectEndDate() {
		LocalDate currentDate = LocalDate.now();

		int interval = random.nextInt(4) + 1; // Generates a random number between 1 and 4

		// Calculate the end date based on the selected interval
		LocalDate endDate;
		switch (interval) {
		case 1:
			endDate = currentDate.plusMonths(3);
			break;
		case 2:
			endDate = currentDate.plusMonths(6);
			break;
		case 3:
			endDate = currentDate.plusMonths(9);
			break;
		case 4:
			endDate = currentDate.plusMonths(12);
			break;
		default:
			throw new IllegalStateException("Unexpected value: " + interval);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fakeProjectEndDate = endDate.format(formatter);
		return fakeProjectEndDate;
	}

	public static List<Integer> generateRandomArrayValues(List<Integer> givenList) {
		// Generate a random number between 2 and the size of the given list
		int subsetLength = random.nextInt(givenList.size()) + 1;

		// Shuffle the given list to randomize the elements
		Collections.shuffle(givenList, random);

		// Create a new list to hold the random subset
		List<Integer> randomSubsetList = givenList.subList(0, subsetLength);
//
//		// Convert the list to an array
//		Integer[] randomArray = randomSubsetList.toArray(new Integer[subsetLength]);

		return randomSubsetList;
	}

}
