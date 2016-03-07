package com.dnocode.microsoft;


import com.dnocode.jhug.net.Req;
import com.dnocode.microsoft.domain.BingoScope;
import com.dnocode.microsoft.enumerator.Language;
import com.sun.deploy.net.URLEncoder;
import rx.Observable;

import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.Optional;

/**
 * Created by dino on 24/02/16.
 */
public class Translator extends ATranslator {




    public Translator(String clientId, String secretId){

         super(clientId,secretId);
    }

   @Override
    public Observable<Language> detectLanguage(String textToTranslate) {
    final String method="Detect";
    return  Observable.create(mainSub -> Bing.auth(clientId,secretId).token(BingoScope.bingTranslator).subscribe(
            (token)->{
                try {
                    Req.New
                   .toUri(BING_TRANSLATOR_ENDPOINT+method)
                            .newParam("text", URLEncoder.encode(textToTranslate,"UTF-8"))
                            .newParam("contentType","json")
                             .newHeader(HEADER_KEY,HEADER_AUTH_PREFIX+token.accessToken )
                            .newHeader("Cache-Control","no-cache")
                            .newHeader("Content-Type","application/json")
                            .newHeader("Accept","application/json")
                            .create().<String>fire()
                            .map((xml)-> xml.substring(xml.indexOf("/\">")+"/\">".length(),xml.indexOf("</string>")))
                            .map(Language::getByCode)
                            .map(Optional::get)
                            .subscribe(mainSub::onNext,mainSub::onError,mainSub::onCompleted);
                } catch (UnsupportedEncodingException e) {
                   mainSub.onError(e);
                }
            },mainSub::onError)
    );



    }



    public Observable<String> translate(String text, Language to) {

        return translate(text,null,to,null,null);
    }


    @Override
    public Observable<String> translate(String text, Language from, Language to, String contentType, String category) {

        if(text==null){new InvalidParameterException("text parameter is required");}
       if(limits().isCharactersLimitExceedeed(text)){
           try {
               throw new Exception("month limit exceeded!!!");
           } catch (Exception e) {
               return Observable.error(e);
           }
       }

        final String method="Translate";
        return    Observable.<String>create(mainSub -> Bing.auth(clientId,secretId).token(BingoScope.bingTranslator).subscribe(
                (token)->{
                    try {
                        Req.New req = Req.New
                                .toUri(BING_TRANSLATOR_ENDPOINT + method)
                                .newParam("text", URLEncoder.encode(text, "UTF-8"))
                                .newParam("to", URLEncoder.encode(to.getCod(), "UTF-8"))
                                .newHeader(HEADER_KEY, HEADER_AUTH_PREFIX + token.accessToken)
                                .newHeader("Cache-Control", "no-cache");

                        if(Optional.ofNullable(from).isPresent()){
                            req.newParam("from", URLEncoder.encode(from.getCod(), "UTF-8"));
                        }
                        if(Optional.ofNullable(contentType).isPresent()){
                            req.newParam("contentType", URLEncoder.encode(contentType, "UTF-8"));
                        }
                        if(Optional.ofNullable(category).isPresent()){
                            req .newParam("category", URLEncoder.encode(category, "UTF-8"));
                        }
                        req.create().<String>fire()
                                .map((xml)-> xml.substring(xml.indexOf("/\">")+"/\">".length(),xml.indexOf("</string>")))
                                .subscribe(mainSub::onNext,mainSub::onError,mainSub::onCompleted);
                    } catch (UnsupportedEncodingException e) {
                        mainSub.onError(e);
                    }
                },mainSub::onError)
        ).doOnNext(translation->limits().counterIncrement(text));


    }



}
