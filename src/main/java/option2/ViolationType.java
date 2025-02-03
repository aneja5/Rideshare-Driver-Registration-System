package option2;

/**
 * Enum representing different types of violations.
 */
public enum ViolationType {
    MOVING_VIOLATION,
    NON_MOVING_VIOLATION, SPEEDING;

    /**
     * Enum representing various types of moving violations.
     */
    public enum MovingViolation {
        DISTRACTED_DRIVING,
        RECKLESS_DRIVING,
        SPEEDING,
        DUI,
        TRAFFIC_SIGN,
        INVALID_LICENSE_INSURANCE;
    }

    /**
     * Enum representing various types of non-moving violations.
     */
    public enum NonMovingViolation {
        PARKING,
        PAPERWORK,
        VEHICLE_ISSUE;
    }
}
