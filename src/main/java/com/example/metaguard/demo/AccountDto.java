
package com.example.metaguard.demo;

import com.example.metaguard.aop.MetaField;
import java.util.Map;

public class AccountDto {
    @MetaField(key = "from")
    private String source;
    @MetaField(key = "sysFieldId")
    private String sysFieldId;
    private Map<String,String> extInfo;
    private CreditAccount creditAccount;
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getSysFieldId() { return sysFieldId; }
    public void setSysFieldId(String sysFieldId) { this.sysFieldId = sysFieldId; }
    public Map<String,String> getExtInfo() { return extInfo; }
    public void setExtInfo(Map<String,String> extInfo) { this.extInfo = extInfo; }
    public CreditAccount getCreditAccount() { return creditAccount; }
    public void setCreditAccount(CreditAccount creditAccount) { this.creditAccount = creditAccount; }
}
