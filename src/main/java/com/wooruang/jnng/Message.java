package com.wooruang.jnng;

import java.util.Arrays;

public class Message {

    private byte[] msg;
    private long receivedDataSize;

    public Message(int bufferSize) {
        msg = new byte[bufferSize];
    }

    public Message(String data) {
        msg = new byte[data.getBytes().length];
        setString(data);
    }

    public int getSize() {
        return msg.length;
    }

    public void setBytes(byte[] data) {
        msg = data;
    }

    public byte[] getBytes() {
        return msg;
    }

    public void setString(String data) {
        msg = data.getBytes();
    }

    public String getString() {
        return new String(msg);
    }

    public void setReceivedDataSize(long dataSize) {
        receivedDataSize = dataSize;
    }

    public long getReceivedDataSize() {
        return receivedDataSize;
    }

}
