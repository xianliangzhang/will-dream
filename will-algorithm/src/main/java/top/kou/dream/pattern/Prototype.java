package top.kou.dream.pattern;

import javax.swing.*;
import java.rmi.registry.Registry;

/**
 * 原型模式
 */
public class Prototype implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
