# Patterdale-jvm [![Build Status](https://travis-ci.org/tjheslin1/Patterdale-jvm.svg?branch=master)](https://travis-ci.org/tjheslin1/Patterdale-jvm)

[![Docker Pulls](https://img.shields.io/docker/pulls/tjheslin1/patterdale-jvm.svg?maxAge=604800)](https://hub.docker.com/r/tjheslin1/patterdale-jvm/)

`docker run -d -p 7000:7000 -v /your/config/directory:/config tjheslin1/patterdale-jvm:0.7`

`/your/config/directory` is expected to contain a file `patterdale.yml` with the following content:

Example:
```yml
httpPort: 7000
databases:
  - name: test
    user: system
    password: oracle
    jdbcUrl: jdbc:oracle:thin:system/oracle@10.55.49.77:1521:xe
    probes:
      - query: SELECT 1 FROM DUAL
        type: exists
        metricName: database_up
        metricLabels: database="myDB",query="SELECT 1 FROM DUAL"
  - name: test2
    user: system
    password: oracle
    jdbcUrl: jdbc:oracle:thin:system/oracle@10.55.49.77:1522:xe
    probes:
      - query: SELECT 1 FROM DUAL
        type: exists
        metricName: database_up
        metricLabels: database="myDB2",query="SELECT 1 FROM DUAL"
      - query: SELECT 2 FROM DUAL
        type: exists # exists type expects a result of 1, therefore this probe will fail
        metricName: database_up
        metricLabels: database="myDB2",query="SELECT 2 FROM DUAL"
connectionPool:
  maxSize: 5
  minIdle: 1
```