package com.example.msg;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

public class TestMsgProto {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        //序列化
        MsgProto.MsgBuf.Builder builder = MsgProto.MsgBuf.newBuilder();
        builder.setID(100);
        builder.setUrl("Dean");

        MsgProto.MsgBuf info = builder.build();
        byte[] result = info.toByteArray();
        System.out.println("序列化结果:" + Arrays.toString(result));

        //反序列化过程
        MsgProto.MsgBuf testBuf = MsgProto.MsgBuf.parseFrom(result);
        System.out.println("反序列化结果:\n" + testBuf);
    }
}
