# spring-web-template
1. 這一個網站結構樣板，使用maven做後端工作管理，npm gulp 做前端管理。 
2. 開發語言是java.
3. 使用 spring 框架.

# maven install
reference -> https://maven.apache.org/install.html   

maven 是一個後端工作管理工具，可用建構、打包等並可管理library的相依性。   

# install npm
reference -> https://www.npmjs.com/package/npm   
  or
reference -> https://nodejs.org/en/download/   

npm 是一個前端程式工作管理工具，可管理前端library、plugi、framework。   

可以直接安裝 node即可使用。
node 安裝是一個比較容易的方式。


# install Backend-end library
1. mvn install


# install front-end library
1.npm install   

# run tomcat
1. mvn tomcat7:run or mvn tomcat7:run

# 備註
以上指令若有發生問題 請加上 sudo

# mvn 指令
mvn install   
mvn compile   
mvn clean   
mvn clean tomcat7:run

# open page
http:127.0.0.1:8080/index.jsp
