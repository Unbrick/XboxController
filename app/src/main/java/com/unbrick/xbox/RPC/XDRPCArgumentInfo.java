package com.unbrick.xbox.RPC;

/*
 * Currently only supports int and string values
 * */
public class XDRPCArgumentInfo<T> {
    T parameter;

    public XDRPCArgumentInfo(T parameter) {
        this.parameter = parameter;
        if (!isBool() && !isString() && !isInt() && !isFloat() && !isDouble() && !isLong() && !isShort() && !isByte()) {
            throw new RuntimeException("Unsupported type, please use one of the following types: String, Float, Boolean, Double, Integer, Short, Long, Byte");
        }
    }

    /*
    * Returns the size of the argument in bytes
    * An integer is per default 32 bits / 8 bytes long
    * For strings, the length needs to be taken exactly
    * */
    public int size() {
        if (isInt() || isDouble() || isFloat() || isBool() || isLong() || isShort() || isByte()) {
            return 8;
        } else if (parameter instanceof String) {
            // as 1 character in the ascii set equals 1 byte, we can just return the length of the string
            return ((String) parameter).length();
        }
        return 0;
    }

    public T get() {
        return parameter;
    }


    public boolean isLong() {
        return parameter instanceof Long;
    }

    public boolean isShort() {
        return parameter instanceof Short;
    }

    public boolean isByte () {
        return parameter instanceof Byte;
    }

    public boolean isString() {
        return parameter instanceof String;
    }

    public boolean isInt() {
        return parameter instanceof Integer;
    }

    public boolean isBool() {
        return parameter instanceof Boolean;
    }

    public boolean isFloat() {
        return parameter instanceof Float;
    }

    public boolean isDouble() {
        return parameter instanceof Double;
    }
}
