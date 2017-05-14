/*
    账号实现逻辑
        1.创建账号

        2.导入账号
    游戏整体实现逻辑
        1.游戏初始化
            a)页面初始化
            b)数据初始化
        2.开始游戏
            a)交互
            a)得分
            b)背景
        3.游戏结束
            a)菜单窗口
        4.商店
            a)皮肤
        5.排行
            a)Top10
*/
    // test
    // $.cookie("randomSeed","maid summer poet photo decrease immense rude float feel speed chimney foil");
    // fan
    // $.cookie("randomSeed","someone silver autumn tumble fan door rally limit range behind verify clerk");

/*
    游戏初始化
*/
    gameLayoutFun();
    // 获取gameId
    getGameId()
/*
    开始游戏  
*/
    $(document).ready(function($) {
        /*
            如果存在token直接进入游戏;
        */
        // if($.cookie("gameToken")){
        //     console.log("#####");
        //     gameAddress = $.cookie("gameAddress",gameAddress);
        //     gameToken = $.cookie("gameToken",gameToken);
        //     getUserData(gameToken,userName);
        //     // // restoreWalletFromSeed();
        //     // restoreWallet($.cookie("randomSeed"),function(err,address){
        //     //     if(err == null){
        //     //         gameAddress = address;
        //     //         console.log(gameAddress);
        //     //         getToken();
        //     //         // heroObj.modal("#adressTip","show",function(){
        //     //         //     $("#adress-content").html("导入成功账号地址为：<span class='text-primary'>"+address+"</span>");
        //     //         //     console.log("地址：" + address);
        //     //         //     // console.log("密语：" + $.cookie("randomSeed"));
        //     //         // });
        //     //     }else{
        //     //         alert(err)
        //     //     }
        //     // })
        // }
        
        that = $(".select").parent();
        // 监听模态框隐藏方法
        $("#adressTip").on("hide.bs.modal",function(){
            if(gameAddress){
                $(".init-before").hide();
                $(".init-after").show();
                // getToken();
            }
        });
        // 获取token数组
        $("#getTokenBtn").on('click', function(event) {
            event.preventDefault();
            console.log("获取token数组");
            console.log(gameTokenArr);
            if(gameTokenArr.length>0){
                // 关闭当前弹窗，弹出token提供用户选择
                heroObj.modal("#adressTip","hide",function(){
                    console.log("关闭地址弹窗");
                });
                heroObj.modal("#tokenTip","show",function(){
                    console.log("开启token数组弹窗,提供用户选择");
                });
            }else{
                heroObj.modal("#adressTip","hide",function(){
                    console.log("关闭地址弹窗");
                });
                // 关闭所有弹窗，输入初始化token
                console.log("###")
            }
            // 对获取的token进行判断
            // 
        });
        // 选择token
        $("#chooseTokenBtn").on("click",function(){
            gameToken =  $('#tokenTip input:radio:checked').val();
            console.log(gameToken);
            if(!gameToken){
                alert("请选择游戏账户");
            }else{
                heroObj.modal("#tokenTip","hide",function(){
                    console.log(gameToken);
                });
                getUserData(gameToken,userName);

            }
        });
        // 新token直接进入游戏
        $("#newToken").on('click', function(event) {
            event.preventDefault();
            /* Act on the event */
            console.log("获取新token,直接跳转");
            // if(gameToken){
            //     console.log("新token直接进入游戏");
            //     heroObj.modal("#tip","hide",function(){
            //         console.log(gameToken);
            //     });
            //     getUserData(gameToken,userName);
            // }
        });
        

    });