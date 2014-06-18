package com.dci.intellij.dbn.language.common.element.util;

import com.dci.intellij.dbn.language.common.element.DBNElementType;
import com.dci.intellij.dbn.language.common.element.NamedElementType;
import com.dci.intellij.dbn.language.common.element.path.PathNode;

public class ElementTypeUtil {
    public static DBNElementType getEnclosingElementType(PathNode pathNode, ElementTypeAttribute elementTypeAttribute) {
        PathNode parentNode = pathNode.getParent();
        while (parentNode != null) {
            DBNElementType elementType = parentNode.getElementType();
            if (elementType.is(elementTypeAttribute)) return elementType;
            parentNode = parentNode.getParent();
        }
        return null;
    }

    public static NamedElementType getEnclosingNamedElementType(PathNode pathNode) {
        PathNode parentNode = pathNode.getParent();
        while (parentNode != null) {
            DBNElementType elementType = parentNode.getElementType();
            if (elementType instanceof NamedElementType) return (NamedElementType) elementType;
            parentNode = parentNode.getParent();
        }
        return null;
    }
    
}
