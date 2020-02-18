package com.zyt.netty.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Description:
 *
 * @author : zhaoyutong
 * Version: 1.0
 * Create Date Time: 2019/7/30 22 07
 * Update Date Time:
 */
public class NioSelectorServer {
    public static void main(String[] args) throws IOException {
        //创建一个selector选择器
        Selector selector = Selector.open();
        //打开一个通道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        //绑定到9000端口
        socketChannel.socket().bind(new InetSocketAddress(8000));
        //设定non-blocking的方式
        socketChannel.configureBlocking(false);
        //
        String str = "我是销售";
        //向selector注册Channel及我们有兴趣的事件
        socketChannel.register(selector, SelectionKey.OP_ACCEPT).attach(str);
        for(;;){
            //选择事件
            selector.select();
            //当有客户端准备连接到服务端时，便会发出请求
            for (Iterator <SelectionKey> keyIter = selector.selectedKeys()
                    .iterator(); keyIter.hasNext();) {
                SelectionKey key = keyIter.next();
                keyIter.remove();
                System.out.println(key.readyOps());
                if (key.isAcceptable()) {
                    System.out.println("Accept");
                    // 接受连接到此Channel的连接
                    socketChannel.accept();
                }
                System.out.println(key.attachment());
            }
        }


    }

}
