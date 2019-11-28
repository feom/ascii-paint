package com.zuhlke.paint.ascii.command;

/**
 * The {@code CommandCode} holds codes for all available commands and an unknown command.
 */
public enum CommandCode {
    C("C"), L("L"), R("R"), B("B"), Q("Q"), UNKNOWN("UNKNOWN");

    private final String commandCode;

    CommandCode(String commandCode) {
        this.commandCode = commandCode;
    }

    public static CommandCode fromString(String inputCode) {
        for (CommandCode code : CommandCode.values()) {
            if (code.commandCode.equalsIgnoreCase(inputCode)) {
                return code;
            }
        }

        return UNKNOWN;
    }
}
