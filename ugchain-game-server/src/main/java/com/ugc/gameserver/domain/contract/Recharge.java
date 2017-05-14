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
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public final class Recharge extends Contract {
    private static final String BINARY = "6060604052600a60035534610000576040516040806106b4833981016040528080519060200190919080519060200190919050505b81600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555033600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50505b6105aa8061010a6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806302d05d3f1461004957806328eea32214610098575b610000565b3461000057610056610112565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34610000576100f8600480803567ffffffffffffffff1690602001909190803567ffffffffffffffff1690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091908035906020019091905050610138565b604051808215151515815260200191505060405180910390f35b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600060006000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16633963b2ef33896000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018267ffffffffffffffff1667ffffffffffffffff16815260200192505050602060405180830381600087803b156100005760325a03f1156100005750505060405180519050915060008273ffffffffffffffffffffffffffffffffffffffff1614151561037e576064600a85028115610000570490508084039350600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3384846000604051602001526040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050602060405180830381600087803b156100005760325a03f1156100005750505060405180519050151561037d57610000565b5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3387876000604051602001526040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050602060405180830381600087803b156100005760325a03f1156100005750505060405180519050151561049857610000565b7fa3249a3b1ef5bbb7759c113e386d5d6829ab59f197dd5a234f23707a0de9002b8787873388604051808667ffffffffffffffff1667ffffffffffffffff1681526020018567ffffffffffffffff1667ffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019550505050505060405180910390a1600192505b50509493505050505600a165627a7a723058203b7755b7606d115e38427a564d6d003da4b4e125198a01a5e7996bb2f37000430029";

    private Recharge(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Recharge(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<PayEventResponse> getPayEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Pay", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event,transactionReceipt);
        ArrayList<PayEventResponse> responses = new ArrayList<PayEventResponse>(valueList.size());
        for(EventValues eventValues : valueList) {
            PayEventResponse typedResponse = new PayEventResponse();
            typedResponse._gameId = (Uint64)eventValues.getNonIndexedValues().get(0);
            typedResponse._tradeId = (Uint64)eventValues.getNonIndexedValues().get(1);
            typedResponse._seller = (Address)eventValues.getNonIndexedValues().get(2);
            typedResponse._payer = (Address)eventValues.getNonIndexedValues().get(3);
            typedResponse._amount = (Uint256)eventValues.getNonIndexedValues().get(4);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<PayEventResponse> payEventObservable() {
        final Event event = new Event("Pay", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, PayEventResponse>() {
            @Override
            public PayEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                PayEventResponse typedResponse = new PayEventResponse();
                typedResponse._gameId = (Uint64)eventValues.getNonIndexedValues().get(0);
                typedResponse._tradeId = (Uint64)eventValues.getNonIndexedValues().get(1);
                typedResponse._seller = (Address)eventValues.getNonIndexedValues().get(2);
                typedResponse._payer = (Address)eventValues.getNonIndexedValues().get(3);
                typedResponse._amount = (Uint256)eventValues.getNonIndexedValues().get(4);
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

    public Future<TransactionReceipt> pay(Uint64 _gameId, Uint64 _tradeId, Address _seller, Uint256 _amount) {
        Function function = new Function("pay", Arrays.<Type>asList(_gameId, _tradeId, _seller, _amount), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<Recharge> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue, Address _dasAddr, Address _ugToken) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_dasAddr, _ugToken));
        return deployAsync(Recharge.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialValue);
    }

    public static Future<Recharge> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue, Address _dasAddr, Address _ugToken) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_dasAddr, _ugToken));
        return deployAsync(Recharge.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialValue);
    }

    public static Recharge load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Recharge(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Recharge load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Recharge(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class PayEventResponse {
        public Uint64 _gameId;

        public Uint64 _tradeId;

        public Address _seller;

        public Address _payer;

        public Uint256 _amount;
    }
}
