package peersim.config;

/**
 * Exception thrown to indicate that a
 * configuration property has an invalid value. It is thrown by
 * several methods in {@link Configuration} and can be thrown by any
 * component that reads the configuration.
 *
 * 如果一个Configuration的属性拥有一个无效的值，那么抛出这个异常
 */
public class IllegalParameterException extends RuntimeException {

// ================== initialization =====================================
// =======================================================================
    /**
     * Calls super constructor. It passes a string message which is the given
     * message, prefixed with the given property name.
     * @param name Name of configuration property that is invalid
     * @param message Additional info about why the value is invalid
     */
    public IllegalParameterException(String name, String message) {

        super("Parameter \"" + name + "\": " + message);
    }

// ================== methods ============================================
// =======================================================================
    /**
     * Extends message with info from stack trace.
     * It tries to guess what class called {@link Configuration} and
     * adds relevant info from the stack trace about it to the message.
     */
    public String getMessage() {

        StackTraceElement[] stack = getStackTrace();

        // Search the element that invoked Configuration
        // It's the first whose class is different from Configuration
        int pos;
        for (pos = 0; pos < stack.length; pos++) {
            if (!stack[pos].getClassName().equals(
                    Configuration.class.getName())) {
                break;
            }
        }

        return super.getMessage() + "\nAt "
                + getStackTrace()[pos].getClassName() + "."
                + getStackTrace()[pos].getMethodName() + ":"
                + getStackTrace()[pos].getLineNumber();
    }

    /**
     * Returns the exception message without stack trace information
     */
    public String getShortMessage() {
        return super.getMessage();
    }
}
