/*
    游戏交互
        A】长按屏幕事件
        B】离开屏幕事件
*/
    // 手机长按事件
    $('section').on('touchstart',function(event) {
        event.preventDefault();
        if(gameTouch){
            // 竿变大
            (function boxWidth(obj) {
                obj.h++;
                that = $(".select").parent();
                $(".select").css({
                    "height": obj.h + "px",
                    "top": obj.h * ( - 1) + "px"
                });
                obj.timer = setTimeout(function() {
                    boxWidth(obj);
                },7)
            })(sofarH)
        }else{
            return;
        }
    });
    // 手指离开事件
    $('section').on('touchend',function(event) {
        if(!gameTouch){
            return;
        }
        gameTouch = false;
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
        if (sofarH.h >= minWidth && sofarH.h <= maxWidth) { //过杠
            stageBox();
            sofarH.count += 100;
            $(".count").html(sofarH.count);
            setTimeout(function() {
                sofarH.h = 0;
                
                $(".hero").animate({
                    "left": successLeft
                },400);
                that.next().children('em').addClass('select option-em');
                sofarH.sL = sofarH.sL + parseInt(that.width()) + minWidth;
                $(".stage").animate({
                    "scrollLeft": sofarH.sL
                },400,function(){
                    
                })
                gameTouch = true;
            },400)
            return;
        } else { 
            //掉下去
            $(".hero").animate({
                "left": failLeft
            },400);
            // 最高记录写入
            if(heroMaxCount<sofarH.count){
                heroMaxCount = sofarH.count;
                saveCookie(userName + "-heroMaxCount",heroMaxCount);
                $(".max-count").html("最高分：" + heroMaxCount);
                // 发送请求到服务器保存最高记录 
                // $.get("/updateData/insert?token=" + token + "&data=" + sofarH.count,function(data){
                $.ajax({
                    url: host + '/updateData/data',
                    data: {
                        "token":gameToken,
                        "data":heroMaxCount,
                        "userName": userName
                    },
                    dataType: 'jsonp',
                    cache: false,
                    timeout: 5000,
                    // jsonp 字段含义为服务器通过什么字段获取回调函数的名称
                    jsonp: 'callback',
                    // 声明本地回调函数的名称，jquery 默认随机生成一个函数名称
                    jsonpCallback: 'jsonpMaxCount',
                    success: function(data) {
                        if(data.meta.code == 200){
                            console.log("write success");
                        }
                    }
                    
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
            $(".begin-test").html("重新开始").removeAttr("disabled").on("click",function() {
                // window.location.reload();
                gameLayoutFun();
            })
        }
    });
    // 手机移动事件
    $("section").bind('touchmove',function(event) {
        event.preventDefault();
    });
/**
    查看排行：
        只显示排行前10名
*/
    $(".ranking-btn").on('click', function(event) {
        event.preventDefault();
        /* Act on the event */
        $(".ranking-list").html("")
        $.ajax({
            url: host + '/getData/list',
            dataType: 'jsonp',
            cache: false,
            timeout: 5000,
            // jsonp 字段含义为服务器通过什么字段获取回调函数的名称
            jsonp: 'callback',
            // 声明本地回调函数的名称，jquery 默认随机生成一个函数名称
            jsonpCallback: 'jsonpList',
            success: function(data) {
                if(data.meta.code == 200){
                    var listData = data.data.sort(compare("data")).reverse();
                    listData.forEach(function(v,i){
                        if(i<9){
                            $(".ranking-list").append("<li>" + v.userName + " : " + v.data + "</li>")
                        }
                    })
                }
            }
        })
    });
/**
    加载皮肤列表
*/
    $(".buy-btn").on('click', function(event) {
        console.log("点击购买皮肤列表");
        event.preventDefault();

        /* Act on the event */
        $("#modalBox").html("")
        $.ajax({
            url: host + '/getData/derma',
            dataType: 'jsonp',
            cache: false,
            timeout: 5000,
            // jsonp 字段含义为服务器通过什么字段获取回调函数的名称
            jsonp: 'callback',
            // 声明本地回调函数的名称，jquery 默认随机生成一个函数名称
            jsonpCallback: 'jsonpDerma',
            success: function(body) {
                if(body.meta.code == 200){
                    var dermaArr = body.data;
                    dermaArr.forEach(function(v,i){
                        $("#modalBox").append('<div class="radio"><label><input type="radio" name="optionsRadios" id="optionsRadios' + v.id + '" value="' + v.id + '"><span class="derma-name">' + v.name + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="derma-prices">' + v.prices + '</span></label></div>');
                    })
                }
            }
        })
    });
/*
    购买皮肤
        A】总共7种皮肤（1种系统默认）
        B】点击购买:
            对照本地cookie进行物品是否以购买判断
            1）未购买：
                发送购买请求，写入cookie，关闭购买界面
            2）已购买:
                切换改皮肤，关闭交易界面。
*/  
    $("#buyBtn").on('click touchstart', function(event) {
        event.preventDefault();
        /* Act on the event */
        var dermaId =  $('#myModal input:radio:checked').val();
        console.log(dermaId);
        buyDermaIdEd = JSON.parse($.cookie(userName + "-derma"));
        console.log(buyDermaIdEd);
        if(!dermaId){
            alert("请选择商品");
        }else{
            
            if(judgeDermaId(dermaId,buyDermaIdEd)){
                console.log("正在购买");
                getUGToken();
                $.ajax({
                    url: host + '/insertOrder/' + gameToken ,
                    data: {
                        "derma":dermaId
                    },
                    dataType: 'jsonp',
                    cache: false,
                    timeout: 5000,
                    // jsonp 字段含义为服务器通过什么字段获取回调函数的名称
                    jsonp: 'callback',
                    // 声明本地回调函数的名称，jquery 默认随机生成一个函数名称
                    jsonpCallback: 'jsonpBuy',
                    success: function(body) {
                        if(body.meta.code == 200){
                            // 获取地址进行转账
                            console.log(body.data.seller);
                            var tradeId = body.data.orderId
                            var seller = body.data.seller
                            
                            var prices = $('#modalBox input:radio:checked').siblings('span.derma-prices').html();
                            // prices = web3.toWei(+prices,"ether");
                            console.log(prices);
                            console.log("###########");
                            
                            console.log("支付金额："+prices+"\r\n订单号："+tradeId+"\r\n支付地址："+seller+"\r\n本账号地址："+gameAddress)
                            console.log("###########");
                            console.log(GAME_ID);
                            console.log(prices); 
                            console.log(gameAddress);
                            setTimeout(function(){
                                $(".hero-mask").fadeIn(100, function() {
                                    $(".mask-content").html("购买皮肤进行中...")
                                });
                            },0)
                            
                            // 发起支付
                            pay(GAME_ID,tradeId,seller,prices,gameAddress,function(err,data1){
                                console.log("支付成功");
                                console.log(err);
                                console.log(data1);
                                // 查询订单状态
                                if(data1){
                                    queryStatus(tradeId,dermaId,buyDermaIdEd)
                                }else{
                                    $(".hero-mask").fadeIn(100, function() {
                                        $(".mask-content").html("购买失败")
                                    });
                                    setTimeout(function(){
                                        $(".hero-mask").fadeOut(100, function() {
                                            $(".mask-content").html("")
                                        });
                                    },500)
                                    
                                }
                                
                                $('#myModal').modal('hide');
                                $(".ug").html("账户余额："+getUGToken());
                                // 查询订单状态
                                
                            });
                            
                        }
                    }
                })
            }else{
                alert("已购买过此皮肤");
                $("section").css({
                    "background": "url(/images/hero-b" + $('#modalBox input:radio:checked').val() + ".png) no-repeat",
                    "background-size": "100% 100%"
                });
            }
        }
        $(".game-tip").on('click', function(event) {
            event.preventDefault();
            /* Act on the event */
            console.log("余额查询")

            // etBalance()
        });

    });
/*
    点击开始游戏按钮
*/
    $(".begin-test").on("click",function(event) {
        event.preventDefault();
        event.stopPropagation();
        // 开始按钮消失
        // $(this).fadeOut('0').siblings().fadeIn(400);
        gameTouch = true;
        $(this).html("游戏中...").attr("disabled","disabled");
    });
/*
    创建账号
        A】获取钱包地址。
        B】密语写入cookie，用户误操作刷新，直接进入游戏界面
        c】进入游戏按钮界面
*/
    $("#creatAccount").on('click', function(event) {
        event.preventDefault();
        /* Act on the event */
        console.log("进入创建账号流程");
        initWallet();
        // $.cookie("randomSeed",randomSeed);
        $("#userName").addClass('name-status');
    });
/*
    导入账号
        A】弹窗 输入密语
        B】获取钱包地址
        C】进入游戏按钮界面
*/
    $("#channelAccount").on('click', function(event) {
        event.preventDefault();
        /* Act on the event */
        // restoreWalletFromSeed()
        heroObj.modal("#seedTip","show",function(){

        });
        console.log("输入密语流程");
        console.log()
    });
/**
    密语解密
*/
    $("#seedBtn").on('click', function(event) {
        event.preventDefault();
        /* Act on the event */
        if($("#inputSeed").val()){
            restoreWalletFromSeed();
        }else{
            console.log("error");
        }
    });
/**
    进入游戏按钮点击事件
*/
    $("#EnterBtn").on('click', function(event) {
        event.preventDefault();
        /**
            A】创建账号
                a)请求携带用户名获取token，得到token后获取用户信息，进入游戏界面进行渲染    #携带用户名
            B】导入账号
                a)没有token: 弹出输入框，请求携带用户名获取token                         #携带用户名
                b)一个token：获取用户信息直接进入游戏
                c)多个token：选择token进入游戏
        */
        if(gameToken){
            /**
                用户已经获取到token
                用户信息写入事件
            */
            console.log("有token")
            userName = $("#userName").val();
            console.log("输入用户名"+userName);
            
            if($("#userName").hasClass('name-status')){
                // 创建新token
                if(userName){
                    console.log(userName);
                    getUserData(gameToken,userName);
                }else{
                    alert("请输入用户名");
                }
            }else{
                getUserData(gameToken,userName);
            }

            
            

           
            
        }else{
            // 没有token
            console.log("没有token");
            if($("#userName").hasClass('name-status')){
                userName = $("#userName").val();
                if(userName){
                    // 初始化token
                    setTimeout(function(){
                        $(".hero-mask").fadeIn(0, function() {
                            $(".mask-content").html("进入游戏...");
                        });
                    },0)
                    
                    initGameToken(gameAddress,GAME_ID,channel,userName,function(error,token){
                        console.log("新建账号 TODO")
                        if(error != null){
                            console.log(error)
                            heroObj.modal("#tip","show",function(){
                                $("#tip-content").html(error);
                            });

                            
                        }else{
                            
                            getPlayerToken(gameAddress,GAME_ID,channel,function(error,token){
                                // console.log(token);
                                console.log(web3.toUtf8(token[token.length-1].alias))
                                console.log("获取第" + token.length + "个token");
                                console.log(token); 
                                gameToken = token[token.length-1].token;
                                // heroObj.modal("#tip","show",function(){
                                //     $("#tip-content").html("token：" + gameToken);
                                // });
                                importCreat = 0;
                                console.log("获取新token,直接跳转");
                                if(gameToken){
                                    console.log("新token直接进入游戏");
                                    // heroObj.modal("#tip","hide",function(){
                                    //     console.log(gameToken);
                                    // });
                                    getUserData(gameToken,userName);
                                }

                            })
                        }
                        setTimeout(function(){
                            $(".hero-mask").fadeOut(0, function() {
                                $(".mask-content").html("")
                            });
                        },1)
                        
                        
                    },importCreat)
                }else{
                    alert("请输入用户名");
                }
            }else{
                heroObj.modal("#tokenTip","show",function(){
                    console.log("开启token数组弹窗,提供用户选择");
                });
            }
            
            
        }
        
    });
/*
    监听出售
*/
    $("#pricesBtn").on("click",function(){
        var prices = $("#inputPrices").val();

        console.log("售出价格："+prices);
        // $("#sellTip").modal("hiden");
        $.ajax({
            url: host + '/updateData/' + gameToken + '/onSelling',
            dataType: 'jsonp',
            data: {
                "prices": prices
            },
            cache: false,
            timeout: 5000,
            // jsonp 字段含义为服务器通过什么字段获取回调函数的名称
            jsonp: 'callback',
            // 声明本地回调函数的名称，jquery 默认随机生成一个函数名称
            jsonpCallback: 'jsonpPrices',
            success: function(body) {
                console.log(body);
                alert("出售成功");
                $("#sellTip").modal("hide");
                // 游戏初始化
                gameLayoutFun();
                $(".begin-test").html("出售中...").attr("disabled","disabled");
                $(".init-account").show();
                $("#getTokenBtn").click();
                getToken();
                // getToken();
            }
        })
    });
/**
    token列表   创建新账户按钮
*/
$("#initTokenBtn").on('click', function(event) {
    event.preventDefault();
    /* Act on the event */
    importCreat = 1;
    heroObj.modal("#tokenTip","hide",function(){
        console.log("关闭弹窗，创建新token");
    });
    $("#userName").addClass('name-status')
});


    