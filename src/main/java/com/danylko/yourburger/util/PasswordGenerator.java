package com.danylko.yourburger.util;

public class PasswordGenerator {

    public static String ganeratePassword() {
        StringBuilder password = new StringBuilder();
        int randomNum;
        char randomChar;
        for (int i = 0; i < 10; i++ ) {
            randomNum = (int) (Math.random() * 3);
            if (randomNum == 0) {
                randomChar = (char) ((Math.random() * 10) + 48);
                password.append(randomChar);
            }
            if (randomNum == 1) {
                randomChar = (char) ((Math.random() * 26) + 65);
                password.append(randomChar);
            }
            if (randomNum == 2) {
                randomChar = (char) ((Math.random() * 26) + 97);
                password.append(randomChar);
            }
        }
        return  password.toString();
    }
}
