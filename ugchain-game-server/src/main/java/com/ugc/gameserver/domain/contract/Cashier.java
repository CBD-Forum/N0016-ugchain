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
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
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
public final class Cashier extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b5b60ee8061001b6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063e522538114603a575bfe5b6040605a565b604051808215151515815260200191505060405180910390f35b6000600034111560ba573373ffffffffffffffffffffffffffffffffffffffff167f4256a058fa2b123d727576d3d31e3a272db98ee5fe264e229610ce43dc849999346040518082815260200191505060405180910390a26001905060bf565b600090505b905600a165627a7a723058200ff46001755f69f4bd9cbfa2d2d7dd99829ba163ccb1a069df0ffd6d325bc1790029";

    private Cashier(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Cashier(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<CollectEventResponse> getCollectEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Collect", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event,transactionReceipt);
        ArrayList<CollectEventResponse> responses = new ArrayList<CollectEventResponse>(valueList.size());
        for(EventValues eventValues : valueList) {
            CollectEventResponse typedResponse = new CollectEventResponse();
            typedResponse.addr = (Address)eventValues.getIndexedValues().get(0);
            typedResponse.amount = (Uint256)eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CollectEventResponse> collectEventObservable() {
        final Event event = new Event("Collect", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, CollectEventResponse>() {
            @Override
            public CollectEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                CollectEventResponse typedResponse = new CollectEventResponse();
                typedResponse.addr = (Address)eventValues.getIndexedValues().get(0);
                typedResponse.amount = (Uint256)eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> collect() {
        Function function = new Function("collect", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<Cashier> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(Cashier.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Future<Cashier> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(Cashier.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Cashier load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Cashier(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Cashier load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Cashier(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class CollectEventResponse {
        public Address addr;

        public Uint256 amount;
    }
}
