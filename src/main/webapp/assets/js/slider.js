$(function () {
    'use strict';
    $('[data-slider]').each(function () {
        var $this = $(this);
        var data = $this.data('slider');
        $this.css({position: 'relative', overflow: 'hidden'});

        var $images = $this.children();
        $images.css({position: 'absolute', display: 'none', top: 0, left: '100%', transition: 'left 1s'});

        var calcHeight = function ($slider) {
            if ($slider.width() > this.naturalWidth)
                $slider.height(this.naturalHeight);
            else {
                var ratio = this.naturalHeight / this.naturalWidth;
                $slider.height($slider.width() * ratio);
            }
        }.bind($images[0], $this);

        if ($images[0].complete)
            calcHeight();
        else
            $images.eq(0).on('load', calcHeight);

        function slide() {
            var i = slide.i;
            if (i > 0) {
                slide.$images.eq(i - 1).css('left', -slide.$images.eq(i - 1).width() * 2);
                setTimeout(function (i) {
                    slide.$images.eq(i - 1).css('display', 'none').css('left', '100%');
                }.bind(undefined, i), 1000);
            }

            if (i == slide.count)
                slide.i = i = 0;

            slide.$images.eq(i).css('display', '');
            setTimeout(function (i) {
                slide.$images.eq(i).css('left', (slide.$slider.width() - slide.$images.eq(i).width()) / 2);
            }.bind(undefined, i), 10);

            slide.i++;
        }

        slide.$slider = $this;
        slide.$images = $images;
        slide.i = 0;
        slide.count = $images.length;

        slide();
        if (slide.count > 1)
            slide.timer = setInterval(slide, data.interval);

        $(window).on('resize', function () {
            clearInterval(slide.timer);
            calcHeight();
            slide.$images.css({display: 'none', left: '100%'});
            slide.i = 0;
            slide();

            if (slide.count > 1)
                slide.timer = setInterval(slide, data.interval);
        });
    });
});
