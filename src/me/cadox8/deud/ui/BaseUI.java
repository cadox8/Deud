package me.cadox8.deud.ui;

import lombok.AllArgsConstructor;
import me.cadox8.deud.nysvaui.NysvaUI;
import me.cadox8.deud.nysvaui.helpers.UIDimension;

@AllArgsConstructor
public abstract class BaseUI {

    private NysvaUI base;
    private UIDimension dimension;
}
