package com.vcarvalho27.belzebase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VMC on 03/11/2016.
 */
public interface IDatabaseVersion {
    List<String> scriptList = new ArrayList<>();
    List<String> getScriptList();
}
