/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jajjamind.commons.utils;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.zip.*;
import java.io.*;
import java.util.*;


public abstract class IOUtil
{
    //private static final String GB_2312 = "GB2312";
    private static final Logger DEBUGLOG;
    private static final int SLEEPTIME = 3;
    
    public static String readFileByChars(final byte[] bytes, final String charSet) {
        if (null == bytes || bytes.length == 0) {
           
            IOUtil.DEBUGLOG.debug("Exit IoUtil.readFileByChars because null == bytes || bytes.length == 0");
            return "";
        }
        IOUtil.DEBUGLOG.debug("Enter IoUtil.readFileByChars and bytes=" + new String(bytes) + ", charSet =" + charSet);
        String str = null;
        if ("GB2312".equalsIgnoreCase(charSet)) {
            str = gb2Utf(bytes, charSet);
        }
        else {
            final InputStream inputStream = new ByteArrayInputStream(bytes);
            str = readInputStream(inputStream, charSet);
            if (null != inputStream) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    IOUtil.DEBUGLOG.error("in close error", e);
                }
            }
        }
        IOUtil.DEBUGLOG.debug("Exit IoUtil.readFileByChars");
        return (null == str || 0 == str.length()) ? "" : str;
    }
    
    public static String gb2Utf(final byte[] bytes, final String charSet) {
        IOUtil.DEBUGLOG.debug("Enter IoUtil.gb2Utf and bytes=" + new String(bytes) + ", charSet =" + charSet);
        if (!"GB2312".equalsIgnoreCase(charSet)) {
            IOUtil.DEBUGLOG.info("The content charSet is not GB2312");
            IOUtil.DEBUGLOG.debug("Exit IoUtil.gb2Utf because !'GB2312'.equalsIgnoreCase(charSet) == true");
            return "";
        }
        OutputStreamWriter outStreamWrite = null;
        ByteArrayOutputStream byteArrOutStr = null;
        String result = null;
        try {
            byteArrOutStr = new ByteArrayOutputStream();
            outStreamWrite = new OutputStreamWriter(byteArrOutStr, StandardCharsets.UTF_8);
            outStreamWrite.write(new String(bytes, "gb2312"));
            outStreamWrite.flush();
            result = readFileByChars(byteArrOutStr.toByteArray(), StandardCharsets.UTF_8.displayName());
        }
        catch (IOException e) {
            IOUtil.DEBUGLOG.error("If an I/O error occurs", e);
            IOUtil.DEBUGLOG.debug("Exit IoUtil.gb2Utf");
            return "";
        }
        finally {
            if (null != byteArrOutStr) {
                try {
                    byteArrOutStr.close();
                }
                catch (IOException e2) {
                    IOUtil.DEBUGLOG.error("bas close error", e2);
                }
            }
            if (null != outStreamWrite) {
                try {
                    outStreamWrite.close();
                }
                catch (IOException e2) {
                    IOUtil.DEBUGLOG.error("osw close error", e2);
                }
            }
        }
        IOUtil.DEBUGLOG.debug("Exit IoUtil.gb2Utf because result != null");
        return result;
    }
    
    private static String readInputStream(final InputStream inputStream, final String charSet) {
        IOUtil.DEBUGLOG.debug("Enter IoUtil.readInputStream and charSet=" + charSet);
        final StringBuffer stringBuff = new StringBuffer();
        BufferedReader reader = null;
        UnicodeReader unicode = null;
        try {
            unicode = new UnicodeReader(inputStream, charSet);
            reader = new BufferedReader(unicode);
            int tempchar = 0;
            while ((tempchar = reader.read()) != -1) {
                stringBuff.append((char)tempchar);
            }
        }
        catch (IOException e) {
            IOUtil.DEBUGLOG.error("If an I/O error occurs", e);
            IOUtil.DEBUGLOG.debug("Exit IoUtil.readInputStream");
            return "";
        }
        finally {
            if (null != unicode) {
                try {
                    unicode.close();
                }
                catch (IOException e2) {
                    IOUtil.DEBUGLOG.error("unicode close error", e2);
                }
            }
            if (null != reader) {
                try {
                    reader.close();
                }
                catch (IOException e2) {
                    IOUtil.DEBUGLOG.error("reader close error", e2);
                }
            }
        }
        IOUtil.DEBUGLOG.debug("Exit IoUtil.readInputStream");
        return (stringBuff.length() > 0) ? stringBuff.toString() : "";
    }
    
    public static byte[] convertEncoding(final byte[] buff, final String charSet) throws IOException {
        if (null == buff) {
            return null;
        }
        byte[] bytes = null;
        InputStream inputStream = null;
        UnicodeReader unicode = null;
        try {
            inputStream = new ByteArrayInputStream(buff);
            unicode = new UnicodeReader(inputStream, charSet);
            unicode.init();
            final String encoding = unicode.getEncoding();
            if (null != encoding) {
                bytes = convertToUtf(buff, encoding);
            }
        }
        catch (IOException e) {
            IOUtil.DEBUGLOG.error("convertEncoding failed: ", e);
            throw e;
        }
        finally {
            IOUtils.closeQuietly(inputStream,IOException::printStackTrace);
            IOUtils.closeQuietly((Reader)unicode,IOException::printStackTrace);
        }
        return bytes;
    }



    private static byte[] convertToUtf(final byte[] buff, final String encoding) {
        IOUtil.DEBUGLOG.debug("Enter IoUtil.convertToUtf");
        if (null == buff) {
            return null;
        }
        byte[] bytes = null;
        ByteArrayOutputStream byteStream = null;
        OutputStreamWriter outWriter = null;
        try {
            byteStream = new ByteArrayOutputStream();
            outWriter = new OutputStreamWriter(byteStream, StandardCharsets.UTF_8);
            outWriter.write(new String(buff, encoding));
            outWriter.flush();
            bytes = byteStream.toByteArray();
        }
        catch (Exception e) {
            IOUtil.DEBUGLOG.error("Convert to UTF exception", e);
        }
        finally {
            IOUtils.closeQuietly((OutputStream)byteStream,IOException::printStackTrace);
            IOUtils.closeQuietly((Writer)outWriter,IOException::printStackTrace);
        }
        IOUtil.DEBUGLOG.debug("Exit IoUtil.convertToUtf");
        return bytes;
    }
    
    public static void closeZipFile(ZipFile zipFile) {
        IOUtil.DEBUGLOG.debug("IOUtil.closeZipFile start...");
        try {
            if (null != zipFile) {
                zipFile.close();
                zipFile = null;
            }
        }
        catch (IOException e) {
            IOUtil.DEBUGLOG.error("closeZipFile failed: ", e);
        }
        IOUtil.DEBUGLOG.debug("IOUtil.closeZipFile end...");
    }
    
    public static byte[] readByte(final InputStream input) throws IOException {
        if (null == input) {
            return new byte[0];
        }
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[4096];
        int len = 0;
        byte[] result = null;
        try {
            while (0 < (len = input.read(buffer))) {
                out.write(buffer, 0, len);
            }
            out.flush();
            result = out.toByteArray();
        }
        finally {
            closeQuiet(out);
            closeQuiet(input);
        }
        return result;
    }
    
    public static byte[] readByte(final InputStream input, final String charset) throws IOException {
        if (null == input) {
            return new byte[]{};
        }
        InputStreamReader reader = null;
        byte[] buffer = null;
        try {
            reader = new InputStreamReader(input, charset);
            final StringBuilder cbuffer = new StringBuilder();
            int b;
            while (-1 != (b = reader.read())) {
                final char c = (char)b;
                cbuffer.append(c);
            }
            buffer = cbuffer.toString().getBytes(charset);
        }
        finally {
            closeQuiet(input);
            closeQuiet(reader);
        }
        return buffer;
    }
    
    public static byte[] readByte(final File file) throws IOException {
        final byte[] result = null;
        if (null == file) {
            return result;
        }
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(file);
            final InputStream in = new BufferedInputStream(fi);
            return readByte(in);
        }
        finally {
            closeQuiet(fi);
        }
    }
    
    public static List<String> getResult(final InputStream is) {
        BufferedReader br = null;
        String line = null;
        final List<String> returnStr = new ArrayList<>();
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while (true) {
                try {
                    line = br.readLine();
                    if (null != line) {
                        returnStr.add(line);
                        Thread.sleep(SLEEPTIME);
                        continue;
                    }
                }
                catch (IOException e) {
                    IOUtil.DEBUGLOG.error("Get Server Retrun Info Error. ", e);
                    returnStr.add(e.toString());
                    return returnStr;
                }
                catch (InterruptedException e2) {
                    IOUtil.DEBUGLOG.error("Interupt Thread Error. ", e2);
                    returnStr.add(e2.toString());
                    return returnStr;
                }
                catch (Exception e3) {
                    IOUtil.DEBUGLOG.error("Thread Error. ", e3);
                    returnStr.add(e3.toString());
                    return returnStr;
                }
                break;
            }
        }
        catch (Exception ex) {
            IOUtil.DEBUGLOG.error("checkOrderResult Error. ", ex);
            returnStr.add(ex.toString());
            return returnStr;
        }
        finally {
            closeQuiet(br);
            closeQuiet(is);
        }
        return returnStr;
    }
    
    public static void closeQuiet(final Closeable closeableStream) {
        if (null != closeableStream) {
            try {
                closeableStream.close();
            }
            catch (IOException e) {
                IOUtil.DEBUGLOG.error("close error", e);
            }
        }
    }
    
    public static void closeInputStream(InputStream inputStream) {
        if (null != inputStream) {
            try {
                inputStream.close();
                inputStream = null;
            }
            catch (IOException e) {
                IOUtil.DEBUGLOG.error("close inputStream error", e);
            }
        }
    }
    
    static {
        DEBUGLOG = LogManager.getLogger();
    }
}
