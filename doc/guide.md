
1. docker container-id 확인 

docker ps

2. docker 컨테이너 들어가기 

docker exec -uroot -it <<container-id>> /bin/bash

3. 설치 
./gralew

4. 빌드 

./gradlew clean build --stacktrace --debug


gradle clean build --stacktrace --debug






0. 디렉토리이동 

cd doc

1. 빌드 

docker build -t selenium-jenkins .

2. 실행 

D:\app\jenkins\secrets


docker run -d -p 80:8080 -p 50001:50000 -v /Users/mz03-jmryu/Downloads/jenkins:/var/jenkins_home --restart unless-stopped --name selenium selenium




docker run -d -p 8088:8080 -p 50001:50000 -v /Users/mz03-jmryu/Downloads/jenkins:/var/jenkins_home --restart unless-stopped --name selenium selenium


docker run -d -p 8088:8080 -p 50001:50000 -v D:/app/jenkins:/var/jenkins_home --restart unless-stopped --name selenium-jenkins selenium-jenkins

docker run -d --env JAVA_OPTS="-Dorg.jenkinsci.plugins.gitclient.Git.timeOut=20" 8088:8080 -p 50001:50000 -v D:/app/jenkins:/var/jenkins_home --restart unless-stopped --name selenium-jenkins selenium-jenkins



docker run -d --env JAVA_OPTS="-Dorg.jenkinsci.plugins.gitclient.Git.timeOut=20" -p 8088:8080 -p 50001:50000 -v /Users/mz03-jmryu/Downloads/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v /etc/localtime:/etc/localtime:ro -e TZ=Asia/Seoul --restart unless-stopped  --name selenium-jenkins selenium-jenkins




https://github.com/eliranshani/selenium-docker-allure


https://bonigarcia.github.io/selenium-jupiter/

https://chromedriver.chromium.org/downloads

https://github.com/bonigarcia/selenium-jupiter



# Install Selenium GRID

selenium HQ: http://www.seleniumhq.org/download/

Step 2: Open the command prompt and navigate to a folder where the server is located. Run the server by using below command

java -jar selenium-server-standalone-3.141.59.jar -role hub

http://localhost:4444/grid/console

Step 3: Go to the other machine where you intend to setup Nodes. Open the command prompt and run the below line.


/Users/mz03-jmryu/Downloads

java -jar selenium-server-standalone-3.141.59.jar  -role node -hub http://localhost:4444/grid/register -port 5556


java -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://localhost:4444/grid/register -port 5556 -browser browserName=chrome

java -jar selenium-server-standalone-3.141.59.jar  -role webdriver -hub http://localhost:4444/grid/register -port 5556 -browser browserName=iexplore
-browser browserName=firefox -browser browserName=chrome


http://localhost:5556/wd/hub/static/resource/hub.html
