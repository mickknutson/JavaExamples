#!/usr/bin/env bash

##---------------------------------------------------------------------------##
##  Initialize the Spring Class System
##---------------------------------------------------------------------------##

# Go up to the root directory:
# set $CURRENT = .
# cd ../


# Download the required source and javadoc dependencies:
mvn dependency:sources
mvn dependency:resolve -Dclassifier=javadoc

#### OR Eclipse Plugin:
mvn eclipse:eclipse -DdownloadSources=true
mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true

##---------------------------------------------------------------------------##
mvn clean cobertura:cobertura -e

##---------------------------------------------------------------------------##
# Go back to the beginning
cd $CURRENT

##---------------------------------------------------------------------------##
