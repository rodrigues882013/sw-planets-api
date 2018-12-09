# Star Wars Planets Register API

Requirements:

Mandatory:

    maven
    java 1.8
    
Highly Recommended:
    
    docker (optional)
    docker-compose (optional)

In case of docker and docker-compose wasn't installed before you'll need of others requirements:

    MongoDB
    Redis

First of all, clone this repository as you wish.

    git clone https://github.com/rodrigues882013/sw-planest-api.git

Execute tests:

    mvn test

Build project with:
    
    mvn clean install
Obs.: Tests will run again

Now build the entire ecossystem with:

    docker-compose up --build
    
In case of you won't install docker and docker-compose execute it with:

    mvn spring-boot:run
Obs.: In this case ensure that you installed before redis and mongodb

With the step below the application will run in:

    http://localhost:8080/sw-planets-api/swagger-ui.html

Now you can test api.
