public class UglyNumbers {
    // This method returns true if n is an ugly number
    // according to the definition given in the problem statements
    static boolean isUgly(int n){
        while(n % 2 == 0){
            n /= 2;
        }
        while(n % 3 == 0){
            n /= 3;
        }
        while(n % 5 == 0){
            n /= 5;
        }
        return n == 1;
    }
    // Returns the kth ugly number.
    static int kthUglyNumber(int k){
        int cnt = 0;
        int i = 1;
        for(; cnt < k; i++){
            if(isUgly(i)){
                cnt++;
            }
        }
        return i-1;
    }
    public static void main(String[] args){
        // Tests
        System.out.println(isUgly(306)); // I am happy that my new dorm room number is not ugly :P

        System.out.println(kthUglyNumber(1));
        System.out.println(kthUglyNumber(3));
        System.out.println(kthUglyNumber(10));
        System.out.println(kthUglyNumber(306));
        System.out.println(kthUglyNumber(1000));
    }
}
