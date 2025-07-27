
package com.example.metaguard.demo;

import com.example.metaguard.aop.MetaField;
import java.util.Map;

public class AccountDto {
    @MetaField(key = "from")
    private String source;
    private Map<String,String> extInfo;
    private CreditAccount creditAccount;
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public Map<String,String> getExtInfo() { return extInfo; }
    public void setExtInfo(Map<String,String> extInfo) { this.extInfo = extInfo; }
    public CreditAccount getCreditAccount() { return creditAccount; }
    public void setCreditAccount(CreditAccount creditAccount) { this.creditAccount = creditAccount; }
}
