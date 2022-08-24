./gradlew bootJar
docker build --no-cache --platform=linux/amd64 -t pove2019/chord-play-api:0.0.4 .
docker push pove2019/chord-play-api:0.0.4
