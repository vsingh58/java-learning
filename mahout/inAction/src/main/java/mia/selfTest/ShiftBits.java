package mia.selfTest;

import sun.security.util.BitArray;

import java.nio.charset.Charset;
import java.util.BitSet;

/**
 * Created by zhishan on 9/24/14.
 */
public class ShiftBits {
    public static byte[] longSeedtoBytes(long seed) {
        byte[] seedBytes = new byte[16];
        seedBytes[0] = (byte) (seed >>> 56);
        seedBytes[1] = (byte) (seed >>> 48);
        seedBytes[2] = (byte) (seed >>> 40);
        seedBytes[3] = (byte) (seed >>> 32);
        seedBytes[4] = (byte) (seed >>> 24);
        seedBytes[5] = (byte) (seed >>> 16);
        seedBytes[6] = (byte) (seed >>> 8);
        seedBytes[7] = (byte) seed;
        System.arraycopy(seedBytes, 0, seedBytes, 8, 8);
        return seedBytes;
    }

    public static long seedBytesToLong(byte[] seed) {
        long result = 0L;
        for (int i = 0; i < 8; i++) {
            result |= (seed[i] & 0xFFL) << (long) (8 * (7 - i));
        }
        return result;
    }


    public static void main(String[] args) {
        long seed = 256l;
        byte[] seedBytes = longSeedtoBytes(seed);
        if (seed  == seedBytesToLong(seedBytes)) {

            System.out.println("Yes" + new String(seedBytes, Charset.forName("UTF-8")).toString());
        } else {
            System.out.println("NO");
        }
        System.out.println(0xFFL); //output is 255
        System.out.println(new Integer(0xFF)); //output is 255
        System.out.println(Integer.parseInt("FF", 16)); //output is 255
        System.out.println(0x0FL); //output is 15

        System.out.println(Character.digit('2', 10));
        System.out.println(Character.digit('0', 10));
        System.out.println(Character.digit('9', 10));
        System.out.println(Character.digit('a', 10));
        System.out.println(Character.digit('-', 10));
        System.out.println(Character.getNumericValue('a'));
        System.out.println(Character.getNumericValue('1'));
    }

    // from public final class RandomUtils  class
    /**
     * <p>
     * Finds smallest prime p such that p is greater than or equal to n.
     * </p>
     */
    public static int nextPrime(int n) {
        if (n <= 2) {
            return 2;
        }
        // Make sure the number is odd. Is this too clever?
        n |= 0x1;
        // There is no problem with overflow since Integer.MAX_INT is prime, as it happens
        while (isNotPrime(n)) {
            n += 2;
        }
        return n;
    }

    /** @return {@code true} iff n is not a prime */
    public static boolean isNotPrime(int n) {
        if (n < 2 || (n & 0x1) == 0) { // < 2 or even
            return n != 2;
        }
        int max = 1 + (int) Math.sqrt(n);
        for (int d = 3; d <= max; d += 2) {
            if (n % d == 0) {
                return true;
            }
        }
        return false;
    }

}
