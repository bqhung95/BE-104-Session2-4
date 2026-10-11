package com.example.orderservice.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;   // "Order"
    private final String fieldName;      // "id"
    private final Object fieldValue;     // 999

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s = '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName    = fieldName;
        this.fieldValue   = fieldValue;
    }

    public String getResourceName() { return resourceName; }
    public String getFieldName()    { return fieldName; }
    public Object getFieldValue()   { return fieldValue; }
}
