package com.dnocode.microsoft;


import com.dnocode.microsoft.enumerator.Language;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by dino on 24/02/16.
 */
public class TranslatorTest {


    private final String urClientId="ar-dev";
    private final String urSecretId="0fKIk+qYwfQPbuIk0rv0nm7aUS+MUI7t2gGQlo1Zko0=";


    @Test
    public void testDetectLanguage(){


        Translator t=new Translator(urClientId,urSecretId);
        Language response= t
                .detectLanguage("hello how are you fine thanks")
                .doOnError((e)-> System.out.print("errore ci fu"+e.toString())).toBlocking().first();
        System.out.print(response);

    }



    @Test
   public  void testTranslate(){


        Translator t=new Translator(urClientId,urSecretId);
        String response= t.translate("hello come stai tutto ben io si ora vado al mare", Language.ENGLISH).toBlocking().first();
        System.out.print(response);

    }

}
