/**
 * Created by Raomengnan on 2016/5/18.
 */
$(function () {
    'use strict';
    var start=0
    var end=0
    $.ajax('../../context/photo/start.txt').done(function(res){
        start = res
    })
    $.ajax('../../context/photo/end.txt').done(function(res){
        end = res
        for(var y = end; y >= start;y--){
            loadPhoto(y)
        }
    })

    function loadPhoto(year){
        var $year = $('<div/>').css("font-size","large")
        $year.css("padding-left","1em")
        $year.html(year+"级")
        $("#years").append($year)

        var $gra = '<ul id="'+year+'" data-blur ></ul>'
        $("#years").append($gra)

        var $hr = $("<hr>")
        $("#years").append($hr)
        $.ajax({url: '../../context/photo/'+ year +'/number.txt',context: year}).done(function (count) {
            for (var i = 1; i <= count; i++) {
                $.ajax({url: '../../context/photo/'+ year +'/' + i + '/index.txt', context: i}).done(function (content) {
                    var title = content.split('\n', 1)[0];

                    var $a = $('<a/>').attr('href', '##');
                    var $div = $('<div/>').append($('<span/>').text(title));
                    var $img = $('<img/>').attr('src', '../../context/photo/'+year+"/" + this + '/1.jpg');
                    $('#'+year).append($('<li/>').append($a.append($div).append($img)));

                });
            }
        });
    }





});
