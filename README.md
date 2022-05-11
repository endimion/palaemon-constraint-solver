
# PALAEMON-CONSTRAINT-SOLVER

---

The "PALAEMON-CONSTRAINT-SOLVER" microservice implements a recommendation service
for the handling of Passenger related Issues and Incidents during vessel evacuation processes supported
by the PaMEAS-A system. Specifically, this microservice is utilized by PaMEAS-A to recommend
specific resource allocations (of specialized personnel) of the vessel to optimize the
management of passenger issues/incidents and reduce the time of the overall evacuation process. 

In more detail, this microservice exposes a single endpoint that can be used to request the 
organization of the assignment of crew members teams and equipment to passenger issues/incidents 
to ensure that the assigned crew member teams are trained/equipped to handle the specific emergency
and that the assigments takes place in such a way that reduces the overall response times for all 
teams/issue pairs. This allocation is implemented over [Redhat KIE OptaPlanner sdk](https://www.optaplanner.org/) and adheres to 
the following constraints:
- Team Distance from Incident
- Technical Capacity of the Crew Member Team
- Existing assignment status of the  Crew Members Team

# Further Reading and Documentation

---
If you want to learn more about the "PALAEMON-CONSTRAINT-SOLVER" microservice please read our
[working document](https://docs.google.com/document/d/1ljmMZdKuIWhcCmA4jlquAxP8VplmNn1SXZUCWVLKp2o/edit?usp=sharing).
which describes how this microservice is utilized in the context of the PaMEAS-A service lifecycle.
To gain a better understanding of the overall functionality of PaMEAS the following presentations are
helpful:
- [PaMEAS (and PALAEMON Pilots) ICT Integration](https://docs.google.com/presentation/d/1ni99nXpgV1XGvfo6XNaR3cbe4MRncCj3/edit?usp=sharing&ouid=101096721707031783382&rtpof=true&sd=true)
- [PaMEAS Evacuation Messaging Policy](https://docs.google.com/presentation/d/1uxZ4Hoah89qz3MuUqt1RmGY8Dxf0upC6/edit?usp=sharing&ouid=101096721707031783382&rtpof=true&sd=true)
- [PaMEAS-A integration](https://docs.google.com/presentation/d/1cRt34HpJzM55kundaGE65re5CHmTzsvp/edit?usp=sharing&ouid=101096721707031783382&rtpof=true&sd=true)
- [PaMEAS-N and PaMEAS-Cell](https://docs.google.com/presentation/d/1xnB5cOLFCL9GC1_jkzBss-vrYs6-Vv5h/edit?usp=sharing&ouid=101096721707031783382&rtpof=true&sd=true)
- [PaMEAS-A Testing Scenarios](https://docs.google.com/presentation/d/178G2WV1pbgP8KswFuqrGacF0mGM67ERetdLD67w74MU/edit?usp=sharing)
- [PALAEMON People Management System and Storage Layer: Demo](https://docs.google.com/presentation/d/16W8H_h-qz2HTbRwcXpGJ9RnrYqZxCAZ8/edit?usp=sharing&ouid=101096721707031783382&rtpof=true&sd=true)

# Code

---

*Disclaimer: Although we tested the code extensively, the "palaemon-conductor" is a research
prototype that may contain bugs. We take no responsibility for and give no warranties in respect of using the code.*

## Layout

The "PALAEMON-CONSTRAINT-SOLVER" microservice is implemented
via a Spring boot application.  As a result it adheres to the typical Spring boot structure:
- `src/main` contains the main logic of the application
- `src/test` contains the executed unit tests


# Deployment

---
The "PALAEMON-CONSTRAINT-SOLVER" microservice is implemented via Spring Boot and is Dockerized in order to
facilitate its deployment. As a result this microservice can be easily deployed using:
```
docker run --name endimion13/palaemon-constraint-solver:0.0.1a -p  8092:8080 -d 
```
Additionally, a typical Docker-compose file for its deployment would look as follows:
```
 
version: '2'
services:
 palaemon-constraint-solver:
    image:  endimion13/palaemon-constraint-solver:0.0.1a
    ports:
      - 8092:8080
```


 # API

---

The PALAEMON-DB-PROXY service exposes a protected REST API. 
For easier integration this API is defined using the OpenAPI notation. 
In order to gain access to a deployed instance API OpenAPI documentation navigate to 
`http://localhost:8080/swagger-ui/index.html#/`
