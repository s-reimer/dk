# DK Swing Data Coding Challenge.
This repo is a solution to the DK Swing Data coding challenge. It contains a Spring Boot application that exposes a REST 
API capable of searching a DK swing data csv file. There is currently no UI, so users should interact
with the application with `curl`.

## How to Run
1) Start the application via gradle. From the root of this repo, `./gradlew bootRun`
2) Load the swing data csv file by sending a POST request to the `/swing/load` endpoint. The application is running on 
`localhost:8080`. The path to the data file must be providing with the `--data` key.

    `curl -X POST -H "Content-Type: text/plain" --data "latestSwing.csv" localhost:8080/swing/load
`
3) Start searching! Send a GET request to one of the search endpoints, `/swing/search`, `/swing/backSearch`, 
`/swing/searchTwo`, `swing/searchMulti`. Here are some examples:

    `curl -X GET 'localhost:8080/swing/search?column=ax&start=0&end=200&threshold=1.1&winLength=3'`

    `curl -X GET 'localhost:8080/swing/backSearch?column=ax&start=112&end=0&thresholdLo=0.1&thresholdHi=3.0&winLength=3'`

    `curl -X GET 'localhost:8080/swing/searchTwo?column1=ax&column2=ay&start=0&end=300&threshold1=1.3&threshold2=-3.3&winLength=2'`

    `curl -X GET 'localhost:8080/swing/searchMulti?column=ax&start=200&end=700&thresholdLo=0&thresholdHi=20&winLength=7'`