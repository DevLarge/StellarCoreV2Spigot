package net.stellar.stellarcore.handlers;

/*
The code in class RunnableHandler, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from StellarCore, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.stellar.stellarcore.StellarCore;
import net.stellar.stellarcore.runnables.RunnableAutoBrodacast;

public class RunnableHandler {

    public static void registerAll(StellarCore stellarCore) {
        new RunnableAutoBrodacast(stellarCore);
    }

}
