package com.dnocode.microsoft;

/**
 * Created by dino on 24/02/16.
 */
public class Translator implements ITranslator {


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
