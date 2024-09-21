stop:
	@ echo "Stopping all running containers . . ."
	@ docker ps -q | xargs -r docker stop

kill:
	@ echo "Killing all running containers . . ."
	@ docker ps -q | xargs -r docker kill

clean:
	@ echo "Removing all running containers . . ."
	@ docker ps -a -q | xargs -r docker rm


updb:
	@ echo "Instatiate the Postgres and PGAdmin . . ."
	@ docker-compose -f docker/docker-compose.yml up

registry:
	@ cd contact-registry/ && mvn spring-boot:run

rmi:
	@if [ -n "$$(docker images -aq)" ]; then docker rmi -f $$(docker images -aq); else echo "No images to remove."; fi