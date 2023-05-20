package net.stellar.stellarcore.utils.chat.patterns;

/*
The code in class RainBowPattern, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from StellarCore, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.stellar.stellarcore.utils.chat.color.IridiumColorAPI;

import java.util.regex.Matcher;

public class RainbowPattern implements Pattern {

    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<RAINBOW([0-9]{1,3})>(.*?)</RAINBOW>");

    /**
     * Applies a rainbow pattern to the provided String.
     * Output might be the same as the input if this pattern is not present.
     *
     * @param string The String to which this pattern should be applied to
     * @return The new String with applied pattern
     */
    public String process(String string) {
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String saturation = matcher.group(1);
            String content = matcher.group(2);
            string = string.replace(matcher.group(), IridiumColorAPI.rainbow(content, Float.parseFloat(saturation)));
        }
        return string;
    }

}