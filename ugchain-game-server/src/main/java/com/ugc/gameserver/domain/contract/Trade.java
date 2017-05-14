package com.ugc.gameserver.domain.contract;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version none.
 */
public final class Trade extends Contract {
    private static final String BINARY = "60606040523461000057604051604080611734833981016040528080519060200190919080519060200190919050505b81600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50505b611670806100c46000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806302d05d3f1461006a57806386b94747146100b95780639bc6af8814610121578063cbc61725146101b7578063ced9e8e1146101ee575b610000565b3461000057610077610240565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3461000057610107600480803567ffffffffffffffff1690602001909190803567ffffffffffffffff1690602001909190803590602001909190803560001916906020019091905050610266565b604051808215151515815260200191505060405180910390f35b346100005761012e610a64565b604051808060200182810382528381815181526020019150805190602001908083836000831461017d575b80518252602083111561017d57602082019150602081019050602083039250610159565b505050905090810190601f1680156101a95780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34610000576101c4610bb6565b604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390f35b3461000057610226600480803567ffffffffffffffff1690602001909190803567ffffffffffffffff16906020019091905050610bd0565b604051808215151515815260200191505060405180910390f35b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600060006000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16634fea5ffd896000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808267ffffffffffffffff1667ffffffffffffffff168152602001915050602060405180830381600087803b156100005760325a03f115610000575050506040518051905092503373ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614151561036557610000565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c08b6fb4886000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808267ffffffffffffffff1667ffffffffffffffff168152602001915050602060405180830381600087803b156100005760325a03f11561000057505050604051805190509150600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ff7e2be9336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b156100005760325a03f11561000057505050604051805190509050600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663bc0f3cad838a8a6000604051602001526040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018367ffffffffffffffff1667ffffffffffffffff1681526020018267ffffffffffffffff1667ffffffffffffffff1681526020019350505050602060405180830381600087803b156100005760325a03f115610000575050506040518051905015156106115760009350610a59565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166339b5ad53886000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808267ffffffffffffffff1667ffffffffffffffff168152602001915050602060405180830381600087803b156100005760325a03f1156100005750505060405180519050156106da5760009350610a59565b6040604051908101604052808781526020018660001916815250600160008967ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060008201518160000155602082015181600101906000191690559050506003805480600101828181548183558181151161078f57600301600490048160030160049004836000526020600020918201910161078e91905b8082111561078a576000816000905550600101610772565b5090565b5b50505091600052602060002090600491828204019190066008025b89909190916101000a81548167ffffffffffffffff021916908367ffffffffffffffff16021790555050600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663670f59038860016040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808367ffffffffffffffff1667ffffffffffffffff1681526020018215151515815260200192505050600060405180830381600087803b156100005760325a03f115610000575050506002600081819054906101000a900467ffffffffffffffff168092919060010191906101000a81548167ffffffffffffffff021916908367ffffffffffffffff160217905550507ff5b96081068a96a1fbe300cdded7aa491196e2dc1573e18f828511cd8cfbec6082828a600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166360e625028c6000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808267ffffffffffffffff1667ffffffffffffffff168152602001915050602060405180830381600087803b156100005760325a03f11561000057505050604051805190508b604051808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018567ffffffffffffffff1667ffffffffffffffff1681526020018467ffffffffffffffff1667ffffffffffffffff16815260200183600019166000191681526020018267ffffffffffffffff1667ffffffffffffffff1681526020019550505050505060405180910390a1600193505b505050949350505050565b6020604051908101604052806000815250602060405190810160405280600081525060006000600060006000600060006060600260009054906101000a900467ffffffffffffffff160267ffffffffffffffff16604051805910610ac55750595b908082528060200260200182016040525b50975060009650600095505b600380549050861015610ba75760038681548110156100005790600052602060002090600491828204019190066008025b9054906101000a900467ffffffffffffffff16945060008567ffffffffffffffff161115610b9957600160008667ffffffffffffffff1667ffffffffffffffff1681526020019081526020016000209350836000015492508360010154915060608702602001905086806001019750508281890152816020820189015284604082018901525b5b8580600101965050610ae2565b8798505b505050505050505090565b600260009054906101000a900467ffffffffffffffff1681565b60006000600060006000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c08b6fb4876000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808267ffffffffffffffff1667ffffffffffffffff168152602001915050602060405180830381600087803b156100005760325a03f11561000057505050604051805190509350600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663bc0f3cad8589896000604051602001526040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018367ffffffffffffffff1667ffffffffffffffff1681526020018267ffffffffffffffff1667ffffffffffffffff1681526020019350505050602060405180830381600087803b156100005760325a03f11561000057505050604051805190501515610db1576000945061163a565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166339b5ad53876000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808267ffffffffffffffff1667ffffffffffffffff168152602001915050602060405180830381600087803b156100005760325a03f11561000057505050604051805190501515610e7b576000945061163a565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3386600160008b67ffffffffffffffff1667ffffffffffffffff168152602001908152602001600020600001546000604051602001526040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050602060405180830381600087803b156100005760325a03f11561000057505050604051805190501515610fc3576000945061163a565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663670f59038760006040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808367ffffffffffffffff1667ffffffffffffffff1681526020018215151515815260200192505050600060405180830381600087803b156100005760325a03f11561000057505050600160008767ffffffffffffffff1667ffffffffffffffff1681526020019081526020016000206000600082016000905560018201600090555050600092505b6003805490508310156111665760038381548110156100005790600052602060002090600491828204019190066008025b9054906101000a900467ffffffffffffffff1667ffffffffffffffff168667ffffffffffffffff1614156111585760038381548110156100005790600052602060002090600491828204019190066008025b6101000a81549067ffffffffffffffff0219169055611166565b5b82806001019350506110bb565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e0cb0ca7856000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b156100005760325a03f11561000057505050604051805190509150600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e0cb0ca7336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b156100005760325a03f11561000057505050604051805190509050600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663812cf01c83838a8a6000604051602001526040518563ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808567ffffffffffffffff1667ffffffffffffffff1681526020018467ffffffffffffffff1667ffffffffffffffff1681526020018367ffffffffffffffff1667ffffffffffffffff1681526020018267ffffffffffffffff1667ffffffffffffffff168152602001945050505050602060405180830381600087803b156100005760325a03f1156100005750505060405180519050506002600081819054906101000a900467ffffffffffffffff16809291906001900391906101000a81548167ffffffffffffffff021916908367ffffffffffffffff160217905550507f7cb33caa9965b731601e035bccd30fc0ff072fedc3931fd3d3e7c4050a5db274848333848b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166360e625028d6000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808267ffffffffffffffff1667ffffffffffffffff168152602001915050602060405180830381600087803b156100005760325a03f11561000057505050604051805190508c604051808873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018767ffffffffffffffff1667ffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018567ffffffffffffffff1667ffffffffffffffff1681526020018467ffffffffffffffff1667ffffffffffffffff16815260200183600019166000191681526020018267ffffffffffffffff1667ffffffffffffffff16815260200197505050505050505060405180910390a15b50505050929150505600a165627a7a723058204b3024b44428410a9aa9e698412b110d2cf3a5687f0d2eecfa299ba8119f116e0029";

