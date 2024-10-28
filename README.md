# Grocery Items Management Application

A Desktop Application Oriented Microservice Architecture

### © 2024 | Lyes Sefiane <img src="https://raw.githubusercontent.com/wiki/lyes-sefiane/grocery-items-management-application/images/algeria-flag-icon.png" width="2%"> <img src="https://raw.githubusercontent.com/wiki/lyes-sefiane/grocery-items-management-application/images/canada-flag-icon.png" width="2%"> All Rights Reserved | [CC BY-NC-ND 4.0](https://creativecommons.org/licenses/by-nc-nd/4.0/)

[![CC BY-NC-ND 4.0][cc-by-nc-nd-image]][cc-by-nc-nd]

[cc-by-nc-nd]: http://creativecommons.org/licenses/by-nc-nd/4.0/
[cc-by-nc-nd-image]: https://licensebuttons.net/l/by-nc-nd/4.0/88x31.png
[cc-by-nc-nd-shield]: https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg

###

![License](https://img.shields.io/static/v1?label=License&message=CC-BY-NC-ND-4.0&color=green)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](code_of_conduct.md)
[![Java CI with Maven](https://github.com/lyes-sefiane/grocery-items-management-application/actions/workflows/maven.yml/badge.svg)](https://github.com/lyes-sefiane/grocery-items-management-application/actions/workflows/maven.yml)
[![pages-build-deployment](https://github.com/lyes-sefiane/grocery-items-management-application/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/lyes-sefiane/grocery-items-management-application/actions/workflows/pages/pages-build-deployment)
[![Dependabot Updates](https://github.com/lyes-sefiane/grocery-items-management-application/actions/workflows/dependabot/dependabot-updates/badge.svg)](https://github.com/lyes-sefiane/grocery-items-management-application/actions/workflows/dependabot/dependabot-updates)
[![CodeQL](https://github.com/lyes-sefiane/grocery-items-management-application/actions/workflows/github-code-scanning/codeql/badge.svg)](https://github.com/lyes-sefiane/grocery-items-management-application/actions/workflows/github-code-scanning/codeql)
[![Automatic Dependency Submission](https://github.com/lyes-sefiane/grocery-items-management-application/actions/workflows/dependency-graph/auto-submission/badge.svg)](https://github.com/lyes-sefiane/grocery-items-management-application/actions/workflows/dependency-graph/auto-submission)
![GitHub Repo stars](https://img.shields.io/github/stars/lyes-sefiane/grocery-items-management-application?style=social)
![GitHub top language](https://img.shields.io/github/languages/top/lyes-sefiane/grocery-items-management-application)

## Documentation

- [Grocery Items Management Application Documentation](https://github.com/lyes-sefiane/grocery-items-management-application/wiki)

## User Interface

<p>
<img src="https://raw.githubusercontent.com/wiki/lyes-sefiane/grocery-items-management-application/images/welcome.PNG">
</p>

## Infrastructure

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

## Grocery Items Management Application User Interface

```bash
cd grocery-items-management-desktop-ui/

mvn clean install -DskipTests

java -jar target/grocery-items-management-desktop-ui-1.0.0.jar

```
## OpenApi / Swagger 

- [API Documentation / Swagger UI](https://lyes-sefiane.github.io/grocery-items-management-application/)

# Enhancements

- [ ] Unit and Integration Tests Implementation
- [ ] CI/CD Implementation
- [ ] Authentication and Authorization Implementation

# Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

