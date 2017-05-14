package com.ugc.gameserver.service;

import com.ugc.gameserver.config.AppConfig;
import com.ugc.gameserver.domain.Derma;
import com.ugc.gameserver.domain.DermaOrder;
import com.ugc.gameserver.domain.OrderStatusEnum;
import com.ugc.gameserver.mapper.DermaOrderMapper;
import com.ugc.gameserver.mapper.SequenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fanjl on 2017/4/27.
 */
@Service
public class DermaOrderServiceImpl implements DermaOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DermaOrderServiceImpl.class);

    @Autowired
    private SequenceMapper sequenceMapper;
    @Autowired
    private DermaOrderMapper dermaOrderMapper;
    @Autowired
    private Web3jService web3jService;
    @Autowired
    private AppConfig appConfig;

    @Override
    public DermaOrder createOrder(String token, Derma derma) {
        DermaOrder dermaOrder = new DermaOrder();
        dermaOrder.setToken(token);
        dermaOrder.setDerma(derma);
        dermaOrder.setOrderId(nextId());
        dermaOrder.setStatus(OrderStatusEnum.PENDING.getId());
        dermaOrder.setSeller(appConfig.getSellerAddress());
        dermaOrder.setGameId(web3jService.getGameId());
        dermaOrderMapper.insertDermaOrder(dermaOrder);
        return dermaOrder;
    }

    @Override
    public void updateOrder(int orderId, OrderStatusEnum orderStatusEnum) {
        dermaOrderMapper.updateDermaOrder(orderStatusEnum.getId(),orderId);
    }


    @Override
    public DermaOrder getOrderById(int orderId) {
        return  dermaOrderMapper.getOrderById(orderId);
    }


    @Override
    public int nextId() {
        return sequenceMapper.nextId(sequenceMapper.DERMA_ORDER);
    }
}