    private Trade(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Trade(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<SellEventResponse> getSellEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Sell", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint64>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint64>() {}));
        List<EventValues> valueList = extractEventParameters(event,transactionReceipt);
        ArrayList<SellEventResponse> responses = new ArrayList<SellEventResponse>(valueList.size());
        for(EventValues eventValues : valueList) {
            SellEventResponse typedResponse = new SellEventResponse();
            typedResponse._seller = (Address)eventValues.getNonIndexedValues().get(0);
            typedResponse._sellerIndex = (Uint64)eventValues.getNonIndexedValues().get(1);
            typedResponse._gameId = (Uint64)eventValues.getNonIndexedValues().get(2);
            typedResponse._asset = (Bytes32)eventValues.getNonIndexedValues().get(3);
            typedResponse._assetIndex = (Uint64)eventValues.getNonIndexedValues().get(4);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SellEventResponse> sellEventObservable() {
        final Event event = new Event("Sell", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint64>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint64>() {}));
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SellEventResponse>() {
            @Override
            public SellEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SellEventResponse typedResponse = new SellEventResponse();
                typedResponse._seller = (Address)eventValues.getNonIndexedValues().get(0);
                typedResponse._sellerIndex = (Uint64)eventValues.getNonIndexedValues().get(1);
                typedResponse._gameId = (Uint64)eventValues.getNonIndexedValues().get(2);
                typedResponse._asset = (Bytes32)eventValues.getNonIndexedValues().get(3);
                typedResponse._assetIndex = (Uint64)eventValues.getNonIndexedValues().get(4);
                return typedResponse;
            }
        });
    }

