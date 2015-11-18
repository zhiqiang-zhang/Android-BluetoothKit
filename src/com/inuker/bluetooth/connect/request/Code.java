package com.inuker.bluetooth.connect.request;

public class Code {

	public static final int REQUEST_SUCCESS = 0;
	public static final int REQUEST_FAILED = -1;
	public static final int ILLEGAL_ARGUMENT = -2;
	public static final int BLE_NOT_SUPPORTED = -3;
	public static final int BLUETOOTH_DISABLED = -4;
	public static final int CONNECTION_NOT_READY = -6;
	
	public static String toString(int code) {
		switch (code) {
		case REQUEST_SUCCESS:
			return "REQUEST_SUCCESS";
		case REQUEST_FAILED:
			return "REQUEST_FAILED";
		case ILLEGAL_ARGUMENT:
			return "ILLEGAL_ARGUMENT";
		case BLE_NOT_SUPPORTED:
			return "BLE_NOT_SUPPORTED";
		case BLUETOOTH_DISABLED:
			return "BLUETOOTH_DISABLED";
		case CONNECTION_NOT_READY:
			return "CONNECTION_NOT_READY";
		default:
			return "unknown code: " + code;
		}
	}
}
