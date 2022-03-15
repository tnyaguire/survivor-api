# ROBOT Apocalypse REST API

This application was built in the midst of a Robot apocalypse to track robots and survivors.

## Technologies

- Spring Boot 2.6.4
- Spring Data JPA
- Lombok
- MySQL

## Prerequisites

You will need Docker installed on your machine to run this project. Please check the
official [Docker documentation](https://docs.docker.com/engine/) for information how to install Docker on your operating
system.

## Running the project

We can easily run the whole with only a single command. Ensure you have Docker running.

```bash
git clone https://github.com/tnyaguire/survivor-api.git
```

```bash
cd survivor-api
```

```bash
docker-compose up
```

The services can be run on the background with command:

```bash
docker-compose up -d
```

## Stop the System

Stopping all the running containers is also simple with a single command:

```bash
docker-compose down
```

To stop and remove all containers, networks, and all images used by any service in <em>
docker-compose.yml</em> file, use the command:

```bash
docker-compose down --rmi all
```
