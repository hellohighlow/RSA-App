import java.awt.*;
import java.math.BigInteger;
import java.util.*;
import static java.lang.System.*;

public class Runner
{
    public static void main(String[] args)throws NumberFormatException
    {
        gui j = new gui("fredrick");
        j.setSize(800, 600);

        //Do you want to be able to see the Frame?
        j.setVisible(true);
        j.setBackground(Color.black);
        Scanner scan = new Scanner(in);
        j.area.append("Would you like to encrypt or decrypt\n");
        String choice = scan.nextLine();
        if(choice.toLowerCase().equals("encrypt"))
        {
            out.println("Would you like to generate new keys?");
            choice = scan.nextLine();
            if(choice.toLowerCase().equals("yes"))
            {
                keyGen kg = new keyGen();
                out.println("Pick 2 primes");
                out.println("Prime number 1");
                int a = Integer.parseInt(scan.nextLine());
                out.println("Prime number 2");
                int b = Integer.parseInt(scan.nextLine());
                kg.setPrimes(a, b);
                kg.keys();
                out.println("What is your message?");
                String message = scan.nextLine();
                String ascii = kg.ascii(message);
                String encrypted = kg.encrypt(ascii);
                out.println("Here is your encrypted message");
                out.println(encrypted);
            }
            else
            {
                keyGen kg = new keyGen();
                out.println("What is your message?");
                String message = scan.nextLine();
                out.println("Enter e value");
                int e = Integer.parseInt(scan.nextLine());
                out.println("Enter mod value");
                int mod = Integer.parseInt(scan.nextLine());
                String ascii = kg.ascii(message);
                out.println(ascii);
                String encrypted = kg.encrypt(ascii, e, mod);
                out.println("Here is your encrypted message");
                out.println(encrypted);
            }

        }
        if(choice.toLowerCase().equals("decrypt"))
        {
            keyGen kg = new keyGen();
            out.println("Enter Message");
            String message = scan.nextLine();
            out.println("Enter d value");
            int d = Integer.parseInt(scan.nextLine());
            out.println("Enter mod value");
            int mod = Integer.parseInt(scan.nextLine());
            String output = kg.decrypt(message, d, mod);
            System.out.println(output);
            out.println(kg.asciiToEnglish(output));

        }
        //out.println("");
        //String decrypted = kg.decrypt(encrypted);
        //String result = kg.asciiToEnglish(decrypted);

    }
}