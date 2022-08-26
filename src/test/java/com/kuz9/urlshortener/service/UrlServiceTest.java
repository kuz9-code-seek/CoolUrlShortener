package com.kuz9.urlshortener.service;

import com.kuz9.urlshortener.repository.UrlRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    @Mock
    UrlRepository urlRepository;

    @InjectMocks
    UrlService urlService;

    @Test
    public void getShortUrl(){
        var longUrl = "https://us04web.zoom.us/postattendee?mn=3c6VMEcJWx_uG6WWKzB3XhljJ4cD_szSaUsu.YrwatFn07uqYIgAb";
        var url = urlRepository.findByLongUrl(longUrl);
        if (url.isEmpty())
            return createNewUrlEntity(longUrl).getShortUrl();
        return url.get().getShortUrl();
    }

}
