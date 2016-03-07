package com.dnocode.microsoft;


import java.time.Clock;
import java.time.LocalDate;
import java.util.HashMap;


public abstract  class ATranslator implements ITranslator {


    protected final String BING_TRANSLATOR_ENDPOINT="http://api.microsofttranslator.com/v2/Http.svc/";
    protected final String HEADER_AUTH_PREFIX="Bearer ";
    protected final String HEADER_KEY="authorization";
    protected int charactersLimit=-1;
    protected final HashMap<Integer,Integer> counter;
    protected final String clientId;
    protected final String secretId;
    private Clock clock=Clock.systemDefaultZone();
    private LimitController limitController=new LimitController();


    public ATranslator(String clientId, String secretId){

        this.clientId=clientId;
        this.secretId=secretId;
        counter=new HashMap<>(1);
    }


    public void setClock(Clock clock) {


        this.clock = clock;
    }


    public void setCharactersLimit(int limit) {
        this.charactersLimit = limit;
    }


    protected synchronized LimitController limits(){  return limitController;}

    protected class LimitController
    {
        protected boolean isCharactersLimitExceedeed (String newInputText){
        final int month = LocalDate.now(clock).getMonthValue();
        return charactersLimit > 0 && (counter.getOrDefault(month, 0) + newInputText.length()) > charactersLimit;
    }

    protected void counterIncrement(String translation) {

        final int month = LocalDate.now(clock).getMonthValue();
        if (charactersLimit > 0 && translation != null) {
            if (counter.size() > 0 && counter.keySet().iterator().next() != month) {
                counter.clear();
            }
            counter.putIfAbsent(month, 0);
            counter.computeIfPresent(month, (key, value) -> value + translation.length());

        }
    }

}

}
