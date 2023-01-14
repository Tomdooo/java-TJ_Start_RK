# TJ Start RK API

## Instalace a běh
Repozitář obsahuje soubory `docker-compose.yml` a `Dockerfile`, které zajišťují spuštění pomocí Dockeru. Je tak potřeba pouze vytvořit a spustit kontejner
právě pomocí `docker-compose.yml`


## Chyby při testech
Aplikace původně obsahovala funkční testy na JPA databázi - vytváření, úprava a ostraňování záznamů v databázi pomocí JPA. Kvůli buildění finální aplikace
a spuštění v Dockeru jsme však museli testy zakomentovat, jelikož všechny testy vždy skončili chybou. Přesnou příčinu se nám nepodařilo objasnit, ale přijde nám, že to může mít co dočinění s tím, že i přes zadané `depends_on: db` v `docker-compose.yml` se začne Spring aplikace spouštět ještě před úplným spuštěním databáze.

Testy je možno odkomentovat a spustit samostatně, kdy všechny testy projdou v pořádku, třeba i přes neinicializovanou databázi (bez tabulek).


## Předvytvořené uživatelské účty
API je chráněno pomocí Basic authorization a OAuth2 s využitím JWT tokenu.

> username: *tomas*\
> password: *Password123@*\
> role: *USER*

> username: *vasek*\
> password: *Password123@*\
> role: *ADMIN*


## Frontend aplikace
Frontend část aplikace ve Svelte i s malým návodem je možné najít zde: https://github.com/Tomdooo/svelte-TJ_Start_RK.git
