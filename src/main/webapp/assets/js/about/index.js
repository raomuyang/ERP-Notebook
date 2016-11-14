$(function () {
    'use strict';

    $.ajax('context/newsphoto/number.txt').done(function (count) {
        for (var i = count; i >= 1; i--) {
            $.ajax({url: 'context/newsphoto/' + i + '/index.txt', context: i}).done(function (content) {
                var title = content.split('\n', 1)[0];

                var $a = $('<a/>').attr('href', 'info/news/news.html?id=' + this);
                var $div = $('<div/>').append($('<span/>').text(title));
                var $img = $('<img/>').attr('src', 'context/newsphoto/' + this + '/1.jpg');

                if ($('#news2').children().length < 4)
                    $('#news2').append($('<li/>').append($a.clone().append($img.clone())));
                $('#gallery').append($('<li/>').append($a.append($div).append($img)));

                var $a2 = $('<a/>').attr('href', '../../info/news/news.html?id=' + this);
                $a2.html(title);
                var $dorpitem = $('<li/>').append($a2)
                $('#select').append($dorpitem)

                if ($('#news').children().length < 6) {
                    var $a3 = $('<a/>').attr('href', 'info/news/news.html?id=' + this);
                    var $title = $('<span/>').attr("class", "news_title")
                    $title.html(title)
                    $a3.append($title)
                    var $li = $('<li/>').attr("class", "news_item")
                    $li.append($a3)
                    $('#news').append($li)
                }

            });
        }
    });
});
