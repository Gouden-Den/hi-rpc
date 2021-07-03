package com.gouden.packet;

public class Packet {

    private long packetId;

    private int magicNum = 0xffffffff;

    private byte version = 1;

    private byte lang = 1;

    private byte langVersion = 1;

    private byte serializerAlgorithm = 1;

    private byte type;

    private Object object;

    public long getPacketId() {
        return packetId;
    }

    public void setPacketId(long packetId) {
        this.packetId = packetId;
    }

    public int getMagicNum() {
        return magicNum;
    }

    public void setMagicNum(int magicNum) {
        this.magicNum = magicNum;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getLang() {
        return lang;
    }

    public void setLang(byte lang) {
        this.lang = lang;
    }

    public byte getLangVersion() {
        return langVersion;
    }

    public void setLangVersion(byte langVersion) {
        this.langVersion = langVersion;
    }

    public byte getSerializerAlgorithm() {
        return serializerAlgorithm;
    }

    public void setSerializerAlgorithm(byte serializerAlgorithm) {
        this.serializerAlgorithm = serializerAlgorithm;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
