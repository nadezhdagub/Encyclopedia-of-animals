FROM adoptopenjdk/openjdk11

ARG BUILD_DATE
LABEL org.label-schema.build-date=$BUILD_DATE

RUN apt update && apt -y install tzdata
RUN ln -sf /usr/share/zoneinfo/Europe/Moscow /etc/localtime

RUN mkdir -p app/log
COPY files/ app

WORKDIR app

ENTRYPOINT ["/app/entrypoint.sh"]

