import java.util.Random;

class RegNumberManager {
    public static void main(String[] args) {
        int[] sampleCounts = {20000, 50000, 60000};

        System.out.println("Sample Count\tApproach 1 (ms)\tApproach 2 (ms)\tApproach 3 (ms)");

        for (int count : sampleCounts) {
            String[] regStr = randomRegNumbersGenerator(count);

            long startTime1 = System.currentTimeMillis();
            int validCount1 = countValidRegsWithParseInt(regStr);
            long endTime1 = System.currentTimeMillis();

            long startTime2 = System.currentTimeMillis();
            int validCount2 = countValidRegsWithCharacterIsDigit(regStr);
            long endTime2 = System.currentTimeMillis();

            long startTime3 = System.currentTimeMillis();
            int validCount3 = countValidRegsWithCharacterGetNumericValue(regStr);
            long endTime3 = System.currentTimeMillis();

            System.out.println(String.format("%d\t\t%d\t\t\t%d\t\t\t%d", count, endTime1 - startTime1, endTime2 - startTime2, endTime3 - startTime3));
        }

    }

    public static String[] randomRegNumbersGenerator(int count) {
        String[] regNumbers = new String[count];
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int year = 2000 + random.nextInt(25);
            String branch = "bit";
            int rollNumber = random.nextInt(1000);
            regNumbers[i] = String.format("%04d%s%03d", year, branch, rollNumber);
        }

        return regNumbers;
    }

    public static int countValidRegsWithParseInt(String[] regStr) {
        int count = 0;

        for (String registration : regStr) {
            String lastThreeDigitsStr = registration.substring(7);
            int lastThreeDigits = Integer.parseInt(lastThreeDigitsStr);

            if (lastThreeDigits < 50 && isValidRegistration(registration)) {
                count++;
            }
        }

        return count;
    }

    public static int countValidRegsWithCharacterIsDigit(String[] regStr) {
        int count = 0;

        for (String registration : regStr) {
            String lastThreeDigitsStr = registration.substring(7);

            if (areLastThreeDigitsLessThan50WithCharacterIsDigit(lastThreeDigitsStr) && isValidRegistration(registration)) {
                count++;
            }
        }

        return count;
    }

    public static int countValidRegsWithCharacterGetNumericValue(String[] regStr) {
        int count = 0;

        for (String registration : regStr) {
            String lastThreeDigitsStr = registration.substring(7);

            if (areLastThreeDigitsLessThan50WithCharacterGetNumericValue(lastThreeDigitsStr) && isValidRegistration(registration)) {
                count++;
            }
        }

        return count;
    }

    public static boolean areLastThreeDigitsLessThan50WithCharacterIsDigit(String lastThreeDigitsStr) {
        for (char c : lastThreeDigitsStr.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        int lastThreeDigits = Integer.parseInt(lastThreeDigitsStr);
        return lastThreeDigits < 50;
    }

    public static boolean areLastThreeDigitsLessThan50WithCharacterGetNumericValue(String lastThreeDigitsStr) {
        for (char c : lastThreeDigitsStr.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        int lastThreeDigits = 0;
        for (int i = 0; i < lastThreeDigitsStr.length(); i++) {
            lastThreeDigits = lastThreeDigits * 10 + Character.getNumericValue(lastThreeDigitsStr.charAt(i));
        }
        return lastThreeDigits < 50;
    }

    public static boolean isValidRegistration(String registration) {
        if (registration.length() != 10) {
            return false;
        }

        String year = registration.substring(0, 4);
        String branch = registration.substring(4, 7);
        String rollNumber = registration.substring(7);

        int convertYear = Integer.parseInt(year);
        if (!(2000 <= convertYear && convertYear <= 2024)) {
            return false;
        }

        if (!branch.equals("bit") && !branch.equals("bcs") && !branch.equals("bme") && !branch.equals("bch")) {
            return false;
        }

        for (char c : rollNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
