# Getting Started

To start server, run DemoApplication.main()
If the ScoresController is present, it'll demand a DATABASE_URL env var.  You can set it by editing the run configuration.  

Note that your database connection URL can't be in the normal form that elephantsql, etc, apply  - not postgres://... - rather you'll need to re-write it into a form as follows:
```
//note: you'll have to substitute real values for the placeholders in CAPS below.
jdbc:postgresql://HOSTNAME/DBNAME?user=DBUSERNAME&password=DBPASSWORD
```

Then visit http://localhost:8080/greeting?name=yourname

### Database? that's too much work for now

If you don't want to faff with database stuff at all for now, just delete `ScoresController.java`.

### history

To build this and get it deployed on render, I followed:
* https://www.youtube.com/watch?v=g4kQ3ELo49Y
* https://start.spring.io/
* https://spring.io/guides/gs/rest-service/

The only thing that was missing, I think, was a .gitignore file!

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.3/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

