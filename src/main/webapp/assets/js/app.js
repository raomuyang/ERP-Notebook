$(function () {
    'use strict';

    $('[data-blur]').each(function () {
        $(this).on('mouseenter', 'li', function () {
            $(this).find('img').css('-webkit-filter', 'blur(5px)');
        }).on('mouseleave', 'li', function () {
            $(this).find('img').css('-webkit-filter', '');
        });
    });
});
