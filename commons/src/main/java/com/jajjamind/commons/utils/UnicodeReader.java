/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jajjamind.commons.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class UnicodeReader extends Reader
{
    private static final Logger DEBUG_LOG;
    private static final int BOM_SIZE = 4;
    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int SEVENTEEN = 17;
    private static final int SIXTY_NINE = 69;
    private static final int SIXTY_FIVE = 65;
    private static final String UTF_32BE = "UTF-32BE";
    private static final String UTF_32LE = "UTF-32LE";
    private static final String UTF_8 = "UTF-8";
    private static final String UTF_16BE = "UTF-16BE";
    private static final String UTF_16LE = "UTF-16LE";
    private PushbackInputStream internalIn;
    private InputStreamReader internalIn2;
    private String defaultEnc;
    
    public UnicodeReader(final InputStream inputStream, final String defaultEnc) {
        this.internalIn2 = null;
        this.internalIn = new PushbackInputStream(inputStream, 4);
        this.defaultEnc = defaultEnc;
    }
    
    public String getDefaultEncoding() {
        return this.defaultEnc;
    }
    
    public String getEncoding() {
        UnicodeReader.DEBUG_LOG.debug("Enter UnicodeReader.getEncoding");
        String result = null;
        if (null == this.internalIn2) {
            UnicodeReader.DEBUG_LOG.debug("Exit UnicodeReader.getEncoding because internalIn2 == null");
        }
        else {
            result = this.internalIn2.getEncoding();
            UnicodeReader.DEBUG_LOG.info("param which is going out : result = {0}" ,result);
        }
        UnicodeReader.DEBUG_LOG.debug("Exit UnicodeReader.getEncoding");
        return result;
    }
    
    public void init() throws IOException {
        UnicodeReader.DEBUG_LOG.debug("Enter UnicodeReader.init");
        if (null != this.internalIn2) {
            UnicodeReader.DEBUG_LOG.debug("Exit UnicodeReader.init because internalIn2 != null");
            return;
        }
        String encoding = "";
        final byte[] bom = new byte[BOM_SIZE];
        final int num = this.internalIn.read(bom, ZERO, bom.length);
        int unread = 0;
        if (this.judgeTwobe(bom)) {
            encoding = UTF_32BE;
            unread = num - FOUR;
        }
        else if (this.judgeTwole(bom)) {
            encoding = UTF_32LE;
            unread = num - FOUR;
        }
        else if (this.judgeUtf8(bom)) {
            encoding = UTF_8;
            unread = num - THREE;
        }
        else if (bom[0] == -TWO && bom[1] == -1) {
            encoding = UTF_16BE;
            unread = num - TWO;
        }
        else if (bom[0] == -1 && bom[1] == -2) {
            encoding = UTF_16LE;
            unread = num - TWO;
        }
        else {
            encoding = this.defaultEnc;
            unread = num;
        }
        if (unread > 0) {
            this.internalIn.unread(bom, num - unread, unread);
        }
        if (null == encoding) {
            this.internalIn2 = new InputStreamReader(this.internalIn);
        }
        else {
            this.internalIn2 = new InputStreamReader(this.internalIn, encoding);
        }
        UnicodeReader.DEBUG_LOG.debug("Exit UnicodeReader.init");
    }
    
    private boolean judgeUtf8(final byte[] bom) {
        return bom[0] == -SEVENTEEN && bom[1] == -SIXTY_NINE && bom[2] == -SIXTY_FIVE;
    }
    
    private boolean judgeTwole(final byte[] bom) {
        return bom[0] == -1 && bom[1] == -2 && bom[2] == 0 && bom[3] == 0;
    }
    
    private boolean judgeTwobe(final byte[] bom) {
        return bom[0] == ZERO && bom[1] == ZERO && bom[2] == -2 && bom[3] == -1;
    }
    
    @Override
    public void close() throws IOException {
        UnicodeReader.DEBUG_LOG.debug("Enter UnicodeReader.close");
        if (null != this.internalIn2) {
            this.internalIn2.close();
        }
        UnicodeReader.DEBUG_LOG.debug("Exit UnicodeReader.close");
    }
    
    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        UnicodeReader.DEBUG_LOG.debug("Enter UnicodeReader.read");
        this.init();
        UnicodeReader.DEBUG_LOG.debug("Exit UnicodeReader.read");
        return this.internalIn2.read(cbuf, off, len);
    }
    
    static {
        DEBUG_LOG = LogManager.getLogger();
    }
}
