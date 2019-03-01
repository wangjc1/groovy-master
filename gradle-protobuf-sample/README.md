### Protocbuf 例子 ###
 
####1. 首先定义proto文件 msg.proto
    syntax = "proto3";
    package test;
    option java_package = "com.example.msg";
    option java_outer_classname = "MsgProto";
    
    message MsgBuf {
       int32 ID = 1;
       string Url = 2;
    }

####2. 编译msg.proto，在gradle Tasks other中选择clear和generateProto两个任务并执行,会在build下的generated下生成编译后java文件
    package com.example.msg;
    public final class MsgProto {
      private MsgProto() {}
      public static void registerAllExtensions(
          com.google.protobuf.ExtensionRegistryLite registry) {
      }
      .....
 
####3. 将编译后的java文件拷贝到相应的目录中(这个可以写个Task来搞定)，编写测试用例
    public static void main(String[] args) throws InvalidProtocolBufferException {
        //序列化
        MsgProto.MsgBuf.Builder builder = MsgProto.MsgBuf.newBuilder();
        builder.setID(100);
        builder.setUrl("Dean");

        MsgProto.MsgBuf info = builder.build();
        byte[] result = info.toByteArray();
        System.out.println("序列化结果:" + result.toString());

        //反序列化过程
        MsgProto.MsgBuf testBuf = MsgProto.MsgBuf.parseFrom(result);
        System.out.println("反序列化结果:" + testBuf);
    }
      
 