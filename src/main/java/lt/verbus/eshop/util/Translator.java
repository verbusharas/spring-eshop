package lt.verbus.eshop.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {


    private static ResourceBundleMessageSource resourceBundleMessageSource;

    public Translator(ResourceBundleMessageSource resourceBundleMessageSource) {
        Translator.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    /**
     * Gets the message from messages.properties file based on correct locale
     * Object ...args optionally takes parameters to message properties file (?~)
     */

    public static String getMessage(String code, Object ...args) {
        Locale locale = LocaleContextHolder.getLocale();
        return resourceBundleMessageSource.getMessage(code, args, locale);
    }

}
