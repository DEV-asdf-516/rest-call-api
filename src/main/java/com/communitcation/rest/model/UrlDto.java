package com.communitcation.rest.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UrlDto {
    private String shortUrl;

    public UrlDto(Builder builder){
        this.shortUrl = builder.url;
    }
   @Data
    public static class Builder{
       private String url;
       public UrlDto build(){
          return new UrlDto(this);
       }
   }
}
