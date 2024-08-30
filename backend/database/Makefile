docker_folder_path := ./docker/

run:
	@ ./mvnw clean package -DskipTests
	@ cp ./target/database-0.0.1-SNAPSHOT.jar ${docker_folder_path}
	@ docker build -t database ${docker_folder_path}
	@ docker-compose -f ./docker/docker-compose.yml up