package com.jajjamind.commons.utils;

import com.jajjamind.commons.time.DateTimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class RealTimeUtil {
	private static final Logger DEBUGLOG;
	private static long sequenceNumMaxValue;
	private static long sequenceNumMinValue;
	private static long sequenceNum;
	private static long streamNum;

	public static String generateTransactionId() {
		RealTimeUtil.DEBUGLOG.debug("DFT_OPER_FUNC_ENTER Enter TransactionId.generateTransactionId");
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			RealTimeUtil.DEBUGLOG.error("DFT_ERR_ERROR IP is null in TransactionId.generateTransactionId",
					new Object[] { e });
		}
		long sum = 0L;
		if (ip != null) {
			final String hostIP = ip.getHostAddress();
			final String hostI1P = hostIP.replaceAll("\\.", "");
			final long[] arr = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, Long.parseLong(hostI1P) };
			for (int i = 0; i < arr.length; ++i) {
				sum += arr[i];
			}
		}
		final String sumStr = String.valueOf(sum);
		final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		final Calendar cal = Calendar.getInstance();
		final String dateTime = dateFormat.format(cal.getTime());
		if (RealTimeUtil.sequenceNum == RealTimeUtil.sequenceNumMaxValue) {
			RealTimeUtil.sequenceNum = RealTimeUtil.sequenceNumMinValue;
		} else {
			++RealTimeUtil.sequenceNum;
		}
		final String result1 = sumStr + dateTime + String.format("%06d", RealTimeUtil.sequenceNum);
		RealTimeUtil.DEBUGLOG.debug("DFT_OPER_FUNC_EXIT Exit TransactionId.generateTransactionId");
		return formatTransactionId(result1);
	}

	public static String generateStreamNumber() {

		RealTimeUtil.DEBUGLOG.debug("DFT_OPER_FUNC_ENTER Enter TransactionId.generateStreamNumber");
		final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		final Calendar cal = Calendar.getInstance();
		final String dateTime = dateFormat.format(cal.getTime());
		if (RealTimeUtil.streamNum == RealTimeUtil.sequenceNumMaxValue) {
			RealTimeUtil.streamNum = RealTimeUtil.sequenceNumMinValue;
		} else {
			++RealTimeUtil.streamNum;
		}
		final String result1 = String.format("%05d", RealTimeUtil.streamNum) + dateTime;
		RealTimeUtil.DEBUGLOG.debug("DFT_OPER_FUNC_EXITExit TransactionId.generateStreamNumber");
		return result1;
	}

	public static String formatTransactionId(final String transactionId) {
		String id = transactionId;
		RealTimeUtil.DEBUGLOG.debug("DFT_OPER_FUNC_ENTEREnter method formatTransactionId");
		if (transactionId.length() >= 30) {
			id = transactionId.substring(transactionId.length() - 30);
		} else {
			for (int noOfZeros = 30 - transactionId.length(), i = 1; i <= noOfZeros; ++i) {
				id = '9' + id;
			}
		}
		RealTimeUtil.DEBUGLOG.debug("DFT_OPER_FUNC_EXITExit method formatTransactionId");
		return id;
	}

	public static String getFourDigitPasscode() {
		final int fromDigits = 1111;
		final int toDigits = 9999;

		Random randGenerator = new Random(System.currentTimeMillis());
		return String.valueOf(randGenerator.nextInt(toDigits)+fromDigits);

	}

	public static String externalId(String MSISDN) {
		final String mdate = DateTimeUtil.dateToString(DateTimeUtil.getCurrentUTCTime(),DateTimeUtil.YYYYMMDDHHMMSS);
		return mdate.substring(2, mdate.length()) +
				MSISDN.substring(4, MSISDN.length());
	}

	static {
		DEBUGLOG = LogManager.getLogger();
		RealTimeUtil.sequenceNumMaxValue = 999999L;
		RealTimeUtil.sequenceNumMinValue = 1L;
		RealTimeUtil.sequenceNum = 1L;
		RealTimeUtil.streamNum = 1L;
	}
}
