package com.geeksville.apiproxy;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * These are low level routines called by the GCS to hook into the proxy.
 * 
 * @author kevinh
 * 
 */
public interface GCSHooks {
	/**
	 * Provide the callbacks for the GCS. GCS must call this routine before
	 * calling any other API functions.
	 * 
	 * @param cb
	 */
	void setCallback(GCSCallback cb);

	/**
	 * GCS must call this for ever mavlink packet received or sent from the
	 * vehicle
	 * 
	 * @param bytes
	 *            the packet
	 * @param fromInterface
	 *            the interface # this data arrived on (or -1 if generated by
	 *            the GCS itself)
	 * @throws IOException
	 */
	void filterMavlink(int fromInterface, byte[] bytes) throws IOException;

	/**
	 * Connect to web service
	 * 
	 * @param userName
	 * @param password
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	void loginUser(String userName, String password)
			throws UnknownHostException, IOException;

	/**
	 * Associate a server vehicleId string with a particular mavlink sysId. GCS
	 * must call this for every vehicle that is connected.
	 * 
	 * @param vehicleId
	 *            a UUID for this vehicle, if the server has never seen this
	 *            UUID before, a new vehicle record will be created
	 * @param fromInterface
	 *            the interface # this vehicle is connected on
	 * @param mavlinkSysId
	 *            the mavlink sysid for this vehicle
	 * @throws IOException
	 */
	void setVehicleId(String vehicleId, int fromInterface, int mavlinkSysId)
			throws IOException;

	/**
	 * Send any queued messages immedately
	 * 
	 * @throws IOException
	 */
	void flush() throws IOException;
}
