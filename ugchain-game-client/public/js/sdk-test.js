
var ugTokenABI =
[{"constant":true,"inputs":[],"name":"creator","outputs":[{"name":"","type":"address"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_spender","type":"address"},{"name":"_value","type":"uint256"}],"name":"approve","outputs":[{"name":"success","type":"bool"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"totalSupply","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"address"},{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transferFrom","outputs":[{"name":"success","type":"bool"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"withdraw","outputs":[],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_owner","type":"address"}],"name":"balanceOf","outputs":[{"name":"balance","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transfer","outputs":[{"name":"success","type":"bool"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"transferEther","outputs":[{"name":"","type":"bool"}],"payable":true,"type":"function"},{"constant":false,"inputs":[],"name":"UGCToken","outputs":[],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_owner","type":"address"},{"name":"_spender","type":"address"}],"name":"allowance","outputs":[{"name":"remaining","type":"uint256"}],"payable":false,"type":"function"},{"anonymous":false,"inputs":[{"indexed":true,"name":"addr","type":"address"},{"indexed":false,"name":"amount","type":"uint256"}],"name":"TransferEther","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"name":"_from","type":"address"},{"indexed":true,"name":"_to","type":"address"},{"indexed":false,"name":"_value","type":"uint256"}],"name":"Transfer","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"name":"_owner","type":"address"},{"indexed":true,"name":"_spender","type":"address"},{"indexed":false,"name":"_value","type":"uint256"}],"name":"Approval","type":"event"}]
var ugTokenAddress = "0xf21902c592851485e6ca8ccc96ffcc08bdbc5d31";


var dasABI =
[{"constant":true,"inputs":[],"name":"creator","outputs":[{"name":"","type":"address"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"","type":"uint64"}],"name":"totalOfAssetMapping","outputs":[{"name":"","type":"uint64"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_player","type":"address"},{"name":"_gameId","type":"uint64"}],"name":"getPlayerToken","outputs":[{"name":"","type":"bytes"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"name","type":"bytes32"}],"name":"initGameId","outputs":[],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_player","type":"address"},{"name":"_gameId","type":"uint64"}],"name":"getChannelByPlayer","outputs":[{"name":"channel","type":"address"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_assetId","type":"uint64"}],"name":"getSellingStatusByAssetId","outputs":[{"name":"","type":"bool"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_gameId","type":"uint64"}],"name":"getAddressByGameId","outputs":[{"name":"","type":"address"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"token","type":"bytes32"}],"name":"getIndexByToken","outputs":[{"name":"","type":"uint64"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_assetId","type":"uint64"}],"name":"getTokenByAssetId","outputs":[{"name":"","type":"bytes32"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_assetId","type":"uint64"},{"name":"status","type":"bool"}],"name":"setSellingStatus","outputs":[],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_player","type":"address"}],"name":"getAssets","outputs":[{"name":"","type":"bytes"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"uint64"},{"name":"_to","type":"uint64"},{"name":"_gameId","type":"uint64"},{"name":"_assetId","type":"uint64"}],"name":"transferAsset","outputs":[{"name":"","type":"bool"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_assetId","type":"uint64"}],"name":"getAliasNameByAssetId","outputs":[{"name":"","type":"bytes32"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"totalOfAsset","outputs":[{"name":"","type":"uint64"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_player","type":"address"}],"name":"getChannelNumberByPlayer","outputs":[{"name":"","type":"uint64"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"gameProvider","type":"address"}],"name":"getGameId","outputs":[{"name":"","type":"uint64"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"nonce","outputs":[{"name":"","type":"uint64"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_gameId","type":"uint64"}],"name":"getGameNameByGameId","outputs":[{"name":"","type":"bytes32"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_channel","type":"address"},{"name":"_gameId","type":"uint64"}],"name":"channel","outputs":[{"name":"","type":"bool"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_gameId","type":"uint64"},{"name":"alias","type":"bytes32"}],"name":"initGameToken","outputs":[{"name":"","type":"bool"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"addressIndexes","outputs":[{"name":"","type":"uint64"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_player","type":"address"},{"name":"_gameId","type":"uint64"},{"name":"_assetId","type":"uint64"}],"name":"isPlayerContainAsset","outputs":[{"name":"","type":"bool"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_assetId","type":"uint64"}],"name":"getPlyerByAssetId","outputs":[{"name":"","type":"address"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_channel","type":"address"}],"name":"getPlayerNumberByChannel","outputs":[{"name":"","type":"uint64"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_addr","type":"address"}],"name":"getAddressIndexOrCreate","outputs":[{"name":"","type":"uint64"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"","type":"uint256"}],"name":"playerOfAddress","outputs":[{"name":"","type":"address"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_addr","type":"address"}],"name":"getAddressIndex","outputs":[{"name":"","type":"uint64"}],"payable":false,"type":"function"},{"inputs":[],"payable":false,"type":"constructor"},{"payable":false,"type":"fallback"},{"anonymous":false,"inputs":[{"indexed":false,"name":"_seller","type":"address"},{"indexed":false,"name":"_sellerIndex","type":"uint64"},{"indexed":false,"name":"_gameId","type":"uint64"},{"indexed":false,"name":"_asset","type":"bytes32"},{"indexed":false,"name":"_assetIndex","type":"uint64"}],"name":"InitGameToken","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"name":"_player","type":"address"},{"indexed":false,"name":"_channel","type":"address"},{"indexed":false,"name":"_gameId","type":"uint64"}],"name":"Channel","type":"event"}]
var dasAddress = "0xf2e9d2368e2f63ce67ff52a0a5c6f99a28c7612d";

var rechargeABI =
[{"constant":true,"inputs":[],"name":"creator","outputs":[{"name":"","type":"address"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_gameId","type":"uint64"},{"name":"_tradeId","type":"uint64"},{"name":"_seller","type":"address"},{"name":"_amount","type":"uint256"}],"name":"pay","outputs":[{"name":"","type":"bool"}],"payable":false,"type":"function"},{"inputs":[{"name":"_dasAddr","type":"address"},{"name":"_ugToken","type":"address"}],"payable":false,"type":"constructor"},{"anonymous":false,"inputs":[{"indexed":false,"name":"_gameId","type":"uint64"},{"indexed":false,"name":"_tradeId","type":"uint64"},{"indexed":false,"name":"_seller","type":"address"},{"indexed":false,"name":"_payer","type":"address"},{"indexed":false,"name":"_amount","type":"uint256"}],"name":"Pay","type":"event"}]
var rechargeAddress = "0x961ab7a0684c9a96ef34193afbe1672ec5e16a8d";

var tradeABI =
[{"constant":true,"inputs":[],"name":"creator","outputs":[{"name":"","type":"address"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_gameId","type":"uint64"},{"name":"_assetId","type":"uint64"},{"name":"price","type":"uint256"},{"name":"proveHash","type":"bytes32"}],"name":"sell","outputs":[{"name":"","type":"bool"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getAllOnSelling","outputs":[{"name":"","type":"bytes"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"onSellingCount","outputs":[{"name":"","type":"uint64"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_gameId","type":"uint64"},{"name":"_assetId","type":"uint64"}],"name":"buy","outputs":[{"name":"","type":"bool"}],"payable":false,"type":"function"},{"inputs":[{"name":"_dasAddr","type":"address"},{"name":"_ugToken","type":"address"}],"payable":false,"type":"constructor"},{"anonymous":false,"inputs":[{"indexed":false,"name":"_seller","type":"address"},{"indexed":false,"name":"_sellerIndex","type":"uint64"},{"indexed":false,"name":"_gameId","type":"uint64"},{"indexed":false,"name":"_asset","type":"bytes32"},{"indexed":false,"name":"_assetIndex","type":"uint64"}],"name":"Sell","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"name":"_owner","type":"address"},{"indexed":false,"name":"_ownerIndex","type":"uint64"},{"indexed":false,"name":"__buyer","type":"address"},{"indexed":false,"name":"_buyerIndex","type":"uint64"},{"indexed":false,"name":"_gameId","type":"uint64"},{"indexed":false,"name":"_asset","type":"bytes32"},{"indexed":false,"name":"_assetIndex","type":"uint64"}],"name":"Buy","type":"event"}]
var tradeAddress = "0x5f646fe7d1937fe0e4f9924826cd64077f45bda4"

var web3 = new Web3();
var global_keystore;
var GAME_ID = 1;
var randomSeed;
function setWeb3Provider(keystore) {
  var web3Provider = new HookedWeb3Provider({
    host: "http://192.168.5.5:8545",
    transaction_signer: keystore
  });

  web3.setProvider(web3Provider);
}

function newAddresses(password,callback){
  if (password == '') {
    password = prompt('Enter password to retrieve addresses', 'Password');
  }
  var numAddr = 1
  var addresses;
  lightwallet.keystore.deriveKeyFromPassword(password, function(err, pwDerivedKey) {
      if(err == null){
          try{
              global_keystore.generateNewAddress(pwDerivedKey, numAddr);
              addresses = global_keystore.getAddresses()
              callback(null,addresses[0])
          }catch(e){
              callback(e.message,null)
          }
    }else{
        callback(err,null)
    }
  })
}

function getBalances() {
  var addresses = global_keystore.getAddresses();
  document.getElementById('addr').innerHTML = 'Retrieving addresses...'

  async.map(addresses, web3.eth.getBalance, function(err, balances) {
    async.map(addresses, web3.eth.getTransactionCount, function(err, nonces) {
      document.getElementById('addr').innerHTML = ''
      for (var i=0; i<addresses.length; ++i) {
        document.getElementById('addr').innerHTML += '<div>' + addresses[i] + ' (Bal: ' + (balances[i] / 1.0e18) + ' ETH, Nonce: ' + nonces[i] + ')' + '</div>'
      }
    })
  })
}

function getPlayerToken(player,gameId,channel,callback){
    var das = web3.eth.contract(dasABI).at(dasAddress);
    player = "0x" + player;
    token = das.getPlayerToken(player,gameId);
    callback(null,deserializeToken(token));
}

function initGameToken(player,gameId,channel,aliasName,callback,importCreat){
    var das = web3.eth.contract(dasABI).at(dasAddress);
    player = "0x" + player;
    token = das.getPlayerToken(player,gameId);
    if(token == '0x' || importCreat){
        console.log("step 1");

        balance = getEtherBalance(player)
        if(web3.fromWei(balance,"ether") < 1){
            callback("No enough ether for Transaction",null)
        }else{
            das.InitGameToken().watch(function(error,result){
                console.log("player: ",result.args._seller,",gameId: " , result.args._gameId,",token: " , result.args._asset);
                token = result.args._asset;
                if(player == result.args._seller && gameId == result.args._gameId){
                    callback(null,token);
                    if(channel.length == 42){
                        das.Channel().watch(function(error,result){
                            console.log("SetChannel --> player: " + result.args._player + ", gameId: " + result.args._gameId + ", channel: " + result.args._channel)
                            das.Channel().stopWatching();
                        });
                        das.channel(channel,gameId,{from:player,gas:'4700000',gasPrice:20000000000},function(error,tx){
                            console.log("das.chanel-->error:" + error +", txHash:" + tx)
                        })
                    }
                }
            })
            das.initGameToken(gameId,web3.fromUtf8(aliasName),{from:player,gas:'4700000',gasPrice:50000000000},function(error,tx){
                console.log("das.initGameToken-->error:" + error + ", txHash:" + tx)
            })
        }
    }else{
      console.log("step 2");
        callback(null,deserializeToken(token));
    }
}

function deserializeToken(tokens){
    var number = parseInt(tokens.length / 208);
    tokens = tokens.substr(2)
    var ret = []
    for(var i = 0 ; i < number ; i++){
        var t = tokens.substr(i * 208, 208)
        var token = "0x" + t.substr(0,64)
        var aliasName = "0x" + t.substr(64,64)
        var gameId = web3.toDecimal("0x" + t.substr(128,64))
        var isOnSell = web3.toDecimal("0x" + t.substr(192,16))
        var obj = {
            "token" : token,
            "alias" : aliasName,
            "gameId" : gameId,
            "isOnSell" : isOnSell,
        }
        ret.push(obj)
    }
    return ret
}

function pay(gameId,tradeId,seller,amount,player,callback){
    var recharge = web3.eth.contract(rechargeABI).at(rechargeAddress);
    player = "0x" + player;
    recharge.Pay().watch(function(error,result){
        if(error != null){
            callback(error,null)
        }else {
            console.log("Pay --> gameId: " + result.args._gameId + ", tradeId: " + result.args._tradeId
                + ", seller: " + result.args._seller + ", payer: " + result.args._payer + ", amount: " + result.args._amount )
            if(gameId == result.args._gameId && tradeId == result.args._tradeId &&
                    seller == result.args._seller && player == result.args._payer && amount == result.args._amount){
                callback(null,true)
                recharge.Pay().stopWatching();
            }
        }
    })

    recharge.pay(gameId,tradeId,seller,amount,{from:player,gas:'4700000',gasPrice:50000000000},function(error,tx){
        console.log("recharge.pay-->error:" + error + ", txHash:" + tx)
        if(error != null){
            callback("Pay failed," + error,false)
        }
    })
}

function getEtherBalance(player){
    balance = web3.eth.getBalance(player);
    return balance
}

function getUGTokenBalance(player){
    player = "0x" + player;
    var ugToken = web3.eth.contract(ugTokenABI).at(ugTokenAddress)
    return ugToken.balanceOf(player)
}

function getPlayerAddress(){
    return global_keystore.getAddresses()[0];
}

function restoreWallet(seed,callback) {
  var password = prompt('Enter Password to encrypt your seed', 'Password');

  lightwallet.keystore.deriveKeyFromPassword(password, function(err, pwDerivedKey) {
      if(err == null){
          global_keystore = new lightwallet.keystore(seed,pwDerivedKey);
          newAddresses(password,function(error,address){
              callback(error,address);
          });
          setWeb3Provider(global_keystore);
      }else{
          callback(err,null)
      }
  })
}

function newWallet(callback) {
  var extraEntropy = '';
  randomSeed = lightwallet.keystore.generateRandomSeed(extraEntropy);

  var infoString = 'Your new wallet seed is: "' + randomSeed +
    '". Please write it down on paper or in a password manager, you will need it to access your wallet. Do not let anyone see this seed or they can take your Ether. ' +
    'Please enter a password to encrypt your seed while in the browser.'
  var password = prompt(infoString, 'Password');

  lightwallet.keystore.deriveKeyFromPassword(password, function(err, pwDerivedKey) {
      if(err == null){
          global_keystore = new lightwallet.keystore(randomSeed,pwDerivedKey);
          newAddresses(password,function(error,address){
              callback(error,address);
          });
          setWeb3Provider(global_keystore);
      }
  })
}
