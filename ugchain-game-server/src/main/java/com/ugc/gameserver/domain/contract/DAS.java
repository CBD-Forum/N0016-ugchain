package com.ugc.gameserver.domain.contract;

import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version none.
 */
public final class DAS extends Contract {
    private static final String BINARY = "60606040523462000000575b600160068181548183558181151162000053578183600052602060002091820191016200005291905b808211156200004e57600081600090555060010162000034565b5090565b5b505050506001600381815481835581811511620000fa57600302816003028360005260206000209182019101620000f991905b80821115620000f5576000600082016000905560018201600090556002820160006101000a81549067ffffffffffffffff02191690556002820160086101000a81549067ffffffffffffffff02191690556002820160106101000a81549060ff02191690555060030162000086565b5090565b5b505050506001600b8181548183558181151162000176576002028160020283600052602060002091820191016200017591905b808211156200017157600060008201600090556001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055506002016200012d565b5090565b5b5050505033600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b61255880620001cd6000396000f3006060604052361561015d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806302d05d3f1461016b5780631b21b263146101ba57806329f1b3fb1461020957806332107399146102d65780633963b2ef146102f757806339b5ad531461037d5780634fea5ffd146103bc5780635d8c605b1461042357806360e625021461046c578063670f5903146104af578063742978da146104e1578063812cf01c1461059b57806396e736d514610613578063a7ef5ec314610656578063aacb1ebf1461068d578063abfd6da5146106e8578063affed0e014610743578063afff99781461077a578063b197b27c146107bd578063b510c5351461081b578063b7fa270f14610867578063bc0f3cad146108c2578063c08b6fb414610933578063d43902c91461099a578063e0cb0ca7146109f5578063fbd6215914610a50578063ff7e2be914610aad575b34610000576101695b5b565b005b3461000057610178610b08565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34610000576101df600480803567ffffffffffffffff16906020019091905050610b2e565b604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390f35b346100005761024d600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803567ffffffffffffffff16906020019091905050610b55565b604051808060200182810382528381815181526020019150805190602001908083836000831461029c575b80518252602083111561029c57602082019150602081019050602083039250610278565b505050905090810190601f1680156102c85780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34610000576102f5600480803560001916906020019091905050610d40565b005b346100005761033b600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803567ffffffffffffffff16906020019091905050610f49565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34610000576103a2600480803567ffffffffffffffff16906020019091905050611022565b604051808215151515815260200191505060405180910390f35b34610000576103e1600480803567ffffffffffffffff16906020019091905050611065565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34610000576104426004808035600019169060200190919050506110bb565b604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390f35b3461000057610491600480803567ffffffffffffffff169060200190919050506110f5565b60405180826000191660001916815260200191505060405180910390f35b34610000576104df600480803567ffffffffffffffff16906020019091908035151590602001909190505061112b565b005b3461000057610512600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050611173565b6040518080602001828103825283818151815260200191508051906020019080838360008314610561575b8051825260208311156105615760208201915060208101905060208303925061053d565b505050905090810190601f16801561058d5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34610000576105f9600480803567ffffffffffffffff1690602001909190803567ffffffffffffffff1690602001909190803567ffffffffffffffff1690602001909190803567ffffffffffffffff169060200190919050506112b7565b604051808215151515815260200191505060405180910390f35b3461000057610638600480803567ffffffffffffffff16906020019091905050611543565b60405180826000191660001916815260200191505060405180910390f35b3461000057610663611579565b604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390f35b34610000576106be600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050611593565b604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390f35b3461000057610719600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506115d0565b604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390f35b346100005761075061162e565b604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390f35b346100005761079f600480803567ffffffffffffffff16906020019091905050611648565b60405180826000191660001916815260200191505060405180910390f35b3461000057610801600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803567ffffffffffffffff1690602001909190505061167e565b604051808215151515815260200191505060405180910390f35b346100005761084d600480803567ffffffffffffffff169060200190919080356000191690602001909190505061178c565b604051808215151515815260200191505060405180910390f35b3461000057610898600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050611c63565b604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390f35b3461000057610919600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803567ffffffffffffffff1690602001909190803567ffffffffffffffff16906020019091905050611c8a565b604051808215151515815260200191505060405180910390f35b3461000057610958600480803567ffffffffffffffff16906020019091905050611d6a565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34610000576109cb600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050611dfc565b604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390f35b3461000057610a26600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050611e39565b604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390f35b3461000057610a6b6004808035906020019091905050611fc6565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3461000057610ade600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050612003565b604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390f35b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60026020528060005260406000206000915054906101000a900467ffffffffffffffff1681565b6020604051908101604052806000815250600060006000602060405190810160405280600081525060006000610b8a89612003565b9550600560008767ffffffffffffffff1667ffffffffffffffff1681526020019081526020016000209450600093506064604051805910610bc85750595b908082528060200260200182016040525b50925060009150600093505b8480549050841015610d2757848481548110156100005790600052602060002090600491828204019190066008025b9054906101000a900467ffffffffffffffff16905060008167ffffffffffffffff16118015610c9557508767ffffffffffffffff1660038267ffffffffffffffff16815481101561000057906000526020600020906003020160005b5060020160089054906101000a900467ffffffffffffffff1667ffffffffffffffff16145b8015610cdd57506000151560038267ffffffffffffffff16815481101561000057906000526020600020906003020160005b5060020160109054906101000a900460ff161515145b15610d19578083838060010194508151811015610000579060200190602002019067ffffffffffffffff16908167ffffffffffffffff16815250505b5b8380600101945050610be5565b610d318383612061565b96505b50505050505092915050565b60006000600a60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900467ffffffffffffffff1667ffffffffffffffff161115610dad57610000565b600b8054809190600101815481835581811511610e2657600202816002028360005260206000209182019101610e2591905b80821115610e2157600060008201600090556001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905550600201610ddf565b5090565b5b5050509050604060405190810160405280836000191681526020013373ffffffffffffffffffffffffffffffffffffffff16815250600b8267ffffffffffffffff16815481101561000057906000526020600020906002020160005b506000820151816000019060001916905560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555090505080600a60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548167ffffffffffffffff021916908367ffffffffffffffff1602179055505b5b5050565b600060006000610f5885612003565b915060008267ffffffffffffffff16111561101957600860008367ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060010160008567ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060009054906101000a900467ffffffffffffffff16905060068167ffffffffffffffff16815481101561000057906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1692505b5b505092915050565b600060038267ffffffffffffffff16815481101561000057906000526020600020906003020160005b5060020160109054906101000a900460ff1690505b919050565b6000600b8267ffffffffffffffff16815481101561000057906000526020600020906002020160005b5060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b919050565b600060046000836000191660001916815260200190815260200160002060009054906101000a900467ffffffffffffffff1690505b919050565b600060038267ffffffffffffffff16815481101561000057906000526020600020906003020160005b506001015490505b919050565b8060038367ffffffffffffffff16815481101561000057906000526020600020906003020160005b5060020160106101000a81548160ff0219169083151502179055505b5050565b60206040519081016040528060008152506000600060006020604051908101604052806000815250600060006111a888612003565b9550600560008767ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002094506000935060646040518059106111e65750595b908082528060200260200182016040525b50925060009150600093505b848054905084101561129f57848481548110156100005790600052602060002090600491828204019190066008025b9054906101000a900467ffffffffffffffff16905060008167ffffffffffffffff161115611291578083838060010194508151811015610000579060200190602002019067ffffffffffffffff16908167ffffffffffffffff16815250505b5b8380600101945050611203565b6112a98383612061565b96505b505050505050919050565b600060006000600060009250600560008967ffffffffffffffff1667ffffffffffffffff1681526020019081526020016000209150600560008867ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002090508767ffffffffffffffff1660038667ffffffffffffffff16815481101561000057906000526020600020906003020160005b5060020160009054906101000a900467ffffffffffffffff1667ffffffffffffffff1614151561137a5760009350611538565b600092505b81805490508367ffffffffffffffff161015611445578467ffffffffffffffff16828467ffffffffffffffff1681548110156100005790600052602060002090600491828204019190066008025b9054906101000a900467ffffffffffffffff1667ffffffffffffffff16141561143757818367ffffffffffffffff1681548110156100005790600052602060002090600491828204019190066008025b6101000a81549067ffffffffffffffff0219169055611445565b5b828060010193505061137f565b848182805480919060010181548183558181151161149d57600301600490048160030160049004836000526020600020918201910161149c91905b80821115611498576000816000905550600101611480565b5090565b5b50505081548110156100005790600052602060002090600491828204019190066008025b6101000a81548167ffffffffffffffff021916908367ffffffffffffffff1602179055508660038667ffffffffffffffff16815481101561000057906000526020600020906003020160005b5060020160006101000a81548167ffffffffffffffff021916908367ffffffffffffffff1602179055505b505050949350505050565b600060038267ffffffffffffffff16815481101561000057906000526020600020906003020160005b506000015490505b919050565b600160009054906101000a900467ffffffffffffffff1681565b600060006115a083612003565b905060018167ffffffffffffffff1610156115be57600091506115ca565b6115c781612197565b91505b50919050565b6000600a60008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900467ffffffffffffffff1690505b919050565b600060149054906101000a900467ffffffffffffffff1681565b6000600b8267ffffffffffffffff16815481101561000057906000526020600020906002020160005b506000015490505b919050565b6000600060003384600061169183611e39565b905061169d81836121cf565b156116a757610000565b6116b088611e39565b94506116bb33611e39565b93506116c884888761224b565b7f21ca1003e8a86c14fc9c803dc1d967d65985325497800630575ed9ef6df1a298338989604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018267ffffffffffffffff1667ffffffffffffffff168152602001935050505060405180910390a1600195505b5b505050505092915050565b6000600060006000600060006000601481819054906101000a900467ffffffffffffffff168092919060010191906101000a81548167ffffffffffffffff021916908367ffffffffffffffff16021790555033604051808367ffffffffffffffff1667ffffffffffffffff1678010000000000000000000000000000000000000000000000000281526008018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166c0100000000000000000000000002815260140192505050604051809103902094506003805480919060010181548183558181151161190b5760030281600302836000526020600020918201910161190a91905b80821115611906576000600082016000905560018201600090556002820160006101000a81549067ffffffffffffffff02191690556002820160086101000a81549067ffffffffffffffff02191690556002820160106101000a81549060ff021916905550600301611899565b5090565b5b505050935060038467ffffffffffffffff16815481101561000057906000526020600020906003020160005b509250868360000181600019169055508483600101816000191690555060008360020160106101000a81548160ff02191690831515021790555061197a33611e39565b9150818360020160006101000a81548167ffffffffffffffff021916908367ffffffffffffffff160217905550878360020160086101000a81548167ffffffffffffffff021916908367ffffffffffffffff160217905550600560008367ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002090508381828054809190600101815481835581811511611a53576003016004900481600301600490048360005260206000209182019101611a5291905b80821115611a4e576000816000905550600101611a36565b5090565b5b50505081548110156100005790600052602060002090600491828204019190066008025b6101000a81548167ffffffffffffffff021916908367ffffffffffffffff1602179055506001600081819054906101000a900467ffffffffffffffff168092919060010191906101000a81548167ffffffffffffffff021916908367ffffffffffffffff160217905550506001600260008a67ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060008282829054906101000a900467ffffffffffffffff160192506101000a81548167ffffffffffffffff021916908367ffffffffffffffff1602179055508360046000876000191660001916815260200190815260200160002060006101000a81548167ffffffffffffffff021916908367ffffffffffffffff1602179055507fdfef7e335ca3bf9d4892551016b59cbfd69ac51e0cc139928c4a0a3d75a5ac1833838a8888604051808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018567ffffffffffffffff1667ffffffffffffffff1681526020018467ffffffffffffffff1667ffffffffffffffff16815260200183600019166000191681526020018267ffffffffffffffff1667ffffffffffffffff1681526020019550505050505060405180910390a1600195505b505050505092915050565b60076020528060005260406000206000915054906101000a900467ffffffffffffffff1681565b6000600060006000611c9b87612003565b925060018367ffffffffffffffff161015611cb95760009350611d60565b600560008467ffffffffffffffff1667ffffffffffffffff1681526020019081526020016000209150600090505b8180549050811015611d5b578467ffffffffffffffff16828281548110156100005790600052602060002090600491828204019190066008025b9054906101000a900467ffffffffffffffff1667ffffffffffffffff161415611d4d5760019350611d60565b5b8080600101915050611ce7565b600093505b5050509392505050565b6000600060038367ffffffffffffffff16815481101561000057906000526020600020906003020160005b5060020160009054906101000a900467ffffffffffffffff16905060068167ffffffffffffffff16815481101561000057906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691505b50919050565b60006000611e0983612003565b905060018167ffffffffffffffff161015611e275760009150611e33565b611e30816124e3565b91505b50919050565b60006000600760008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900467ffffffffffffffff16905060018167ffffffffffffffff161015611fbc5760068054809190600101815481835581811511611eef57818360005260206000209182019101611eee91905b80821115611eea576000816000905550600101611ed2565b5090565b5b50505090508260068267ffffffffffffffff16815481101561000057906000526020600020900160005b6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600760008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548167ffffffffffffffff021916908367ffffffffffffffff1602179055505b8091505b50919050565b600681815481101561000057906000526020600020900160005b915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600760008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900467ffffffffffffffff1690505b919050565b60206040519081016040528060008152506020604051908101604052806000815250600060006000600060006000600060688a026040518059106120a25750595b908082528060200260200182016040525b509750600096505b898710156121855760038b888151811015610000579060200190602002015167ffffffffffffffff16815481101561000057906000526020600020906003020160005b50955085600101549450856000015493508560020160089054906101000a900467ffffffffffffffff16925060009150600115158660020160109054906101000a900460ff161515141561215157600191505b60688702602001905084818901528360208201890152826040820189015281606082018901535b86806001019750506120bb565b8798505b505050505050505092915050565b6000600860008367ffffffffffffffff1667ffffffffffffffff1681526020019081526020016000206000018054905090505b919050565b60006000600860008567ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060010160008467ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060009054906101000a900467ffffffffffffffff1667ffffffffffffffff161190505b92915050565b80600860008567ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060010160008467ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060006101000a81548167ffffffffffffffff021916908367ffffffffffffffff160217905550600860008467ffffffffffffffff1667ffffffffffffffff168152602001908152602001600020600001805480600101828181548183558181151161234057600301600490048160030160049004836000526020600020918201910161233f91905b8082111561233b576000816000905550600101612323565b5090565b5b50505091600052602060002090600491828204019190066008025b84909190916101000a81548167ffffffffffffffff021916908367ffffffffffffffff16021790555050600960008267ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060010160008367ffffffffffffffff1667ffffffffffffffff168152602001908152602001600020805480600101828181548183558181151161242957600301600490048160030160049004836000526020600020918201910161242891905b8082111561242457600081600090555060010161240c565b5090565b5b50505091600052602060002090600491828204019190066008025b85909190916101000a81548167ffffffffffffffff021916908367ffffffffffffffff16021790555050600960008267ffffffffffffffff1667ffffffffffffffff168152602001908152602001600020600001600081819054906101000a900467ffffffffffffffff168092919060010191906101000a81548167ffffffffffffffff021916908367ffffffffffffffff160217905550505b505050565b6000600960008367ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160009054906101000a900467ffffffffffffffff1690505b9190505600a165627a7a72305820fa1b24961843101fdca8b94f336ed4295df470507e0dbc4c3977cb1670685fdd0029";

