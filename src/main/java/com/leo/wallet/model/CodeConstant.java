package com.leo.wallet.model;

public class CodeConstant {

    public static final int APPROVE_CODE = 0;
    public static final int INTERNAL_ERROR = -1;
    /**
     * ********************************* Global ***************************
     */
    public static final int GLOBAL_URL_NOT_FOUND = 404;
    public static final String GLOBAL_URL_NOT_FOUND_MESSAGE = "global.error.url.notfound";

    public static final int GLOBAL_HTTP_MESSAGE_NOT_READABLE = 100;
    public static final String GLOBAL_HTTP_MESSAGE_NOT_READABLE_MESSAGE = "global.error.message.notreadable";

    public static final int GLOBAL_HTTP_MISSING_REQUEST_PARAMETER = 101;
    public static final String GLOBAL_HTTP_MISSING_REQUEST_PARAMETER_MESSAGE = "global.error.missing.parameter";

    public static final int GLOBAL_HTTP_MISSING_ARGUMENT_TYPE_PARAMETER = 102;
    public static final String GLOBAL_HTTP_MISSING_ARGUMENT_TYPE_PARAMETER_MESSAGE = "global.error.missing.type.parameter";

    public static final int DATA_NOT_FOUND = 10001;
    public static final String DATA_NOT_FOUND_MESSAGE = "data.notfound";

    /**
     * ********************************* Custom ***************************
     */
    public static final int BALANCE_NOT_ENOUGH = 10002;
    public static final String BALANCE_NOT_ENOUGH_MESSAGE = "balance.notenough";

    public static final int REQUEST_ID_DUPLICATE = 10003;
    public static final String REQUEST_ID_DUPLICATE_MESSAGE = "request.duplicate";

    public static final int INVALID_ID = 10004;
    public static final String INVALID_ID_MESSAGE = "invalid.id.player";

}
