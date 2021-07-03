package com.gouden.registry;

import java.util.Objects;

public class Url {

    private String ip;

    private short port;

    private String interfaceName;

    private String methodName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public short getPort() {
        return port;
    }

    public void setPort(short port) {
        this.port = port;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getUniqueName(){
        return interfaceName + "#" + methodName;
    }

    public String getConnectName(){
        return ip + ":" + port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return getPort() == url.getPort() &&
                Objects.equals(getIp(), url.getIp()) &&
                Objects.equals(getInterfaceName(), url.getInterfaceName()) &&
                Objects.equals(getMethodName(), url.getMethodName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIp(), getPort(), getInterfaceName(), getMethodName());
    }
}
