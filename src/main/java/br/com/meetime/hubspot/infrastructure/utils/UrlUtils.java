package br.com.meetime.hubspot.infrastructure.utils;

public class UrlUtils {

    public static String decodificarUrl(String uri) {
        return uri
                .replace("%2F", "/")
                .replace("%3A", ":")
                .replace("%3F", "?")
                .replace("%40", "@")
                .replace("%21", "!")
                .replace("%24", "$")
                .replace("%27", "'")
                .replace("%28", "(")
                .replace("%29", ")")
                .replace("%2A", "*")
                .replace("%2C", ",")
                .replace("%3B", ";");
    }
}
