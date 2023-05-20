package net.stellar.stellarcore.utils.chat.patterns;

/*
The code in class GradientPattern, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from StellarCore, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.stellar.stellarcore.utils.chat.color.IridiumColorAPI;

import java.awt.*;
import java.util.regex.Matcher;

public class GradientPattern implements Pattern {

    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\{#([A-Fa-f0-9]{6})[>}](.*?)[<{]/#([A-Fa-f0-9]{6})}");

    /**
     * Applies a gradient pattern to the provided String.
     * Output might me the same as the input if this pattern is not present.
     *
     * @param string The String to which this pattern should be applied to
     * @return The new String with applied pattern
     */
    public String process(String string) {
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String start = matcher.group(1);
            String end = matcher.group(3);
            String content = matcher.group(2);
            string = string.replace(matcher.group(), IridiumColorAPI.color(content, new Color(Integer.parseInt(start, 16)), new Color(Integer.parseInt(end, 16))));
        }
        return string;
    }

}
