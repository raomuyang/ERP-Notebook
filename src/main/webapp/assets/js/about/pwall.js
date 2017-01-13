/**
 * Created by Raomengnan on 2016/5/18.
 */
$(function () {
    'use strict';
    var start=0
    var end=0
    $.ajax('../../context/picture/start.txt').done(function(res){
        start = res
    })
    $.ajax('../../context/picture/end.txt').done(function(res){
        end = res
        for(var y = end; y >= start;y--){
            loadPhoto(y)
        }
    })

    function loadPhoto(year){
        var $year = $('<div/>').css("font-size","large")
        $year.css("padding-left","1em")
        $year.html(year+"年-----------")
        $("#years").append($year)

        var $gra = '<ul id="'+year+'" ></ul>'
        $("#years").append($gra)

        var $hr = $("<hr>")
        $("#years").append($hr)

        $.ajax({url: '../../context/picture/'+ year +'/number.txt',context: year}).done(function (count) {
            for (var i = 1; i <= count; i++) {
                var $a = $('<a/>').attr('href', '##');
                var $div = $('<div/>').append($('<span/>').text(""));
                var $img = $('<img/>').attr('src', '../../context/picture/'+year+"/" + i + '.jpg');
                $('#'+year).append($('<li/>').append($a.append($div).append($img)));
            }
        });
    }





});
