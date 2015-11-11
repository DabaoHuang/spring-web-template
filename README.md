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
2. 這會將java有使用的jar檔都下載好。


# install front-end library
1.npm install   
2.這會將前端的各種套件下載。
3.npm 會依package.json裡的列表抓取所需要的套件。

# run tomcat
1. mvn tomcat7:run or mvn tomcat7:run

# mvn 指令
mvn install   
mvn compile   
mvn clean   
mvn clean tomcat7:run

# Gulp 指令
1. gulpfile.js 為gulp指令搞檔案。
2. gulp sass 可編譯所有sass檔案。
3. gulp sass --f {檔案名稱}，可指定單一檔編譯。
4. gulp clearCss 可清除所有編譯完的css。
5. gulp clearCss --f {檔案名稱}，可刪除單一 css。


# open page
http:127.0.0.1:8080/index.jsp

# 備註
1. 以上指令若有發生問題 試試加上 sudo
2. 使用maven並不受限於使用什麼IDE，但是建議使用intellij.

# 還未完成部分
1. gulp 的 script 
2. 前端工程架構 less bable livereload.
3. DB Connections interface.  (mongodb and T sql db)


# mac 
brew 所安裝的 maven 路徑會在   
/usr/local/Cellar/maven/{version}/   
/usr/local/Cellar/maven/3.2.3/libexec/conf$   

repository路徑在：   
~/.m2/repository$ 

