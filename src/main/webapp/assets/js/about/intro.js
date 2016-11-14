$(function () {
    'use strict';

    $.ajax('../../context/newsphoto/number.txt').done(function (count) {
        for (var i = count; i >= 1; i--) {
            $.ajax({url: '../../context/newsphoto/' + i + '/index.txt', context: i}).done(function (content) {
                var title = content.split('\n', 1)[0];

                var $a = $('<a/>').attr('href', '../../info/news/news.html?id=' + this);
                var $div = $('<div/>').append($('<span/>').text(title));
                var $img = $('<img/>').attr('src', '../../context/newsphoto/' + this + '/1.jpg');
                $('#gallery').append($('<li/>').append($a.append($div).append($img)));

                var $a1 = $('<a/>').attr('href', '../../info/news/news.html?id=' + this);
                $a1.html(title)
                $('#fu-nav').append($('<li/>').append($a1))

                var $a2 = $('<a/>').attr('href', '../../info/news/news.html?id=' + this);
                $a2.html(title);
                var $dorpitem = $('<li/>').append($a2)
                $('#select').append($dorpitem)
            });
        }
    });


});
