# eubr-atmosphere

## Stack
- Web (`1.5.3.RELEASE`)
- Security (`1.5.3.RELEASE`)
- JPA (`1.5.2.RELEASE`)
- Thymeleaf (`1.5.2.RELEASE`)

## Development

### Prerequisites

Before moving on, make sure you have the following software installed:

1. Git
2. Docker
3. Docker Compose
4. Java SE JDK 8
5. Maven 3

To run it locally, clone the repository:

```shell
$ git clone git@gitlab.com:atmosphere-project/privacy-assessment-forms.git
```

During development time you can use Docker and Docker Compose to deploy locally all required services like MySQL, RabbitMQ, etc. and initialize the database.

```$shell
$ docker-compose up --build
```

Then you can build and run the application using the following commands:

```shell
$ mvn clean install
$ cd app
$ mvn spring-boot:run
```

To initialise the database with an admin user, use the following command:
```shell
$ mvn spring-boot:run -Drun.profiles=init-data
```

Visit: `http://localhost:8080` and login using `admin`/`adminadmin`

### Clean installation

```shell
- SQL -
DROP database atmosphere;
CREATE database atmosphere;
```

```shell
$ mvn clean install
$ cd app
$ mvn spring-boot:run -Drun.profiles=init-data
```

```shell
- SQL -
Execute 'db.sql' script to import data
```
