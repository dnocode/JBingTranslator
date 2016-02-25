package com.dnocode.microsoft;


import rx.Observable;

/**
 * Created by dino on 24/02/16.
 */
public interface ITranslator {


     Observable<String> detectLanguage(String textToTranslate);


      Observable<String>  translate(String text);




}
