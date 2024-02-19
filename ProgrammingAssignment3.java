import java.util.Random;
class PrimeCount{
    static int primeCount=0;
    static int nonPrimeCount=0;
    public static void main(String[] args) {
        int[] arr = getArray();
        long startTime1 = System.currentTimeMillis();
        countPrimeNonPrimeByIfElseApproch(arr);
        long endTime1 = System.currentTimeMillis();
        System.out.println("Approch1: positive condidtion is in if block");
        System.out.println("Prime Count: "+ primeCount);
        System.out.println("NonPrime Count: "+ nonPrimeCount);
        System.out.println("Time Taken(in ms): "+ (endTime1-startTime1));
        primeCount=0;
        nonPrimeCount=0;
        long startTime2 = System.currentTimeMillis();
        countPrimeNonPrimeByElseIfApproch(arr);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Approch2: negative condidtion is in if block");
        System.out.println("Prime Count: "+ primeCount);
        System.out.println("NonPrime Count: "+ nonPrimeCount);
        System.out.println("Time Taken(in mc): "+ (endTime2-startTime2));
    }
    public static int getDigit()
    {
        Random random = new Random();
        int randomdigit = random.nextInt(10);
        return randomdigit;
    }
    public static int getSevenDigits()
    {
        int sevenDigitNumber = 0;
        int multipal = 1;
        for(int i=0; i<7; i++){
            int randonDigit = getDigit();
            sevenDigitNumber = sevenDigitNumber + randonDigit*multipal;
            multipal*=10;
        }
        return sevenDigitNumber;
    }
    public static int[] getArray()
    {
        int[] arr = new int[100000];
        for(int i=0; i<100000; i++){
            arr[i] = getSevenDigits();
        }
        return arr;
    }
    public static boolean isPrime(int number)
    {
        if(number<2)
            return false;
        if(number==2||number==3)
            return true;
        if(number%2==0||number%3==0)
            return false;
        for(int i=5; i<Math.sqrt(number)+1; i+=6){
            if(number%i==0||number%(i+2)==0)
                return false;
        }
        return true;
    }
    public static void countPrimeNonPrimeByIfElseApproch(int arr[])
    {
        for(int val: arr)
        {
            if(val==1)
                continue;
            if(isPrime(val)){
                primeCount++;
            }
            else
                nonPrimeCount++;
        }
    }
    public static void countPrimeNonPrimeByElseIfApproch(int arr[])
    {
        for(int val : arr)
        {
            if(val==1)
                continue;
            if(!isPrime(val)){
                nonPrimeCount++;
            }
            else
                primeCount++;
        }
    }
}