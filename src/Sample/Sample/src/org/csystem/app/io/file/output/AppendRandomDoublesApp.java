package org.csystem.app.io.file.output;

import org.csystem.util.console.Console;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

import static org.csystem.util.console.commandline.CommandLineArgsUtil.checkLengthEquals;

public class AppendRandomDoublesApp {
    private static int getCount(String countStr)
    {
        return Integer.parseInt(countStr);
    }

    private static void doAppend(String path, int count)
    {
        try (FileOutputStream fos = new FileOutputStream(path, true)) {
            Random random = new Random();

            while (count-- > 0) {
                double val = random.nextDouble(100);
                Console.writeLine("%f", val);
                byte [] data = ByteBuffer.allocate(Double.BYTES).putDouble(val).array();

                fos.write(data);
            }

            Console.writeLine();
        }
        catch (FileNotFoundException ex) {
            Console.writeErrLine("Problem occurred while opening the file:%s", ex.getMessage());
        }
        catch (SecurityException ex) {
            Console.writeErrLine("Security occurred while opening the file:%s", ex.getMessage());
        }
        catch (IOException ex) {
            Console.writeErrLine("IO problem occurred while opening the file:%s", ex.getMessage());
        }
    }

    public static void run(String [] args)
    {
        checkLengthEquals(args.length, 2, "Wrong number of arguments!...");

        try {
            doAppend(args[0], getCount(args[1]));
        }
        catch (NumberFormatException ignore) {
            Console.writeErrLine("Invalid count value!...");
        }
    }

    public static void main(String[] args)
    {
        run(args);
    }
}
