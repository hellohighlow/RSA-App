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
            j.area.append("Would you like to generate new keys?\n");
            choice = scan.nextLine();
            if(choice.toLowerCase().equals("yes"))
            {
                keyGen kg = new keyGen();
                j.area.append("Pick 2 primes\n");
                j.area.append("Prime number 1\n");
                int a = Integer.parseInt(scan.nextLine());
                j.area.append("Prime number 2\n");
                int b = Integer.parseInt(scan.nextLine());
                kg.setPrimes(a, b);
                kg.keys();
                j.area.append("What is your message?\n");
                String message = scan.nextLine();
                String ascii = kg.ascii(message);
                String encrypted = kg.encrypt(ascii);
                j.area.append("Here is your encrypted message\n");
                j.area.append(encrypted);
            }
            else
            {
                keyGen kg = new keyGen();
                j.area.append("What is your message?\n");
                String message = scan.nextLine();
                j.area.append("Enter e value\n");
                int e = Integer.parseInt(scan.nextLine());
                j.area.append("Enter mod value\n");
                int mod = Integer.parseInt(scan.nextLine());
                String ascii = kg.ascii(message);
                j.area.append(ascii);
                String encrypted = kg.encrypt(ascii, e, mod);
                j.area.append("Here is your encrypted message\n");
                j.area.append(encrypted);
            }

        }
        if(choice.toLowerCase().equals("decrypt"))
        {
            keyGen kg = new keyGen();
            j.area.append("Enter Message\n");
            String message = scan.nextLine();
            j.area.append("Enter d value\n");
            int d = Integer.parseInt(scan.nextLine());
            j.area.append("Enter mod value\n");
            int mod = Integer.parseInt(scan.nextLine());
            String output = kg.decrypt(message, d, mod);
            j.area.append(output);
            j.area.append(kg.asciiToEnglish(output));

        }
        //j.area.append("\n");
        //String decrypted = kg.decrypt(encrypted);
        //String result = kg.asciiToEnglish(decrypted);

    }
}