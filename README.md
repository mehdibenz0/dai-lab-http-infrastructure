# dai-lab-http-infrastructure

# Step 1: Static Web site

In this step, we built a Docker image containing a static HTTP server using Nginx. The server serves a static Web site with a single page featuring a stylish template obtained from [Free-CSS](https://www.free-css.com/). The Dockerfile is based on the Nginx image and includes instructions to copy the static site content into the image.

To run the Docker image and access the static content, execute the following commands:
docker build -t dai-lab-http-infrastructure-static-server ./static-server
docker run -p 8080:80 dai-lab-http-infrastructure-static-server
Access the static site by opening your browser and navigating to http://localhost:80.

# Step 2: Docker Compose

I added a `docker-compose.yml` configuration file to the GitHub repository:

```yml
version: '3'

services:
  static-server:
    build:
      context: ./static-server
      dockerfile: Dockerfile
    ports:
      - "8080:80"
```
This configuration defines a service named static-server, builds the Docker image using the specified Dockerfile, and maps port 8080 on the host to port 80 on the container.
To start the infrastructure, use the following command:

```
docker-compose up -d
```
To stop the infrastructure:
```
docker-compose down
```
With the infrastructure running, you can access the static Web server by opening your browser and navigating to http://localhost:80

# Step 3: HTTP API Server

In this step, I've implemented a HTTP API server using Javalin. The API is designed to manage a list of quotes. It supports all CRUD operations (Create, Read, Update, Delete).
Implementation Overview
The main class of our API is Api, which contains the Javalin configuration and handles the CRUD operations.
Building with Maven
We have a Maven pom.xml file for managing dependencies and building the JAR.

To build the JAR with dependencies:
```
mvn clean package
```
To containerize our API server, we've created a Dockerfile.
We added the API server as a service in the Docker Compose configuration:
To start the API server using Docker Compose:
```
docker-compose up -d
```
![](https://github.com/mehdibenz0/dai-lab-http-infrastructure/blob/main/InsomniaDemo.gif)

# Step 5: Scalability and load balancing

The deploy section is added to both api-server and static-server services. It specifies the desired number of replicas for each service (in this case, 3). You can adjust the replicas value based on your needs.
To scale the api-server to 5 instances, you can run:
```
docker-compose up -d --scale api-server=5
```

on the Traefik dashboard, under HTTP Services, for api-server-dai-lab-http-infrastructure@docker we see 5 and for static-server-dai-lab-http-infrastructure@docker we see 3.

# Optional Step 1: Management UI (Using Portainer)

In this project, I've opted to enhance the management and monitoring of the web infrastructure using Portainer. Portainer is a user-friendly and powerful container management tool that provides a convenient web interface for Docker. It simplifies Docker container orchestration and makes it easier to visualize, manage, and update containers.

To access it go to http://localhost:9000.
```
Username: adminDAI
Password: VU$F438Tjju!sHJ
```
Once you've accessed Portainer, you gain a powerful interface for managing the Docker containers. The dashboard offers a comprehensive overview of the containers, networks, and volumes, providing insights into the web infrastructure.

Navigate to the "Containers" section to view and manage the running containers.

Starting/Stopping Containers:

Start or stop containers with a simple click on the respective container in the Portainer interface.
