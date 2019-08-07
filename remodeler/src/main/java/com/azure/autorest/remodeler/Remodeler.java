package com.azure.autorest.remodeler;

import java.util.Collections;
import java.util.List;

public class Remodeler extends com.azure.autorest.extension.base.Extension {
    public List<String> getPluginNames() {
        return Collections.singletonList("remodeler");
    }
}
