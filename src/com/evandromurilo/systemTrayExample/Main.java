package com.evandromurilo.systemTrayExample;

/**
 * Created by Evandro Murilo on 6/7/17.
 */

import java.io.File;
import java.io.IOException;

public class Main {
    private static MyFrame frame;

    public static void main(String[] args) {
        File file = new File("running");
        boolean createdFile;

        try {
            createdFile = file.createNewFile();
            if (createdFile) file.deleteOnExit();
        } catch (IOException e) {
            createdFile = false;
        }

        if (createdFile) frame = new MyFrame();
    }
}
