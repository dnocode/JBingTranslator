package com.dnocode.microsoft;


import com.dnocode.microsoft.enumerator.Language;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;


/**
 * Created by dino on 24/02/16.
 */
public class TranslatorTest {


    private final String urClientId="put ur key here";
    private final String urSecretId="put ur secret here";


    @Test
    public void testDetectLanguage(){

        Translator t=new Translator(urClientId,urSecretId);
        Language response= t
                .detectLanguage("hello how are you fine thanks")
                .doOnError((e)-> System.out.print("errore ci fu"+e.toString())).toBlocking().first();
        Assert.assertTrue("errror on language detection",response==Language.ENGLISH);

    }


   @Test
   public  void testTranslate(){

        Translator t=new Translator(urClientId,urSecretId);
        String response= t.translate("ciao come stai?", Language.ENGLISH)
                .toBlocking()
                .first();
        Assert.assertTrue("error on translation","Hi how are you?".equals(response));

    }


    @Test
    public void testTranslationLimit(){

           Translator t=new Translator(urClientId,urSecretId);
            String sixCharacters="albero";
           t.setCharactersLimit(12);

           String t1= t.translate(sixCharacters,Language.ENGLISH).toBlocking().first();

           String t2=t.translate(sixCharacters,Language.ENGLISH).toBlocking().first();

           Clock clock = Clock.fixed(Instant.now().plus(40, ChronoUnit.DAYS), ZoneId.systemDefault());

           t.translate(sixCharacters,Language.ENGLISH)
                    .toBlocking()
                    .subscribe((translation)->{
                        Assert.assertFalse("ha effettuato la traduzione",translation.length()==4);
                    },(e)->{
                        System.out.print("limit is riched ");
                        Assert.assertTrue("translation failed",e!=null);
                    });



        t.setClock(clock);


        t.translate(sixCharacters,Language.ENGLISH)
                .toBlocking()
                .subscribe((translation)->{
                    Assert.assertTrue("ha effettuato la traduzione",translation.length()==4);
                });

        String t3= t.translate(sixCharacters,Language.ENGLISH).toBlocking().first();




        t.translate(sixCharacters,Language.ENGLISH)
                .toBlocking()
                .subscribe((translation)->{
                    Assert.assertFalse("ha effettuato la traduzione",translation.length()==4);
                },(e)->{
                    System.out.print("limit is riched ");
                    Assert.assertTrue("translation failed again",e!=null);
                });



    }
}
