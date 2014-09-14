var gulp = require('gulp'),
    clean = require('gulp-clean'),
    sass = require('gulp-ruby-sass'),
    minifycss = require('gulp-minify-css'),
    rename = require('gulp-rename'),
    notify = require('gulp-notify'),
    concat = require('gulp-concat'),
    minifyHTML = require('gulp-minify-html'),
    livereload = require('gulp-livereload'),
    source = require('vinyl-source-stream'),
    browserify = require('browserify'),
    reactify = require('reactify');

var browserSync = require('browser-sync');
var reload = browserSync.reload;

gulp.task('browser-sync', function () {
    browserSync({
        server: {
            baseDir: "./build"
        }
    });
});

gulp.task('clean', function () {
    return gulp.src(['build/*'], {
        read: false
    }).pipe(clean());
});

// Styles
gulp.task('styles', function () {
    return gulp.src('public/src/styles/**/*.scss')
        .pipe(sass({
            style: 'expanded',
        }))
        .pipe(concat('main.css'))
        .pipe(gulp.dest('build/styles'))
        .pipe(rename({
            suffix: '.min'
        }))
        .pipe(minifycss())
        .pipe(gulp.dest('build/styles'));
});

gulp.task('scripts', function () {
    var b = browserify();
    b.transform(reactify); // use the reactify transform
    b.add('./public/src/scripts/index.js');

    return b.bundle()
        .pipe(source('main.js'))
        .pipe(gulp.dest('./build/scripts'));
});

gulp.task('markup', function () {
    return gulp.src('public/index.html')
        .pipe(minifyHTML({
            conditionals: true
        }))
        .pipe(gulp.dest('build/'))
        .pipe(notify({
            message: 'Markup task complete'
        }));
});

// Watch
gulp.task('watch', function () {

    // Watch .scss files
    gulp.watch('public/src/styles/**/*.scss', ['styles', reload]);

    // Watch .js files
    gulp.watch('public/src/scripts/**/*.js', ['scripts', reload]);

    // Watch .js files
    gulp.watch('public/**/*.html', ['markup', reload]);

    // Create LiveReload server
    //livereload.listen();

    // Watch any files in dist/, reload on change
    //gulp.watch('build/**', [reload]);

});

gulp.task('build', ['styles', 'scripts', 'markup']);

// Default task
gulp.task('default', ['clean', 'build', 'browser-sync', 'watch']);