import java.util.Scanner;

class SY2022bit056 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of registrations: ");
        int n = sc.nextInt();

        String[] regStr = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter registration " + (i + 1) + ": ");
            regStr[i] = sc.next();
        }

        sc.close();

        SY2022bit056 obj = new SY2022bit056();

        int validCount = obj.getValidRegistrationsCount(regStr);

        System.out.println("Valid registrations count: " + validCount);
    }

    public int getValidRegistrationsCount(String[] regStr) {
        int count = 0;

        for (String registration : regStr) {
            if (isValidRegistration(registration)) {
                count++;
            }
        }

        return count;
    }

    boolean isValidRegistration(String registration) {
        if (registration.length() != 10) {
            return false;
        }

        String year = registration.substring(0, 4);
        String branch = registration.substring(4, 7);
        String rollNumber = registration.substring(7);

        if (!year.equals("2020") && !year.equals("2021") && !year.equals("2022") && !year.equals("2023") && !year.equals("2024")) {
            return false;
        }

        if (!branch.equals("bit") && !branch.equals("bcs") && !branch.equals("bme")) {
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
