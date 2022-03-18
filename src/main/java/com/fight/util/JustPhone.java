package com.fight.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 判断用户输入的账手机号码是否符合要求
 *
/**
 *
 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
 * 此方法中前三位格式有：
 * 13+任意数
 * 145,147,149
 * 15+除4的任意数(不要写^4，这样的话字母也会被认为是正确的)
 * 166
 * 17+3,5,6,7,8
 * 18+任意数
 * 198,199
 *
 * @return 正确返回true
 * @throws PatternSyntaxException
 */
public class JustPhone {
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {

        String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }



}
