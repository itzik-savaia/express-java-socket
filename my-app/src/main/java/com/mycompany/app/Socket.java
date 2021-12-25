package com.mycompany.app;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.google.gson.Gson;

// import com.google.gson.Gson;
// import lombok.*;

import io.socket.client.IO;
import io.socket.emitter.Emitter;
import lombok.var;

public class Socket {
    Gson toJSON = new Gson();

    public Socket() {
        super();
        connectToSocket();
    }

    private String user;
    private String password;
    private io.socket.client.Socket socket;

    public void connectToSocket() {
        IO.Options options = IO.Options.builder().setAuth(new HashMap<String, String>() {
            {
                put("user", user);
                put("password", password);
            }
        }).build();

        socket = IO.socket(URI.create("ws://localhost:3007"), options);

        socket.open();
        System.out.println(socket.EVENT_CONNECT);

        socket.on("connection", object -> {
            var obj = toJSON.toJson(object);
            System.out.println("connection " + socket.connected());
            System.out.println("connection  " + obj);
        });

        socket.on("hello", object -> {
            var obj = toJSON.toJson(object);
            System.out.println("hello: " + obj);
            socket.emit("hello", "hello from server");
        });

        socket.on("stockPosition", object -> {
            var obj = toJSON.toJson(object);
            System.out.println("stockPosition  " + obj);
            socket.emit("stockPosition", "stockPosition from server");

        });
        socket.on("closeOrder", object -> {
            System.out.println("closeOrder" + object);
        });

        socket.on("stopLoss", object -> {
            System.out.println("stopLoss" + object);
        });
    }
}
