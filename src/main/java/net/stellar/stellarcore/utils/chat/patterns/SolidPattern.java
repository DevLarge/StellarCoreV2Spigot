package net.stellar.stellarcore.utils.chat.patterns;

/*
The code in class SolidPattern, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from StellarCore, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.stellar.stellarcore.utils.chat.color.IridiumColorAPI;

import java.util.regex.Matcher;

public class SolidPattern implements Pattern {

    public static final java.util.regex.Pattern PATTERN = java.util.regex.Pattern.compile("\\{#([A-Fa-f0-9]{6})}|[&]?#([A-Fa-f0-9]{6})");

    /**
     * Applies a solid RGB color to the provided String.
     * Output might be the same as the input if this pattern is not present.
     *
     * @param string The String to which this pattern should be applied to
     * @return The new String with applied pattern
     */
    public String process(String string) {
        Matcher matcher = PATTERN.matcher(string);
        while (matcher.find()) {
            String color = matcher.group(1);
            if (color == null) color = matcher.group(2);

            string = string.replace(matcher.group(), IridiumColorAPI.getColor(color) + "");
        }
        return string;
    }

}
