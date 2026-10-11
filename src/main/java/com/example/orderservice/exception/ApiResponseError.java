package com.example.orderservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)   // bỏ field null khi serialize
public class ApiResponseError {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private int status;            // HTTP status code: 400, 404, 500...
    private String error;          // Tên ngắn gọn: "Not Found", "Bad Request"
    private String message;        // Thông báo chi tiết cho người dùng
    private String path;           // URI gây lỗi (optional, hữu ích khi debug)
    private Map<String, String> details;   // Lỗi validation theo field (optional)

    // Constructor rỗng
    public ApiResponseError() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor đầy đủ (dùng Builder bên dưới cho gọn)
    private ApiResponseError(Builder b) {
        this.timestamp = LocalDateTime.now();
        this.status    = b.status;
        this.error     = b.error;
        this.message   = b.message;
        this.path      = b.path;
        this.details   = b.details;
    }

    // ===== Getters =====
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus()              { return status; }
    public String getError()            { return error; }
    public String getMessage()          { return message; }
    public String getPath()             { return path; }
    public Map<String, String> getDetails() { return details; }

    // ===== Builder Pattern =====
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private int status;
        private String error;
        private String message;
        private String path;
        private Map<String, String> details;

        public Builder status(int status)      { this.status = status; return this; }
        public Builder error(String error)     { this.error = error; return this; }
        public Builder message(String message) { this.message = message; return this; }
        public Builder path(String path)       { this.path = path; return this; }
        public Builder details(Map<String,String> d) { this.details = d; return this; }

        public ApiResponseError build() { return new ApiResponseError(this); }
    }
}
