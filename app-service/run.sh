export POSTGRES_HOST=127.0.0.1
export POSTGRES_PORT=5432
export POSTGRES_DB=conkueror
export POSTGRES_USER=conkueror
export POSTGRES_PASSWORD=1029384756!

mvn package
mvn install
mvn exec:java -Dexec.mainClass=app.Main