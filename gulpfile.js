/**
 * Created by ocean on 2015/11/11.
 */
//var gulp = require('gulp');
var gulp = require('gulp-param')(require('gulp'), process.argv);

var gutil = require('gulp-util');               //

//var minifyCSS = require('gulp-minify-css');     //css 壓縮
//var minJS   = require('gulp-uglify');           //js 壓縮
//var fileInclude = require('gulp-file-include'); //
var rename = require('gulp-rename');            //
//var liveReload = require('gulp-livereload');    //
//var fs = require('fs');                         //
//var path = require('path');                     //
var del = require('del');                         //檔案刪除plugin
//var coffeeCompile = require('gulp-coffee');     //CoffeeScript Compile
//var babelCompile = require('gulp-babel');       //Babel Compile
//var concat = require("gulp-concat");            //file concat
var less = require('gulp-less');                //less compile
var sass = require('gulp-sass');

var webapp     = "src/main/webapp/";
var cssPath    = webapp.concat("dist/css/");
var sassParent = webapp.concat("sass/");
var sassPath   = sassParent.concat("**/*.scss");

//sass 任務項目
//gulp sass or gulp sass --f {your file name}
gulp.task('sass', function(f){
        if(f) {
            return gulp.src(sassParent.concat(f))
                .pipe(sass())
                .pipe(gulp.dest(cssPath));
        }else{
            return gulp.src(sassPath)
                .pipe(sass())
                //.pipe(minifycss())
                .pipe(gulp.dest(cssPath));
        }
});


//清除dist內的css
//gulp clearCss --f {your file name} or gulp clearCss
gulp.task('clearCss', function(f){
        if(f){
            return del([cssPath.concat(f)]);
        }else{
            return del([cssPath]);
        }
});


//預設動作
gulp.task('default',['sass']);