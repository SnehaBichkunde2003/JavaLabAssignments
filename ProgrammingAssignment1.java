import java.util.Scanner;

class SY2022bit056 {
    public static void main(String[] args) {
        String regStr[] = {"2020bit001", "2021bcs165", "2022bme053",
                "2023bch502", "2023BIT503", "2025bce423", "2023bce42"};
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

        int convertYear = Integer.parseInt(year);
        if(2000<= convertYear&& convertYear>=2024){
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
