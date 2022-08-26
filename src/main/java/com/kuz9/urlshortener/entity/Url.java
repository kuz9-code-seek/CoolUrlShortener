package com.kuz9.urlshortener.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Url {

    @Indexed
    private String longUrl;

    @Indexed
    private String shortUrl;

}
