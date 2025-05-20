package com.wallet.transaction.common.components;

import com.wallet.transaction.common.logger.TransactionServiceLogger;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Component("messageSource")
@RequiredArgsConstructor
public class MessageSourceComponent extends AbstractMessageSource {
    private final TransactionServiceLogger logger;
    private static final String FILE_NAME_FORMAT = "i18n/message_%s.properties";
    private final ConcurrentHashMap<String, Properties> messageCache = new ConcurrentHashMap<>();


    @Override
    protected MessageFormat resolveCode(final String key, final Locale locale) {

        final String targetMessage = getTargetLocalMessage(key, locale.getLanguage());

        if (StringUtils.isBlank(targetMessage)) {
            logger.trace("\n\t-------Message Not found--------" + key + "\n");
            return new MessageFormat("Could not process Message.", locale);
        }

        return new MessageFormat(targetMessage, locale);
    }

    private String getTargetLocalMessage(final String key, final String language) {
        final String fileName = resolveMessageFileName(language);

        final Properties properties = messageCache.computeIfAbsent(fileName, this::loadProperties);
        return properties.getProperty(key);
    }

    private Properties loadProperties(final String fileName) {
        final Properties properties = new Properties();
        try (final InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            if (input != null)
                properties.load(input);

        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return properties;
    }

    private String resolveMessageFileName(final String language) {
        return String.format(FILE_NAME_FORMAT, language);
    }
}
