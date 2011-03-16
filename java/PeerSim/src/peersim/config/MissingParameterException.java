package peersim.config;

/**
 * Exception thrown to indicate that a
 * configuration property is not defined. It is thrown exclusively by
 * {@link Configuration}, since it is the only class that has access to the
 * set of defined properties.
 *
 * 如果一个Configuration属性没有定义，那么抛出这个异常。
 */
public class MissingParameterException extends RuntimeException {

// ================== initialization =====================================
// =======================================================================
    MissingParameterException(String name) {

        super("Parameter \"" + name + "\" not found.");
    }

    MissingParameterException(String name, String motivation) {

        super("Parameter \"" + name + "\" not found " + motivation);
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
