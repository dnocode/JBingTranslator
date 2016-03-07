package com.dnocode.microsoft;


import com.dnocode.microsoft.enumerator.Language;
import rx.Observable;

/**
 * Created by dino on 24/02/16.
 */
public interface ITranslator {


     Observable<Language> detectLanguage(String textToTranslate);

      Observable<String>  translate(String text, Language from, Language to, String contentType, String format);




}
