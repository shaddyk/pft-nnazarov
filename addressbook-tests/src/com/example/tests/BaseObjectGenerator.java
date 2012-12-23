package com.example.tests;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 23.12.12
 */
public class BaseObjectGenerator {

    public static String generateRandomString() {
        char[] alphabet = new char[]{'a','b','c','d','e','f','g','h','i','j','k',
                'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String string = "";
        Random rnd = new Random();
        int length = rnd.nextInt(10) + 1;

        for(int i = 0; i < length; i++) {
            string = string + alphabet[rnd.nextInt(alphabet.length)];
        }

        return string;
    }
}
