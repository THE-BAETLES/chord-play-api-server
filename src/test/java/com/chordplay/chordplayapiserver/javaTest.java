package com.chordplay.chordplayapiserver;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;

public class javaTest {


    public static void main(String[] args) {
//        for (CountryCode code : CountryCode.values())
//        {
//            System.out.format("%s, ", code);
//        }

// List all the language codes.
        for (LanguageCode code : LanguageCode.values())
        {
            System.out.format("%s, ", code);
        }
    }
}
