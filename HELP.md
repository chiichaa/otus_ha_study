требовуется устровленное окружение Docker (docker-compose, docker desktop, colima ...):

### Гайд по сборке и запуску
все команды выполняются из корня проекта

сборка образа для демонастрации:

`docker build . -t otus-highload-study:homework1 -f build.image.Dockerfile`

запуск демонстрационного compose файла:

`docker-compose -f ./.docker-compose/local-demo/docker-compose.yml up -d`

остановка демонстрационного compose файла:

`docker-compose -f ./.docker-compose/local-demo/docker-compose.yml down`

### Дополнительные, полезные файлы 

файл с коллекцией postman:

[.postman-collections/otus_highload.postman_collection.json](.postman-collections/otus_highload.postman_collection.json)