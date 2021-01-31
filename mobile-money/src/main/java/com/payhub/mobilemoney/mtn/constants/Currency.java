package com.payhub.mobilemoney.mtn.constants;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "currency")
@XmlEnum
public enum Currency {
    UGX, KSH
}
