package com.inuker.bluetooth.search;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.inuker.bluetooth.model.XmBluetoothDevice;

/**
 * @author liwentian
 */
public class BluetoothDeviceHandler {
	
	private static final int MSG_DEVICE_FOUNDED = 0x11;
	
	private HandlerThread mWorkerThread;
	private Handler mDeviceHandler;
	
	private BluetoothDeviceHandler() {
		mWorkerThread = new HandlerThread("BluetoothDeviceHandler");
		mWorkerThread.start();

		mDeviceHandler = new Handler(mWorkerThread.getLooper()) {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				processBluetoothDeviceMessage(msg);
			}

		};
	}

	public static BluetoothDeviceHandler getInstance() {
		return BluetoothDeviceHandlerHolder.instance;
	}

	private static class BluetoothDeviceHandlerHolder {
		private static BluetoothDeviceHandler instance = new BluetoothDeviceHandler();
	}
	
	private void processBluetoothDeviceMessage(Message msg) {
		switch (msg.what) {
		case MSG_DEVICE_FOUNDED:
			BluetoothSearchResponse response = (BluetoothSearchResponse) msg.obj;
			XmBluetoothDevice device = msg.getData().getParcelable("device");
			processDeviceFounded(device, response);
			break;
		}
	}
	
	public void notifyDeviceFounded(XmBluetoothDevice device, BluetoothSearchResponse response) {
		Message msg = mDeviceHandler.obtainMessage(MSG_DEVICE_FOUNDED,
				response);
		Bundle bundle = new Bundle();
		bundle.putParcelable("device", device);
		msg.setData(bundle);
		msg.sendToTarget();
	}
	
	private void processDeviceFounded(XmBluetoothDevice device, BluetoothSearchResponse response) {
		device.name = device.device.getName();
		BluetoothSearchResponser.getInstance().notifyDeviceFounded(device, response);
	}
	
}
