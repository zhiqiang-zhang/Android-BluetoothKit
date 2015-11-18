package com.inuker.bluetooth.search.le;

import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;

import com.inuker.bluetooth.model.XmBluetoothDevice;
import com.inuker.bluetooth.search.BluetoothSearchResponse;
import com.inuker.bluetooth.search.BluetoothSearcher;
import com.inuker.bluetooth.utils.BluetoothUtils;

/**
 * @author liwentian
 */
public class BluetoothLESearcher extends BluetoothSearcher {

	private BluetoothLESearcher() {
		mBluetoothAdapter = BluetoothUtils.getBluetoothLeAdapter();
	}

	public static BluetoothLESearcher getInstance() {
		return BluetoothLESearcherHolder.instance;
	}

	private static class BluetoothLESearcherHolder {
		private static BluetoothLESearcher instance = new BluetoothLESearcher();
	}

	@Override
	public void startScanBluetooth(BluetoothSearchResponse response) {
		// TODO Auto-generated method stub
		super.startScanBluetooth(response);
		
		mBluetoothAdapter.startLeScan(mLeScanCallback);
	}

	@Override
	public void stopScanBluetooth() {
		// TODO Auto-generated method stub
		mBluetoothAdapter.stopLeScan(mLeScanCallback);
		super.stopScanBluetooth();
	}
	
	@Override
	protected void cancelScanBluetooth() {
		// TODO Auto-generated method stub
		mBluetoothAdapter.stopLeScan(mLeScanCallback);
		super.cancelScanBluetooth();
	}

	private final LeScanCallback mLeScanCallback = new LeScanCallback() {

		@Override
		public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
			// TODO Auto-generated method stub
			XmBluetoothDevice xmDevice = new XmBluetoothDevice(device, rssi, scanRecord, XmBluetoothDevice.DEVICE_TYPE_BLE);
			xmDevice.name = device.getName();
            notifyDeviceFounded(xmDevice);
		}
		
	};
}
