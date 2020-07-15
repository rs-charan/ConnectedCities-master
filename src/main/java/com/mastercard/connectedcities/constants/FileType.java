package com.mastercard.connectedcities.constants;

/**
 * Below Enum stores different file types
 * in which input can be expected
 */
public enum FileType {
    CSV("CSV"),
    ;

    private String fileType;
    FileType(String fileType) {
        this.fileType = fileType;
    }
}
