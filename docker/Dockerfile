# Getting jenkins image
FROM jenkins/jenkins:latest

# Changing the user to root
USER root
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# installing chrome driver
RUN apt-get update && apt-get -qq -y install curl

RUN apt-get install ca-certificates

# We need wget to set up the PPA and xvfb to have a virtual screen and unzip to install the Chromedriver
RUN apt-get install -y wget xvfb unzip

RUN curl -o - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -

RUN echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list

# Update the package list and install chrome
RUN apt-get update -y
RUN apt-get install -y google-chrome-stable


# Set up Chromedriver Environment variables
#ENV CHROMEDRIVER_VERSION 76.0.3809.12
ENV CHROMEDRIVER_VERSION 85.0.4183.38

ENV CHROMEDRIVER_DIR /chromedriver
RUN mkdir $CHROMEDRIVER_DIR

RUN curl http://chromedriver.storage.googleapis.com/85.0.4183.38/chromedriver_linux64.zip -o /chromedriver/chromedriver_linux64.zip -J -L

RUN unzip $CHROMEDRIVER_DIR/chromedriver* -d $CHROMEDRIVER_DIR

RUN chmod 777 -R /chromedriver


# Install maven
RUN apt-get -y update && apt-get install -y maven

# Install gradle
RUN apt-get -y update && apt-get install -y gradle


# Put Chromedriver into the PATH
ENV PATH $CHROMEDRIVER_DIR:$PATH


USER jenkins
