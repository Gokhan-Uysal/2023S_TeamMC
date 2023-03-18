FROM postgres:latest

ENV POSTGRES_USER=root \
    POSTGRES_PASSWORD=postgres

COPY conkueror.sql /docker-entrypoint-initdb.d/

EXPOSE 5432