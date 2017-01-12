/**
 * Created by raomengnan on 16-11-22.
 */


function req_err(response) {
    console.log(JSON.stringify(response))
    // alert("发生错误")
}


function load_user_info() {

    function req_user_succ(response){
        $(".user-numbers").html(response.length)
        load_data(response)
    }
    user_info_list_by_name("", req_user_succ, req_err)


}

function load_news_info() {
    function req_news_succ(response) {
        $(".news-numbers").html(response.pageCount)
    }
    news_get_page(1, 1, req_news_succ, req_err)
}

function load_mshow() {
    function req_main_succ(response) {
        $(".image-numbers").html(response.iHistory.length)
        $(".video-numbers").html(response.vHistory.length)
    }
    about_mshow_main(req_main_succ, req_err)
}
function load_data(peoples) {

    var reg_time = {}
    for(var i = 0; i < peoples.length; i += 1){
        var str = timeStamp2DataStr(peoples[i].registerTime)
        if(reg_time[str] == null)
            reg_time[str] = 1
        else
            reg_time[str] += 1
    }

    var statistic = [];
    var count1 = 14
    var count2 = 0
    while(count2 < count1 - reg_time.length){
        count2 += 1
        statistic.push([count2, 0])
    }
    // for(count2 =0; count2 += 1; count2 < count1 - reg_time.length){
    //     statistic.push([0, 0])
    // }
    $.each(reg_time, function (e) {
        console.log(count2, reg_time[e])
        count2 += 1
        statistic.push([count2, reg_time[e]]);
    })

    $("#flot-1ine").length && $.plot($("#flot-1ine"), [{
            data: statistic
        }],
        {
            series: {
                lines: {
                    show: true,
                    lineWidth: 2,
                    fill: true,
                    fillColor: {
                        colors: [{
                            opacity: 0.0
                        }, {
                            opacity: 0.2
                        }]
                    }
                },
                points: {
                    radius: 5,
                    show: true
                },
                grow: {
                    active: true,
                    steps: 50
                },
                shadowSize: 2
            },
            grid: {
                hoverable: true,
                clickable: true,
                tickColor: "#f0f0f0",
                borderWidth: 1,
                color: '#f0f0f0'
            },
            colors: ["#65bd77"],
            xaxis:{
                ticks: 5
            },
            yaxis: {
                ticks: 5
            },
            tooltip: true,
            tooltipOpts: {
                content: "register: %x is %y",
                defaultTheme: false,
                shifts: {
                    x: 0,
                    y: 20
                }
            }
        }
    );


}

$(function(){
    load_news_info()
    load_user_info()
    load_mshow()
});