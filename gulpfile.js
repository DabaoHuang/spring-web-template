
const gulp    = require('gulp');            //工作管理
const gutil   = require('gulp-util');
const fs      = require('fs');              //檔案讀取
const del     = require('del');             //檔案刪除
const babel   = require('gulp-babel');      //babel   轉譯
const coffee  = require('gulp-coffee');     //coffee  轉譯
const sass    = require('gulp-sass');       //sass    轉譯
const concat  = require('gulp-concat');     //合併檔案
const rename  = require('gulp-rename');     //更名工具
const uglify  = require('gulp-uglify');     //壓縮js工具
const cssnano = require('gulp-cssnano');    //壓縮css工具
const browserify = require('gulp-browserify');


const versionRegex = /^version=(\S+)/m;
const version = fs.readFileSync('./src/main/resources/frontEnd.properties', 'utf8').toString().match(versionRegex)[1];

//path
const path = {};
      path.root     = './src/main';
      path.webapp   = './src/main/webapp'
      path.dist     = './src/main/webapp/dist';
      path.js       = './src/main/webapp/dist/' + version + '/js';
      path.css      = './src/main/webapp/dist/' + version + '/css'; 
      path.resource = './src/main/webapp/resource';
      path.babel    = './src/main/webapp/babel';
      path.coffee   = './src/main/webapp/coffee';
      path.sass     = './src/main/webapp/sass';
      path.temp     = './src/main/webapp/temp';
      path.tempjs   = './src/main/webapp/temp/js';
      path.clean    = './src/main/webapp/dist/' + version;
      path.cleanTmp = './src/main/webapp/temp/**/'; 


//browserify 將有使用到 require 部分合併
var browserifyTask = function(){
     return gulp.src( path.tempjs+'/**/*.js' , {read: false})
                .pipe(browserify({extensions: ['.js']}))
                .pipe(gulp.dest( path.js ))
};

//compile babel
gulp.task('babel', function() {
    //del(path.tempjs+'/**/');
    return gulp.src(path.babel+'/**/*.js')
                .pipe(babel({presets: ['es2015']}))
                //.pipe(babel())
                .pipe(gulp.dest( path.tempjs  ));
});


//compile coffee
gulp.task('coffee',function() {
    return gulp.src(path.coffee+'/**/*.coffee')
               .pipe(coffee({bare: true}).on('error', gutil.log))
               .pipe(gulp.dest( path.tempjs  ));
});

//compile sass
gulp.task('sass', function () {
  return gulp.src( path.sass+'/**/*.scss')
             .pipe(sass().on('error', sass.logError))
             .pipe(gulp.dest(path.css ));
});


//group task
gulp.task('build', ['babel','coffee' , 'sass'], function(){
    browserifyTask();
});



//預設動作
/*
gulp.task('default',function(){

});*/