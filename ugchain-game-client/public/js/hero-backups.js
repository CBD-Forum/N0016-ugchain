/*
       *1.游戏初始化
       *2.
       *3.
       *4.
       * 
       */
// window['sofarH'] = {
//   h: 0,         //竿的高度
//   timer : null,       //计时器
//   that : null,      //竿的父级容器
//   sL : 0,   //横向滚动距离
//   count : 0,         //游戏得分
//   liW ：null,  //站台的宽度
//   liM : null //站台的margin-left
// }

// 站台生成
function stageBox() {
    $(".stage-list").append('<li style="width:' + sofarW[parseInt(Math.round(Math.random() * (sofarW.length - 1)))] + ';margin-left:' + sofarM[parseInt(Math.round(Math.random() * (sofarM.length - 1)))] + '"><em></em></li>');
}
// 节点生成
function dom() {
    // console.log("dom");
    $("section").css({
        "background": "url(/images/hero-b" + parseInt(Math.round(Math.random() + 1)) + ".jpg) no-repeat",
        "background-size": "100% 100%"
    });
    $(".hero").css({
        "left": $(".stage-list li").eq(0).width() - $(".hero").width(),
        "top": ( - 1) * 24 + "px"
    });
    $(".begin").css({
        "left": ($(window).width() - $(".begin").width()) / 2
    });
    // stageBox();
}
// 游戏初始化
function gameInit() {
    window['sofarH'] = {
        h: 0,//竿的高度
        timer: null,//计时器
        // that : {},      //竿的父级容器
        sL: 0,//横向滚动距离
        count: 0 //游戏得分
    }
    $('.stage-list').html('<div class="hero"></div><li><em class="option-em select"></em></li>')

    // $(".stage").css({
    //   "scrollLeft":0
    // });
}
$(document).ready(function() {
    gameInit();
    stageBox();
    dom();
    // 点击开始
    $(".begin-btn").fadeIn(1000).click(function(event) {
        event.stopPropagation();
        // 开始按钮消失
        $(this).fadeOut('0').siblings().fadeIn(400);
        // 按住屏幕使竿变长
        $('section').on('touchstart',function(event) {
            event.preventDefault();
            // 竿变大
            (function boxWidth(obj) {
                // console.log(obj.h);
                obj.h++;
                // console.log(height);
                that = $(".select").parent();
                $(".select").css({
                    "height": obj.h + "px",
                    "top": obj.h * ( - 1) + "px"
                });
                obj.timer = setTimeout(function() {
                    boxWidth(sofarH);
                },7)
            })(sofarH)
        });
        // 手指离开屏幕
        $('section').on('touchend',function(event) {
            event.preventDefault();
            // 清计时器
            clearInterval(sofarH.timer);
            // 清除按住事件
            $("section").off("touchstart", this, false);
            // 顺时针旋转90度 并且移除select类名
            $(".option-em").css({
                "transition": "0.3s",
                "transform": "rotate(90deg)"
            }).removeClass('select');
            // 判断杠的长度是否在合理范围内
            var minWidth = parseInt(that.next().css("margin-left")); //竿的最小长度
            var maxWidth = minWidth + that.next().width(); //竿的最大长度
            var successLeft = parseInt($(".hero").css("left")) + maxWidth; //成功过杠
            var failLeft = parseInt($(".hero").css("left")) + sofarH.h; //掉下去
            // console.log(maxWidth);
            // console.log(minWidth);
            if (sofarH.h >= minWidth && sofarH.h <= maxWidth) { //过杠
                console.log("过杠");
                stageBox();
                sofarH.count++;
                $(".count").html(sofarH.count);
                // console.log(sofarH.sL + "sofarH.sL");
                setTimeout(function() {
                    sofarH.h = 0;
                    $(".hero").animate({
                        "left": successLeft
                    },400);
                    // that.removeClass('class name')
                    that.next().children('em').addClass('select option-em');
                    sofarH.sL = sofarH.sL + parseInt(that.width()) + minWidth;

                    $(".stage").animate({
                        "scrollLeft": sofarH.sL
                    },400)
                },400)

                return;
            } else { //掉下去
                console.log("掉下去");
                // console.log(sofarH.h);
                $(".hero").animate({
                    "left": failLeft
                },400);
                // 最高值写入
                console.log(heroMaxCount);
                console.log(sofarH.count);
                
                if(heroMaxCount<sofarH.count){
                	$.cookie("heroMaxCount", sofarH.count, {
			            expires: 30,
			            path: '/'
			        })
                }
                
                setTimeout(function() {
                    that.children(".option-em").css({
                        "transition": "0.3s",
                        "transform": "rotate(185deg)"
                    });
                    $(".hero").animate({
                        "top": 10000 + "px"
                    },400);
                },400)
                // 失败后出现重新开始，点击重新开始
                $(".begin-btn").fadeIn(400).css({
                    "fontSize": "18px"
                }).html("重新开始").on("click",function() {
                    window.location.reload();
                }).siblings().fadeOut('400');
            }
        });
        $("section").bind('touchmove',function(event) {
            event.preventDefault();
        });
    });

});

$(".begin-btn").on('click',function(event) {
    event.preventDefault();
    /* Act on the event */
    if ($.cookie("heroMaxCount") === undefined) {
        $.cookie("heroMaxCount", heroMaxCount, {
            expires: 30,
            path: '/'
        })
    } else {
        heroMaxCount = $.cookie("heroMaxCount");
    }
});
/*
             *实现逻辑
             *1.页面的布局
             *2.游戏初始化
             *3.点击开始游戏
             *4.游戏结束，点击重新开始，游戏重新初始化
             */

// $.cookie("heroMaxCount",)
