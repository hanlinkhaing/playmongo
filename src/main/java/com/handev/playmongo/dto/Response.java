package com.handev.playmongo.dto;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private String message;
    private T data;

    public static <T> ResBuilder<T> builder() {
        return new ResBuilder<T>();
    }

    public static class ResBuilder<T> {
        private HttpStatus status;
        private String message;
        private T data;

        public ResBuilder<T> status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ResBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ResBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResponseEntity<Response<T>> build() {
            return ResponseEntity.status(this.status).body(new Response<T>(message, data));
        }
    }
}
