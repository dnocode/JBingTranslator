package com.dnocode.microsoft;


import com.dnocode.microsoft.enumerator.Language;
import org.junit.Assert;
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
        Language response= t
                .detectLanguage("hello how are you fine thanks")
                .doOnError((e)-> System.out.print("errore ci fu"+e.toString())).toBlocking().first();
        Assert.assertTrue("errror on language detection",response==Language.ITALIAN);

    }



    @Test
   public  void testTranslate(){


        Translator t=new Translator(urClientId,urSecretId);
        String response= t.translate("ciao come stai?", Language.ENGLISH)
                .toBlocking()
                .first();
        Assert.assertTrue("error on translation","hello how are you".equals(response));

    }

}
