package com.kuz9.urlshortener.controller;

import com.kuz9.urlshortener.service.UrlService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @Cacheable(value = "url")
    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @PostMapping("create-short")
    public String convertToShortUrl(@RequestParam String longUrl) {
        return urlService.getShortUrl(longUrl);
    }

    @Cacheable(value = "url")
    @SneakyThrows
    @ApiOperation(value = "Return long Url", notes = "Finds original url from short url")
    @GetMapping(value = "return-long")
    public String getOriginalURL(@RequestParam String shortUrl) {
        return urlService.getOriginalUrl(shortUrl);
    }
}
