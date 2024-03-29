package com.practice;

/*
This jar is used in the ci/cd of the TOPP App

ASSUMPTIONS - this tool assumes the dev folder
is installed on the desktop - or some other
specified location

command line args -
    [0] - latest version jar to copy
    [1] - copy location for latest jar
    [2] - config command
    [3] - config path
    [4] - file name for git-add <untrackedFile>
 */

// TODO - make simple GUI

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        var usageMessage = " TOPP App CI/CD Dev Tool Usage:\n" +
                "   [0] - latestPath \n" +
                "   [1] - copyLocation\n" +
                "   [2] - configFilePath";

        try {

            if (args[0].compareTo("help") == 0 ||
                    args.length != 3) {

                System.out.println(usageMessage);

            } else {
                var latestPath = args[0];
                var copyLocation = args[1];
                var configFilePath = args[2];

                System.out.println(" Copying File - " + latestPath + " to - " + copyLocation);

                try (var latestFile = new FileInputStream(latestPath);
                     var copy = new FileOutputStream(copyLocation);
                     var configFile = new FileOutputStream(configFilePath)) {

                    var commandOne = (int) '0';
                    var commandTwo = (int) '0';
                    int byteRead;

                    do {
                        byteRead = latestFile.read();

                        if (byteRead != -1) {
                            copy.write(byteRead);
                        }

                    } while (byteRead != -1);

                    configFile.write(commandOne);
                    configFile.write(commandTwo);
                } catch (IOException ignore) {
                    System.out.println(" ERROR: Could Not Copy File");
                }
            }
        } catch(ArrayIndexOutOfBoundsException ignore) {
            System.out.println(usageMessage);
        }
    }
}
