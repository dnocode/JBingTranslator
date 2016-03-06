package com.dnocode.microsoft;


import com.dnocode.jhug.net.Http;

/**
 * Created by dino on 24/02/16.
 */
public abstract  class ATranslator implements ITranslator {


    protected final String BING_TRANSLATOR_ENDPOINT="http://api.microsofttranslator.com/v2/Http.svc/";
    protected final String HEADER_AUTH_PREFIX="Bearer ";
    protected final String HEADER_KEY="authorization";
    protected Http http;









}
