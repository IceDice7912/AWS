package com.mulcam.ai.web.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.ai.web.dao.OrderDAO;
import com.mulcam.ai.web.dao.OrderDAOImpl;
import com.mulcam.ai.web.vo.OrderVO;

@Service
public class OrderService {
    @Autowired
    OrderDAOImpl orderDAO;
    
    public long ordersInsert(ArrayList<OrderVO> list) throws Exception{
        return orderDAO.ordersInsert(list);
    }
}








