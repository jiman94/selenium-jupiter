1. 빌드 

docker build -t selenium-jenkins .

2. 실행 

D:\app\jenkins\secrets

docker run -d -p 80:8080 -p 50001:50000 -v /Users/mz03-jmryu/Downloads/jenkins:/var/jenkins_home --restart unless-stopped --name selenium-jenkins selenium-jenkins
]


docker run -d -p 8088:8080 -p 50001:50000 -v /Users/mz03-jmryu/Downloads/jenkins:/var/jenkins_home --restart unless-stopped --name selenium selenium


# windows 
docker run -d -p 8088:8080 -p 50001:50000 -v D:/app/jenkins:/var/jenkins_home --restart unless-stopped --name selenium-jenkins selenium-jenkins


docker run -d --env JAVA_OPTS="-Dorg.jenkinsci.plugins.gitclient.Git.timeOut=20" 8088:8080 -p 50001:50000 -v D:/app/jenkins:/var/jenkins_home --restart unless-stopped --name selenium-jenkins selenium-jenkins


docker run -d --env JAVA_OPTS="-Dorg.jenkinsci.plugins.gitclient.Git.timeOut=20" -p 8088:8080 -p 50001:50000 -v /Users/mz03-jmryu/Downloads/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v /etc/localtime:/etc/localtime:ro -e TZ=Asia/Seoul --restart unless-stopped  --name selenium-jenkins selenium-jenkins



1. docker container-id 확인 

docker ps

2. docker 컨테이너 들어가기 

docker exec -uroot -it <<container-id>> /bin/bash

3. 설치 
./gralew

4. 빌드 

./gradlew clean build --stacktrace --debug


gradle clean build --stacktrace --debug

