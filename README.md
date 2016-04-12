# spring-web-template
1. 這一個網站結構樣板，使用maven做後端librry與工作管理，npm 做前端套件管理, gulp 做前端工作管理。 
2. 開發語言是java.
3. 使用 spring 框架.

# 說明 :
 1. maven 是一個後端工作管理工具，可用建構、打包等並可管理library的相依性。   
 2. npm 是一個前端程式工作管理工具，可管理前端library、plugi、framework。

# 環境準備
1. maven install
reference -> https://maven.apache.org/install.html   

2. install npm
reference -> https://www.npmjs.com/package/npm   
  or
reference -> https://nodejs.org/en/download/   

可以直接安裝 node即可使用。
node 安裝是一個比較容易的方式。

#快速開始:

 1. mvn install
 2. npm install
 3. mvn clean tomcat7:run or mvn tomcat7:run
 
 說明: 
  a. mav install 依pom.xml下載後端使用套件。   
  b. npm install 依package.json裡的列表下載所需要的套件。   
  c. mvn tomcat:run 啟動tomcat   


# mvn 指令
mvn install   
mvn compile   
mvn clean   
mvn clean tomcat7:run

# Gulp 指令
1. gulpfile.js 為gulp指令搞檔案。
2. gulp sass 可編譯所有sass檔案。
3. gulp build 
3. gulp 則會執行預設的命令。

# open page
走 JSP >  http://127.0.0.1:8080/index.jsp
走 thymeleaf >  http://localhost:8080/index.html
# 備註
1. 以上指令若有發生問題 試試加上 sudo
2. 使用maven並不受限於使用什麼IDE，但是建議使用intellij.

# 還未完成部分
1. 前端工程架構   livereload.
2. DB Connections interface.  (mongodb and T sql db)
3. 若用 SQL 則需架構 ORM 

# mac 
brew 所安裝的 maven 路徑會在   
/usr/local/Cellar/maven/{version}/   
/usr/local/Cellar/maven/3.2.3/libexec/conf$   

repository路徑在：   
~/.m2/repository$ 