    private DAS(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private DAS(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<InitGameTokenEventResponse> getInitGameTokenEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("InitGameToken", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint64>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint64>() {}));
        List<EventValues> valueList = extractEventParameters(event,transactionReceipt);
        ArrayList<InitGameTokenEventResponse> responses = new ArrayList<InitGameTokenEventResponse>(valueList.size());
        for(EventValues eventValues : valueList) {
            InitGameTokenEventResponse typedResponse = new InitGameTokenEventResponse();
            typedResponse._seller = (Address)eventValues.getNonIndexedValues().get(0);
            typedResponse._sellerIndex = (Uint64)eventValues.getNonIndexedValues().get(1);
            typedResponse._gameId = (Uint64)eventValues.getNonIndexedValues().get(2);
            typedResponse._asset = (Bytes32)eventValues.getNonIndexedValues().get(3);
            typedResponse._assetIndex = (Uint64)eventValues.getNonIndexedValues().get(4);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<InitGameTokenEventResponse> initGameTokenEventObservable() {
        final Event event = new Event("InitGameToken", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint64>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint64>() {}));
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, InitGameTokenEventResponse>() {
            @Override
            public InitGameTokenEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                InitGameTokenEventResponse typedResponse = new InitGameTokenEventResponse();
                typedResponse._seller = (Address)eventValues.getNonIndexedValues().get(0);
                typedResponse._sellerIndex = (Uint64)eventValues.getNonIndexedValues().get(1);
                typedResponse._gameId = (Uint64)eventValues.getNonIndexedValues().get(2);
                typedResponse._asset = (Bytes32)eventValues.getNonIndexedValues().get(3);
                typedResponse._assetIndex = (Uint64)eventValues.getNonIndexedValues().get(4);
                return typedResponse;
            }
        });
    }

    public List<ChannelEventResponse> getChannelEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Channel", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint64>() {}));
        List<EventValues> valueList = extractEventParameters(event,transactionReceipt);
        ArrayList<ChannelEventResponse> responses = new ArrayList<ChannelEventResponse>(valueList.size());
        for(EventValues eventValues : valueList) {
            ChannelEventResponse typedResponse = new ChannelEventResponse();
            typedResponse._player = (Address)eventValues.getNonIndexedValues().get(0);
            typedResponse._channel = (Address)eventValues.getNonIndexedValues().get(1);
            typedResponse._gameId = (Uint64)eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ChannelEventResponse> channelEventObservable() {
        final Event event = new Event("Channel", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint64>() {}));
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ChannelEventResponse>() {
            @Override
            public ChannelEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ChannelEventResponse typedResponse = new ChannelEventResponse();
                typedResponse._player = (Address)eventValues.getNonIndexedValues().get(0);
                typedResponse._channel = (Address)eventValues.getNonIndexedValues().get(1);
                typedResponse._gameId = (Uint64)eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Future<Address> creator() {
        Function function = new Function("creator", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint64> totalOfAssetMapping(Uint64 param0) {
        Function function = new Function("totalOfAssetMapping", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<DynamicBytes> getPlayerToken(Address _player, Uint64 _gameId) {
        Function function = new Function("getPlayerToken", 
                Arrays.<Type>asList(_player, _gameId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> initGameId(Bytes32 name) {
        Function function = new Function("initGameId", Arrays.<Type>asList(name), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> getChannelByPlayer(Address _player, Uint64 _gameId) {
        Function function = new Function("getChannelByPlayer", 
                Arrays.<Type>asList(_player, _gameId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> getSellingStatusByAssetId(Uint64 _assetId) {
        Function function = new Function("getSellingStatusByAssetId", 
                Arrays.<Type>asList(_assetId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> getAddressByGameId(Uint64 _gameId) {
        Function function = new Function("getAddressByGameId", 
                Arrays.<Type>asList(_gameId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint64> getIndexByToken(Bytes32 token) {
        Function function = new Function("getIndexByToken", 
                Arrays.<Type>asList(token), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bytes32> getTokenByAssetId(Uint64 _assetId) {
        Function function = new Function("getTokenByAssetId", 
                Arrays.<Type>asList(_assetId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setSellingStatus(Uint64 _assetId, Bool status) {
        Function function = new Function("setSellingStatus", Arrays.<Type>asList(_assetId, status), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<DynamicBytes> getAssets(Address _player) {
        Function function = new Function("getAssets", 
                Arrays.<Type>asList(_player), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> transferAsset(Uint64 _from, Uint64 _to, Uint64 _gameId, Uint64 _assetId) {
        Function function = new Function("transferAsset", Arrays.<Type>asList(_from, _to, _gameId, _assetId), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bytes32> getAliasNameByAssetId(Uint64 _assetId) {
        Function function = new Function("getAliasNameByAssetId", 
                Arrays.<Type>asList(_assetId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint64> totalOfAsset() {
        Function function = new Function("totalOfAsset", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint64> getChannelNumberByPlayer(Address _player) {
        Function function = new Function("getChannelNumberByPlayer", 
                Arrays.<Type>asList(_player), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint64> getGameId(Address gameProvider) {
        Function function = new Function("getGameId", 
                Arrays.<Type>asList(gameProvider), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint64> nonce() {
        Function function = new Function("nonce", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bytes32> getGameNameByGameId(Uint64 _gameId) {
        Function function = new Function("getGameNameByGameId", 
                Arrays.<Type>asList(_gameId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> channel(Address _channel, Uint64 _gameId) {
        Function function = new Function("channel", Arrays.<Type>asList(_channel, _gameId), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> initGameToken(Uint64 _gameId, Bytes32 alias) {
        Function function = new Function("initGameToken", Arrays.<Type>asList(_gameId, alias), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint64> addressIndexes(Address param0) {
        Function function = new Function("addressIndexes", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> isPlayerContainAsset(Address _player, Uint64 _gameId, Uint64 _assetId) {
        Function function = new Function("isPlayerContainAsset", 
                Arrays.<Type>asList(_player, _gameId, _assetId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> getPlyerByAssetId(Uint64 _assetId) {
        Function function = new Function("getPlyerByAssetId", 
                Arrays.<Type>asList(_assetId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint64> getPlayerNumberByChannel(Address _channel) {
        Function function = new Function("getPlayerNumberByChannel", 
                Arrays.<Type>asList(_channel), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> getAddressIndexOrCreate(Address _addr) {
        Function function = new Function("getAddressIndexOrCreate", Arrays.<Type>asList(_addr), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> playerOfAddress(Uint256 param0) {
        Function function = new Function("playerOfAddress", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint64> getAddressIndex(Address _addr) {
        Function function = new Function("getAddressIndex", 
                Arrays.<Type>asList(_addr), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<DAS> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(DAS.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Future<DAS> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(DAS.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static DAS load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DAS(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static DAS load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DAS(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class InitGameTokenEventResponse {
        public Address _seller;

        public Uint64 _sellerIndex;

        public Uint64 _gameId;

        public Bytes32 _asset;

        public Uint64 _assetIndex;
    }

    public static class ChannelEventResponse {
        public Address _player;

        public Address _channel;

        public Uint64 _gameId;
    }
}
