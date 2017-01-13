$(function () {
    'use strict';

    $.ajax('../../context/about/join.txt').done(function (content) {
        var contentArray = content
        var article = content.replace(/\n/g, '<br/>');
        article = article.replace(' ','&nbsp;')
        $('#article').html(article);
    });
});
