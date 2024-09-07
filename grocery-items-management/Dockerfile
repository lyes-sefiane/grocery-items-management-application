FROM amazoncorretto:17-alpine
LABEL maintainer="The M.E.R.I.T Team Lead : Lyes Sefiane"
WORKDIR grocery-items-management
COPY target/grocery-items-management.jar grocery-items-management.jar
RUN apk upgrade --no-cache
EXPOSE 8080
ENTRYPOINT ["java","-jar","grocery-items-management.jar"]