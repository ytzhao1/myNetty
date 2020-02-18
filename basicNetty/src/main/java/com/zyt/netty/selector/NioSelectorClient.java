package com.zyt.netty.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Description:
 *
 * @author : zhaoyutong
 * Version: 1.0
 * Create Date Time: 2019/7/30 22 08
 * Update Date Time:
 */
public class NioSelectorClient {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("127.0.0.1",8000));
    }
}
