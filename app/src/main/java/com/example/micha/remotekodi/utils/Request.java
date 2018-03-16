package com.example.micha.remotekodi.utils;

/**
 * Builds get requests in jsonrpc format
 * Created by micha on 3/12/2018.
 */

public class Request{
    //Required
    private final String version = "2.0";
    private final String methodName;
    private final String[] args;
    private final String id;

    /**
     * Static class for builder pattern.
     */
    public static class Builder{

        private final String methodName;
        private String[] args;
        private String id;
        public Builder(String methodName){
            this.methodName = methodName;
        }

        public Builder addArgs(String...args){
            this.args = args;
            return this;
        }

        public Builder addID(String id){
            this.id = id;
            return this;
        }

        public Request build(){
            return new Request(this);
        }
    }

    /**
     * Request constructor takes in a static builder object to get values from.
     * @param builder Static builder.
     */
    private Request(Builder builder) {
        methodName = builder.methodName;
        args = builder.args;
        id = builder.id;
    }

    @Override
    public String toString() {
        if(args == null){
            return String.format("{\"jsonrpc\":\"%s\",\"method\":" +
                    "\"%s\",\"id\":\"%s\"}",version,methodName,id);
        }

        String start = String.format("{\"jsonrpc\":\"%s\",\"method\":\"%s\",\"params\":{",version,methodName);
        for (String argument : args) {
            start = start.concat(argument);
            start = start.concat(",");
        }
        start = start.substring(0, start.length() - 1);
        return start.concat(String.format("},\"id\":\"%s\"}",id));
    }
}
