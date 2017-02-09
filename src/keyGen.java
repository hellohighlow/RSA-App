import java.math.*;
import java.util.*;

public class keyGen
{
    private int p;
    private int n;
    private int q;
    private int e;
    private int d;
    public keyGen()
    {

    }
    //Sets Primes...
    public void setPrimes(int a, int b)
    {
        if (isPrime(a) && isPrime(b))
        {
            p = a;
            q = b;
        }
        if(!isPrime(a))
        {
            System.out.println(a + " isn't prime!");
            System.exit(0);
        }
        if(!isPrime(b))
        {
            System.out.println(b + " isn't prime!");
            System.exit(0);
        }
    }
    public boolean isPrime(int number)
    {
        //time saver
        if (number == 2 || number == 3)
        {
            return true;
        }
        //if divisible by 2 it isn't prime
        if (number % 2 == 0)
        {
            return false;
        }
        //stuffs
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 3; i < sqrt; i += 2)
        {
            if (number % i == 0)
            {
                return false;
            }
        }
        return true;
    }
    //generates the keys
    public void keys()
    {
        n = p*q;
        int r = (p-1)*(q-1);
        int fact1 = 0;
        int fact2 = 0;
        Fredrick:
        for(int i = 2; i < r/2; i++)
        {
            double tmp = (double)r;
            tmp *= i;
            tmp++;
            //System.out.println(tmp);
            for(int y = 2; y < r/2; y++)
            {
                //determines factors
                if(!isPrime((int)tmp) && tmp % y == 0)
                {
                    fact1 = (int)tmp/y;
                    fact2 = (int)tmp/fact1;
                    System.out.println(tmp);
                    break Fredrick;
                }
            }
        }
        System.out.println("Your public key is ("+n+","+fact1+")");
        e = fact1;
        System.out.println("Your private key is ("+n+","+fact2+")");
        d = fact2;
    }
    //ascii conversion
    public String ascii(String m)
    {
        try
        {
            String asciiChar = "";
            for (int x = 0; x < m.length(); x++)
            {
                char cheese = m.charAt(x);
                //converts to char to ascii
                int b = (int) cheese;
                String c = "" + b;
                String d = "";
                //System.out.println(c.length());
                //if less than 3 pad with 0
                if (c.length() < 3)
                {
                    //System.out.println("<3");
                    for (int i = c.length(); i < 3; i++)
                    {
                        d += "0" + c;
                        //System.out.println(d);
                    }
                } else d += c;
                //add the character(in ascii to the string
                asciiChar = asciiChar + d;
            }
            System.out.println(asciiChar);
            return asciiChar;
        }catch(NumberFormatException e)
        {
            e.printStackTrace();
            return "";
        }
    }
    public String encrypt(String m, int pubKey, int mod)
    {
        String output = "";
        List message = new ArrayList<Integer>();
        for (int x = 0; x < m.length(); x += 3)
        {
            //converts to manageable string
            Long letter = Long.parseLong(m.substring(x, x + 3));
            Long result = (long)1;
            int y = pubKey;
            while (y > 0)
            {
                //m = c^d % n
                result = (result * letter) % mod;
                y--;
            }
            message.add(result);
        }
        //pad it once more
        for (Object x : message)
        {
            String d = "" + x;
            if (d.length() < 3)
            {
                while (d.length() < 3)
                    d = "0" + d;
            }
            d += "a";
            output += d;
        }
        return output;
    }
    public String encrypt(String m)
    {
        String output = "";
        List message = new ArrayList<Integer>();
        for (int x = 0; x < m.length(); x += 3)
        {
            //manageable ascii
            Long letter = Long.parseLong(m.substring(x, x + 3));
            Long result = (long)1;
            int y = e;
            while (y > 0)
            {
                //c = m^e % n
                result = (result * letter) % n;
                y--;
            }
            message.add(result);
        }
        for (Object x : message)
        {
            String d = "" + x;
            if (d.length() < 3) {
                while (d.length() < 3)
                    //padding
                    d = "0" + d;
            }
            d += "a";
            output += d;
        }
        return output;
    }
    public String decrypt(String m, int pK, int mod)
    {

        String output ="";
        List message = new ArrayList<Integer>();
        String[] chars =m.split("a");
        //separates each encrypted character
        for(int x = 0; x < chars.length; x++)
        {
            chars[x]+="\b";
            //System.out.println(chars[x]);
            Long letter = Long.parseLong(chars[x].trim());
            Long result = (long) 1;
            int y = pK;
            while (y > 0)
            {
                //m = c^d % n
                result = (result * letter) % mod;
                y--;
            }
            message.add(result);
        }
        for(Object x: message)
        {
            String d = ""+x;
            //padding
            if(d.length()<3)
            {
                while(d.length() < 3)
                    d = "0"+d;
            }
            output += d;
        }
        return output;
    }
    public String asciiToEnglish(String m)
    {
        String message = "";
        for(int x = 0; x < m.length(); x += 3)
        {
            int letter = Integer.parseInt(m.substring(x,x+3));
            //ascii to anglais
            message = message + Character.toString((char)letter);
        }

        return message;
    }
    //spaghetti code from https://stackoverflow.com/questions/4009198/java-get-greatest-common-divisor TY Tony Enis
    //never actually used... whats the point?!?
    private static int gcdThing(int a, int b) {
        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }
}
