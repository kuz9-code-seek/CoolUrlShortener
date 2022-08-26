package com.kuz9.urlshortener.service;

import com.kuz9.urlshortener.entity.Url;
import com.kuz9.urlshortener.repository.UrlRepository;
import com.sangupta.murmur.Murmur3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public String getShortUrl(String longUrl) {
        var url = urlRepository.findByLongUrl(longUrl);
        if (url.isEmpty())
            return createNewUrlEntity(longUrl).getShortUrl();
        return url.get().getShortUrl();
    }

    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl).orElse(new Url()).getLongUrl();
    }

    private Url createNewUrlEntity(String longUrl) {
        var url = new Url();
        url.setLongUrl(longUrl);
        url.setShortUrl(createShortUrl(longUrl));
        urlRepository.save(url);
        return url;
    }

    private String createShortUrl(String longUrl) {

        String hash = Arrays.toString(Murmur3.hash_x64_128(longUrl.getBytes(), longUrl.length(), 0));

        hash = hash.replace("[", "").replace("]", "")
                .replace(",", "").replace(" ", "").replace("-", "");

        return longUrl.substring(0, 11) + "/" + hash;
    }
}
