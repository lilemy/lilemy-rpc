package com.lilemy.lilemyrpc.server;

import io.vertx.core.Vertx;

/**
 * 基于 Vert.x 实现 web 服务器
 */
public class VertxHttpServer implements HttpServer {

    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();
        // 创建 HTTP 服务器
        io.vertx.core.http.HttpServer httpServer = vertx.createHttpServer();
        // 监听端口并处理请求
        httpServer.requestHandler(new HttpServerHandler());
        // 启动 HTTP 服务器并监听指定端口
        httpServer.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("服务器正在监听：" + port + "端口");
            } else {
                System.out.println("服务器启动失败：" + result.cause());
            }
        });
    }
}
