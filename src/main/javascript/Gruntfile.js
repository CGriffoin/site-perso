module.exports = function(grunt) {

    require('load-grunt-tasks')(grunt);

    grunt.initConfig({

        assetsDir: '../webapp',
        distDir: 'dist',

        'bower-install': {
            target: {
                html: '<%= assetsDir %>/index.html',
                ignorePath: '<%= assetsDir %>/',
                jsPattern: '<script type="text/javascript" src="{{filePath}}"></script>'
            }
        },
        clean: {
            dist: ['.tmp', '<%= distDir %>']
        },
        copy: {
            dist: {
                files: [{
                    expand: true,
                    dot: true,
                    cwd: '<%= assetsDir %>',
                    dest: '<%= distDir %>/',
                    src: [
                        'index.html'
                    ]
                }]
            },
            minApp: {
                files: [{
                    cwd: '.tmp/concat/js/',
                    src: ['*.app.js'],
                    dest: '<%= assetsDir %>/js/',
                    expand: true
                }]
            },
            minHtml: {
                files: [{
                    cwd: '<%= distDir %>/',
                    src: ['index.html'],
                    dest: '<%= assetsDir %>/',
                    expand: true
                }]
            }
        },
        useminPrepare: {
            html: '<%= assetsDir %>/index.html'
        },
        usemin: {
            html: '<%= distDir %>/index.html'
        },
        rename: {
            main: {
                files: [{
                    src: ['<%= assetsDir %>/index.html'],
                    dest: '<%= assetsDir %>/index.html.save'
                }]
            }
        },
        rev: {
            dist: {
                files: {
                    src: [
                        '.tmp/concat/js/{,*/}*.js'
                    ]
                }
            }
        }

    });

    grunt.registerTask('package', ['clean', 'useminPrepare', 'copy:dist', 'concat', 'rev', 'usemin', 'copy:minApp', 'rename', 'copy:minHtml']);
    grunt.registerTask('default', ['package']);


};
