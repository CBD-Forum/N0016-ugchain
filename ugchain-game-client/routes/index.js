var express = require('express');
var router = express.Router();
 
/* GET home page. */
router.get('/', function (req, res, next) {
    res.render('index', {
        title: 'Express'
    });
});
router.get('/test1', function (req, res, next) {
    console.log(req.query);
    // 伪数据
    var data = {
        "age": "25"
    }
 
    var callback = req.query.callback + "(" + JSON.stringify(data) + ")"
    console.log(callback);
    res.send(callback)
});

var data = {
    "meta": {
        "code": 200,
        "message": "success"
    },
    "data": [
        {
            "userName":"Mr.Sofar",
            "userTokenId": 0,
            "token": "4bd9f7a2-71f8-4077-9b19-0f5d431ad04d",
            "data": "10",
            "createTime": 1493113725000,
            "updateTime": 1493113725000,
            "status": 1,
            "derma":"0"
        },
        {   
            "userName":"菜花",
            "userTokenId": 0,
            "token": "af7b946d-b653-4d7d-b045-b6964d8a668e",
            "data": "100",
            "createTime": 1493105884000,
            "updateTime": 1493105884000,
            "status": 1,
            "derma":"0"
        },
        {
            "userName":"魔术手",
            "userTokenId": 0,
            "token": "80b23610-2d5a-4585-82bb-553c223d9a99",
            "data": "200",
            "createTime": 1493113725000,
            "updateTime": 1493113725000,
            "status": 1,
            "derma":"0"
        },
        {
            "userName":"麦霸",
            "userTokenId": 0,
            "token": "f67a91bc-64a1-4593-bf31-c2f7f08768fa",
            "data": "300",
            "createTime": 1493113725000,
            "updateTime": 1493113725000,
            "status": 1,
            "derma":"0"
        }
        
    ]
};
router.get('/getData/:id', function (req, res, next) {
    if(req.url.indexOf("list") >= 0){
        res.send(data);
    }else{
        data.data.forEach(function(v,i){
            if(v.token == req.url.substring(9,1000)){
                res.send({"meta":{"code":200,"message":"success"},"data":v})
            }
        })
    }
    
});
router.get('/updateData/insert', function (req, res, next) {
    var insertData = {};
    for(i in req.query){
        insertData[i] = req.query[i]
    }
    data.data.forEach(function(v,i){
        
        if(v.token == insertData.token){
            v.data = insertData.data;
            res.send({
                "meta": {
                    "code": 200,
                    "message": "success"
                },
                "data": ""
            })
        }
    })
});

router.get('/updateData/derma', function (req, res, next) {
    var dermaData = {};
    for(i in req.query){
        dermaData[i] = req.query[i]
    }
    data.data.forEach(function(v,i){
        if(v.token == dermaData.token){
            v.derma = dermaData.derma;
            res.send({
                "meta": {
                    "code": 200,
                    "message": "success"
                },
                "data": ""
            })
        }
    })
});
 
module.exports = router;