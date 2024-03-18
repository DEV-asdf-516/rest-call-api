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
