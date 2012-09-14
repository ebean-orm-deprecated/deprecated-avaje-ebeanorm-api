package com.avaje.ebean.bean;

import java.io.Serializable;

/**
 * Represent the call stack (stack trace elements).
 * <p>
 * Used with a query to identify a CallStackQuery for AutoFetch automatic query
 * tuning.
 * </p>
 * <p>
 * This is used so that a single query called from different methods can be
 * tuned for each different call stack.
 * </p>
 * <p>
 * Note the call stack is trimmed to remove the common ebean internal elements.
 * </p>
 */
public final class CallStack implements Serializable {

  private static final long serialVersionUID = -8590644046907438579L;

  private final String zeroHash;
  private final String pathHash;

  private final StackTraceElement[] callStack;

  public CallStack(StackTraceElement[] callStack) {
    this.callStack = callStack;
    this.zeroHash = enc(callStack[0].hashCode());
    int hc = 0;
    for (int i = 1; i < callStack.length; i++) {
      hc = 31 * hc + callStack[i].hashCode();
    }
    this.pathHash = enc(hc);
  }

  /**
   * Return the first element of the call stack.
   */
  public StackTraceElement getFirstStackTraceElement() {
    return callStack[0];
  }

  /**
   * Return the call stack.
   */
  public StackTraceElement[] getCallStack() {
    return callStack;
  }

  /**
   * Return the hash for the first stack element.
   */
  public String getZeroHash() {
    return zeroHash;
  }

  /**
   * Return the hash for the stack elements (excluding first stack element).
   */
  public String getPathHash() {
    return pathHash;
  }

  public String toString() {
    return zeroHash + ":" + pathHash + ":" + callStack[0];
  }

  public String getOriginKey(int queryHash) {
    return zeroHash + "." + enc(queryHash) + "." + pathHash;
  }

  private static final int radix = 1 << 6;
  private static final int mask = radix - 1;

  /**
   * Convert the integer to unsigned base 64.
   */
  public static String enc(int i) {
    char[] buf = new char[32];
    int charPos = 32;
    do {
      buf[--charPos] = intToBase64[i & mask];
      i >>>= 6;
    } while (i != 0);

    return new String(buf, charPos, (32 - charPos));
  }

  private static final char intToBase64[] = {
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
      'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
      'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
  };
}
