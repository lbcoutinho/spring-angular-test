# spring-angular-test

This is a Single Page Application created using Spring Boot 2, Angular 5 and Bootstrap 4.
It contains a simple Client CRUD where you can perform the basic operations of create, update and delete.
Besides that, the edit page also contains a Loan Simulator, which simulates a loan to the Client considering his income to define the interest rate. 

## Demo

The app is deployed in Heroku: https://sleepy-brushlands-76988.herokuapp.com

## Prerequisites

* Java 8: http://www.oracle.com/technetwork/java/javase/downloads/index.html
* Node: https://nodejs.org/en/download/
* Angular CLI: https://github.com/angular/angular-cli#installation
* copyfiles lib: https://www.npmjs.com/package/copyfiles
* Git: https://git-scm.com/downloads

## Running locally

1. Clone this repo
2. Download dependencies to Angular project
   - Access /client and run: $ npm install
3. Build Angular project and copy files to Spring project resources
   - Access /client and run: $ npm run build
4. Run Spring server
   - Access /server and run: $ mvnw spring-boot:run
5. Access the app in the browser: http://localhost:8080

## Extending the project

If you want to edit the project and test your changes quickly, then follow these steps:
1. Clone this repo
2. Download dependencies to Angular project
   - Access /client and run: $ npm install
3. Start Spring server
   - Access /server and run: $ mvnw spring-boot:run
4. Start Angular server
   - In a different terminal window, access /client and run: $ npm run start
5. Access the app in the browser: http://localhost:4200
6. Edit the project code normally
7. Restart the Spring or Angular server if your changes don't get applied.

## Deploying to Heroku

### Part 1 - Prepare the code
1. Clone this repo
2. Download dependencies to Angular project
   - Access /client and run: $ npm install
3. Build Angular project and copy files to Spring project resources
   - Access /client and run: $ npm run build
4. Edit file **spring-angular-test/server/src/main/resources/application.properties**
   - Disable embedded H2 database: comment the properties starting with **spring.h2** and starting with **spring.datasource**.
   - Allow Hibernate to create tables: uncomment the property **spring.jpa.hibernate.ddl-auto**

### Part 2 - Setup Heroku app
1. Create a free account on https://www.heroku.com/
2. Install the Heroku Command Line Interface (CLI): https://devcenter.heroku.com/articles/heroku-cli
3. Open the command terminal and run: $ heroku login
4. Login with your Heroku account credentials.
5. In the command terminal access the project **/server** folder
6. Create a Git repository: $ git init
7. Create a Heroku app by running the command: $ heroku create
8. Add a ClearDB database to the Heroku app: $ heroku addons:create cleardb:ignite
   - You may be asked to enter billing information for the above task. Since it's just a test app ClearDB should be free.

### Part 3 - Push code to Heroku
1. In the command terminal access the project **/server** folder
2. Commit the project code: $ git add . && git commit -m "Initial commit"
3. Deploy the code in Heroku using the command: $ git push heroku master
4. After the deployment is finished, the Heroku app URL should be shown in the your command terminal.

## Tutorials Used
* Spring Boot 2 and Angular 5: https://developer.okta.com/blog/2017/12/04/basic-crud-angular-and-spring-boot
* Bootstrap 4: https://www.youtube.com/playlist?list=PL55RiY5tL51rLqH4-8LBVlUTIFF70dxhb
* Angular proxy setup and deploy to Spring Boot: https://youtu.be/k8r76d8QzXs?t=37m17s
* Deploy Spring Boot app to Heroku: https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku
* Setup MySQL and Hibernate: https://spring.io/guides/gs/accessing-data-mysql/
