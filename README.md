# Grocery Items Management Application

![License](https://img.shields.io/static/v1?label=License&message=CC-BY-NC-ND-4.0&color=green)
![GitHub Repo stars](https://img.shields.io/github/stars/lyes-sefiane/grocery-items-management-application?style=social)
![GitHub top language](https://img.shields.io/github/languages/top/lyes-sefiane/grocery-items-management-application)

## Documentation

- [Grocery Items Management Application Documentation](https://github.com/lyes-sefiane/grocery-items-management-application/wiki)

<p>
<img src="https://raw.githubusercontent.com/wiki/lyes-sefiane/grocery-items-management-application/images/grocery-items-management-application-infrastructure.PNG" width="100%" alt="https://github.com/lyes-sefiane/grocery-items-management-application/wiki">
</p>

## Requirements

```bash
1. Java 17.x.y

2. Maven 3.x.y

3. Docker 3.x.y
```

## Microservice Deployment to Localhost

```bash
cd grocery-items-management-application/

chmod u+x grocery-items-management-application

./grocery-items-management-application


  ┏┓             ┳
  ┃┓┏┓┏┓┏┏┓┏┓┓┏  ┃╋┏┓┏┳┓┏
  ┗┛┛ ┗┛┗┗ ┛ ┗┫  ┻┗┗ ┛┗┗┛
  ┳┳┓         ┛          ┳┳┓•            •
  ┃┃┃┏┓┏┓┏┓┏┓┏┓┏┳┓┏┓┏┓╋  ┃┃┃┓┏┏┓┏┓┏┏┓┏┓┓┏┓┏┏┓
  ┛ ┗┗┻┛┗┗┻┗┫┗ ┛┗┗┗ ┛┗┗  ┛ ┗┗┗┛ ┗┛┛┗ ┛ ┗┛┗┗┗
            ┛


MINGW64_NT-10.0-19045 : Hi 😊 ! please make a selection.

1) Start Microservice
2) ShutDown Microservice
3) quit
#? 1
Start Grocery Items Management Application Microservice ...
[+] Running 7/7
 ✔ Network docker-compose_grocery-items-management-application  Created                                                                                  0.2s 
 ✔ Container redis                                              Started                                                                                  2.6s 
 ✔ Container zipkin                                             Started                                                                                  2.8s 
 ✔ Container consul-server                                      Started                                                                                  3.1s 
 ✔ Container grocery-items-management                           Started                                                                                  4.4s 
 ✔ Container consul-client                                      Started                                                                                  4.3s 
 ✔ Container grocery-items-management-api-gateway               Started                                                                                  5.7s 
```

## Grocery Items Management Application UI

```bash
cd grocery-items-management-desktop-ui/

mvn clean install -DskipTests

java -jar target/grocery-items-management-desktop-ui-1.0.0.jar

```
## OpenApi / Swagger 

- [Swagger UI](https://lyes-sefiane.github.io/grocery-items-management-application/)

# Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

