# JAVA 17 / Spring boot 3 👉 How to use RestTempalte

 1. https://velog.io/@asdf-dev/spring-boot-rest-template
 2. https://velog.io/@asdf-dev/spring-boot-open-api

## 📌 application.yml 

```
spring:
  devtools:
    livereload:
      enabled: true

  thymeleaf:
    prefix: classpath:/templates/
    suffix: .html
    cache: false
    check-template-location: true

api:
  naver:
    client-id:  your-id
    client-key: your-key
  kakao:
    key: your-key
  gpt:
    key: your-key
```
