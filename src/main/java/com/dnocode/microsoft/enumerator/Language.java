package com.dnocode.microsoft.enumerator;


import java.util.Optional;
import java.util.stream.Stream;

public enum Language {

      ARABIC("ar","arabic"),
      BOSNIAN("bs-Latn","bosnian"),
      BULGARIAN("bg" , "bulgarian"),
      CATALAN("ca","catalan"),
      CHINESE_SIMPLIFIED("zh-CHS","chinese simplified"),
      CHINESE_TRADITIONAL("zh-CHT","chinese traditional"),
      CROATIAN("hr","Croatian"),
      CZECH("cs","czech"),
      DANISH("da","danish"),
      DUTCH("nl","dutch"),
      ENGLISH("en" ,"english"),
      ESTONIAN("et", "estonian"),
      FINNISH("fi","finnish"),
      FRENCH("fr","french"),
      GERMAN("de","german"),
      GREEK("el","greek"),
      HAITIAN_CREOLE("ht","Haitian Creole"),
      HEBREW("he","hebrew"),
      HINDI("hi","hindi"),
      HMONG_DAW("mww","hmong daw"),
      HUNGARIAN("hu","hungarian"),
      INDIAN("id","indian"),
      ITALIAN("it","italian"),
      JAPANESE("ja","japanese"),
      SW("sw","kiswahili"),
      TLH("tlh","Klingon"),
      TLH_QAAK("tlh-Qaak","klingon (pIqaD)"),
      KOREAN("ko","korean"),
      LV("lv","latvian"),
      LITHUANIAN("lt","lithuanian"),
      MALAY("ms","malay"),
      MT("mt","maltese"),
      NO("no","norwegian"),
      FA("fa","persian"),
      PL("pl","polish"),
      PT("pt","portuguese"),
      OTQ("otq","Querétaro Otomi"),
      ROMANIAN("ro","romanian"),
      RUSSIAN("ru","russian"),
      SERBIAN_CYRILLIC("sr-Cyrl","Serbian (Cyrillic)"),
      SERBIAN_LATIN("sr-Latn","Serbian (Latin)"),
      SLOVAK("sk","slovak"),
      SLOVENIAN("sl","slovenian"),
      SPANISH("es","spanish"),
      SWEDISH("sv","swedish"),
      THAI("th","thai"),
      TURKISH("tr","turkish"),
      UKRAINIAN("uk","ukrainian"),
      URDU("ur","urdu"),
      VIETNAMESE("vi","vietnamese"),
      WELSH("cy","welsh"),
      YUCATEC_MAYA("yua"," yucatec maya");

    private final String cod;

    private final String language;


    Language(String cod, String language){
            this.cod = cod;
            this.language = language;
        }


    public String getCod(){return cod;}

    public static Optional<Language> getByCode(String cod){

      return   Stream
              .of(values())
              .filter(language->language.getCod().toLowerCase().equals(cod.toLowerCase()))
              .findFirst();
    }


}
