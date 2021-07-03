package com.gouden.serialization;

public interface Serializer {

    static Serializer getSerializer(byte serializerAlgorithm) {
        if (serializerAlgorithm == SerializerAlgorithm.JSON) {
            return JSONSerializer.INSTANCE;
        }
        throw new RuntimeException("找不到对应序列化协议!");
    }

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     *
     * @param object
     * @return
     */
    byte [] serialize(Object object);

    /**
     * 反序列化
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte [] bytes);

}
