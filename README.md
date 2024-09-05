# JAVA 17 / Spring boot 3 👉 How to use RestTempalte

 1. https://velog.io/@asdf-dev/spring-boot-rest-template
 2. https://velog.io/@asdf-dev/spring-boot-open-api
 3. https://velog.io/@asdf-dev/Spring-boot-GitLab-CICD-Heroku

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

<br>

##### 🎮 SCREEN SHOT
![ai](https://github.com/user-attachments/assets/55c8d6f7-e85f-45e6-9274-a75295fd7cd2)

<br>

##### 🌍 DEPLOYMENT
> https://gen-image-web-server-a5817d1d5219.herokuapp.com/
