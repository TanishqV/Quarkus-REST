# Quarkus-REST
This is a REST API made using Quarkus for GET, PUT, POST and DELETE requests.

Quarkus
----

A Kubernetes Native Java stack tailored for OpenJDK HotSpot and GraalVM, crafted from the best of breed Java libraries and standards.
**Version Used:**  0.18.0

Packages Used
----

• [Rest Easy](https://resteasy.github.io/): A JBoss project which provides various frameworks to build REST
Web Services. It is a fully certified and portable implementation of JAX-RS 2.1
implementation

• Rest Easy-JSONB: A part of Rest Easy project focusing on the functionalities and
handling of JSON objects binding to the entities to store data in memory.

• [JUnit5](https://junit.org/junit5/): It is a unit testing framework for Java. It is an open source framework
which provides annotations to identify test methods.

• [Rest Assured](http://rest-assured.io/): It is a library used for REST API Automation Testing.

• [Hibernate-ORM-Panache](https://quarkus.io/guides/hibernate-orm-panache): Hibernate ORM is the de facto JPA (Java Persistence
API) implementation and makes complex mappings of Object Relational
Mapper. But it does not make simple and common mappings trivial. Hibernate
ORM Panache focuses on making the entities trivial with simple and
understandable syntax.

• [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/): It has only one goal – surefire:test runs the unit
tests of an application.

DockerFiles
----

 ** Custom made entrypoint scripts were coded to integrate PostgreSQL into Linux

 Comparison of Performance of Docker Images
----

| Parameters | Container (JVM based Image) | Container (NATIVE Image) |
| ------ | ------ | ------ |
| Image size | 142MB | 101MB |
| Real time | 3min 48.564s | 3min 58.729s |
| User time | 0min 7.171s | 0min 0.029s |
| System time | 0min 0.450s | 0min 0.019s |

License
----

MIT
