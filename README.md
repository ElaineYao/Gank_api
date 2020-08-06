# Gank App
----
This is a client-side app based on the Gank website. Users can browse the website through this app.

It used SQlite3 to handle server-side database tasks, Retrofit to process the network requests (such as loading the website’s picture through API), and Material Design for UI.

For now, it realizes the function of Login&Signup and Classified Reading.

However, the speed of online pictures loading needs to be improved.

# File Structure

```bash
├─.idea
├─app
│  └─src
│      ├─androidTest
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─elainedv
│      │  │          ├─Classification
│      │  │          ├─Gank
│      │  │          ├─Register
│      │  │          └─Utils
│      │  └─res
│      └─test
└─gradle
    └─wrapper
```
### Register


### Classified Reading



# Dependencies
- Retrofit + Okhttp (Network request)
- Gson (Parse JSON data)
- Realm (Database Operation)
- Glide (Load pictures online)
