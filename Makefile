DC       = docker compose
DATA_DIR = .docker/mariadb

.PHONY: build up start down stop restart ps logs db reset

build:
	$(DC) pull
	$(DC) build

up:
	$(DC) up -d --wait

start: build up

down:
	$(DC) down

stop:
	$(DC) stop

restart: down up

ps:
	$(DC) ps

logs:
	$(DC) logs -f

db:
	$(DC) exec mariadb mariadb -uroot -proot db_domedespuys

reset:
	$(DC) down -v
	powershell -Command "Remove-Item -Recurse -Force '$(DATA_DIR)' -ErrorAction SilentlyContinue"
	$(MAKE) up