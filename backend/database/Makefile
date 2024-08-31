docker_folder_path := ./docker/
docker_compose_file := ./docker/docker-compose.yml
service_name := postgres

init: restart build

build:
	@ ./mvnw clean package -DskipTests
	@ cp ./target/database-0.0.1-SNAPSHOT.jar ${docker_folder_path}
	@ docker build -t database ${docker_folder_path}
	@ docker-compose -f ./docker/docker-compose.yml up

restart:
	@ docker-compose -f ${docker_compose_file} down





