package com.ugc.gameserver.service;

import com.ugc.gameserver.domain.Derma;
import com.ugc.gameserver.domain.DermaOrder;
import com.ugc.gameserver.domain.OrderStatusEnum;

/**
 * Created by fanjl on 2017/4/27.
 */
public interface DermaOrderService {
    DermaOrder createOrder(String token, Derma derma);

    void updateOrder(int orderId, OrderStatusEnum orderStatusEnum);

    DermaOrder getOrderById(int orderId);

    int nextId();
}
