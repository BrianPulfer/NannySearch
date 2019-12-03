# NannySearch
Information Retrieval System for research of Nannies based on the websites nannyjob.co.uk and childcare.co.uk

## Project
Information Retrieval course's individual project. The course took place by the Università della Svizzera Italiana (USI - University of Southern Switzerland) in the fall semester of the academic year 2019/20.

The goal of the project is to apply in practice the theoretical notions learned during the course. In particular, each student has to submit by the deadline (December 4th, 2019) a working prototype of an information retrieval system for a specific task and user needs.
In this particular project, the goal is to develop an information retrieval system for potentialusers who are interested in the search of nannies.  This project is called NannySearch.
Also, the application should allow users to search for Nannies filtering by age, experience and location. 

## Back-end
The back-end was developed using Spring, apache nutch and apache solr frameworks.
Apache nutch allows for web crawling and, thus, local caching of webpages.
Apache solr allows for indexing and fast searching of a collection of documents.

The crawling and indexing of the documents is made offline.

Spring: https://spring.io/
Apache Nutch: https://nutch.apache.org/
Apache Solr: https://lucene.apache.org/solr/


## Front end
The website is made responsive by the use of bootstrap4's grid system.
Thymeleaf is used to put the HTML elements according to the retrieved documents by the back-end.

Bootstrap: https://getbootstrap.com/
Thymeleaf: https://www.thymeleaf.org/