    public List<BuyEventResponse> getBuyEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Buy", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint64>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint64>() {}));
        List<EventValues> valueList = extractEventParameters(event,transactionReceipt);
        ArrayList<BuyEventResponse> responses = new ArrayList<BuyEventResponse>(valueList.size());
        for(EventValues eventValues : valueList) {
            BuyEventResponse typedResponse = new BuyEventResponse();
            typedResponse._owner = (Address)eventValues.getNonIndexedValues().get(0);
            typedResponse._ownerIndex = (Uint64)eventValues.getNonIndexedValues().get(1);
            typedResponse.__buyer = (Address)eventValues.getNonIndexedValues().get(2);
            typedResponse._buyerIndex = (Uint64)eventValues.getNonIndexedValues().get(3);
            typedResponse._gameId = (Uint64)eventValues.getNonIndexedValues().get(4);
            typedResponse._asset = (Bytes32)eventValues.getNonIndexedValues().get(5);
            typedResponse._assetIndex = (Uint64)eventValues.getNonIndexedValues().get(6);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BuyEventResponse> buyEventObservable() {
        final Event event = new Event("Buy", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint64>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint64>() {}));
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, BuyEventResponse>() {
            @Override
            public BuyEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                BuyEventResponse typedResponse = new BuyEventResponse();
                typedResponse._owner = (Address)eventValues.getNonIndexedValues().get(0);
                typedResponse._ownerIndex = (Uint64)eventValues.getNonIndexedValues().get(1);
                typedResponse.__buyer = (Address)eventValues.getNonIndexedValues().get(2);
                typedResponse._buyerIndex = (Uint64)eventValues.getNonIndexedValues().get(3);
                typedResponse._gameId = (Uint64)eventValues.getNonIndexedValues().get(4);
                typedResponse._asset = (Bytes32)eventValues.getNonIndexedValues().get(5);
                typedResponse._assetIndex = (Uint64)eventValues.getNonIndexedValues().get(6);
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

    public Future<TransactionReceipt> sell(Uint64 _gameId, Uint64 _assetId, Uint256 price, Bytes32 proveHash) {
        Function function = new Function("sell", Arrays.<Type>asList(_gameId, _assetId, price, proveHash), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<DynamicBytes> getAllOnSelling() {
        Function function = new Function("getAllOnSelling", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint64> onSellingCount() {
        Function function = new Function("onSellingCount", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> buy(Uint64 _gameId, Uint64 _assetId) {
        Function function = new Function("buy", Arrays.<Type>asList(_gameId, _assetId), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<Trade> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue, Address _dasAddr, Address _ugToken) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_dasAddr, _ugToken));
        return deployAsync(Trade.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialValue);
    }

    public static Future<Trade> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue, Address _dasAddr, Address _ugToken) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_dasAddr, _ugToken));
        return deployAsync(Trade.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialValue);
    }

    public static Trade load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Trade(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Trade load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Trade(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class SellEventResponse {
        public Address _seller;

        public Uint64 _sellerIndex;

        public Uint64 _gameId;

        public Bytes32 _asset;

        public Uint64 _assetIndex;
    }

    public static class BuyEventResponse {
        public Address _owner;

        public Uint64 _ownerIndex;

        public Address __buyer;

        public Uint64 _buyerIndex;

        public Uint64 _gameId;

        public Bytes32 _asset;

        public Uint64 _assetIndex;
    }
}
