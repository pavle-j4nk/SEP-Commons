package rs.ac.uns.ftn.sep.commons.helper;

import org.springframework.web.util.UriComponentsBuilder;

public final class UrlHelper {
    private UrlHelper() {
    }

    public static String addQueryParam(String url, String name, String value) {
        return UriComponentsBuilder.fromUriString(url).queryParam(name, value).build().toUriString();
    }

    public static String addPathVariables(String url, String ... params) {
        return UriComponentsBuilder.fromUriString(url).pathSegment(params).build().toUriString();
    }

}
