package com.dnocode.microsoft;


import com.dnocode.jhug.net.Req;
import com.dnocode.microsoft.domain.BingoScope;
import com.sun.deploy.net.URLEncoder;
import rx.Observable;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by dino on 24/02/16.
 */
public class Translator extends ATranslator {

    private final String clientId;
    private final String secretId;

    public Translator(String clientId, String secretId){

       this.clientId=clientId;
        this.secretId=secretId;
    }

   @Override
    public Observable<String> detectLanguage(String textToTranslate) {
    final String method="Detect";
    return  Observable.create(mainSub ->{
       Bing.auth(clientId,secretId).token(BingoScope.bingTranslator).subscribe(
               (token)->{
                   try {
                       Req.New
                      .toUri(BING_TRANSLATOR_ENDPOINT+method).newParam("text", URLEncoder.encode(textToTranslate,"UTF-8"))

                      .newHeader(HEADER_KEY,HEADER_AUTH_PREFIX+token.accessToken )
                               .newHeader("Cache-Control","no-cache")
                               .create().<String>fire()
                               .subscribe(mainSub::onNext,mainSub::onError,mainSub::onCompleted);
                   } catch (UnsupportedEncodingException e) {
                      mainSub.onError(e);
                   }
               },mainSub::onError);
    }
    );



    }

    @Override
    public Observable<String> translate(String text) {

        final String method="Detect";
        return null;
    }


   /* private static void DetectMethod(string authToken)
    {
        Console.WriteLine("Enter Text to detect language:");
        string textToDetect = Console.ReadLine();
        //Keep appId parameter blank as we are sending access token in authorization header.
        string uri = "http://api.microsofttranslator.com/v2/Http.svc/Detect?text=" + textToDetect;
        HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(uri);
        httpWebRequest.Headers.Add("Authorization", authToken);
        WebResponse response = null;
        try
        {
            response = httpWebRequest.GetResponse();
            using (Stream stream = response.GetResponseStream())
            {
                System.Runtime.Serialization.DataContractSerializer dcs = new System.Runtime.Serialization.DataContractSerializer(Type.GetType("System.String"));
                string languageDetected = (string)dcs.ReadObject(stream);
                Console.WriteLine(string.Format("Language detected:{0}", languageDetected));
                Console.WriteLine("Press any key to continue...");
                Console.ReadKey(true);
            }
        }
        catch
        {
            throw;
        }
        finally
        {
            if (response != null)
            {
                response.Close();
                response = null;
            }
        }
*/



}
