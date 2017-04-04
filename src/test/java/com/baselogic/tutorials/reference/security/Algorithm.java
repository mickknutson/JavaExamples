package com.baselogic.tutorials.reference.security;

/**
 * Created by mickknutson on 5/1/15.
 */
public final class Algorithm {

    String type;
    String mode;
    String padding;
    int keyLength;
    int targetSize;

    public Algorithm(String type, String mode, String padding, int size, int targetSize) {
        this.type = type;
        this.mode = mode;
        this.padding = padding;
        this.keyLength = size;
        this.targetSize = targetSize;
    }

    public String getTypeModePadding(){
        return this.type +"/"+ this.mode +"/"+ this.padding;
    }

    public String getType() {
        return type;
    }

    public String getMode() {
        return mode;
    }

    public String getPadding() {
        return padding;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public int getTargetSize() {
        return targetSize;
    }

    @Override
    public String toString() {
        return "Algorithm{" +
                "type='" + type + '\'' +
                ", mode='" + mode + '\'' +
                ", padding='" + padding + '\'' +
                ", keyLength='" + keyLength + '\'' +
                ", targetSize=" + targetSize +
                '}';
    }

} // The End...
