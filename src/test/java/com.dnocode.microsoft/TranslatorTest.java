package com.dnocode.microsoft;


import org.junit.Test;

/**
 * Created by dino on 24/02/16.
 */
public class TranslatorTest {


    private final String urClientId="ar-dev";
    private final String urSecretId="0fKIk+qYwfQPbuIk0rv0nm7aUS+MUI7t2gGQlo1Zko0=";



    @Test
    public void testDetectLanguage(){


        Translator t=new Translator(urClientId,urSecretId);
        String response= t.detectLanguage("hello come stai tutto ben io si ora vado al mare").toBlocking().first();
        System.out.print(response);

    }

}
