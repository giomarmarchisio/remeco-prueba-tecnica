@echo off
start cmd /k "cd clientes-personas && call mvnw.cmd spring-boot:run"
start cmd /k "cd cuentas-movimientos && call mvnw.cmd spring-boot:run"
