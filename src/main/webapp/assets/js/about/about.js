$(function () {
    'use strict';

    var id = location.search.match(/id=(\d+)/)[1];

    $.ajax('../../context/newsphoto/' + id + '/index.txt').done(function (content) {
        var contentArray = content.split('\n', 2);
        var title = contentArray[0];
        var article = content.substring(title.length + 4).replace(/\n/g, '<br/>');

    document.title = title;
    $('#title').text(title);
    $('#article').html(article);
});

    $.ajax('../../context/newsphoto/' + id + '/number.txt').done(function (count) {
        for (var i = 1; i <= count; i++) {
            $('#gallery').append($('<li/>').append($('<img/>').attr('src', '../../context/newsphoto/' + id + '/' + i + '.jpg')));
        }
    });


    $.ajax('../../context/newsphoto/number.txt').done(function (count) {
        for (var i = count; i >= 1; i--) {
            $.ajax({url: '../../context/newsphoto/' + i + '/index.txt', context: i}).done(function (content) {
                var title = content.split('\n', 1)[0];

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
