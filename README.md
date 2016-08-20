# ConcurrencyCookBook
![the project logo](https://espenberntsen.files.wordpress.com/2010/03/duke_thread.gif)

[![Build Status](https://travis-ci.org/LyashenkoGS/ConcurrencyCookBook.svg?branch=master)](https://travis-ci.org/LyashenkoGS/ConcurrencyCookBook)
[![codecov](https://codecov.io/gh/LyashenkoGS/ConcurrencyCookBook/branch/master/graph/badge.svg)](https://codecov.io/gh/LyashenkoGS/ConcurrencyCookBook)
[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/LyashenkoGS/ConcurrencyCookBook/master/LICENCE)  

### Overview
The collection of  concurrency programming examples with [JETM](http://jetm.void.fm/) benchmarks and detail explanations. Mostly in Java.

### How to run
    mvn clean install
    java -jar ./target/benchmarks.jar 
  
  Results will be available on [http://localhost:45000](http://localhost:45000)
  ![results](result.png)
  
### Problems 
  * jacoco doesn't work properly.
  * need an external tool to build charts.
  
    