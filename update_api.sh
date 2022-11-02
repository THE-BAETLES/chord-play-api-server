aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 199349264777.dkr.ecr.ap-northeast-2.amazonaws.com
./gradlew bootJar
docker build --no-cache --platform=linux/amd64 -t 199349264777.dkr.ecr.ap-northeast-2.amazonaws.com/chord-play-api:0.0.4 .
docker push 199349264777.dkr.ecr.ap-northeast-2.amazonaws.com/chord-play-api:0.0.4
