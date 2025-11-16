package common.domain;

import java.time.Instant;
import java.util.Locale;
import java.util.Objects;

/**
 * Standard API response envelope for all HTTP endpoints.
 *
 * Example JSON:
 * {
 *   "status": "SUCCESS",
 *   "errorCode": null,
 *   "errorMessage": null,
 *   "timestamp": "2025-11-15T12:34:56Z",
 *   "data": { ... endpoint payload ... }
 * }
 */
public record ApiResponse<T>(
        String status,        // "SUCCESS" or "ERROR"
        String errorCode,     // e.g. "VALIDATION_ERROR", "INSUFFICIENT_FUNDS"
        String errorMessage,  // human-readable message
        Instant timestamp,
        T data                // actual payload, or null on error
) {

    public ApiResponse {
        Objects.requireNonNull(status, "status must not be null");
        status = status.toUpperCase(Locale.ROOT);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("SUCCESS", null,
                null, Instant.now(), data);
    }

    public static <T> ApiResponse<T> error(String errorCode, String errorMessage) {
        return new ApiResponse<>("ERROR", errorCode,
                errorMessage, Instant.now(), null);
    }
}
